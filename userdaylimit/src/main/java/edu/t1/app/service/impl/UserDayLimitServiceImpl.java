package edu.t1.app.service.impl;

import edu.t1.app.enums.Operation;
import edu.t1.app.exception.UserDayLimitCreateException;
import edu.t1.app.exception.UserDayLimitUpdateException;
import edu.t1.app.exception.UserNotFoundException;
import edu.t1.app.mapper.UserDayLimitDtoToEntityMapper;
import edu.t1.app.mapper.UserDayLimitEntityToDtoMapper;
import edu.t1.app.model.UserDayLimitEntity;
import edu.t1.app.model.UserDayLimitOperation;
import edu.t1.app.model.UserDayLimitRequest;
import edu.t1.app.model.dto.UserDayLimitDto;
import edu.t1.app.model.dto.UserDto;
import edu.t1.app.repository.UserDayLimitRepository;
import edu.t1.app.service.UserDayLimitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDayLimitServiceImpl implements UserDayLimitService {
    private final UserDayLimitRepository userDayLimitRepository;
    private final UserDayLimitEntityToDtoMapper userDayLimitEntityToDtoMapper;
    private final UserDayLimitDtoToEntityMapper userDayLimitDtoToEntityMapper;
    private final RestClient usersRestClient;
    @Value("${app.day-limit}")
    private BigDecimal defaultUserDayLimit;


    @Override
    public UserDayLimitDto create(UserDayLimitRequest userDayLimitRequest) {
        if (userDayLimitRequest == null || userDayLimitRequest.getUsername() == null)
            throw new UserDayLimitCreateException("Передан пустой объект");
        ResponseEntity<UserDto> userDtoResponse = usersRestClient.get().uri(uriBuilder -> uriBuilder.pathSegment("v1", "users")
                        .queryParam("username", userDayLimitRequest.getUsername()).build())
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> {
                    throw new UserNotFoundException("Пользователь с логином " + userDayLimitRequest.getUsername() + " не найден");
                })
                .toEntity(new ParameterizedTypeReference<>() {
                });
        if (userDtoResponse.getStatusCode().value() != 200) {
            throw new UserNotFoundException("Ошибка получения пользователя " + userDayLimitRequest.getUsername());
        }
        Optional<UserDayLimitEntity> dayLimitEntityOptional = userDayLimitRepository.findByUsername(userDayLimitRequest.getUsername());
        if (dayLimitEntityOptional.isPresent())
            throw new UserDayLimitCreateException("Лимит пользователя " + userDayLimitRequest.getUsername() + " уже существует");
        UserDayLimitDto userDayLimitDto = new UserDayLimitDto();
        userDayLimitDto.setDefaultDayLimit(userDayLimitRequest.getDefaultUserDayLimit() != null ?
                userDayLimitRequest.getDefaultUserDayLimit() : defaultUserDayLimit);
        userDayLimitDto.setCurrentDayLimit(userDayLimitDto.getDefaultDayLimit());
        userDayLimitDto.setUsername(userDayLimitRequest.getUsername());
        return userDayLimitEntityToDtoMapper
                .apply(userDayLimitRepository.save(userDayLimitDtoToEntityMapper.apply(userDayLimitDto)));
    }

    @Override
    public void resetLimits() {
        List<UserDayLimitEntity> userDayLimitEntityList = userDayLimitRepository.findAll();
        userDayLimitEntityList.forEach(u -> u.setCurrentUserDayLimit(u.getDefaultUserDayLimit()));
        userDayLimitRepository.saveAll(userDayLimitEntityList);
    }

    @Override
    public void update(UserDayLimitDto userDayLimitDto) {
        if (userDayLimitDto.getId() == null)
            throw new UserDayLimitUpdateException("Передан пустой id лимита пользователя");
        userDayLimitRepository.save(userDayLimitDtoToEntityMapper.apply(userDayLimitDto));
    }

    @Override
    public UserDayLimitDto changeLimit(UserDayLimitOperation userDayLimitOperation) {
        if (userDayLimitOperation.getUsername() == null) {
            log.error("Передан пустой логин пользователя");
            throw new UserDayLimitUpdateException("Передан пустой логин пользователя");
        }
        UserDayLimitEntity userDayLimitEntity;
        Optional<UserDayLimitEntity> updateUserLimitOpt = userDayLimitRepository.findByUsername(userDayLimitOperation.getUsername());
        //Если лимит не найден создаем новый
        if (updateUserLimitOpt.isEmpty()) {
            userDayLimitEntity = new UserDayLimitEntity();
            userDayLimitEntity.setCurrentUserDayLimit(defaultUserDayLimit);
            userDayLimitEntity.setDefaultUserDayLimit(defaultUserDayLimit);
            userDayLimitEntity.setUsername(userDayLimitOperation.getUsername());
        } else {
            userDayLimitEntity = updateUserLimitOpt.get();
        }
        //Уменьшение текущего лимита
        if (userDayLimitOperation.getOperation().equals(Operation.WITHDROW)) {
            log.info("Уменьшение лимита пользователя: {}", userDayLimitOperation.getUsername());
            userDayLimitEntity.setCurrentUserDayLimit(userDayLimitEntity.getCurrentUserDayLimit().subtract(userDayLimitOperation.getAmount()));
        }
        //Пополнение текущего лимита
        if (userDayLimitOperation.getOperation().equals(Operation.DEPOSIT)) {
            log.info("Увеличение лимита пользователя: {}", userDayLimitOperation.getUsername());
            userDayLimitEntity.setCurrentUserDayLimit(userDayLimitEntity.getCurrentUserDayLimit().add(userDayLimitOperation.getAmount()));
        }
        return userDayLimitEntityToDtoMapper.apply(userDayLimitRepository.save(userDayLimitEntity));
    }
}

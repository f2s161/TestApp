package edu.t1.app;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestStream {
    public static void main(String[] args) {
        _1();
        _2();
        _3();
        _4();
        _5();
        _6();
        _7();
        _8();
    }

    private static void _1() {
        List<Integer> numbers = new ArrayList<>(List.of(5, 2, 10, 9, 4, 3, 10, 1, 13, 10));
        Optional<Integer> value = numbers.stream().sorted().skip(numbers.size() - 3).limit(1).findFirst();
        value.ifPresent(System.out::println);
    }

    private static void _2() {
        List<Integer> numbers2 = new ArrayList<>(List.of(5, 2, 10, 9, 4, 3, 10, 1, 13, 10));
        Optional<Integer> value2 = numbers2.stream().distinct().sorted(Comparator.reverseOrder()).limit(3).min(Integer::compareTo);
        value2.ifPresent(System.out::println);
    }

    private static void _3() {
        Empoyee empoyee = new Empoyee("A", 21, "Инженер");
        Empoyee empoyee2 = new Empoyee("B", 23, "Инженер");
        Empoyee empoyee3 = new Empoyee("C", 24, "Инженер");
        Empoyee empoyee4 = new Empoyee("D", 27, "Инженер");
        Empoyee empoyee5 = new Empoyee("E", 40, "Директор");
        Empoyee empoyee6 = new Empoyee("F", 35, "Бухгалтер");
        List<String> empoyees = Stream.of(empoyee, empoyee2, empoyee3, empoyee4, empoyee5, empoyee6).filter(i -> i.position.equals("Инженер"))
                .sorted(Comparator.comparing(Empoyee::getAge).reversed()).limit(3).map(e -> e.name).toList();
        System.out.println(empoyees);
    }

    private static void _4() {
        Empoyee empoyee = new Empoyee("A", 21, "Инженер");
        Empoyee empoyee2 = new Empoyee("B", 23, "Инженер");
        Empoyee empoyee3 = new Empoyee("C", 24, "Инженер");
        Empoyee empoyee4 = new Empoyee("D", 27, "Инженер");
        Empoyee empoyee5 = new Empoyee("E", 40, "Директор");
        Empoyee empoyee6 = new Empoyee("F", 35, "Бухгалтер");
        Double averageAge = Stream.of(empoyee, empoyee2, empoyee3, empoyee4, empoyee5, empoyee6).filter(i -> i.position.equals("Инженер"))
                .collect(Collectors.averagingInt(e -> e.age));
        System.out.println(averageAge);
    }

    private static void _5() {
        List<String> strings = new ArrayList<>(List.of("sfdfsd", "wrerrewrr", "fsdfdsdsffdsfssdsd", "etrtrert", "bgfg", "were", "werewewrerwer"));
        Optional<String> maxLength = strings.stream().sorted(Comparator.comparing(String::length).reversed()).limit(1).findFirst();
        maxLength.ifPresent(System.out::println);
    }

    private static void _6() {
        String inputString = "test test and or and or test";
        Map<String, Long> stringsMap = Arrays.stream(inputString.split(" ")).collect(Collectors.groupingBy(s -> s, Collectors.counting()));
        System.out.println(stringsMap);
    }
    private static void _7() {
        String inputString = "test abcd and or xyz or 1est";
        List<String> strings = Arrays.stream(inputString.split(" ")).sorted(Comparator.comparing(String::length)
                .thenComparing(Comparator.naturalOrder())).toList();
        System.out.println(strings);
    }

    private static void _8() {
        String[] strings = {"xyz or 1est sfdfsd wrerrewrr",
                "fsdfdsdsffdsfssdsd etrtrert bgfg were wewrerwer"};
        Optional<String>maxLengthStr = Stream.of(strings).map(list->List.of(list.split(" ")))
                .flatMap(Collection::stream).max(Comparator.comparing(String::length));
        maxLengthStr.ifPresent(System.out::println);
    }
}

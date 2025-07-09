package edu.t1.app;

import edu.t1.annotation.*;

public class Sample {

    @BeforeTest
    void beforeTest() {
    }

    @BeforeSuite
    static void beforeSuite() {
    }

    @AfterSuite
    static void afterSuite() {
    }

    @Test(priority = 1)
    void A() {
    }

    @Test(priority = 3)
    void B() {
    }
    @Test(priority = 6)
    void C() {
    }
    @Test(priority = 4)
    void D() {
    }

    @Test
    void E() {
    }
    @Test(priority = 2)
    void F() {
    }

    @CsvSource(params = "1,test,2.0,true")
    void G(int a, String b, Double c, boolean d) {
    }

    @AfterTest
    void afterTest() {
    }
}

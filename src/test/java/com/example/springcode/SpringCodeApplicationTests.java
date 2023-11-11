package com.example.springcode;

import com.example.springcode.step06.test.TestBeanFactory06;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringCodeApplicationTests {
    @Test
    void testBeanFactory() {
        TestBeanFactory06 test = new TestBeanFactory06();
        test.test();
    }
}



package com.example.springcode;

import com.example.springcode.step04.beans.test.TestBeanFactory04;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringCodeApplicationTests {
    @Test
    void testBeanFactory() {
        TestBeanFactory04 test = new TestBeanFactory04();
        test.test();
    }
}



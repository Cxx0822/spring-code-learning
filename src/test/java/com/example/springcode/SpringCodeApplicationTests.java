package com.example.springcode;

import com.example.springcode.step04.beans.test.TestBeanFactory04;
import com.example.springcode.step05.beans.test.TestBeanFactory05;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringCodeApplicationTests {
    @Test
    void testBeanFactory() {
        TestBeanFactory05 test = new TestBeanFactory05();
        test.test();
    }
}



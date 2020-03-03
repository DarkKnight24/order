package com.movie.order;

import com.movie.base.interfaces.UserClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OrderApplicationTests {

    @Autowired
    private UserClient userClient;
    @Test
    void contextLoads() {
        System.out.println(userClient.getUserDetail(17L));
    }

}

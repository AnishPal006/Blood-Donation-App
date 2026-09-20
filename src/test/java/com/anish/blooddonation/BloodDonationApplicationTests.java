package com.anish.blooddonation;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@org.springframework.test.context.ActiveProfiles("test")
@org.springframework.boot.test.mock.mockito.MockBean(org.springframework.data.redis.connection.RedisConnectionFactory.class)
class BloodDonationApplicationTests {

    @Test
    void contextLoads() {
    }

}

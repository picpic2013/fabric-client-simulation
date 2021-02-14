package site.grec.fabricclientsimulation.database.mongo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import site.grec.fabricclientsimulation.dao.user.BasicUser;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class BasicUserRepositoryTest {

    @Autowired
    BasicUserRepository basicUserRepository;

    @Test
    void saveUser() {
        basicUserRepository.saveUser(new BasicUser(
                "userID".getBytes(StandardCharsets.UTF_8),
                100.07,
                new ArrayList<>(Arrays.asList(
                        "Device1",
                        "Device2"
                ))
        ));
    }

    @Test
    void getUserByID() {
        BasicUser user = basicUserRepository.getUserByID("userID".getBytes(StandardCharsets.UTF_8));
        log.info(user.toString());
    }

    @Test
    void getNotExistUser() {
        BasicUser user = basicUserRepository.getUserByID("Not Exist!".getBytes(StandardCharsets.UTF_8));
        assertNull(user);
    }

    @Test
    void updateUser() {
        BasicUser user = basicUserRepository.getUserByID("userID".getBytes(StandardCharsets.UTF_8));
        user.getDevices().add("Device3");

        basicUserRepository.updateUser(user);
        user = basicUserRepository.getUserByID("userID".getBytes(StandardCharsets.UTF_8));
        log.info(user.toString());
    }

    @Test
    void deleteByID() {
        basicUserRepository.deleteByID("userID".getBytes(StandardCharsets.UTF_8));

        BasicUser user = basicUserRepository.getUserByID("userID".getBytes(StandardCharsets.UTF_8));
        assertNull(user);
    }
}
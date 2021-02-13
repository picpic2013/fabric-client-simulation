package site.grec.fabricclientsimulation.database.mongo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import site.grec.fabricclientsimulation.dao.user.MultiUser;
import site.grec.fabricclientsimulation.dao.user.UserProposal;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class MultiUserRepositoryTest {
    @Autowired
    MultiUserRepository multiUserRepository;

    @Test
    void saveUser() {
        multiUserRepository.saveUser(new MultiUser(
                "userID",
                "Divident description",
                new ArrayList<>(Arrays.asList(
                        "sub user 1",
                        "sub user 2"
                )),
                new ArrayList<>(Arrays.asList(
                        .0, .0, .0
                )),
                new ArrayList<>(Arrays.asList(
                        0.1, 0.5, 0.4
                )),
                new ArrayList<>()
        ));
    }

    @Test
    void getUserByID() {
        MultiUser user = multiUserRepository.getUserByID("userID");
        log.info(user.toString());
    }

    @Test
    void getNotExistUser() {
        MultiUser user = multiUserRepository.getUserByID("Not Exist!");
        assertNull(user);
    }

    @Test
    void updateUser() {
        MultiUser user = multiUserRepository.getUserByID("userID");
        ArrayList<Double> lockedIncome = user.getLockedIncome();
        for (int i = 0; i < lockedIncome.size(); ++i) {
            lockedIncome.set(i, lockedIncome.get(i) + 100);
        }
        user.setLockedIncome(lockedIncome);

        ArrayList<UserProposal> proposals = user.getProposal();
        HashMap<String, Date> map = new HashMap<>();
        map.put("user1", new Date());
        map.put("user2", null);
        proposals.add(new UserProposal(
                "change description",
                map
        ));
        user.setProposal(proposals);

        multiUserRepository.updateUser(user);
        log.info(user.toString());

        user = multiUserRepository.getUserByID("userID");
        log.info(user.toString());
    }

    @Test
    void deleteByID() {
        multiUserRepository.deleteByID("userID");

        MultiUser user = multiUserRepository.getUserByID("userID");
        assertNull(user);
    }
}
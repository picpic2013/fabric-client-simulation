package site.grec.fabricclientsimulation.database.mongo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import site.grec.fabricclientsimulation.dao.copyright.Copyright;
import site.grec.fabricclientsimulation.dao.copyright.CopyrightInfo;
import site.grec.fabricclientsimulation.dao.copyright.PiracyInfo;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class CopyrightRepositoryTest {
    @Autowired
    CopyrightRepository copyrightRepository;

    @Test
    void createObjectTest() {
        Copyright copyright = new Copyright(
                "123456",
                "789111",
                0.732,
                new CopyrightInfo(
                        "description",
                        new ArrayList<>(Arrays.asList(
                                "feature1",
                                "feature2"
                        )),
                        3.1415926
                ),
                new ArrayList<>(),
                false, false,
                new ArrayList<>(),
                new ArrayList<>(Arrays.asList(
                        "CEK1",
                        "CEK2"
                ))
        );

        log.info(copyright.toString());
    }

    @Test
    void saveTest() {
        copyrightRepository.saveCopyright(new Copyright(
                "123456",
                "789111",
                0.732,
                new CopyrightInfo(
                        "description",
                        new ArrayList<>(Arrays.asList(
                                "feature1",
                                "feature2"
                        )),
                        3.1415926
                ),
                new ArrayList<>(),
                false, false,
                new ArrayList<>(),
                new ArrayList<>(Arrays.asList(
                        "CEK1",
                        "CEK2"
                ))
        ));
    }

    @Test
    void searchTest() {
        log.info(
                copyrightRepository.getCopyrightByID("123456").toString()
        );
    }

    @Test
    void searchFailTest() {
        Copyright copyright = copyrightRepository.getCopyrightByID("not exist");
        assertNull(copyright);
    }

    @Test
    void updateTest() {
        Copyright copyright = copyrightRepository.getCopyrightByID("123456");
        copyright.getCEK().add("CEK3");
        copyrightRepository.updateCopyright(copyright);

        copyright = copyrightRepository.getCopyrightByID("123456");
        log.info(copyright.toString());
    }

    @Test
    void deleteTest() {
        copyrightRepository.deleteByID("123456");

        Copyright copyright = copyrightRepository.getCopyrightByID("not exist");
        assertNull(copyright);
    }
}
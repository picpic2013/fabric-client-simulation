package site.grec.fabricclientsimulation.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import site.grec.fabricclientsimulation.utils.sign.SignatureFunction;

import java.security.PublicKey;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class UserServiceTest {
    @Autowired
    SignatureFunction signatureFunction;

    @Test
    void test0() {
        PublicKey pk = signatureFunction.generateKeyPair().getPublic();
        log.info(Arrays.toString(pk.getEncoded()) + "");
    }
}
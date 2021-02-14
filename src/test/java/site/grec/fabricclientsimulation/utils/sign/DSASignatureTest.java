package site.grec.fabricclientsimulation.utils.sign;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class DSASignatureTest {
    @Autowired
    SignatureFunction signatureFunction;

    @Test
    void test0() {
        try {
            KeyPairGenerator dsa = KeyPairGenerator.getInstance("DSA");
            dsa.initialize(1024);
            KeyPair keyPair = dsa.generateKeyPair();
            PrivateKey sk = keyPair.getPrivate();
            PublicKey pk = keyPair.getPublic();

//            System.out.println(sk);
//            System.out.println(pk);

            String msg = "This is a message";
            byte[] msgBytes = msg.getBytes(StandardCharsets.UTF_8);

            Signature s = Signature.getInstance("SHA256withDSA");
            s.initSign(sk);
            s.update(msgBytes);
            byte[] signedBytes = s.sign();

            Signature v = Signature.getInstance("SHA256withDSA");
            v.initVerify(pk);
            v.update(msgBytes);
            boolean isValid = v.verify(signedBytes);
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            e.printStackTrace();
        }
    }

    @Test
    void test1() {
        KeyPair keyPair = signatureFunction.generateKeyPair();
        PrivateKey sk = keyPair.getPrivate();
        PublicKey pk = keyPair.getPublic();

        String msg = "This is a message";
        byte[] msgBytes = msg.getBytes(StandardCharsets.UTF_8);

        byte[] signature = signatureFunction.signature(sk, msgBytes);

        boolean verify = signatureFunction.verify(pk, signature, msgBytes);

        log.info(verify + "");

        assertTrue(verify);
    }

    @Test
    void bytes2PrivateKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeyPair keyPair = signatureFunction.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        byte[] encoded = privateKey.getEncoded();

        KeyFactory kf = KeyFactory.getInstance("DSA");

        PrivateKey privateKey1 = kf.generatePrivate(new PKCS8EncodedKeySpec(encoded));

        log.info(privateKey.toString());
        log.info(privateKey1.toString());

        assertEquals(privateKey, privateKey1);
    }

    @Test
    void bytes2PublicKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeyPair keyPair = signatureFunction.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        byte[] encoded = publicKey.getEncoded();

        KeyFactory kf = KeyFactory.getInstance("DSA");

        PublicKey publicKey1 = kf.generatePublic(new X509EncodedKeySpec(encoded));

        assertEquals(publicKey, publicKey1);
    }

    @Test
    void bytes2Key() {
        KeyPair keyPair = signatureFunction.generateKeyPair();
        PublicKey pk = keyPair.getPublic();
        PrivateKey sk = keyPair.getPrivate();

        byte[] pkEncoded = pk.getEncoded();
        byte[] skEncoded = sk.getEncoded();

        PublicKey pk1 = signatureFunction.getPkByBytes(pkEncoded);
        PrivateKey sk1 = signatureFunction.getSkByBytes(skEncoded);

        KeyPair keyPair1 = signatureFunction.generateKeyPair();
        PublicKey pk2 = keyPair1.getPublic();
        PrivateKey sk2 = keyPair1.getPrivate();

        assertEquals(pk, pk1);
        assertEquals(sk, sk1);
        assertNotEquals(pk1, pk2);
        assertNotEquals(sk1, sk2);
    }
}
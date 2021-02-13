package site.grec.fabricclientsimulation.utils.sign;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.security.*;

import static org.junit.jupiter.api.Assertions.*;

class DSASignatureTest {

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

            System.out.println(isValid);

        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            e.printStackTrace();
        }
    }
}
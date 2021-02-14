package site.grec.fabricclientsimulation.utils.sign;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.*;
import java.security.spec.DSAPrivateKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

@Slf4j
@Data
public class DSASignature implements SignatureFunction {
    @Autowired
    SignatureConfig signatureConfig;

    @Override
    public KeyPair generateKeyPair() {
        KeyPairGenerator dsa;
        try {
            dsa = KeyPairGenerator.getInstance("DSA");
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage());
            return null;
        }
        dsa.initialize(signatureConfig.getKeySize());
        return dsa.generateKeyPair();
    }

    @Override
    public byte[] signature(PrivateKey sk, byte[] content) {
        Signature s;
        byte[] result = null;
        try {
            s = Signature.getInstance("SHA256withDSA");
            s.initSign(sk);
            s.update(content);
            result = s.sign();
        } catch (NoSuchAlgorithmException
                | SignatureException
                | InvalidKeyException e) {
            log.error(e.getMessage());
        }
        return result;
    }

    @Override
    public boolean verify(PublicKey pk, byte[] signedBytes, byte[] msgBytes) {
        Signature v;
        boolean isValid = false;
        try {
            v = Signature.getInstance("SHA256withDSA");
            v.initVerify(pk);
            v.update(msgBytes);
            isValid = v.verify(signedBytes);
        } catch (NoSuchAlgorithmException
                | SignatureException
                | InvalidKeyException e) {
            log.error(e.getMessage());
        }
        return isValid;
    }

    @Override
    public PrivateKey getSkByBytes(byte[] bytes) {
        KeyFactory kf;
        PrivateKey sk = null;
        try {
            kf = KeyFactory.getInstance("DSA");
            sk = kf.generatePrivate(new PKCS8EncodedKeySpec(bytes));
        } catch (NoSuchAlgorithmException
                | InvalidKeySpecException e) {
            log.error(e.getMessage());
        }
        return sk;
    }

    @Override
    public PublicKey getPkByBytes(byte[] bytes) {
        KeyFactory kf;
        PublicKey pk = null;
        try {
            kf = KeyFactory.getInstance("DSA");
            pk = kf.generatePublic(new X509EncodedKeySpec(bytes));
        } catch (NoSuchAlgorithmException
                | InvalidKeySpecException e) {
            log.error(e.getMessage());
        }
        return pk;
    }
}

package site.grec.fabricclientsimulation.utils.sign;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

public interface SignatureFunction {
    KeyPair generateKeyPair();
    byte[] signature(PrivateKey sk, byte[] content);
    boolean verify(PublicKey pk, byte[] signedBytes, byte[] msgBytes);
    PrivateKey getSkByBytes(byte[] bytes);
    PublicKey getPkByBytes(byte[] bytes);

}
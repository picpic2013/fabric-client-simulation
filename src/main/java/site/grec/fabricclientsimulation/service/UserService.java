package site.grec.fabricclientsimulation.service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.grec.fabricclientsimulation.dao.user.BasicUser;
import site.grec.fabricclientsimulation.database.mongo.BasicUserRepository;
import site.grec.fabricclientsimulation.database.mongo.MultiUserRepository;
import site.grec.fabricclientsimulation.utils.sign.SignatureFunction;

import java.security.PublicKey;
import java.util.ArrayList;

@Service
@Slf4j
@Data
public class UserService {

    @Autowired
    BasicUserRepository basicUserRepository;
    @Autowired
    MultiUserRepository multiUserRepository;
    @Autowired
    SignatureFunction signatureFunction;

    public boolean registerBasicUser(PublicKey pk, byte[] sig) {
        if (signatureFunction.verify(pk, sig, pk.getEncoded())) {
            basicUserRepository.saveUser(
                    new BasicUser(
                            pk.getEncoded(),
                            0.0,
                            new ArrayList<>()
                    )
            );
            return true;
        }
        return false;
    }
}

package site.grec.fabricclientsimulation.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import site.grec.fabricclientsimulation.utils.hash.HashFunction;
import site.grec.fabricclientsimulation.utils.hash.InnerHashFunction;
import site.grec.fabricclientsimulation.utils.sign.DSASignature;
import site.grec.fabricclientsimulation.utils.sign.SignatureConfig;
import site.grec.fabricclientsimulation.utils.sign.SignatureFunction;

@Configuration
public class EncryptedFunctionsConfiguration {

    @Bean
    HashFunction hashFunction() {
        return new InnerHashFunction();
    }

    @Bean
    SignatureFunction signatureFunction() {
        return new DSASignature();
    }

    @Bean
    SignatureConfig signatureConfig() {
        return new SignatureConfig();
    }
}

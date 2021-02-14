package site.grec.fabricclientsimulation.utils.sign;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "encrypt.signature")
@Data
public class SignatureConfig {
    private Integer keySize;
}

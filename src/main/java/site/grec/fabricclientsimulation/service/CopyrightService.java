package site.grec.fabricclientsimulation.service;

import org.springframework.stereotype.Service;
import site.grec.fabricclientsimulation.dao.copyright.PiracyInfo;

@Service
public class CopyrightService {
    public PiracyInfo getDebugPiracyInfo(String url, String content) {
        return new PiracyInfo(url, content);
    }
}

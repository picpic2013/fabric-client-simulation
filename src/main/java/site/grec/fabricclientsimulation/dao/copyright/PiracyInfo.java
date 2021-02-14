package site.grec.fabricclientsimulation.dao.copyright;

import org.springframework.beans.factory.annotation.Autowired;
import site.grec.fabricclientsimulation.utils.hash.HashFunction;
import site.grec.fabricclientsimulation.utils.hash.InnerHashFunction;

import java.util.Arrays;

public class PiracyInfo {
    @Autowired
    private HashFunction hashFunction;

    private String url;
    private String content;

    public PiracyInfo(String url, String content) {
        this.url = url;
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int hashCode() {
        return hashFunction.getHash(getUrl(), getContent());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        PiracyInfo another = (PiracyInfo) obj;

        return Arrays.deepEquals(
                new String[]{getUrl(), getContent()},
                new String[]{another.getUrl(), another.getContent()}
        );
    }

    @Override
    public String toString() {
        return "PiracyInfo{" +
                "url='" + url + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}

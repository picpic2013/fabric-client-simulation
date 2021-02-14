package site.grec.fabricclientsimulation.dao.user;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import site.grec.fabricclientsimulation.utils.hash.HashFunction;
import site.grec.fabricclientsimulation.utils.hash.InnerHashFunction;

import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

@Data
public class UserProposal {
    @Autowired
    private HashFunction hashFunction;

    private String name;
    private HashMap<String, Date> status;

    public UserProposal(String name, HashMap<String, Date> status) {
        this.name = name;
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProposal that = (UserProposal) o;
        return Objects.equals(name, that.name)
                && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return hashFunction.getHash(name, status);
    }

    @Override
    public String toString() {
        return "UserProposal{" +
                "name='" + name + '\'' +
                ", status=" + status +
                '}';
    }
}

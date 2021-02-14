package site.grec.fabricclientsimulation.dao.user;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import site.grec.fabricclientsimulation.utils.hash.HashFunction;
import site.grec.fabricclientsimulation.utils.hash.InnerHashFunction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

@Data
public class BasicUser {
    @Autowired
    private HashFunction hashFunction;

    private byte[] userID;
    private Double balance;
    private ArrayList<String> devices;

    public BasicUser(byte[] userID, Double balance, ArrayList<String> devices) {
        this.userID = userID;
        this.balance = balance;
        this.devices = devices;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasicUser basicUser = (BasicUser) o;
        return Arrays.equals(userID, basicUser.userID)
                && Objects.equals(balance, basicUser.balance)
                && Objects.equals(devices, basicUser.devices);
    }

    @Override
    public int hashCode() {
        return hashFunction.getHash(userID, balance, devices);
    }

    @Override
    public String toString() {
        return "BasicUser{" +
                "userID='" + Arrays.toString(userID) + '\'' +
                ", balance=" + balance +
                ", devices=" + devices +
                '}';
    }
}

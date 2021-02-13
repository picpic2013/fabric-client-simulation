package site.grec.fabricclientsimulation.dao.user;

import lombok.Data;
import site.grec.fabricclientsimulation.utils.hash.HashFunction;

import java.util.ArrayList;
import java.util.Objects;

@Data
public class BasicUser {
    private String userID;
    private Double balance;
    private ArrayList<String> devices;

    public BasicUser(String userID, Double balance, ArrayList<String> devices) {
        this.userID = userID;
        this.balance = balance;
        this.devices = devices;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasicUser basicUser = (BasicUser) o;
        return Objects.equals(userID, basicUser.userID)
                && Objects.equals(balance, basicUser.balance)
                && Objects.equals(devices, basicUser.devices);
    }

    @Override
    public int hashCode() {
        return HashFunction.getHash(userID, balance, devices);
    }

    @Override
    public String toString() {
        return "BasicUser{" +
                "userID='" + userID + '\'' +
                ", balance=" + balance +
                ", devices=" + devices +
                '}';
    }
}

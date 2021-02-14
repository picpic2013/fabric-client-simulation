package site.grec.fabricclientsimulation.dao.user;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import site.grec.fabricclientsimulation.utils.hash.HashFunction;
import site.grec.fabricclientsimulation.utils.hash.InnerHashFunction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

@Data
public class MultiUser {
    @Autowired
    private HashFunction hashFunction;

    private byte[] userID;
    private String divisionDescription;
    private ArrayList<String> subAddresses;
    private ArrayList<Double> lockedIncome;
    private ArrayList<Double> dividends;
    private ArrayList<UserProposal> proposal;

    public MultiUser(byte[] userID,
                     String divisionDescription,
                     ArrayList<String> subAddresses,
                     ArrayList<Double> lockedIncome,
                     ArrayList<Double> dividends,
                     ArrayList<UserProposal> proposal) {
        this.userID = userID;
        this.divisionDescription = divisionDescription;
        this.subAddresses = subAddresses;
        this.lockedIncome = lockedIncome;
        this.dividends = dividends;
        this.proposal = proposal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MultiUser multiUser = (MultiUser) o;
        return Arrays.equals(userID, multiUser.userID)
                && Objects.equals(divisionDescription, multiUser.divisionDescription)
                && Objects.equals(subAddresses, multiUser.subAddresses)
                && Objects.equals(lockedIncome, multiUser.lockedIncome)
                && Objects.equals(dividends, multiUser.dividends)
                && Objects.equals(proposal, multiUser.proposal);
    }

    @Override
    public int hashCode() {
        return hashFunction.getHash(userID, divisionDescription,
                subAddresses, lockedIncome, dividends, proposal);
    }

    @Override
    public String toString() {
        return "MultiUser{" +
                "userID='" + Arrays.toString(userID) + '\'' +
                ", divisionDescription='" + divisionDescription + '\'' +
                ", subAddresses=" + subAddresses +
                ", lockedIncome=" + lockedIncome +
                ", dividends=" + dividends +
                ", proposal=" + proposal +
                '}';
    }
}

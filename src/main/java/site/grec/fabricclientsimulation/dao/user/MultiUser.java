package site.grec.fabricclientsimulation.dao.user;

import lombok.Data;
import site.grec.fabricclientsimulation.utils.hash.HashFunction;

import java.util.ArrayList;
import java.util.Objects;

@Data
public class MultiUser {
    private String userID;
    private String divisionDescription;
    private ArrayList<String> subAddresses;
    private ArrayList<Double> lockedIncome;
    private ArrayList<Double> dividends;
    private ArrayList<UserProposal> proposal;

    public MultiUser(String userID,
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
        return Objects.equals(userID, multiUser.userID)
                && Objects.equals(divisionDescription, multiUser.divisionDescription)
                && Objects.equals(subAddresses, multiUser.subAddresses)
                && Objects.equals(lockedIncome, multiUser.lockedIncome)
                && Objects.equals(dividends, multiUser.dividends)
                && Objects.equals(proposal, multiUser.proposal);
    }

    @Override
    public int hashCode() {
        return HashFunction.getHash(userID, divisionDescription,
                subAddresses, lockedIncome, dividends, proposal);
    }

    @Override
    public String toString() {
        return "MultiUser{" +
                "userID='" + userID + '\'' +
                ", divisionDescription='" + divisionDescription + '\'' +
                ", subAddresses=" + subAddresses +
                ", lockedIncome=" + lockedIncome +
                ", dividends=" + dividends +
                ", proposal=" + proposal +
                '}';
    }
}

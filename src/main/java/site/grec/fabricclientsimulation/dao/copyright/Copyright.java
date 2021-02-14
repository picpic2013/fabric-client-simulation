package site.grec.fabricclientsimulation.dao.copyright;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import site.grec.fabricclientsimulation.utils.hash.HashFunction;
import site.grec.fabricclientsimulation.utils.hash.InnerHashFunction;

import java.util.ArrayList;
import java.util.Arrays;

@Data
public class Copyright {
    @Autowired
    private HashFunction hashFunction;

    private byte[] assetID;
    private String authorAddress;
    private Double lockedIncome;
    private CopyrightInfo copyrightInfo;
    private boolean politicalBool;
    private boolean workLockBool;
    private ArrayList<PiracyInfo> piracyInfo;
    private ArrayList<String> quotedMaterials;
    private ArrayList<String> CEK;

    public Copyright(byte[] assetID,
                     String authorAddress,
                     Double lockedIncome,
                     CopyrightInfo copyrightInfo,
                     ArrayList<PiracyInfo> piracyInfo,
                     boolean politicalBool,
                     boolean workLockBool,
                     ArrayList<String> quotedMaterials,
                     ArrayList<String> CEK) {
        this.assetID = assetID;
        this.authorAddress = authorAddress;
        this.lockedIncome = lockedIncome;
        this.copyrightInfo = copyrightInfo;
        this.piracyInfo = piracyInfo;
        this.politicalBool = politicalBool;
        this.workLockBool = workLockBool;
        this.quotedMaterials = quotedMaterials;
        this.CEK = CEK;
    }

    @Override
    public int hashCode() {
        return hashFunction.getHash(
                getAssetID(),
                getAuthorAddress(),
                getLockedIncome(),
                getCopyrightInfo(),
                getPiracyInfo(),
                isPoliticalBool(),
                isWorkLockBool(),
                getQuotedMaterials(),
                getCEK()
        );
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Copyright another = (Copyright) obj;

        return Arrays.deepEquals(
                new Object[]{
                        getAssetID(),
                        getAuthorAddress(),
                        getLockedIncome(),
                        getCopyrightInfo(),
                        getPiracyInfo(),
                        isPoliticalBool(),
                        isWorkLockBool(),
                        getQuotedMaterials(),
                        getCEK()
                },
                new Object[]{
                        another.getAssetID(),
                        another.getAuthorAddress(),
                        another.getLockedIncome(),
                        another.getCopyrightInfo(),
                        another.getPiracyInfo(),
                        another.isPoliticalBool(),
                        another.isWorkLockBool(),
                        another.getQuotedMaterials(),
                        another.getCEK()
                }
        );
    }

    @Override
    public String toString() {
        return "Copyright{" +
                "assetID='" + Arrays.toString(assetID) + '\'' +
                ", authorAddress='" + authorAddress + '\'' +
                ", lockedIncome=" + lockedIncome +
                ", copyrightInfo=" + copyrightInfo +
                ", paricyInfo=" + piracyInfo +
                ", politicalBool=" + politicalBool +
                ", workLockBool=" + workLockBool +
                ", quotedMaterials=" + quotedMaterials +
                ", CEK=" + CEK +
                '}';
    }
}

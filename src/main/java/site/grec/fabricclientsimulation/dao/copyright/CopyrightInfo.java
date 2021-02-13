package site.grec.fabricclientsimulation.dao.copyright;

import site.grec.fabricclientsimulation.utils.hash.HashFunction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class CopyrightInfo {
    private String description;
    private ArrayList<String> feature;
    private Double price;


    public CopyrightInfo(String description,
                         ArrayList<String> feature,
                         Double price) {
        this.description = description;
        this.feature = feature;
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getFeature() {
        return feature;
    }

    public void setFeature(ArrayList<String> feature) {
        this.feature = feature;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        return HashFunction.getHash(
                getDescription(),
                getFeature(),
                getPrice()
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

        CopyrightInfo another = (CopyrightInfo) obj;

        return Arrays.deepEquals(
                new Object[]{
                        getDescription(),
                        getFeature(),
                        getPrice()
                },
                new Object[]{
                        another.getDescription(),
                        another.getFeature(),
                        another.getPrice()
                }
        );
    }

    @Override
    public String toString() {
        return "CopyrightInfo{" +
                "description='" + description + '\'' +
                ", feature=" + feature +
                ", price=" + price +
                '}';
    }
}

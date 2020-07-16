package com.mint.task1.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BinlistResponse {

    private NumberDetails number;
    private String scheme;
    private String type;
    private String brand;
    private boolean prepaid;
    private CountryDetails country;
    private BankDetails bank;

    public NumberDetails getNumber() {
        return number;
    }

    public void setNumber(NumberDetails number) {
        this.number = number;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public boolean isPrepaid() {
        return prepaid;
    }

    public void setPrepaid(boolean prepaid) {
        this.prepaid = prepaid;
    }

    public CountryDetails getCountry() {
        return country;
    }

    public void setCountry(CountryDetails country) {
        this.country = country;
    }

    public BankDetails getBank() {
        return bank;
    }

    public void setBank(BankDetails bank) {
        this.bank = bank;
    }
}

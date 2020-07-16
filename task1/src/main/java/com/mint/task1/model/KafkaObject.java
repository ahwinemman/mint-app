package com.mint.task1.model;

import org.json.JSONObject;

public class KafkaObject {

    private String scheme;
    private String type;
    private String bank;

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

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    @Override
    public String toString() {
        return new JSONObject(this).toString();
    }

}

package com.partycalc.database;

public class Contribution {

    String contrib_comment;
    Float contrib_amount;

    public Contribution(String contrib_comment, Float contrib_amount) {
        this.contrib_comment = contrib_comment;
        this.contrib_amount = contrib_amount;
    }

    public String getContrib_comment() {
        return contrib_comment;
    }

    public void setContrib_comment(String contrib_comment) {
        this.contrib_comment = contrib_comment;
    }

    public Float getContrib_amount() {
        return contrib_amount;
    }

    public void setContrib_amount(Float contrib_amount) {
        this.contrib_amount = contrib_amount;
    }
}

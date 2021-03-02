package com.mohsin.pharmapedia.Models.ApiCall;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class apiCallDrugsResponseDetail {
    @SerializedName("NAME")
    @Expose
    private String nAME;
    @SerializedName("BID")
    @Expose
    private Integer bID;


    private String type = "Data";

    public apiCallDrugsResponseDetail(String type) {
        this.type = type;
    }


    public String getNAME() {
        return nAME;
    }

    public void setNAME(String nAME) {
        this.nAME = nAME;
    }

    public Integer getBID() {
        return bID;
    }

    public void setBID(Integer bID) {
        this.bID = bID;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

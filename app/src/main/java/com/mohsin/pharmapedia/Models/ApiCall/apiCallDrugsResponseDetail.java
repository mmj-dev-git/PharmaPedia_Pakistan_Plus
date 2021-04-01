package com.mohsin.pharmapedia.Models.ApiCall;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class apiCallDrugsResponseDetail {
    @SerializedName("BID")
    @Expose
    private Integer bid;
    @SerializedName("BNAME")
    @Expose
    private String bname;
    @SerializedName("CID")
    @Expose
    private Integer cid;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String type = "Data";

    public apiCallDrugsResponseDetail(String type) {
        this.type = type;
    }


    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

}

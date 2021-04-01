package com.mohsin.pharmapedia.Models.ApiCall;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class apiCallDrugsResponse {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("error")
    @Expose
    private Object error;
    @SerializedName("response")
    @Expose
    private List<apiCallDrugsResponseDetail> response = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }

    public List<apiCallDrugsResponseDetail> getResponse() {
        return response;
    }

    public void setResponse(List<apiCallDrugsResponseDetail> response) {
        this.response = response;
    }
}

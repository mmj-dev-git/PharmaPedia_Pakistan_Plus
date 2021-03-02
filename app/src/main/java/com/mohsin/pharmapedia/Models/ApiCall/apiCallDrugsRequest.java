package com.mohsin.pharmapedia.Models.ApiCall;

public class apiCallDrugsRequest {
    String searchText;
    String pageIndex;
    String pageRecord;
    String type;

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public String getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(String pageIndex) {
        this.pageIndex = pageIndex;
    }

    public String getPageRecord() {
        return pageRecord;
    }

    public void setPageRecord(String pageRecord) {
        this.pageRecord = pageRecord;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }




    public apiCallDrugsRequest(String searchText, String pageIndex, String pageRecord, String type) {
        this.searchText = searchText;
        this.pageIndex = pageIndex;
        this.pageRecord = pageRecord;
        this.type = type;
    }


}

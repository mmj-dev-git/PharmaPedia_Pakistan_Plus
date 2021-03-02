package com.mohsin.pharmapedia.Models;

public class MainDashboardItemModel {

    int image;
    String menuName;


    public MainDashboardItemModel(int image, String menuName) {
        this.image = image;
        this.menuName = menuName;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
}

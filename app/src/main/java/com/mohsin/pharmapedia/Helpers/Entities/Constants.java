package com.mohsin.pharmapedia.Helpers.Entities;

public class Constants {

    public static String conditionAutoFillUrl = "https://clinicaltables.nlm.nih.gov/";

    enum Environment {
        Testing,
        Dev,
        SIT,
        Staging,
        Prod,
        DevPublic,
        UAT
    }


    // Testing for local QA
    // Dev for local Development
    // DevPublic for Public Development
    // Staging for Live Environment


    static Environment env = Environment.Dev;

    public static String userCustomerId ="userCustomerId";


    public static String apiError = "apiError";
    public static String apiCallDrugs = "apiCallDrugs";

    public static String Brand = "Brand";
    public static String Generic = "Generic";



    /////////////////////////////////


    public static String getMainUrl() {
        if (env == Environment.Dev) {
            return "http://10.0.2.2:8000/";   // IPATH
          //  return  "http://172.30.109.33:8080/MBLMiddleware-0.1-SNAPSHOT/api/";   // Meezan SIT
          //  return "http://192.168.111.101:8080/api/";

        }
        else if (env == Environment.SIT) {

              return  "http://172.30.109.33:8080/MBLMiddleware-0.1-SNAPSHOT/api/";   // Meezan SIT


        }

        return "";
    }


}

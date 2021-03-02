package com.mohsin.pharmapedia.Interfaces;


import com.mohsin.pharmapedia.Models.ApiCall.apiCallDrugsRequest;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface apiDefinition {



//    @Headers({
//            "Content-Type: text/xml",
//            "Accept-Charset: utf-8"
//    })
//    @POST("/mockNumberConversionSoapBinding")
//    Call<ResponseBody> requestStateInfo(@Body reqMain body);
//



//    @POST("v1/ResetPassword")
//    Call<ResponseBody> resetPassword
//            (@Body apiCallResetPasswordRequest _req);


    @POST("api/BrandDrug")
    Call<ResponseBody> drug
            (@Body apiCallDrugsRequest _req);



}

package com.mohsin.pharmapedia.Helpers.httpHelper;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mohsin.pharmapedia.Helpers.Entities.App;
import com.mohsin.pharmapedia.Helpers.Entities.Constants;
import com.mohsin.pharmapedia.Helpers.Entities.Utils;
import com.mohsin.pharmapedia.Interfaces.IBase;
import com.mohsin.pharmapedia.Interfaces.apiDefinition;

import com.mohsin.pharmapedia.Models.ApiCall.apiCallDrugsRequest;
import com.mohsin.pharmapedia.UI.Dialogs.DefaultDialog;
import com.mohsin.pharmapedia.UI.Dialogs.Loading_Dialog;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class callApi {
    public IBase ibase;
    ProgressDialog loadingDialog;
    Context context;

    public callApi(IBase _ibase, Context _context) {
        ibase = _ibase;
        context = _context;


    }

    protected void showLoadingDialog(String message) {
        loadingDialog = Loading_Dialog.ctor(context);
        loadingDialog.setMessage("");
        loadingDialog.show();
    }


    protected void hideLoadingDialog() {

        if (loadingDialog != null)
            loadingDialog.dismiss();

    }

    public void apiCal(final String type, Object cv, final boolean loader) {

        String FileName = App.get().FileName;
        if (loader) {
            showLoadingDialog("");
        }
        apiDefinition apiService;
        RequestBody body = null;
//        if (cv != null) {
//
//            App.get().storage.appendFile("Meezan", FileName+".txt", "\nonApiRequest ***************************************** Time: " + Utils.getCurrentDateTime(1));
//            App.get().storage.appendFile("Meezan", FileName+".txt", "onApiResponseType: " + type);
//
//            Log.i("onApiRequest", "***************************************** Time" + Utils.getCurrentDateTime(1));
//            Log.i("onApiResponseType", type + " ");
//            try {
//                App.get().storage.appendFile("Meezan", FileName+".txt", "onApiRequestToken: " + sharedPrefs.getString(Constants.userAccessToken) + " ");
//
//                Log.i("onApiRequestToken", sharedPrefs.getString(Constants.userAccessToken) + " ");
//            } catch (Exception e) {
//            }
//            try {
//
//                App.get().storage.appendFile("Meezan", FileName+".txt", "onApiRequestSessionId: " + sharedPrefs.getString(Constants.sessionsId) + " ");
//
//                Log.i("onApiRequestSessionId", sharedPrefs.getString(Constants.sessionsId) + " ");
//            } catch (Exception e) {
//            }
//            try {
//                App.get().storage.appendFile("Meezan", FileName+".txt", "onApiRequestBody: " + new Gson().toJson(cv) + " ");
//
//                Log.i("onApiRequestBody", new Gson().toJson(cv) + " ");
//            } catch (Exception e) {
//            }
//            App.get().storage.appendFile("Meezan", FileName+".txt", "onApiRequest: *****************************************");
//
//            Log.i("onApiRequest", "*****************************************");
//
//        }

        apiService = RestCall.getMainClient().create(apiDefinition.class);

        Call<ResponseBody> call = null;

        if (Constants.apiCallDrugs.equals(type)) {
            apiCallDrugsRequest cls = (apiCallDrugsRequest) cv;
            call = apiService.drug(cls);
        }
//        ************************ more apis in else*********************************
//




        call.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (loader) {
                    hideLoadingDialog();
                }

                try {
                    int responceCode = response.code();
                    String responceMessagae = response.message();
                    String responceBody = "";

                    if (response.isSuccessful()) {
                        responceBody = response.body().string();
                    } else {
                        responceBody = response.errorBody().string();
                    }
                    Log.i("onApiResponse", "***************************************** Time " + Utils.getCurrentDateTime(1));
                    Log.i("onApiResponseType", type + " ");
                    Log.i("onApiResponseBody", responceBody + " ");
                    Log.i("onApiResponseCode", responceCode + " ");
                    Log.i("onApiResponse", "*****************************************");


//                    App.get().storage.appendFile("Meezan", FileName+".txt", "\nonApiResponse ***************************************** Time: " + Utils.getCurrentDateTime(1));
//                    App.get().storage.appendFile("Meezan", FileName+".txt", "onApiResponseType " + type);
//                    App.get().storage.appendFile("Meezan", FileName+".txt", "onApiResponseBody " + responceBody);
//                    App.get().storage.appendFile("Meezan", FileName+".txt", "onApiResponseCode " + responceCode);
//                    App.get().storage.appendFile("Meezan", FileName+".txt", "onApiResponse ***************************************** End ");
//

                    if (responceCode == 200) {
                        Object obj = new Gson().fromJson(responceBody, Utils.getResponseClass(type));

                        if (ibase != null) {
                            ibase.apiCallBack(obj, type);
                        }
                    } else {

                        if (Utils.isHTML(responceBody)) {
                            new DefaultDialog(context, "Server is not reachable", false);
                            //  showAlertDialog("Server is not reachable");
                        } else {
                            Object obj = new Gson().fromJson(responceBody, Utils.getResponseClass(Constants.apiError));
                            if (ibase != null) {
                                ibase.apiCallBackFailed(obj, type);
                            }

//                            if (!type.equals(Constants.apiCallFetchTitleWallet)) {
//                                new DefaultDialog(context, ((apiErrorResponse) obj).getMessage(), false);
//
////                                showToast();
//                            }
//
//                            if (((apiErrorResponse) obj).getMessage().equalsIgnoreCase("Unauthorized")) {
//                                Intent goActivity = new Intent(context, JoinNConnect.class);
//                                goActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                                context.startActivity(goActivity);
//                            }

//                        if (type.equals(Constants.apiCallLogin)) {
//                          //  showToast(((apiErrorResponse) obj).getErrorDescription());
//                        } else {
//                            //  showToast(((apiErrorResponse) obj).getResponseMsg());
//                        }
                        }
                    }


                } catch (Exception e) {
                    showToast(" " + e.getMessage());
                    //  Log.i("CallApiException", "Type : " + type + " " + e.getMessage());


                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (loader) {
                    hideLoadingDialog();
                }

                Log.i("onApiFailure", "***************************************** Time " + Utils.getCurrentDateTime(1));
                try {
                    Log.i("onApiFailureMsg", t.getCause().getMessage() + " ");
                } catch (Exception e) {
                    try {
                        Log.i("onApiFailureMsgExcep", t.getMessage() + " ");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                Log.i("onApiResponse", "*****************************************");


//                App.get().storage.appendFile("Meezan", FileName+".txt", "\nonApiResponse ***************************************** Time: " + Utils.getCurrentDateTime(1));
//                try {
//
//                    App.get().storage.appendFile("Meezan", FileName+".txt", "onApiFailureMsg " + t.getCause().getMessage());
//                } catch (Exception e) {
//                    try {
//                        App.get().storage.appendFile("Meezan", FileName+".txt", "onApiFailureMsgExcep " + t.getMessage());
//                    } catch (Exception ex) {
//                        ex.printStackTrace();
//                    }
//                }
//                App.get().storage.appendFile("Meezan", FileName+".txt", "onApiResponse ***************************************** End ");


                try {
                    String msg = t.getCause().getMessage();
                    if (msg.toLowerCase().contains("socket close")) {
                        showToast("Network Error : Request Timeout");
                    } else {
                        showToast("Network Error : " + msg);
                    }

                } catch (Exception e) {

                    try {
                        String msg = t.getMessage();
                        if (msg.toLowerCase().contains("socket close")) {
                            showToast("Network Error : Request Timeout");
                        } else {
                            showToast("Network Error : " + msg);
                        }

                    } catch (Exception ex) {
                        //  ex.printStackTrace();
                    }
                    //  e.printStackTrace();
                }


            }
        });

    }

    protected void showToast(String text) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }


    protected void showAlertDialog(String message_) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(context);
        }
        builder.setMessage(message_);

        builder.setCancelable(true);

        builder.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

/*        builder.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
*/

        AlertDialog alert11 = builder.create();
        alert11.show();

    }
}

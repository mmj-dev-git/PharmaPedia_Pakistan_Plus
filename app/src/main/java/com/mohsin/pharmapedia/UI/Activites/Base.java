package com.mohsin.pharmapedia.UI.Activites;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mohsin.pharmapedia.Helpers.httpHelper.callApi;
import com.mohsin.pharmapedia.Interfaces.IBase;
import com.mohsin.pharmapedia.R;
import com.mohsin.pharmapedia.UI.Dialogs.Loading_Dialog;

/**
 * Created by mac on 25/05/2018.
 */

public class Base extends AppCompatActivity implements View.OnClickListener {

    IBase ibase;
    ProgressDialog loadingDialog;
    ImageView btnBack ;

    protected void callApi() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        btnBack = (ImageView) findViewById(R.id.btnBack);

    }


    protected void showLoadingDialog(String message) {
        loadingDialog = Loading_Dialog.ctor(this);
        loadingDialog.setMessage("");
        loadingDialog.show();
    }

    protected void hideLoadingDialog() {

        if ((loadingDialog != null) && loadingDialog.isShowing() && !this.isFinishing()) {
            loadingDialog.dismiss();
        }

    }

    protected void openActivity(Class act) {
        Intent i = new Intent(getApplicationContext(), act.getClass());
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplicationContext().startActivity(i);
    }


    public void closeKeyBoard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    protected void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    protected void showToastLong(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }


    @Override
    public void onClick(View view) {



    }

    //    http://sms.falcon-softwares.com/web_distributor/api/balance_check.php?username=923008242322&password=***

    protected void callInitializer(IBase ibase_) {
        ibase = ibase_;
        ibase.initializeControls();
    }

    protected void showAlertDialog(String message_) {

        try {
            AlertDialog.Builder builder;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
            } else {
                builder = new AlertDialog.Builder(this);
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


            AlertDialog alert11 = builder.create();
            alert11.show();
        } catch (Exception e) {
            Log.i("Exception", "showAlertDialog: ");
        }

    }


    protected void showAppUpdateDialog() {

        try {
            AlertDialog.Builder builder;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
            } else {
                builder = new AlertDialog.Builder(this);
            }
            builder.setMessage("Please Update Your App..");

            builder.setCancelable(true);

            builder.setPositiveButton(
                    "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                            try {
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                            } catch (android.content.ActivityNotFoundException anfe) {
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                            }

                            dialog.cancel();


                        }
                    });


            AlertDialog alert11 = builder.create();
            alert11.show();
        } catch (Exception e) {
            Log.i("Exception", "showAlertDialog: ");
        }

    }

    public void restCall(final String type,  Object cv, boolean loader)
    {
        closeKeyBoard();
        callApi callObj = new callApi(ibase,getApplicationContext());
        callObj.apiCal(type,cv,loader);
    }



}

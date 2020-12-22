package com.mohsin.baseapp.UI.Activites;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.mohsin.baseapp.Helpers.FragmentHandler;
import com.mohsin.baseapp.Interfaces.IBase;
import com.mohsin.baseapp.Interfaces.OnFragmentInteractionListener;
import com.mohsin.baseapp.R;
import com.mohsin.baseapp.UI.Fragments.MainDashboard;

public class Main extends Base implements IBase, OnFragmentInteractionListener, View.OnClickListener {


    public FragmentHandler fragmentHandler;
    ImageView btnBack;
  public   ImageView btnLogOut;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout content = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_main, content);
        callInitializer(this);
    }

    public void setBackEnabled() {
        btnBack.setVisibility(View.VISIBLE);
        btnLogOut.setVisibility(View.GONE);
    }


    public void setLogOutDisable()
    {
        btnLogOut.setVisibility(View.GONE);
    }



    public void setBackDisable() {
        btnBack.setVisibility(View.GONE);
        btnLogOut.setVisibility(View.VISIBLE);


    }


    @Override
    public void initializeControls() {
        fragmentHandler = new FragmentHandler(R.id.rlFragmentHandler, this);
        fragmentHandler.replaceFragment(new MainDashboard(fragmentHandler), false);

        btnBack = findViewById(R.id.btnBack);
//        btnLogOut = findViewById(R.id.btnLogOut);
        btnBack.setOnClickListener(this);


    }

    @Override
    public void apiCallBack(Object obj, String type) {

    }

    @Override
    public void initializeControls(View view) {


    }

    @Override
    public void apiCallBackFailed(Object obj, String type) {

    }

    @Override
    public void onFragmentInteraction(String from, String action) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        fragmentHandler.getTopFragment().onActivityResult(requestCode,resultCode,data);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);

        if(view == btnBack)
        {
            fragmentHandler.popStack();
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
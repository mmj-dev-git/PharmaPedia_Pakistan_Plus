package com.mohsin.pharmapedia.UI.Activites;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.mohsin.pharmapedia.Helpers.FragmentHandler;
import com.mohsin.pharmapedia.Interfaces.IBase;
import com.mohsin.pharmapedia.Interfaces.OnFragmentInteractionListener;
import com.mohsin.pharmapedia.R;


public class JoinNConnect  extends Base implements IBase, OnFragmentInteractionListener, View.OnClickListener
{

    ImageView btnBack;

    public FragmentHandler fragmentHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout content = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_join_n_connect, content);
        callInitializer(this);
    }


    @Override
    public void initializeControls() {
//        fragmentHandler = new FragmentHandler(R.id.rlFragmentHandler, this);
//        fragmentHandler.replaceFragment(new Login(fragmentHandler), false);


        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

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



    public void setBackEnabled() {
        btnBack.setVisibility(View.VISIBLE);

    }

    public void setBackDisable() {
        btnBack.setVisibility(View.GONE);

    }


    @Override
    public void onClick(View view) {
        super.onClick(view);

        if(view == btnBack)
        {
            fragmentHandler.popStack();
        }
    }

}
package com.mohsin.pharmapedia.UI.Activites;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.gauravk.bubblenavigation.BubbleNavigationLinearView;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;
import com.mohsin.pharmapedia.Helpers.Entities.Constants;
import com.mohsin.pharmapedia.Helpers.FragmentHandler;
import com.mohsin.pharmapedia.Interfaces.IBase;
import com.mohsin.pharmapedia.Interfaces.OnFragmentInteractionListener;
import com.mohsin.pharmapedia.Models.ApiCall.apiCallDrugsRequest;
import com.mohsin.pharmapedia.R;
import com.mohsin.pharmapedia.UI.Fragments.Drugs;
import com.mohsin.pharmapedia.UI.Fragments.Home;

public class Main extends Base implements IBase, OnFragmentInteractionListener, View.OnClickListener {


    public FragmentHandler fragmentHandler;
    ImageView btnBack;
  public   ImageView btnLogOut;
    BubbleNavigationLinearView bottom_navigation_view_linear;

    Object _apiCallDrugsResponseObject = null;



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
        fragmentHandler.replaceFragment(new Home(fragmentHandler), false);

        bottom_navigation_view_linear = (BubbleNavigationLinearView) findViewById(R.id.bottom_navigation_view_linear);

        btnBack = findViewById(R.id.btnBack);
//        btnLogOut = findViewById(R.id.btnLogOut);
        btnBack.setOnClickListener(this);

        bottom_navigation_view_linear.setNavigationChangeListener(new BubbleNavigationChangeListener() {
            @Override
            public void onNavigationChanged(View view, int position) {
                if(position==0)
                {
                    if(_apiCallDrugsResponseObject==null) {
                        apiCallDrugsRequest _apiCallDrugsRequest = new apiCallDrugsRequest("", "1", "20",Constants.Brand);
                        restCall(Constants.apiCallDrugs, _apiCallDrugsRequest, true);
                    }
                    else
                    {
                        fragmentHandler.replaceFragment(new Drugs(fragmentHandler,_apiCallDrugsResponseObject), false);
                    }

                }
                else  if(position==2)
                {
                    fragmentHandler.replaceFragment(new Home(fragmentHandler), false);
                }
            }
        });


    }

    @Override
    public void apiCallBack(Object obj, String type) {

        if(type.equalsIgnoreCase(Constants.apiCallDrugs))
        {
            _apiCallDrugsResponseObject = obj;
            fragmentHandler.replaceFragment(new Drugs(fragmentHandler,_apiCallDrugsResponseObject), false);
        }

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
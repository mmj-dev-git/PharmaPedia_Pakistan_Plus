package com.mohsin.pharmapedia.UI.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mohsin.pharmapedia.Helpers.FragmentHandler;
import com.mohsin.pharmapedia.Interfaces.IBase;
import com.mohsin.pharmapedia.Interfaces.IMainDashboard;
import com.mohsin.pharmapedia.Interfaces.OnFragmentInteractionListener;
import com.mohsin.pharmapedia.R;
import com.mohsin.pharmapedia.UI.Activites.Main;
import com.mohsin.pharmapedia.UI.CustomComponents.TextView_N;

public class Home extends Base implements IBase , IMainDashboard {

    Context mContext;

    private OnFragmentInteractionListener mListener;
    FragmentHandler fragmentHandler;
    TextView_N tvName;

    public Home(FragmentHandler _fragmentHandler) {
        fragmentHandler = _fragmentHandler;
    }

    public Home() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View _view = inflater.inflate(R.layout.fragment_main_dashboard, container, false);
        callInitializer(this,_view);


        return _view;

    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            ((Main)getActivity()).setBackDisable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        mContext = context;
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;

    }

    @Override
    public void initializeControls() {

    }


    @Override
    public void initializeControls(View view) {


//        try {
//            ((Main)getActivity()).btnLogOut.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                    try{
//                        AlertDialog.Builder builder = new AlertDialog.Builder(_context);
//                        builder.setMessage("Are you sure? you want to logout").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                            }
//                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//
//
//                            }
//                        }).show();
//
//                    }
//                    catch (Exception e)
//                    {
//
//                    }
//                }
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//        }



    }


    @Override
    public void apiCallBack(Object obj, String type) {

    }

    @Override
    public void apiCallBackFailed(Object obj, String type) {

    }



    @Override
    public void onClick(View view) {
        super.onClick(view);

    }

    @Override
    public void onClickItem(int index) {

//
//        if(index == 0)
//        {
//            if(App.get().privileges.contains(Constants.privilegeNewWallet) ||  App.get().privileges.contains(Constants.privilegeWalletUpgrade)) {
//                fragmentHandler.addReplaceFragmentWithAnimation(new SmartWallet(fragmentHandler), true, true, R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out);
//            }
//            else {
//                showToast("You are not allowed for this menu");
//            }
//        }
//        else if(index == 1)
//        {
////            showToast("Coming Soon");
//            fragmentHandler.addReplaceFragmentWithAnimation(new CustomerTransactions(fragmentHandler), true, true, R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out);
//        }
//        else if(index == 2)
//        {
//            showToast("Coming Soon");
//          //  fragmentHandler.addReplaceFragmentWithAnimation(new CashOutMain(fragmentHandler), true, true, R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out);
//        }
//        else if(index == 3)
//        {
//            showToast("Coming Soon");
//        }
//
//        if(index==0)
//        {
//            try {
//
//                ((Main)getActivity()).setBackEnabled();
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }

    }
}

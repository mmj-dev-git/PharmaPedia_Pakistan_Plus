package com.mohsin.pharmapedia.UI.Fragments;

import android.content.Context;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.mohsin.pharmapedia.Adapters.drugActivityAdapter;
import com.mohsin.pharmapedia.Helpers.Entities.Constants;
import com.mohsin.pharmapedia.Helpers.Entities.PaginationScrollListener;
import com.mohsin.pharmapedia.Helpers.FragmentHandler;
import com.mohsin.pharmapedia.Interfaces.IBase;
import com.mohsin.pharmapedia.Interfaces.OnFragmentInteractionListener;
import com.mohsin.pharmapedia.Models.ApiCall.apiCallDrugsRequest;
import com.mohsin.pharmapedia.Models.ApiCall.apiCallDrugsResponse;
import com.mohsin.pharmapedia.Models.ApiCall.apiCallDrugsResponseDetail;
import com.mohsin.pharmapedia.R;
import com.mohsin.pharmapedia.UI.Activites.Main;
import com.mohsin.pharmapedia.UI.CustomComponents.Button_N;
import com.mohsin.pharmapedia.UI.CustomComponents.EditText_N;
import com.mohsin.pharmapedia.UI.CustomComponents.RadioButton_N;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import info.hoang8f.android.segmented.SegmentedGroup;


public class Drugs extends Base implements IBase , View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    Timer timer;

    String type;

    Context mContext;
    FragmentHandler fragmentHandler;
    private RecyclerView rvAvailableSources;

    SwipeRefreshLayout swipeRefreshLayout;

    private EditText_N etSearch;
    private Button_N btnEraseText;

    private SegmentedGroup sgFilter;
    private RadioButton_N rbBrand;
    private RadioButton_N rbGeneric;

    private OnFragmentInteractionListener mListener;
    Object obj;
    apiCallDrugsResponse _apiCallDrugsResponse;
    ArrayList<apiCallDrugsResponseDetail> drugList;
    drugActivityAdapter _drugActivityAdapter;

    LinearLayoutManager linearLayoutManager;

    private static final int PAGE_START = 2;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private static int TOTAL_PAGES = 1;
    private int currentPage = PAGE_START;

    boolean isTotalPagesCount = false;

    String drugType = Constants.Brand;
    public Drugs(FragmentHandler _fragmentHandler,Object obj) {
        fragmentHandler = _fragmentHandler;
        this.obj=obj;
    }


    public Drugs() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View _view = inflater.inflate(R.layout.fragment_drugs, container, false);
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
    public void apiCallBack(Object obj, String type) {
        if (type.equals(Constants.apiCallDrugs)) {
            _apiCallDrugsResponse = (apiCallDrugsResponse) obj;

                getTotalNumberOfPages(2323232);
                swipeRefreshLayout.setRefreshing(false);

                try {
                    _drugActivityAdapter.removeLoadingFooter();
                    isLoading = false;
                }
                catch (Exception e)
                {

                }



            drugList.addAll(_apiCallDrugsResponse.getResponse());
         }

        addFooter();
        _drugActivityAdapter.notifyDataSetChanged();


    }

    private void addFooter() {
        if (currentPage <= TOTAL_PAGES) {
            _drugActivityAdapter.addLoadingFooter();
        } else {
            isLastPage = true;
        }
    }

    @Override
    public void initializeControls(View view) {
        timer = new Timer();

        rvAvailableSources = (RecyclerView) view.findViewById(R.id.rvAvailableSources);
        etSearch = (EditText_N) view.findViewById(R.id.etSearch);
        btnEraseText = (Button_N) view.findViewById(R.id.btnEraseText);

        swipeRefreshLayout = view.findViewById(R.id.main_swiperefresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorMagenta);

        sgFilter = (SegmentedGroup) view.findViewById(R.id.sgFilter);
        rbBrand = (RadioButton_N) view.findViewById(R.id.rbBrand);
        rbGeneric = (RadioButton_N) view.findViewById(R.id.rbGeneric);


        _apiCallDrugsResponse = (apiCallDrugsResponse) obj;

//        getTotalNumberOfPages(_apiCallDrugsResponse.getTotalRecords());
        getTotalNumberOfPages(26666);

        _apiCallDrugsResponse.getResponse();
        drugList = new ArrayList<>();
        drugList.addAll(_apiCallDrugsResponse.getResponse());


        _drugActivityAdapter = new drugActivityAdapter(drugList,mContext);
        //RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        //rvAvailableSources.setLayoutManager(mLayoutManager);


        linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        rvAvailableSources.setLayoutManager(linearLayoutManager);
        rvAvailableSources.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvAvailableSources.getContext(),
                ((LinearLayoutManager) linearLayoutManager).getOrientation());
        rvAvailableSources.addItemDecoration(dividerItemDecoration);
        rvAvailableSources.setAdapter(_drugActivityAdapter);

        btnEraseText.setOnClickListener(this);
        sgFilter.setOnCheckedChangeListener(this);

        addFooter();

        rvAvailableSources.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage += 1;

                Log.i("Timer", "loadMoreItems: ");

                loadNextPage();

                //  Toast.makeText(AvailableSources.this, Integer.toString(currentPage), Toast.LENGTH_SHORT).show();
            }

            @Override
            public int getTotalPageCount() {
                return TOTAL_PAGES;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });


        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                swipeRefreshLayout.setRefreshing(true);
                timer.cancel();
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                drugList.clear();
                                currentPage = 1;
                                isLastPage = false;
                                isLoading = false;
                                TOTAL_PAGES = 1;
                                isTotalPagesCount = false;

                                _drugActivityAdapter.notifyDataSetChanged();

                                if (!editable.toString().equals("")) {

                                    btnEraseText.setVisibility(View.VISIBLE);
                                } else {
                                    btnEraseText.setVisibility(View.GONE);
                                }



                                apiCallDrugsRequest _apiCallDrugsRequest = new apiCallDrugsRequest(editable.toString(), Integer.toString(currentPage), "20",drugType);
                                restCall(Constants.apiCallDrugs, _apiCallDrugsRequest, false);

                            }
                        });
                    }

                },1000
                        );

            }
        });

    }

    private void loadNextPage() {

//        if (currentPage <= TOTAL_PAGES) {
//            _drugActivityAdapter.addLoadingFooter();
//        } else {
//            isLastPage = true;
//        }

        apiCallDrugsRequest _apiCallDrugsRequest = new apiCallDrugsRequest(etSearch.getText().toString(), Integer.toString(currentPage), "20",drugType);
        restCall(Constants.apiCallDrugs, _apiCallDrugsRequest, false);
    }


    private void getTotalNumberOfPages(Integer noOfRecord) {
        float numberOfPages = noOfRecord / 20;
        TOTAL_PAGES = (int) Math.ceil(numberOfPages);
        TOTAL_PAGES = TOTAL_PAGES - 1;
    }

    @Override
    public void apiCallBackFailed(Object obj, String type) {

    }

    @Override
    public void onClick(View view) {
        super.onClick(view);

        if(btnEraseText ==view)
        {
            btnEraseText.setVisibility(View.GONE);
            etSearch.setText("");
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        apiCallDrugsRequest _apiCallDrugsRequest ;
        switch (i) {

            case R.id.rbGeneric:


                drugType = Constants.Generic;

                break;
            case R.id.rbBrand:
                drugType = Constants.Brand;

                break;
        }
        drugList.clear();
        currentPage = 1;
        isLastPage = false;
        isLoading = false;
        TOTAL_PAGES = 1;

        isTotalPagesCount = false;

        _drugActivityAdapter.notifyDataSetChanged();



        _apiCallDrugsRequest = new apiCallDrugsRequest(etSearch.getText().toString(), Integer.toString(currentPage), "20",drugType);

        restCall(Constants.apiCallDrugs, _apiCallDrugsRequest, false);}
}
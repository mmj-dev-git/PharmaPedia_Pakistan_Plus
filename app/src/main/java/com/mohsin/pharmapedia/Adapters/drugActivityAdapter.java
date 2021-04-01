package com.mohsin.pharmapedia.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mohsin.pharmapedia.Models.ApiCall.apiCallDrugsResponseDetail;
import com.mohsin.pharmapedia.R;
import com.mohsin.pharmapedia.UI.CustomComponents.TextView_N;

import java.util.ArrayList;

public class drugActivityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {

    private ArrayList<apiCallDrugsResponseDetail>  drugList;
    private ArrayList<apiCallDrugsResponseDetail>  drugItem;

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_LOADING = 1;

    Context con;
    int filterCount = 0;

    private boolean isLoadingAdded = false;
    private boolean retryPageLoad = false;

    private String errorMsg;

    String TAG = "AwesumWeb";


    String _text = "";


    public drugActivityAdapter(ArrayList<apiCallDrugsResponseDetail> drugList, Context con) {
        this.drugList = drugList;
        this.drugItem = drugList;
        this.con = con;
    }
    
    
    @Override
    public Filter getFilter() {
        return null;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        switch (viewType) {
            case TYPE_HEADER: {
                View view = LayoutInflater.from(con).inflate(R.layout.drug_row_header, parent, false);
                return new ViewHolderHeader(view);
            }
            case TYPE_LOADING: {
                View view = LayoutInflater.from(con).inflate(R.layout.item_progress, parent, false);
                return new LoadingVH(view);
            }
        }
        return null;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        apiCallDrugsResponseDetail _apiCallDrugsResponseDetail = drugItem.get(position);


        switch (holder.getItemViewType()) {
            case TYPE_HEADER:
                ViewHolderHeader _viewHolderHeader = (ViewHolderHeader) holder;

                _viewHolderHeader.tvDrugName.setText(_apiCallDrugsResponseDetail.getBname());
                break;

            case TYPE_LOADING:
                LoadingVH _LoadingVH = (LoadingVH) holder;
                _LoadingVH.mProgressBar.setVisibility(View.VISIBLE);
                break;
        }


        }

    @Override
    public int getItemViewType(int position) {


        if (drugList.get(position).getType()==null) {

            return TYPE_HEADER;
        } else if (drugList.get(position).getType().equals("loading")) {

            return TYPE_LOADING;
        }

        return -1;
    }


    public void addLoadingFooter() {
        isLoadingAdded = true;
        //sourcesItem.add(new ActivityAvailableSourcesModel("","loading"));
        drugList.add(new apiCallDrugsResponseDetail("loading"));

        //  add(new Result());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = drugList.size() - 1;

        drugList.remove(position);
            notifyItemRemoved(position);

    }

    @Override
    public int getItemCount() {
        return drugList.size();
    }

    class ViewHolderHeader extends RecyclerView.ViewHolder {

        public TextView_N tvDrugName;
        View view;

        public ViewHolderHeader(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            tvDrugName = (TextView_N) itemView.findViewById(R.id.tvDrugName);
        }
    }


    protected class LoadingVH extends RecyclerView.ViewHolder {
        private ProgressBar mProgressBar;
        private LinearLayout mErrorLayout;

        public LoadingVH(View itemView) {
            super(itemView);

            mProgressBar = itemView.findViewById(R.id.loadmore_progress);

//            mErrorLayout = itemView.findViewById(R.id.loadmore_errorlayout);

        }
    }
}

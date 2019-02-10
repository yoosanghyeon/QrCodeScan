package com.nvqrcode.yoosanghyeon.noadvertiasmentqrcode.scanlist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nvqrcode.yoosanghyeon.noadvertiasmentqrcode.R;
import com.nvqrcode.yoosanghyeon.noadvertiasmentqrcode.roomdata.Recode;
import com.nvqrcode.yoosanghyeon.noadvertiasmentqrcode.scanlist.scanlistinterface.ScanListClickListener;

import java.util.ArrayList;

public class ScanListAdapter extends RecyclerView.Adapter<ScanListViewHolder> {

    private ArrayList<Recode> recodes;
    private ScanListClickListener scanListClickListener;

    public ScanListAdapter(ScanListClickListener scanListClickListener) {
        recodes = new ArrayList<>();
        this.scanListClickListener = scanListClickListener;
    }

    public void onItemAdd(Recode recode){
        recodes.add(recode);
        notifyDataSetChanged();
    }

    public void onRecodesClear(){
        if(recodes != null){
            recodes.clear();
        }
    }

    @NonNull
    @Override
    public ScanListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.scan_list_viewholder, viewGroup, false);
        ScanListViewHolder scanListViewHolder = new ScanListViewHolder(view);
        return scanListViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ScanListViewHolder scanListViewHolder, int i) {
        scanListViewHolder.onBindViewholder(recodes.get(i) , scanListClickListener);
    }

    @Override
    public int getItemCount() {
        return recodes.size();
    }


}

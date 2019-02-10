package com.nvqrcode.yoosanghyeon.noadvertiasmentqrcode.scanlist;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nvqrcode.yoosanghyeon.noadvertiasmentqrcode.R;
import com.nvqrcode.yoosanghyeon.noadvertiasmentqrcode.dialog.ExcuteDialogFragment;
import com.nvqrcode.yoosanghyeon.noadvertiasmentqrcode.roomdata.Recode;
import com.nvqrcode.yoosanghyeon.noadvertiasmentqrcode.roomdata.RecodeDataBase;
import com.nvqrcode.yoosanghyeon.noadvertiasmentqrcode.scanlist.scanlistinterface.ScanListClickListener;

import java.util.ArrayList;
import java.util.List;


public class ScanListFragment extends Fragment implements ScanListClickListener {


    private RecyclerView recyclerView;
    private ArrayList<Recode> recodes;
    private ScanListAdapter scanListAdapter;



    public static ScanListFragment newInstance() {
        ScanListFragment fragment = new ScanListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public void setRecodes(ArrayList<Recode> recodes) {
        this.recodes = recodes;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_scan_list, container, false);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.scan_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity() , LinearLayoutManager.VERTICAL , false);
        recyclerView.setLayoutManager(linearLayoutManager );
        scanListAdapter = new ScanListAdapter(this);
        recyclerView.setAdapter(scanListAdapter);

        recodes = new ArrayList<>();
        List<Recode> recodes = RecodeDataBase.getInstance(getContext()).recodeDao().getAll();
        for (int i =0 ; i < recodes.size() ; i++){
            scanListAdapter.onItemAdd(recodes.get(i));
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void onUpdateQrText(){
        scanListAdapter.onRecodesClear();
        List<Recode> recodes = RecodeDataBase.getInstance(getContext()).recodeDao().getAll();
        for (int i =0 ; i < recodes.size() ; i++){
            scanListAdapter.onItemAdd(recodes.get(i));
        }
    }


    @Override
    public void onScanListClicked(Recode recode) {

        ExcuteDialogFragment excuteDialogFragment = ExcuteDialogFragment.getInstace(recode);
        excuteDialogFragment.show(getActivity().getSupportFragmentManager() , recode.getData());
    }
}

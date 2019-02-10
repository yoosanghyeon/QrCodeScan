package com.nvqrcode.yoosanghyeon.noadvertiasmentqrcode.scanlist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.nvqrcode.yoosanghyeon.noadvertiasmentqrcode.R;
import com.nvqrcode.yoosanghyeon.noadvertiasmentqrcode.roomdata.Recode;
import com.nvqrcode.yoosanghyeon.noadvertiasmentqrcode.scanlist.scanlistinterface.ScanListClickListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ScanListViewHolder extends RecyclerView.ViewHolder {

    private TextView textView;
    private TextView dateTextView;

    public ScanListViewHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.scan_list_textview);
        dateTextView = itemView.findViewById(R.id.scan_list_date_textview);
    }

    public void onBindViewholder(final Recode recode , final ScanListClickListener scanListClickListener){
        textView.setText(recode.getData());
        dateTextView.setText(toDateFormat(recode.getTimestamp()));
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanListClickListener.onScanListClicked(recode);
            }
        });
    }

    private String toDateFormat(Long date){
        String dateString = new SimpleDateFormat("yyyy/MM/dd hh:mm").format(new Date(date));
        return dateString;
    }

}

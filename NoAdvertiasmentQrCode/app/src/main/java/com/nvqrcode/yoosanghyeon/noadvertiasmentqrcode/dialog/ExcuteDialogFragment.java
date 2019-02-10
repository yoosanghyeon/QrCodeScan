package com.nvqrcode.yoosanghyeon.noadvertiasmentqrcode.dialog;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.nvqrcode.yoosanghyeon.noadvertiasmentqrcode.R;
import com.nvqrcode.yoosanghyeon.noadvertiasmentqrcode.roomdata.Recode;

public class ExcuteDialogFragment extends DialogFragment implements View.OnClickListener {

    private Recode recode;

    private AppCompatButton dialogCancelBtn;
    private TextView dialog_data_text;

    private CardView excute_clipboard_cardview;
    private CardView excute_internet_cardview;


    public void setRecode(Recode recode) {
        this.recode = recode;
    }


    public static ExcuteDialogFragment getInstace(Recode recode) {
        ExcuteDialogFragment excuteDialogFragment = new ExcuteDialogFragment();
        excuteDialogFragment.setRecode(recode);
        return excuteDialogFragment;
    }

//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_excute, container, false);

        dialogCancelBtn = view.findViewById(R.id.dialog_cancel);
        dialogCancelBtn.setOnClickListener(this);

        dialog_data_text = view.findViewById(R.id.dialog_data_text);
        dialog_data_text.setText(recode.getData());

        excute_clipboard_cardview = view.findViewById(R.id.excute_clipboard_cardview);
        excute_internet_cardview = view.findViewById(R.id.excute_internet_cardview);

        excute_clipboard_cardview.setOnClickListener(this);
        excute_internet_cardview.setOnClickListener(this);


        // remove dialog title
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        // remove dialog background
        getDialog().getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));


        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dialog_cancel:
                dismiss();
                break;
            case R.id.excute_clipboard_cardview :
                ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("qrcodeText", recode.getData());
                clipboard.setPrimaryClip(clip);

                Toast.makeText(getContext() , "텍스트 복사가 완료되었습니다", Toast.LENGTH_SHORT).show();
                dismiss();
                break;
            case R.id.excute_internet_cardview:
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(recode.getData()));
                startActivity(intent);
                dismiss();
                break;
        }
    }

}
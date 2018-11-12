package com.yoosanghyeon.qrcodescan;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {



  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    IntentIntegrator integrator = new IntentIntegrator(this);
    integrator.setOrientationLocked(false);
    integrator.initiateScan();

  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    // QR코드/ 바코드를 스캔한 결과
    IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(result.getContents()));
    startActivity(intent);
    finish();
  }


}

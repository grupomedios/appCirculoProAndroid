package com.grupomedios.circulo_pro.android.warranty.activity;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.grupomedios.circulo_pro.android.R;
import com.grupomedios.circulo_pro.android.common.activity.DesclubGeneralActivity;
import com.grupomedios.circulo_pro.api.facade.CorporateMembershipFacade;
import com.grupomedios.circulo_pro.api.representations.CorporateMembershipRepresentation;
import com.grupomedios.circulo_pro.util.security.UserHelper;
import com.grupomedios.circulo_pro.util.utils.TakePictureActivity;

import java.io.File;

import javax.inject.Inject;

public class RequestWarrantyActivity extends DesclubGeneralActivity {

    private final static String TAG = "RequestWarrantyActivity";
    private static final int REQUEST_CODE_TAKE_PICTURE = 100;
    private String mPicturePath;

    @Inject
    UserHelper userHelper;

    @Inject
    CorporateMembershipFacade corporateMembershipFacade;

    private TextView mBtnScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUi();
    }

    private void initUi() {
        mBtnScan = (TextView) findViewById(R.id.request_warranty_btn_scan);
        findViewById(R.id.request_warranty_btn_call).setOnClickListener(mOnClickOnCall);
        findViewById(R.id.request_warranty_btn_scan).setOnClickListener(mOnClickOnScan);
        findViewById(R.id.warranty_btn_close).setOnClickListener(mOnClickOnClose);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_request_warranty;
    }

    private View.OnClickListener mOnClickOnCall = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.fromParts("tel", getString(R.string.contact_phone), null));
            startActivity(intent);
        }
    };

    private View.OnClickListener mOnClickOnScan = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            takePicture();
        }
    };

    protected void sendEmail() {
        String userData = "";

        try {
            CorporateMembershipRepresentation currentUser = userHelper.getCurrentUser();
            userData = String.format(getString(R.string.warranty_body), getString(R.string.app_name), currentUser.getName(), currentUser.getEmail(), userHelper.getCardNumber(), userHelper.getValidThru());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", getString(R.string.contact_email), null));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.contact_subject));
            emailIntent.putExtra(Intent.EXTRA_TEXT, userData);
            emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(mPicturePath)));
            startActivity(Intent.createChooser(emailIntent, getString(R.string.contact_chooser_title)));
        } catch (Exception e) {
            Toast.makeText(this, R.string.warranty_email_error, Toast.LENGTH_SHORT).show();

        }
    }


    public void takePicture() {
        startActivityForResult(new Intent(this,
                TakePictureActivity.class), REQUEST_CODE_TAKE_PICTURE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CODE_TAKE_PICTURE) {
                mPicturePath = data
                        .getStringExtra(TakePictureActivity.ARG_DATA_PICTURE);
                if (mPicturePath != null && !mPicturePath.isEmpty()) {
                    sendEmail();
                    mBtnScan.setBackgroundResource(R.drawable.oval_background_grey);
                } else
                    Toast.makeText(this, R.string.warranty_scan_error, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private View.OnClickListener mOnClickOnClose = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            onBackPressed();
        }
    };
}

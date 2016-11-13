package com.zulu.publicidadinappa;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class MainActivity extends AppCompatActivity {

    private static final int ACTIVITY_FOR_RESULT_REQUEST_CODE = 222222;
    private AdView mBottomBanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Obtenemos el banner
        mBottomBanner = (AdView) findViewById(R.id.av_bottom_banner);
        AdRequest adRequest = new AdRequest.Builder()
                //        .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("1B20B54F213FB3C22C8DA78BEA799F68")
                .build();
        //En produccion usar ->  AdRequest adRequest = new AdRequest.Builder().build();


        mBottomBanner.loadAd(adRequest);
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (mBottomBanner != null) {
            mBottomBanner.pause();

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBottomBanner != null) {
            mBottomBanner.destroy();

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mBottomBanner != null) {
            mBottomBanner.resume();

        }
        /*

        If the result code is SUCCESS,
        then the Google Play services APK is up-to-date and you can continue
        to make a connection.
        If, however, the result code is SERVICE_MISSING,
        SERVICE_VERSION_UPDATE_REQUIRED, or SERVICE_DISABLED,
        then the user needs to install an update. In this case,
        call the getErrorDialog() method and pass it the result error code.
        The method returns a Dialog you should show,
        which provides an appropriate message about the error and provides
        an action that takes the user to Google Play Store to install the update.

         */

        //Verificamos que los google play services esten activados
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        int statusCode = googleApiAvailability.isGooglePlayServicesAvailable(this);
        if (statusCode != ConnectionResult.SUCCESS) {
            googleApiAvailability.getErrorDialog(this, statusCode, ACTIVITY_FOR_RESULT_REQUEST_CODE, new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    Toast.makeText(MainActivity.this,"Debe actualizar su version de google play services para ver la publicidad",Toast.LENGTH_LONG).show();
                }
            });
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == ACTIVITY_FOR_RESULT_REQUEST_CODE && resultCode == RESULT_OK){
            //Manejar el intent data
        }

    }
}

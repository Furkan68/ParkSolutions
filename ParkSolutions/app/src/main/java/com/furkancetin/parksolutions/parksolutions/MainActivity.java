package com.furkancetin.parksolutions.parksolutions;

import android.app.AlertDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static String LOG_TAG = "MA";

    private DownloaderTask _mTask;
    private TextView _available;
    private FrameLayout _secondPanel;
    private TextView _boxTitle;
    private ImageView _parkGreen;
    private ImageView _parkRed;

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (isOnline()) {
            _mTask = new DownloaderTask(this);
            _mTask.execute();
        } else {
            new AlertDialog.Builder(this)
                    .setTitle(getString(R.string.dialog_internet_title))
                    .setMessage(getString(R.string.dialog_internet_text))
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }

    }

    public void setData(Data data) {
        _available = (TextView) findViewById(R.id.available_places);
        _secondPanel = (FrameLayout) findViewById(R.id.secondPanel);
        _boxTitle = (TextView) findViewById(R.id.box_title);
        _parkGreen = (ImageView) findViewById(R.id.park_green);
        _parkRed = (ImageView) findViewById(R.id.park_red);


        Log.d(LOG_TAG, Boolean.toString(data.available));
        Log.d(LOG_TAG, Double.toString(data.distance));

        if(data.available){
            _boxTitle.setText(R.string.park_available);
            _parkGreen.setAlpha(1f);
            //_parkGreen.setVisibility(View.VISIBLE);
            _parkRed.setAlpha(0.1f);
            //_parkRed.setVisibility(View.INVISIBLE);
            _available.setText("1");
            _secondPanel.setBackgroundColor(getResources().getColor(R.color.orange));
        }else{
            _boxTitle.setText(R.string.park_unavailable);
            //_parkGreen.setVisibility(View.INVISIBLE);
            _parkGreen.setAlpha(0.1f);
            //_parkRed.setVisibility(View.VISIBLE);
            _parkRed.setAlpha(1f);
            _available.setText("0");
            _secondPanel.setBackgroundColor(getResources().getColor(R.color.red));

        }
    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }
}
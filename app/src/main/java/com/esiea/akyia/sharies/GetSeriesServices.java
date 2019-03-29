package com.esiea.akyia.sharies;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;


public class GetSeriesServices extends IntentService {

    private static final String ACTION_get_all_series = "com.esiea.akyia.sheries.action.ACTION_get_all_series";

    public GetSeriesServices() {
        super("GetSeriesServices");
    }

    public static void startActionSeries(Context context) {
        Log.d("test", "startActionSeries: debut");
        Intent intent = new Intent(context, GetSeriesServices.class);
        intent.setAction(ACTION_get_all_series);

        context.startService(intent);
        Log.d("test", "startActionSeries: fin");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_get_all_series.equals(action)) {

                handleActionSeries();
            }
        }
    }

    private void handleActionSeries(){
        Log.d("test", "Thread service name:"+ Thread.currentThread().getName());
        URL url = null;
        try
        {
            url = new URL(String.valueOf("https://raw.githubusercontent.com/EurydiceAscar/AKYIA/master/psycho.json"));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            Log.d("test", "handleActionSeries: apres connect");
            if (HttpsURLConnection.HTTP_OK == conn.getResponseCode()) {
                File file = new File(getCacheDir(), "series.json");
                copyInputStreamToFile(conn.getInputStream(),file);
                Log.d("handleActionSeries", "Series json downloaded !");

                Log.d("test", "handleActionSeries: "+ conn);

            }
        }catch(MalformedURLException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(MainActivity.SERIES_UPDATE));
        Log.d("test", "handleActionSeries: fin");
    }

    private void copyInputStreamToFile(InputStream in, File file){
        Log.d("test", "copyInputStreamToFile: debut");
        try{
            OutputStream out = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len;
            while((len=in.read(buf))>0){
                out.write(buf,0,len);
            }
            out.close();
            in.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        Log.d("test", "copyInputStreamToFile: fin");
    }

}



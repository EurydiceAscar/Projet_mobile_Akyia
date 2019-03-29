package com.esiea.akyia.sharies;

import android.app.DownloadManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class FilmActivity extends AppCompatActivity {

    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film);

        String url = getIntent().getExtras().getString("url");
        String nom = getIntent().getExtras().getString("nom");
        String dates = getIntent().getExtras().getString("dates");
        String description = getIntent().getExtras().getString("description");

       /* final String poster_path = getIntent().getExtras().getString("poster_path");
        final String backdrop_path = getIntent().getExtras().getString("backdrop_path");*/

        this.id = String.valueOf(getIntent().getExtras().getInt("id"));

        ImageView portraitView = findViewById(R.id.portrait);
        TextView nameView = findViewById(R.id.nom);
        TextView datesView = findViewById(R.id.dates);
        TextView descriptionView = findViewById(R.id.description);

        Picasso.with(this).load(url).into(portraitView);
        nameView.setText(nom);
        datesView.setText(dates);
        descriptionView.setText(description);

/*        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri url = Uri.parse("https://image.tmdb.org/t/p/w500"+poster_path);
                DownloadManager.Request r1 = new DownloadManager.Request(url);
                r1.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "poster");
                r1.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                dm.enqueue(r1);
                Toast.makeText(getApplicationContext(),R.string.dl,Toast.LENGTH_LONG).show();
            }
        });

        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri url = Uri.parse("https://image.tmdb.org/t/p/w500"+backdrop_path);
                DownloadManager.Request r1 = new DownloadManager.Request(url);
                r1.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "background");
                r1.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                dm.enqueue(r1);
                Toast.makeText(getApplicationContext(),R.string.dl, Toast.LENGTH_LONG).show();

            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d("test", "onCreateOptionsMenu: appel");
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_film, menu);
        return true;

    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            case R.id.buttoninfo:
                Uri webpage = Uri.parse("https://www.themoviedb.org/movie/"+ id);
                Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(webIntent);
                return true;
        }

        return super.onOptionsItemSelected(item);
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

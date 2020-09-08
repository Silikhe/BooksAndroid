package com.sil.books;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvResult = (TextView) findViewById(R.id.tvResponse);

        try {
            URL bookUrl = ApiUtils.buildUrl("cooking");
            String jsonResult = ApiUtils.getJson(bookUrl);
            tvResult.setText(jsonResult);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public class BookQueryTask extends AsyncTaskLoader<URL, Void, String> {

        @Nullable
        @Override
        public URL loadInBackground() {
            return null;
        }
    }
}o laptop charger

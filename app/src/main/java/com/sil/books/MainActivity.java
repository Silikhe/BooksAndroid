package com.sil.books;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ProgressBar mLoadingProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLoadingProgress = (ProgressBar) findViewById(R.id.pb_loading);

        try {
            URL bookUrl = ApiUtils.buildUrl("cooking");

            new BooksQueryTask().execute(bookUrl);

        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }
    }

    public class BooksQueryTask extends AsyncTask<URL, Void, String> {


        @Override
        protected String doInBackground(URL... urls) {
            URL searchURL = urls[0];
            String result = null;

            try {
                result = ApiUtils.getJson(searchURL);
            } catch (IOException e) {
                Log.e("Error", e.getMessage());
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            TextView tvResult = (TextView) findViewById(R.id.tvResponse);
            TextView tvError = (TextView) findViewById(R.id.tv_error);
            tvResult.setText(result);
            mLoadingProgress.setVisibility(View.INVISIBLE);
            if (result == null){
                tvResult.setVisibility(View.INVISIBLE);
                tvError.setVisibility(View.VISIBLE);
            }else {
                tvResult.setVisibility(View.VISIBLE);
                tvError.setVisibility(View.INVISIBLE);
            }
            ArrayList<Book> books = ApiUtils.getBooksFromJson(result);
            String resultString = "";

            for (Book book : books ){
                resultString = resultString + book.title + "\n" +
                        book.publishedDate + "\n\n" ;
            }
            tvResult.setText(resultString);

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingProgress.setVisibility(View.VISIBLE);
        }
    }
}

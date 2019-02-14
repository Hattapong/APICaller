package com.aum.apicaller;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.aum.apicaller.helper.HttpHelper;
import com.aum.apicaller.helper.ResponseMessage;

import java.util.concurrent.ExecutionException;

import okhttp3.internal.http.HttpMethod;

public class MainActivity extends AppCompatActivity {

    EditText txtUrl;
    private EditText txtCode;
    private EditText txtMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtUrl = findViewById(R.id.txtUrl);
        txtCode = findViewById(R.id.txtCode);
        txtMessage = findViewById(R.id.txtMessage);

        findViewById(R.id.btnCmd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                BackgroundTask task = new BackgroundTask();
                ResponseMessage message = null;
                try {
                    message = task.execute("").get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                showResult(Integer.toString(message.getStatusCode()),message.getMessage());

            }
        });


    }

    void showResult(String code,String msg){
        txtCode.setText(code);
        txtMessage.setText(msg);
    }

    class BackgroundTask extends AsyncTask<String,String,ResponseMessage> {

        @Override
        protected ResponseMessage doInBackground(String... strings) {
            HttpHelper httpHelper = HttpHelper.getInstance();


            return httpHelper.setMethod(HttpHelper.Verb.GET)
                    .setUrl(txtUrl.getText().toString())
                    .getResponseMessage("");
        }
    }
}

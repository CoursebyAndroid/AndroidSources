package com.leshchenko.root.filemanager;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "MainActivity";
    private EditText textWrite;
    private TextView textRead;
    private Button   btnSave;
    private Button   btnRead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textWrite = (EditText) findViewById(R.id.text_view_write);
        textRead = (TextView)findViewById(R.id.text_view_read);
        btnSave = (Button)findViewById(R.id.btn_view_write);
        btnRead = (Button)findViewById(R.id.btn_view_read);
        btnSave.setOnClickListener(this);
    }



    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_view_write : writeBtn();
                break;
            case R.id.btn_view_read : reabBtn();
                break;
            default: throw new NullPointerException("No select click");
        }
    }

    private void writeBtn(){
        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            Log.d(TAG, "SD-карта не доступна: " + Environment.getExternalStorageState());
            return;
        }

            String dirName = Environment.getExternalStorageDirectory() + "/Airlogger/";
            File dirFile = new File(dirName);
            if (!dirFile.exists()) {
                dirFile.mkdir();
            }
            File file = new File(dirName + fileName());
         try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(textWrite.getText().toString());
            bw.close();
            Toast.makeText(this,"Запись в файл",Toast.LENGTH_LONG);
            Log.d(TAG, "Файл записан на SD: " + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private String fileName(){
        String date  = null;
        String time = null;
        Date utc = new Date();
        SimpleDateFormat dateFormatA = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat dateFormatB = new SimpleDateFormat("HH:mm:ss");
        date = dateFormatA.format(utc);
        time = dateFormatB.format(utc);
        return "Airlogger" + "_" + date + "_" + time+".txt";
    }


    private void reabBtn(){
        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            Log.d(TAG, "SD-карта не доступна: " + Environment.getExternalStorageState());
            return;
        }


    }
}

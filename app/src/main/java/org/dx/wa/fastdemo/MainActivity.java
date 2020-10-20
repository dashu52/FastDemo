package org.dx.wa.fastdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
    }

    public void hiLog(View view){
        Intent it = new Intent(this,HiLogActivity.class);
        startActivity(it);
    }
    public void hiExecutor(View view){
        Intent it = new Intent(this,HiExecutorActivity.class);
        startActivity(it);
    }
}
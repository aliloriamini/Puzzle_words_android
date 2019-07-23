 package com.example.ali.firstapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

 public class MainActivity extends AppCompatActivity {
   public static int gameDifficulty = 2;


   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     Log.i("test",""+gameDifficulty);
      Button btn_start = (Button) findViewById(R.id.btn_start);
      Button btn_get_coin = (Button) findViewById(R.id.btn_get_coin);
      Button btn_setting = (Button) findViewById(R.id.btn_setting);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent_start = new Intent(MainActivity.this, level.class);
              MainActivity.this.startActivity(intent_start);
            }
        });
//      btn_get_coin.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//          Intent intent_start = new Intent(MainActivity.this, level.class);
//          MainActivity.this.startActivity(intent_start);
//        }
//      });
      btn_setting.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          Intent setting = new Intent(MainActivity.this, setting.class);
          MainActivity.this.startActivity(setting);
        }
      });

    }
}

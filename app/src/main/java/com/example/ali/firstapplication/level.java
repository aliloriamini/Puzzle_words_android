package com.example.ali.firstapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class level extends AppCompatActivity {

  TextView btn_lvl_1;
  TextView btn_lvl_2;
  TextView btn_lvl_3;
  TextView btn_lvl_4;
  TextView btn_lvl_5;
  TextView btn_lvl_6;
  TextView btn_lvl_7;
  TextView btn_lvl_8;
  TextView btn_lvl_9;
  TextView btn_lvl_10;
  TextView btn_lvl_11;
  TextView btn_lvl_12;
  TextView btn_lvl_13;
  TextView btn_lvl_14;
  TextView btn_lvl_15;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_level);

    btn_lvl_1 = (TextView) findViewById(R.id.btn_lvl_1);
    btn_lvl_2 = (TextView) findViewById(R.id.btn_lvl_2);
    btn_lvl_3 = (TextView) findViewById(R.id.btn_lvl_3);
    btn_lvl_4 = (TextView) findViewById(R.id.btn_lvl_4);
    btn_lvl_5 = (TextView) findViewById(R.id.btn_lvl_5);
    btn_lvl_6 = (TextView) findViewById(R.id.btn_lvl_6);
    btn_lvl_7 = (TextView) findViewById(R.id.btn_lvl_7);
    btn_lvl_8 = (TextView) findViewById(R.id.btn_lvl_8);
    btn_lvl_9 = (TextView) findViewById(R.id.btn_lvl_9);
    btn_lvl_10 = (TextView) findViewById(R.id.btn_lvl_10);
    btn_lvl_11 = (TextView) findViewById(R.id.btn_lvl_11);
    btn_lvl_12 = (TextView) findViewById(R.id.btn_lvl_12);
    btn_lvl_13 = (TextView) findViewById(R.id.btn_lvl_13);
    btn_lvl_14 = (TextView) findViewById(R.id.btn_lvl_14);
    btn_lvl_15 = (TextView) findViewById(R.id.btn_lvl_15);

    View.OnClickListener listner = new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String game_level = v.getTag().toString();
        Intent intent_start = new Intent(level.this, Main_game.class);
        intent_start.putExtra("game_level",game_level);
        level.this.startActivity(intent_start);
      }
    };

    btn_lvl_1.setOnClickListener(listner);
    btn_lvl_2.setOnClickListener(listner);
    btn_lvl_3.setOnClickListener(listner);
    btn_lvl_4.setOnClickListener(listner);
    btn_lvl_5.setOnClickListener(listner);
    btn_lvl_6.setOnClickListener(listner);
    btn_lvl_7.setOnClickListener(listner);
    btn_lvl_8.setOnClickListener(listner);
    btn_lvl_9.setOnClickListener(listner);
    btn_lvl_10.setOnClickListener(listner);
    btn_lvl_11.setOnClickListener(listner);
    btn_lvl_12.setOnClickListener(listner);
    btn_lvl_13.setOnClickListener(listner);
    btn_lvl_14.setOnClickListener(listner);
    btn_lvl_15.setOnClickListener(listner);

  }
}

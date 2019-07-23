package com.example.ali.firstapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class setting extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_setting);

    Button btn_easy = (Button) findViewById(R.id.btn_easy);
    Button btn_normal = (Button) findViewById(R.id.btn_normal);
    Button btn_hard = (Button) findViewById(R.id.btn_hard);

      View.OnClickListener difListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          String difficulty = v.getTag().toString();
          Intent intent_difficulty = new Intent(setting.this, MainActivity.class);
          MainActivity.gameDifficulty =Integer.parseInt(difficulty);
          setting.this.startActivity(intent_difficulty);
        }
      };

    btn_easy.setOnClickListener(difListener);
    btn_normal.setOnClickListener(difListener);
    btn_hard.setOnClickListener(difListener);



  }
}

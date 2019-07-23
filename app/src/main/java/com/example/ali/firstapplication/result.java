package com.example.ali.firstapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class result extends AppCompatActivity {

  String game_level;
  int game_result;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_result);

    Button try_again = (Button) findViewById(R.id.try_again);
    Button go_next_level = (Button) findViewById(R.id.go_next_level);
    LinearLayout coin_result = (LinearLayout)findViewById(R.id.coin_result);
    TextView result_show = (TextView) findViewById(R.id.result_show);

    Intent result = getIntent();
    Bundle result_bundle = result.getExtras();
    game_level = result_bundle.getString("game_levels");
    game_result = result_bundle.getInt("game_result");

    if (game_result == 1){
      coin_result.setVisibility(View.VISIBLE);
      result_show.setText("بردی ");
    }

    View.OnClickListener btnNextListener = new View.OnClickListener(){
      @Override
      public void onClick(View v){
        if (Integer.parseInt(game_level) <= 14) {
          int levels = Integer.parseInt(game_level) + 1;
          game_level = "" + levels;
        }
        Intent intent_start = new Intent(result.this, Main_game.class);
        intent_start.putExtra("game_level",game_level);
        result.this.startActivity(intent_start);
        finish();
      }
    };
    View.OnClickListener btnReplayListener = new View.OnClickListener(){
      @Override
      public void onClick(View v){
        Intent intent_start = new Intent(result.this, Main_game.class);
        intent_start.putExtra("game_level",game_level);
        result.this.startActivity(intent_start);
        finish();
      }
    };
    try_again.setOnClickListener(btnReplayListener);
    go_next_level.setOnClickListener(btnNextListener);
  }
}

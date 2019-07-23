package com.example.ali.firstapplication;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;


public class Main_game extends AppCompatActivity {

  TextView txt_1;TextView txt_2;TextView txt_3;TextView txt_4;TextView txt_5;
  TextView txt_6;TextView txt_7;TextView txt_8;TextView txt_9;TextView txt_10;
  TextView txt_11;TextView txt_12;TextView txt_13;TextView txt_14;TextView txt_15;
  TextView txt_16;TextView txt_17;TextView txt_18;TextView txt_19;TextView txt_20;
  TextView txt_21;TextView txt_22;TextView txt_23;TextView txt_24;TextView txt_25;
  TextView txt_26;TextView txt_27;TextView txt_28;TextView txt_29;TextView txt_30;
  TextView txt_31;TextView txt_32; TextView txt_33;
  TextView txt_gold_num;
  TextView game_time;


  Handler timeHandler;
  public static int goldNum = 100;
  public int wordCorrect;
  TextView txt_word;
  String word;
  private TextView[] btnDic;
  final int btnDicNum =33;
  int timer = 10;
  boolean thread_continue = true,clear_pop = true,show_pop = true,time_pop = true;
  String level;
  ImageView showPopupBtn;
  Button btn_clear,closePopupBtn,btn_add_time,btn_show;
  PopupWindow popupWindow;
  LinearLayout linearLayout1;


  private int imgSrc(int imgSrcNum){
    int[] imgSrcs = new int[15];
    imgSrcs[0] = getResources().getIdentifier("image", "drawable", getPackageName());
    imgSrcs[1] = getResources().getIdentifier("image1", "drawable", getPackageName());
    return imgSrcs[imgSrcNum%2];
  }
  private String selectedword(int wordNumber){
    String[] words = new String[15];
    words[0] = "هندونه";
    words[1] = "خردل";
    words[2] = "کاهو";
    words[3] = "شاهزاده و گدا";
    words[4] = "ژاپن";
    words[5] = "شمعدانی";
    words[6] = "کاکتوس";
    words[7] = "چقندر";
    words[8] = "ماهیتابه";
    words[9] = "سمرقند";
    words[10] = "کشمش";
    words[11] = "عرق نعنا";
    words[12] = "خیار سالادی";
    words[13] = "پشمک حاج خلیفه";
    words[14] = "عقرب";
    return words[wordNumber];
  }
  private String wordDashed;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main_game);

    Intent lvl = getIntent();
    Bundle lvl_bundle = lvl.getExtras();
    level = lvl_bundle.getString("game_level");


    word = selectedword(Integer.parseInt(level) - 1);
    wordCorrect = 0;

    ImageView img_question = (ImageView) findViewById(R.id.img_question);
    img_question.setImageResource(imgSrc(Integer.parseInt(level) - 1));

    wordDashed = "";
    for(int i=0;i<word.length();i++){
      if (word.charAt(i) != ' '){
        wordDashed +='-';
      }else {
        wordDashed +=' ';
      }
    }
    txt_word = (TextView) findViewById(R.id.txt_word);
    txt_word.setText(wordDashed);

    txt_gold_num = (TextView) findViewById(R.id.txt_gold_num);
    txt_gold_num.setText("" + goldNum);

    // popup
    showPopupBtn = (ImageView) findViewById(R.id.showPopupBtn);
    linearLayout1 = (LinearLayout) findViewById(R.id.MainGame_linearLayout);

    showPopupBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        //instantiate the popup.xml layout file
        LayoutInflater layoutInflater = (LayoutInflater) Main_game.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View customView = layoutInflater.inflate(R.layout.popup,null);

        btn_clear = (Button) customView.findViewById(R.id.btn_clear);
        btn_show = (Button) customView.findViewById(R.id.btn_show);
        btn_add_time = (Button) customView.findViewById(R.id.btn_add_time);
        closePopupBtn = (Button) customView.findViewById(R.id.closePopupBtn);
        if (!clear_pop){
          btn_clear.setVisibility(View.INVISIBLE);
        }
        if (!show_pop){
          btn_show.setVisibility(View.INVISIBLE);
        }
        if (!time_pop){
          btn_add_time.setVisibility(View.INVISIBLE);
        }

        //instantiate popup window
        popupWindow = new PopupWindow(customView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

//        display the popup window
//        popupWindow.setHeight(300);
//        popupWindow.setWidth(linearLayout1.getWidth()+15);
        popupWindow.showAtLocation(linearLayout1, Gravity.CENTER, 0, 0);
        popupWindow.setOutsideTouchable(true);
        //close the popup window on button click
        closePopupBtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            popupWindow.dismiss();
          }
        });
        btn_clear.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            hideFalse();
            clear_pop = false;
            popupWindow.dismiss();
          }
        });
        btn_show.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            show_pop = false;
            hintShow();
            popupWindow.dismiss();
          }
        });
        btn_add_time.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            time_pop = false;
            add_time();
            popupWindow.dismiss();
          }
        });
      }
    });
    ////////////////////////////////////////////////////////////////////////////////////
    // timer
    timeHandler = new Handler();
    game_time = (TextView) findViewById(R.id.game_time);
    if (MainActivity.gameDifficulty != 1) {
      timer = word.length() * 10 / (MainActivity.gameDifficulty - 1);
//        timer = 10;
    }
    else {
      game_time.setVisibility(View.INVISIBLE);
    }
    game_time.setText(""+timer);
    new Thread(new Runnable() {
      @Override
      public void run() {
        while (timer>0 && MainActivity.gameDifficulty != 1 && thread_continue ){
          try {
            timeHandler.post(new Runnable() {
                               @Override
                               public void run() {
                                 game_time.setText(""+timer);
                               }
                             });
              timer--;
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
        if (timer == 0){
          Intent intent_start = new Intent(Main_game.this, result.class);
          intent_start.putExtra("game_result",0);
          intent_start.putExtra("game_levels",level);
          Main_game.this.startActivity(intent_start);
          finish();
        }
      }
    }).start();

    //

    btnDic = new TextView[btnDicNum];
    btnDic[0] = txt_1 = (TextView) findViewById(R.id.txt_1);
    btnDic[1] = txt_2 = (TextView) findViewById(R.id.txt_2);
    btnDic[2] = txt_3 = (TextView) findViewById(R.id.txt_3);
    btnDic[3] = txt_4 = (TextView) findViewById(R.id.txt_4);
    btnDic[4] = txt_5 = (TextView) findViewById(R.id.txt_5);
    btnDic[5] = txt_6 = (TextView) findViewById(R.id.txt_6);
    btnDic[6] = txt_7 = (TextView) findViewById(R.id.txt_7);
    btnDic[7] = txt_8 = (TextView) findViewById(R.id.txt_8);
    btnDic[8] = txt_9 = (TextView) findViewById(R.id.txt_9);
    btnDic[9] = txt_10 = (TextView) findViewById(R.id.txt_10);
    btnDic[10] = txt_11 = (TextView) findViewById(R.id.txt_11);
    btnDic[11] = txt_12 = (TextView) findViewById(R.id.txt_12);
    btnDic[12] = txt_13 = (TextView) findViewById(R.id.txt_13);
    btnDic[13] = txt_14 = (TextView) findViewById(R.id.txt_14);
    btnDic[14] = txt_15 = (TextView) findViewById(R.id.txt_15);
    btnDic[15] = txt_16 = (TextView) findViewById(R.id.txt_16);
    btnDic[16] = txt_17 = (TextView) findViewById(R.id.txt_17);
    btnDic[17] = txt_18 = (TextView) findViewById(R.id.txt_18);
    btnDic[18] = txt_19 = (TextView) findViewById(R.id.txt_19);
    btnDic[19] = txt_20 = (TextView) findViewById(R.id.txt_20);
    btnDic[20] = txt_21 = (TextView) findViewById(R.id.txt_21);
    btnDic[21] = txt_22 = (TextView) findViewById(R.id.txt_22);
    btnDic[22] = txt_23 = (TextView) findViewById(R.id.txt_23);
    btnDic[23] = txt_24 = (TextView) findViewById(R.id.txt_24);
    btnDic[24] = txt_25 = (TextView) findViewById(R.id.txt_25);
    btnDic[25] = txt_26 = (TextView) findViewById(R.id.txt_26);
    btnDic[26] = txt_27 = (TextView) findViewById(R.id.txt_27);
    btnDic[27] = txt_28 = (TextView) findViewById(R.id.txt_28);
    btnDic[28] = txt_29 = (TextView) findViewById(R.id.txt_29);
    btnDic[29] = txt_30 = (TextView) findViewById(R.id.txt_30);
    btnDic[30] = txt_31 = (TextView) findViewById(R.id.txt_31);
    btnDic[31] = txt_32 = (TextView) findViewById(R.id.txt_32);
    btnDic[32] = txt_33 = (TextView) findViewById(R.id.txt_33);

    View.OnClickListener btn_listener = new View.OnClickListener(){
      @Override
      public void onClick(View v){
        TextView textView = (TextView) v;
        String gameBtn = v.getTag().toString();
        if (word.contains(gameBtn) && goldNum > 0){
          textView.setVisibility(View.INVISIBLE);
          Log.i("test","won "+gameBtn);
          for (int i =0;i<wordDashed.length();i++){
            if (word.charAt(i) == gameBtn.charAt(0)){
              char[] wordDashedCharArray = wordDashed.toCharArray();
              wordDashedCharArray[i] = gameBtn.charAt(0);
              wordDashed = new String(wordDashedCharArray);
              txt_word.setText(wordDashed);
              wordCorrect ++;
              if (!wordDashed.contains("-")){
                goldNum +=30;
                txt_gold_num.setText("" + goldNum);
                ///////////////////////////////////////////////////////////////////////////////////////////////
                Log.i("test",""+level);
                Intent intent_start = new Intent(Main_game.this, result.class);
                intent_start.putExtra("game_result",1);
                intent_start.putExtra("game_levels",level);
                Main_game.this.startActivity(intent_start);
                thread_continue = false;
                finish();
                ///////////////////////////////////////////////////////////////////////////////////////////////
                Log.i("test","won complet ");
              }
              if ((word.length() - wordCorrect) < 3){
                show_pop =false;
              }
            }
          }
        }else if (goldNum > 0) {
          textView.setVisibility(View.INVISIBLE);
          goldNum -=5;
          txt_gold_num.setText("" + goldNum);
          Log.i("test","lose "+gameBtn);
        }
      }
    };

    txt_1.setOnClickListener(btn_listener);
    txt_2.setOnClickListener(btn_listener);
    txt_3.setOnClickListener(btn_listener);
    txt_4.setOnClickListener(btn_listener);
    txt_5.setOnClickListener(btn_listener);
    txt_6.setOnClickListener(btn_listener);
    txt_7.setOnClickListener(btn_listener);
    txt_8.setOnClickListener(btn_listener);
    txt_9.setOnClickListener(btn_listener);
    txt_10.setOnClickListener(btn_listener);
    txt_11.setOnClickListener(btn_listener);
    txt_12.setOnClickListener(btn_listener);
    txt_13.setOnClickListener(btn_listener);
    txt_14.setOnClickListener(btn_listener);
    txt_15.setOnClickListener(btn_listener);
    txt_16.setOnClickListener(btn_listener);
    txt_17.setOnClickListener(btn_listener);
    txt_18.setOnClickListener(btn_listener);
    txt_19.setOnClickListener(btn_listener);
    txt_20.setOnClickListener(btn_listener);
    txt_21.setOnClickListener(btn_listener);
    txt_22.setOnClickListener(btn_listener);
    txt_23.setOnClickListener(btn_listener);
    txt_24.setOnClickListener(btn_listener);
    txt_25.setOnClickListener(btn_listener);
    txt_26.setOnClickListener(btn_listener);
    txt_27.setOnClickListener(btn_listener);
    txt_28.setOnClickListener(btn_listener);
    txt_29.setOnClickListener(btn_listener);
    txt_30.setOnClickListener(btn_listener);
    txt_31.setOnClickListener(btn_listener);
    txt_32.setOnClickListener(btn_listener);
    txt_33.setOnClickListener(btn_listener);

  }

  public void hideFalse(){
    int k =0;
    goldNum -=10;
    txt_gold_num.setText("" + goldNum);
    while (k<10) {
      int randFalse = (int) (Math.random() * 32);
      if (! word.contains(btnDic[randFalse].getTag().toString()) && btnDic[randFalse].getVisibility() != View.INVISIBLE) {
        btnDic[randFalse].setVisibility(View.INVISIBLE);
        k++;
      }
    }
  }

  public void add_time(){
    timer += 60;
    goldNum -=15;
    txt_gold_num.setText("" + goldNum);

  }

  public void hintShow(){
    int j =0;
    goldNum -=15;
    txt_gold_num.setText("" + goldNum);
      while (j < 2) {
        int randHint = (int) (Math.random() * word.length());
        for (int i = 0; i < wordDashed.length(); i++) {
          if (selectedword(Integer.parseInt(level) - 1).charAt(i) == word.charAt(randHint) && wordDashed.charAt(i) != word.charAt(randHint)) {
            char[] wordDashedCharArray = wordDashed.toCharArray();
            wordDashedCharArray[i] = word.charAt(randHint);
            wordDashed = new String(wordDashedCharArray);
            txt_word.setText(wordDashed);
            j++;
            break;
          }
        }
      }
    }
  }
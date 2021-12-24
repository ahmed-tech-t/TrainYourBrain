package com.example.trainbrain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;


public class Mainprogram extends AppCompatActivity {
    TextView tvtimer;
    TextView tvq;
    TextView tvscore;
    TextView tvres;
    TextView tvfscore;
    GridLayout gridLayout;
    LinearLayout linearLayout ;
    Button bult;
    Button burt;
    Button bulb;
    Button burb;
    int[] array={0,0,0,0};
    Button buplayagain;
    int rightResulttag;
    int manyOfQuestion =1;
    int score = 0 ;
    String tim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainprogram);
    }
    public void StartGame(View view){
        score =0;
        manyOfQuestion = 1;
        buplayagain= (Button)findViewById(R.id.buplayAgain);
        Button bugo =(Button) findViewById(R.id.bugo);
        bugo.setVisibility(view.INVISIBLE);
        buplayagain.setVisibility(view.GONE);
         gridLayout =(GridLayout) findViewById(R.id.gridLayout);
         linearLayout =(LinearLayout) findViewById(R.id.linearlayout);
        tvtimer =(TextView) findViewById(R.id.tvtimer);
        tvtimer.setText("30s");
        tvq = (TextView) findViewById(R.id.tvq);
        bult =(Button)findViewById(R.id.bult);
        burt =(Button)findViewById(R.id.burt);
        bulb =(Button)findViewById(R.id.bulb);
        burb =(Button)findViewById(R.id.burb);
        tvscore=(TextView)findViewById(R.id.tvscore);
        tvres =(TextView) findViewById(R.id.tvres);
        tvscore.setText(String.valueOf(score +"/"+manyOfQuestion));
        gridLayout.setVisibility(view.VISIBLE);
        linearLayout.setVisibility(view.VISIBLE);
        tvfscore =(TextView) findViewById(R.id.tvfscore);
        tvfscore.setVisibility(view.GONE);
    }
    public void SetTimer(final View view){
        CountDownTimer countDownTimer =new CountDownTimer(30000,100) {
            @Override
            public void onTick(long millisUntilFinished) {
                tim =String.valueOf((int)millisUntilFinished/1000);
                if(Integer.parseInt(tim)<10){
                    tim ="0"+tim;
                }
                tvtimer.setText(tim+"s");
            }

            @Override
            public void onFinish() {
                Log.i("run","finshed");
                tvfscore .setVisibility(view.VISIBLE);
                tvfscore.setText("your Score Is : "+score);
                buplayagain.setVisibility(view.VISIBLE);
            }
        }.start();
    }
    public void SetQuestion(){
        int firstNum = new Random().nextInt(100) + 1;
        int secondNum = new Random().nextInt(50) + 1;
        int result = firstNum + secondNum;
        tvq.setText(firstNum + " + " + secondNum);
        rightResulttag = new Random().nextInt(4);
        for (int i =0 ; i <4 ; i++ ){
           if(rightResulttag == i)
           {
            array[i] = result;
           }
           else
           {
               int wrogAnswer = new Random().nextInt(150) + 1;
               while (wrogAnswer == result) {
                   wrogAnswer = new Random().nextInt(150) + 1;
               }
               array[i]=wrogAnswer;
           }
       }
        bult.setText(Integer.toString(array[0]));
        burt.setText(Integer.toString(array[1]));
        bulb.setText(Integer.toString(array[2]));
        burb.setText(Integer.toString(array[3]));

    }
    public void bugo (View view){
        MediaPlayer media =MediaPlayer.create(getApplicationContext(),R.raw.go);
        media.start();
        StartGame(view);
        SetTimer(view);
        SetQuestion();
    }
    public void chickTheAnswer(View view){
    String result="";
    if(Integer.toString(rightResulttag).equals(view.getTag().toString()))
    {
       score++;
        result = "Right";
        MediaPlayer media =MediaPlayer.create(getApplicationContext(),R.raw.right_answer);
        media.start();
    }
    else
    {
        result = "Wrong";
        MediaPlayer media =MediaPlayer.create(getApplicationContext(),R.raw.wrong_answer);
        media.start();
    }
    tvres.setVisibility(View.VISIBLE);
    tvres.postDelayed(new Runnable() {
        public void run() {
            tvres.setVisibility(View.INVISIBLE);
        }
    }, 1000);
    tvres.setText(result);
}
    public void buchick(View view) {
        if(Integer.parseInt(tim)>0){
            manyOfQuestion =manyOfQuestion+1;
            chickTheAnswer(view);
            SetQuestion();
            tvscore.setText(String.valueOf(score +"/"+manyOfQuestion));
       }
    }
}
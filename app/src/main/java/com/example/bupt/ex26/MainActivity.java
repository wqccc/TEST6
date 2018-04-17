package com.example.bupt.ex26;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = (TextView) findViewById(R.id.textView_show);
        final Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg){
                textView.setText(msg.arg1 + "!");
            }
        };
        final Runnable myWorker = new Runnable(){
            @Override
            public void run(){
                int progress = 0;
                while(progress <= 100){
                    Message msg = new Message();
                    msg.arg1 = progress;
                    handler.sendMessage(msg);
                    progress += 1;
                    try{
                        Thread.sleep(1000);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
                Message msg = handler.obtainMessage();
                msg.arg1 = -1;
                handler.sendMessage(msg);
            }
        };
        Button button = (Button) findViewById(R.id.button_start);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Thread workThread = new Thread(null,myWorker,"WorkThread");
                workThread.start();
            }
        });
    }
}

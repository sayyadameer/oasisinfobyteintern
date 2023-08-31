package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;
import org.w3c.dom.Text;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView lblCounter;
    Button btnStart,btnStop,btnHold;
    int counter=0;
    boolean running=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lblCounter=(TextView)findViewById(R.id.lbl_text);
        btnStart=(Button)findViewById(R.id.btn_start);
        btnStop=(Button)findViewById(R.id.btn_stop);
        btnHold=(Button)findViewById(R.id.btn_hold);
        btnStop.setOnClickListener(this);
        btnStart.setOnClickListener(this);
        btnHold.setOnClickListener(this);
    }
    public void onClick(View v)
    {
        if(v.equals(btnStart))   // enable the stopwatch
        {
            running=true;
            new MyCounter().start();
        }
        else if (v.equals(btnHold)){  // pause the stopwatch
            if(running){
                running = false;
            }
        }
        else if(v.equals(btnStop))  // stop the watch and will set the counter as 0
        {
            running=false;
            counter = 0;
        }
    }
    Handler handler=new Handler()
    {
        public void handleMessage(Message m)
        {
            lblCounter.setText(String.valueOf(m.what));
        }
    };
    class MyCounter extends Thread
    {
        public void run()
        {
            while(running)
            {
                counter++;
                handler.sendEmptyMessage(counter);
                try {
                    Thread.sleep(1000);
                }
                catch(Exception e)
                {
                }
            }
        }
    }
}
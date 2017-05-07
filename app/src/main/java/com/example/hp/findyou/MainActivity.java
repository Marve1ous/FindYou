package com.example.hp.findyou;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnClickListener{
    Mybutton incomingBtn;
    Mybutton outcomingBtn;
    Mybutton switchBtn;
    Mybutton messageBtn;
    EditText listenerET;
    //新创建activity自动执行
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        incomingBtn = (Mybutton) findViewById(R.id.incoming);
        switchBtn = (Mybutton) findViewById(R.id.myswitch);
        listenerET = (EditText)findViewById(R.id.listenerid);
        outcomingBtn = (Mybutton)findViewById(R.id.outcoming);
        messageBtn = (Mybutton)findViewById(R.id.message);
    }

    @Override
    protected void onResume() {
        super.onResume();
        incomingBtn.setOnClickListener(this);
        outcomingBtn.setOnClickListener(this);
        messageBtn.setOnClickListener(this);
        switchBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.incoming:
                if (MyConstants.isIncoming) {
                    incomingBtn.setText("来电监控");
                    incomingBtn.setBackgroundResource(R.drawable.close);
                    MyConstants.isIncoming = false;
                } else {
                    MyConstants.isIncoming = true;
                    incomingBtn.setText("来电监控 已选择");
                    incomingBtn.setBackgroundResource(R.drawable.open);
                }
                break;

            case R.id.outcoming:
                if (MyConstants.isOutcoming) {
                   outcomingBtn.setText("去电监控");
                    outcomingBtn.setBackgroundResource(R.drawable.close);
                    MyConstants.isOutcoming= false;
                } else {
                    MyConstants.isOutcoming = true;
                    outcomingBtn.setText("去电监控 已选择");
                    outcomingBtn.setBackgroundResource(R.drawable.open);
                }
                break;

            case R.id.message:
                if (MyConstants.isMessage) {
                    messageBtn.setText("短信监控");
                    messageBtn.setBackgroundResource(R.drawable.close);
                    MyConstants.isMessage= false;
                } else {
                    MyConstants.isMessage = true;
                    messageBtn.setText("短信监控 已选择");
                    messageBtn.setBackgroundResource(R.drawable.open);
                }
                break;

            case R.id.myswitch:
                MyConstants.listenerNumber = listenerET.getText().toString();
                if (MyConstants.listenerNumber.equals("")) {
                    Toast.makeText(MainActivity.this, "请输入正确的号码", Toast.LENGTH_SHORT).show();
                } else {
                    if (MyConstants.isOpen) {
                        MyConstants.isOpen = false;

                        incomingBtn.setEnabled(true);
                        outcomingBtn.setEnabled(true);
                        messageBtn.setEnabled(true);

                        switchBtn.setText("开启监控");
                        Intent intent = new Intent(MainActivity.this, MyService.class);
                        stopService(intent);
                    } else {
                        MyConstants.isOpen = true;

                        incomingBtn.setEnabled(false);
                        outcomingBtn.setEnabled(false);
                        messageBtn.setEnabled(false);

                        switchBtn.setText("关闭监控");
                        Intent intent = new Intent(MainActivity.this, MyService.class);
                        startService(intent);
                    }
                }
                break;

            default:
                break;
        }
    }
}

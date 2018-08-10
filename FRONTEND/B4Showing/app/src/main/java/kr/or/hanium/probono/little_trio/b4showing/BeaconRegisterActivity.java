package kr.or.hanium.probono.little_trio.b4showing;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.or.hanium.probono.little_trio.b4showing.StationFind.StationFindActivity;

public class BeaconRegisterActivity extends AppCompatActivity {
    @BindView(R.id.beaconregister_button_ok)
    Button buttonOk;
    @BindView(R.id.beaconregister_edittext_BeaconNum)
    EditText editTextBeaconNum;
    @BindView(R.id.beaconregister_button_test)
    Button buttonTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beacon_register);
        ButterKnife.bind(this);
        clickEvent();
    }

    void clickEvent() {
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Number = editTextBeaconNum.getText().toString();
                if(Number.equals("")){
                    Toast.makeText(getApplicationContext(), "번호를 입력해 주세요", Toast.LENGTH_SHORT).show();
                }else{

                }

                Intent intent = new Intent(getApplicationContext(), StationFindActivity.class);
                startActivity(intent);
                finish();
            }
        });
        buttonTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TestActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

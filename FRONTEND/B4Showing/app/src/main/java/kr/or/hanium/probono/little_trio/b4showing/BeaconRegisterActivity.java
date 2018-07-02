package kr.or.hanium.probono.little_trio.b4showing;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BeaconRegisterActivity extends AppCompatActivity {
    @BindView(R.id.beaconregister_button_ok)
    Button buttonOk;
    @BindView(R.id.beaconregister_edittext_BeaconNum)
    EditText editTextBeaconNum;

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
                Intent intent = new Intent(BeaconRegisterActivity.this, StationFindActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

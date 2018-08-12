package kr.or.hanium.probono.little_trio.b4showing;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.or.hanium.probono.little_trio.b4showing.StationFind.StationFindActivity;
import kr.or.hanium.probono.little_trio.b4showing.model.response.BaseResult;
import kr.or.hanium.probono.little_trio.b4showing.network.ApplicationController;
import kr.or.hanium.probono.little_trio.b4showing.network.NetworkService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BeaconRegisterActivity extends AppCompatActivity {
    @BindView(R.id.beaconregister_button_ok)
    Button buttonOk;
    @BindView(R.id.beaconregister_edittext_BeaconNum)
    EditText editTextBeaconNum;
    @BindView(R.id.beaconregister_button_test)
    Button buttonTest;
    private NetworkService service;
    private final String TAG = "RegisterActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beacon_register);
        ButterKnife.bind(this);

        service = ApplicationController.getInstance().getNetworkService();
        clickEvent();
    }

    void clickEvent() {
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Number = editTextBeaconNum.getText().toString();
                if (Number.equals("")) {
                    Toast.makeText(getApplicationContext(), "번호를 입력해 주세요", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getApplicationContext(), StationFindActivity.class);
                    startActivity(intent);
                    finish();

//                    Call<BaseResult> getNfcRegisterResult = service.getNfcRegisterResult(Number);
//                    Log.d(TAG, Number);
//                    Log.d(TAG, "Call");
//                    getNfcRegisterResult.enqueue(new Callback<BaseResult>() {
//
//                        @Override
//                        public void onResponse(Call<BaseResult> call, Response<BaseResult> response) {
//                            Log.d(TAG, "fail");
//                            if (response.isSuccessful()) {
//                                Log.d(TAG, "Success");
//                                int code = response.code();
//                                switch (code) {
//                                    case 200:
//                                        Toast.makeText(getApplicationContext(), "정상적으로 등록되었습니다.", Toast.LENGTH_SHORT).show();
//                                        Intent intent = new Intent(getApplicationContext(), StationFindActivity.class);
//                                        startActivity(intent);
//                                        finish();
//                                    case 400:
//                                        Toast.makeText(getApplicationContext(), "번호를 확인해 주세요.", Toast.LENGTH_SHORT).show();
//                                }
//
//                            } else {
//                                Log.d(TAG, "실패");
//                            }
//
//                        }
//
//
//                        @Override
//                        public void onFailure(Call<BaseResult> call, Throwable t) {
//                            System.out.println("zz");
//                        }
//                    });
//
                }
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

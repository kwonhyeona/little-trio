package kr.or.hanium.probono.little_trio.b4showing;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.or.hanium.probono.little_trio.b4showing.StationFind.StationFindActivity;
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
    private NetworkService service;
    private final String TAG = "RegisterActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beacon_register);
        ButterKnife.bind(this);
        service = ApplicationController.getInstance().getNetworkService();

        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        String check = pref.getString("checkNumber", "NO");
        if (check.equals("OK")) {
            Intent intent = new Intent(getApplicationContext(), StationFindActivity.class);
            startActivity(intent);
            finish();
        }
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
                    Call<Void> getNfcRegisterResult = service.getNfcRegisterResult(Number);
                    getNfcRegisterResult.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            Log.d(TAG, "fail");
                            Log.d(TAG, "Success");
                            int code = response.code();
                            switch (code) {
                                case 200:
                                    Toast.makeText(getApplicationContext(), "정상적으로 등록되었습니다.", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), StationFindActivity.class);
                                    startActivity(intent);
                                    finish();
                                    SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = pref.edit();
                                    editor.putString("checkNumber", "OK");
                                    editor.commit();
                                case 400:
                                    Toast.makeText(getApplicationContext(), "번호를 확인해 주세요.", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            System.out.println("내부 통신 오류");
                        }
                    });
                }
            }
        });
    }
}

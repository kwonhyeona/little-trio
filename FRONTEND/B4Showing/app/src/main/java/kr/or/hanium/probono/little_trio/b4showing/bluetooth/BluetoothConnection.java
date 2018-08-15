package kr.or.hanium.probono.little_trio.b4showing.bluetooth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.or.hanium.probono.little_trio.b4showing.R;
import kr.or.hanium.probono.little_trio.b4showing.StationFind.StationFindActivity;

public class BluetoothConnection extends AppCompatActivity {

    @BindView(R.id.btn_connection)
    Button btConntecion;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.txt_result)
    TextView txtResult;

    private BluetoothService btService = null;

    private static final String  TAG = "BluetoothConnection";

    //Intent request code
    private static final int REQUEST_CONNECT_DEVICE = 1;
    private static final int REQUEST_ENABLE_BT = 2;

    private final Handler btHandler = new Handler() {
        public void handleMessage(Message msg){
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth_connection);

        ButterKnife.bind(this);

        //BluetoothService 클래스 생성
        if(btService == null){
            btService = new BluetoothService(this,btHandler);
        }
        clickEvent();
    }

    void clickEvent(){
        btConntecion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btService.getDeviceState()){
                    btService.enableBluetooth();
                } else {
                    finish();
                }
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), StationFindActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult " + resultCode);
        switch (requestCode) {
            case REQUEST_CONNECT_DEVICE:
                //device 목록 뜰 때
                if(resultCode == Activity.RESULT_OK){
                    btService.getDeviceInfo(data);
                }
            case REQUEST_ENABLE_BT:
                // When the request to enable Bluetooth returns
                if (resultCode == Activity.RESULT_OK) {
                    btService.scanDevice();
                } else {
                    Log.d(TAG, "Bluetooth is not enabled");
                }
                break;
        }
    }
}

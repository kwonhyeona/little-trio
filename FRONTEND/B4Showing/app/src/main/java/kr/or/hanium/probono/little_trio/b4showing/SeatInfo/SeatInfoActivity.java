package kr.or.hanium.probono.little_trio.b4showing.SeatInfo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.or.hanium.probono.little_trio.b4showing.R;
import kr.or.hanium.probono.little_trio.b4showing.StationFind.StationFindActivity;
import kr.or.hanium.probono.little_trio.b4showing.model.response.SeatInfoResult;
import kr.or.hanium.probono.little_trio.b4showing.model.response.room;
import kr.or.hanium.probono.little_trio.b4showing.network.ApplicationController;
import kr.or.hanium.probono.little_trio.b4showing.network.NetworkService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeatInfoActivity extends AppCompatActivity {

    @BindView(R.id.seatinfo_seekBar_room_number)
    SeekBar seekBarRoomNum;
    @BindView(R.id.seatinfo_textview_number)
    TextView textViewNumber;
    @BindView(R.id.seatinfo_first_button)
    Button buttonFirst;
    @BindView(R.id.seatinfo_second_button)
    Button buttonSecond;
    @BindView(R.id.seatinfo_third_button)
    Button buttonthird;
    @BindView(R.id.seatinfo_fourth_button)
    Button buttonFourth;
    @BindView(R.id.seatinfo_refresh_button)
    Button buttonRefresh;

    private NetworkService service;
    ProgressDialog progress;
    private ArrayList<room> seatinfo = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_info);
        ButterKnife.bind(this);
        service = ApplicationController.getInstance().getNetworkService();
        progress = new ProgressDialog(SeatInfoActivity.this);
        getSeatInfo();
        clieckEvent();

    }

    private void getSeatInfo() {
        String trainNumber = "1234";
        Call<SeatInfoResult> getSeatInfoResult = service.getSeatInfoResult(trainNumber);

        getSeatInfoResult.enqueue(new Callback<SeatInfoResult>() {
            @Override
            public void onResponse(Call<SeatInfoResult> call, Response<SeatInfoResult> response) {
                int code = response.code();

                switch (code) {
                    case 200:
                        seatinfo = response.body().getRooms();
                        setSeatInfo(0);
                        seekBarEvent();
                        if (progress.isShowing()) {
                            progress.dismiss();
                        }

                }
            }

            @Override
            public void onFailure(Call<SeatInfoResult> call, Throwable t) {

            }
        });
    }

    private void setSeatInfo(int index) {
        String info = seatinfo.get(index).getSeatInfo();
        if (info.charAt(0) == '0') {
            buttonFirst.setBackgroundColor(Color.GREEN);
        } else {
            buttonFirst.setBackgroundColor(Color.RED);
        }

        if (info.charAt(2) == '0') {
            buttonSecond.setBackgroundColor(Color.GREEN);
        } else {
            buttonSecond.setBackgroundColor(Color.RED);
        }

        if (info.charAt(2) == '0') {
            buttonthird.setBackgroundColor(Color.GREEN);
        } else {
            buttonthird.setBackgroundColor(Color.RED);
        }

        if (info.charAt(3) == '0') {
            buttonFourth.setBackgroundColor(Color.GREEN);
        } else {
            buttonFourth.setBackgroundColor(Color.RED);
        }
    }

    public void seekBarEvent() {
        seekBarRoomNum.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStopTrackingTouch(SeekBar seekBar) {
                //   tv.setText("onStop TrackingTouch");
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                //  tv.setText("onStart TrackingTouch");
            }

            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                textViewNumber.setText(String.valueOf(progress + 1));
                setSeatInfo(progress);
            }

        });

    }

    public void clieckEvent() {
        buttonRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                progress.setTitle("새로고침");
                progress.setMessage("좌석 정보를 새로고침 중 입니다.");
                progress.setProgressStyle((ProgressDialog.STYLE_SPINNER));
                progress.setCancelable(false);
                progress.show();
                getSeatInfo();

            }
        });
    }
}

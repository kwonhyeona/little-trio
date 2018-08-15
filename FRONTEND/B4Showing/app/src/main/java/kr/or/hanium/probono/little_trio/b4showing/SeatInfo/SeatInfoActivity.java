package kr.or.hanium.probono.little_trio.b4showing.SeatInfo;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.or.hanium.probono.little_trio.b4showing.R;
import kr.or.hanium.probono.little_trio.b4showing.model.response.SeatInfoResult;
import kr.or.hanium.probono.little_trio.b4showing.network.ApplicationController;
import kr.or.hanium.probono.little_trio.b4showing.network.NetworkService;
import retrofit2.Call;

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

    private NetworkService service;
    private ArrayList<SeatInfoResult> seatinfo = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_info);
        ButterKnife.bind(this);
        service = ApplicationController.getInstance().getNetworkService();

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
            }
        });
    }

    private void getSeatInfo() {
        String trainNumber = null;
        Call<SeatInfoResult> getSeatInfoResult = service.getSeatInfoResult(trainNumber);
    }

    private void setSeatInfo(int index) {
        String[] info;
        info = seatinfo.get(index).getSeatinfo().get(index);

        if (info[0].equals("0")) {
            buttonFirst.setBackgroundColor(Color.GREEN);
        } else {
            buttonFirst.setBackgroundColor(Color.RED);
        }

        if (info[1].equals("0")) {
            buttonSecond.setBackgroundColor(Color.GREEN);
        } else {
            buttonSecond.setBackgroundColor(Color.RED);
        }

        if (info[2].equals("0")) {
            buttonthird.setBackgroundColor(Color.GREEN);
        } else {
            buttonthird.setBackgroundColor(Color.RED);
        }

        if (info[3].equals("0")) {
            buttonFourth.setBackgroundColor(Color.GREEN);
        } else {
            buttonFourth.setBackgroundColor(Color.RED);
        }
    }
}

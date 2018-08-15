package kr.or.hanium.probono.little_trio.b4showing.SeatInfo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.or.hanium.probono.little_trio.b4showing.R;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_info);
        ButterKnife.bind(this);
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

}

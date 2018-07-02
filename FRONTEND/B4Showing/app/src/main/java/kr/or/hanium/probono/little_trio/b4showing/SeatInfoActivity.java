package kr.or.hanium.probono.little_trio.b4showing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SeatInfoActivity extends AppCompatActivity {

    @BindView(R.id.seatinfo_seekBar_room_number)
    SeekBar seekBarRoomNum;
    @BindView(R.id.seatinfo_textview_number)
    TextView textViewNumber;

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
                textViewNumber.setText(String.valueOf(progress));
            }
        });


    }
}

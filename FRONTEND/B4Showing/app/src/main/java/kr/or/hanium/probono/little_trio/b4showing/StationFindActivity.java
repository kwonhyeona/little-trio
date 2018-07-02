package kr.or.hanium.probono.little_trio.b4showing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.melnykov.fab.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StationFindActivity extends AppCompatActivity {

    @BindView(R.id.stationfind_fab_find)
    FloatingActionButton buttonFind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_find);
        ButterKnife.bind(this);
        clickEvent();
    }

    void clickEvent() {
        buttonFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SeatInfoActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

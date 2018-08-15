package kr.or.hanium.probono.little_trio.b4showing.model.response;

import java.util.ArrayList;

/**
 * Created by LG on 2018-08-13.
 */

public class SeatInfoResult {

    private ArrayList<String[]> seatinfo;

    public ArrayList<String[]> getSeatinfo() {
        return seatinfo;
    }

    public void setSeatinfo(ArrayList<String[]> seatinfo) {
        this.seatinfo = seatinfo;
    }

    public SeatInfoResult(ArrayList<String[]> seatinfo) {

        this.seatinfo = seatinfo;
    }
}

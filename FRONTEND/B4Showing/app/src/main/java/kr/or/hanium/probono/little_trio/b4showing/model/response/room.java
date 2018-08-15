package kr.or.hanium.probono.little_trio.b4showing.model.response;

/**
 * Created by LG on 2018-08-15.
 */

public class room {
    private long id;
    private int number;
    private String seatInfo;

    public room(long id, int number, String seatInfo) {
        this.id = id;
        this.number = number;
        this.seatInfo = seatInfo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getSeatInfo() {
        return seatInfo;
    }

    public void setSeatInfo(String seatInfo) {
        this.seatInfo = seatInfo;
    }
}

package kr.or.hanium.probono.little_trio.b4showing.model.response;

import java.util.ArrayList;

/**
 * Created by LG on 2018-08-13.
 */

public class SeatInfoResult {

    private long id;
    private String trainNumber;
    private ArrayList<room> rooms;

    public SeatInfoResult(long id, String trainNumber, ArrayList<room> rooms) {
        this.id = id;
        this.trainNumber = trainNumber;
        this.rooms = rooms;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public ArrayList<room> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<room> rooms) {
        this.rooms = rooms;
    }
}

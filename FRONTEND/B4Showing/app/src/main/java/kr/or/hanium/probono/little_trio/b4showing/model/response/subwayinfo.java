package kr.or.hanium.probono.little_trio.b4showing.model.response;

/**
 * Created by LG on 2018-08-15.
 */

public class subwayinfo {
    //지하철 호선 ID
    private String subwayLineName;
    //도착시 방면
    private String destination;

    //상행선 하행선 구분
    private String direction;
    //도착 예정 열차 순번
    private String orderKey;
    //도착 예성 시간
    private String barvlDt;
    //열차번호
    private String tranNumber;

    public subwayinfo(String subwayLineName, String destination, String direction, String orderKey, String barvlDt, String tranNumber) {
        this.subwayLineName = subwayLineName;
        this.destination = destination;
        this.direction = direction;
        this.orderKey = orderKey;
        this.barvlDt = barvlDt;
        this.tranNumber = tranNumber;
    }

    public String getSubwayLineName() {
        return subwayLineName;
    }

    public void setSubwayLineName(String subwayLineName) {
        this.subwayLineName = subwayLineName;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getOrderKey() {
        return orderKey;
    }

    public void setOrderKey(String orderKey) {
        this.orderKey = orderKey;
    }

    public String getBarvlDt() {
        return barvlDt;
    }

    public void setBarvlDt(String barvlDt) {
        this.barvlDt = barvlDt;
    }

    public String getTranNumber() {
        return tranNumber;
    }

    public void setTranNumber(String tranNumber) {
        this.tranNumber = tranNumber;
    }
}

package kr.or.hanium.probono.little_trio.b4showing.model.response;

import java.util.ArrayList;

/**
 * Created by LG on 2018-08-15.
 */

public class subwayinfoResult {
    private ArrayList<subwayinfo> subwayinfos;

    public subwayinfoResult(ArrayList<subwayinfo> subwayinfos) {
        this.subwayinfos = subwayinfos;
    }

    public ArrayList<subwayinfo> getSubwayinfos() {
        return subwayinfos;
    }

    public void setSubwayinfos(ArrayList<subwayinfo> subwayinfos) {
        this.subwayinfos = subwayinfos;
    }
}

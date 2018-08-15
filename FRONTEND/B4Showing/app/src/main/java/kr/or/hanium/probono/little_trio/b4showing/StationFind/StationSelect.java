package kr.or.hanium.probono.little_trio.b4showing.StationFind;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.LoginFilter;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import kr.or.hanium.probono.little_trio.b4showing.R;
import kr.or.hanium.probono.little_trio.b4showing.model.response.subwayinfo;


/**
 * Created by LG on 2018-08-12.
 */

public class StationSelect extends Fragment {
    LinearLayout linearLayout;
    String Station = "";

    public StationSelect() {
        super();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.station_find_fragment_layout, null);
        linearLayout = (LinearLayout) view.findViewById(R.id.station_find_linearLayout);
        Station = getArguments().getString("station");
        getApi();
        return view;
    }

    private void getApi() {
        new AsyncTask<Void, Void, String>() {
            ProgressDialog progress;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progress = new ProgressDialog(getContext());
                progress.setTitle("다운로드");
                progress.setMessage("열차 정보 조회를 요청 중 입니다.");
                progress.setProgressStyle((ProgressDialog.STYLE_SPINNER));
                progress.setCancelable(false);
                progress.show();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try {
                    JSONObject json = new JSONObject(s);

                    JSONArray rows = json.getJSONArray("realtimeArrivalList");
                    JSONObject errMessage = json.getJSONObject("errorMessage");
                    String code = errMessage.getString("code");

                    Log.d("코드는", code);
                    if (!code.equals("INFO-000")) {
                        Toast.makeText(getActivity().getApplicationContext(), "서울시 서버에서 데이터 조회를 실패했습니다.", Toast.LENGTH_LONG).show();
                    } else {
                        ArrayList<subwayinfo> subwayinfos = new ArrayList<>();
                        int length = rows.length();

                        //linear Layout params 정의
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                        for (int i = 0; i < length; i++) {
                            JSONObject result = (JSONObject) rows.get(i);
                            //LiearLayout 생성
                            LinearLayout linearLayout1 = new LinearLayout(getContext());
                            LinearLayout linearLayout2 = new LinearLayout(getContext());
                            linearLayout2.setOrientation(LinearLayout.HORIZONTAL);
                            linearLayout1.setOrientation(LinearLayout.VERTICAL);
                            linearLayout1.setGravity(Gravity.CENTER);

                            TextView updnLine = new TextView(getContext());
                            String updn = result.getString("updnLine");
                            String trainLineNm = result.getString("trainLineNm");
                            String subwayId = result.getString("subwayId");
                            String subwayname = "";

                            switch (subwayId) {
                                case "1001":
                                    subwayname = "1호선";
                                    updnLine.setTextColor(Color.BLUE);
                                    break;
                                case "1002":
                                    subwayname = "2호선";
                                    updnLine.setTextColor(Color.rgb(29, 219, 22));
                                    break;
                                case "1003":
                                    subwayname = "3호선";
                                    updnLine.setTextColor(Color.rgb(255, 94, 0));
                                    break;
                                case "1004":
                                    subwayname = "4호선";
                                    updnLine.setTextColor(Color.rgb(0, 216, 255));
                                    break;
                                case "1005":
                                    subwayname = "5호선";
                                    updnLine.setTextColor(Color.rgb(128, 65, 217));
                                    break;
                                case "1006":
                                    subwayname = "6호선";
                                    updnLine.setTextColor(Color.rgb(135, 64, 0));
                                    break;
                                case "1007":
                                    subwayname = "7호선";
                                    updnLine.setTextColor(Color.rgb(0, 103, 0));
                                    break;
                                case "1008":
                                    subwayname = "8호선";
                                    updnLine.setTextColor(Color.rgb(217, 65, 197));

                                    break;
                                case "1009":
                                    subwayname = "9호선";
                                    updnLine.setTextColor(Color.rgb(102, 75, 0));
                                    break;
                                case "1063":
                                    subwayname = "경의중앙선";
                                    break;
                                case "1075":
                                    subwayname = "분당선";
                                    break;
                                case "1071":
                                    subwayname = "수인선";
                                    break;
                                case "1067":
                                    subwayname = "경춘선";
                                    break;

                            }
                            updnLine.setText(subwayname + " " + trainLineNm + "(" + updn + ")");
                            linearLayout1.addView(updnLine);

                            //textView 생성
                            TextView arviMasg2 = new TextView(getContext());
                            String arriveTime = result.getString("arvlMsg2");
                            arviMasg2.setText("도착 예정: " + arriveTime);
                            linearLayout1.addView(arviMasg2);
                            linearLayout2.addView(linearLayout1);
                            String orderkey = result.getString("ordkey");
                            //Button 생성
                            final Button btn = new Button(getContext());
                            String btrainNo = result.getString("btrainNo");

                            btn.setId(Integer.parseInt(btrainNo));
                            btn.setText("선택");
                            btn.setLayoutParams(params);
                            final String position = btrainNo;
                            btn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Log.d("log", "position :" + position);

                                    Toast.makeText(getContext(), "클릭한 position:" + position, Toast.LENGTH_LONG).show();


                                }
                            });
                            linearLayout2.addView(btn);
                            linearLayout.addView(linearLayout2);
                            subwayinfo info = new subwayinfo(subwayname, trainLineNm, updn, orderkey, arriveTime, btrainNo);
                            subwayinfos.add(info);
                        }
                    }

                } catch (Exception e) {
                }

                progress.dismiss();

            }

            @Override
            protected String doInBackground(Void... params) {
                String result = "";
                try {

                    String Key = getString(R.string.subwayKey);
                    result = Remote.getData("http://swopenapi.seoul.go.kr/api/subway/" + Key + "/json/realtimeStationArrival/0/30/" + Station);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return result;
            }
        }.execute();
    }
}

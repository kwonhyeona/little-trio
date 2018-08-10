package kr.or.hanium.probono.little_trio.b4showing.StationFind;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.melnykov.fab.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.or.hanium.probono.little_trio.b4showing.R;
import kr.or.hanium.probono.little_trio.b4showing.SeatInfo.SeatInfoActivity;

@SuppressLint({"SetJavaScriptEnabled", "JavascriptInterface"})
public class StationFindActivity extends AppCompatActivity {
    private final String TAG = "StationFindActivity";
    private String Station = "";


    @BindView(R.id.stationfind_fab_find)
    FloatingActionButton buttonFind;
    @BindView(R.id.stationfind_webView_webView)
    WebView webView;
    @BindView(R.id.stationfind_button_name)
    Button nameButton;
    @BindView(R.id.stationfind_edittext_name)
    EditText nemaEditText;
    //    @BindView(R.id.find)
//    TextView find;
    @BindView(R.id.stationfind_linearLayout_linearLayout)
    LinearLayout linearLayout;

    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_find);
        ButterKnife.bind(this);
        clickEvent();
        setWebView();
        nameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Station = nemaEditText.getText().toString();
                if (Station.equals("")) {
                    Toast.makeText(getApplicationContext(), "역 이름을 입력해 주세요", Toast.LENGTH_SHORT).show();
                } else
                    getApi();
            }
        });

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

    void setWebView() {
        //배경색 설정
        webView.setBackgroundColor(0);
        //스크롤 설정
        webView.setHorizontalScrollBarEnabled(true);
        webView.setVerticalScrollBarEnabled(true);
        //HTML을 파싱하여 웹뷰에서 보여주거나 하는 작업에서
        //width , height 가 화면 크기와 맞지 않는 현상이 발생한다
        //이를 잡아주기 위한 코드
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        //캐시파일 사용 금지(운영중엔 주석처리 할 것)
        //webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);

        //zoom 허용
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setSupportZoom(true);

        //javascript의 window.open 허용
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        //javascript 허용
        webView.getSettings().setJavaScriptEnabled(true);

        webView.addJavascriptInterface(new AndroidBridge(), "android");


        //meta태그의 viewport사용 가능
        webView.getSettings().setUseWideViewPort(true);
        webView.loadUrl("file:///android_asset/little-trio.html");
        webView.setWebViewClient(new WishWebViewClient());

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private class WishWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    private class AndroidBridge {
        @JavascriptInterface
        public void setMessage(final String arg) {
            handler.post(new Runnable() {

                public void run() {
                    Toast.makeText(getApplicationContext(), " 클릭한 역은 : \n" + arg, Toast.LENGTH_SHORT).show();
                }

            });

        }

    }


    private void getApi() {

        new AsyncTask<Void, Void, String>() {
            ProgressDialog progress;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progress = new ProgressDialog(StationFindActivity.this);
                progress.setTitle("다운로드");
                progress.setMessage("download");
                progress.setProgressStyle((ProgressDialog.STYLE_SPINNER));
                progress.setCancelable(false);
                progress.show();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                StringBuffer sb = new StringBuffer();
                try {
                    JSONObject json = new JSONObject(s);

                    JSONArray rows = json.getJSONArray("realtimeArrivalList");

                    int length = rows.length();
                    //linear Layout params 정의
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                    for (int i = 0; i < length; i++) {
                        JSONObject result = (JSONObject) rows.get(i);
                        //LiearLayout 생성
                        LinearLayout linearLayout1 = new LinearLayout(getBaseContext());
                        linearLayout1.setOrientation(LinearLayout.HORIZONTAL);

                        //textView 생성
                        TextView updnLine = new TextView(getBaseContext());
                        String updn = result.getString("updnLine");
                        String trainLineNm = result.getString("trainLineNm");

                        updnLine.setText("방향 : " + trainLineNm + "(" + updn + ")");
                        linearLayout1.addView(updnLine);

                        //textView 생성
                        TextView arviMasg2 = new TextView(getBaseContext());
                        String trainName2 = result.getString("arvlMsg2");
                        arviMasg2.setText(" 도착 예정 시간: " + trainName2);
                        linearLayout1.addView(arviMasg2);

                        //Button 생성
                        final Button btn = new Button(getBaseContext());
                        String btrainNo = result.getString("btrainNo");

                        btn.setId(Integer.parseInt(btrainNo));
                        btn.setText("선택");
                        btn.setLayoutParams(params);
                        final String position = btrainNo;
                        btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Log.d("log", "position :" + position);

                                Toast.makeText(getApplicationContext(), "클릭한 position:" + position, Toast.LENGTH_LONG).show();


                            }
                        });

                        linearLayout1.addView(btn);

                        linearLayout.addView(linearLayout1);


                        //    sb.append(trainName + "\n");

                    }

                } catch (Exception e) {
                }
                //    find.setText(sb.toString());

                progress.dismiss();
            }

            @Override
            protected String doInBackground(Void... params) {
                String result = "";
                try {
                    //서울시 오픈 API 제공(샘플 주소 json으로 작업)
                    result = Remote.getData("http://swopenapi.seoul.go.kr/api/subway/sample/json/realtimeStationArrival/0/5/" + Station);

                } catch (Exception e) {
                    e.printStackTrace();
                }

                return result;
            }
        }.execute();
    }


}

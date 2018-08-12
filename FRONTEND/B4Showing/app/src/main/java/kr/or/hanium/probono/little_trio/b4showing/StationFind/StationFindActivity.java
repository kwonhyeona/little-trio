package kr.or.hanium.probono.little_trio.b4showing.StationFind;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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
                } else {
                    webView.setVisibility(View.GONE);

                    StationSelect myFragment = new StationSelect();
                    Bundle bundle = new Bundle();
                    bundle.putString("station", Station);
                    myFragment.setArguments(bundle);
                    android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                    android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.stationfind_linearLayout_linearLayout, myFragment);
                    fragmentTransaction.commit();
                }
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


        webView.invalidate();
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
                    nemaEditText.setText(arg);
                    Toast.makeText(getApplicationContext(), " 클릭한 역은 : \n" + arg, Toast.LENGTH_SHORT).show();
                }

            });

        }
    }

}

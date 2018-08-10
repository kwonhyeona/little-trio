package kr.or.hanium.probono.little_trio.b4showing;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

@SuppressLint({ "SetJavaScriptEnabled", "JavascriptInterface" })
public class TestActivity extends Activity {
    WebView mWebView;
    TextView mTextView;
    EditText mEditText;
    Button mButton;

    private final Handler handler = new Handler();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mWebView = (WebView) findViewById(R.id.webview);
        mTextView = (TextView) findViewById(R.id.textview);
        mButton = (Button) findViewById(R.id.button);
        mEditText = (EditText) findViewById(R.id.edittext);

        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl("file:///android_asset/javapage.html");
        mWebView.addJavascriptInterface(new AndroidBridge(), "android");

        mButton.setOnClickListener( new OnClickListener(){
            public void onClick(View view) {
                mWebView.loadUrl("javascript:setMessage('" + mEditText.getText().toString() + "')");
            }
        });
    }

    private class AndroidBridge {
        @JavascriptInterface
        public void setMessage(final String arg) {
            handler.post(new Runnable() {
                public void run() {
                    mTextView.setText("받은 메시지 : \n" + arg);
                }
            });
        }
    }
}
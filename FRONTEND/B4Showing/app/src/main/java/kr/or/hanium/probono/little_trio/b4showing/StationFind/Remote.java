package kr.or.hanium.probono.little_trio.b4showing.StationFind;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by LG on 2018-08-04.
 */

public class Remote {
    private static final String TAG = "ResponseCode : ";

    // http 연결은 static 권장
    public static String getData(String webURL) throws Exception {
        StringBuilder result = new StringBuilder();
        String dataLine;
        URL url = new URL(webURL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        // REST API = GET(조회), POST(입력), DELETE(삭제), PUT(수정)
        conn.setRequestMethod("GET");
        int responseCode = conn.getResponseCode();
        Log.i("실행" , " re " +responseCode);
        // 200
        if (responseCode == HttpsURLConnection.HTTP_OK) {
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            while ((dataLine = br.readLine()) != null) {
                result.append(dataLine);
            }
            br.close();
        } else {
            Log.i(TAG, "" + responseCode);
        }


        return result.toString();
    }

}

package kr.or.hanium.probono.little_trio.b4showing.network;

import kr.or.hanium.probono.little_trio.b4showing.model.response.BaseResult;
import kr.or.hanium.probono.little_trio.b4showing.model.response.SeatInfoResult;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by LG on 2018-07-06.
 */

public interface NetworkService {
    @POST("nfcs/register")
    Call<Void> getNfcRegisterResult(@Body String deviceNumber);
    @GET("subways/{trainNumber}")
    Call<SeatInfoResult> getSeatInfoResult(@Path("trainNumber") String trainNumber );
}

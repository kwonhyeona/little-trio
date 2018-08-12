package kr.or.hanium.probono.little_trio.b4showing.network;

import kr.or.hanium.probono.little_trio.b4showing.model.response.BaseResult;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by LG on 2018-07-06.
 */

public interface NetworkService {
    @POST("/api/nfcs/register")
    Call<BaseResult> getNfcRegisterResult(@Body String deviceNumber);
    
}

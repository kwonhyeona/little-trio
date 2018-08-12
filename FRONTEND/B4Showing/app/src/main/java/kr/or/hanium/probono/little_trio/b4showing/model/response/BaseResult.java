package kr.or.hanium.probono.little_trio.b4showing.model.response;

/**
 * Created by LG on 2018-08-10.
 */

public class BaseResult {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BaseResult(String message) {

        this.message = message;
    }
}

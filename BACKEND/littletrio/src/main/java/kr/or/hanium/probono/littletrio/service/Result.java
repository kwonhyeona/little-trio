package kr.or.hanium.probono.littletrio.service;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class Result<T> {
    private MultiValueMap<String, Object> params;

    private Result() {
        this.params = new LinkedMultiValueMap<>();
    }

    public static Result of() {
        return new Result();
    }

    public Result setMessage(String message) {
        this.params.add("message", message);
        return this;
    }

    public MultiValueMap<String, Object> getParams() {
        return params;
    }

    public void setParams(MultiValueMap<String, Object> params) {
        this.params = params;
    }

    public Result addParameter(String key, Object value) {
        this.params.add(key, value);
        return this;
    }

    public Result ok() {
        this.params.add("message", "ok");
        return this;
    }
}
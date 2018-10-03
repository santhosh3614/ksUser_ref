package com.kstransfter.webservice;

public interface WsResponse<T> {

    public void successResponse(T response, int code);
    public void failureRespons(Throwable error, int code);


}

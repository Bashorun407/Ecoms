package com.akinnova.Ecoms.exception;

public class ErrorUtils {

    private ErrorUtils(){}
    /**
     * Creates and returns an error object
     * @param errMsgKey
     * @param errorCode
     * @param httpStatusCode
     * @param url
     * @param reqMethod
     * @return error
     */

    public static Error createError(final String errMsgKey, final String errorCode, final Integer httpStatusCode,
                                    final String url, final String reqMethod){

        Error error = new Error();
        error.setErrorMessage(errMsgKey);
        error.setErrorCode(errorCode);
        error.setStatusCode(httpStatusCode);
        error.setUrl(url);
        error.setReqMethod(reqMethod);


        return error;
    }
}

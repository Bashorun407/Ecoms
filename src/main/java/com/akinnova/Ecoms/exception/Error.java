package com.akinnova.Ecoms.exception;

import lombok.Data;

@Data
public class Error {
    private static final Long serialVersionUID = 1L;
    /**
     * App error code which is different from Http error code
     */
    private String errorCode;
    /**
     * Short human-readable summary of the problem
     */
    private String errorMessage;
    /**
     * Http Status code
     */
    private Integer statusCode;
    /**
     * Url of request that produced the error
     */
    private String url = "Not available";
    /**
     * Method of request that produced the error
     */
    private String reqMethod = "Not available";

}

package com.akinnova.Ecoms.exception;

import com.fasterxml.jackson.core.JsonParseException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;


@ControllerAdvice
public class RestApiErrorHandler {
    private static final Logger log = LoggerFactory.getLogger(RestApiErrorHandler.class);

    private final MessageSource messageSource;


    public RestApiErrorHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * Here, user-defined exceptions can be added. You can also make use of the Locale
     * instance (a method parameter) and the messageSource class member for supporting
     * internationalized messages
     */

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleException(HttpServletRequest request, Exception ex, Locale locale){

        Error error = ErrorUtils.createError(ErrorCode.GENERIC_ERROR.getErrMsgKey(),
                ErrorCode.GENERIC_ERROR.getErrCode(), HttpStatus.INTERNAL_SERVER_ERROR.value(),
                request.getRequestURL().toString(), request.getMethod());

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<Error> handleHttpMediaTypeNotSupportedException(HttpServletRequest request,
                                                                          HttpMediaTypeNotSupportedException ex, Locale locale) {

        Error error = ErrorUtils.createError(ErrorCode.HTTP_MEDIA_TYPE_NOT_SUPPORTED.getErrMsgKey(),
                ErrorCode.HTTP_MEDIA_TYPE_NOT_SUPPORTED.getErrCode(), HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(),
                request.getRequestURL().toString(), request.getMethod());

        log.info("HttpMediaTypeNotSupportedException :: request.getMethod(): " + request.getMethod());

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpMessageNotWritableException.class)
    public ResponseEntity<Error> handleHttpMessageNotWriteableException(HttpServletRequest request,
                                                                        HttpMessageNotWritableException ex, Locale locale){

        Error error = ErrorUtils.createError(ErrorCode.HTTP_MESSAGE_NOT_WRITABLE.getErrMsgKey(),
                ErrorCode.HTTP_MESSAGE_NOT_WRITABLE.getErrCode(), HttpStatus.NOT_IMPLEMENTED.value(),
                request.getRequestURL().toString(), request.getMethod());

        log.info("HttpMessageNotWriteableException :: request.getMethod(): " + request.getMethod());

        return new ResponseEntity<>(error, HttpStatus.METHOD_NOT_ALLOWED);
    }


    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public ResponseEntity<Error> handleHttpMediaTypeNotAcceptableException(HttpServletRequest request,
                                                                           HttpMediaTypeNotAcceptableException ex, Locale locale){

        Error error = ErrorUtils.createError(ErrorCode.HTTP_MEDIA_TYPE_NOT_ACCEPTABLE.getErrMsgKey(),
                ErrorCode.HTTP_MEDIA_TYPE_NOT_ACCEPTABLE.getErrCode(), HttpStatus.NOT_ACCEPTABLE.value(),
                request.getRequestURL().toString(), request.getMethod());

        log.info("HttpMediaTypeNotAcceptableException :: request.getMethod(): " + request.getMethod());

        return new ResponseEntity<>(error, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @ExceptionHandler(JsonParseException.class)
    public ResponseEntity<Error> handleJsonParseException(HttpServletRequest request,
                                                          JsonParseException ex, Locale locale){

        Error error = ErrorUtils.createError(ErrorCode.JSON_PARSE_ERROR.getErrMsgKey(),
                ErrorCode.JSON_PARSE_ERROR.getErrCode(), HttpStatus.CONFLICT.value(),
                request.getRequestURL().toString(), request.getMethod());

        log.info("JsonParseException :: request.getMethod(): " + request.getMethod());

        return new ResponseEntity<>(error, HttpStatus.CONFLICT);

    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Error> handleHttpMessageNotReadableException(HttpServletRequest request,
                                                                       HttpMessageNotReadableException ex, Locale locale){

        Error error = ErrorUtils.createError(ErrorCode.HTTP_MESSAGE_NOT_READABLE.getErrMsgKey(),
                ErrorCode.HTTP_MESSAGE_NOT_READABLE.getErrCode(), HttpStatus.NOT_IMPLEMENTED.value(),
                request.getRequestURL().toString(), request.getMethod());

        log.info("HttpMessageNotReadableException :: request.getMethod(): " + request.getMethod());

        return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
    }




//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<Error> handleException(HttpServletRequest request, Exception ex, Locale locale){
//
//        Error error = ErrorUtils.createError(ErrorCode.GENERIC_ERROR.getErrMsgKey(),
//                        ErrorCode.GENERIC_ERROR.getErrCode(), HttpStatus.INTERNAL_SERVER_ERROR.value()).
//                setUrl(request.getRequestURL().toString()).setReqMethod(request.getMethod());
//        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
//    public ResponseEntity<Error>
//    handleHttpMediaTypeNotSupportedException(
//            HttpServletRequest request, HttpMediaTypeNotSupportedException ex, Locale locale) {
//        Error error = ErrorUtils.createError(ErrorCode.HTTP_MEDIATYPE_NOT_SUPPORTED.
//                        getErrMsgKey(), ErrorCode.HTTP_MEDIATYPE_NOT_SUPPORTED.
//                        getErrCode(), HttpStatus.UNSUPPORTED_MEDIA_TYPE.value()).
//                setUrl(request.getRequestURL().toString()).setReqMethod(request.getMethod());
//        log.info("HttpMediaTypeNotSupportedException :: request. getMethod(): " + request.getMethod());
//
//        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}

package edu.nwu.anisc.hgmonitor.exception;

import cn.hutool.core.util.StrUtil;
import edu.nwu.anisc.hgmonitor.response.ResponseEnum;
import edu.nwu.anisc.hgmonitor.response.ServerResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zizhou
 * @version 1.0.0
 * @date 2024-10-18 11:25
 * @since JDK 17
 */
public class DefaultExceptionHandlerConfig {
    private static final Logger logger = LoggerFactory.getLogger(DefaultExceptionHandlerConfig.class);

    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    public ResponseEntity<ServerResponseEntity<List<String>>> methodArgumentNotValidExceptionHandler(Exception e) {
        logger.error("methodArgumentNotValidExceptionHandler", e);
        List<FieldError> fieldErrors = null;
        if (e instanceof MethodArgumentNotValidException) {
            fieldErrors = ((MethodArgumentNotValidException) e).getBindingResult().getFieldErrors();
        }
        if (e instanceof BindException) {
            fieldErrors = ((BindException) e).getBindingResult().getFieldErrors();
        }
        if (fieldErrors == null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ServerResponseEntity.fail(ResponseEnum.METHOD_ARGUMENT_NOT_VALID));
        }

        List<String> defaultMessages = new ArrayList<>(fieldErrors.size());
        for (FieldError fieldError : fieldErrors) {
            defaultMessages.add(fieldError.getField() + ":" + fieldError.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(ServerResponseEntity.fail(ResponseEnum.METHOD_ARGUMENT_NOT_VALID, defaultMessages));
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<ServerResponseEntity<List<FieldError>>> methodArgumentNotValidExceptionHandler(
            HttpMessageNotReadableException e) {
        logger.error("methodArgumentNotValidExceptionHandler", e);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ServerResponseEntity.fail(ResponseEnum.HTTP_MESSAGE_NOT_READABLE));
    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ServerResponseEntity<Object>> applicationExceptionHandler(ApplicationException e) {
        logger.error("vendora4cloudExceptionHandler", e);

        ResponseEnum responseEnum = e.getResponseEnum();
        // 失败返回失败消息 + 状态码
        if (responseEnum != null) {
            return ResponseEntity.status(HttpStatus.OK).body(ServerResponseEntity.fail(responseEnum, e.getObject()));
        }
        // 失败返回消息 状态码固定为直接显示消息的状态码
        return ResponseEntity.status(HttpStatus.OK).body(ServerResponseEntity.showFailMsg(e.getMessage()));
    }

}

package edu.nwu.anisc.hgmonitor.exception;

import edu.nwu.anisc.hgmonitor.response.ResponseEnum;

/**
 * @author zizhou
 * @version 1.0.0
 * @date 2024-10-18 10:19
 * @since JDK 17
 */
public class ApplicationException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    private Object object;

    private ResponseEnum responseEnum;

    public ApplicationException(String msg) {
        super(msg);
    }

    public ApplicationException(String msg, Object object) {
        super(msg);
        this.object = object;
    }

    public ApplicationException(String msg, Throwable cause) {
        super(msg, cause);
    }


    public ApplicationException(ResponseEnum responseEnum) {
        super(responseEnum.getMsg());
        this.responseEnum = responseEnum;
    }

    public ApplicationException(ResponseEnum responseEnum, Object object) {
        super(responseEnum.getMsg());
        this.responseEnum = responseEnum;
        this.object = object;
    }


    public Object getObject() {
        return object;
    }

    public ResponseEnum getResponseEnum() {
        return responseEnum;
    }
}

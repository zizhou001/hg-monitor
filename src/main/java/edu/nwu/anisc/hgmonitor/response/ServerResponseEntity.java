package edu.nwu.anisc.hgmonitor.response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author zizhou
 * @version 1.0.0
 * @date 2024-10-18 10:09
 * @since JDK 17
 */
public class ServerResponseEntity<T> implements Serializable {

    private static final Logger log = LoggerFactory.getLogger(ServerResponseEntity.class);

    /**
     * 状态码
     */
    private String code;

    /**
     * 信息
     */
    private String msg;

    /**
     * 数据
     */
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return Objects.equals(ResponseEnum.OK.value(), this.code);
    }

    @Override
    public String toString() {
        return "ServerResponseEntity{" + "code=" + code
                + ", msg='" + msg + '\''
                + ", data=" + data + '}';
    }

    public static <T> ServerResponseEntity<T> success(T data) {
        ServerResponseEntity<T> serverResponseEntity = new ServerResponseEntity<>();
        serverResponseEntity.setData(data);
        serverResponseEntity.setCode(ResponseEnum.OK.value());
        return serverResponseEntity;
    }

    public static <T> ServerResponseEntity<T> success() {
        ServerResponseEntity<T> serverResponseEntity = new ServerResponseEntity<>();
        serverResponseEntity.setCode(ResponseEnum.OK.value());
        serverResponseEntity.setMsg(ResponseEnum.OK.getMsg());
        return serverResponseEntity;
    }

    /**
     * 前端显示失败消息
     *
     * @param msg 失败消息
     * @return
     */
    public static <T> ServerResponseEntity<T> showFailMsg(String msg) {
        log.error(msg);
        ServerResponseEntity<T> serverResponseEntity = new ServerResponseEntity<>();
        serverResponseEntity.setMsg(msg);
        serverResponseEntity.setCode(ResponseEnum.SHOW_FAIL.value());
        return serverResponseEntity;
    }

    /**
     * 失败时，根据具体原因传入具体的返回枚举类型
     *
     * @param responseEnum
     * @param <T>
     * @return
     */
    public static <T> ServerResponseEntity<T> fail(ResponseEnum responseEnum) {
        log.error(responseEnum.toString());
        ServerResponseEntity<T> serverResponseEntity = new ServerResponseEntity<>();
        serverResponseEntity.setMsg(responseEnum.getMsg());
        serverResponseEntity.setCode(responseEnum.value());
        return serverResponseEntity;
    }

    /**
     * 失败时，传入错误类型和相应数据
     *
     * @param responseEnum
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ServerResponseEntity<T> fail(ResponseEnum responseEnum, T data) {
        log.error(responseEnum.toString());
        ServerResponseEntity<T> serverResponseEntity = new ServerResponseEntity<>();
        serverResponseEntity.setMsg(responseEnum.getMsg());
        serverResponseEntity.setCode(responseEnum.value());
        serverResponseEntity.setData(data);
        return serverResponseEntity;
    }

    /**
     * 将一个 ServerResponseEntity 转换为另一个 ServerResponseEntity 实例，
     * 保留原始的 msg 和 code 属性，但可以更改泛型类型 T。
     *
     * @param oldServerResponseEntity
     * @param <T>
     * @return
     */
    public static <T> ServerResponseEntity<T> transform(ServerResponseEntity<?> oldServerResponseEntity) {
        ServerResponseEntity<T> serverResponseEntity = new ServerResponseEntity<>();
        serverResponseEntity.setMsg(oldServerResponseEntity.getMsg());
        serverResponseEntity.setCode(oldServerResponseEntity.getCode());
        log.error(serverResponseEntity.toString());
        return serverResponseEntity;
    }

}

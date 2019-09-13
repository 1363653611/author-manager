package com.zbcn.authormanager.common.exception;

/**
 * @author zbcn8
 * @version 1.0.0
 * @ClassName RedisConnectException.java
 * @Description Redis 连接异常
 * @createTime 2019年08月11日 22:15:00
 */
public class RedisConnectException extends Exception {

    private static final long serialVersionUID = 1639374111871115063L;

    public RedisConnectException(String message) {
        super(message);
    }
}

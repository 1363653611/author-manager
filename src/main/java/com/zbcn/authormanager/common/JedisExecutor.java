package com.zbcn.authormanager.common;

import com.zbcn.authormanager.common.exception.RedisConnectException;

/**
 * @author zbcn8
 * @version 1.0.0
 * @ClassName JedisExecutor.java
 * @Description redis functional 接口
 * @createTime 2019年08月18日 15:17:00
 */
@FunctionalInterface
public interface JedisExecutor<T,R> {
    R execute(T t) throws RedisConnectException;
}

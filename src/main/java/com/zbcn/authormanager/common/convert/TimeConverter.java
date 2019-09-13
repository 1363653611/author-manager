package com.zbcn.authormanager.common.convert;

import com.wuwenze.poi.convert.WriteConverter;
import com.wuwenze.poi.exception.ExcelKitWriteConverterException;
import com.zbcn.authormanager.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;

/**
 * @author zbcn8
 * @version 1.0.0
 * @ClassName TimeConverter.java
 * @Description 时间转换类
 * @createTime 2019年07月21日 12:10:00
 */
@Slf4j
public class TimeConverter implements WriteConverter {

    @Override
    public String convert(Object value) {
        if (value == null) {
            return "";
        } else {
            try {
                return DateUtil.formatCSTTime(value.toString(), DateUtil.FULL_TIME_SPLIT_PATTERN);
            } catch (ParseException e) {
                String message = "时间转换异常";
                log.error(message, e);
                throw new ExcelKitWriteConverterException(message);
            }
        }
    }
}

/**
 * LY.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.kylin.spring.utils;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONToken;
import com.alibaba.fastjson.parser.deserializer.AbstractDateDeserializer;

/**
 * LY日期反序列化处理器
 * @author Smile
 * @version $Id: MyDateFormatDeserializer.java, v 0.1 2016年6月15日 下午3:17:37 Smile Exp $
 */
public class LYDateFormatDeserializer extends AbstractDateDeserializer {
    /** 日期格式*/
    private String format;

    /**
     * 
     * @param format
     */
    public LYDateFormatDeserializer(String format) {
        super();
        this.format = format;
    }

    @Override
    public int getFastMatchToken() {
        return JSONToken.LITERAL_INT;
    }

    /** 
     * @see com.alibaba.fastjson.parser.deserializer.DateFormatDeserializer#cast(com.alibaba.fastjson.parser.DefaultJSONParser, java.lang.reflect.Type, java.lang.Object, java.lang.Object)
     */
    @SuppressWarnings("unchecked")
    @Override
    protected <Date> Date cast(DefaultJSONParser parser, Type clazz, Object fieldName, Object val) {
        if (format == null) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        if (val instanceof String) {
            String strVal = (String) val;
            if (strVal.length() == 0) {
                return null;
            }

            try {
                return (Date) new SimpleDateFormat(format).parse((String) val);
            } catch (ParseException e) {
                throw new JSONException("parse error");
            }
        }
        throw new JSONException("parse error");
    }

    /**
     * Getter method for property <tt>myFormat</tt>.
     * 
     * @return property value of myFormat
     */
    public String getMyFormat() {
        return format;
    }

    /**
     * Setter method for property <tt>myFormat</tt>.
     * 
     * @param myFormat value to be assigned to property myFormat
     */
    public void setMyFormat(String myFormat) {
        this.format = myFormat;
    }

}

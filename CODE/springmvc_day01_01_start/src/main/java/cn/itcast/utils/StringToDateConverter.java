package cn.itcast.utils;

import com.sun.xml.internal.ws.policy.privateutil.RuntimePolicyUtilsException;
import org.springframework.core.convert.converter.Converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 把字符串转换日期
 */
public class StringToDateConverter implements Converter<String, Date> {
    /**
     *
     * @param s 传入进来的字符串
     * @return
     */
    @Override
    public Date convert(String s) {
        //判断
        if(s == null){
            throw new RuntimeException("请您传入数据");
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        //把字符串转换日期
        try {
            return df.parse(s);
        } catch (Exception e) {
            throw new RuntimeException("数据类型转换出现错误");
        }
    }
}

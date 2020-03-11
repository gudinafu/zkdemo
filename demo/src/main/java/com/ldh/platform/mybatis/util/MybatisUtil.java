package com.ldh.platform.mybatis.util;


import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

/**
 * <一句话功能简述>mybatis <if test="?"></if> test参数校验
 * <功能详细描述>调用方式:<if test="@com.core.base.mybatis.util.MybatisUtil@isNotEmpty(userId)"></if>
 */
public class MybatisUtil {
    /**
     * <一句话功能简述>校验属性是否为空
     * <功能详细描述>
     *
     * @param obj 需要校验的属性(属性可以是:Collection，Array,String,Map)
     * @return
     * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings("rawtypes")
    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }

        if (obj instanceof String) {
            return StringUtils.isEmpty((String) obj);
        } else if (obj instanceof Collection<?>) {
            return CollectionUtils.isEmpty((Collection<?>) obj);
        } else if (obj.getClass().isArray()) {
            return Array.getLength(obj) == 0;
        } else if (obj instanceof Map) {
            return MapUtils.isEmpty((Map) obj);
        }

        return false;
    }

    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }

    /**
     * 将普通字符串格式化成数据库认可的字符串格式
     *
     * @param input 要格式化的字符串
     * @return 合法的数据库字符串('a','b','c')
     */
    public static String formatSqlIn(String input) {
        if(isEmpty(input)){
            return "''";
        }
        StringBuffer sb=new StringBuffer("");
        String[] strs=input.split(",");
        for (String s : strs){
            sb.append("'").append(s).append("'");
            sb.append(",");
        }
        return sb.substring(0,sb.length()-1);
    }
}

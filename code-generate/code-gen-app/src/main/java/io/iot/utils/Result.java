package io.iot.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.Map;

/**
 * 和前端交互的数据结果
 */
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 5870221908873132839L;
    /**
     * 标示
     */
    private int code;

    /**
     * 返回给页面的成功消息
     */
    private String msg;

    /**
     * 返回的内容
     */
    private T data;

    public Result() {
        super();
    }

    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> Result<T> success() {
        return success(null);
    }

    public static <T> Result<T> success(T data) {
        return success(data, "操作成功");
    }

    public static <T> Result<T> success(T data, String msg) {
        return success(data, ConstantCode.SUCCESS, msg);
    }

    public static <T> Result<T> success(Integer code, String msg) {
        return success(null, code, msg);
    }

    public static <T> Result<T> success(T data, Integer code, String msg) {
        Result<T> resultData = new Result<T>();
        resultData.setCode(code);
        resultData.setMsg(msg);
        resultData.setData(data);
        return resultData;
    }
    
    public static Result error() {
        Result resultData = new Result();
        resultData.setCode(ConstantCode.ERROR);
        resultData.setMsg("操作失败");
        return resultData;
    }

    public static Result error(String msg) {
        Result resultData = new Result();
        resultData.setCode(ConstantCode.ERROR);
        resultData.setMsg(msg);
        return resultData;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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
    
   /**
    * 处理返回大小写问题
    * @param map
    * @return
    */
    public static Map<String, Object> addLowercase(Map<String, Object> map) {
    	map.put("code", map.get("CODE"));
    	map.put("msg", map.get("MSG"));
		return map;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
    
	public String toJsonString() {
		JSONObject json =  new JSONObject();
		json.put("code", code);
		if(data!=null){
			json.put("data", data);
		}
		if(StringUtils.isNotEmpty(msg)){
			json.put("msg", msg);
		}
		return json.toString();
	}

}

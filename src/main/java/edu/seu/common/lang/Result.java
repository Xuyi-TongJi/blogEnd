package edu.seu.common.lang;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result implements Serializable {

    private int code; // 200 - 正常；非200 - 异常请求
    private String msg;
    private Object data;


    public static Result success(Object data){
        return success(200, "success", data);
    }

    public static Result success(int code, String msg, Object data){
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

    public static Result fail(String msg){
        return fail(400 , msg, null);
    }

    public static Result fail(String msg, Object data){
        return fail(400 , msg, data);
    }

    public static Result fail(int code, String msg, Object data){
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }
}

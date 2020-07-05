package com.lx.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result implements Serializable {
    private Object data;
    private Boolean success = true;
    private Integer code;
    private String info;
    private Integer total = null;
    private Integer currPage = null;
    private Integer pageSize = null;

    public Result(Object data, boolean b, Integer code, String info) {
        this.data = data;
        this.success = b;
        this.code = code;
        this.info = info;

    }

    public Result(Object data, boolean b, Integer code, String info, Integer total, Integer currPage, Integer pageSize) {
        this.data = data;
        this.success = b;
        this.code = code;
        this.info = info;
        this.total = total;
        this.currPage = currPage;
        this.pageSize = pageSize;
    }


    public static Result succeed(String info) {
        return succeedWith(new HashMap<>(), 200, info);
    }

    public static Result succeed(Object model, String info) {
        return succeedWith(model, 200, info);
    }

    public static Result succeed(Object model) {
        return succeedWith(model, 200, "ok");
    }

    public static Result succeed(Object model,Integer total,Integer currPage,Integer pageSize) {
        return succeedWith(model, 200, "ok",total,currPage,pageSize);
    }

    public static Result succeedWith(Object data, Integer code, String info) {
        return new Result(data, true, code, info);
    }

    public static Result succeedWith(Object data, Integer code, String info,Integer total,Integer currPage,Integer pageSize) {
        return new Result(data, true, code, info,total,currPage,pageSize);
    }

    public static Result failed(String info) {
        return failedWith(new HashMap<>(), 9000, info);
    }

    public static Result failed(Object model, String info) {
        return failedWith(model, 9000, info);
    }

    public static Result failed(Integer code, String info) {
        return failedWith(new HashMap<>(), code, info);
    }

    public static Result failedWith(Object data, Integer code, String info) {
        return new Result(data, false, code, info);
    }
}

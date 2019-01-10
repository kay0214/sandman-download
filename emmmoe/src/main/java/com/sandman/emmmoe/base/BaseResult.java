/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.emmmoe.base;

import com.alibaba.fastjson.JSON;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author sunpeikai
 * @version BaseResult, v0.1 2018/12/3 11:27
 */
public class BaseResult{
    private int code = 200;
    private int count = 0;
    private String msg;
    private List data;

    public BaseResult(){

    }

    public BaseResult(int code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public BaseResult(String msg){
        this.msg = msg;
    }

    public BaseResult(int count,List data){
        this.count = count;
        this.data = data;
        if(!CollectionUtils.isEmpty(data)){
            msg = "暂无数据";
        }
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}

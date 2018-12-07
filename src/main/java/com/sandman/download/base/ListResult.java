/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.base;

import java.util.List;

/**
 * @author sunpeikai
 * @version ListResult, v0.1 2018/7/2 19:42
 */
public class ListResult<T> {

    private List<T> list;
    private T data;
    private int count;

    public static <T> ListResult<T> build(List<T> list,int count) {
        ListResult<T> result = new ListResult<>();
        result.setCount(count);
        result.setList(list);
        return result;
    }

    public static <T> ListResult<T> build2(List<T> list, int count, T data) {
        ListResult<T> result = new ListResult<>();
        result.setCount(count);
        result.setList(list);
        result.setData(data);
        return result;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ListResult{" +
                "list=" + list +
                ", data=" + data +
                ", count=" + count +
                '}';
    }
}

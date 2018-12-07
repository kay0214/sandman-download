/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.base;

import com.alibaba.fastjson.JSON;
import com.sandman.download.constant.ReturnMessage;
import com.sandman.download.utils.StringUtil;

import java.io.Serializable;

/**
 * @author sunpeikai
 * @version BaseResult, v0.1 2018/12/3 11:27
 */
public class BaseResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String SUCCESS = "000";
    public static final String SUCCESS_DESC = "成功";
    public static final String ERROR = "-1";
    public static final String ERROR_DESC = "异常";
    public static final String FAIL = "1";
    public static final String FAIL_DESC = "失败";
    public static final String NO_PERMISSION = "2";
    public static final String NO_PERMISSION_DESC = "无权限";

    private String status = SUCCESS;
    private String statusDesc = SUCCESS_DESC;
    private T data;
    private int totalCount;

    public BaseResult() {
        super();
    }

    public BaseResult(T data) {
        super();
        this.data = data;
    }

    public BaseResult(T data,int totalCount) {
        super();
        this.data = data;
        this.totalCount = totalCount;
    }

    public BaseResult(Throwable e) {
        super();
        this.status = ERROR;
        this.statusDesc = e.toString();
    }

    public BaseResult(ReturnMessage returnMessage){
        super();
        this.status = returnMessage.getCode();
        this.statusDesc = returnMessage.getMessage();
    }

    public BaseResult(String status, String statusDesc) {
        super();
        this.status = status;
        this.statusDesc = statusDesc;
    }

    public BaseResult(ReturnMessage returnMessage, Object... params) {
        super();
        this.status = returnMessage.getCode();
        this.statusDesc = StringUtil.getMessage(returnMessage.getMessage(), params);
    }

    /**
     * statusDesc
     * @return the statusDesc
     */

    public String getStatusDesc() {
        return statusDesc;
    }

    /**
     * @param statusDesc the statusDesc to set
     */
    public void setStatusInfo(String status, String statusDesc) {
        this.status = status;
        this.statusDesc = statusDesc;
    }

    /**
     * 用枚举类型设定status和statusDesc，可选拼接参数
     * @param returnMessage
     * @param params
     */
    public void setStatusInfo(ReturnMessage returnMessage, Object... params) {
        this.status = returnMessage.getCode();
        this.statusDesc = StringUtil.getMessage(returnMessage.getMessage(), params);
    }

    /**
     * @param statusDesc the statusDesc to set
     */

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    /**
     * status
     * @return the status
     */

    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * data
     * @return the data
     */

    public T getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(T data) {
        this.data = data;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}

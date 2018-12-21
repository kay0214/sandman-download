package com.sandman.emmmoe.constant;

import com.sandman.emmmoe.dao.mysql.emmmoe.model.auto.NotContains;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiasq
 * @version CommonConstant, v0.1 2018/4/25 17:09
 */
public class CommonConstant {

    /**
     * 不包含链接list
     * @auth sunpeikai
     * @param
     * @return
     */
    public static List<NotContains> notContainsList = new ArrayList<>();

    public static final String EMMMOE_GOTO = "goto=";
    /**
     * 登录失效时间(秒)
     * */
    public static final int LOGIN_EXPIRE = 3600;
}

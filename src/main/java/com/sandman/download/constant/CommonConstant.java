package com.sandman.download.constant;

import com.sandman.download.dao.mysql.download.model.auto.Resource;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiasq
 * @version CommonConstant, v0.1 2018/4/25 17:09
 */
public class CommonConstant {

    /**
     * 热门资源列表缓存
     * @auth sunpeikai
     * @param
     * @return
     */
    public static List<Resource> HOT_RESOURCES = new ArrayList<>();

    /**
     * 热门资源默认分页大小
     * @auth sunpeikai
     * @param
     * @return
     */
    public static final int HOT_RESOURCES_LIMIT = 30;

    /**
     * 积分操作-下载资源默认描述desc
     * @auth sunpeikai
     * @param
     * @return
     */
    public static final String GOLD_REDUCE_DESC = "下载资源，积分扣除";

    /**
     * 积分操作-下载资源默认描述desc
     * @auth sunpeikai
     * @param
     * @return
     */
    public static final String GOLD_ADD_DESC = "其他用户下载该资源，积分增加";

    /**
     * 登录失效时间(秒)
     * */
    public static final int LOGIN_EXPIRE = 3600;

    /**
     * 文件限制大小
     * @auth sunpeikai
     * @param
     * @return
     */
    //public static final int

    /**
     * 默认分页大小
     * @auth sunpeikai
     * @param
     * @return
     */
    public static final int DEFAULT_LIMIT = 10;

    /**
     * 状态
     */
    public static final String SUCCESS = "success";

    /**PC*/
    public static final String CLIENT_PC = "0";

    /**微官网*/
    public static final String CLIENT_WECHAT = "1";

     /**安卓*/
    public static final String CLIENT_ANDROID = "2";

     /**IOS*/
    public static final String CLIENT_IOS = "3";

     /** 其他*/
    public static final String CLIENT_OTHER = "4";

    /** 短信验证码状态,新验证码 */
    public static final Integer CKCODE_NEW = 0;
    /** 短信验证码状态,失效 */
    public static final Integer CKCODE_FAILED = 7;
    /** 短信验证码状态,已验 */
    public static final Integer CKCODE_YIYAN = 8;
    /** 短信验证码状态,已用 */
    public static final Integer CKCODE_USED = 9;



    /** 短信模板名 */
    /** 注册 */
    public static final String PARAM_TPL_ZHUCE = "TPL_ZHUCE";
    /** 短信模板名-提现验证码 */
    public static final String PARAM_TPL_SMS_WITHDRAW = "TPL_SMS_WITHDRAW";

    /** 找回密码 */
    public static final String PARAM_TPL_ZHAOHUIMIMA = "TPL_ZHAOHUIMIMA";

    /** 更换手机号-验证原手机号 */
    public static final String PARAM_TPL_YZYSJH = "TPL_YZYSJH";

    /** 更换手机号-绑定新手机号 */
    public static final String PARAM_TPL_BDYSJH = "TPL_BDYSJH";



	/** borrow_apicron 字段说明 */
	// api_type = 0 放款
	public static final int APICRON_API_TYPE_BORROW = 0;
	// api_type = 1 还款
	public static final int APICRON_API_TYPE_REPAY = 1;
	// status 初始
	public static final int APICRON_STATUS_INIT = 0;
	// 1 放款/还款 请求中
	public static final int APICRON_STATUS_REQUESTING = 1;
	// 2 放款/还款 请求成功
	public static final int APICRON_STATUS_REQUEST_SUCCESS = 2;
	// 3 放款/还款 校验成功
	public static final int APICRON_STATUS_CHECK_SUCCESS = 3;
	// 4 放款/还款 校验失败
	public static final int APICRON_STATUS_CHECK_FAIL = 4;
	// 5 放款/还款 失败
	public static final int APICRON_STATUS_RESULT_FAIL_ = 5;
	// 6 放款/还款 成功
	public static final int APICRON_STATUS_RESULT_SUCCESS_ = 6;
    // 融通宝加息利息还款状态 0-未完成
	public static final int APICRON_EXTRA_YIELD_REPAY_STATUS_INIT = 0;
    // 融通宝加息利息还款状态 1-已完成
	public static final int APICRON_EXTRA_YIELD_REPAY_STATUS_FINISH = 1;
    // 融通宝加息利息还款状态 2-进行中
    public static final int APICRON_EXTRA_YIELD_REPAY_STATUS_RUNNING = 2;
    // 融通宝加息利息还款状态 9-错误
    public static final int APICRON_EXTRA_YIELD_REPAY_STATUS_ERROR = 9;

    // hyjf inst_code机构编号
    public static final String HYJF_INST_CODE = "10000000";

    /** 注册成功跳转页面 */
    public static final String REGIST_RESULT_SUCCESS = "/user/regist/result/success";

    //-------------------------------活动---------------------------------
    //活动编号不能为空
    public final static String ACTIVITYID_IS_NULL="活动编号不能为空";
    //该活动不存在
    public final static String ACTIVITY_ISNULL="该活动不存在";
    //该活动不存在
    public final static String ACTIVITY_TIME_NOT_START="该活动还未开始";
    //该活动已结束
    public final static String ACTIVITY_TIME_END="您来晚了，活动已过期~~";
    //该活动已结束
    public final static String PLATFORM_LIMIT="本活动限***端参与";
    //该活动用户已参与
    public final static String ACTIVITY_LIMIT="该活动用户已参与";


    /**
     * 数据源控制优先级别(值越小优先级越高)，读方法切面
     */
    public static final String[] DATASOURCE_QUERY_PREFIX = {"select","query","count","search","get","find","check","export"};
    public static final int DATASOURCE_AOP_DS = 1;
    public static final int DATASOURCE_AOP_TRANSACTION = 2;
}

package com.sandman.download.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author xiasq
 * @version CommonUtils, v0.1 2017/11/11 11:51
 */
public class BeanUtils {

	private final static Logger logger = LoggerFactory.getLogger(BeanUtils.class);

	/**
	 * 数据脱敏
	 * @auth sunpeikai
	 * @param
	 * @return
	 */
	public static String sensitiveReplace(String source,int from,int to){
		if(StringUtils.isBlank(source)){
			return "";
		}
		String replace = "";
		int size = to - from;
		for(int i=0;i<size;i++){
			replace += "*";
		}
		String temp = source.substring(from,to);
		return source.replace(temp,replace);
	}

	/**
	 * 提供对象属性null转""方法，目前只支持String的属性
	 * 
	 * @param obj
	 */
	public static Object convertNullToEmptyString(Object obj) {
		if (obj == null) {
            return obj;
        }
		// 获取对象属性
		Field[] fields = obj.getClass().getDeclaredFields();
		for (Field field : fields) {
			// 跳过静态属性
			String mod = Modifier.toString(field.getModifiers());
			if (mod.indexOf("static") != -1) {
                continue;
            }

			// 得到属性的类名
			String className = field.getType().getSimpleName();
			if ("String".equalsIgnoreCase(className)) {
				try {
					field.setAccessible(true); // 设置些属性是可以访问的
					Object val = field.get(obj);
					if (val == null) {
						field.set(obj, "");
					}
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		return obj;
	}

	/**
	 * 提供对象属性null转""方法，目前只支持String的属性
	 * 
	 * @param colls
	 * @return
	 */
	public static Collection convertNullToEmptyString(Collection colls) {
		for (Object coll : colls) {
			convertNullToEmptyString(coll);
		}
		return colls;
	}

	/**
	 * 如果为空就从collection中移除
	 * @auth sunpeikai
	 * @param
	 * @return
	 */
	public static void removeIfNullOrNullStr(Collection collection){
		collection.removeAll(Collections.singleton(null));
		collection.removeAll(Collections.singleton(""));
	}

	/**
	 * 适应app返回格式，数据不能是null,转换为指定格式
	 * 
	 * @param obj
	 * @param val
	 * @return
	 */
	public static Object nvl(Object obj, String val) {
		if (obj == null) {
			return val;
		}
		return obj;
	}

	/**
	 * 手机号验证
	 *
	 * @param str
	 * @return 验证通过返回true
	 */
	public static boolean isMobile(String str) {
		Pattern p = null;
		Matcher m = null;
		boolean b = false;
		p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号
		m = p.matcher(str);
		b = m.matches();
		return b;
	}

	/**
	 * 金额格式化不保留小数点后"0"
	 */
	public static String formatBigDecimal(BigDecimal money) {
		DecimalFormat df = new DecimalFormat("###.####");
		return df.format(money).toString();
	}

	// 判断小数点后2位的数字的正则表达式
	private final static String IS_AMOUNT_REGEX_STR = "^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$";

	/**
	 * 金额验证 保留2位小数
	 */
	public static boolean isNumber(String amount) {
		Pattern pattern = Pattern.compile(IS_AMOUNT_REGEX_STR);
		Matcher match = pattern.matcher(amount);
		return match.matches();
	}

	/**
	 * list数据循环copyProperties
	 * @param sources
	 * @param clazz
	 * @param <S>
	 * @param <T>
	 * @return
	 * @author zhangyk
	 * @date 2018年6月6日14:57:50
	 */
	public static <S, T> List<T> convertBeanList(List<S> sources, Class<T> clazz) {
		return sources.stream().map(source -> convertBean(source, clazz)).collect(Collectors.toList());
	}

	/**
	 * 简单属性copy
	 * @param s
	 * @param clazz
	 * @param <S>
	 * @param <T>
	 * @author zhangyk
	 * @date 2018年6月6日14:57:50
	 */
	public static <S, T> T convertBean(S s, Class<T> clazz) {
		if (s == null) {
			return null;
		}
		try {
			T t = clazz.newInstance();
			org.springframework.beans.BeanUtils.copyProperties(s, t);
			return t;
		} catch (InstantiationException | IllegalAccessException e) {
			logger.error("拷贝属性异常", e);
			throw new RuntimeException("拷贝属性异常");
		}
	}
}

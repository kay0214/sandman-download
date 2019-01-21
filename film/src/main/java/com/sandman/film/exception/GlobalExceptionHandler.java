package com.sandman.film.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author sunpeikai
 * @version GlobalExceptionHandler, v0.1 2018/1/21 22:15
 */

@ControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	private static final String SYSTEM_ERROR = "99";
	private static final String SYSTEM_ERROR_MSG = "系统异常";

	@ExceptionHandler(value = Exception.class)
	public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) {
		logger.error("请求路径:[{}]",req.getRequestURI());
		logger.error("system error", e);
		ModelAndView modelAndView = new ModelAndView("/error");
		modelAndView.addObject("status",SYSTEM_ERROR);
		modelAndView.addObject("msg",SYSTEM_ERROR_MSG);
		return modelAndView;
	}

}

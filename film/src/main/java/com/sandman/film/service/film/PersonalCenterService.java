/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.film.service.film;

import com.sandman.film.base.BaseService;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author sunpeikai
 * @version PersonalCenterService, v0.1 2019/1/8 16:51
 */
public interface PersonalCenterService extends BaseService {

    /**
     * 上传头像到服务器
     * @auth sunpeikai
     * @param
     * @return
     */
    boolean uploadIcon(MultipartFile multipartFile);
}

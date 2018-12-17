/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.service.download;

import com.alibaba.fastjson.JSONObject;
import com.sandman.download.base.BaseService;
import com.sandman.download.dao.mysql.download.model.auto.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author sunpeikai
 * @version UploadService, v0.1 2018/12/11 17:09
 */
public interface UploadService extends BaseService {

    /**
     * 上传文件
     * @auth sunpeikai
     * @param
     * @return
     */
    JSONObject uploadFile(MultipartFile multipartFile);

    /**
     * 发布资源
     * @auth sunpeikai
     * @param
     * @return
     */
    int uploadResource(Resource resource);
}

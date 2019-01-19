/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.controller.admin;

import com.sandman.download.base.BaseController;
import com.sandman.download.base.BaseResult;
import com.sandman.download.config.InterceptorConfig;
import com.sandman.download.config.SystemConfig;
import com.sandman.download.constant.ReturnMessage;
import com.sandman.download.utils.PasswordEncrypt;
import com.sandman.download.utils.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import java.io.*;

/**
 * @author sunpeikai
 * @version RestartProjectController, v0.1 2019/1/19 11:51
 */
@RestController
@RequestMapping(value = "/project")
public class RestartProjectController extends BaseController {

    String cmd = "sh /home/sandman/download/restart.sh";

    String reload = "";

    @PostMapping(value = "/restart")
    public BaseResult restart(String password){

        logger.info("输入的password:[{}]",password);
        if(!SystemConfig.getRestartPassword().equals(PasswordEncrypt.getPasswordEncrypt(password,"sunpeikai"))){
            return new BaseResult(ReturnMessage.ERR_PASSWORD);
        }
        try{
/*            Resource resource = new ClassPathResource("restart.txt");
            //String path = this.getClass().getResource("restart.txt").getPath();
            logger.info("path:[{}]",((ClassPathResource) resource).getPath());
            File file = resource.getFile();
            OutputStream outputStream = new FileOutputStream(file);
            outputStream.write(RandomUtils.getUuidStr().getBytes());
            outputStream.close();*/
            String[] cmds = {"/bin/sh","-c",cmd};
            Process pro = Runtime.getRuntime().exec(cmds);
            pro.waitFor();
            InputStream in = pro.getInputStream();
            BufferedReader read = new BufferedReader(new InputStreamReader(in));
            String line = null;
            while((line = read.readLine())!=null){
                System.out.println(line);
            }
            return new BaseResult();
        }catch (Exception e){
            logger.info("写入配置文件restart失败，download应用重启失败");
            e.printStackTrace();
            return new BaseResult(ReturnMessage.ERR_APPLICATION_RESTART);
        }
    }
}

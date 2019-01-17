package com.sandman.download.dao.mysql.system.mapper.auto;

import com.sandman.download.dao.mysql.system.model.auto.SignIn;
import com.sandman.download.dao.mysql.system.model.auto.SignInExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SignInMapper {
    int countByExample(SignInExample example);

    int deleteByExample(SignInExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SignIn record);

    int insertSelective(SignIn record);

    List<SignIn> selectByExample(SignInExample example);

    SignIn selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SignIn record, @Param("example") SignInExample example);

    int updateByExample(@Param("record") SignIn record, @Param("example") SignInExample example);

    int updateByPrimaryKeySelective(SignIn record);

    int updateByPrimaryKey(SignIn record);
}
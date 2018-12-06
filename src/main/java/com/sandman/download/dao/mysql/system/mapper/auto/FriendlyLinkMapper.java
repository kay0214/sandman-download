package com.sandman.download.dao.mysql.system.mapper.auto;

import com.sandman.download.dao.mysql.system.model.auto.FriendlyLink;
import com.sandman.download.dao.mysql.system.model.auto.FriendlyLinkExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FriendlyLinkMapper {
    int countByExample(FriendlyLinkExample example);

    int deleteByExample(FriendlyLinkExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FriendlyLink record);

    int insertSelective(FriendlyLink record);

    List<FriendlyLink> selectByExample(FriendlyLinkExample example);

    FriendlyLink selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FriendlyLink record, @Param("example") FriendlyLinkExample example);

    int updateByExample(@Param("record") FriendlyLink record, @Param("example") FriendlyLinkExample example);

    int updateByPrimaryKeySelective(FriendlyLink record);

    int updateByPrimaryKey(FriendlyLink record);
}
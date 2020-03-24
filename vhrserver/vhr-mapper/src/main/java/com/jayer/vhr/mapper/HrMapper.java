package com.jayer.vhr.mapper;

import com.jayer.vhr.model.Hr;
import com.jayer.vhr.model.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HrMapper {
    int deleteByPrimaryKey(Integer id);

    Hr selectByPrimaryKey(Integer id);

    Hr loadUserByUserName(String username);

    List<Role> getHrRolesById(Integer id);

    List<Hr> getAllHrs(@Param("hrid") Integer hrid, @Param("keywords") String keywords);

    int updateByPrimaryKey(Hr record);

    int updateByPrimaryKeySelective(Hr record);

    Integer updatePasswd(@Param("hrid") Integer hrid, @Param("encodePass") String encodePass);

    Integer updateUserface(@Param("url") String url, @Param("id") Integer id);



}

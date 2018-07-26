package com.open.capacity.controller;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

/**
 * Created by Meng Ruo on 2018/7/19  15:49.
 **/
@Service
@Mapper
public interface UserTimesMapper {

    @Select("select count(1)  from user_times where token=#{token}")
    int findToken(String token);

    @Insert("INSERT into user_times (token,user,times) values (#{token},#{name},#{times})")
    void insertTimes(@Param("token") String token,@Param("name") String name,  @Param("times") Long times);
}

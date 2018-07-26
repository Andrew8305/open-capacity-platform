package com.open.capacity.client.oauth2.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

/**
 * Created by Meng Ruo on 2018/7/19  15:49.
 **/
@Service
@Mapper
public interface UserTimesMapper {

    @Select("select * from user_times where token=#{token}")
    @Results({
            @Result(property = "token",column = "token"),
            @Result(property = "times",column = "times")
    })
    User findTokenTimes(String token);

    @Update("update user_times set times=#{times},updateTime = NOW(),ip=#{ip} where token=#{token}")
    void updateTimes(@Param("token") String token,@Param("times") Long times,@Param("ip") String ip);

    @Update("update user_times set times=0 where times>0")
    void cleanTimes();
}

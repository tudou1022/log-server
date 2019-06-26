package com.boge.logserver.dao;

import com.boge.logserver.domain.LogInfoTest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface LogInfoTestMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LogInfoTest record);

    int insertSelective(LogInfoTest record);

    LogInfoTest selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LogInfoTest record);

    int updateByPrimaryKey(LogInfoTest record);

    List<LogInfoTest> getLogInfoList(Map<String, Object> paraMap);

    Integer getInfoCountByMap(Map<String, Object> paraMap);
}
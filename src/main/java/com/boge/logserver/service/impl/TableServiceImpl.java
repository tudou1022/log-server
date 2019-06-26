package com.boge.logserver.service.impl;

import com.boge.logserver.dao.LogInfoTestMapper;
import com.boge.logserver.domain.LogInfoTest;
import com.boge.logserver.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: TableServiceImpl
 * @Description: TODO
 * @Author: zhangwb
 * @Date: 2019/5/30 14:14
 **/
@Service
public class TableServiceImpl implements TableService {

    @Autowired
    LogInfoTestMapper testMapper;

    @Override
    public List<LogInfoTest> getInfoList(Map<String, Object> paraMap) {

        return  testMapper.getLogInfoList(paraMap);

    }

    @Override
    public Integer getInfoCountByMap(Map<String, Object> paraMap) {

        return testMapper.getInfoCountByMap(paraMap);
    }
}

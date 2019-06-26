package com.boge.logserver.service;


import com.boge.logserver.domain.LogInfoTest;

import java.util.List;
import java.util.Map;

public interface TableService {
    List<LogInfoTest> getInfoList(Map<String, Object> paraMap);

    Integer getInfoCountByMap(Map<String, Object> paraMap);
}

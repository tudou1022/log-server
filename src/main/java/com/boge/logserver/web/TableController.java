package com.boge.logserver.web;

import com.boge.logserver.constant.LogLevel;
import com.boge.logserver.constant.StmpResultEnum;
import com.boge.logserver.domain.LogInfoTest;
import com.boge.logserver.dto.LogInfoDTO;
import com.boge.logserver.model.base.GridDataResult;
import com.boge.logserver.model.req.SearchParam;
import com.boge.logserver.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: TableController
 * @Description: TODO
 * @Author: zhangwb
 * @Date: 2019/5/30 13:59
 **/
@RestController
@RequestMapping("/table")
public class TableController {

    @Autowired
    TableService tableService;

    @RequestMapping("/getInfoList")
    @ResponseBody
    public GridDataResult<LogInfoDTO> getInfoList(@RequestBody SearchParam searchParam){
        String[] dataRange = searchParam.getDataRange();
        Map<String,Object> paraMap = new HashMap<>();
        if (dataRange !=null && dataRange.length > 0){
            paraMap.put("dataStart",dataRange[0].toString());
            paraMap.put("dataEnd",dataRange[1].toString());
        }
        StringBuffer levelBuffer = new StringBuffer();
        if(null != searchParam.getLevelSearch() && searchParam.getLevelSearch().length > 0){
            for (String level:searchParam.getLevelSearch()) {
                levelBuffer.append(level+",");
            }
            paraMap.put("level",levelBuffer.substring(0,levelBuffer.length()-1));
        }
        paraMap.put("pageIndex",(searchParam.getCurrentPage()-1)*10);
        paraMap.put("pageSize",(searchParam.getCurrentPage()-1)*10+10);
        Integer count = tableService.getInfoCountByMap(paraMap);
        if(count == 0){
            return new GridDataResult(null,StmpResultEnum.success.getValue(),"暂无数据", count);
        }
        List<LogInfoTest> logInfoList = tableService.getInfoList(paraMap);
        List<LogInfoDTO> logInfoDTOList =new ArrayList<>();
        if(logInfoList != null && logInfoList.size()> 0){
            for (LogInfoTest loginfo: logInfoList){
                LogInfoDTO dto =new LogInfoDTO();
                dto.setId(loginfo.getId());
                dto.setLevel(LogLevel.getEnum(loginfo.getLevel()).getName());
                dto.setClasspath(loginfo.getClasspath());
                dto.setErrorInfo(loginfo.getErrorInfo());
                String[] split = loginfo.getErrorMethod().split(":");
                dto.setErrorMethod(split[0]);
                dto.setLineNumber(split[1]);
                dto.setCreatetime(new SimpleDateFormat("yyyy-dd-MM HH:mm:ss").format(loginfo.getCreatetime()));
                logInfoDTOList.add(dto);
            }
        }
        return new GridDataResult(logInfoDTOList,StmpResultEnum.success.getValue(), StmpResultEnum.success.getName(), count);
    }

    public static void main(String[] args) {
        String[] s= new String[]{"info","error"};
    }
}

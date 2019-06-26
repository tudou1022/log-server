package com.boge.logserver.model.base;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: GridDataResult
 * @Description: TODO
 * @Author: zhangwb
 * @Date: 2019/5/31 14:21
 **/
public class GridDataResult<T> implements Serializable {
    private List<T> data;
    private Integer code;
    private String msg;
    private Integer count;

    public GridDataResult(){
        this.code=200;
        this.count=0;
        this.msg="暂无数据";
    }

    public GridDataResult(List<T> data, Integer code, String msg, Integer count) {
        this.data = data;
        this.code = code;
        this.msg = msg;
        this.count = count;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    /**
     * 返回code
     * @return
     */
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     * 返回消息
     * @return
     */
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 总数
     * @return
     */
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}

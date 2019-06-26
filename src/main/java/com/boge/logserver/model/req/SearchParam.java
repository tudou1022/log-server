package com.boge.logserver.model.req;

/**
 * @ClassName: SearchParam
 * @Description: TODO
 * @Author: zhangwb
 * @Date: 2019/5/31 11:14
 **/
public class SearchParam {
    private String[] dataRange;
    private String[] levelSearch;
    private Integer currentPage;

    public String[] getDataRange() {
        return dataRange;
    }

    public void setDataRange(String[] dataRange) {
        this.dataRange = dataRange;
    }

    public String[] getLevelSearch() {
        return levelSearch;
    }

    public void setLevelSearch(String[] levelSearch) {
        this.levelSearch = levelSearch;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }
}

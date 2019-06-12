package com.springboot.lipeng.vo;

import java.io.Serializable;

/**
* @Description:    分页工具类
* @Author:         LiPeng
* @CreateDate:     2019/5/30 12:48
* @UpdateUser:     LiPeng
* @UpdateDate:     2019/5/30 12:48
* @UpdateRemark:   暂无
* @Version:        1.0
*/
public class Page<T> implements Serializable {
    private Integer limit = 10;
    //当前页
    private Integer page = 1;
    //页大小
    private Integer rows = 10;
    // 总记录 数
    private Integer totalRecord = 10;
    //总页数
    private Integer totalPage;
    //关键字类型
    private String keyType;
    //查询关键字
    private String keyWord;
    //开始记录位置
    private Integer start;
    //用户id
    private String userid;
    //其他用户id
    private String otherid;

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getKeyType() {
        return keyType;
    }

    public void setKeyType(String keyType) {
        this.keyType = keyType;
    }

    public String getOtherid() {
        return otherid;
    }

    public void setOtherid(String otherid) {
        this.otherid = otherid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(Integer totalRecord) {
        this.totalRecord = totalRecord;
    }

    public Integer getTotalPage() {
        totalPage = (totalRecord - 1) / rows + 1;

        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {

        this.totalPage = totalPage;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public Integer getStart() {
        start = (page - 1) * rows;

        return start;
    }

    public void setStart(Integer start) {

        this.start = start;
    }


    public Page() {
    }

    public Page(Integer page, Integer rows, Integer totalRecord, Integer totalPage, String keyType, String keyWord, Integer start, String userid, String otherid) {
        this.page = page;
        this.rows = rows;
        this.totalRecord = totalRecord;
        this.totalPage = totalPage;
        this.keyType = keyType;
        this.keyWord = keyWord;
        this.start = start;
        this.userid = userid;
        this.otherid = otherid;
    }

    public Page(Integer start, Integer rows) {
        this.rows = rows;
        this.start = start;
    }

    @Override
    public String toString() {
        return "Page{" +
                "limit=" + limit +
                ", page=" + page +
                ", rows=" + rows +
                ", totalRecord=" + totalRecord +
                ", totalPage=" + totalPage +
                ", keyType='" + keyType + '\'' +
                ", keyWord='" + keyWord + '\'' +
                ", start=" + start +
                ", userid='" + userid + '\'' +
                ", otherid='" + otherid + '\'' +
                '}';
    }
}

package com.yuepeng.web.manage.log.bean.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @Author: xtq
 * @Date: 2020/5/29 15:40
 * Copyright (c) 2019, Samton. All rights reserved
 */
public class DataCrawlerLogExcel implements Serializable {

    @Excel(name = "爬虫服务名称", width = 15)
    private String datacrawlerServiceName;//爬虫服务名称

    @Excel(name = "数据来源名称", width = 20)
    private String datasourceWebname;//数据来源名称

    @Excel(name = "爬虫执行时间", format = "yyyy-MM-dd HH:mm:ss",  width = 25)
    private Date crawlerStartTime;//爬虫执行时间

    @Excel(name = "爬虫结束时间", format = "yyyy-MM-dd HH:mm:ss",  width = 25)
    private Date crawlerEndTime;//爬虫结束时间

    @Excel(name = "爬虫数量", width = 15)
    private Integer crawlerNum;//爬虫数量

    public String getDatacrawlerServiceName() {
        return datacrawlerServiceName;
    }

    public void setDatacrawlerServiceName(String datacrawlerServiceName) {
        this.datacrawlerServiceName = datacrawlerServiceName;
    }

    public String getDatasourceWebname() {
        return datasourceWebname;
    }

    public void setDatasourceWebname(String datasourceWebname) {
        this.datasourceWebname = datasourceWebname;
    }

    public Date getCrawlerStartTime() {
        return crawlerStartTime;
    }

    public void setCrawlerStartTime(Date crawlerStartTime) {
        this.crawlerStartTime = crawlerStartTime;
    }

    public Date getCrawlerEndTime() {
        return crawlerEndTime;
    }

    public void setCrawlerEndTime(Date crawlerEndTime) {
        this.crawlerEndTime = crawlerEndTime;
    }

    public Integer getCrawlerNum() {
        return crawlerNum;
    }

    public void setCrawlerNum(Integer crawlerNum) {
        this.crawlerNum = crawlerNum;
    }
}
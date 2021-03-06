package com.yuepeng.web.manage.dict.controller;

import com.yuepeng.platform.framework.base.BaseController;
import com.yuepeng.platform.framework.constant.WebConstant;
import com.yuepeng.platform.framework.exception.ServiceException;
import com.yuepeng.platform.framework.mybatis.pagination.Pagination;
import com.yuepeng.platform.pm.bean.entity.TSysLog;
import com.yuepeng.platform.pm.constant.PmStateConstant;
import com.yuepeng.platform.pm.service.ILogService;
import com.yuepeng.web.manage.dict.bean.entity.TInfotype;
import com.yuepeng.web.manage.dict.bean.excel.InfotypeEntity;
import com.yuepeng.web.manage.dict.bean.vo.InfotypeVo;
import com.yuepeng.web.manage.dict.constant.InfoTypeExpCodeConstant;
import com.yuepeng.web.manage.dict.service.IInfotypeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * @Description: 招标信息Controller
 * @Author: ZhongShengbin
 * @Date: 2020/05/20 15:21
 * Copyright (c) 2019, Samton. All rights reserved
 */
@Controller
@RequestMapping("/manage/dict/")
public class InfotypeController extends BaseController {


    @Resource
    private IInfotypeService infotypeService;

    @Resource
    private ILogService logService;
    /**
     * @Author ZhongShengbin
     * @Description  招标信息分页
     * @Date 2020/5/19 0019
     * @Param [paramBean, subscribeSearchVo]
     * @return java.lang.String
     **/
    @RequestMapping("infoType/queryInfoTypeList"+ WebConstant.PAGE_SUFFIX)
    public String queryInfoTypeList(Pagination<InfotypeVo> paramBean, InfotypeVo infotypeVo) throws Exception{
        paramBean.setSearch(infotypeVo);
        Pagination<InfotypeVo> pageData = infotypeService.queryInfoTypePageList(paramBean);
        this.addAttr("pageData", pageData);
        return "dictInfoType/infoType";
    }

    /**
     * @Author ZhongShengbin
     * @Description 招标信息导出
     * @Date 2020/5/19 0019
     * @Param [paramBean, subscribeSearchVo]
     * @return java.lang.String
     **/
    @RequestMapping("infoType/exportInfoTypeList" + WebConstant.BUSINESS_SUFFIX)
    public String exportInfoTypeList(Pagination<InfotypeVo> paramBean, InfotypeVo infotypeVo) throws Exception {
        paramBean.setSearch(infotypeVo);
        Pagination<InfotypeEntity> list = infotypeService.exportInfoTypePageList(paramBean);
        logService.addLog(new TSysLog("招标信息-导出", "导出招标信息！", PmStateConstant.LOG_PLATFORM));
        this.export(response,"字典管理招标信息管理" + String.format("%1$tY%1$tm%1$td", new Date()), InfotypeEntity.class, list.getData());
        return null;
    }

    /**
     * @Author ZhongShengbin
     * @Description 招标信息查看
     * @Date 2020/5/19 0019
     * @Param [subscribeSearchId]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @RequestMapping("infoType/viewInfoTypeList" + WebConstant.BUSINESS_SUFFIX)
    @ResponseBody
    public Map<String, Object> viewInfoType(Integer infotypeId) throws Exception {
        InfotypeVo infotypeVo = infotypeService.queryInfoTypePageListById(infotypeId);
        return this.getResultMap(true, infotypeVo);
    }

    /**
     * @Author ZhongShengbin
     * @Description 编辑招标信息
     * @Date 2020/5/19 0019
     * @Param [searchHotwordVo]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @RequestMapping("infoType/updateInfoType" + WebConstant.BUSINESS_SUFFIX)
    @ResponseBody
    public Map<String,Object> updateInfoType(TInfotype tInfotype) throws Exception{
        if(tInfotype.getClassId() == null){
            throw new ServiceException(InfoTypeExpCodeConstant.INFO_EDIT_COLUMN_ERROR);
        }
        boolean result = infotypeService.updateSelectiveById(tInfotype);
        if(result){
            if (result) {
                logService.addLog(new TSysLog("招标信息类型-编辑", "编辑招标信息类型【" + tInfotype.getInfotypeName() + "】成功 ！", PmStateConstant.LOG_PLATFORM));
            } else {
                logService.addLog(new TSysLog("招标信息类型-编辑", "编辑招标信息类型【" + tInfotype.getInfotypeName() + "】失败 ！", PmStateConstant.LOG_PLATFORM));
            }
            if(!result){
                throw new ServiceException(InfoTypeExpCodeConstant.INFO_EDIT_COLUMN_ERROR);
            }
        }
        return this.getResultMap(true,result);
    }

    /**
     * @Author ZhongShengbin
     * @Description   删除招标信息
     * @Date 2020/5/19 0019
     * @Param [tSearchHotword]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @RequestMapping("infoType/delInfoType" + WebConstant.BUSINESS_SUFFIX)
    @ResponseBody
    public Map<String,Object> deleteHotWord(@RequestParam(value = "infotypeId",required = true) Integer infotypeId) throws Exception{
        boolean result = infotypeService.deleteById(infotypeId);
        if(result){
            if (result) {
                logService.addLog(new TSysLog("招标信息类型-删除", "删除招标信息类型信息成功 ！", PmStateConstant.LOG_PLATFORM));
            } else {
                logService.addLog(new TSysLog("招标信息类型-删除", "删除招标信息类型信息失败 ！", PmStateConstant.LOG_PLATFORM));
            }
            if(!result){
                throw new ServiceException(InfoTypeExpCodeConstant.INFO_EDIT_COLUMN_ERROR);
            }
        }
        return this.getResultMap(true,result);
    }

    /**
     * @Author ZhongShengbin
     * @Description  新增
     * @Date 2020/5/21 0021
     * @Param [memberPriceVo]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @RequestMapping("infoType/addInfoType" + WebConstant.BUSINESS_SUFFIX)
    @ResponseBody
    public Map<String, Object> addPrice(InfotypeVo infotypeVo) throws Exception{
        boolean result = infotypeService.insertSelective(infotypeVo);
        if (result) {
            logService.addLog(new TSysLog("招标信息类型-新增", "新增招标【" + infotypeVo.getInfotypeName() + "】成功 ！", PmStateConstant.LOG_PLATFORM));
        } else {
            logService.addLog(new TSysLog("招标信息类型-新增", "新增套餐【" + infotypeVo.getInfotypeName() + "】失败！", PmStateConstant.LOG_PLATFORM));
        }
        if (!result) {
            throw new ServiceException(InfoTypeExpCodeConstant.INFO_EDIT_COLUMN_ERROR);
        }
        return this.getResultMap(true, result);
    }
}

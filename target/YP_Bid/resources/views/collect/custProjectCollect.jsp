<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/resources/platform/inc.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <script src="${ctx.path}/resources/js/common/DataForm.js?v=${ctx.version}"></script>
    <script src="${ctx.path}/resources/script/collect/custCollect.js?v=${ctx.version}"></script>
    <script src="${ctx.path}/resources/components/My97DatePicker/WdatePicker.js?v=${ctx.version}"></script>
    <script type="text/javascript">
        $(function () {
            loadCriteria();
        });

        //加载查询条件
        function loadCriteria() {
        }
    </script>
    <script type="text/javascript">
        //加载查询条件
        function doSearch() {
            $("#pageForm").submit();
            top.progressbar(frameId);
        }
    </script>
    <style type="text/css">
        .hideTr {
            display: none;
        }
    </style>
</head>
<body>
<div class="main_con">
    <form class="tableform" action="${ctx.path}/manage/collect/project/queryProjectList${ctx.pageSuffix}" method="post"
          id="pageForm">
        <div class="operation_box" id="operation_box">
            <div class="operation_con">
                <div class="currenttit"></div>
                <div class="operation_info">
                    <p:auth mcode="MENU_MANAGE_COLLECT_PROJECT_EXPORT">
                        <button type="button" class="btn add_btn"
                                onclick="fun_export('${ctx.path}/manage/collect/project/exportProjectList${ctx.bizSuffix}')">
                            <i class="minicon exportoperation_icon"></i><span>导出</span>
                        </button>
                    </p:auth>
                </div>
            </div>
        </div>
        <div class="search_box" id="search_box">
            <div class="search_nav">
                <ul>
                    <li>
                        <input type="text" id="custCodeAndMobile" name="mobile" class="inputtext" placeholder="客户编号\手机号"
                               value="${projectCollectVo.mobile}"/>
                    </li>
                    <li>
                        <input type="text" id="projectTitle" name="projectTitle" class="inputtext" placeholder="项目标题"
                               value="${projectCollectVo.projectTitle}"/>
                    </li>
                </ul>
            </div>
            <div class="search_btncon">
                <p:auth mcode="MENU_MANAGE_COLLECT_PROJECT_SEARCH">
                    <button type="button" class="btn add_btn" onClick="doSearch()">
                        <i class="minicon search_icon"></i>
                        <span>查询</span>
                    </button>
                </p:auth>
                <button type="button" class="btn export_btn clearToolBtn">
                    <i class="minicon reload_icon"></i>
                    <span>重置</span>
                </button>
            </div>
        </div>

        <div class="tablelist_box tablelistboxH">
            <div class="tablelist_con">
                <div class="tablelist_theadbox">
                    <div class="tablelist_thead">
                        <table>
                            <tr>
                                <th width="50">序号</th>
                                <th width="80">客户编号</th>
                                <th width="100">手机号码</th>
                                <th width="100">会员类型</th>
                                <th width="120">对应招标信息类型</th>
                                <th width="120">对应招标行业类型</th>
                                <th width="100">项目标题</th>
                                <th width="80">收藏时间</th>
                                <th width="200" class="operation_th">操作</th>
                            </tr>
                        </table>
                    </div>
                </div>
                <div class="tablelist_tbody">
                    <table>
                        <c:if test="${empty pageData.data}">
                            <tr>
                                <td align="center" colspan="10">对不起，没有找到相关数据！</td>
                            </tr>
                        </c:if>
                        <c:forEach items="${pageData.data }" var="data" varStatus="staus">
                            <tr>
                                <td title="${((pageData.page-1)>0?pageData.page-1:0)*pageData.rows+(staus.index + 1) }">
                                        ${((pageData.page-1)>0?pageData.page-1:0)*pageData.rows+(staus.index + 1) }
                                </td>
                                <td title="${data.custCode }">${data.custCode }</td>
                                <td title="${data.mobile }">${data.mobile }</td>
                                <td title="${data.memberTypeCn }">${data.memberTypeCn }</td>
                                <td title="${data.infotypeName }">${data.infotypeName }</td>
                                <td title="${data.industryName}">${data.industryName }</td>
                                <td title="${data.projectTitle}">${data.projectTitle}</td>
                                <td title="<fmt:formatDate value='${data.collectDate }' pattern='yyyy-MM-dd HH:mm:ss'/>">
                                    <fmt:formatDate value='${data.collectDate }' pattern='yyyy-MM-dd HH:mm:ss'/>
                                </td>
                                <td class="operation_td">
                                    <p:auth mcode="MENU_MANAGE_COLLECT_PROJECT_VIEW">
                                        <button class="operationbtn" type="button" onclick="viewPage(${data.collectId});">
                                            <span>查看</span>
                                        </button>
                                    </p:auth>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
        <jsp:include page="/resources/common/page.jsp"></jsp:include>
    </form>
</div>

<!-- 查看 -->
<div id="viewId" class="add_box mCustomScrollbar_y" >
    <form method="post" id="viewForm">
        <div class="add_list">
            <h5>客户编号：</h5>
            <div class="add_value">
                <span id="custCode"></span>
            </div>
        </div>
        <div class="add_list">
            <h5>手机号码：</h5>
            <div class="add_value">
                <span id="mobile"></span>
            </div>
        </div>
        <div class="add_list">
            <h5>会员类型：</h5>
            <div class="add_value">
                <span id="memberTypeCn"></span>
            </div>
        </div>
        <div class="add_list">
            <h5>对应招标信息类型：</h5>
            <div class="add_value">
                <span id="infotypeName"></span>
            </div>
        </div>
        <div class="add_list">
            <h5>对应招标行业类型：</h5>
            <div class="add_value">
                <span id="industryName"></span>
            </div>
        </div>
        <div class="add_list">
            <h5>项目标题：</h5>
            <div class="add_value">
                <span id="projectTitle"></span>
            </div>
        </div>
        <div class="add_list">
            <h5>收藏时间：：</h5>
            <div class="add_value">
                <span id="collectDate"></span>
            </div>
        </div>
    </form>
</div>
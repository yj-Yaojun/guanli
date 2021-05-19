<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../base.jsp"%>
<!DOCTYPE html>
<html>

<head>
    <!-- 页面meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>数据 - AdminLTE2定制版</title>
    <meta name="description" content="AdminLTE2定制版">
    <meta name="keywords" content="AdminLTE2定制版">
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
    <!-- 页面meta /-->
    <script src="${ctx}/plugins/jQuery/jquery-2.2.3.min.js"></script>
</head>
<script>
    function deleteById() {
        var id = getCheckId();
        if(id) {
            if(confirm("你确认要删除此条记录吗？")) {
                location.href="${ctx}/system/user/delete.do?id="+id;
            }
        }else{
            alert("请勾选待处理的记录，且每次只能勾选一个")
        }
    }

    function getCheckId() {
        let ids = $("input[name='ids']:checked");
        let r = "";
        for(let i = 0; i < ids.length; i ++){
            if(i == ids.length - 1){
                r += ids[i].value;
            }else{
                r += ids[i].value + ",";
            }
        }
        return r;
    }
</script>
<body>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">
    <section class="content-header">
        <h1>
            用户管理
            <small>用户列表</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
        </ol>
    </section>
    <!-- 内容头部 /-->

    <!-- 正文区域 -->
    <section class="content">

        <!-- .box-body -->
        <div class="box box-primary">
            <div class="box-header with-border">
                <h3 class="box-title">列表</h3>
            </div>

            <div class="box-body">

                <!-- 数据表格 -->
                <div class="table-box">

                    <!--工具栏-->
                    <div class="pull-left">
                        <div class="form-group form-inline">
                            <div class="btn-group">
                                <button type="button" class="btn btn-default" title="新建" onclick='location.href="${ctx}/system/user/toAdd.do"'><i class="fa fa-file-o"></i> 新建</button>
                                <button type="button" class="btn btn-default" title="删除" onclick='deleteById()'><i class="fa fa-trash-o"></i> 删除</button>
                                <button type="button" class="btn btn-default" title="刷新" onclick="window.location.reload();"><i class="fa fa-refresh"></i> 刷新</button>
                            </div>
                        </div>
                    </div>
                    <div class="box-tools pull-right">
                        <div class="has-feedback">
                            <input type="text" class="form-control input-sm" placeholder="搜索">
                            <span class="glyphicon glyphicon-search form-control-feedback"></span>
                        </div>
                    </div>
                    <!--工具栏/-->

                    <!--数据列表-->
                    <table id="dataList" class="table table-bordered table-striped table-hover dataTable">
                        <thead>
                        <tr>
                            <th class="" style="padding-right:0px;">

                            </th>
                            <th class="sorting">序号</th>
                            <th class="sorting">用户名</th>
                            <th class="sorting">部门</th>
                            <th class="sorting">邮箱</th>
                            <th class="sorting">联系电话</th>
                            <th class="sorting">性别</th>
                            <th class="sorting">职位</th>
                            <th class="sorting">状态</th>
                            <th class="text-center">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${pageInfo.list}" var="item">
                            <tr>
                                <td><input name="ids" value="${item.id}" type="checkbox"></td>
                                <td>${status.index+1}</td>
                                <td><a href="${ctx}/system/user/toUpdate.do?id=${o.id}">${item.userName}</a></td>
                                <td>${item.deptName }</td>
                                <td>${item.email }</td>
                                <td>${item.telephone }</td>
                                <td>${item.gender ==0?'男':'女'}</td>
                                <td>${item.station }</td>
                                <td>${item.state  ==0?'停用':'启用'}</td>
                                <th class="text-center">
                                    <button type="button" class="btn bg-olive btn-xs" onclick='location.href="${ctx}/system/user/toUpdate.do?id=${item.id}"'>编辑</button>
                                </th>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <!-- /.box-body -->

            <!-- .box-footer-->
            <div class="box-footer">
                <div class="pull-left">
                    <div class="form-group form-inline">
                        总共${pageInfo.pages} 页，共${pageInfo.total} 条数据。 每页
                        <select class="form-control">
                            <option>10</option>
                            <option>15</option>
                            <option>20</option>
                            <option>50</option>
                            <option>80</option>
                        </select> 条
                    </div>
                </div>

                <div class="box-tools pull-right">
                    <ul class="pagination">
                        <li>
                            <a href="../user/list.do?page=1" aria-label="Previous">首页</a>
                        </li>
                        <li><a href="../user/list.do?page=${pageInfo.prePage}">上一页</a></li>
                        <c:forEach begin="1" end="${pageInfo.pages}" var="i">
                            <li><a href="../user/list.do?page=${i}">${i}</a></li>
                        </c:forEach>
                        <li><a href="../user/list.do?page=${pageInfo.nextPage}">下一页</a></li>
                        <li>
                            <a href="../user/list.do?page=${pageInfo.pages}" aria-label="Next">尾页</a>
                        </li>
                    </ul>
                </div>

            </div>


            <%--        <div class="box-footer">
                        <jsp:include page="../common/page.jsp">
                            <jsp:param value="${ctx}/system/user/list.do" name="pageUrl"/>
                        </jsp:include>
                    </div>--%>
            <!-- /.box-footer-->

        </div>
    </section>
</div>
</body>

</html>
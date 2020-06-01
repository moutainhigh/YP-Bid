var index = null;
var frameId = window.frameElement && window.frameElement.id || '';
var addValidate,editValidate;

//校验
$(function() {
    //编辑校验
    editValidate = $("#editForm").validate({
        rules : {
            projectKeywordEdit : {
                required : true
            }
        },
        messages : {
            projectKeywordEdit : {
                required : "请输入项目关键词！"
            }
        },
        submitHandler : function(form) {
            $(form).ajaxSubmit({
                //表单提交成功后的回调
                success : function(responseText, statusText, xhr, $form) {
                    if (responseText.rs > 0) {
                        top.toastr.success("编辑项目关键词信息【" + $("#editForm").find("#projectKeywordEdit").text() + "】成功！");
                        FormUtil.resetForm("editForm");
                        doSearch();
                        layer.close(index); //再执行关闭
                    }
                }
            });
        }
    });

    addValidate = $("#addForm").validate({
        rules : {
            projectKeywordAdd : {
                required : true
            }
        },
        messages : {
            projectKeywordAdd : {
                required : "请输入项目关键词！"
            }
        },
        submitHandler : function(form) {
            $(form).ajaxSubmit({
                //表单提交成功后的回调
                success : function(responseText, statusText, xhr, $form) {
                    if (responseText.rs > 0) {
                        top.toastr.success("新增项目关键词信息【" + $("#addForm").find("#projectKeywordAdd").text() + "】成功！");
                        FormUtil.resetForm("addForm");
                        doSearch();
                        layer.close(index); //再执行关闭
                    }
                }
            });
        }
    });

});


//打开查看页面
function viewPage(id) {
    if (Number(id)) {
        $.ajax({
            url: ctx.path + '/manage/dict/keyword/viewKeyWordList' + ctx.bizSuffix,
            async: true,
            dataType: 'json',
            type: 'POST',
            data: {projectKeywordId: id},
            success: function (data) {
                if (data.rs == -1) {
                    top.toastr.error("获取数据信息失败");
                    return false;
                }
                //返回的map对象参数
                var dataRet = data.data;
                for (var key in dataRet) {
                    //对页面属性赋值（要求页面id与map的key值保持一致）
                    $("#viewForm #" + key).html(dataRet[key]);
                }
                parentIndex = layer.open({
                    title: '查看',
                    type: 1,
                    area: ['450px', '500px'], //宽高
                    content: $('#viewId'),
                    btn: ['关闭'],
                    cancel: function (index) {
                    }
                });
            },
            error: function (jqXHR, textStatus, errorThrown) {
                top.toastr.error("操作失败");
            }
        });
    } else {
        top.toastr.error("数据异常！");
    }
}

//删除
function delWord(id) {
    if (Number(id)) {
        var msg = "确定删除该项目关键词吗？";
        layer.confirm(msg, {
            icon : 3,
            title : "提示信息"
        }, function(index) {
            $.get(ctx.path + "/manage/dict/keyword/delKeyWord"+ ctx.bizSuffix + "?projectKeywordId=" + id,function(result) {
                if (result.rs == 1) {
                    top.toastr.success("删除成功！");
                    doSearch();
                    //刷新表单
                }
            }, "json");
            layer.close(index);
        });
    } else {
        top.toastr.error("数据异常！");
    }
}

//打开编辑页面
function editPage(id) {
    if (Number(id)) {
        $.ajax({
            url : ctx.path + '/manage/dict/keyword/viewKeyWordList' + ctx.bizSuffix,
            async : true,
            dataType : 'json',
            type : 'POST',
            data : {
                projectKeywordId : id
            },
            success : function(data) {
                if (data.rs == -1) {
                    top.toastr.error("操作失败");
                    return false;
                }
                var dataRet = data.data;
                for (var key in dataRet) {
                    //对页面属性赋值（要求页面id与map的key值保持一致）
                    $("#editForm #"+ key +"Edit").val(dataRet[key]);
                }
                $("#editForm #projectKeywordEdit").text(dataRet.projectKeyword);
                $("#editForm #orderNumEdit").text(dataRet.orderNum);
                parentIndex = layer.open({
                    title : '编辑项目关键词信息',
                    type : 1,
                    area : [ '450px', '350px' ], //宽高
                    btn : [ '保存', '关闭' ],
                    yes : function(index, layero) {
                        $("#editForm").submit();
                    },
                    content : $('#editId'),
                    cancel : function(index) {
                        FormUtil.resetForm("editForm");
                        editValidate.resetForm();
                    }
                });
            },
            error : function(jqXHR, textStatus, errorThrown) {
                top.toastr.error("操作失败");
            }
        });

    } else {
        top.toastr.error("数据异常！");

    }

}

//新增
function openAddPage() {
    //获取所有部门
    index = layer.open({
        type: 1,
        title: '新增项目关键词',
        move: false,
        area: ['420px', '300px'], //宽高
        content: $('#addProjectKeyword'),
        btn: ['保存', '关闭'],
        yes: function (index, layero) {
            $("#addForm").submit();
        },
        cancel: function (index) {
            FormUtil.resetForm("addForm");
            addValidate.resetForm();
        }
    })
}
layui.use(['form','layer','table','laytpl','upload'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        upload = layui.upload,
        table = layui.table;
    var opTable = 0;//记录目前是哪个数据table显示
    
    //majorList列表
    var tableIns = null;
    loadMajor();
    function loadMajor() {
      tableIns = table.render({
        elem: '#majorList',
        url : '../major/list.html',
        request: {
        	pageName: 'curr' //页码的参数名称
        	,limitName: 'nums' //每页数据量的参数名
        },
        where: {
            searchKey: $(".searchVal").val()  //搜索的关键字
        },
        cellMinWidth : 95,
        page : true,
        height : "full-100",
        limits : [10,15,20,25],
        limit : 10,
        loading : true,
        id : "majorListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'id', title: '专业ID', sort:true, minWidth:80, align:"left"},
            {field: 'majorNum', title: '专业编号', sort:true, minWidth:80, align:"left"},
            {field: 'name', title: '专业名',minWidth:120, align:'left'},
            {field: 'collegeName', title: '所属学院',minWidth:120, align:'left'},
            {field: 'synopsis', title: '专业简介', minWidth:100, align:'left'},
            {title: '操作', width:160, templet:'#majorListBar',fixed:"right",align:"center"}
        ]]
      });
    }
    
    //collegeList列表
    var collegeTableIns = null;
    function loadCollege() {
      collegeTableIns = table.render({
        elem: '#majorList',
        url : '../college/list.html',
        request: {
        	pageName: 'curr' //页码的参数名称
        	,limitName: 'nums' //每页数据量的参数名
        },
        where: {
            searchKey: $(".searchVal").val()  //搜索的关键字
        },
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        loading : true,
        id : "collegeListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'id', title: '学院ID', sort:true, minWidth:80, align:"left"},
            {field: 'name', title: '学院名',minWidth:120, align:'left'},
            {field: 'synopsis', title: '学院简介', minWidth:100, align:'left'},
            {title: '操作', width:160, templet:'#majorListBar',fixed:"right",align:"center"}
        ]]
      });
    }

    //搜索
    $(".search_btn").on("click",function(){
    	//对major的搜索
    	if ($("#searchType").val() == 0) {
    		opTable = 0;
    		if (tableIns == null) {
    			loadMajor();
    		} else {
        		tableIns.reload({
        			page: {
        				curr: 1 //重新从第 1 页开始
        			},
        			where: {
        				searchKey: $(".searchVal").val()  //搜索的关键字
        			}
        		});
    		}
    	} else {
    		opTable = 1;
    		if (collegeTableIns == null) {
    			loadCollege();
    		} else {
        		collegeTableIns.reload({
        			page: {
        				curr: 1
        			},
        			where: {
        				searchKey: $(".searchVal").val()
        			}
        		});
    		}
    	}
    });

    //按回车搜索
    $(document).keydown(function (event) {//阻止页面刷新
        if (event.keyCode == 13) {
            $("*").blur();//去掉焦点
        }
    });
    $(".searchVal").on("keydown", function (event){
        if (event.keyCode == "13") {
            $(".search_btn").click();
        }
    });

    //编辑or添加开设课程
    function addMajor(edit){
        var title;
        if (edit) {
            title = "编辑开设专业";
        }
    	title = "新增开设专业";
        var index = layui.layer.open({
            title : title,
            type : 2,
            content : "../major/addPage.html",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                    body.find("#id").val(edit.id);
                    body.find(".name").val(edit.name);
                    var select = body.find("#collegeSelect");
                    select.html("<option id='defaultCollege' value='"+edit.collegeId+"' >"+edit.collegeName+"</option>");
                    body.find(".majorNum").val(edit.majorNum);
                    body.find("#synopsis").val(edit.synopsis);
                    body.find('#reset').attr("disabled","disabled").addClass("layui-disabled");
                    form.render();
                }
                setTimeout(function(){
                    layui.layer.tips('点击此处返回专业列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500);
            }
        });
        layui.layer.full(index);
        window.sessionStorage.setItem("index",index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize",function(){
            layui.layer.full(window.sessionStorage.getItem("index"));
        });
    }
    
    function addCollege(edit){
    	var title = "新增基本学院";
    	if (edit) title = "编辑基本学院";
        var index = layui.layer.open({
            title : title,
            type : 2,
            area: ['500px','380px'],
            content : "../college/addPage.html",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                    body.find("#opType").val("1");
                    body.find("#id").val(edit.id);
                    body.find("#name").val(edit.name);
                    body.find("#synopsis").val(edit.synopsis);
                    body.find('#reset').attr("disabled","disabled").addClass("layui-disabled");
                    form.render();
                }else{
                    body.find("#opType").val("0");
                }
                setTimeout(function(){
                    layui.layer.tips('点击此处返回学院列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500);
            }
        });
    }
    //添加
    $(".addMajor_btn").click(function(){
        addMajor();
    });
    $("#addCollege").click(function(){
    	addCollege();
    });
    

    //批量导入专业
    var uploadInst = upload.render({
        elem: '#upload_btn' //绑定元素
        ,url: '../major/import.html' //上传接口
        ,accept: 'file'
        ,exts:'xls|xlsx'
        ,before: function(obj){
            layer.load(); //上传loading
        }
        ,done: function(res){
        	layer.closeAll('loading'); //关闭loading
        	if (opTable == 0)  {
                tableIns.reload();
			}
        }
        ,error: function(){
        	layer.closeAll('loading'); //关闭loading
        }
    });
    //批量删除
    $(".delAll_btn").click(function(){
    	var checkStatus;
    	var url;
        if (opTable == 0)  {
        	checkStatus = table.checkStatus('majorListTable');
        	url = '../major/deleteList.html';
        } else {
        	checkStatus = table.checkStatus('collegeListTable');
        	url = '../college/deleteList.html';
        }
        
        var data = checkStatus.data, ids = "";
        if(data.length > 0) {
            for (var i in data) {
            	ids += data[i].id + ",";
            }
            layer.confirm('确定删除选中的信息？', {icon: 3, title: '提示信息'}, function (index) {
            	$.ajax({
            		type: 'get',
            		data: {
                        ids : ids
            		},
                    dataType: "json",
            		url: url,
            		timeout:2000,
            		success:function(rs) {
                    	if (rs.msg == "true") {
                    		if (opTable == 0)  {
            					tableIns.reload({page: {
                                    curr: 1 //重新从第 1 页开始
                                }});
            				} else {
            					collegeTableIns.reload({page: {
                                    curr: 1 //重新从第 1 页开始
                                }});
            				}
                    	} else {
                    		layer.msg(rs.msg, {icon: 5,time:1000});
                    	}
                    	layer.close(index);
            		},
            		error:function() {
            			layer.msg("操作失败！", {icon: 5,time:1000});
            			layer.close(index);
            		}
            	});
            });
        }else{
            layer.msg("请选择需要删除的行");
        }
    });

    //监听select
    form.on("select(searchType)",function (){
        if($("#searchType").val() == '0'){
            opTable = 0;
            loadMajor();
        }else{
            opTable = 1;
            loadCollege();
        }
        return false;
    });

    //列表操作
    table.on('tool(majorList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;
        if(layEvent === 'edit'){ //编辑
            if (opTable == 0)  {
            	addMajor(data);
            } else {
            	addCollege(data);
            }
        } else if(layEvent === 'del'){ //删除
        	var url;
        	if (opTable == 0)  {
        		url = '../major/delete.html';
            } else {
            	url = '../college/delete.html';
            }
            layer.confirm('确定删除？',{icon:3, title:'提示信息'},function(index){
            	$.ajax({
            		type: 'get',
            		data: {
            			id: data.id,
            		},
                    dataType: "json",
            		url: url,
            		success:function(rs) {
            			if (rs.msg == "true") {
            				if (opTable == 0)  {
            					tableIns.reload();
            				} else {
            					collegeTableIns.reload();
            				}
        	                layer.close(index);
        				}else {
        					layer.msg(rs.msg, {icon: 5,time:1000});
        				}
            		}
            	});
            });
        }
    });
    
});

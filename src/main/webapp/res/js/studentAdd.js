layui.config({
	base: "/SMS/res/layui/lay/mymodules/ajaxCascader/",version: '1.6'
}).use(['form','layer','laydate',"jquery","ajaxCascader"],function(){
    var form = layui.form,
    	laydate = layui.laydate,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;
		cascader = layui.ajaxCascader;

    laydate.render({
        elem: '#admissionDate', //渲染日历input
		trigger: 'click'
    });




    //自动获取学号
	function autoCreateSid(){
		$.get({
			url:"/SMS/student/getAutoSid.html",
			type:"get",
			dataType: "json",
			data:{
				admissionDate: $('#admissionDate').val(),
				grade:$("#grade").val(),
				academicStatus: cascader.getChooseData()[0],//学院id，临时用学业状态替代
				name: $(".cascader-choose-active:eq(1)").text() //专业名称，临时用学生名称替代
			},
			success:function (rs){
				$(".id").val(rs.data);
				$(".id").attr("readOnly","readOnly").addClass("layui-disabled");
			}
		});
	}

	//专业级联选择器
	cascader.load({

		elem: "#major",
		placeholder: $("#majorName").val(),
		//data: data,
		// type: "post",
		// triggerType: "change",
		showLastLevels: true,
		search: {
			show: true,
			// minLabel: 1,
			// placeholder: '请输入搜索词'
		},
		showlast:true,                           //【可选】是否只显示最后一级 【默认：false】
		clicklast:true,			      //【可选】点击最后一级才选择数据
		disabled:false,                           //【可选】是否禁用当前组件
		clear:true,                               // 【1.6新增】【可选】是否显示清空功能
		getChildren:function(value,callback) {
			var data = {};
			$.ajax({
				url: "/SMS/major/getCascader.html",
				type: 'get',
				dataType: "json",
				success: function (res) {
					data = res.data;
					for (var i in data) {
						data[i].value = data[i].id;
						data[i].label = data[i].name;
						delete data[i].id;
						delete data[i].name;
						for(let j in data[i].children){
							data[i].children[j].value = data[i].children[j].id;
							data[i].children[j].label = data[i].children[j].name;
							delete data[i].children[j].id;
							delete data[i].children[j].name;
						}
						data[i].hasChild = true;
					}
					callback(data);
				}
			})
		}
	});
	//输入框的值改变时触发
	form.on("select(autoSid)",function (){
		if ($('#admissionDate').val() != '' && cascader.getChooseData()[1] != null && $("#grade").val() != '' && $("#opType").val() != "1"){
			autoCreateSid();
		}
		return false;
	})
	$(".autoSid").on("blur",function(){
		if ($('#admissionDate').val() != '' && cascader.getChooseData()[1] != null && $("#grade").val() != '' && $("#opType").val() != "1"){
			autoCreateSid();
		}
	});
	cascader.on('click', '#major', function(){
		if ($('#admissionDate').val() != '' && cascader.getChooseData()[1] != null && $("#grade").val() != '' && $("#opType").val() != "1"){
			autoCreateSid();
		}
	});



    form.on("submit(addStudent)",function(data){
    	var pwd = $('#re_password').val();
    	if (pwd != $('#password').val()) {
    		layer.msg('密码不一致！', {icon: 5,time:1000});
    		return false;
    	}
    	pwd = hex_md5(pwd);

    	if($(".id").val() == ''){
			layer.msg('请完善资料！', {icon: 5,time:1000});
    	}
		if($("#grade").val() == '0'){
			layer.msg('请选择班级！', {icon: 5,time:1000});
		}
    	//弹出loading
        var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
    	$.ajax({
    		type: 'post',
    		url: '/SMS/student/add.html',
			dataType: "json",
    		data: {
    			opType: $("#opType").val(),
				id : $(".id").val(),
	            name : $(".name").val(),
	            sex : $("input[type='radio']:checked").val(),
	            password : pwd,
	            admissionDate : $('#admissionDate').val(),
	            majorId : cascader.getChooseData()[1],
	            grade : $("#grade").val(),
				academicStatus:$(".academicStatus").val()
			},
			timeout:2000,
    		success:function(rs) {
				top.layer.close(index);
				if (rs.msg == "true") {
					if ($("#opType").val() == 1) {//是修改操作
						//修改成功则刷新父页面
						layer.closeAll("iframe");
						parent.location.reload();
					} else {
						top.layer.msg("操作成功！");
						parent.location.reload();
					}
				}else {
					layer.msg(rs.msg, {icon: 5,time:1000});
				}
			},
    		error:function() {
    			layer.msg("操作失败！", {icon: 5,time:1000});
    			layer.close(index);
    		}
    	});
        return false;
    });
});
layui.use(['form','layer','laydate'],function(){
    var form = layui.form,
    	laydate = layui.laydate,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;
    
    laydate.render({ elem: '#startDate',trigger: 'click' });
    laydate.render({ elem: '#endDate',trigger: 'click' });

    var inputObj1 = $("#baseCourseDiv").find("input");
	form.on("select(baseCourseSelect)",function (){
		loadTeacher();
		return false;
	});
    function loadCourse(date) {
      	$.ajax({
      		type: 'get',
      		url: '/SMS/basecourse/listForSelect.html',
      		data:{},
			dataType: "json",
      		timeout:1500,
      		success:function(rs) {
      			var select = $('#baseCourseSelect');
      			var options = "";
      			var data = rs.data;
				options += "<option value='0'></option>";
      			for (var i in data) {
      				options += "<option value='"+data[i].id+"'>"+data[i].name+"</option>";
      			}
      			select.html(options);
				$("#baseCourseSelect").val(date).attr("checked","checked");
      			form.render('select');
      		}
      	});
    }

	function loadMajor(date){
		$.ajax({
			type: "post",
			url: '/SMS/major/listForSelect.html',
			data:{},
			dataType: "json",
			success: function (rs) {
				if (rs.msg == "true") {
					var list = rs.data;
					var s = '';
					$.each(list, function (i, n) {
						s = s + '<option value="' + n.id + '">' + n.name + '</option>';
					});
					$("#majorSelect").html(s);
					$("#majorSelect").val(date).attr("checked","checked");
					form.render('select');
				}
			}
		});
	}
	loadMajor($("#defaultMajorId").val());

	function loadTeacher(date) {
		inputObj1 = $("#baseCourseDiv").find("input");//重新获取
		var searchKey = inputObj1.val();
		$.ajax({
			type: 'get',
			url: '/SMS/teacher/listForSelect.html',
			data:{searchKey: searchKey},
			dataType: "json",
			timeout:1500,
			success:function(rs) {
				var select = $('#teacherSelect');
				var options = "";//select.html();
				var data = rs.data;
				options += "<option value='0'></option>";
				for (var i in data) {
					options += "<option value='"+data[i].id+"'>"+data[i].name+"</option>";
				}
				select.html(options);
				$("#teacherSelect").val(date).attr("checked","checked");
				form.render('select');
			}
		});
	}
	//编辑数据时重新加载select
	$(document).ready(function (){
		loadCourse($("#defaultCourse").val());
		loadTeacher($("#defaultTeacher").val());
	});




    
    form.on("submit(addCourse)",function(data){
    	//弹出loading
        var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
    	var id = $("#id").val();
        $.ajax({
    		type: 'post',
    		url: '../course/add.html',
    		data: {
				id : id,
	            tId : $("#teacherSelect").val(),
	            baseCourseId: $("#baseCourseSelect").val(),
	            startDate: $("#startDate").val(),
	            endDate: $("#endDate").val(),
	            classHour: $("#classHour").val(),
	            testMode : $("#testMode").val(),
	            studentNum : $("#studentNum").val(),
				majorId:$("#majorSelect").val()
			},
			dataType: "json",
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
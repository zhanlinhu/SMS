layui.use(['form','layer'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

	var count = -1;
	form.verify({
		majorNumVer: function(value){ //自定义lay-verify的昵称，value：表单的值
			if(!(value > 0 && value < 10)) {
				return "专业编号为一位正整数！";
			}
			if($("#id").val() == ''){
				$.ajax({
					type: 'get',
					async:false,
					url: '/SMS/major/getCount.html',
					data:{majorNum: $(".majorNum").val(),collegeId:$("#collegeSelect").val()},
					dataType: "json",
					success:function(rs) {
						if(rs.msg == "true"){
							count = rs.data;
						}
					}
				});
				if(count != -1 && count != 0){
					return "专业编号重复！";
				}
			}
		}
	});

    function loadCollege(date) {
      	$.ajax({
      		type: 'get',
      		url: '/SMS/college/listForSelect.html',
      		data:{},
			dataType: "json",
      		timeout:1500,
      		success:function(rs) {
      			var select = $('#collegeSelect');
      			var options = "";
      			var data = rs.data;
				options += "<option value='0'></option>";
      			for (var i in data) {
      				options += "<option value='"+data[i].id+"'>"+data[i].name+"</option>";
      			}
      			select.html(options);
				$("#collegeSelect").val(date).attr("checked","checked");
      			form.render('select');
      		}
      	});
    }

	//编辑数据时重新加载select
	$(document).ready(function (){
		loadCollege($("#defaultCollege").val());
	});


    form.on("submit(addMajor)",function(data){

    	//弹出loading
        var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
    	var id = $("#id").val();
        $.ajax({
    		type: 'post',
    		url: '/SMS/major/add.html',
    		data: {
				id : id,
	            majorNum : $(".majorNum").val(),
	            name: $(".name").val(),
				synopsis: $("#synopsis").val(),
				collegeId: $("#collegeSelect").val()
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
						parent.location.reload();
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
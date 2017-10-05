/**
 * 用户管理
 */

function userConfig(){
	
	var self=this;
	var adminTable;
	
	this.init=function(){
		
      $('#addAdminBtn').bind('click',function(){
//        	self.clearFrom();
        	self.listRole(0);
        	$("#role_title").text("添加管理员");
        	$("#addAdminDialog").modal("show");
		});
        
        $('#saveAdminBtn').bind('click',function(){
        	self.saveAdmin();
        });
        
        //self.listAdmin();
	}
	
	this.saveAdmin=function(){

		var adminId = $("#adminId").val();
		var state = $("#state").val();
		var name = $.trim($("#name").val());
		var password = $.trim($("#password").val());
		
		if(name==null || name==""){
			layer.msg('名称不能为空！', {icon: 7});
			return;
		}
		
		var roles=new Array()
		
		$.each($(".icheckbox-blue"), function(index, itemobj) {
			var size = $(this).attr("class").length;
			if(size == 22){
				 var id = $(this).parent().attr("data-id");
				 roles.push(id);
			}
		});
		
		if(roles.length == 0){
			layer.msg('角色不能为空！', {icon: 7});
			return;
		}
		
		var admin={};
		admin.id = adminId;
		admin.state = state;
		admin.userName = name;
		admin.password = password;
		admin.roles = roles;
		
		$.ajax({
			url:'/admin/saveAdmin',
            type: "POST",
            dataType: "json",//跨域ajax请求,返回数据格式为json
            cache: false,
            timeout: 10000,//请求超时时间,单位为毫秒
            async: true,
            global: false,//禁用Jquery全局事件
            scriptCharset: 'UTF-8',
            //processData : false,         // 告诉jQuery不要去处理发送的数据
            contentType: 'application/json;charset=UTF-8',//请求内容的MIMEType
			data:JSON.stringify(admin),
			success:function(responseData, status){
				if(responseData.data==1){
					adminTable.ajax.reload();
					$("#addAdminDialog").modal("hide");
					layer.msg('操作成功！', {icon: 1});
				}else if(responseData.data==-1){
					layer.msg('不能有相同名称,请修改！', {icon: 7});
				}else{
					layer.msg('操作失败！', {icon: 5});
				}
			}
		});
	}
	
	this.listAdmin=function(){
		adminTable=$('#adminList').DataTable({
			 "processing": true,
			 "ordering": false,
			 "searching":false,
		     "serverSide": true,
		     "ajax": {
		        "url":"/admin/adminList",
		        "type": "POST"
			 },
		     "dataType":"json",
		     "aLengthMenu": [10, 20, 30],
		     "columns": [
				    	 {"data": "userName"},
				    	 {"data": "roleName"},
				    	 {"data": "b","render":function( data, type, row ) {
			                	if(row.state == 1){
			                		return "<span class=\"label label-success radius\">正常</span>";
			                	}else{
			                		return "<span class=\"label label-warning radius\">冻结</span>";
			                	}
			                }},
		                 {"data": "createTime"},
		                 {"data": "b","render":function( data, type, row ) {
			                	return '<input class="btn btn-secondary-outline radius" type="button" onClick="admin_config.updateAdmin(\''+row.id+'\',\''+row.userName+'\',\''+row.state+'\')" value="修改">&nbsp;&nbsp;'+
					                   '<input class="btn btn-success-outline radius" type="button" onClick="admin_config.deleteAdmin(\''+row.id+'\')" value="删除">';
		                 }},
		              ]
		});
	}
	
	this.listRole=function(id){
		$.post("/admin/roleList",{},function(data){
		
			if(data.success){
				var result = data.data;
				var htmlContent="";
				$.each(result, function(index, itemobj) {
					
					var id=result[index].id;  
					var name=result[index].name;
					
					htmlContent += "<div class=\"check-box\" data-id="+id+">";
						htmlContent += "<input type=\"checkbox\" id='checkbox-"+id+"'>";
						htmlContent += "<label for='checkbox-"+id+"'>"+name+"</label>";
					htmlContent += "</div>";
					
				});
				$(".skin-minimal").html(htmlContent);
				$('.skin-minimal input').iCheck({
		    		checkboxClass: 'icheckbox-blue',
		    		radioClass: 'iradio-blue',
		    		increaseArea: '20%'
		    	});
				
				if(id!=0){
					self.listAdminRole(id);
				}
				
			} else{
				layer.msg('程序异常！', {icon: 5});
			}
		});
	}
	
	this.listAdminRole=function(id){
		$.post("/admin/adminRoleList",{"id":id},function(data){
			if(data.success){
				var result = data.data;
				$.each(result, function(index, itemobj) {
					var roleId=result[index].roleId;  
					$("#checkbox-"+roleId).attr("checked", true);
				});
				$('.skin-minimal input').iCheck({
		    		checkboxClass: 'icheckbox-blue',
		    		radioClass: 'iradio-blue',
		    		increaseArea: '20%'
		    	});
			} else{
				layer.msg('程序异常！', {icon: 5});
			}
		});
	}
	
	this.deleteAdmin=function(id){
		layer.confirm('确定删除吗？', {
			  btn: ['确认','取消'] //按钮
			}, function(){
				$.post("/admin/delAdmin",{"id":id},function(data){
					if(data.success){
						adminTable.ajax.reload();
						layer.msg('操作成功！', {icon: 1});
					} else{
						layer.msg('操作失败！', {icon: 5});
					}
				});
			}, function(){
			  
		});
	}
	
	this.updateAdmin=function(id,name,state){
		$("#role_title").text("修改管理员");
    	$("#addAdminDialog").modal("show");
    	$("#adminId").val(id);
    	$("#state").val(state);
		$("#name").val(name);
		self.listRole(id);
	}
	
	this.clearFrom=function(){
    	$("#adminId").val("");
    	$("#state").val("");
		$("#name").val("");
		$("#roleDesc").val("");
	}
	
	self.init();
	
}


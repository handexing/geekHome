/**
 * 管理员管理
 */

function adminConfig(){
	
	var self=this;
	var adminTable;
	
	this.init=function(){
		
      /*  $('#addRoleBtn').bind('click',function(){
        	self.clearFrom();
        	$("#role_title").text("添加角色");
        	$("#addRoleDialog").modal("show");
		});
        
        $('#saveRoleBtn').bind('click',function(){
        	self.saveRole();
        });*/
        
        self.listAdmin();
	}
	
	this.saveRole=function(){

		var id = $("#roleId").val();
		var state = $("#state").val();
		var name = $.trim($("#name").val());
		var roleDesc = $.trim($("#roleDesc").val());
		var oldName = $.trim($("#oldName").val());
		
		if(name==null || name==""){
			layer.msg('角色名称不能为空！', {icon: 7});
			return;
		}
		
		if(name == oldName){
			layer.msg('请修改之后再提交！', {icon: 7});
			return;
		}
		
		if(roleDesc==null || roleDesc==""){
			layer.msg('角色描述不能为空！', {icon: 7});
			return;
		}
		
		var role={};
		role.id = id;
		role.state = state;
		role.name = name;
		role.roleDesc = roleDesc;
		
		$.ajax({
			url:'/role/saveRole',
            type: "POST",
            dataType: "json",//跨域ajax请求,返回数据格式为json
            cache: false,
            timeout: 10000,//请求超时时间,单位为毫秒
            async: true,
            global: false,//禁用Jquery全局事件
            scriptCharset: 'UTF-8',
            //processData : false,         // 告诉jQuery不要去处理发送的数据
            contentType: 'application/json;charset=UTF-8',//请求内容的MIMEType
			data:JSON.stringify(role),
			success:function(responseData, status){
				if(responseData.data==1){
					roleTable.ajax.reload();
					$("#addRoleDialog").modal("hide");
					layer.msg('操作成功！', {icon: 1});
				}else if(responseData.data==-1){
					layer.msg('不能有相同角色名称,请修改！', {icon: 7});
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
			                	return '<input class="btn btn-secondary-outline radius" type="button" onClick="role_config.updateRole(\''+row.id+'\',\''+row.name+'\',\''+row.roleDesc+'\',\''+row.state+'\')" value="修改">&nbsp;&nbsp;'+
					                   '<input class="btn btn-success-outline radius" type="button" onClick="role_config.deleteRole(\''+row.id+'\')" value="删除">';
		                 }},
		              ]
		});
	}
	
	this.deleteRole=function(id){
		layer.confirm('确定删除吗？', {
			  btn: ['确认','取消'] //按钮
			}, function(){
				$.post("/role/delRole",{"id":id},function(data){
					if(data.success){
						roleTable.ajax.reload();
						layer.msg('操作成功！', {icon: 1});
					} else{
						layer.msg('操作失败！', {icon: 5});
					}
				});
			}, function(){
			  
		});
	}
	
	this.updateRole=function(id,name,desc,state){
		self.clearFrom();
		$("#role_title").text("修改角色");
    	$("#addRoleDialog").modal("show");
    	$("#roleId").val(id);
    	$("#state").val(state);
		$("#name").val(name);
		$("#oldName").val(name);
		$("#roleDesc").val(desc);
	}
	
	this.clearFrom=function(){
    	$("#roleId").val("");
    	$("#state").val("");
		$("#name").val("");
		$("#roleDesc").val("");
	}
	
	self.init();
	
}


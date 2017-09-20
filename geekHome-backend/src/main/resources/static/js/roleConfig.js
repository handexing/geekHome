/**
 * 角色管理
 */

function roleConfig(){
	
	var self=this;
	var roleTable;
	
	this.init=function(){
		
        $('#addRoleBtn').bind('click',function(){
        	$("#role_title").text("添加角色");
        	$("#addRoleDialog").modal("show");
		});
        
        $('#saveRoleBtn').bind('click',function(){
        	self.saveRole();
        });
        
        self.listRole();
	}
	
	this.saveRole=function(){

		var state = $("#state").val();
		var name = $.trim($("#name").val());
		var roleDesc = $.trim($("#roleDesc").val());
		
		if(name==null || name==""){
			layer.msg('角色名称不能为空！', {icon: 7});
			return;
		}
		
		if(roleDesc==null || roleDesc==""){
			layer.msg('角色描述不能为空！', {icon: 7});
			return;
		}
		
		var role={};
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
				if(responseData.success){
					roleTable.ajax.reload();
					$("#addRoleDialog").modal("hide");
					layer.msg('操作成功！', {icon: 1});
				}else{
					layer.msg('操作失败！', {icon: 5});
				}
			}
		});
	}
	
	this.listRole=function(){
		roleTable=$('#roleList').DataTable({
			 "processing": true,
			 "ordering": false,
			 "searching":false,
		     "serverSide": true,
		     "ajax": {
		        "url":"/role/roleList",
		        "type": "POST"
			 },
		     "dataType":"json",
		     "aLengthMenu": [10, 20, 30],
		     "columns": [
				    	 {"data": "name"},
				    	 {"data": "roleDesc"},
				    	 {"data": "b","render":function( data, type, row ) {
			                	if(row.state == 1){
			                		return "<span class=\"label label-success radius\">正常</span>";
			                	}else{
			                		return "<span class=\"label label-warning radius\">冻结</span>";
			                	}
			                }},
		                 {"data": "createTime"},
		                 {"data": "b","render":function( data, type, row ) {
			                	return '<input class="btn btn-primary-outline radius" type="button" value="授权">&nbsp;&nbsp;'+
					                   '<input class="btn btn-secondary-outline radius" type="button" value="修改">&nbsp;&nbsp;'+
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
	
	self.init();
	
}


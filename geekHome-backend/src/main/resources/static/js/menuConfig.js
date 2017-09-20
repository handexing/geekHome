/**
 * 菜单管理
 */

function menuConfig(){
	
	var self=this;
	
	this.init=function(){
		
        $('#addMenuBtn').bind('click',function(){
        	self.clearFrom();
        	$("#menu_title").text("添加资源");
        	$("#addMenuDialog").modal("show");
		});
        
        $('#saveMenuBtn').bind('click',function(){
        	self.saveMenu();
        });
		
        self.listMenu();
	}
	
	//获取menuList
	this.listMenu=function(){
		$.post("/menu/menuList",{},function(data){
			if(data.success){
				
				var result =  data.data;
				var htmlContent="";
				
				htmlContent+="<tr><th>菜单名称</th><th>权限标识</th><th>菜单URL</th><th>菜单类型</th><th>排序</th><th>创建时间</th><th>操作</th></tr>";
				
				$.each(result, function(index, itemobj) {
					var id=result[index].id;  
					var name=result[index].name;
					var type=result[index].type;
					var url=result[index].url;
					var code=result[index].code;
					var createTime=result[index].createTime;
					var parentId=result[index].parentId;
					var sort=result[index].sort;
					
					if(parentId == 0){
						htmlContent += "<tr class=\"treegrid-"+id+"\" id="+id+">";
							htmlContent += "<td>"+name+"</td>";
							htmlContent += "<td>"+code+"</td>";
							htmlContent += "<td>"+url+"</td>";
							if(type == "menu"){
								htmlContent += "<td><span class=\"label label-primary radius\">仅菜单</span></td>";
							} else if(type == "auth"){
								htmlContent += "<td><span class=\"label label-secondary radius\">菜单权限</span></td>";
							} else if(type == "button"){
								htmlContent += "<td><span class=\"label label-success radius\">按钮权限</span></td>";
							}
							htmlContent += "<td><input class=\"input-text\" type=\"text\" onclick=\"menu_config.updateSort()\" data-id="+id+" name=\"sort\" value="+sort+" /></td>";
							htmlContent += "<td>"+createTime+"</td>";
							htmlContent +="<td>" +
											"<input class=\"btn btn-primary-outline radius\" type=\"button\" onclick=\"menu_config.addChildMenu("+id+")\" value=\"添加子菜单\">&nbsp;&nbsp;" +
											"<input class=\"btn btn-secondary-outline radius\" type=\"button\" onclick=\"menu_config.updateMenu("+id+")\" value=\"修改\">&nbsp;&nbsp;" +
											"<input class=\"btn btn-success-outline radius\" type=\"button\" onclick=\"menu_config.deleteMenu("+id+")\" value=\"删除\">" +
										"</td>";
						htmlContent += "</tr>";
					}else{
						htmlContent += "<tr class=\"treegrid-"+id+" treegrid-parent-"+parentId+"\" id="+id+">";
							htmlContent += "<td>"+name+"</td>";
							htmlContent += "<td>"+code+"</td>";
							htmlContent += "<td>"+url+"</td>";
							if(type == "menu"){
								htmlContent += "<td><span class=\"label label-primary radius\">仅菜单</span></td>";
							} else if(type == "auth"){
								htmlContent += "<td><span class=\"label label-secondary radius\">菜单权限</span></td>";
							} else if(type == "button"){
								htmlContent += "<td><span class=\"label label-success radius\">按钮权限</span></td>";
							}
							htmlContent += "<td><input class=\"input-text\" type=\"text\" onclick=\"menu_config.updateSort()\" data-id="+id+" name=\"sort\" value="+sort+" /></td>";
							htmlContent += "<td>"+createTime+"</td>";
							htmlContent +="<td>" +
											"<input class=\"btn btn-primary-outline radius\" type=\"button\" onclick=\"menu_config.addChildMenu("+id+")\" value=\"添加子菜单\">&nbsp;&nbsp;" +
											"<input class=\"btn btn-secondary-outline radius updateMenuBtn\" type=\"button\" onclick=\"menu_config.updateMenu("+id+")\" value=\"修改\">&nbsp;&nbsp;" +
											"<input class=\"btn btn-success-outline radius\" type=\"button\" onclick=\"menu_config.deleteMenu("+id+")\" value=\"删除\">" +
										"</td>";
						htmlContent += "</tr>";
					}
					
				});
				
				$("#menuGrid").html(htmlContent);
				$("#menuGrid").treegrid({
		            expanderExpandedClass: 'Hui-iconfont Hui-iconfont-add',
		            expanderCollapsedClass: 'Hui-iconfont Hui-iconfont-jianhao'
		        });
			}
		});
	}
	
	//保存menu
	this.saveMenu=function(){

		var id = $("#menuId").val();
		var parentId = $("#parentId").val();
		var type = $("#type").val();
		var name = $.trim($("#name").val());
		var code = $.trim($("#code").val());
		var url = $.trim($("#url").val());
		
		if(name==null || name==""){
			layer.msg('资源名称不能为空！', {icon: 7});
			return;
		}
		
		if(code==null || code==""){
			layer.msg('权限标识不能为空！', {icon: 7});
			return;
		}
		
		if(url==null || url==""){
			layer.msg('资源URL不能为空！', {icon: 7});
			return;
		}
		
		var menu={};
		menu.id = id;
		menu.parentId = parentId;
		menu.type = type;
		menu.name = name;
		menu.code = code;
		menu.url = url;
		
		$.ajax({
			url:'/menu/saveMenu',
            type: "POST",
            dataType: "json",//跨域ajax请求,返回数据格式为json
            cache: false,
            timeout: 10000,//请求超时时间,单位为毫秒
            async: true,
            global: false,//禁用Jquery全局事件
            scriptCharset: 'UTF-8',
            //processData : false,         // 告诉jQuery不要去处理发送的数据
            contentType: 'application/json;charset=UTF-8',//请求内容的MIMEType
			data:JSON.stringify(menu),
			success:function(responseData, status){
				if(responseData.success){
					$("#addMenuDialog").modal("hide");
					layer.msg('操作成功！', {icon: 1});
					self.listMenu();
				}else{
					layer.msg('操作失败！', {icon: 5});
				}
			}
		});
	}
	
	//修改menu
	this.updateMenu=function(id){
		self.clearFrom();
		$.post("/menu/getMenu",{"id":id},function(data){
			if(data.success){
				var result = data.data;
				$("#menu_title").text("修改资源");
				$("#addMenuDialog").modal("show");
				$("#menuId").val(result.id);
				$("#type").val(result.type);
				$("#name").val(result.name);
				$("#code").val(result.code);
				$("#url").val(result.url);
				$("#parentId").val(result.id);
			} 
		});
	}
	
	//添加子菜单
	this.addChildMenu=function(id){
		self.clearFrom();
		$.post("/menu/getMenu",{"id":id},function(data){
			if(data.success){
				var result = data.data;
				$("#addMenuDialog").modal("show");
				$("#menu_title").text("【"+result.name+"】添加资源");
				$("#parentId").val(result.id);
			} 
		});
	}
	
	//删除菜单
	this.deleteMenu=function(id){
		self.clearFrom();
		layer.confirm('确定删除吗？', {
			  btn: ['确认','取消'] //按钮
			}, function(){
				$.post("/menu/delMenu",{"id":id},function(data){
					if(data.success){
						layer.msg('操作成功！', {icon: 1});
						self.listMenu();
					} else{
						layer.msg('操作失败！', {icon: 5});
					}
				});
			}, function(){
			  
		});
	}
	
	//更新排序
	this.updateSort=function(){
        $("input[name=sort]").bind('keyup',function(event){
		    event=document.all?window.event:event;
		    if((event.keyCode || event.which)==13){
		    	var id = $(this).data('id')
	            var sort = $(this).val();
	            if(!isNaN(sort)){
	                $.post('/menu/updateOrder',{'id': id,'sort':sort},function (data) {
	                    if(data.success){
	                    	layer.msg('操作成功！', {icon: 1});
							self.listMenu();
						}
	                });
	            }
		    }
		});
	}
	
	//清空表单
	this.clearFrom=function(){
		$("#menuId").val("");
		$("#name").val("");
		$("#code").val("");
		$("#url").val("");
	}
	
	self.init();
	
}


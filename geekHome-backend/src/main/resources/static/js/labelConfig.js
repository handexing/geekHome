/**
 * 标签管理
 */

function labelConfig(){
	
	var self=this;
	
	this.init=function(){
		
       $('#addLabelBtn').bind('click',function(){
        	self.clearFrom();
        	$("#menu_title").text("添加标签");
        	$("#addLabelDialog").modal("show");
		});
        
        $('#saveLabelBtn').bind('click',function(){
        	self.saveLabel();
        });
		
        self.listLabel();
	}
	
	this.listLabel=function(){
		$.post("/label/labelList",{},function(data){
			if(data.success){
				
				var result =  data.data;
				var htmlContent="";
				
				htmlContent+="<tr><th>名称</th><th>类型</th><th>排序</th><th>状态</th><th>创建时间</th><th>操作</th></tr>";
				
				$.each(result, function(index, itemobj) {
					var id=result[index].id;  
					var name=result[index].lableName;
					var type=result[index].type;
					var createTime=result[index].createTime;
					var parentId=result[index].parentId;
					var sort=result[index].sort;
					var status=result[index].status;
					
					if(parentId == 0){
						htmlContent += "<tr class=\"treegrid-"+id+"\" id="+id+">";
							htmlContent += "<td>"+name+"</td>";
							if(type == 1){
								htmlContent += "<td><span class=\"label label-primary radius\">标签</span></td>";
							} else if(type == 2){
								htmlContent += "<td><span class=\"label label-secondary radius\">开源</span></td>";
							} else if(type == 3){
								htmlContent += "<td><span class=\"label label-success radius\">问与答</span></td>";
							}
							htmlContent += "<td><input class=\"input-text\" type=\"text\" onclick=\"label_config.updateSort()\" data-id="+id+" name=\"sort\" value="+sort+" /></td>";
							if(status == 1){
								htmlContent += "<td><span class=\"label label-primary radius\">正常</span></td>";
							} else if(status == 0){
								htmlContent += "<td><span class=\"label label-warning radius\">冻结</span></td>";
							}
							htmlContent += "<td>"+createTime+"</td>";
							htmlContent +="<td>" +
											"<input class=\"btn btn-primary-outline radius\" type=\"button\" onclick=\"label_config.addChildLabel("+id+")\" value=\"添加子标签\">&nbsp;&nbsp;" +
											"<input class=\"btn btn-secondary-outline radius\" type=\"button\" onclick=\"label_config.updateLabel("+id+")\" value=\"修改\">&nbsp;&nbsp;" +
											"<input class=\"btn btn-success-outline radius\" type=\"button\" onclick=\"label_config.deleteLabel("+id+")\" value=\"删除\">" +
										"</td>";
						htmlContent += "</tr>";
					}else{
						htmlContent += "<tr class=\"treegrid-"+id+" treegrid-parent-"+parentId+"\" id="+id+">";
							htmlContent += "<td>"+name+"</td>";
							if(type == 1){
								htmlContent += "<td><span class=\"label label-primary radius\">标签</span></td>";
							} else if(type == 2){
								htmlContent += "<td><span class=\"label label-secondary radius\">开源</span></td>";
							} else if(type == 3){
								htmlContent += "<td><span class=\"label label-success radius\">问与答</span></td>";
							}
							htmlContent += "<td><input class=\"input-text\" type=\"text\" onclick=\"label_config.updateSort()\" data-id="+id+" name=\"sort\" value="+sort+" /></td>";
							if(status == 1){
								htmlContent += "<td><span class=\"label label-primary radius\">正常</span></td>";
							} else if(status == 0){
								htmlContent += "<td><span class=\"label label-warning radius\">冻结</span></td>";
							}
							htmlContent += "<td>"+createTime+"</td>";
							htmlContent +="<td>" +
											"<input class=\"btn btn-primary-outline radius\" type=\"button\" onclick=\"label_config.addChildLabel("+id+")\" value=\"添加子标签\">&nbsp;&nbsp;" +
											"<input class=\"btn btn-secondary-outline radius updateMenuBtn\" type=\"button\" onclick=\"label_config.updateLabel("+id+")\" value=\"修改\">&nbsp;&nbsp;" +
											"<input class=\"btn btn-success-outline radius\" type=\"button\" onclick=\"label_config.deleteLabel("+id+")\" value=\"删除\">" +
										"</td>";
						htmlContent += "</tr>";
					}
					
				});
				
				$("#labelGrid").html(htmlContent);
				$("#labelGrid").treegrid({
		            expanderExpandedClass: 'Hui-iconfont Hui-iconfont-add',
		            expanderCollapsedClass: 'Hui-iconfont Hui-iconfont-jianhao'
		        });
			}
		});
	}
	
	this.saveLabel=function(){

		var id = $("#labelId").val();
		var parentId = $("#parentId").val();
		var type = $("#type").val();
		var name = $.trim($("#name").val());
		
		if(name==null || name==""){
			layer.msg('名称不能为空！', {icon: 7});
			return;
		}
		
		debugger
		var label={};
		label.id = id;
		label.parentId = parentId;
		label.type = type;
		label.lableName = name;
		
		$.ajax({
			url:'/label/saveLabel',
            type: "POST",
            dataType: "json",//跨域ajax请求,返回数据格式为json
            cache: false,
            timeout: 10000,//请求超时时间,单位为毫秒
            async: true,
            global: false,//禁用Jquery全局事件
            scriptCharset: 'UTF-8',
            //processData : false,         // 告诉jQuery不要去处理发送的数据
            contentType: 'application/json;charset=UTF-8',//请求内容的MIMEType
			data:JSON.stringify(label),
			success:function(responseData, status){
				if(responseData.data==1){
					self.listLabel();
					$("#addLabelDialog").modal("hide");
					layer.msg('操作成功！', {icon: 1});
				}else if(responseData.data==-1){
					layer.msg('相同类型下不能有相同名称,请修改！', {icon: 7});
				}else{
					layer.msg('操作失败！', {icon: 5});
				}
			}
		});
	}
	
	this.updateLabel=function(id){
		self.clearFrom();
		$.post("/label/getLabel",{"id":id},function(data){
			if(data.success){
				var result = data.data;
				$("#menu_title").text("修改标签");
				$("#addLabelDialog").modal("show");
				
				$("#labelId").val(result.id);
				$("#type").val(result.type);
				$("#name").val(result.lableName);
				$("#parentId").val(result.id);
			} 
		});
	}
	
	this.addChildLabel=function(id){
		self.clearFrom();
		$.post("/label/getLabel",{"id":id},function(data){
			if(data.success){
				var result = data.data;
				$("#addLabelDialog").modal("show");
				$("#menu_title").text("【"+result.lableName+"】添加标签");
				$("#parentId").val(result.id);
			} 
		});
	}
	
	this.deleteLabel=function(id){
		self.clearFrom();
		layer.confirm('确定删除吗？', {
			  btn: ['确认','取消'] //按钮
			}, function(){
				$.post("/label/delLabel",{"id":id},function(data){
					if(data.success){
						layer.msg('操作成功！', {icon: 1});
						self.listLabel();
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
	                $.post('/label/updateOrder',{'id': id,'sort':sort},function (data) {
	                    if(data.success){
	                    	layer.msg('操作成功！', {icon: 1});
							self.listLabel();
						}
	                });
	            }
		    }
		});
	}
	
	//清空表单
	this.clearFrom=function(){
		$("#labelId").val("");
		$("#name").val("");
		$("#parentId").val("0");
	}
	
	self.init();
	
}


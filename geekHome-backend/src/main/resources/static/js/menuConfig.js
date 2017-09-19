/**
 * 菜单管理
 */

function menuConfig(){
	
	var self=this;
	
	this.init=function(){
		
		 //更新排序
        $("input[name=sort]").bind('keyup',function(event){
		    event=document.all?window.event:event;
		    if((event.keyCode || event.which)==13){
		    	var id = $(this).data('id')
	            var sort = $(this).val();
	            if(!isNaN(sort)){
	                $.post('/menu/updateOrder',{'id': id,'sort':sort},function (data) {
	                    if(data.success){
	                    	 window.location.reload();
						}
	                });
	            }
		    }
		});
        
        $('#addMenuBtn').bind('click',function(){
        	$("#menuId").val("");
    		$("#name").val("");
    		$("#code").val("");
    		$("#url").val("");
        	$("#menu_title").text("添加资源");
        	$("#addMenuDialog").modal("show");
		});
        
        $('#saveMenuBtn').bind('click',function(){
        	self.saveMenu();
        });
        
        $('.updateMenuBtn').bind('click',function(){
        	var id = $(this).attr("data-id");
        	$("#menu_title").text("修改资源");
        	$.post("/menu/getMenu",{"id":id},function(data){
    			if(data.success){
    				var result = data.data;
    				$("#addMenuDialog").modal("show");
    				$("#menuId").val(result.id);
    				$("#type").val(result.type);
    				$("#name").val(result.name);
    				$("#code").val(result.code);
    				$("#url").val(result.url);
    			} 
    		});
        });
		
        //self.listMenu();
	}
	
	//获取menuList
	/*this.listMenu=function(){
		$.post("/menu/menuList",{},function(data){
			console.log("~~~~~~~");
			$("#menuGrid").treegrid({
	            expanderExpandedClass: 'Hui-iconfont Hui-iconfont-add',
	            expanderCollapsedClass: 'Hui-iconfont Hui-iconfont-jianhao'
	        });
			$('#menuGrid').treegrid('render');
		});
	}*/
	
	//保存menu
	this.saveMenu=function(){

		var id = $("#menuId").val();
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
		menu.type = type;
		menu.name = name;
		menu.code = code;
		menu.url = url;
		
		console.log(menu);
		
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
					//$('#menuGrid').treegrid('render');
					window.location.reload();
				}else{
					layer.msg('操作失败！', {icon: 5});
				}

			}
		});
	}
	
	self.init();
	
}


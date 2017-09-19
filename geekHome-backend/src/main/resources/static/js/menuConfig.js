/**
 * 菜单管理
 */

function menuConfig(){
	
	var self=this;
	
	this.init=function(){
		
		$("#dataGrid").treegrid({
            expanderExpandedClass: 'Hui-iconfont Hui-iconfont-add',
            expanderCollapsedClass: 'Hui-iconfont Hui-iconfont-jianhao'
        });
		
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
        	$("addMenuDialog").modal("show");
		});
        
        
        
        
		
	}
	
	self.init();
	
}


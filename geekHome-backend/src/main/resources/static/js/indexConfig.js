/**
 * 首页管理
 */

function indexConfig(){
	
	var self=this;
	
	this.init=function(){
		
		$("#min_title_list li").contextMenu('Huiadminmenu', {
			bindings: {
				'closethis': function(t) {
					if(t.find("i")){
						t.find("i").trigger("click");
					}		
				},
				'closeall': function(t) {
					alert('Trigger was '+t.id+'\nAction was Email');
				},
			}
		});
		
		$.post("/index/menuList",{},function(data){
			if(data.success){
				
				var result = data.data;
				var htmlContent="";
				
				$.each(result, function(index, itemobj) {
					
					var id = result[index].id;  
					var name = result[index].name;  
					var url = result[index].url;  
					var children = result[index].children;  
					
					htmlContent += "<dl id='menu-"+id+"'>";
						htmlContent += "<dt><i class=\"Hui-iconfont\">&#xe62e;</i> "+name+"<i class=\"Hui-iconfont menu_dropdown-arrow\">&#xe6d5;</i></dt>";
						htmlContent += self.getMenu(children);
					htmlContent += "</dl>";
					
				});
				$(".menu_dropdown").html(htmlContent);
				$(".Hui-aside").Huifold({
					titCell:'.menu_dropdown dl dt',
					mainCell:'.menu_dropdown dl dd',
				});
			} 
		});
	}
	
	this.getMenu=function(children){
		if(children.length > 0){
			
			var htmlContent="";
				htmlContent += "<dd>";
				htmlContent += "<ul>";
			
			$.each(children, function(i, itemobj) {
				id = children[i].id;  
				name = children[i].name;  
				url = children[i].url;  
				childrens = children[i].children;  
				htmlContent += "<li><a data-href='"+url+"' data-title='"+name+"' href=\"javascript:void(0)\">"+name+"</a></li>";
			});
			htmlContent += "</ul>";
			htmlContent += "</dd>";
			
			return htmlContent;
		}
		
	}
	
	self.init();
	
}


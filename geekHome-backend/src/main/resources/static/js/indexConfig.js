/**
 * 首页管理
 */

function indexConfig(){
	
	var self=this;
	
	this.init=function(){
		
		$("#min_title_list li").contextMenu('Huiadminmenu', {
			bindings: {
				'closethis': function(t) {
					console.log(t);
					if(t.find("i")){
						t.find("i").trigger("click");
					}		
				},
				'closeall': function(t) {
					alert('Trigger was '+t.id+'\nAction was Email');
				},
			}
		});
	}
	
	self.init();
	
}


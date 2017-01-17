 
 
 /* SIGNIN BOX */

// HOME Login Box
function lb_show_login() {
	try {
		Effect.BlindUp('home_welcome',{duration:0.4,afterFinish:function() {$('home_welcome').style.display="visible";Effect.BlindDown('home_login',{duration:0.5,afterFinish:lb_focus_login});} });
		//$('home_welcome').style.display="visible";		
 	}
 	catch(e){
 		$('home_welcome').style.display="none";
 		$('home_login').style.display="block";
 		lb_focus_login();
 	}
}
function lb_hide_login() {
		try {
		Effect.BlindUp('home_login',{duration:0.5,afterFinish:function() {Effect.BlindDown('home_welcome',{duration:0.3});} });
		}
		catch(e)
		{
		$('home_welcome').style.display="block";
		$('home_login').style.display="none";
	}
}
function lb_focus_login() {
		if ( $('login_email').value ) $('login_passwd').focus();
	else $('login_email').focus();
}

/* MENU Box */
function lb_show_signin_menu() {
	try {
		Effect.BlindDown('menu_signin',{duration:0.5,afterFinish:lb_focus_signin_menu});	
 	}
 	catch(e){
 		$('menu_signin').style.display="block";
 		lb_focus_signin_menu();
 	}
}
function lb_hide_signin_menu() {
		try {
		Effect.BlindUp('menu_signin',{duration:0.5});
		}
		catch(e)
		{
			$('menu_signin').style.display="none";
	}
}
function lb_focus_signin_menu() {
		if ( $('login_email').value ) $('login_passwd').focus();
	else $('login_email').focus();
}
  
  /* HIDE */
  function hideAll(e) {
	Event.stop(e);
	new Element.hide('menu_me');
	new Element.hide('menu_desires');
	new Element.hide('menu_se');
	new Element.hide('menu_he');
	new Element.hide('menu_sea');
	
	Element.setStyle('rtag-p-001',{visibility:'hidden'});
	Element.setStyle('rtag-m-001',{visibility:'hidden'});
  }
  
  
  function hideAllMenu(e) {
	Event.stop(e);
	new Element.hide('menu_me');
	new Element.hide('menu_desires');
	new Element.hide('menu_se');
	new Element.hide('menu_he');
	new Element.hide('menu_sea');
	new Element.hide('menu_signin');
	Element.setStyle('rtag-p-001',{visibility:'hidden'});
	Element.setStyle('rtag-m-001',{visibility:'hidden'});
  }
  
  
  
  
  function borderColorRed2(e) {
  	Event.stop(e);
	new Element.setStyle('id_tag_txt',{borderColor:'#FF33CC'});
  }
  
  function borderColorLightblue2(e) {
  	Event.stop(e);
	new Element.setStyle('id_tag_txt',{borderColor:'#00CCFF'});
  }
  
  
  var voted=false;
  function displayVote(e){
	Event.stop(e);
	//new Effect.toggle('rtag-p-001','appear',{duration:0.2});
	//new Effect.toggle('rtag-m-001','appear',{duration:0.2});
	//Element.show('rtag-m-001');
	if(Element.getStyle('rtag-p-001','visibility')=='hidden' && !voted){
		Element.setStyle('rtag-p-001',{visibility:'visible'});
		Element.setStyle('rtag-m-001',{visibility:'visible'});
	}
	
  }
  function plusVote(e){
	Event.stop(e);
	//Element.setStyle('rtag-001',{fontSize:'+2px'});
	Element.removeClassName('rtag-001',Element.classNames('rtag-h-001'));
	Element.addClassName('rtag-001','size_lev0');
	voted=true;
  }
  function minusVote(e){
	  Event.stop(e);
	  //Element.setStyle('rtag-h-001',{display:'none'});
	  Element.hide('rtag-h-001');
	  voted=true;
  }
  
  function toggleAdviseOver(e){
	Event.stop(e);  
	if(Element.getStyle('tip_001','display')=='none'){
		Element.removeClassName('sh_tip_001',Element.classNames('sh_tip_001'));
		Element.addClassName('sh_tip_001','tip_item_top_img_b');
	}
	else{
		Element.removeClassName('sh_tip_001',Element.classNames('sh_tip_001'));
		Element.addClassName('sh_tip_001','tip_item_top_img_r');
	}
  }
  function toggleAdviseOut(e){
	Event.stop(e);  
	if(Element.getStyle('tip_001','display')=='block'){
		Element.removeClassName('sh_tip_001',Element.classNames('sh_tip_001'));
		Element.addClassName('sh_tip_001','tip_item_top_img_b');
	}
	else{
		Element.removeClassName('sh_tip_001',Element.classNames('sh_tip_001'));
		Element.addClassName('sh_tip_001','tip_item_top_img_r');
	}
  }
  
  function toggleAdvise(e) {
  	Event.stop(e);
	new Effect.toggle('tip_001','blind',{duration:0.7});
  }
  
  //home borderColor
 function borderColorRed3(e) {
  	Event.stop(e);
	new Element.setStyle('home_question_txt',{borderColor:'#FF33CC'});
  }
  
  function borderColorLightblue3(e) {
  	Event.stop(e);
	new Element.setStyle('home_question_txt',{borderColor:'#00CCFF'});
  }
  
  
  //LISTENER
  function addListenerHome(){
	  
	  //home : il aime pas les observe de DesirePage (d'ou une error) mais ca marche quand mm...
	  //il faudrait loader les observes selon la page sur laquel on est
		Event.observe('home_question_txt','mouseover', borderColorRed3);
		Event.observe('home_question_txt','mouseout', borderColorLightblue3);
	
	
	
	
	//Element.hide('rtag-m-001');
	/*
	Event.observe('home_question_txt','mouseover', borderColorRed2);
	Event.observe('home_question_txt','mouseout', borderColorLightblue2);
  	*/
  }
  /*
  function changeImgMenuOver(e){
	Event.stop(e);  
	if(Event.element(e).id =='launch_effect_me'){
		Element.removeClassName('sh_tip_001',Element.classNames('sh_tip_001'));
		Element.addClassName('sh_tip_001','tip_item_top_img_b');
	}
	else{
		Element.removeClassName('sh_tip_001',Element.classNames('sh_tip_001'));
		Element.addClassName('sh_tip_001','tip_item_top_img_r');
	}
  }
  function changeImgMenuOut(e){
	Event.stop(e);  
	if(Element.getStyle('tip_001','display')=='block'){
		Element.removeClassName('sh_tip_001',Element.classNames('sh_tip_001'));
		Element.addClassName('sh_tip_001','tip_item_top_img_b');
	}
	else{
		Element.removeClassName('sh_tip_001',Element.classNames('sh_tip_001'));
		Element.addClassName('sh_tip_001','tip_item_top_img_r');
	}
  }
  */
  
  
  
  
  function addListenerMenu(){
  }
  
  function addListenerDesire(){
	  
	Event.observe('sh_tip_001','click', toggleAdvise);//sh_tip_001
	Event.observe('sh_tip_001','mouseover', toggleAdviseOver);//sh_tip_001  
	Event.observe('sh_tip_001','mouseout', toggleAdviseOut);//sh_tip_001  
	
	Event.observe('id_tag_txt','mouseover', borderColorRed2);
	Event.observe('id_tag_txt','mouseout', borderColorLightblue2);
	
	
	Event.observe('rtag-001','mouseover',displayVote);
	Event.observe('rtag-p-001','mouseover',displayVote);
	Event.observe('rtag-m-001','mouseover',displayVote);
	Event.observe('rtag-p-001','click',plusVote);
	Event.observe('rtag-m-001','click',minusVote);  
  }
  
  Event.observe(window, 'load', addListenerMenu);
  Event.observe(window, 'load', addListenerDesire);
  Event.observe(window, 'load', addAllListenerHome);
  

	
  function changeArrowMenuOver(el){
  	//Element.removeClassName(el,Element.classNames(el));
	Element.addClassName(el,'tip_item_top_img_b');
  }
  function changeArrowMenuOut(el){
  	Element.removeClassName(el,Element.classNames(el));
	Element.addClassName(el,'tip_item_top_img_r');
  }
  
  
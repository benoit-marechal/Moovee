/*****************
 *  W_Menu.js
 ****************/

Event.observe(window, 'load', pageLoaded, false);
		
var ltnMenu = new Array();
var ltnSubmenu = new Array();

function pageLoaded(evt) {
	//Listener on inputField
	Event.observe('item_body','click', deleteFieldContent);

	//Listeners on menu & submenu
	var ltn = $('header').getElementsByClassName('ltn_arrow');
	var lmenu=0;
	var lsubmenu=0;
	for(i=0;i<ltn.length;i++){
		Event.observe(ltn[i].id, 'click', showMenu);	
		if(ltn[i].id.match("menu[0-9]{2}_sub[0-9]{2}")){
			ltnSubmenu[lsubmenu] = ltn[i];
			lsubmenu++;
		}
		else if(ltn[i].id.match("menu[0-9]{2}")){
			ltnMenu[lmenu] = ltn[i];
			
			//alert("ltnMenu[lmenu] = "+ltnMenu[lmenu]+" ltnMenu[lmenu].id="+ltnMenu[lmenu].id);
			lmenu++;
		}
	}
}

function showMenu(e) {
	Event.stop(e);
	//submenu
	if(Event.element(e).id.match("menu[0-9]{2}_sub[0-9]{2}")){
		new Effect.toggle('pan_'+Event.element(e).id,'blind',{duration:0.7});
		/*for (i=0; i<ltnSubmenu.length; i++)
			if( ltnSubmenu[i].id != Event.element(e).id)
					new Element.hide('pan_'+ltnSubmenu[i].id);*/
	}
	//menu
	else if(Event.element(e).id.match("menu[0-9]{2}")) {
		new Effect.toggle('pan_'+Event.element(e).id,'appear',{duration:0.7});
		for (i=0; i<ltnMenu.length; i++)
			if(ltnMenu[i].id != Event.element(e).id)
				new Element.hide('pan_'+ltnMenu[i].id);
	}
}

function deleteFieldContent(e){
	$('item_body').clear();
}
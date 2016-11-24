	var xmlHtt;

function createXML(){
	var xmlHttp;
	//here is the XMLHTTP element, it changes depending on if user is using internt exsplorer//
	if(window.ActiveXObject){
		try{
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		}catch(e){
			xmlHttp = false;
		}
	}else {		
		try{
			xmlHttp = new XMLHttpRequest();
		}catch(e){
			xmlHttp = false;
		}
	}
	if(!xmlHttp){
			alert("can't make xml object");
	}else{
		return xmlHttp;
	}
}

function get_housing_data(address){
	//this call will be used to see if we have the house in our data base, will return true if we do, false if we don't
	xmlHtt = createXML();

       // Typical action to be performed when the document is ready:
			xmlHtt.open("GET", "http://rentsplanet.com/RentsPlanet-1.0-SNAPSHOT/rest/entry?address=" + encodeURIComponent(address), true);
			xmlHtt.setRequestHeader("Content-type", "application/json");
			xmlHtt.onreadystatechange = function () { 
			if (xmlHtt.readyState == 4 && xmlHtt.status == 200) {
			return(xmlHtt.responseText);
		}
	}
	xmlHtt.send(null);
}

function handleEvent(){
	//no longer used left here so I don't mess anyting up
	if (xmlHtt.readyState == 4 && xmlHtt.status == 200){

	} else{

	}
	
}

function get_rid_of (address_json){
	xmlHtt = createXML();
	xmlHtt.open("DELETE", "http://rentsplanet.com/RentsPlanet-1.0-SNAPSHOT/rest/delete", true);
	xmlHtt.setRequestHeader("Content-type", "application/json");
	var what_to_send = JSON.stringify(address_json);
	xmlHtt.onreadystatechange = function () { 
		if (xmlHtt.readyState == 4 && xmlHtt.status == 200) {
		return("data gone");
		}
	}
	xmlHtt.send(what_to_send);
}


function get_info(address){
	//called to get a json with info about the address, not working//
	xmlHtt = createXML();
	xmlHtt.open("POST", "http://rentsplanet.com/RentsPlanet-1.0-SNAPSHOT/rest/getInfo", true);
	xmlHtt.setRequestHeader("Content-type", "application/json");
	var obj = '{' + '"address" : ' + address + '}';
	var what_to_send = JSON.stringify(obj);
	xmlHtt.onreadystatechange = function () { 
		if (xmlHtt.readyState == 4 && xmlHtt.status == 200) {
		return(xmlHtt.responseText);
		}
	}
	xmlHtt.send(obj);
	
}

function submit_review(address_review_jason){
	//will be use to sadd a review of the property, passed as a json to server//
	xmlHtt = createXML();
	xmlHtt.open("POST", "http://rentsplanet.com/RentsPlanet-1.0-SNAPSHOT/rest/create", true);
	xmlHtt.setRequestHeader("Content-type", "application/json");
	var what_to_send = JSON.stringify(address_review_jason);
	xmlHtt.onreadystatechange = function () { 
		if (xmlHtt.readyState == 4 && xmlHtt.status == 200) {
		return("it work");
		}
	}
	xmlHtt.send(what_to_send);
}
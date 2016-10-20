function main(){
		$('.name').hide();
}

function test(){
	//alert("it worked");
	var color = document.getElementById('inputcolor').value;
	//alert(color);
	document.getElementById('house').innerHTML = color;
	var color2 = document.getElementById('inputcolor').value;

	$('.name').fadeIn(1000);
}

$(document).ready(main);
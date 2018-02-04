var n = 0;
var id;
var oli = document.getElementsByTagName("li");
var oimg = document.getElementsByTagName("img");
window.onload = function() {
	//console.log(oimg);
	for (var i = 0; i < oli.length; i++) {
		oli[i].index = i;
		oli[i].onclick = function() {
			for (var j = 0; j < oimg.length; j++) {
				oimg[j].style.display = "none";
				oli[j].style.background="white";
			}
			oimg[this.index].style.display = "block";
			oli[this.index].style.background="red";
			n = this.index;
		}
	}
	
	id = setInterval(() => {
		oimg[n].style.display = "none";
		oli[n].style.background="white";
		if (n==oimg.length-1) {
			n=-1;
		}
		n++;
		oimg[n].style.display = "block";
		oli[n].style.background="red";
	}, 1000);
}
function stop() {
	window.clearInterval(id);
}
function start() {
	id = setInterval(() => {
		oimg[n].style.display = "none";
		oli[n].style.background="white";
		if (n==oimg.length-1) {
			n=-1;
		}
		n++;
		oimg[n].style.display = "block";
		oli[n].style.background="red";
	}, 1000);
}

function fs() {
	if (n==0) {
		oimg[n].style.display = "none";
		oli[n].style.background="white";
		n=oimg.length-1;
		oimg[n].style.display = "block";
		oli[n].style.background="red";
	}else{
		oimg[n].style.display = "none";
		oli[n].style.background="white";
		oimg[n-1].style.display = "block";
		oli[n-1].style.background="red";
		n-=1;
	}
}
function fx() {
	if (n==oimg.length-1) {
		oimg[n].style.display = "none";
		oli[n].style.background="white";
		n=0;
		oimg[n].style.display = "block";
		oli[n].style.background="red";
	} else{
		oimg[n].style.display = "none";
		oli[n].style.background="white";
		oimg[n+1].style.display = "block";
		oli[n+1].style.background="red";
		n+=1;
	}
}
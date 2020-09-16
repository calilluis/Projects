
var server = new WebSocket( "ws://ecv-etic.upf.edu:9020" );
//var server = new WebSocket( "ws://localhost:9020" );

//--------------CALENDAR----------------------------------------------------

YUI({skin: 'night'}).use('calendar', 'datatype-date', 'cssbutton',  function(Y){
var calendar = new Y.Calendar({contentBox: "#mycalendar",width:'340px',showPrevMonth: true,
showNextMonth: true, date: new Date()}).render();

var dtdate = Y.DataType.Date;
//console.log(dtdate.format(new Date()));
var selecteddate=dtdate.format(new Date()).toString();//per defecte es la data actual.
Y.one("#selecteddatee").setHTML(selecteddate+" (Today)");//la ensenyam per defecte

calendar.on("selectionChange", function (ev){
  document.getElementById("info").innerHTML = "";//borram lu que hi havia abans
  var newDate = ev.newSelection[0];
  selecteddate=dtdate.format(newDate);
  console.log(selecteddate);
  Y.one("#selecteddatee").setHTML(selecteddate);
  var msg={ type: "selectionChange", user: localStorage.getItem("username"), date: selecteddate.toString()};
  msg = JSON.stringify(msg); //transformam el msg a json
  server.send(msg);//enviam missatge al servidor amb l'input escrit per nosaltres

});//fi calendar

//-------------------------



});//fi yui


server.onmessage = function (msg) {
  var msgp=JSON.parse(msg.data);
  if(msgp.type==="selectionChange"){
    recieveEvents(msg);
  }else if(msgp.type==="newEvent"){
    recievePosition(msg);
  }else if(msgp.type==="pos1"){
      recieveinPosition(msg);
  }
};

function recieveEvents(msg){

  var dataa = JSON.parse(msg.data);
  var cont=document.getElementById("info");
  var divv0=document.createElement("div");
  divv0.classList.add('row');
  divv0.classList.add('block');
  divv0.id="eventt"+dataa.idevent;
  var divv1=document.createElement("div");
  divv1.classList.add('col-sm-8');
  var divv2=document.createElement("div");
  divv2.classList.add('col-sm-4');
  var info=document.createElement("div");
  info.classList.add('row');

  var divv3=document.createElement("div");
  divv3.classList.add('col-sm-5');
  var divv4=document.createElement("div");
  divv4.classList.add('col-sm-5');
  var convocator=document.createElement("h5");
  convocator.innerHTML='<br>'+ '<u>' +'Convocator: '+'</u>'+dataa.convocator;
  var style=document.createElement("h5");
  style.innerHTML='<u>'+'Style: ' +'</u>'+ dataa.style;
  var location=document.createElement("h5");
  location.innerHTML='<u>' +'Location: '+'</u>'+ dataa.location;
  var description=document.createElement("h5");
  description.innerHTML='<br>'+ '<u>' +'Description: '+'</u>'+ dataa.description;
  var nasistants=document.createElement("h5");
  nasistants.innerHTML='<u>' +'Asistants: '+'</u>'+ dataa.nasistants;

  var but=document.createElement("button");
  but.classList.add("block-button");
  but.innerHTML="I will come !";
  console.log(dataa.idevent);
  but.id=dataa.idevent;
  but.onclick=function(){

    var eventt=but.id;
    document.getElementById("eventt"+eventt).outerHTML = "";
    var msg={ type: "sumAsistants", idevent: eventt};
    msg = JSON.stringify(msg); //transformam el msg a json
    server.send(msg);
  };
  divv3.appendChild(convocator);
  divv3.appendChild(location);
  divv3.appendChild(style);
  divv4.appendChild(description);
  divv4.appendChild(nasistants);
  info.appendChild(divv3);
  info.appendChild(divv4);
  divv1.appendChild(info);
  divv1.appendChild(but);
  divv2.appendChild(returnImg(dataa.style));
  divv0.appendChild(divv1);
  divv0.appendChild(divv2);
  cont.appendChild(divv0);

}

function returnImg(style){
  var img = new Image(); // width, height values are optional params
  if(style=="slalom"){
    img.src = 'img/slalom2.jpg';
  }
  else if(style=="agressive"){
    img.src = 'img/agressive2.png';
  }
  else if(style=="freeskate"){
    img.src = 'img/freeskate3.jpeg';
  }
  else if(style=="downhill"){
    img.src = 'img/downhill2.jpg';
  }
  return img;
}
function newEvent(){ //enviam info a la bdd quan anyadim un nou event
  var date=document.getElementById("date").value;
  var style=document.getElementById("stylee").value;
  var location=document.getElementById("location").value;
  var description=document.getElementById("description").value;
  var msg={ type: "newEvent", user: localStorage.getItem("username"), date: date,
    style:style,description : description, location : location,nasistants:0 };
  msg = JSON.stringify(msg); //transformam el msg a json
  server.send(msg);//enviam missatge al servidor amb l'input escrit per nosaltres

}

server.onopen=function(){//cuando el usuario abre conexión con el servidor, envia su personaje
  var msg={type: "pos1", interests:localStorage.getObj("interests")};
  server.send(JSON.stringify(msg));
};

Storage.prototype.getObj = function(key) {
  return JSON.parse(this.getItem(key))
};


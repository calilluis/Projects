
//----CONFIG FIREBASE---------------------------------
var admin = require('firebase-admin');
var serviceAccount = require('credentials.json');
admin.initializeApp({
    credential: admin.credential.cert(serviceAccount),
    databaseURL: 'https://ecv-p3-8784b.firebaseio.com'
});
var db = admin.database();
//-----------------------------------------------------
//----CONFIG SERVER------------------------------------
var WebSocketServer = require('ws').Server;
var http = require('http');
var path = require('path');
var fs = require('fs');
var server = http.createServer(onRequest);
console.log("escuchando el puerto 9020");
server.listen(9020, function () { });
// create the server
wsServer = new WebSocketServer({
    server: server
});
//-----------------------------------------------------
//variables meves:
var last_id = 0;
var clients= [];
var interestsxclient= [];
var counterlocal;

//-----------------------------------------------------

function onRequest(request, response){ //agafam les coses de la nostra web

    if(request.method == 'GET' && request.url == '/')
    {
        response.writeHead(200, {"Context-Type": "text/html"});
        fs.createReadStream("./login.html").pipe(response);
    }
    else if(request.url.match('/selector.html'))
    {
        response.writeHead(200, {"Context-Type": "text/html"});
        fs.createReadStream("./selector.html").pipe(response);
    }
    else if(request.url.match('/main.html'))
    {
        response.writeHead(200, {"Context-Type": "text/html"});
        fs.createReadStream("./main.html").pipe(response);
    }
    else if(request.url.match("\.css$"))
    {
        response.writeHead(200, {"Context-Type": "text/css"});
        fs.createReadStream(path.join(__dirname, request.url)).pipe(response);
    }
    else if(request.url.match("\.png$"))
    {
        response.writeHead(200, {"Context-Type": "image/png"});
        fs.createReadStream(path.join(__dirname, request.url)).pipe(response);
    }else if(request.url.match("\.jpg$"))
    {
        response.writeHead(200, {"Context-Type": "image/jpg"});
        fs.createReadStream(path.join(__dirname, request.url)).pipe(response);
    }
    else if(request.url.match("\.jpeg$"))
    {
        response.writeHead(200, {"Context-Type": "image/jpeg"});
        fs.createReadStream(path.join(__dirname, request.url)).pipe(response);
    }
    else if(request.url.match("\.js$"))
    {
        response.writeHead(200, {"Context-Type": "text/js"});
        fs.createReadStream(path.join(__dirname, request.url)).pipe(response);
    }

}

var dbRef=db.ref().child('events');//CREATE REFERENCES &COUNTER OF EVENTS
var counter=db.ref().child('counter');
counter.on('value', function(snapshot){
    counterlocal=snapshot.val();
    console.log(counterlocal);
});

//----CONEXION CON EL CLIENTE-----------------------------------------
wsServer.on('connection', function (request) {//cuando se conecta un usuario, se ejecuta la funcion (request es el socket del cliente)

  request.onmessage = (function(event){
    var data = JSON.parse(event.data);

    if(data.type==="pos1"){
        console.log(data);
        request.user_id = last_id;
        request.user_name = "user_" + request.user_id;
        clients.push(request);//tambe aqui dins es guardaran els interesos de l'usuari amb request.data.interests.
        last_id++;
    }
    else if(data.type==="selectionChange"){
       // dbRef.orderByChild("date").equalTo(data.date.toString()).on('value',function(snap){
       // console.log();
       // snap.forEach(function(childsnap){
       //      console.log("ieeeee");
       //     var idevent = childsnap.val().id;
       //     var convocator = childsnap.val().user;
       //     var style = childsnap.val().style;
       //     var location = childsnap.val().location;
       //     var description = childsnap.val().description;
       //     var nasistants = childsnap.val().nasistants;
       //     var newdata = {
       //         type: "selectionChange", convocator: convocator, idevent: idevent,
       //         location: location, style: style, description: description, nasistants: nasistants
       //     };
       //     request.send(JSON.stringify(newdata));
       // });
       // });//fi snap
        for (var i = 0; i < counterlocal; i++) { //mostra els events si la data es la seva.
            //db.ref('events/'+i).once('value').then(function(snap){
            db.ref('events/'+i).on('value',function(snap){
                if(snap.val().date===data.date){
                    var idevent=snap.val().id;
                    var convocator=snap.val().user;
                    var style=snap.val().style;
                    var location=snap.val().location;
                    var description = snap.val().description;
                    var nasistants=snap.val().nasistants;
                    var newdata={type: "selectionChange", convocator: convocator, idevent: idevent,
                        location: location, style: style, description: description, nasistants: nasistants};
                    request.send(JSON.stringify(newdata));
                    //console.log("hoila");
                }
            });//fi snap
        }
    }//fi ifselectionchange
    else if(data.type==="sumAsistants"){
        var id=data.idevent;
        db.ref('events/'+id+'/nasistants').once('value').then(function(snap) {
            db.ref('events/'+id).child("nasistants").set(snap.val()+1);
        });
    }//fi sumasistants
    else if(data.type==="newEvent"){
        db.ref('events/' + counterlocal).set({
            id:counterlocal,
            date: data.date,
            description : data.description,
            location : data.location,
            style: data.style,
            nasistants:0,
            user:data.user
        });
        /*if(data.date===data.actualdate){
           var idevent=counterlocal;
           var convocator=data.user;
           var style=data.style;
           var location=data.location;
           var description = data.description;
           var nasistants=0;
           var newdata={type: "selectionChange", convocator: convocator, idevent: idevent,
           location: location, style: style, description: description, nasistants: nasistants};
           request.send(JSON.stringify(newdata));
           console.log("hoila");
         }*/
        counter.set(counterlocal+1);
    }//fi newevent
  });//fi onmessage

  request.on('close', function () {
    // close user connection
    clients.splice(clients.indexOf(request), 1);//elimina 1 posicion a partir del indice de request==id de usuario
  });

});//fi onconnection
//-----------------------------------------------------

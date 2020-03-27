var app = (function () {
    class Sala{
        constructor(name){
            this.name = name;
            this.players = 0;
        }
    }
    class Jugador{
        constructor(name){
            this.name = name;
        }
    }
    var username;
    var joinedRoom;
    var stompClient = null;

    function crearSala(name){
        var sala = new Sala(name);
        stompClient.subscribe('/topic/created.'+name, function (message) {
            var user = new Jugador(username);
            joinedRoom = name;
            stompClient.subscribe('/topic/joined.'+joinedRoom, function (eventbody) {
                var salas = JSON.parse(eventbody.body);
                stompClient.unsubscribe("misala"+name);
                mostrarUnido();
                _tableJugadores(salas);
            },{id:"joined."+name});
            stompClient.unsubscribe("salaGeneral");
            stompClient.send("/app/joinroom."+joinedRoom,{},JSON.stringify(user));
        },{id:"misala"+name});
        stompClient.send("/app/newroom."+name,{},JSON.stringify(sala));
    }

    function unirseSala(name){
        var user = new Jugador(username);
        joinedRoom = name;
        stompClient.unsubscribe("salaGeneral");
        stompClient.subscribe('/topic/joined.'+joinedRoom, function (eventbody) {
            var salas = JSON.parse(eventbody.body);
            mostrarUnido();
            _tableJugadores(salas);
        },{id:"joined."+name});
        stompClient.send("/app/joinroom."+joinedRoom,{},JSON.stringify(user));
    }

    function salirSala(){
        var user = new Jugador(username);
        stompClient.send("/app/exitroom."+joinedRoom,{},JSON.stringify(user));
        mostrarInicial();
    }


    function _tableJugadores(data){
        var tabla = document.getElementById("tablaSalas");
        if (tabla.style.display = "block"){
            tabla.style.display = "none";
            var tabla = document.getElementById("tablaJugadores");
            tabla.style.display = "block";
        }

        $("#jugadores").empty();
        for (var i = 0; i < data.length; i++) {
            var markup = "<tr> <td>"+ data[i].name;
            $("#jugadores").append(markup)

        }
    }

    function _tableSalas(data){
        var tabla = document.getElementById("tablaJugadores");
        if (tabla.style.display = "none"){
            console.log(data);
            $("#salas").empty();
            for (var i = 0; i < data.length; i++) {
                var markup = "<tr> <td>"+ data[i].name +"</td> <td>"+ data[i].players;
                $("#salas").append(markup)
            }
        }
    }

    function getSalas(){
        var url = window.location;
        var nueva = url.protocol+"//"+url.host + "/superhex/salas";
        var getPromise = $.get(nueva);
        getPromise.then(
            function(data){
                _tableSalas(data)
            },
            function(){
                console.log('error')
            }
        );
        return getPromise;
    }

    var connectAndSubscribeSala = function () {
        console.info('Connecting to WS...');
        var socket = new SockJS('/stompendpoint');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/created', function (message) {
                var salas = JSON.parse(message.body);
                _tableSalas(salas);
            },{id:"salaGeneral"});
        });
    };

    return{
        connectCreate: function () {
            username = document.getElementById("text").value;
            mostrarCreate();
        },
        connectJoin: function () {
            var nombre = document.getElementById("text").value;
            if( nombre == null||nombre ==""){
                console.log("nombre "+nombre);
                toastr.error("Noooo oo oo ooooo!!!", "Title", {
                    "timeOut": "0",
                    "extendedTImeout": "0"
                });
            }else{
                username = document.getElementById("text").value;
                mostrarJoin();
            }
        },
        connect: function () {
            if (stompClient !== null) {
                stompClient.disconnect();
            }
            connectAndSubscribeSala();
            getSalas();
        },
        crearSala:crearSala,
        unirseSala:unirseSala,
    }
})();
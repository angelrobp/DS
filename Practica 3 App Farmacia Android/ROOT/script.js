var articulos = [];
var destacados = false;
var map;
var marcadores = [];
var departamentos = [];
var departamento = "Todos";

class Articulo {
	constructor(nombre, descripcion, stock, precio, imagen, id, destacado, departamento){
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.stock = stock;
		this.precio = precio;
		this.imagen = imagen;
		this.id = id;
		this.departamento = departamento;
		if(destacado==0)
			this.destacado = false;
		else
			this.destacado = true;
	}
	getNombre(){
		return this.nombre;
	}
	getStock(){
		return this.stock;
	}
	getPrecio(){
		return this.precio;
	}
	getDescripcion(){
		return this.descripcion;
	}
	getImagen(){
		return this.imagen;
	}
	getId(){
		return this.id;
	}
	getDepartamento(){
		return this.departamento;
	}
	isDestacado(){
		return this.destacado;
	}
}

var HttpClient = function(){
	this.get = function(url,callBack){
		var anHttpRequest = new XMLHttpRequest();
		console.log("get: "+ url);
	    anHttpRequest.onreadystatechange = function() { 
	        if (anHttpRequest.readyState == 4 && anHttpRequest.status == 200)
	            callBack(anHttpRequest.responseText);
	    }
	    anHttpRequest.open( "GET", 'http://85.55.247.119:8080/RESTful/'+url, true );            
	    anHttpRequest.send( null );
	}

	this.post = function(url,parametros,callBack){
		var anHttpRequest = new XMLHttpRequest();
		console.log("post: "+ url + " " + parametros);
	    anHttpRequest.onreadystatechange = function() { 
	        if (anHttpRequest.readyState == 4 && anHttpRequest.status == 200)
	            callBack(anHttpRequest.responseText);
	    }
	    anHttpRequest.open( "POST", 'http://85.55.247.119:8080/RESTful/'+url, true );
	    anHttpRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	    anHttpRequest.send( parametros );
	}
}

function setDestacados(){
	destacados = true;
	document.getElementById("menu-articulos").classList.remove("active");
	document.getElementById("menu-mapa").classList.remove("active");
	document.getElementById("menu-destacados").classList.add("active");
	document.getElementById("menu-departamento").classList.remove("active");
	document.getElementById("todo").innerHTML = anadirMigaDePan();
	document.getElementById("todo").innerHTML += 
	`<div class="py-0">
      <div class="container">
        <div class="row">
          <div class="col-md-12 mt-3">
            <img class="mx-auto d-block" src="images/add.png" width="45px" data-toggle="modal" data-target="#myModal" style="cursor: pointer;" onclick='modificarModalAdd();'">
          </div>
        </div>
      </div>
    </div>
    <div class="py-4">
      <div class="container" id="articulos">
      </div>
    </div>
    <div class="py-0">
      <div class="container">
        <div class="row">
          <div class="col-md-12 mb-4">
            <img class="mx-auto d-block" src="images/add.png" width="45px" data-toggle="modal" data-target="#myModal" style="cursor: pointer;" onclick='modificarModalAdd();'">
          </div>
        </div>
      </div>
    </div>`;
	realizarBusqueda();
}

function unSetDestacados(){
	destacados = false;
	document.getElementById("menu-articulos").classList.add("active");
	document.getElementById("menu-mapa").classList.remove("active");
	document.getElementById("menu-destacados").classList.remove("active");
	document.getElementById("menu-departamento").classList.remove("active");
	document.getElementById("todo").innerHTML = anadirMigaDePan();
	document.getElementById("todo").innerHTML += 
	`<div class="py-0">
      <div class="container">
        <div class="row">
          <div class="col-md-12 mt-3">
            <img class="mx-auto d-block" src="images/add.png" width="45px" data-toggle="modal" data-target="#myModal" style="cursor: pointer;" onclick='modificarModalAdd();'">
          </div>
        </div>
      </div>
    </div>
    <div class="py-4">
      <div class="container" id="articulos">
      </div>
    </div>
    <div class="py-0">
      <div class="container">
        <div class="row">
          <div class="col-md-12 mb-4">
            <img class="mx-auto d-block" src="images/add.png" width="45px" data-toggle="modal" data-target="#myModal" style="cursor: pointer;" onclick='modificarModalAdd();'">
          </div>
        </div>
      </div>
    </div>`;
	realizarBusqueda();
}

function cargarDepartamentos(){
	var client = new HttpClient();
	departamentos = [];
	departamentos.push("Todos");
	client.get('obtenerDepartamentos/', function(response) {
		var array = JSON.parse(response);
    	for(var i = 0; i < array.length; i++)
    		departamentos.push(array[i].nombre);
    });
}

function cargarDropDown(){
	document.getElementById("dropdown-categoria").innerHTML = "";
	var client = new HttpClient();
	departamentos = [];
	departamentos.push("Todos");
	if(departamento=="Todos")
		document.getElementById("dropdown-categoria").innerHTML += `<a class="dropdown-item active" href="javascript:void(0)" onclick="activarDepartamento('Todos')">Todos</a>`;
	else
		document.getElementById("dropdown-categoria").innerHTML += `<a class="dropdown-item" href="javascript:void(0)" onclick="activarDepartamento('Todos')">Todos</a>`;
	client.get('obtenerDepartamentos/', function(response) {
		var array = JSON.parse(response);
    	for(var i = 0; i < array.length; i++){
    		departamentos.push(array[i].nombre);
    		if(departamento == array[i].nombre )
    			document.getElementById("dropdown-categoria").innerHTML += `<a class="dropdown-item active" href="javascript:void(0)" onclick="anadirMigaDePan();activarDepartamento('`+ array[i].nombre +`')">`+array[i].nombre+`</a>`;
    		else
    			document.getElementById("dropdown-categoria").innerHTML += `<a class="dropdown-item" href="javascript:void(0)" onclick="anadirMigaDePan();activarDepartamento('`+ array[i].nombre +`')">`+array[i].nombre+`</a>`;
    	}
    	document.getElementById("dropdown-categoria").innerHTML += `<div class="dropdown-divider"></div>`;
    	document.getElementById("dropdown-categoria").innerHTML += `<a class="dropdown-item" href="javascript:void(0)" onclick="gestionarDepartamentos()">Gestionar departamentos</a>`;
	});

}

function gestionarDepartamentos(){
	document.getElementById("menu-articulos").classList.remove("active");
	document.getElementById("menu-mapa").classList.remove("active");
	document.getElementById("menu-destacados").classList.remove("active");
	document.getElementById("menu-departamento").classList.add("active");
	var lista = "";
	for(var i = 0; i < departamentos.length; i++)
		if(departamentos[i]!="Todos")
			lista += `<li class="list-group-item">`+departamentos[i]+`<span class="float-right"><img src="images/edit.png" width="35px" onclick="modificarDepartamento('`+departamentos[i]+`')" style="cursor: pointer;"><img class="ml-3 mr-1" src="images/trash.png" width="35px" onclick="eliminarDepartamento('`+departamentos[i]+`')" style="cursor: pointer;"></span></li>`;
	document.getElementById("todo").innerHTML = `
		<div class="py-3">
			<div class="container">
			  <div class="row">
			    <div class="col-md-12">
			      <h1 class="display-3 text-center" id="titulo">Gestionar departamentos</h1>
			    </div>
			  </div>
			</div>
		</div>
		<div class="py-0">
			<div class="container">
				<div class="row">
				  <div class="col-md-12 mt-3">
				    <img class="mx-auto d-block" src="images/add.png" width="45px" onclick='anadirDepartamento();' style="cursor: pointer;"">
				  </div>
				</div>
			</div>
		</div>
		<div class="mt-3 mb-3">
			<ul class="list-group">
	    		`+lista+`
	  		</ul>
  		</div>
  		<div class="py-0">
			<div class="container">
				<div class="row">
				  <div class="col-md-12 mt-3">
				    <img class="mx-auto d-block" src="images/add.png" width="45px" onclick='anadirDepartamento();' style="cursor: pointer;"">
				  </div>
				</div>
			</div>
		</div>
	`;

}

function modificarDepartamento(dep){
	var nombre = prompt("Cambia el nombre del departamento:", "");
    if (nombre == null || nombre == "") {
        return null;
    } else {
    	var parametros = "departamento="+dep+"&nombre="+nombre;
    	var client = new HttpClient();
    	departamento = nombre;
		client.post('modificarDepartamento/', parametros, function(response) {
			if(destacados)
				setDestacados();
			else
				unSetDestacados();
			cargarDropDown();
		});
    }

}

function anadirDepartamento(){
	var nombre = prompt("Escribe el nombre del departamento:", "");
    if (nombre == null || nombre == "") {
        return null;
    } else {
    	var parametros = "&nombre="+nombre;
    	var client = new HttpClient();
		client.post('anadirDepartamento/', parametros, function(response) {
			if(destacados)
				setDestacados();
			else
				unSetDestacados();
			cargarDropDown();
		});
    }

}

function eliminarDepartamento(dep){
	if (confirm("¿Seguro que quieres eliminar el departamento"+dep+"?")){
		var parametros = "departamento="+dep;
		if(dep==departamento)
			departamento="Todos";
		var client = new HttpClient();
		client.post('eliminarDepartamento/', parametros, function(response) {
			if(destacados)
				setDestacados();
			else
				unSetDestacados();
			cargarDropDown();
		});
	}
}

function activarDepartamento(dep){
	departamento = dep;
	if(destacados)
		setDestacados()
	else
		unSetDestacados();
	console.log(destacados);
	cargarDropDown();
	modificarMigaDePan();
}

function setMap(){
	document.getElementById("menu-articulos").classList.remove("active");
	document.getElementById("menu-mapa").classList.add("active");
	document.getElementById("menu-destacados").classList.remove("active");
	document.getElementById("menu-departamento").classList.remove("active");
	document.getElementById("todo").innerHTML = 
	`<div id="map" style="height:100%; width:100%;"></div>`;
	initMap();

}

function convertirLogin(login){
	var contenedor;
	var titulo;
	if(login){
		titulo = "Login";
		contenedor = `
	    <div class="container">
	      <div class="row">
	        <div class="col-md-12">
	          <form action="javascript:void(0);" onsubmit="enviarLogin();">
	            <div class="form-group">
	              <label>Nombre de usuario</label>
	              <input type="text" class="form-control" id="nombre" placeholder="Introduce el nombre de usuario" required="required"> </div>
	            <div class="form-group">
	              <label>Contraseña</label>
	              <input type="password" class="form-control" placeholder="Introduce la contraseña" id="pass"> </div>
	              <button type="button" class="btn btn-primary" onclick="convertirLogin(false);">Registrarse</button>
	              <button type="submit" class="btn btn-primary float-right">Enviar</button>
	          </form>
	        </div>
	      </div>
	    </div>`;
	}else{
		titulo = "Registro";
		contenedor = `
	    <div class="container">
	      <div class="row">
	        <div class="col-md-12">
	          <form action="javascript:void(0);" onsubmit="enviarRegistro();">
	            <div class="form-group">
	              <label>Nombre de usuario</label>
	              <input type="text" class="form-control" id="nombre" placeholder="Introduce el nombre de usuario" required="required"> </div>
	            <div class="form-group">
	              <label>Contraseña</label>
	              <input type="password" class="form-control" placeholder="Introduce la contraseña" id="pass"> </div>
	              <div class="form-group">
	              <label>Repite la contraseña</label>
	              <input type="password" class="form-control" placeholder="Introduce de nuevo la contraseña" id="pass2"> </div>
	              <button type="button" class="btn btn-primary" onclick="convertirLogin(true);">Loguearse</button>
	              <button type="submit" class="btn btn-primary float-right">Enviar</button>
	          </form>
	        </div>
	      </div>
	    </div>`; 
	}
	document.getElementById("contenedor").innerHTML = contenedor;
	document.getElementById("titulo").innerHTML = titulo;
}

function stringToBoolean(cadena){
	if(cadena=="false")
		return false;
	else
		return true;
}

function enviarGet(){
	var client = new HttpClient();
	client.get('login/', function(response) {
		document.getElementById('ver').innerHTML = response;
	});
}

function enviarLogin(){
	var nombre = document.getElementById("nombre").value;
    var pass = document.getElementById("pass").value;
    var parametros = "nombre="+nombre+"&pass="+pass;
    var client = new HttpClient();
    client.post('loginWeb/', parametros, function(response) {
    	if(response=="1"){
    		alert("Login realizado con exito");
    		location.href ="http://85.55.247.119:8080/menu.html";
    	}
    	else if(response=="2")
    		alert("Debes ser admin para poder acceder a la web");
    	else
    		alert("El usuario no existe o no ha introducido bien la contraseña");
	});
}

function enviarRegistro(){
	var nombre = document.getElementById("nombre").value;
    var pass = document.getElementById("pass").value;
    var pass2 = document.getElementById("pass2").value;
    if(pass2!=pass)
    	alert("Las contraseñas no coinciden, vuelvalo a intentar");
    else{
    	var parametros = "nombre="+nombre+"&pass="+pass;
    	var client = new HttpClient();
    	client.post('registroWeb/', parametros, function(response) {
    		var respuesta = stringToBoolean(response);
			if(!respuesta)
				alert("Ya existe ese nombre de usuario");
			else{
				alert("El registro se ha realizado con éxito");
				convertirLogin(true);
			}
		});
    }
}

function mostrarUrl(input){
	if (input.files && input.files[0]){
		var file = input.files[0];
		var reader = new FileReader();

		var target = document.getElementById("img-edit");
		target.title = file.name;

		reader.onload = function(event) {
			target.src = event.target.result;
		};
		reader.readAsDataURL(file);
	}
}

function mostrarUrlAnadir(input){
	if (input.files && input.files[0]){
		var file = input.files[0];
		var reader = new FileReader();
		var aux = document.getElementById("descripcion-modal").innerHTML;
		document.getElementById("descripcion-modal").innerHTML = "<img id='img-edit' src='' class='mx-auto d-block mb-2' width='400px'>" + aux;
		var target = document.getElementById("img-edit");
		target.title = file.name;

		reader.onload = function(event) {
			target.src = event.target.result;
		};
		reader.readAsDataURL(file);
	}
}

function editarArticulo(id){
	var nombre = document.getElementById("nombre-edit").value;
	var precio = document.getElementById("precio-edit").value;
	var descripcion = document.getElementById("descripcion-edit").value;
	var stock = document.getElementById("stock-edit").value;
	var parametros = "id="+id+"&nombre="+nombre+"&descripcion="+descripcion+"&precio="+precio+"&stock="+stock+"&destacado="+document.getElementById("checkbox-destacado").checked+"&departamento="+document.getElementById("select-modificar").value;
	var client = new HttpClient();
	client.post('editarArticulo/', parametros, function(response) {
		realizarBusqueda();
	});
}

function modificarModalAdd(){
	var select = "<select id='select-anadir'>";
	for(var i = 0; i < departamentos.length; i++)
		select += "<option value="+departamentos[i]+">"+departamentos[i]+"</option>";
	select += "</select>";
	document.getElementById("nombre-modal").innerHTML = " Nombre: <input type='text' id='nombre-edit' class='form-control ml-1 pt-0 pb-0' name='nombre'>";
	document.getElementById("descripcion-modal").innerHTML = "<input class='mx-auto d-block mb-2' type='file' name='file' size='45' accept='image/x-png,image/gif,image/jpeg' onchange='mostrarUrlAnadir(this)' />" +
	"<p>Descripcion: </p><p><textarea id='descripcion-edit' class='form-control' rows='3'></textarea>" +
	"</p><div class='row'><span class='ml-2'>Precio: <input type='text' id='precio-edit' class='form-control ml-2 pt-0 pb-0' name='precio' " +
	"></span><span class='ml-3'>Stock: <input type='text' class='form-control ml-2 pt-0 pb-0' id='stock-edit' name='stock'</span></div>"+
	"<div class='row'><div class='mx-auto mt-2'><input class='mx-auto' type='checkbox' id='checkbox-destacado'>Destacado</div></div><div class='row'><div class='mx-auto mt-2'>Departamento: " + select +"</div></div>";
	document.getElementById("pie-modal").innerHTML = "<input class='btn btn-default' type='submit' value='Aceptar' data-dismiss='modal' onclick='anadirArticulo()'>"+
          "<button type='button' class='btn btn-default' data-dismiss='modal'>Cancelar</button>";
}

function modificarModal(i){
	var select = "<select id='select-modificar'>";
	for(var j = 0; j < departamentos.length; j++){
		if(departamentos[j]==articulos[i].getDepartamento())
			select += "<option value="+departamentos[j]+" selected>"+departamentos[j]+"</option>";
		else
			select += "<option value="+departamentos[j]+">"+departamentos[j]+"</option>";
	}
	select += "</select>";
	document.getElementById("nombre-modal").innerHTML = "#" + articulos[i].getId() + " Nombre: <input type='text' id='nombre-edit' class='form-control ml-1 pt-0 pb-0' name='nombre' value='"+articulos[i].getNombre()+"'>";
	document.getElementById("descripcion-modal").innerHTML = "<img id='img-edit' src=" + articulos[i].getImagen() +" class='mx-auto d-block mb-2' width='400px'> <input class='mx-auto d-block mb-2' type='file' name='file' size='45' accept='image/x-png,image/gif,image/jpeg' onchange='mostrarUrl(this)' />" +
	"<p>Descripcion: </p><p><textarea id='descripcion-edit' class='form-control' rows='3'>" + articulos[i].getDescripcion() +"</textarea>" +
	"</p><div class='row'><span class='ml-2'>Precio: <input type='text' id='precio-edit' class='form-control ml-2 pt-0 pb-0' name='precio' value='"+articulos[i].getPrecio()+
	"'></span><span class='ml-3'>Stock: <input type='text' class='form-control ml-2 pt-0 pb-0' id='stock-edit' name='stock' value='"+articulos[i].getStock()+"'</span></div>";
	if(articulos[i].isDestacado())
		document.getElementById("descripcion-modal").innerHTML+="<div class='row'><div class='mx-auto mt-2'><input type='checkbox' id='checkbox-destacado'checked>Destacado</div></div>";
	else
		document.getElementById("descripcion-modal").innerHTML+="<div class='row'><div class='mx-auto mt-2'><input class='mx-auto' type='checkbox' id='checkbox-destacado'>Destacado</div></div>";
	document.getElementById("descripcion-modal").innerHTML+="<div class='row'><div class='mx-auto mt-2'>Departamento: " + select +"</div></div>";
	document.getElementById("pie-modal").innerHTML = "<input class='btn btn-default' type='submit' value='Aceptar' data-dismiss='modal' onclick='editarArticulo("+articulos[i].getId()+")'>"+
          "<button type='button' class='btn btn-default' data-dismiss='modal'>Cancelar</button>";
}

function crearColumna(i){
	var devolver = `
		<div class="col-md-3">
          <div class="card">`;
          if(articulos[i].isDestacado())
          	devolver += `<img class="position-absolute" src="images/destacado.png" width="45px">`;
          devolver +=  `<img class="card-img-top" src="`+ articulos[i].getImagen() + `" alt="` + articulos[i].getImagen() + `">
            <div class="card-body">
              <h5 class="card-title">
                <a>#` + articulos[i].getId() + `</a>
                <a>` + articulos[i].getNombre() + `</a>
              </h5>
              <p class="card-text">`+articulos[i].getDescripcion()+`</p>
              <p>Precio:` + articulos[i].getPrecio() + `€ Stock:` + articulos[i].getStock() + `</p>
              <img class="float-left" src="images/edit.png" width="35px" data-toggle="modal" data-target="#myModal" onclick="modificarModal(`+i+`);"	 style="cursor: pointer;">
              <img class="float-right" src="images/trash.png" width="35px" onclick="aceptarEliminacion(`+articulos[i].getId()+`)" style="cursor: pointer;">
            </div>
          </div>
        </div>`;
    return devolver;
}

function anadirMigaDePan(){
	var d = "";
	if(destacados)
		d = "Si";
	else
		d = "No";

	var miga = `<div class="breadcrumb" id="migas">
      <span><strong>Departamento:</strong> `+departamento+`</span>
      <span class="ml-3"><strong>Solo destacados:</strong> `+d+`</span>
    </div>`;
    return miga;
}

function modificarMigaDePan(){
	var d = "";
	if(destacados)
		d = "Si";
	else
		d = "No";
	document.getElementById("migas").innerHTML = `
		<span><strong>Departamento:</strong> `+departamento+`</span>
    	<span class="ml-3"><strong>Solo destacados:</strong> `+d+`</span>
    `;
}


function pedirArticulos(){
	console.log("pedimos todos los articulos");
	articulos = [];
    var client = new HttpClient();
    client.get('obtenerArticulos/'+destacados+"/"+departamento, function(response) {
    	var array = JSON.parse(response);
    	for(var i = 0; i < array.length; i++){
    		articulos.push(new Articulo(array[i].nombre,array[i].descripcion,array[i].stock,array[i].precio,"http://85.55.247.119:8080/RESTful/obtenerImagen/" + array[i].imagen,array[i].id, array[i].destacado, array[i].departamento));
    	}
    	var devolver = "";
    	for(var i = 0; i < articulos.length; i++){
    		if(i%4==0)
    			devolver += `<div class="row mb-3">`;
			devolver += crearColumna(i);
    		if((i+1)%4==0)
    			devolver += `</div>`;
    	}
    	document.getElementById("articulos").innerHTML = devolver;
	});
}

function realizarBusqueda(){
	console.log("realizamos busqueda");
	articulos = [];
    var client = new HttpClient();
   	if(document.getElementById("busqueda").value=="" || document.getElementById("select").value == "nada"){
   		pedirArticulos();
   	}
   	else{
   		var busqueda = "";
   		if(document.getElementById("select").value == "palabras")
   			busqueda = "realizarBusquedaPalabras/";
   		else
   			busqueda = "realizarBusquedaID/";
	    client.get(busqueda + document.getElementById("busqueda").value+"/"+destacados+"/"+departamento, function(response) {
	    	var array = JSON.parse(response);
	    	for(var i = 0; i < array.length; i++){
	    		articulos.push(new Articulo(array[i].nombre,array[i].descripcion,array[i].stock,array[i].precio,"http://85.55.247.119:8080/RESTful/obtenerImagen/" + array[i].imagen,array[i].id, array[i].destacado, array[i].departamento));
	    	}
	    	var devolver = "";
	    	for(var i = 0; i < articulos.length; i++){
	    		if(i%4==0)
	    			devolver += `<div class="row mb-3">`;
	    		devolver += crearColumna(i);
	    		if((i+1)%4==0)
	    			devolver += `</div>`;
	    	}
	    	document.getElementById("articulos").innerHTML = devolver;
		});
	}

}

function aceptarEliminacion(id){
    if (confirm("¿Seguro que quieres eliminar el producto con id "+id+"?")) {
    	var client = new HttpClient();
    	var parametros = "identificador="+id;
		client.post('eliminarArticulo/', "identificador="+id, function(response) {
			realizarBusqueda();
		});
    }
}

function anadirArticulo(){
	var nombre = document.getElementById("nombre-edit").value;
	var precio = document.getElementById("precio-edit").value;
	var descripcion = document.getElementById("descripcion-edit").value;
	var stock = document.getElementById("stock-edit").value;
	var parametros = "nombre="+nombre+"&descripcion="+descripcion+"&precio="+precio+"&stock="+stock+"&destacado="+document.getElementById("checkbox-destacado").checked+"&departamento="+document.getElementById("select-anadir").value;
	var client = new HttpClient();
	client.post('anadirArticulo/', parametros, function(response) {
		realizarBusqueda();
	});
}

function initMap() {
	var granada = {lat: 37.175855, lng: -3.597511}
	map = new google.maps.Map(document.getElementById('map'), {
	  zoom: 14,
	  center: granada
	});	
	map.addListener('click', function(event) {
   		anadirMarcador(event.latLng, map);
	});
	cargarMarcadores();
}

function cargarMarcadores(){
	for(var i = 0; i < marcadores.length; i++){
		marcadores[i].setMap(null);
	}
	marcadores = [];
	var client = new HttpClient();
    client.get('obtenerFarmacias/', function(response) {
    	var array = JSON.parse(response);
    	for(var i = 0; i < array.length; i++){
    		var farmacia = array[i].nombre;
    		var lat = array[i].lat;
    		var lng = array[i].lng;
    		var id = array[i].id;

    		var posicion = {lat: parseFloat(lat), lng: parseFloat(lng)};
    		var marker = new google.maps.Marker({
	        	label: farmacia,
	        	position: posicion, 
	        	title: id,
	       		map: map
    		});
    		marcadores.push(marker);
    		anadirListenerMarker(marker);
	    }
    });
}

function anadirMarcador(posicion) {
	var farmacia = prompt("Escribe el nombre de la farmacia:", "");
    if (farmacia == null || farmacia == "") {
        return null;
    } else {
        var marker = new google.maps.Marker({
        	label: farmacia,
        	position: posicion, 
    	});
    	anadirListenerMarker(marker);
    	var lat = marker.getPosition().lat();
    	var lng = marker.getPosition().lng();
    	var nombre = marker.getLabel();
    	var parametros = "nombre="+nombre+"&lat="+lat+"&lng="+lng;
    	var client = new HttpClient();
		client.post('anadirFarmacia/', parametros, function(response) {
			cargarMarcadores();
		});
    }
}

function anadirListenerMarker(marker){
	marker.addListener('click', function() {

		if (confirm("¿Seguro que quieres eliminar esta farmacia?")) {
        	marker.setMap(null);
			var lat = marker.getPosition().lat();
	    	var lng = marker.getPosition().lng();
	    	var nombre = marker.getLabel();
	    	var parametros = "id="+marker.getTitle();
	    	var client = new HttpClient();
				client.post('eliminarFarmacia/', parametros, function(response) {
				});
		}
    });
}
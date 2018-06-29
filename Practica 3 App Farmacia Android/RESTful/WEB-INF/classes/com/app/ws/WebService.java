package com.app.ws;
import java.sql.*;
import java.util.*;
import javax.ws.rs.*;
import javax.json.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

@Path("/")
public class WebService
{

    @POST
    @Path("login/")
    @Produces("text/plain")
    public String realizarLogin(@FormParam("nombre") String nombre, @FormParam("pass") String pass) throws SQLException
    {
        System.out.println("Nombre: " + nombre + "  pass: " + pass);
        BaseDeDatos datos = new BaseDeDatos("root","","farmacia");
        ResultSet rs = datos.realizarQuery("SELECT * FROM usuario");
        while(rs.next()){
            if(rs.getString("username").equals(nombre))
                if(rs.getString("pass").equals(pass))
                    return rs.getString("id_usuario");
        }
        return "false";
    }

    @POST
    @Path("loginWeb/")
    @Produces("text/plain")
    public String realizarLoginWeb(@FormParam("nombre") String nombre, @FormParam("pass") String pass) throws SQLException
    {
        BaseDeDatos datos = new BaseDeDatos("root","","farmacia");
        ResultSet rs = datos.realizarQuery("SELECT * FROM usuario");
        while(rs.next()){
            if(rs.getString("username").equals(nombre))
                if(rs.getString("pass").equals(pass))
                    if(rs.getString("tipo").equals("admin"))
                        return "1";
                    else
                        return "2";
        }
        return "3";
    }

    @POST
    @Path("registroWeb/")
    @Produces("text/plain")
    public boolean realizarRegistroWeb(@FormParam("nombre") String nombre, @FormParam("pass") String pass) throws SQLException
    {
        BaseDeDatos datos = new BaseDeDatos("root","","farmacia");
        String tipo = "admin";
        String valores = "'"+nombre + "','" + pass + "','" + tipo + "'";

        ResultSet rs = datos.realizarQuery("SELECT * FROM usuario");
        while(rs.next()){
            if(rs.getString("username").equals(nombre))
                return false;
        }
        datos.realizarUpdate("INSERT INTO USUARIO (username, pass, tipo) VALUES ("+valores+");");
        return true;
    }
    @POST
    @Path("registro/")
    @Produces("text/plain")
    public boolean realizarRegistro(@FormParam("nombre") String nombre, @FormParam("pass") String pass) throws SQLException
    {
        String valores = "'"+nombre + "','" + pass + "'";
        BaseDeDatos datos = new BaseDeDatos("root","","farmacia");
        ResultSet rs = datos.realizarQuery("SELECT * FROM usuario");
        while(rs.next()){
            if(rs.getString("username").equals(nombre))
                return false;
        }
        datos.realizarUpdate("INSERT INTO USUARIO (username, pass, tipo) VALUES ("+valores+");");
        return true;
    }

    @GET
    @Path("obtenerArticulos/{destacados}/{departamento}")
    @Produces("application/json")
    public JsonArray obtenerArticulos(@PathParam("destacados") boolean destacados, @PathParam("departamento") String departamento) throws SQLException
    {
        BaseDeDatos datos = new BaseDeDatos("root","","farmacia");
        ResultSet rs;
        if(destacados && !departamento.equals("Todos"))
            rs = datos.realizarQuery("SELECT * FROM producto WHERE destacado='1' AND departamento='"+departamento+"'");
        else if(destacados && departamento.equals("Todos"))
            rs = datos.realizarQuery("SELECT * FROM producto WHERE destacado='1'");
        else if(!destacados && !departamento.equals("Todos"))
            rs = datos.realizarQuery("SELECT * FROM producto WHERE departamento='"+departamento+"'");
        else
            rs = datos.realizarQuery("SELECT * FROM producto");

        String envio = "";
        JsonArrayBuilder builder = Json.createArrayBuilder();
        while(rs.next()){
            JsonObject model = Json.createObjectBuilder()
            .add("nombre",rs.getString("nombre"))
            .add("descripcion",rs.getString("descripcion"))
            .add("precio",rs.getString("precio"))
            .add("stock",rs.getString("stock"))
            .add("imagen",rs.getString("imagen"))
            .add("id",rs.getString("id_producto"))
            .add("destacado",rs.getString("destacado"))
            .add("departamento",rs.getString("departamento"))
            .build();
            builder.add(model);
        }
        JsonArray arr = builder.build();
        return arr;
    }

    @GET
    @Path("realizarBusquedaPalabras/{palabras}/{destacados}/{departamento}")
    @Produces("application/json")
    public JsonArray realizarBusquedaPalabras(@PathParam("palabras") String palabras, @PathParam("destacados") boolean destacados, @PathParam("departamento") String departamento) throws SQLException
    {
        BaseDeDatos datos = new BaseDeDatos("root","","farmacia");
        ResultSet rs;
        if(destacados && !departamento.equals("Todos"))
            rs = datos.realizarQuery("SELECT * FROM producto WHERE (descripcion LIKE \'%"+ palabras +"%\' OR nombre LIKE \'%"+ palabras +"%\') AND (destacado='1') AND (departamento='"+departamento+"')");
        else if(destacados && departamento.equals("Todos"))
            rs = datos.realizarQuery("SELECT * FROM producto WHERE (descripcion LIKE \'%"+ palabras +"%\' OR nombre LIKE \'%"+ palabras +"%\') AND (destacado='1')");
        else if(!destacados && !departamento.equals("Todos"))
            rs = datos.realizarQuery("SELECT * FROM producto WHERE (descripcion LIKE \'%"+ palabras +"%\' OR nombre LIKE \'%"+ palabras +"%\') AND (departamento='"+departamento+"')");
        else
            rs = datos.realizarQuery("SELECT * FROM producto WHERE (descripcion LIKE \'%"+ palabras +"%\' OR nombre LIKE \'%"+ palabras +"%\')");
        String envio = "";
        JsonArrayBuilder builder = Json.createArrayBuilder();
        while(rs.next()){
            JsonObject model = Json.createObjectBuilder()
            .add("nombre",rs.getString("nombre"))
            .add("descripcion",rs.getString("descripcion"))
            .add("precio",rs.getString("precio"))
            .add("stock",rs.getString("stock"))
            .add("imagen",rs.getString("imagen"))
            .add("id",rs.getString("id_producto"))
            .add("destacado",rs.getString("destacado"))
            .add("departamento",rs.getString("departamento"))
            .build();
            builder.add(model);
        }
        JsonArray arr = builder.build();
        return arr;
    }

    @GET
    @Path("realizarBusquedaID/{id}/{destacados}/{departamento}")
    @Produces("application/json")
    public JsonArray realizarBusquedaID(@PathParam("id") String id, @PathParam("destacados") boolean destacados, @PathParam("departamento") String departamento) throws SQLException
    {
        BaseDeDatos datos = new BaseDeDatos("root","","farmacia");
        ResultSet rs;
        if(destacados && !departamento.equals("Todos"))
            rs = datos.realizarQuery("SELECT * FROM producto WHERE (id_producto LIKE \'"+ id +"\') AND (destacado='1') AND (departamento='"+departamento+"')");
        else if(destacados && departamento.equals("Todos"))
            rs = datos.realizarQuery("SELECT * FROM producto WHERE (id_producto LIKE \'"+ id +"\') AND (destacado='1')");
        else if(!destacados && !departamento.equals("Todos"))
            rs = datos.realizarQuery("SELECT * FROM producto WHERE (id_producto LIKE \'"+ id +"\') AND (departamento='"+departamento+"')");
        else
            rs = datos.realizarQuery("SELECT * FROM producto WHERE (id_producto LIKE \'"+ id +"\')");

        String envio = "";
        JsonArrayBuilder builder = Json.createArrayBuilder();
        while(rs.next()){
            JsonObject model = Json.createObjectBuilder()
            .add("nombre",rs.getString("nombre"))
            .add("descripcion",rs.getString("descripcion"))
            .add("precio",rs.getString("precio"))
            .add("stock",rs.getString("stock"))
            .add("imagen",rs.getString("imagen"))
            .add("id",rs.getString("id_producto"))
            .add("departamento",rs.getString("departamento"))
            .build();
            builder.add(model);
        }
        JsonArray arr = builder.build();
        return arr;
    }

    @GET
    @Path("descargarApk/")
    @Produces({"application/x-octet-stream"})
    public Response obtenerApk(){
        String nombre = "farmacia.apk";
        File file = new File("C:/xampp/tomcat/webapps/RESTful/apk/"+nombre);
        ResponseBuilder response = Response.ok((Object) file);
        response.header("Content-Disposition",
            "attachment; filename="+nombre);
        return response.build();
    }

    @GET
    @Path("obtenerImagen/{nombre}")
    @Produces({"image/png","image/png"})
    public BufferedImage obtenerImagen(@PathParam("nombre") String nombre)
    {
         BufferedImage img = null;
        try {
            img = ImageIO.read(new File("C:/xampp/tomcat/webapps/RESTful/imgs/"+nombre));
            return  img;
        } catch (IOException e) {
            System.out.println("Excepcion: " + e.getMessage() + "  " + "C:/xampp/tomcat/webapps/RESTful/imgs/" + nombre);
        }
        return img;
    }

    @POST
    @Path("eliminarArticulo/")
    @Produces("text/plain")
    public boolean eliminarArticulo(@FormParam("identificador") String identificador) throws SQLException
    {
        BaseDeDatos datos = new BaseDeDatos("root","","farmacia");
        datos.realizarUpdate("DELETE FROM producto WHERE id_producto='"+identificador+"'");
        return true;
    }

    @POST
    @Path("anadirArticulo/")
    @Produces("text/plain")
    public Boolean anadirArticulo(@FormParam("id") String id, @FormParam("nombre") String nombre, @FormParam("descripcion") String descripcion, @FormParam("stock") String stock, @FormParam("precio") String precio, @FormParam("destacado") boolean destacado, @FormParam("departamento") String departamento) throws SQLException
    {
        BaseDeDatos datos = new BaseDeDatos("root","","farmacia");
        String destc="";
        if(destacado)
            destc = "1";
        else
            destc = "0";
        String query = "INSERT INTO producto (nombre, stock, descripcion, precio, imagen, destacado, departamento) VALUES ('"+nombre+"', '"+stock+"', '"+descripcion+"', '"+precio+"', '"+obtenerImagenAleatoria()+"', '"+destc+"', '"+departamento+"')";
        datos.realizarUpdate(query);
        return true;
    }

    @POST
    @Path("editarArticulo/")
    @Produces("text/plain")
    public Boolean editarArticulo(@FormParam("id") String id, @FormParam("nombre") String nombre, @FormParam("descripcion") String descripcion, @FormParam("stock") String stock, @FormParam("precio") String precio, @FormParam("destacado") boolean destacado, @FormParam("departamento") String departamento) throws SQLException
    {
        BaseDeDatos datos = new BaseDeDatos("root","","farmacia");
        String destc="";
        if(destacado)
            destc = "1";
        else
            destc = "0";
        String query = "UPDATE producto SET nombre='"+nombre+"', descripcion='"+descripcion+"', stock='"+stock+"', precio='"+precio+"', destacado='"+destc+"', departamento='"+departamento+"' WHERE id_producto='"+id+"'";
        datos.realizarUpdate(query);
        return true;
    }

    @POST
    @Path("anadirFarmacia/")
    @Produces("text/plain")
    public Boolean anadirFarmacia(@FormParam("nombre") String nombre, @FormParam("lat") String lat, @FormParam("lng") String lng) throws SQLException
    {
        BaseDeDatos datos = new BaseDeDatos("root","","farmacia");
        String query = "INSERT INTO farmacias (nombre, punto) VALUES ('"+nombre+"',GeomFromText('POINT("+lat+" "+lng+")'))";
        datos.realizarUpdate(query);
        return true;
    }

    @POST
    @Path("eliminarFarmacia/")
    @Produces("text/plain")
    public Boolean anadirFarmacia(@FormParam("id") String id) throws SQLException
    {
        BaseDeDatos datos = new BaseDeDatos("root","","farmacia");
        datos.realizarUpdate("DELETE FROM farmacias WHERE id_farmacia='"+id+"'");
        return true;
    }

    @GET
    @Path("obtenerFarmacias/")
    @Produces("application/json")
    public JsonArray obtenerFarmacias() throws SQLException
    {
        BaseDeDatos datos = new BaseDeDatos("root","","farmacia");
        ResultSet rs = datos.realizarQuery("SELECT nombre, X(punto), Y(punto), id_farmacia FROM farmacias");

        String envio = "";
        JsonArrayBuilder builder = Json.createArrayBuilder();
        while(rs.next()){
            JsonObject model = Json.createObjectBuilder()
            .add("nombre",rs.getString("nombre"))
            .add("lat",rs.getString("X(punto)"))
            .add("lng",rs.getString("Y(punto)"))
            .add("id",rs.getString("id_farmacia"))
            .build();
            builder.add(model);
        }
        JsonArray arr = builder.build();
        return arr;
    }

    @POST
    @Path("anadirProductoCarrito/")
    @Produces("text/plain")
    public boolean anadirProductoCarrito(@FormParam("id_producto") String idP, @FormParam("id_usuario") String idU, @FormParam("cantidad") String cantidad) throws SQLException
    {
        BaseDeDatos datos = new BaseDeDatos("root","","farmacia");
        ResultSet rs = datos.realizarQuery("SELECT precio FROM producto WHERE id_producto="+idP);
        rs.next();
        double precio = rs.getFloat("precio");
        int cant = Integer.parseInt(cantidad);
        rs = datos.realizarQuery("SELECT id_carro FROM carrito WHERE id_usuario="+idU);
        rs.next();
        String id_carro = rs.getString("id_carro");
        rs = datos.realizarQuery("SELECT * FROM itemcarro WHERE (id_carro='"+id_carro+"') AND (id_producto='"+idP+"')");
        int aux = 0;
        boolean encontrado = false;
        while(rs.next()){
            aux = rs.getInt("cantidad");
            encontrado = true;
        }
        cant += aux;
        precio = precio * cant;
        DecimalFormat formato = new DecimalFormat("#.00");
        String precioS = formato.format(precio).replace(",",".");
        if(!encontrado)
            datos.realizarUpdate("INSERT INTO itemcarro (id_carro, id_producto, cantidad, precio) VALUES ('"+id_carro+"', '"+idP+"', '"+cantidad+"', '"+precioS+"')");
        else
            datos.realizarUpdate("UPDATE itemcarro SET cantidad='"+cant+"', precio='"+precioS+"' WHERE (id_carro='"+id_carro+"') AND (id_producto='"+idP+"')");
        return true;
    }

    @POST
    @Path("eliminarProductoCarrito/")
    @Produces("text/plain")
    public boolean eliminarProductoCarrito(@FormParam("id_producto") String idP, @FormParam("id_usuario") String idU) throws SQLException
    {
        BaseDeDatos datos = new BaseDeDatos("root","","farmacia");
        ResultSet rs = datos.realizarQuery("SELECT id_carro FROM carrito WHERE id_usuario="+idU);
        rs.next();
         String id_carro = rs.getString("id_carro");
        datos.realizarUpdate("DELETE FROM itemcarro WHERE (id_producto='"+idP+"') AND (id_carro='"+id_carro+"')");
        return true;
    }

    @POST
    @Path("disminuirProductoCarrito/")
    @Produces("text/plain")
    public boolean disminuirProductoCarrito(@FormParam("id_producto") String idP, @FormParam("id_usuario") String idU, @FormParam("cantidad") String cantidad) throws SQLException
    {
        BaseDeDatos datos = new BaseDeDatos("root","","farmacia");
        ResultSet rs = datos.realizarQuery("SELECT id_carro FROM carrito WHERE id_usuario="+idU);
        rs.next();
        String id_carro = rs.getString("id_carro");
        rs = datos.realizarQuery("SELECT precio FROM producto WHERE id_producto="+idP);
        rs.next();
        double precio = rs.getFloat("precio");
        rs = datos.realizarQuery("SELECT cantidad FROM itemcarro  WHERE (id_carro='"+id_carro+"') AND (id_producto='"+idP+"')");
        rs.next();
        int cant = rs.getInt("cantidad");
        cant -= Integer.parseInt(cantidad);
        precio = precio * cant;
        DecimalFormat formato = new DecimalFormat("#.00");
        String precioS = formato.format(precio).replace(",",".");
        if(cant==0)
            datos.realizarUpdate("DELETE FROM itemcarro WHERE (id_producto='"+idP+"') AND (id_carro='"+id_carro+"')");
        else
            datos.realizarUpdate("UPDATE itemcarro SET cantidad='"+cant+"', precio='"+precioS+"' WHERE (id_carro='"+id_carro+"') AND (id_producto='"+idP+"')");
        return true;
    }

    @POST
    @Path("pagar/")
    @Produces("text/plain")
    public boolean pagar(@FormParam("id_usuario") String idU) throws SQLException
    {
        BaseDeDatos datos = new BaseDeDatos("root","","farmacia");
        ResultSet rs = datos.realizarQuery("SELECT id_carro FROM carrito WHERE id_usuario="+idU);
        rs.next();
         String id_carro = rs.getString("id_carro");
        datos.realizarUpdate("DELETE FROM itemcarro WHERE id_carro='"+id_carro+"'");
        return true;
    }

    @GET
    @Path("obtenerCarrito/{id_usuario}")
    @Produces("application/json")
    public JsonArray obtenerCarrito(@PathParam("id_usuario") String idU) throws SQLException
    {
        BaseDeDatos datos = new BaseDeDatos("root","","farmacia");
        ResultSet rs = datos.realizarQuery("SELECT id_carro FROM carrito WHERE id_usuario="+idU);
        rs.next();
        String id_carro = rs.getString("id_carro");
        rs = datos.realizarQuery("SELECT * FROM itemcarro WHERE id_carro="+id_carro);
        String envio = "";
        JsonArrayBuilder builder = Json.createArrayBuilder();
        while(rs.next()){
            ResultSet rs1 = datos.realizarQuery("SELECT nombre, precio FROM producto WHERE id_producto="+rs.getString("id_producto"));
            rs1.next();
            JsonObject model = Json.createObjectBuilder()
            .add("cantidad",rs.getString("cantidad"))
            .add("precioTotal",rs.getString("precio"))
            .add("precio",rs1.getString("precio"))
            .add("nombre",rs1.getString("nombre"))
            .add("id_producto",rs.getString("id_producto"))
            .build();
            builder.add(model);
        }
        JsonArray arr = builder.build();
        return arr;
    }

    public String obtenerImagenAleatoria() throws SQLException
    {
        String sDirectorio = "c:/xampp/tomcat/webapps/RESTful/imgs";
        File f = new File(sDirectorio);
        if (f.exists()){
            File[] ficheros = f.listFiles();
            int numero = (int)(Math.random()*ficheros.length);
            return ficheros[numero].getName();
        }
        else {
            return "img.jpg";
        }
    }

    @GET
    @Path("obtenerDepartamentos/")
    @Produces("application/json")
    public JsonArray obtenerDepartamentos() throws SQLException
    {
        BaseDeDatos datos = new BaseDeDatos("root","","farmacia");
        ResultSet rs = datos.realizarQuery("SELECT * FROM departamento");
        JsonArrayBuilder builder = Json.createArrayBuilder();
        while(rs.next()){
            JsonObject model = Json.createObjectBuilder()
            .add("nombre",rs.getString("nombre"))
            .build();
            builder.add(model);
        }
        JsonArray arr = builder.build();
        return arr;
    }

    @POST
    @Path("eliminarDepartamento/")
    public boolean eliminarDepartamento(@FormParam("departamento")String departamento) throws SQLException
    {
        BaseDeDatos datos = new BaseDeDatos("root","","farmacia");
        datos.realizarUpdate("DELETE FROM departamento WHERE nombre='"+departamento+"'");
        return true;
    }

    @POST
    @Path("modificarDepartamento/")
    public boolean modificarDepartamento(@FormParam("departamento")String departamento, @FormParam("nombre")String nombre) throws SQLException
    {
        BaseDeDatos datos = new BaseDeDatos("root","","farmacia");
        datos.realizarUpdate("UPDATE departamento SET nombre='"+nombre+"' WHERE nombre='"+departamento+"'");
        return true;
    }

    @POST
    @Path("anadirDepartamento/")
    public boolean modificarDepartamento(@FormParam("nombre")String nombre) throws SQLException
    {
        BaseDeDatos datos = new BaseDeDatos("root","","farmacia");
        datos.realizarUpdate("INSERT INTO departamento (nombre) VALUES ('"+nombre+"')");
        return true;
    }

/*
    @GET
    @Path("anadirProductoCarrito/{id_producto}/{id_usuario}/{cantidad}")
    @Produces("text/plain")
    public boolean anadirProductoCarrito(@PathParam("id_producto") String idP, @PathParam("id_usuario") String idU, @PathParam("cantidad") String cantidad) throws SQLException
    {
        BaseDeDatos datos = new BaseDeDatos("root","","farmacia");
        ResultSet rs = datos.realizarQuery("SELECT precio FROM producto WHERE id_producto="+idP);
        rs.next();
        double precio = rs.getFloat("precio");
        int cant = Integer.parseInt(cantidad);
        precio = precio * cant;
        rs = datos.realizarQuery("SELECT id_carro FROM carrito WHERE id_usuario="+idU);
        rs.next();
        String id_carro = rs.getString("id_carro");
        datos.realizarUpdate("INSERT INTO itemcarro (id_carro, id_producto, cantidad, precio) VALUES ('"+id_carro+"', '"+idP+"', '"+cantidad+"', '"+precio+"')");
        return true;
    }

    @GET
    @Path("obtenerCarrito/{id_usuario}")
    @Produces("application/json")
    public JsonArray obtenerCarrito(@PathParam("id_usuario") String idU) throws SQLException
    {
        BaseDeDatos datos = new BaseDeDatos("root","","farmacia");
        ResultSet rs = datos.realizarQuery("SELECT id_carro FROM carrito WHERE id_usuario="+idU);
        rs.next();
        String id_carro = rs.getString("id_carro");
        rs = datos.realizarQuery("SELECT * FROM itemcarro WHERE id_carro="+id_carro);
        String envio = "";
        JsonArrayBuilder builder = Json.createArrayBuilder();
        while(rs.next()){
            ResultSet rs1 = datos.realizarQuery("SELECT nombre, precio FROM producto WHERE id_producto="+rs.getString("id_producto"));
            rs1.next();
            JsonObject model = Json.createObjectBuilder()
            .add("cantidad",rs.getString("cantidad"))
            .add("precioTotal",rs.getString("precio"))
            .add("precio",rs1.getString("precio"))
            .add("nombre",rs1.getString("nombre"))
            .build();
            builder.add(model);
        }
        JsonArray arr = builder.build();
        return arr;
    }
*/
}
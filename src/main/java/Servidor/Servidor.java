/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Servidor;

import Clases.Persistencia;
import Clases.cliente;
import Clases.habitaciones;
import Clases.personal;
import Clases.reclamaciones;
import Clases.sucursales;
import MongoDB.Consultas_Mongo;
import SAX.ManejadorSAX;
import SAX.MyHandler;
import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

/**
 *
 * @author Admin
 */
public class Servidor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException, ParserConfigurationException {
        ServerSocket server = new ServerSocket(5555);
        Socket socket;
        Persistencia p = new Persistencia();

        while (true) {
            socket = server.accept();
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            System.out.println("Hasta aqui");

            JsonObject jso = new JsonObject();
            Jsoner.serialize(jso);

            String consulta = dis.readUTF();
            System.out.println(consulta);
            switch (consulta) {

                case ("buscar_sucursal"):
                    dis = new DataInputStream(socket.getInputStream());
                    System.out.println("Prueba");
                    String hotel = dis.readUTF();
                    System.out.println(hotel);
                    sucursales su = p.mostrarSucursal(hotel);

                    jso.put("nombre", su.getNombre());
                    jso.put("ciudad", su.getCiudad());
                    jso.put("codigo_postal", su.getCodigo_postal());
                    jso.put("imagen", su.getImagen());

                    oos.writeObject(jso);
                    break;

                case ("reservar_habitacion"):
                    ois = new ObjectInputStream(socket.getInputStream());
                    jso = (JsonObject) ois.readObject();

                    p.insertarHabitacion((String) jso.get("num_Hab"), (String) jso.get("dni"), (String) jso.get("cp"));
                    ois.close();
                    break;

                    
                case ("insertar_cliente"):
                    //ois = new ObjectInputStream(socket.getInputStream());
                    JsonObject jso3 = (JsonObject) ois.readObject();

                    String nom = (String) jso3.get("nombre");
                    String ape = (String) jso3.get("apellido");
                    String dni = (String) jso3.get("dni");
                    String tel = (String) jso3.get("tel");

                    cliente c = p.mostrarCliente(dni);

                    if (c.getDni().length() < 6) {
                        p.insertarCliente(nom, ape, dni, tel, "10");
                    } else {
                        System.out.println("La persona existe.");
                    }
                    ois.close();
                    break;

                    
                case ("insertar_reclamacion"):
                    //ois = new ObjectInputStream(socket.getInputStream());
                    jso = (JsonObject) ois.readObject();
                    p.insertarReclamacion(jso.get("reclamacion").toString(), jso.get("dni").toString());

                    ois.close();
                    break;

                case ("borrar_reclamacion"):
                    //ois = new ObjectInputStream(socket.getInputStream());
                    jso = (JsonObject) ois.readObject();
                    int eliminar = Integer.parseInt(jso.get("id").toString());
                    boolean isEliminado = false;
                    ArrayList<reclamaciones> misRecla = p.mostrarReclamacionPersona(jso.get("dni").toString());

                    for (int i = 0; i < misRecla.size(); i++) {
                        if (misRecla.get(i).getId() == eliminar) {
                            p.eliminarReclamacion(eliminar);
                            isEliminado = true;
                        }
                    }

                    if (isEliminado == true) {
                        dos.writeUTF("Reclamacion eliminada.");
                    } else {
                        dos.writeUTF("La reclamacion no se encuentra en la base de datos.");
                    }
                    ois.close();
                    break;

                case ("ver_reservas"):
                    String dniR = dis.readUTF();
                    ArrayList<habitaciones> reservas = p.mostrarHabitacionesCliente(dniR);
                    JsonArray jsos = new JsonArray();
                    JsonObject json;
                    for (int i = 0; i < reservas.size(); i++) {
                        json = new JsonObject();
                        json.put("id", reservas.get(i).getId());
                        json.put("num", reservas.get(i).getNumero_habitacion());
                        json.put("dni", reservas.get(i).getDni_Cliente().getDni());
                        json.put("cp", reservas.get(i).getSucursal().getCodigo_postal());

                        System.out.println("Habitacion: " + reservas.get(i).getNumero_habitacion());
                        jsos.add(json);
                    }
                    oos.writeObject(jsos);

                    break;

                case ("ver_reclamaciones"):
                    ArrayList<reclamaciones> reclamaciones = p.mostrarReclamacion();
                    JsonArray jsos2 = new JsonArray();
                    JsonObject json2;
                    for (int i = 0; i < reclamaciones.size(); i++) {
                        json2 = new JsonObject();
                        json2.put("id", reclamaciones.get(i).getId());
                        json2.put("reclamacion", reclamaciones.get(i).getReclamacion());
                        json2.put("dni", reclamaciones.get(i).getCliente().getDni());
                        
                        jsos2.add(json2);
                    }
                    oos.writeObject(jsos2);

                    break;

                case ("ver_personal_sax"):
                    ManejadorSAX s = new ManejadorSAX();
                    List<personal> personal = (ArrayList<personal>) s.metodoDeSax();

                    JsonArray jsos3 = new JsonArray();
                    JsonObject json3;
                    for (int i = 0; i < personal.size(); i++) {
                        json3= new JsonObject();
                        json3.put("nombre", personal.get(i).getNombre());
                        json3.put("dni", personal.get(i).getDni_personal());
                        json3.put("horario", personal.get(i).getHorario());
                        json3.put("tel", personal.get(i).getTelefono());
                        json3.put("cp", personal.get(i).getSucursal().getCodigo_postal());

                        jsos3.add(json3);
                    }
                    oos.writeObject(jsos3);
                    break;
                    
                    
                case ("borrar_reserva"):
                    //ois = new ObjectInputStream(socket.getInputStream());
                    jso = (JsonObject) ois.readObject();
                    int id= Integer.parseInt( jso.get("id").toString());
                    p.eliminarHabitacion(id);
                    break;
                    
                    
                case("modificar_reserva"):
                    //ois = new ObjectInputStream(socket.getInputStream());
                    
                    JsonObject jsoMR =(JsonObject) ois.readObject();
                    int idMR = Integer.parseInt(jsoMR.get("id").toString());
                    habitaciones h = p.mostrarHabitacionesClienteHab(idMR).get(0);
                    
                    p.modificarHabitacion(idMR,jsoMR.get("numHab").toString()
                            ,jsoMR.get("dni").toString(),h.getSucursal().getCodigo_postal());
                    break;
                    
                case ("insertar_mantenimiento"):
                    //ois = new ObjectInputStream(socket.getInputStream());
                    JsonObject jsoMan = (JsonObject) ois.readObject();
                    Consultas_Mongo mongo = new Consultas_Mongo();
                    String idHab = jsoMan.get("id_hab").toString();
                    String redactar = jsoMan.get("redactar").toString();
                    String fecha = jsoMan.get("fecha").toString();
                    String cp = jsoMan.get("cp").toString();
                    mongo.insertarMantenimiento(idHab,redactar,fecha,cp);
                    
                    break;

            }

        }
    }

}

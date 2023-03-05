/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servidor;

import Clases.Persistencia;
import Clases.cliente;
import Clases.reclamaciones;
import Clases.sucursales;
import com.github.cliftonlabs.json_simple.JsonObject;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class Hilo_Servidor extends Thread{

    private DataInputStream dis;
    private DataOutputStream dos;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    
    public Hilo_Servidor(DataInputStream dis, DataOutputStream dos, ObjectOutputStream oos, ObjectInputStream ois) {
        this.dis = dis;
        this.dos = dos;
        this.oos =oos;
        this.ois = ois;
    }
    
    @Override
    public void run() {
        try {
            System.out.println("Hasta aqui");
            Persistencia p = new Persistencia();
            
            
            JsonObject jso = new JsonObject();
            String consulta=dis.readUTF();
            try {
                consulta = dis.readUTF();
            } catch (IOException ex) {
                Logger.getLogger(Hilo_Servidor.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(consulta);
            switch (consulta) {
                
                case ("buscar_sucursal"):
                    System.out.println("Prueba");
                    String hotel = dis.readUTF();
                    System.out.println(hotel);
                    sucursales su = p.mostrarSucursal(hotel);
                    
                    
                    jso.put("nombre", su.getNombre());
                    jso.put("ciudad", su.getCiudad());
                    jso.put("codigo_postal", su.getCodigo_postal());

                    oos.writeObject(jso);
                    break;
                    
                    
                case ("reservar_habitacion"):
                    JsonObject jso2 = new JsonObject();
                    jso2 = (JsonObject) ois.readObject();

                    p.insertarHabitacion((String) jso2.get("num_Hab"), (String) jso2.get("dni"), (String) jso2.get("cp"));
                    ois.close();
                    break;

                case ("insertar_cliente"):
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

                    break;
                    
                    
                case ("insertar_reclamacion"):
                    jso = (JsonObject) ois.readObject();
                    p.insertarReclamacion(jso.get("reclamacion").toString(), jso.get("dni").toString());

                    break;
                    
                    
                    
                case ("borrar_reclamacion"):
                {
                    try {
                        jso = (JsonObject) ois.readObject();
                    } catch (IOException ex) {
                        Logger.getLogger(Hilo_Servidor.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Hilo_Servidor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                int eliminar=Integer.parseInt(jso.get("id").toString());
                boolean isEliminado=false;
                ArrayList<reclamaciones> misRecla=p.mostrarReclamacionPersona(jso.get("dni").toString());
                
                for(int i =0; i<misRecla.size();i++){
                    if(misRecla.get(i).getId()==eliminar){
                        p.eliminarReclamacion(eliminar);
                        isEliminado=true;
                    }
                }
                
                if(isEliminado==true){
                    dos.writeUTF("Reclamacion eliminada.");
                }else{
                    dos.writeUTF("La reclamacion no se encuentra en la base de datos.");
                }
                
                break;
                
                
            }
            
            
        } catch (IOException ex) {
            Logger.getLogger(Hilo_Servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Hilo_Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
            

        }
        
    }
    


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SAX;

import Clases.Persistencia;
import Clases.personal;
import Clases.sucursales;
import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Admin
 */
public class MyHandler extends DefaultHandler{
    
    List<personal> personalLista = null;
    personal per=null;
    StringBuilder data;
    
    boolean isNombre=false;
    boolean isHorario=false;
    boolean isTelefono=false;
    boolean isCp=false;
    
    public List<personal> devolverLista(){
        return personalLista;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if(qName.equalsIgnoreCase("personal")){
            String dni = attributes.getValue("dni");
            per = new personal();
            per.setDni_personal(dni);
            
            if(personalLista==null){
                personalLista= new ArrayList<personal>();
            }
        }else if(qName.equalsIgnoreCase("nombre")){
            isNombre=true;
        }else if(qName.equalsIgnoreCase("horario")){
            isHorario=true;
        }else if(qName.equalsIgnoreCase("telefono")){
            isTelefono=true;
        }else if(qName.equalsIgnoreCase("cp")){
            isCp=true;
        }
        
        data= new StringBuilder();
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(isNombre){
            per.setNombre(data.toString());
            isNombre=false;
        }else if(isHorario){
            per.setHorario(data.toString());
            isHorario=false;
        }else if(isTelefono){
            per.setTelefono(Integer.parseInt(data.toString()));
            isTelefono=false;
        }else if(isCp){
            String datos = data.toString();
            sucursales s = Persistencia.mostrarSucursalCP(datos);
            per.setSucursal(s);
            isCp=false;
        }
        
        if(qName.equalsIgnoreCase("personal")){
            personalLista.add(per);
        }
    }
    
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        data.append(new String(ch, start, length));
    }
    
    
    
}

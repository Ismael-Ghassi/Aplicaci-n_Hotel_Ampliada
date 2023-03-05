/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SAX;

import Clases.personal;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


/**
 *
 * @author Admin
 */
public class ManejadorSAX {

    public static List<personal> metodoDeSax() throws ParserConfigurationException, IOException {
        try {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            
            SAXParser saxParser = saxParserFactory.newSAXParser();
            MyHandler h = new MyHandler();
            saxParser.parse(new File("C://Users//Admin//eclipse-workspace//Proyecto_Hotel//src//main//java//SAX//ListaPersonal.xml"), h);
            
            return h.personalLista;
        } catch (SAXException ex) {
            Logger.getLogger(ManejadorSAX.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return null;
        }
    
    
    public static void main(String[] args) throws ParserConfigurationException, IOException {
        List<personal> p =metodoDeSax();
        
        System.out.println(p.get(1).getNombre());
        
        for(int i =0; i<p.size();i++){
            System.out.println(p.get(i).getDni_personal());
            System.out.println(p.get(i).getNombre());
            System.out.println(p.get(i).getSucursal().getCodigo_postal());
            System.out.println(p.get(i).getHorario());
            System.out.println(p.get(i).getTelefono());
        }
    }
}

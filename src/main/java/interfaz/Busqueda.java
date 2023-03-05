/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Admin
 */



public class Busqueda extends JPanel{
    JTextField busqueda;
    JButton buscar;
    JButton buscarVoz;
    JPanel panelBusqueda;
    JPanel panelBotones;
    //JPanel panelTodo;
    
    public Busqueda(){
        setLayout(new GridLayout(0,2,5,5));
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder());
        panelBusqueda= new JPanel();
        panelBotones= new JPanel();
        
        panelBotones.setLayout(new GridLayout(0,6,3,3));
        
        panelBusqueda.setLayout(new GridLayout(0,1,10,10));
        panelBusqueda.setBorder(BorderFactory.createEmptyBorder());
        panelBusqueda.setBackground(new Color(0,0,0,0));
        busqueda = new JTextField();
        
        buscar = new JButton();
        buscarVoz = new JButton();
        
        
        ImageIcon iconoBuscar = new ImageIcon("src/lupa.png");
        buscar.setIcon(iconoBuscar);
        buscar.setBorder(BorderFactory.createEmptyBorder());
        buscar.setBackground(new Color(0,0,0,0));
        
        ImageIcon iconoBuscarVoz = new ImageIcon("src/micro.png");
        buscarVoz.setIcon(iconoBuscarVoz);
        buscarVoz.setBorder(BorderFactory.createEmptyBorder());
        buscarVoz.setBackground(new Color(0,0,0,0));
        
        
        panelBotones.setOpaque(false);
        panelBotones.setBorder(BorderFactory.createEmptyBorder());
        
        panelBusqueda.add(busqueda);
        panelBotones.add(buscar);
        panelBotones.add(buscarVoz);
        panelBotones.add(new JLabel());
        panelBotones.add(new JLabel());
        panelBotones.add(new JLabel());
        panelBotones.add(new JLabel());
        add(panelBusqueda);
        add(panelBotones);
    }
    
    
}

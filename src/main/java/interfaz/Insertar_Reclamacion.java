/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package interfaz;

import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class Insertar_Reclamacion extends javax.swing.JFrame implements ActionListener{

    /**
     * Creates new form Pantalla_Reclamacion
     */
    public Insertar_Reclamacion() {
        initComponents();
        ImageIcon fondo1 = new ImageIcon("src/fondoAPP.png");
        fondo.setIcon(fondo1);
        
        ImageIcon vol = new ImageIcon("src/volver.png");
        volver.setIcon(vol);
        volver.setBorder(BorderFactory.createEmptyBorder());
        volver.setBackground(new Color(0,0,0,0));
        volver.addActionListener(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        enviar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        dni = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textReclamacion = new javax.swing.JTextArea();
        volver = new javax.swing.JButton();
        fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel1.setText("RECLAMACIONES");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(78, 15, 317, 36));

        enviar.setBackground(new java.awt.Color(0, 0, 0));
        enviar.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        enviar.setForeground(new java.awt.Color(255, 255, 255));
        enviar.setText("ENVIAR");
        enviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enviarActionPerformed(evt);
            }
        });
        getContentPane().add(enviar, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 300, -1, -1));

        jLabel2.setFont(new java.awt.Font("Serif", 1, 15)); // NOI18N
        jLabel2.setText("Introduce tu DNI:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, -1, -1));

        dni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dniActionPerformed(evt);
            }
        });
        getContentPane().add(dni, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, 80, -1));

        jLabel3.setFont(new java.awt.Font("Serif", 1, 15)); // NOI18N
        jLabel3.setText("Escriba sus reclamaciones:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, -1, -1));

        textReclamacion.setColumns(20);
        textReclamacion.setRows(5);
        jScrollPane1.setViewportView(textReclamacion);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, 230, -1));
        getContentPane().add(volver, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 40));
        getContentPane().add(fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 470, 370));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void dniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dniActionPerformed

    private void enviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enviarActionPerformed
       
        try {
            Socket cliente = new Socket("localhost",5555);
            DataInputStream dis = new DataInputStream(cliente.getInputStream()); 
            DataOutputStream dos = new DataOutputStream(cliente.getOutputStream()); 
            ObjectInputStream ois = new ObjectInputStream(cliente.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(cliente.getOutputStream());
            
            dos.writeUTF("insertar_reclamacion");
            JsonObject jso = new JsonObject();
            Jsoner.serialize(jso);
            jso.put("dni", dni.getText());
            jso.put("reclamacion", textReclamacion.getText());
            
            oos.writeObject(jso);
            cliente.close();
            
            JOptionPane.showMessageDialog(this, "La reclamaci?n se ha almacenado");
            dni.setText("");
            textReclamacion.setText("");
            
        } catch (IOException ex) {
            Logger.getLogger(Insertar_Reclamacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }//GEN-LAST:event_enviarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Insertar_Reclamacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Insertar_Reclamacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Insertar_Reclamacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Insertar_Reclamacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Insertar_Reclamacion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField dni;
    private javax.swing.JButton enviar;
    private javax.swing.JLabel fondo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea textReclamacion;
    private javax.swing.JButton volver;
    // End of variables declaration//GEN-END:variables

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==volver){
            Pantalla_Principal p = null;
            try {
                p = new Pantalla_Principal();
            } catch (SQLException ex) {
            } catch (IOException ex) {
                Logger.getLogger(Insertar_Reclamacion.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.setVisible(false);
            p.setVisible(true);
        }

    }
}

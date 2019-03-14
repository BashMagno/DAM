
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import static javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author usuario
 */
public class AccesoBD extends javax.swing.JFrame
{
    DefaultTableModel m;
    Connection conexion;
    Statement sentencia;
    boolean correcto = true;
    /**
     * Creates new form AccesoBD
     */
    public AccesoBD() {
        initComponents();
        PrepararBaseDatos();
        prepararTabla();
    }
    
    void prepararTabla()
    {
        String titulos[] = new String[4];
        
        m = new DefaultTableModel(null, titulos);
        tabla.setModel(m);
    }
    
    void PrepararBaseDatos()
    {
    
        
        try
        {
            //1.- Cargar el controlador
            String controlador="com.mysql.jdbc.Driver";
            Class.forName(controlador).newInstance();
            
            //2.- Crear el objeto conexión.
            String DBURL="jdbc:mysql://localhost/manempsa";
            String usuario="root";
            String password="";
            
            conexion = DriverManager.getConnection(DBURL,usuario,password);
            
            //3.- Crear el objeto sentencia.
            sentencia = conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(rootPane, "No se ha podido conectar a la base de datos, comprueba que esté activa (activar XAMP)", "Error al iniciar la aplicación", WIDTH, null);
            correcto = false;
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null,"Error al cargar el controlador.");
        }     
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        btnTrabajadores = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tabla);

        btnTrabajadores.setText("Trabajadores");
        btnTrabajadores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrabajadoresActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnTrabajadores)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTrabajadores)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try
        {
            conexion.close();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(rootPane, "No se puede cerrar la base de datos");
        }
    }//GEN-LAST:event_formWindowClosing

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        if(!correcto)
        {
            this.dispose();
        }
    }//GEN-LAST:event_formWindowActivated

    private void btnTrabajadoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrabajadoresActionPerformed
        try
        {
            String fecha;
            String titulos[] = {"DNI", "Nombre", "Apellidos", "Sueldo", "Fecha", "Matrícula"};
            m = new DefaultTableModel(null, titulos);
            
            ResultSet r = sentencia.executeQuery("select * from trabajadores");
            
            String fila[] = new String[6];
            
            while(r.next())
            {
                fila[0] = r.getString("DNI");
                fila[1] = r.getString("Nombre");
                fila[2] = r.getString("Apellido");
                fila[3] = r.getString("Sueldo").replace(".", ",");
                fecha = r.getString("Fecha");
                fecha = fecha.substring(8, 10) + "/" + fecha.substring(5, 7) + "/" + fecha.substring(0, 4);
                fila[4] = fecha;
                fila[5] = r.getString("Matricula");
                
                m.addRow(fila);
            }
            
            tabla.setModel(m);
            tabla.setAutoResizeMode(AUTO_RESIZE_ALL_COLUMNS);
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(rootPane, "Error al extraer los datos de la tabla " + e);
        }
    }//GEN-LAST:event_btnTrabajadoresActionPerformed

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
            java.util.logging.Logger.getLogger(AccesoBD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AccesoBD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AccesoBD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AccesoBD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AccesoBD().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTrabajadores;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
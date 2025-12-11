
package Buscar;


import Conexion.conexionBD;
import javax.swing.*;
import java.awt.*;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;



public class Usuario extends JInternalFrame  {
    private DefaultTableModel model;
    private JTable jTabla1;


    public Usuario() {
        // Configuración del internal frame
        setTitle("Configuraciones de Usuario");
        setClosable(true);
        setMaximizable(true);
        setIconifiable(true);
        setResizable(true);
        setSize(800, 600);
        setLocation(100, 100);
        initComponents();
        pack();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        idLabel = new JLabel("Id Usuario");
        Id = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        botonBuscar = new JButton("Buscar");
        botonLimpiarTabla = new JButton("Limpiar Tabla");
        botonListar = new JButton("Listar");
        botonQuitar = new JButton("Desactivar Usuario");
        botonModificar = new JButton("Modificar");
        botonAgregar = new JButton("Agregar");
        jMenuBar1 = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximizable(true);

        botonBuscar.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 17)); // NOI18N
        botonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarActivoActionPerformed(evt);
            }
        });
      
        idLabel.setFont(new java.awt.Font("Lucida Sans Unicode", 2, 18)); // NOI18N
        idLabel.setForeground(new java.awt.Color(70, 80, 72));

        Id.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 17)); // NOI18N
        Id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CodigoActionPerformed(evt);
            }
        });

        tabla.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 18)); // NOI18N
        tabla.setForeground(new java.awt.Color(51, 51, 51));
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
               "IdUsuario", "NombreUsuario", "Contrasenia", "Rol"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, JPasswordField.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        
       
        
        tabla.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jScrollPane2.setViewportView(tabla);
        if (tabla.getColumnModel().getColumnCount() > 0) {
            tabla.getColumnModel().getColumn(0).setHeaderValue("Id Usuario");
            tabla.getColumnModel().getColumn(1).setHeaderValue("Nombre Usuario");
            tabla.getColumnModel().getColumn(2).setHeaderValue("Contraseña");
            tabla.getColumnModel().getColumn(3).setHeaderValue("Rol");
        }

        botonQuitar.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 17)); // NOI18N
        botonQuitar.setEnabled(false);
        botonQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarUsuarioActionPerformed(evt);
                //quitarUsuarioTablaActionPerformed(evt);
            }
        });

        botonModificar.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 17)); // NOI18N
        botonModificar.setEnabled(false);
        botonModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               modificarUsuarioActionPerformed(evt);
            }
        });


        botonListar.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 17)); // NOI18N
        botonListar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listarUsuariosActivosActionPerformed(evt);
            }
        });
        
        

        botonAgregar.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 17)); // NOI18N
        botonAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarUsuarioActionPerformed(evt);
            }
        });

        botonLimpiarTabla.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 17)); // NOI18N
         botonLimpiarTabla.setEnabled(false);
        botonLimpiarTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiarTablaActionPerformed(evt);
            }
        });
        
       
        
        setJMenuBar(jMenuBar1);
        
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(158, 158, 158)
                        .addComponent(idLabel)
                        .addGap(18, 18, 18)
                        .addComponent(Id, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(botonBuscar))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 956, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonListar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(botonQuitar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botonModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botonAgregar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(16, 16, 16))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(botonLimpiarTabla)
                .addGap(187, 187, 187))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(idLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botonBuscar))
                        .addGap(42, 42, 42))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(botonLimpiarTabla)
                            .addComponent(botonListar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                        .addComponent(botonQuitar)
                        .addGap(12, 12, 12)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(botonModificar)
                        .addGap(36, 36, 36)
                        .addComponent(botonAgregar)
                        .addGap(36, 36, 36))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(58, Short.MAX_VALUE))
        );

        

        pack();
    }// </editor-fold>                        

    private void buscarActivoActionPerformed(java.awt.event.ActionEvent evt) {                                         
    // Obtener el valor del campo de texto para el id usuario
    String id = Id.getText();
    
     if (id.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Por favor, ingrese un ID de usuario.", "Campo vacío", JOptionPane.WARNING_MESSAGE);
        return;  // No procede si el campo está vacío
    }
     // Verificar si el ID contiene solo números
if (!id.matches("\\d+")) {
    JOptionPane.showMessageDialog(null, "El ID debe contener solo números.", "ID inválido", JOptionPane.ERROR_MESSAGE);
    return;  // No procede si el ID no es numérico
}
    
    // Consulta SQL para buscar el usuario por su id
    String consulta = "SELECT u.IdUsuario, u.NombreUsuario, u.Contrasenia, u.IdRol,u.Estado,r.NombreRol FROM usuario u"
            + " JOIN rol r ON r.IdRol = u.IdRol"
            + " WHERE IdUsuario = ?";

    Connection conexion = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    try {
        // Usar la clase conexionBD para ejecutar la consulta
        conexion = conexionBD.getConnection();
        rs = conexionBD.ejecutarConsulta(consulta, id);
        
        // Procesar el resultado de la consulta
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        model.setRowCount(0);
        boolean usuarioEncontrado = false;

        while (rs.next()) {
            usuarioEncontrado = true;

            int idUsuario = rs.getInt("IdUsuario");
            String nombreUsuario = rs.getString("NombreUsuario");
            String contrasenia = (rs.getString("Contrasenia")).replaceAll(".", "*");
            String rol = rs.getString("NombreRol");
            int estado = rs.getInt("Estado");
            
            int respuesta = 0 ;
            if (estado == 0) {
               respuesta = JOptionPane.showConfirmDialog(null,
                        "El usuario está inactivo. ¿Desea activarlo?",
                        "Activar Usuario",
                        JOptionPane.YES_NO_OPTION);

                if (respuesta == JOptionPane.YES_OPTION) {
                    // Actualizar el estado del producto a 1 (activo)
                    String updateConsulta = "UPDATE usuario SET Estado = 1 WHERE IdUsuario = ?";
                    try (PreparedStatement updatePst = conexion.prepareStatement(updateConsulta)) {
                        updatePst.setString(1, id);
                        int filasAfectadas = updatePst.executeUpdate();
                        if (filasAfectadas > 0) {
                            JOptionPane.showMessageDialog(null, "Usuario activado exitosamente.");
                        } else {
                            JOptionPane.showMessageDialog(null, "No se pudo activar el producto.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }

            // Verificar si el usuario ya está en la tabla
            boolean usuarioEnTabla = false;
            for (int i = 0; i < model.getRowCount(); i++) {
                Object valorCelda = model.getValueAt(i, 1);
                if (valorCelda != null && valorCelda instanceof Integer && (int) valorCelda == idUsuario) {
                    usuarioEnTabla = true;
                    break;
                }
            }

            if (usuarioEnTabla) {
                JOptionPane.showMessageDialog(null, "El usuario ya está en la tabla.", "Usuario duplicado", JOptionPane.WARNING_MESSAGE);
            } else {
                if (respuesta != JOptionPane.NO_OPTION) {
                Object[] fila = { idUsuario, nombreUsuario, contrasenia,rol };
                model.addRow(fila); // Agregar el usuario encontrado a la tabla
                }
               if (estado == 0) {
                    // Aplicar formato condicional para resaltar la fila en rojo
                    tabla.setRowSelectionInterval(model.getRowCount() - 1, model.getRowCount() - 1);
                }
                
            }
        }

        if (!usuarioEncontrado) {
            JOptionPane.showMessageDialog(null, "No se encontraron usuarios con el id ingresado.", "Usuario no encontrado", JOptionPane.WARNING_MESSAGE);
        }
        
        if (model.getRowCount() > 0) {
            updateButtonVisibility();
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al buscar el usuario.", "Error", JOptionPane.ERROR_MESSAGE);
    } finally {
        // Cerrar los recursos
        conexionBD.cerrarRecursos(rs, pst, conexion);
    }
}

    private void CodigoActionPerformed(java.awt.event.ActionEvent evt) {                                       
        // TODO add your handling code here:
    }                                      
                           

    private void modificarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {       

        //modificar
        int selectedRow = tabla.getSelectedRow();
    int[] selectedRows = tabla.getSelectedRows();
    if (selectedRows.length == 1) {
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();

        // Obtener los datos del usuario seleccionado
        int idUsuario = (int) model.getValueAt(selectedRow, 0);
        String nombreUsuario = (String) model.getValueAt(selectedRow, 1);
        //String contrasenia = (String) model.getValueAt(selectedRow, 2);
        String rol = (String) model.getValueAt(selectedRow, 3);
        ArrayList<String> roles = new ArrayList<>();
        String contrasenia = "";
        
            try {    
                Connection conexion = conexionBD.getConnection();
                String consulta = "SELECT NombreRol FROM rol";
                PreparedStatement pst = conexion.prepareStatement(consulta);
                ResultSet rs = pst.executeQuery();
                
                // Consulta para obtener la contraseña del usuario seleccionado
                String consultaContrasenia = "SELECT contrasenia FROM usuario WHERE idUsuario = ?";
                PreparedStatement pstContrasenia = conexion.prepareStatement(consultaContrasenia);
                pstContrasenia.setInt(1, idUsuario);
                ResultSet rsContrasenia = pstContrasenia.executeQuery();

                
                if (rsContrasenia.next()) {
                    contrasenia = rsContrasenia.getString("contrasenia");
                }
                 
                while (rs.next()) {
             
                    roles.add(rs.getString("NombreRol"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        

        // Abrir el diálogo de modificación
        ModificarUsuarioDialog modificarDialog = new ModificarUsuarioDialog((JFrame) SwingUtilities.getWindowAncestor(this), nombreUsuario, contrasenia,rol,roles);
        modificarDialog.setVisible(true);

        if (modificarDialog.isModified()) {
            // Obtener los datos modificados
            String nuevoNombreUsuario = modificarDialog.getNombreUsuario();
            //String nuevaContrasenia = (modificarDialog.getContrasenia());
            String nuevaContrasenia = (modificarDialog.getContrasenia());
            String nuevoRol = modificarDialog.getRol();
            int idRol = 0;
            

            // Actualizar la tabla
            model.setValueAt(nuevoNombreUsuario, selectedRow, 1);
            model.setValueAt(nuevaContrasenia, selectedRow, 2);
            model.setValueAt(nuevoRol, selectedRow, 3);
            
            

            // Actualizar la base de datos
            try {
                Connection conexion = conexionBD.getConnection();                
                String consulta = "UPDATE usuario SET NombreUsuario = ?, Contrasenia = ? WHERE IdUsuario = ?";
                PreparedStatement pst = conexion.prepareStatement(consulta);
                pst.setString(1, nuevoNombreUsuario);
                pst.setString(2, nuevaContrasenia);
                pst.setInt(3, idUsuario);
                pst.executeUpdate();

                
                // Obtener el IdRol correspondiente al nuevoRol
                consulta = "SELECT IdRol FROM rol WHERE NombreRol = ?";
                pst = conexion.prepareStatement(consulta);
                pst.setString(1, nuevoRol);
                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    idRol = rs.getInt("IdRol");
                }
                
                consulta = "UPDATE usuario SET IdRol = ? WHERE IdUsuario = ?";
                pst = conexion.prepareStatement(consulta);
                pst.setInt(1, idRol);
                pst.setInt(2, idUsuario);
                pst.executeUpdate();
                
                

                pst.close();
                conexion.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error al actualizar el usuario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    } else {
        JOptionPane.showMessageDialog(null, "Seleccione un usuario de la tabla para eliminar.", "Usuario no seleccionado", JOptionPane.WARNING_MESSAGE);
    }

    }                                        

    
    
    
    
    public class ModificarUsuarioDialog extends JDialog {
    private JTextField txtNombreUsuario;
    private JPasswordField txtContrasenia;
    private JComboBox<String>  comboRol;
    private JButton btnGuardar;
    private JButton btnCancelar;
    private boolean isModified = false;

    public ModificarUsuarioDialog(Frame parent, String nombreUsuario, String contrasenia, String rol,ArrayList<String> roles) {
        ///modificar usuario
       super(parent, true);
        setTitle("Modificar Usuario");
        setSize(400, 250);
        setLayout(new BorderLayout()); // Utiliza BorderLayout para una disposición más flexible
        
        // Panel para los campos de texto usando GridBagLayout
        JPanel panelCampos = new JPanel(new GridBagLayout());
        add(panelCampos, BorderLayout.CENTER); // Agrega el panel al centro del diálogo

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 1.0; // Permite que el componente se expanda horizontalmente

        // Etiqueta y campo de texto para ID Categoría
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelCampos.add(new JLabel("Nombre Usuario:"), gbc);
        
        txtNombreUsuario = new JTextField(nombreUsuario);
        txtNombreUsuario.setEditable(false); // Desactiva la edición
        gbc.gridx = 1;
        gbc.gridy = 0;
        panelCampos.add(txtNombreUsuario, gbc);

        JLabel lblContrasenia = new JLabel("Contraseña:");
        lblContrasenia.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelCampos.add(lblContrasenia, gbc);

        txtContrasenia = new JPasswordField(contrasenia);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panelCampos.add(txtContrasenia, gbc);
        
       
        
         JLabel lblRol = new JLabel("Rol:");
        lblRol.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelCampos.add(lblRol, gbc);

        comboRol = new JComboBox<>();
        for (String nombreRol : roles) {
            comboRol.addItem(nombreRol);
        }
        comboRol.setSelectedItem(rol);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panelCampos.add(comboRol, gbc);
        
        // Panel para los botones
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        add(panelBoton, BorderLayout.SOUTH); // Agrega el panel al sur del diálogo
        
        btnGuardar = new JButton("Guardar");
        btnGuardar.setVisible(false); // Inicialmente, el botón "Guardar" está oculto
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isModified = true;
                dispose();
            }
        });
        panelBoton.add(btnGuardar); // Agrega el botón "Guardar" al panel

        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isModified = false;
                dispose(); // Cierra el diálogo sin hacer cambios
            }
        });
        panelBoton.add(btnCancelar); // Agrega el botón "Cancelar" al panel
        
        // Agrega un DocumentListener para detectar cambios en el campo de texto
        txtContrasenia.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                checkForChanges();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkForChanges();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                checkForChanges();
            }
            
            private void checkForChanges() {
                // Si el nuevo texto es diferente al texto original, muestra el botón "Guardar"
                if (!txtContrasenia.getText().equals(contrasenia)) {
                    btnGuardar.setVisible(true);
                } else {
                    btnGuardar.setVisible(false);
                }
            }
        });
        
        
        comboRol.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        checkForChanges();
    }

    private void checkForChanges() {
        // Si el nuevo rol es diferente al rol original, muestra el botón "Guardar"
        if (!comboRol.getSelectedItem().equals(rol)) {
            btnGuardar.setVisible(true);
        } else {
            btnGuardar.setVisible(false);
        }
    }
});
        

        setLocationRelativeTo(parent);
    }

    public boolean isModified() {
        return isModified;
    }

    public String getNombreUsuario() {
        return txtNombreUsuario.getText();
    }

    public String getContrasenia() {
        return txtContrasenia.getText();
    }
    
    public String getRol() {
        return (String) comboRol.getSelectedItem();
    }
    
  

}
     
    
                                       

    private void listarUsuariosActivosActionPerformed(java.awt.event.ActionEvent evt) {                                         
        ///LISTA TODO LOS USUARIOS
         try {
        // Establecer conexión a la base de datos
        Connection conexion = conexionBD.getConnection();
        // Consulta SQL para obtener todos los productos
        String consulta = "SELECT u.IdUsuario, u.NombreUsuario, u.Contrasenia, u.IdRol,r.NombreRol FROM usuario u"
            + " JOIN rol r ON r.IdRol = u.IdRol"
            + " WHERE Estado = 1";
        PreparedStatement pst = conexion.prepareStatement(consulta);
        ResultSet rs = pst.executeQuery();

        // Limpiar la tabla antes de agregar nuevos datos
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        model.setRowCount(0);

        // Procesar resultados y agregar a la tabla
        while (rs.next()) {
            int idUsuario = rs.getInt("IdUsuario");
            String nombreUsuario = rs.getString("NombreUsuario");
            String contrasenia = (rs.getString("Contrasenia")).replaceAll(".", "*");
            String rol = rs.getString("NombreRol");
           

            // Agregar fila al modelo de la tabla
            model.addRow(new Object[]{ idUsuario, nombreUsuario, contrasenia, rol});
        }
        
        
      updateButtonVisibility();

        // Cerrar recursos
        rs.close();
        pst.close();
        conexion.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al listar usuarios: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    }                
    
    
    
    private void agregarUsuarioActionPerformed(java.awt.event.ActionEvent evt) { 
        ArrayList<String> roles = new ArrayList<>();
        
            try {    
                Connection conexion = conexionBD.getConnection();
                String consulta = "SELECT NombreRol FROM rol";
                PreparedStatement pst = conexion.prepareStatement(consulta);
                ResultSet rs = pst.executeQuery();
                 
                while (rs.next()) {
             
                    roles.add(rs.getString("NombreRol"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        // Abrir el diálogo de modificación
        AgregarUsuarioDialog agregarDialog = new AgregarUsuarioDialog((JFrame) SwingUtilities.getWindowAncestor(this),roles);
        agregarDialog.setVisible(true);
        
        if (agregarDialog.isModified()) {
            // Actualizar la base de datos
            try {
                // Obtener los datos modificados
            String nombreUsuario = agregarDialog.getNombreUsuario();
            String contrasenia = agregarDialog.getContrasenia();
            String rol = agregarDialog.getRol();
            int idRol = 0; 
            
           Connection conexion = null;
           PreparedStatement pst = null ;
           
           
                String consulta = "INSERT INTO usuario (NombreUsuario,Contrasenia,IdRol) VALUES (?,?,?)";
                conexion  = conexionBD.getConnection();              
                pst = conexion.prepareStatement(consulta);
               
               
               
            // Obtener el IdRol correspondiente al nuevoRol
                String consultaRol = "SELECT IdRol FROM rol WHERE NombreRol = ?";
                PreparedStatement pstRol = conexion.prepareStatement(consultaRol);
                pstRol.setString(1, rol);
                ResultSet rs = pstRol.executeQuery();

                if (rs.next()) {
                    idRol = rs.getInt("IdRol");
                }

                rs.close();
                pstRol.close();
    
                pst.setString(1, nombreUsuario);
                pst.setString(2, contrasenia);
                pst.setInt(3, idRol);
                pst.executeUpdate();
                
                pst.close();
                conexion.close();
                
                JOptionPane.showMessageDialog(this, "Usuario agregado con éxito");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error al agregar el usuario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            }
        
    }



public class AgregarUsuarioDialog extends JDialog {
    private JTextField txtNombreUsuario;
    private JPasswordField txtContrasenia;
    private JComboBox<String> comboRol;
    private JButton btnGuardar;
    private JButton btnCancelar;
    private boolean isModified = false;

        private JLabel lblRequisitosContrasenia; // JLabel para los requisitos de la contraseña

    public AgregarUsuarioDialog(Frame parent, ArrayList<String> roles) {
        super(parent, true);
        setTitle("Nuevo Usuario");
        setSize(400, 400);
        setLayout(new BorderLayout());

        // Panel para los campos de texto usando GridBagLayout
        JPanel panelCampos = new JPanel(new GridBagLayout());
        add(panelCampos, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 1.0;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelCampos.add(new JLabel("Nombre Usuario:"), gbc);

        txtNombreUsuario = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 0;
        panelCampos.add(txtNombreUsuario, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelCampos.add(new JLabel("Contraseña:"), gbc);

        txtContrasenia = new JPasswordField();
        gbc.gridx = 1;
        gbc.gridy = 1;
        panelCampos.add(txtContrasenia, gbc);

        // JLabel para requisitos de contraseña
        lblRequisitosContrasenia = new JLabel("<html><font color='red'>Requisitos de contraseña:</font><ul>" +
                "<li>Al menos 8 caracteres</li>" +
                "<li>Al menos una letra mayúscula</li>" +
                "<li>Al menos una letra minúscula</li>" +
                "<li>Al menos un número</li>" +
                "<li>Al menos un carácter especial</li>" +
                "</ul></html>");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2; // Ocupa dos columnas
        panelCampos.add(lblRequisitosContrasenia, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panelCampos.add(new JLabel("Rol:"), gbc);

        comboRol = new JComboBox<>();
        for (String nombreRol : roles) {
            comboRol.addItem(nombreRol);
        }
        gbc.gridx = 1;
        gbc.gridy = 3;
        panelCampos.add(comboRol, gbc);

        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        add(panelBoton, BorderLayout.SOUTH);

        btnGuardar = new JButton("Guardar");
        btnGuardar.setVisible(false); // Inicialmente, el botón "Guardar" está oculto
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreUsuario = txtNombreUsuario.getText();
                String contrasenia = new String(txtContrasenia.getPassword());

                // Validar los campos antes de guardar
                if (validarCampos(nombreUsuario, contrasenia)) {
                    if (isUsuarioExists(nombreUsuario)) {
                        JOptionPane.showMessageDialog(AgregarUsuarioDialog.this,
                                "El usuario ya existe en la base de datos.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        isModified = true;
                        dispose();
                    }
                }
            }
        });
        panelBoton.add(btnGuardar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isModified = false;
                dispose(); // Cierra el diálogo sin hacer cambios
            }
        });
        panelBoton.add(btnCancelar);

        DocumentListener documentListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                checkForChanges();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkForChanges();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                checkForChanges();
            }
        };

        txtNombreUsuario.getDocument().addDocumentListener(documentListener);
        txtContrasenia.getDocument().addDocumentListener(documentListener);
        comboRol.addActionListener(e -> checkForChanges());

        setLocationRelativeTo(parent);
        txtNombreUsuario.requestFocus(); // Pone el foco en el primer campo de entrada
    }

    private void checkForChanges() {
        String nombreUsuario = txtNombreUsuario.getText();
        String contrasenia = new String(txtContrasenia.getPassword());
        // Verificar si todos los campos están llenos y mostrar/ocultar el botón "Guardar" según corresponda
        btnGuardar.setVisible(!nombreUsuario.isEmpty() && !contrasenia.isEmpty() && comboRol.getSelectedItem() != null);
    }

    public boolean isModified() {
        return isModified;
    }

    public String getNombreUsuario() {
        return txtNombreUsuario.getText();
    }

    public String getContrasenia() {
        return new String(txtContrasenia.getPassword());
    }

    public String getRol() {
        return (String) comboRol.getSelectedItem();
    }

     private boolean validarCampos(String nombreUsuario, String contrasenia) {
        if (nombreUsuario.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre de usuario no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // Validar la contraseña y mostrar mensajes adecuados
        if (!validarContrasenia(contrasenia)) {
            return false; // La validación de contraseña ya mostrará el mensaje de error
        }

        return true;
    }

    private boolean validarContrasenia(String contrasenia) {
        StringBuilder mensajes = new StringBuilder();

        // Verifica que la contraseña tenga más de 7 caracteres
        if (contrasenia.length() < 8) {
            mensajes.append("La contraseña debe tener al menos 8 caracteres.\n");
        }
        
        // Verifica que contenga al menos una letra mayúscula, una minúscula, un número y un carácter especial
        boolean tieneMayuscula = false;
        boolean tieneMinuscula = false;
        boolean tieneNumero = false;
        boolean tieneCaracterEspecial = false;
        
        for (char c : contrasenia.toCharArray()) {
            if (Character.isUpperCase(c)) tieneMayuscula = true;
            else if (Character.isLowerCase(c)) tieneMinuscula = true;
            else if (Character.isDigit(c)) tieneNumero = true;
            else if (!Character.isLetterOrDigit(c)) tieneCaracterEspecial = true;
        }
        
        if (!tieneMayuscula) mensajes.append("La contraseña debe contener al menos una letra mayúscula.\n");
        if (!tieneMinuscula) mensajes.append("La contraseña debe contener al menos una letra minúscula.\n");
        if (!tieneNumero) mensajes.append("La contraseña debe contener al menos un número.\n");
        if (!tieneCaracterEspecial) mensajes.append("La contraseña debe contener al menos un carácter especial.\n");

        // Si hay mensajes, se muestran en un cuadro de diálogo
        if (mensajes.length() > 0) {
            JOptionPane.showMessageDialog(this, mensajes.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private boolean isUsuarioExists(String nombreUsuario) {
        String sql = "SELECT COUNT(*) FROM usuario WHERE NombreUsuario = ?";
        try (Connection conn = conexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombreUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}


   
    
    private void limpiarTablaActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        //LIMPIAR TABLA
    DefaultTableModel model = (DefaultTableModel) tabla.getModel();
    model.setRowCount(0);
    updateButtonVisibility();
    }       
    
    private void eliminarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        ///eleimina de la lista
          int selectedRow = tabla.getSelectedRow();
          int[] selectedRows = tabla.getSelectedRows();
    if (selectedRows.length == 1) {
        int confirm = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que quieres deshabilitar este usuario?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
    if (confirm == JOptionPane.YES_OPTION) {
 // Actualizar la base de datos
            try {
                Connection conexion = conexionBD.getConnection();   
                int columna = 0; // Índice de la columna que contiene el dato numérico

                // Verificar que se haya seleccionado una fila
                    Object valor = tabla.getValueAt(selectedRow, columna); // Obtener el valor de la celda
                    if (valor instanceof Number) { // Verificar que el valor sea numérico
                        Number numero = (Number) valor;
                        int datoNumerico = (int) numero.intValue(); // Convertir a tipo numérico
                        
                        String consulta = "UPDATE usuario SET Estado=0  WHERE IdUsuario=?";
                        
                       

                PreparedStatement pst = conexion.prepareStatement(consulta);
                pst.setInt(1, datoNumerico);
                pst.executeUpdate();

                // Eliminar la fila de la tabla
                    DefaultTableModel model = (DefaultTableModel) tabla.getModel();
                    model.removeRow(selectedRow);

                pst.close();
                conexion.close();
               
                
                JOptionPane.showMessageDialog(this, "Usuario eliminado con éxito");
                    } 
                
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error al eliminar el usuario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
    }
        
    } else {
        JOptionPane.showMessageDialog(null, "Seleccione un usuario de la tabla para eliminar.", "Usuario no seleccionado", JOptionPane.WARNING_MESSAGE);
    }
    } 
    
      private void updateButtonVisibility() {
    DefaultTableModel model = (DefaultTableModel) tabla.getModel();
    boolean hasRows = model.getRowCount() > 0;

    botonQuitar.setEnabled(hasRows); // Botón "Quitar"
    botonModificar.setEnabled(hasRows); // Botón "Modificar"
    botonLimpiarTabla.setEnabled(hasRows); // Botón "Limpiar"
}

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
            java.util.logging.Logger.getLogger(Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Usuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JTextField Id;
     private javax.swing.JButton botonBuscar;
    private javax.swing.JButton botonQuitar;
    private javax.swing.JButton botonModificar;
    private javax.swing.JButton botonListar;
    private javax.swing.JButton botonAgregar;
    private javax.swing.JButton botonLimpiarTabla;
    private javax.swing.JLabel idLabel;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tabla;
    // End of variables declaration                   
}


package Buscar;

import javax.swing.DefaultListModel;
import Conexion.conexionBD;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.Timer;
import javax.swing.JList; 
import javax.swing.JScrollPane; 
import java.sql.Statement; // Asegúrate de que esta línea esté incluida
import java.awt.event.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.UIManager;

public class Proveedor extends JInternalFrame {
    private DefaultTableModel model;
    private JTable tabla;
    private JList<String> listProveedores; // Cambiado a JList para proveedores
    private DefaultListModel<String> listModel; // Modelo para el JList
    private JButton botonBuscar, botonListar, botonQuitar, botonModificar, botonAgregar;
    private JLabel idLabel;
    private JTextField textFieldBuscar;
    private Timer timer;

    public Proveedor() {
        setTitle("Configuraciones de Proveedor");
        setClosable(true);
        setMaximizable(true);
        setIconifiable(true);
        setResizable(true);
        setSize(800, 600);
        setLocation(100, 100);
        initComponents();
        
        cargarProveedores(); // Cargar proveedores al inicializar

        pack();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        idLabel = new JLabel("Proveedor");
        listModel = new DefaultListModel<>(); // Inicializar el modelo del JList
        listProveedores = new JList<>(listModel); // Inicializar el JList con el modelo
        jScrollPane2 = new JScrollPane();
        tabla = new JTable();
        botonBuscar = new JButton("Buscar");
        botonListar = new JButton("Listar");
        botonQuitar = new JButton("Desactivar Proveedor");
        botonModificar = new JButton("Modificar");
        botonAgregar = new JButton("Agregar");
        jMenuBar1 = new javax.swing.JMenuBar();
        textFieldBuscar = new JTextField(20); // TextField para buscar proveedores
                Id = new JTextField(); // Asegúrate de inicializarlo aquí


        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximizable(true);

        // Configuración del botón buscar
        botonBuscar.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 17));
        botonBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                buscarActivoActionPerformed(evt);
            }
        });

        // Configuración de etiquetas y campos
        idLabel.setFont(new java.awt.Font("Lucida Sans Unicode", 2, 18));
        idLabel.setForeground(new java.awt.Color(70, 80, 72));

        // Crear el modelo para la tabla
        tabla.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] { "IdProveedor", "NombreProveedor", "Direccion", "Telefono" }
        ));

        tabla.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 18));
        tabla.setForeground(new java.awt.Color(51, 51, 51));
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][] {},
            new String[] { "IdProveedor", "NombreProveedor", "Direccion", "Telefono" }
        ) {
            Class[] types = new Class[] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });

        tabla.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jScrollPane2.setViewportView(tabla);
        if (tabla.getColumnModel().getColumnCount() > 0) {
            tabla.getColumnModel().getColumn(0).setHeaderValue("Id Proveedor");
            tabla.getColumnModel().getColumn(1).setHeaderValue("Nombre Proveedor");
            tabla.getColumnModel().getColumn(2).setHeaderValue("Direccion");
            tabla.getColumnModel().getColumn(3).setHeaderValue("Telefono");
        }

        // Configurar JScrollPane para el JList
        JScrollPane scrollPane = new JScrollPane(listProveedores);
        scrollPane.setPreferredSize(new java.awt.Dimension(200, 100)); // Ajusta el tamaño según tus necesidades

       

        botonListar.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 17));
        botonListar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                listarProveedoresActivosActionPerformed(evt);
            }
        });

        botonQuitar.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 17));
        botonQuitar.setEnabled(false);
        botonQuitar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                eliminarProveedorActionPerformed(evt);
            }
        });

        botonModificar.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 17));
        botonModificar.setEnabled(false);
        botonModificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                modificarProveedorActionPerformed(evt);
            }
        });

        botonAgregar.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 17));
        botonAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                agregarProveedorActionPerformed(evt);
            }
        });
        
                // Agregar un DocumentListener al textFieldBuscar
        textFieldBuscar.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filtrarProveedores();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filtrarProveedores();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filtrarProveedores();
            }
        });
        
        
       
        setJMenuBar(jMenuBar1);

        // Configuración del layout
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
                        .addComponent(textFieldBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE) // TextField para buscar
                        .addGap(18, 18, 18)
                        .addComponent(scrollPane) // Usar JScrollPane con el JList
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
                .addGap(187, 187, 187))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(idLabel)
                            .addComponent(textFieldBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botonBuscar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(botonListar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonModificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonQuitar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonAgregar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
    }
    
    
private void filtrarProveedores() {
    String buscar = textFieldBuscar.getText().trim().toLowerCase();
    listModel.removeAllElements(); // Limpiar el modelo

    // Cargar todos los proveedores que comienzan con la búsqueda
   try (Connection con = conexionBD.getConnection();
     PreparedStatement ps = con.prepareStatement(
         "SELECT NombreProveedor FROM proveedor WHERE LOWER(NombreProveedor) LIKE ? ORDER BY NombreProveedor ASC")) {
    ps.setString(1, buscar + "%"); // Cambia '%' a solo al final
    ResultSet rs = ps.executeQuery();
    while (rs.next()) {
        listModel.addElement(rs.getString("NombreProveedor")); // Agregar a la lista
    }
    } catch (SQLException e) {
        e.printStackTrace();
    }

}


private void cargarProveedores() {
    // Llenar el modelo del JList al inicializar con los proveedores ordenados alfabéticamente
    try (Connection con = conexionBD.getConnection();
         Statement stmt = con.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT NombreProveedor FROM proveedor ORDER BY NombreProveedor ASC")) {
        while (rs.next()) {
            listModel.addElement(rs.getString("NombreProveedor")); // Agregar a la lista
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    
    
private void buscarActivoActionPerformed(java.awt.event.ActionEvent evt) {
    // Obtener el valor del campo de texto para el id proveedor
    String id = Id.getText().trim(); // Suponiendo que el Id es el campo de texto para el ID del proveedor

    // Verificar si hay un proveedor seleccionado en el JList
    String proveedorSeleccionado = (String) listProveedores.getSelectedValue(); // listaProveedores es el JList que contiene los proveedores

    if (proveedorSeleccionado == null) {
        JOptionPane.showMessageDialog(null, "Por favor, seleccione un proveedor de la lista.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        return; // No procede si no se ha seleccionado un proveedor
    }

   

    // Consulta SQL para buscar el proveedor por su nombre
    String consulta = "SELECT IdProveedor, NombreProveedor, Direccion, Telefono, Estado FROM proveedor WHERE NombreProveedor = ?";

    Connection conexion = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    try {
        // Usar la clase conexionBD para ejecutar la consulta
        conexion = conexionBD.getConnection();
        pst = conexion.prepareStatement(consulta);
        pst.setString(1, proveedorSeleccionado); // Usar el nombre del proveedor seleccionado
        rs = pst.executeQuery();

        // Procesar el resultado de la consulta
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        model.setRowCount(0); // Limpiar la tabla antes de agregar nuevos resultados
        boolean proveedorEncontrado = false;

        while (rs.next()) {
            proveedorEncontrado = true;

            int idProveedor = rs.getInt("IdProveedor");
            String nombreProveedor = rs.getString("NombreProveedor");
            String direccion = rs.getString("Direccion");
            String telefono = rs.getString("Telefono");
            int estado = rs.getInt("Estado");

            // Comprobar si el proveedor está inactivo y preguntar si se debe activar
            if (estado == 0) {
                int respuesta = JOptionPane.showConfirmDialog(null,
                        "El proveedor está inactivo. ¿Desea activarlo?",
                        "Activar Proveedor",
                        JOptionPane.YES_NO_OPTION);

                if (respuesta == JOptionPane.YES_OPTION) {
                    // Actualizar el estado del proveedor a 1 (activo)
                    String updateConsulta = "UPDATE proveedor SET Estado = 1 WHERE IdProveedor = ?";
                    try (PreparedStatement updatePst = conexion.prepareStatement(updateConsulta)) {
                        updatePst.setInt(1, idProveedor); // Cambiar a setInt ya que idProveedor es un int
                        int filasAfectadas = updatePst.executeUpdate();
                        if (filasAfectadas > 0) {
                            JOptionPane.showMessageDialog(null, "Proveedor activado exitosamente.");
                        } else {
                            JOptionPane.showMessageDialog(null, "No se pudo activar el proveedor.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }

            // Verificar si el proveedor ya está en la tabla
            boolean proveedorEnTabla = false;
            for (int i = 0; i < model.getRowCount(); i++) {
                Object valorCelda = model.getValueAt(i, 0); // Comprobar en la columna del IdProveedor
                if (valorCelda != null && valorCelda instanceof Integer && (int) valorCelda == idProveedor) {
                    proveedorEnTabla = true;
                    break;
                }
            }

            // Agregar el proveedor a la tabla si no está duplicado
            if (!proveedorEnTabla) {
                Object[] fila = { idProveedor, nombreProveedor, direccion, telefono };
                model.addRow(fila); // Agregar el proveedor encontrado a la tabla
            } else {
                JOptionPane.showMessageDialog(null, "El proveedor ya está en la tabla.", "Proveedor duplicado", JOptionPane.WARNING_MESSAGE);
            }
        }

        if (!proveedorEncontrado) {
            JOptionPane.showMessageDialog(null, "No se encontró proveedor con el nombre ingresado.", "Proveedor no encontrado", JOptionPane.WARNING_MESSAGE);
        }

        if (model.getRowCount() > 0) {
            updateButtonVisibility(); // Asegúrate de que este método esté definido para actualizar la visibilidad de los botones
        }

    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al buscar el proveedor.", "Error", JOptionPane.ERROR_MESSAGE);
    } finally {
        // Cerrar los recursos
        conexionBD.cerrarRecursos(rs, pst, conexion);
    }
}



    private void modificarProveedorActionPerformed(java.awt.event.ActionEvent evt) {                                         
    int selectedRow = tabla.getSelectedRow();
    int[] selectedRows = tabla.getSelectedRows();
    
    if (selectedRows.length == 1) {
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();

        int idProveedor = (int) model.getValueAt(selectedRow, 0);
        String nombreProveedor = (String) model.getValueAt(selectedRow, 1);
        String direccion = (String) model.getValueAt(selectedRow, 2);
        String telefono = (String) model.getValueAt(selectedRow, 3);

        ModificarProveedorDialog modificarDialog = new ModificarProveedorDialog(
            (JFrame) SwingUtilities.getWindowAncestor(this), nombreProveedor, direccion, telefono);
        modificarDialog.setVisible(true);

        if (modificarDialog.isModified()) {
            String nuevoNombreProveedor = modificarDialog.getNombreProveedor();
            String nuevaDireccion = modificarDialog.getDireccion();
            String nuevoTelefono = modificarDialog.getTelefono();

            model.setValueAt(nuevoNombreProveedor, selectedRow, 1);
            model.setValueAt(nuevaDireccion, selectedRow, 2);
            model.setValueAt(nuevoTelefono, selectedRow, 3);

            try {
                Connection conexion = conexionBD.getConnection();
                String consulta = "UPDATE proveedor SET NombreProveedor = ?, Direccion = ?, Telefono = ? WHERE IdProveedor = ?";
                PreparedStatement pst = conexion.prepareStatement(consulta);
                pst.setString(1, nuevoNombreProveedor);
                pst.setString(2, nuevaDireccion);
                pst.setString(3, nuevoTelefono);
                pst.setInt(4, idProveedor);
                pst.executeUpdate();

                pst.close();
                conexion.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error al actualizar el proveedor: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    } else {
        JOptionPane.showMessageDialog(this, "Seleccione un proveedor de la tabla para modificar.", "Proveedor no seleccionado", JOptionPane.WARNING_MESSAGE);
    }
}



public class ModificarProveedorDialog extends JDialog {
    private JTextField txtNombreProveedor;
    private JTextField txtDireccion;
    private JTextField txtTelefono;
    private JButton btnGuardar;
    private JButton btnCancelar;
    private boolean isModified = false;

    public ModificarProveedorDialog(Frame parent, String nombreProveedor, String direccion, String telefono) {
        super(parent, true);
        setTitle("Modificar Proveedor");
        setSize(400, 250);
        setLayout(new BorderLayout());

        JPanel panelCampos = new JPanel(new GridBagLayout());
        add(panelCampos, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 1.0;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelCampos.add(new JLabel("Nombre Proveedor:"), gbc);

        txtNombreProveedor = new JTextField(nombreProveedor);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panelCampos.add(txtNombreProveedor, gbc);

        JLabel lblDireccion = new JLabel("Dirección:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelCampos.add(lblDireccion, gbc);

        txtDireccion = new JTextField(direccion);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panelCampos.add(txtDireccion, gbc);

        JLabel lblTelefono = new JLabel("Teléfono:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelCampos.add(lblTelefono, gbc);

        txtTelefono = new JTextField(telefono);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panelCampos.add(txtTelefono, gbc);

        // Restringir txtTelefono a solo números
        // Restringir txtTelefono a solo números, permitiendo Backspace y Delete
txtTelefono.addKeyListener(new KeyAdapter() {
    @Override
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();

        // Permitir solo dígitos, tecla de retroceso y tecla de suprimir
        if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE) {
            e.consume();  // Ignora el carácter
            JOptionPane.showMessageDialog(null, "Solo se permiten números en el campo Teléfono.", "Entrada inválida", JOptionPane.WARNING_MESSAGE);
        }
    }
});

        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        add(panelBoton, BorderLayout.SOUTH);

        btnGuardar = new JButton("Guardar");
        btnGuardar.setVisible(false); 
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isModified = true;
                dispose();
            }
        });
        panelBoton.add(btnGuardar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isModified = false;
                dispose();
            }
        });
        panelBoton.add(btnCancelar);

        addChangeListeners(nombreProveedor, direccion, telefono);
        setLocationRelativeTo(parent);
    }

    private void addChangeListeners(String nombreOriginal, String direccionOriginal, String telefonoOriginal) {
        DocumentListener changeListener = new DocumentListener() {
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
                if (!txtNombreProveedor.getText().equals(nombreOriginal) ||
                    !txtDireccion.getText().equals(direccionOriginal) ||
                    !txtTelefono.getText().equals(telefonoOriginal)) {
                    btnGuardar.setVisible(true);
                } else {
                    btnGuardar.setVisible(false);
                }
            }
        };

        txtNombreProveedor.getDocument().addDocumentListener(changeListener);
        txtDireccion.getDocument().addDocumentListener(changeListener);
        txtTelefono.getDocument().addDocumentListener(changeListener);
    }

    public boolean isModified() {
        return isModified;
    }

    public String getNombreProveedor() {
        return txtNombreProveedor.getText();
    }

    public String getDireccion() {
        return txtDireccion.getText();
    }

    public String getTelefono() {
        return txtTelefono.getText();
    }
}


     
    
                                      

    private void listarProveedoresActivosActionPerformed(java.awt.event.ActionEvent evt) {                                         
        ///LISTA TODO LOS PROVEEDORES ACTIVOS
         try {
        // Establecer conexión a la base de datos
        Connection conexion = conexionBD.getConnection();
        // Consulta SQL para obtener todos los proveedor activos
        String consulta = "SELECT IdProveedor,NombreProveedor,Direccion,Telefono " +
                          "FROM proveedor    WHERE Estado = 1 ";
        PreparedStatement pst = conexion.prepareStatement(consulta);
        ResultSet rs = pst.executeQuery();

        // Limpiar la tabla antes de agregar nuevos datos
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        model.setRowCount(0);

        // Procesar resultados y agregar a la tabla
        while (rs.next()) {
            int idProveedor = rs.getInt("IdProveedor");
            String nombreProveedor = rs.getString("NombreProveedor");
            String direccion = rs.getString("Direccion");
            String telefono = rs.getString("Telefono");
           

            // Agregar fila al modelo de la tabla
            model.addRow(new Object[]{ idProveedor, nombreProveedor, direccion, telefono});
        }
        
        
        updateButtonVisibility();

        // Cerrar recursos
        rs.close();
        pst.close();
        conexion.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al listar proveedores: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }}               
    
    
    
    
    
    
    
    
    
    
private void agregarProveedorActionPerformed(java.awt.event.ActionEvent evt) {
    // Abrir el diálogo de agregar
    AgregarProveedorDialog agregarDialog = new AgregarProveedorDialog((JFrame) SwingUtilities.getWindowAncestor(this));
    agregarDialog.setVisible(true);
    
    if (agregarDialog.isModified()) {
        // Obtener los datos agregar
        String nombreProveedor = agregarDialog.getNombreProveedor();
        String direccion = agregarDialog.getDireccion();
        String telefono = agregarDialog.getTelefono();

        // Validar que el nombre del proveedor no esté vacío
        if (nombreProveedor.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre del proveedor es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            return; // No continuar si no hay nombre
        }

        // Verificar si la dirección o el teléfono están vacíos y mostrar un mensaje informativo
        if (direccion.trim().isEmpty() || telefono.trim().isEmpty()) {
            String mensaje = "Atención: ";
            if (direccion.trim().isEmpty()) {
                mensaje += "La dirección no fue cargada. ";
            }
            if (telefono.trim().isEmpty()) {
                mensaje += "El teléfono no fue cargado.";
            }
            JOptionPane.showMessageDialog(this, mensaje, "Advertencia", JOptionPane.WARNING_MESSAGE);
        }

        Connection conexion = null;
        PreparedStatement pst = null;

        try {
            conexion = conexionBD.getConnection();
            String consulta = "INSERT INTO proveedor (NombreProveedor, Direccion, Telefono) VALUES (?, ?, ?)";
            pst = conexion.prepareStatement(consulta);

            // Establecer valores
            pst.setString(1, nombreProveedor);
            pst.setString(2, direccion.isEmpty() ? "" : direccion); // Cargar cadena vacía si no se proporcionó dirección
            pst.setString(3, telefono.isEmpty() ? "" : telefono); // Cargar cadena vacía si no se proporcionó teléfono

            pst.executeUpdate();

            JOptionPane.showMessageDialog(this, "Proveedor agregado con éxito");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al agregar el proveedor: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Asegurarse de cerrar los recursos
            try {
                if (pst != null) pst.close();
                if (conexion != null) conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}



   public class AgregarProveedorDialog extends JDialog {
    private JTextField txtNombreProveedor;
    private JTextField txtDireccion;
    private JTextField txtTelefono;
    private JButton btnGuardar;
    private JButton btnCancelar;
    private boolean isModified = false;

    public AgregarProveedorDialog(Frame parent) {
        // Modificar proveedor
        super(parent, true);
        setTitle("Agregar Proveedor");
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

        // Campo de Nombre Proveedor
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelCampos.add(new JLabel("Nombre Proveedor:"), gbc);

        txtNombreProveedor = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 0;
        panelCampos.add(txtNombreProveedor, gbc);

        // Campo de Dirección
        JLabel lblDireccion = new JLabel("Dirección:");
        lblDireccion.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelCampos.add(lblDireccion, gbc);

        txtDireccion = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 1;
        panelCampos.add(txtDireccion, gbc);

        // Campo de Teléfono
        JLabel lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelCampos.add(lblTelefono, gbc);

        txtTelefono = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 2;
        panelCampos.add(txtTelefono, gbc);

        // Restringir txtTelefono a solo números, permitiendo Backspace y Delete
        txtTelefono.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();

                // Permitir solo dígitos, tecla de retroceso y tecla de suprimir
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE) {
                    e.consume();  // Ignora el carácter
                    JOptionPane.showMessageDialog(null, "Solo se permiten números en el campo Teléfono.", "Entrada inválida", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
;

        // Panel para los botones
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        add(panelBoton, BorderLayout.SOUTH); // Agrega el panel al sur del diálogo

        btnGuardar = new JButton("Guardar");
        btnGuardar.setVisible(false); // Inicialmente, el botón "Guardar" está oculto
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isProveedorExists(txtNombreProveedor.getText())) {
                    JOptionPane.showMessageDialog(AgregarProveedorDialog.this,
                            "El proveedor ya existe en la base de datos.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    btnGuardar.setVisible(false);
                } else {
                    isModified = true;
                    dispose();
                }
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
        txtNombreProveedor.getDocument().addDocumentListener(new DocumentListener() {
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
                if (!txtNombreProveedor.getText().trim().isEmpty()) {
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

    public String getNombreProveedor() {
        return txtNombreProveedor.getText();
    }

    public String getDireccion() {
        return txtDireccion.getText();
    }

    public String getTelefono() {
        return txtTelefono.getText();
    }

    private boolean isProveedorExists(String nombreProveedor) {
        String sql = "SELECT COUNT(*) FROM proveedor WHERE NombreProveedor = ?";
        try (Connection conn = conexionBD.getConnection(); // Usa el método getConnection de conexionBD
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombreProveedor);
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



        
    
    private void eliminarProveedorActionPerformed(java.awt.event.ActionEvent evt) {    
          int selectedRow = tabla.getSelectedRow();
    int[] selectedRows = tabla.getSelectedRows();
    // Cambiar los textos de los botones a español 
    UIManager.put("OptionPane.yesButtonText", "Sí"); 
    UIManager.put("OptionPane.noButtonText", "No");
    if (selectedRows.length == 1) {
        int confirm = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que quieres deshabilitar este proveedor?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) { // Actualizar la base de datos 
            System.out.println("Base de datos actualizada."); // Actualizar la base de datos
            try {
                Connection conexion = conexionBD.getConnection();   
                int columna = 0; // Índice de la columna que contiene el dato numérico

                // Verificar que se haya seleccionado una fila
                    Object valor = tabla.getValueAt(selectedRow, columna); // Obtener el valor de la celda
                    if (valor instanceof Number) { // Verificar que el valor sea numérico
                        Number numero = (Number) valor;
                        int datoNumerico = (int) numero.intValue(); // Convertir a tipo numérico
                        String consulta = "UPDATE proveedor SET Estado=0  WHERE IdProveedor=?";
                       

                PreparedStatement pst = conexion.prepareStatement(consulta);
                pst.setInt(1, datoNumerico);
                pst.executeUpdate();

// Eliminar la fila de la tabla
                    DefaultTableModel model = (DefaultTableModel) tabla.getModel();
                    model.removeRow(selectedRow);
                
                pst.close();
                conexion.close();
                
                JOptionPane.showMessageDialog(this, "Proveedor eliminado con éxito");
                    } 
                
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error al eliminar el proveedor: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
    }
    } else {
        JOptionPane.showMessageDialog(null, "Seleccione un proveedor de la tabla para eliminar.", "Proveedor no seleccionado", JOptionPane.WARNING_MESSAGE);
    }
    } 
    
     private void updateButtonVisibility() {
    DefaultTableModel model = (DefaultTableModel) tabla.getModel();
    boolean hasRows = model.getRowCount() > 0;

    botonQuitar.setEnabled(hasRows); // Botón "Quitar"
    botonModificar.setEnabled(hasRows); // Botón "Modificar"
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
            java.util.logging.Logger.getLogger(Proveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Proveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Proveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Proveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Proveedor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JTextField Id;
   
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration                   
}


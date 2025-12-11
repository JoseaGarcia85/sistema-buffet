package Buscar;


import Conexion.conexionBD;
import java.awt.Frame;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import java.awt.Insets;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.text.*;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.text.NumberFormat;



public class ModificarProducto extends JInternalFrame  {
    private DefaultTableModel model;
    private JTable jTabla1;
    private JTextField Id; // Asegúrate de que esta variable esté inicializada en tu clase



    public ModificarProducto() {
         setTitle("Configuraciones de Producto");
        setClosable(true);
        setMaximizable(true);
        setIconifiable(true);
        setResizable(true);
        setSize(800, 600);
        setLocation(100, 100);
        initComponents();
        initNumericFilter();

        pack();
    }

    public ModificarProducto(int rolUsuario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

    idLabel = new JLabel("Id Producto");
    Id = new javax.swing.JTextField();
    jScrollPane2 = new javax.swing.JScrollPane();
    tabla = new javax.swing.JTable();
    botonBuscar = new JButton("Buscar");
    botonLimpiarTabla = new JButton("Limpiar Tabla");
    botonListar = new JButton("Listar");
    botonQuitar = new JButton("Desactivar Producto");
    botonModificar = new JButton("Modificar");
    botonAgregar = new JButton("Agregar");
    botonEliminarProveedor = new JButton("Eliminar"); // Asegúrate de inicializar este botón

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
       

        tabla.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 18)); // NOI18N
        tabla.setForeground(new java.awt.Color(51, 51, 51));
        
        tabla.setModel(new javax.swing.table.DefaultTableModel(
    new Object[][] {},
    new String[] {
        "IdProducto", "NombreProducto", "Descripcion", "Categoria", "Precio Unitario", "Precio Venta" // Agrega "Precio Venta" aquí
    }) 
        {
        Class[] types = new Class[] {
            java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
        };
        boolean[] canEdit = new boolean[] {
            false, false, false, false, false, false
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
            tabla.getColumnModel().getColumn(0).setHeaderValue("Id Producto");
            tabla.getColumnModel().getColumn(1).setHeaderValue("Nombre Producto");
            tabla.getColumnModel().getColumn(2).setHeaderValue("Descripcion");
            tabla.getColumnModel().getColumn(3).setHeaderValue("Categoria");
            tabla.getColumnModel().getColumn(4).setHeaderValue("Precio Unitario");
            tabla.getColumnModel().getColumn(5).setHeaderValue("Precio Venta"); // Nuevo encabezado

        }

        botonLimpiarTabla.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 17)); // NOI18N
        botonLimpiarTabla.setEnabled(false);
        botonLimpiarTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiarTablaActionPerformed(evt);
            }
        });

        botonListar.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 17)); // NOI18N
        botonListar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listarProveedoresActivosActionPerformed(evt);
            }
        });

        botonQuitar.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 17)); // NOI18N
        botonQuitar.setEnabled(false);
        botonQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitarProveedorTablaActionPerformed(evt);
            }
        });

        botonModificar.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 17)); // NOI18N
        botonModificar.setEnabled(false);
        botonModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarProductoActionPerformed(evt);
            }
        });

        botonAgregar.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 17)); // NOI18N
        botonAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarProductoActionPerformed(evt);
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
                        .addComponent(botonAgregar))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(58, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>
                     

    
    
    
private void buscarActivoActionPerformed(java.awt.event.ActionEvent evt) {
    // Obtener el valor del campo de texto para el id del producto
    String id = Id.getText().trim();

    // Verificar si el campo está vacío
    if (id.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Por favor, ingrese un ID del Producto.", "Campo vacío", JOptionPane.WARNING_MESSAGE);
        return;  // No procede si el campo está vacío
    }

    // Validar si el ID ingresado es numérico
    if (!id.matches("\\d+")) {
        JOptionPane.showMessageDialog(null, "El ID del Producto debe ser un número válido.", "ID no válido", JOptionPane.ERROR_MESSAGE);
        return;  // No proceder si el ID no es numérico
    }

    // Consulta SQL para buscar el producto por su id, y hacer JOIN con la tabla categoria
    String consulta = "SELECT p.IdProducto, p.NombreProducto, p.Descripcion, c.NombreCategoria, p.Estado, s.Precio, s.Precio_Venta " +
                      "FROM producto p " +
                      "JOIN categoria c ON p.IdCategoria = c.IdCategoria " +
                      "JOIN stock s ON p.IdProducto = s.IdProducto " +
                      "WHERE p.IdProducto = ?";
    
    Connection conexion = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    try {
        // Establecer conexión a la base de datos
        conexion = conexionBD.getConnection();
        if (conexion == null) {
            throw new SQLException("No se pudo obtener una conexión a la base de datos.");
        }

        pst = conexion.prepareStatement(consulta);
        pst.setString(1, id);
        rs = pst.executeQuery();

        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        model.setRowCount(0);
        boolean productoEncontrado = false;

        // Crear un formateador de moneda
        NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance();

        while (rs.next()) {
            productoEncontrado = true;

            int idProducto = rs.getInt("IdProducto");
            String nombreProducto = rs.getString("NombreProducto");
            String descripcion = rs.getString("Descripcion");
            String nombreCategoria = rs.getString("NombreCategoria"); // Obtener el nombre de la categoría
            int estado = rs.getInt("Estado");
            float precio = rs.getFloat("Precio");
            float preciounitario = rs.getFloat("Precio_Venta");

            // Formatear los precios a moneda
            String precioFormateado = formatoMoneda.format(precio);
            String precioVentaFormateado = formatoMoneda.format(preciounitario);

            if (estado == 0) {
                // Opciones personalizadas en español
                Object[] opciones = {"Sí", "No"};
                int respuesta = JOptionPane.showOptionDialog(null,
                        "El producto está inactivo. ¿Desea activarlo?",
                        "Activar Producto",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null, // Icono predeterminado
                        opciones, // Botones personalizados
                        opciones[0]); // Botón predeterminado (Sí)

                if (respuesta == 0) { // Si el usuario selecciona "Sí"
                    // Actualizar el estado del producto a 1 (activo)
                    String updateConsulta = "UPDATE producto SET Estado = 1 WHERE IdProducto = ?";
                    try (PreparedStatement updatePst = conexion.prepareStatement(updateConsulta)) {
                        updatePst.setString(1, id);
                        int filasAfectadas = updatePst.executeUpdate();
                        if (filasAfectadas > 0) {
                            JOptionPane.showMessageDialog(null, "Producto activado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "No se pudo activar el producto.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else { // Si el usuario selecciona "No"
                    JOptionPane.showMessageDialog(null, "El producto permanece inactivo.", "Información", JOptionPane.INFORMATION_MESSAGE);
                }
            }

            // Agregar el producto encontrado a la tabla, mostrando el nombre de la categoría en lugar del IdCategoria
            Object[] fila = { idProducto, nombreProducto, descripcion, nombreCategoria, precioFormateado, precioVentaFormateado};
            model.addRow(fila);
        }

        if (!productoEncontrado) {
            JOptionPane.showMessageDialog(null, "No se encontró producto con el ID ingresado.", "Producto no encontrado", JOptionPane.WARNING_MESSAGE);
        }

        if (model.getRowCount() > 0) {
            updateButtonVisibility();
        }

        // Limpiar el cuadro del ID del producto después de la búsqueda
        Id.setText(""); // Línea añadida para limpiar el cuadro

    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al buscar el producto: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    } finally {
        // Cerrar los recursos
        try {
            if (rs != null) rs.close();
            if (pst != null) pst.close();
            if (conexion != null) conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al cerrar los recursos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}





// Clase interna para filtrar la entrada numérica
private class NumericDocumentFilter extends DocumentFilter {
    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        if (string == null) return;
        if (string.matches("[0-9]*")) { // Permitir solo números
            super.insertString(fb, offset, string, attr);
        } else {
            JOptionPane.showMessageDialog(null, "Solo se permiten números.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        if (text == null) return;
        if (text.matches("[0-9]*")) { // Permitir solo números
            super.replace(fb, offset, length, text, attrs);
        } else {
            JOptionPane.showMessageDialog(null, "Solo se permiten números.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
        super.remove(fb, offset, length);
    }
}

// Método para inicializar el filtro en el campo de texto
private void initNumericFilter() {
    ((AbstractDocument) Id.getDocument()).setDocumentFilter(new NumericDocumentFilter());
}



 private void quitarProveedorTablaActionPerformed(java.awt.event.ActionEvent evt) {                                         
    // Obtener la fila seleccionada en la tabla
    int[] selectedRows = tabla.getSelectedRows();

    if (selectedRows.length == 0) {
        // No se ha seleccionado ninguna fila
        JOptionPane.showMessageDialog(null, "Debe seleccionar un producto de la tabla para desactivar.", "Atención", JOptionPane.WARNING_MESSAGE);
        return;
    } else if (selectedRows.length > 1) {
        // Se han seleccionado varias filas
        JOptionPane.showMessageDialog(null, "Debe seleccionar solo un producto a la vez para desactivar.", "Atención", JOptionPane.WARNING_MESSAGE);
        return;
    }

    // Solo una fila está seleccionada
    int selectedRow = selectedRows[0];
    DefaultTableModel model = (DefaultTableModel) tabla.getModel();
    int idProducto = (int) model.getValueAt(selectedRow, 0); // Suponiendo que el ID está en la primera columna

    // Mostrar confirmación antes de desactivar
    // Mostrar el diálogo de confirmación
        Object[] opciones = {"Sí", "No"};
        int confirmacion = JOptionPane.showOptionDialog(null,
        "¿Está seguro de que desea desactivar el producto?",
        "Confirmar Desactivación",
        JOptionPane.DEFAULT_OPTION,
        JOptionPane.QUESTION_MESSAGE,
        null, // Icono predeterminado
        opciones, // Botones personalizados
        opciones[0]); // Botón predeterminado (Sí)

    // Comprobar la respuesta del usuario
    if (confirmacion == 0) { // 0 corresponde a "Sí"
        Connection conexion = null;
        PreparedStatement pst = null;
            try {
                // Establecer conexión a la base de datos
                conexion = conexionBD.getConnection();

                // Actualizar el estado del producto a 0 (inactivo)
                String updateConsulta = "UPDATE producto SET Estado = 0 WHERE IdProducto = ?";
                pst = conexion.prepareStatement(updateConsulta);
                pst.setInt(1, idProducto);

                int filasAfectadas = pst.executeUpdate();
                if (filasAfectadas > 0) {
                    // Eliminar la fila de la tabla
                    model.removeRow(selectedRow);
                    JOptionPane.showMessageDialog(null, "Producto desactivado exitosamente.", "Desactivación exitosa", JOptionPane.INFORMATION_MESSAGE);

                    // Deshabilitar botones si la tabla está vacía
                    if (model.getRowCount() == 0) {
                        updateButtonVisibility(); // Actualizar visibilidad de botones
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo desactivar el producto. Verifique si el ID es correcto.", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al desactivar el producto.", "Error", JOptionPane.ERROR_MESSAGE);
            } finally {
                // Cerrar los recursos
                conexionBD.cerrarRecursos(null, pst, conexion);
            }
        }
    }
public static void cerrarRecursos(ResultSet rs, PreparedStatement pst, Connection conexion) {
    try {
        if (rs != null) {
            rs.close();
        }
    } catch (SQLException e) {
        e.printStackTrace(); // Puedes manejar el error aquí o registrar el error
    }
    
    try {
        if (pst != null) {
            pst.close();
        }
    } catch (SQLException e) {
        e.printStackTrace(); // Puedes manejar el error aquí o registrar el error
    }
    
    try {
        if (conexion != null) {
            conexion.close();
        }
    } catch (SQLException e) {
        e.printStackTrace(); // Puedes manejar el error aquí o registrar el error
    }
}

                                    

  private void modificarProductoActionPerformed(java.awt.event.ActionEvent evt) {                                          
    // Obtener las filas seleccionadas en la tabla
    int[] selectedRows = tabla.getSelectedRows();

    // Verificar si se ha seleccionado ninguna fila
    if (selectedRows.length == 0) {
        JOptionPane.showMessageDialog(null, "Debe seleccionar un producto de la tabla para modificar.", "Atención", JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    // Verificar si se han seleccionado varias filas
    if (selectedRows.length > 1) {
        JOptionPane.showMessageDialog(null, "Debe seleccionar solo un producto a la vez para modificar.", "Atención", JOptionPane.WARNING_MESSAGE);
        return;
    }

    // Solo una fila está seleccionada
    int selectedRow = selectedRows[0];
    DefaultTableModel model = (DefaultTableModel) tabla.getModel();
    int idProducto = (int) model.getValueAt(selectedRow, 0);
    String nombreProducto = (String) model.getValueAt(selectedRow, 1);
    String descripcion = (String) model.getValueAt(selectedRow, 2);
    String categoriaSeleccionada = (String) model.getValueAt(selectedRow, 3);

    // Obtener el Frame que contiene el JInternalFrame actual
    Frame parentFrame = (Frame) SwingUtilities.getWindowAncestor(this);

    // Crear y mostrar el diálogo de modificación
    ModificarProductoDialog dialog = new ModificarProductoDialog(parentFrame, nombreProducto, descripcion, categoriaSeleccionada);
    dialog.setVisible(true);

    // Procesar los cambios si se modificó
    if (dialog.isModified()) {
        Connection conexion = null;
        PreparedStatement pst = null;
        PreparedStatement pstCategoria = null;
        ResultSet rs = null;

        try {
            // Establecer conexión a la base de datos
            conexion = conexionBD.getConnection();

            // Obtener el IdCategoria basado en el nombre de la categoría seleccionada
            String queryCategoria = "SELECT IdCategoria FROM categoria WHERE NombreCategoria = ?";
            pstCategoria = conexion.prepareStatement(queryCategoria);
            pstCategoria.setString(1, dialog.getIdCategoria());
            rs = pstCategoria.executeQuery();

            int idCategoria = -1; // Valor por defecto si no se encuentra la categoría
            if (rs.next()) {
                idCategoria = rs.getInt("IdCategoria");
            }

            // Si se encontró un IdCategoria válido, actualizar el producto
            if (idCategoria != -1) {
                String updateConsulta = "UPDATE producto SET NombreProducto = ?, Descripcion = ?, IdCategoria = ? WHERE IdProducto = ?";
                pst = conexion.prepareStatement(updateConsulta);
                pst.setString(1, dialog.getNombreProducto());
                pst.setString(2, dialog.getDescripcion());
                pst.setInt(3, idCategoria); // Guardar el IdCategoria en lugar del nombre
                pst.setInt(4, idProducto);

                int filasAfectadas = pst.executeUpdate();
                if (filasAfectadas > 0) {
                    // Actualizar la fila en la tabla
                    model.setValueAt(dialog.getNombreProducto(), selectedRow, 1);
                    model.setValueAt(dialog.getDescripcion(), selectedRow, 2);
                    model.setValueAt(dialog.getIdCategoria(), selectedRow, 3); // Esto es el nombre de la categoría
                    JOptionPane.showMessageDialog(null, "Producto modificado exitosamente.", "Modificación exitosa", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo modificar el producto. Verifique si el ID es correcto.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró la categoría especificada.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al modificar el producto.", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Cerrar los recursos
            conexionBD.cerrarRecursos(rs, pstCategoria, conexion);
            conexionBD.cerrarRecursos(null, pst, null);
        }
    }
}


   
   public class ModificarProductoDialog extends JDialog {
    private JTextField txtNombreProducto;
    private JTextField txtDescripcion;
    private JComboBox<String> comboBoxIdCategoria; // Cambiar a JComboBox
    private JButton btnGuardar;
    private JButton btnCancelar;
    private boolean isModified = false;
    
    private String originalNombreProducto;
    private String originalDescripcion;
    private String originalIdCategoria;

    public ModificarProductoDialog(Frame parent, String nombreProducto, String descripcion, String idCategoria) {
        super(parent, true);
        setTitle("Modificar Producto");
        setSize(400, 250);
        setLayout(new BorderLayout());

        originalNombreProducto = nombreProducto;
        originalDescripcion = descripcion;
        originalIdCategoria = idCategoria;

        JPanel panelCampos = new JPanel(new GridBagLayout());
        add(panelCampos, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 1.0;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelCampos.add(new JLabel("Nombre Producto:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        txtNombreProducto = new JTextField(nombreProducto);
        txtNombreProducto.setPreferredSize(new Dimension(200, 25));
        panelCampos.add(txtNombreProducto, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelCampos.add(new JLabel("Descripción:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        txtDescripcion = new JTextField(descripcion);
        txtDescripcion.setPreferredSize(new Dimension(200, 25));
        panelCampos.add(txtDescripcion, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panelCampos.add(new JLabel("Categoría:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;

        // Inicializar JComboBox con categorías
        comboBoxIdCategoria = new JComboBox<>();
        cargarCategorias(); // Método que carga las categorías desde la base de datos
        comboBoxIdCategoria.setSelectedItem(idCategoria); // Seleccionar la categoría actual
        panelCampos.add(comboBoxIdCategoria, gbc);

        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        add(panelBoton, BorderLayout.SOUTH);

        btnGuardar = new JButton("Guardar");
        btnGuardar.setEnabled(false); // Desactivado inicialmente
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

        // Agregar DocumentListeners para verificar cambios
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

        txtNombreProducto.getDocument().addDocumentListener(documentListener);
        txtDescripcion.getDocument().addDocumentListener(documentListener);

        setLocationRelativeTo(parent);
    }

    private void cargarCategorias() {
        // Método que carga las categorías desde la base de datos
        try {
            Connection conexion = conexionBD.getConnection();
            String consulta = "SELECT NombreCategoria FROM categoria"; // Asegúrate de que la consulta sea correcta
            PreparedStatement pst = conexion.prepareStatement(consulta);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                comboBoxIdCategoria.addItem(rs.getString("NombreCategoria")); // Agregar nombre de la categoría al JComboBox
            }

            // Cerrar recursos
            rs.close();
            pst.close();
            conexion.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar categorías: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void checkForChanges() {
        String currentNombreProducto = txtNombreProducto.getText();
        String currentDescripcion = txtDescripcion.getText();
        String currentIdCategoria = (String) comboBoxIdCategoria.getSelectedItem(); // Obtener el ID de categoría del JComboBox

        // Habilitar el botón solo si hay cambios en los campos
        boolean isChanged = !currentNombreProducto.equals(originalNombreProducto) ||
                            !currentDescripcion.equals(originalDescripcion) ||
                            !currentIdCategoria.equals(originalIdCategoria);

        btnGuardar.setEnabled(isChanged);
    }

    public boolean isModified() {
        return isModified;
    }

    public String getNombreProducto() {
        return txtNombreProducto.getText();
    }

    public String getDescripcion() {
        return txtDescripcion.getText();
    }

    public String getIdCategoria() {
        return (String) comboBoxIdCategoria.getSelectedItem(); // Devolver el nombre de la categoría seleccionada
    }
}

                                      

  private void listarProveedoresActivosActionPerformed(java.awt.event.ActionEvent evt) {                                         

    try {
        Connection conexion = conexionBD.getConnection();
        
        String consulta = "SELECT p.IdProducto, p.NombreProducto, p.Descripcion, c.NombreCategoria, s.Precio, s.Precio_Venta " +
                          "FROM producto p " +
                          "JOIN categoria c ON p.IdCategoria = c.IdCategoria " +
                          "JOIN stock s ON p.IdProducto = s.IdProducto " +
                          "WHERE p.Estado = 1";
        
        PreparedStatement pst = conexion.prepareStatement(consulta);
        ResultSet rs = pst.executeQuery();

        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        model.setRowCount(0);

        // Crear el formato de moneda
        NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance();

        while (rs.next()) {
            int idProducto = rs.getInt("IdProducto");
            String nombreProducto = rs.getString("NombreProducto");
            String descripcion = rs.getString("Descripcion");
            String nombreCategoria = rs.getString("NombreCategoria");
            float precio = rs.getFloat("Precio");
            float precioVenta = rs.getFloat("Precio_Venta");

            // Formatear precio y precioVenta en formato de moneda
            String precioMoneda = formatoMoneda.format(precio);
            String precioVentaMoneda = formatoMoneda.format(precioVenta);

            model.addRow(new Object[]{ idProducto, nombreProducto, descripcion, nombreCategoria, precioMoneda, precioVentaMoneda });
        }
        
        updateButtonVisibility();

        rs.close();
        pst.close();
        conexion.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al listar productos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}

            
    
   
// Definición del método agregar producto
private void agregarProductoActionPerformed(java.awt.event.ActionEvent evt) {
    // Mostrar el diálogo para agregar un producto
    AgregarProductoDialog dialog = new AgregarProductoDialog((Frame) SwingUtilities.getWindowAncestor(this));
    dialog.setVisible(true);

    // Verificar si el usuario ha decidido agregar un producto
    if (dialog.isAdded()) {
        String nombreProducto = dialog.getNombreProducto();
        String descripcion = dialog.getDescripcion();
        String idCategoria = dialog.getIdCategoria(); // Obtiene el ID de la categoría desde el JComboBox

        // Validar que los campos no estén vacíos
        if (nombreProducto.trim().isEmpty() || descripcion.trim().isEmpty() || idCategoria == null) {
            JOptionPane.showMessageDialog(this, "Todos los campos deben estar completos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Connection conexion = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            // Establecer conexión a la base de datos
            conexion = conexionBD.getConnection();

            // Consulta SQL para insertar un nuevo producto
            String consultaProducto = "INSERT INTO producto (NombreProducto, Descripcion, IdCategoria, Estado) VALUES (?, ?, ?, 1)";
            pst = conexion.prepareStatement(consultaProducto, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, nombreProducto);
            pst.setString(2, descripcion);
            pst.setString(3, idCategoria);
            int filasAfectadas = pst.executeUpdate();

            // Verificar si la inserción del producto fue exitosa
            if (filasAfectadas > 0) {
                // Obtener el IdProducto generado
                rs = pst.getGeneratedKeys();
                if (rs.next()) {
                    int idProducto = rs.getInt(1);

                    // Insertar en la tabla stock
                    String consultaStock = "INSERT INTO stock (IdProducto, Cantidad, Precio) VALUES (?, 0, 0)";
                    pst = conexion.prepareStatement(consultaStock);
                    pst.setInt(1, idProducto);
                    pst.executeUpdate();

                    JOptionPane.showMessageDialog(this, "Producto agregado exitosamente.");
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo obtener el ID del producto.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo agregar el producto.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al agregar el producto.", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            conexionBD.cerrarRecursos(rs, pst, conexion);
        }
    }
}



   
   
   private void listarProductosActivos() {
    // Implementación para listar productos activos en la tabla
    try {
        Connection conexion = conexionBD.getConnection();
        String consulta = "SELECT IdProducto, NombreProducto, Descripcion, IdCategoria FROM producto WHERE Estado = 1";
        PreparedStatement pst = conexion.prepareStatement(consulta);
        ResultSet rs = pst.executeQuery();

        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        model.setRowCount(0); // Limpiar la tabla antes de agregar nuevos datos

        while (rs.next()) {
            int idProducto = rs.getInt("IdProducto");
            String nombreProducto = rs.getString("NombreProducto");
            String descripcion = rs.getString("Descripcion");
            String idCategoria = rs.getString("IdCategoria");
            model.addRow(new Object[]{ idProducto, nombreProducto, descripcion, idCategoria });
        }

        rs.close();
        pst.close();
        conexion.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al listar productos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}


public class AgregarProductoDialog extends JDialog {
    private JTextField txtNombreProducto;
    private JTextField txtDescripcion;
    private JComboBox<String> comboBoxCategoria; // Usamos JComboBox en lugar de JTextField para Categoría
    private JButton btnAgregar;
    private JButton btnCancelar;
    private boolean added = false;
    private Map<String, Integer> categoriasMap = new HashMap<>(); // Mapa para almacenar categorías con sus IDs

    public AgregarProductoDialog(Frame parent) {
        super(parent, true);
        setTitle("Agregar Producto");
        setSize(400, 250);
        setLayout(new BorderLayout());

        // Panel para los campos de texto usando GridBagLayout
        JPanel panelCampos = new JPanel(new GridBagLayout());
        add(panelCampos, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espaciado entre componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        // Nombre Producto
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelCampos.add(new JLabel("Nombre Producto:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        txtNombreProducto = new JTextField();
        panelCampos.add(txtNombreProducto, gbc);

        // Descripción
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        panelCampos.add(new JLabel("Descripción:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        txtDescripcion = new JTextField();
        panelCampos.add(txtDescripcion, gbc);

        // ComboBox para Categoría
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        panelCampos.add(new JLabel("Categoría:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        comboBoxCategoria = new JComboBox<>();
        cargarCategorias(); // Cargar categorías desde la base de datos
        panelCampos.add(comboBoxCategoria, gbc);

        // Panel para los botones
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        add(panelBoton, BorderLayout.SOUTH);

        btnAgregar = new JButton("Agregar");
        btnAgregar.setEnabled(false); // Deshabilitado inicialmente
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                added = true;
                dispose();
            }
        });
        panelBoton.add(btnAgregar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());
        panelBoton.add(btnCancelar);

        // Agregar DocumentListener a los campos de texto
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

        txtNombreProducto.getDocument().addDocumentListener(documentListener);
        txtDescripcion.getDocument().addDocumentListener(documentListener);

        setLocationRelativeTo(parent);
    }

    // Método para cargar las categorías desde la base de datos
    private void cargarCategorias() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = conexionBD.getConnection();
            String sql = "SELECT IdCategoria, NombreCategoria FROM categoria";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                int idCategoria = rs.getInt("IdCategoria");
                String nombreCategoria = rs.getString("NombreCategoria");
                comboBoxCategoria.addItem(nombreCategoria);
                categoriasMap.put(nombreCategoria, idCategoria); // Guardar ID y nombre en el mapa
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Método para verificar si los campos de texto están llenos
    private void checkForChanges() {
        boolean allFieldsFilled = !txtNombreProducto.getText().trim().isEmpty() &&
                                  !txtDescripcion.getText().trim().isEmpty() &&
                                  comboBoxCategoria.getSelectedItem() != null;
        btnAgregar.setEnabled(allFieldsFilled);
    }

    public boolean isAdded() {
        return added;
    }

    public String getNombreProducto() {
        return txtNombreProducto.getText();
    }

    public String getDescripcion() {
        return txtDescripcion.getText();
    }

    // Obtener el ID de la categoría seleccionada desde el ComboBox
    public String getIdCategoria() {
        String categoriaSeleccionada = (String) comboBoxCategoria.getSelectedItem();
        return categoriasMap.get(categoriaSeleccionada).toString(); // Devolver el ID como cadena
    }
}






    private void limpiarTablaActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        //LIMPIAR TABLA
    DefaultTableModel model = (DefaultTableModel) tabla.getModel();
    model.setRowCount(0);
    updateButtonVisibility();
    }       
    
 
    
     private void updateButtonVisibility() {
    DefaultTableModel model = (DefaultTableModel) tabla.getModel();
    boolean hasRows = model.getRowCount() > 0;

    botonQuitar.setEnabled(hasRows); // Botón "Quitar"
    botonModificar.setEnabled(hasRows); // Botón "Modificar"
    botonLimpiarTabla.setEnabled(hasRows); // Botón "Limpiar"
    botonEliminarProveedor.setEnabled(hasRows); // Botón "Eliminar"


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

    private javax.swing.JButton botonBuscar;
    private javax.swing.JButton botonQuitar;
    private javax.swing.JButton botonModificar;
    private javax.swing.JButton botonListar;
    private javax.swing.JButton botonAgregar;
    private javax.swing.JButton botonLimpiarTabla;
     private javax.swing.JButton botonEliminarProveedor;
    private javax.swing.JLabel idLabel;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tabla;
    // End of variables declaration                   
}
package Buscar;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import Conexion.conexionBD;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
 import java.text.DecimalFormat;


public class Compra extends JInternalFrame {

   private JLabel jLabelProducto, jLabelProveedor, jLabelIdProducto, jLabelDescripcion, jLabelPrecio, jLabelFecha, jLabelCantidad, jLabelNombreProducto, jLabelCategoria, jLabelStock;
    private JLabel jLabelIdProveedor, jLabelNombreProveedor, jLabelDireccion, jLabelTelefono, jLabelEstado;
    private JTextField jTextFieldIdProducto, jTextFieldDescripcion, jTextFieldPrecio, jTextFieldFecha, jTextFieldCantidad, jTextFieldNombreProducto, jTextFieldStock;
    private JTextField jTextFieldIdProveedor, jTextFieldDireccion, jTextFieldTelefono, jTextFieldEstado, jTextFieldCategoria;
    private JComboBox<String> jComboBoxProveedores;
    private JButton jButtonCargarProducto, jButtonNuevoProducto, jButtonNuevoProveedor, jButtonGuardarCompra, jButtonCancelar;
    private JTable jTableProductos;
    private JButton jButtonEliminarProducto; // Declara el botón "Eliminar Producto"
    private DefaultTableModel tableModel;
    private Timer timer;
    private static final int DELAY = 500; // Retraso en milisegundos

    public Compra() {
        initComponents(); 
        setFechaSistema(); // Configura la fecha del sistema
        initProveedores();

    }

private void initComponents() {
    setTitle("Gestión de Productos");
    setSize(800, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Inicialización de los componentes
    jLabelProducto = new JLabel("PRODUCTOS");
    jLabelProveedor = new JLabel("PROVEEDOR");
    jLabelIdProducto = new JLabel("Id Producto");
    jLabelDescripcion = new JLabel("Descripcion");
    jLabelPrecio = new JLabel("Precio Unitario");
    jLabelFecha = new JLabel("Fecha");
    jLabelCantidad = new JLabel("Cantidad");
    jLabelNombreProducto = new JLabel("Nombre Producto");
    jLabelCategoria = new JLabel("Categoria");
    jLabelStock = new JLabel("Stock Disponible");

    jLabelIdProveedor = new JLabel("Id Proveedor");
    jLabelNombreProveedor = new JLabel("Nombre Proveedor");
    jComboBoxProveedores = new JComboBox<>();
    jLabelDireccion = new JLabel("Direccion");
    jLabelTelefono = new JLabel("Telefono");
    jLabelEstado = new JLabel("Estado");

    jTextFieldIdProducto = new JTextField();
    jTextFieldDescripcion = new JTextField();
    jTextFieldDescripcion.setEditable(false);
    jTextFieldPrecio = new JTextField();
    jTextFieldFecha = new JTextField();
    jTextFieldFecha.setEditable(false);
    jTextFieldCantidad = new JTextField();
    jTextFieldNombreProducto = new JTextField();
    jTextFieldStock = new JTextField();
    jTextFieldStock.setEditable(false);

    jTextFieldIdProveedor = new JTextField();
    jTextFieldIdProveedor.setEditable(false);
    jTextFieldDireccion = new JTextField();
    jTextFieldDireccion.setEditable(false);
    jTextFieldTelefono = new JTextField();
    jTextFieldTelefono.setEditable(false);
    jTextFieldEstado = new JTextField();
    jTextFieldEstado.setEditable(false);

    jTextFieldCategoria = new JTextField();
    jTextFieldCategoria.setEditable(false);

    jButtonCargarProducto = new JButton("Cargar Producto");
    jButtonNuevoProducto = new JButton("Nuevo Producto");
    
    jButtonNuevoProducto.addActionListener(evt -> jButtonNuevoProductoActionPerformed(evt));
    jButtonNuevoProveedor = new JButton("Nuevo Proveedor");
    jButtonNuevoProveedor.addActionListener(evt -> jButtonNuevoProveedorActionPerformed(evt));

    
    jButtonGuardarCompra = new JButton("Guardar Compra");
    // Agregar ActionListener para el botón
    jButtonGuardarCompra.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            guardarCompra(); // Llamar al método que guarda la compra
        }
    });

    jButtonCancelar = new JButton("Cancelar");
    jButtonEliminarProducto = new JButton("Eliminar Producto"); // Nuevo botón
    jButtonEliminarProducto.setEnabled(false); // Inicialmente deshabilitado

    // Configuración del layout
    setLayout(new BorderLayout());

    // Panel de entrada (izquierda)
    JPanel inputPanel = new JPanel();
    inputPanel.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.insets = new Insets(5, 5, 5, 5);

    int row = 0;

    gbc.gridx = 0;
    gbc.gridy = row;
    gbc.gridwidth = 2;
    inputPanel.add(jLabelProducto, gbc);
    row++;

    gbc.gridx = 0;
    gbc.gridy = row;
    gbc.gridwidth = 1;
    inputPanel.add(jLabelIdProducto, gbc);
    gbc.gridx = 1;
    inputPanel.add(jTextFieldIdProducto, gbc);
    row++;

    gbc.gridx = 0;
    gbc.gridy = row;
    inputPanel.add(jLabelNombreProducto, gbc);
    gbc.gridx = 1;
    inputPanel.add(jTextFieldNombreProducto, gbc);
    row++;

    gbc.gridx = 0;
    gbc.gridy = row;
    inputPanel.add(jLabelDescripcion, gbc);
    gbc.gridx = 1;
    inputPanel.add(jTextFieldDescripcion, gbc);
    row++;

    gbc.gridx = 0;
    gbc.gridy = row;
    inputPanel.add(jLabelCategoria, gbc);
    gbc.gridx = 1;
    inputPanel.add(jTextFieldCategoria, gbc);
    row++;

    gbc.gridx = 0;
    gbc.gridy = row;
    inputPanel.add(jLabelCantidad, gbc);
    gbc.gridx = 1;
    inputPanel.add(jTextFieldCantidad, gbc);
    row++;

    gbc.gridx = 0;
    gbc.gridy = row;
    inputPanel.add(jLabelPrecio, gbc);
    gbc.gridx = 1;
    inputPanel.add(jTextFieldPrecio, gbc);
    row++;

    gbc.gridx = 0;
    gbc.gridy = row;
    inputPanel.add(jLabelFecha, gbc);
    gbc.gridx = 1;
    inputPanel.add(jTextFieldFecha, gbc);
    row++;

    gbc.gridx = 0;
    gbc.gridy = row;
    inputPanel.add(jLabelStock, gbc);
    gbc.gridx = 1;
    inputPanel.add(jTextFieldStock, gbc);
    row++;

    gbc.gridx = 0;
    gbc.gridy = row;
    gbc.gridwidth = 2;
    inputPanel.add(jLabelProveedor, gbc);
    row++;

    gbc.gridx = 0;
    gbc.gridy = row;
    gbc.gridwidth = 1;
    inputPanel.add(jLabelIdProveedor, gbc);
    gbc.gridx = 1;
    inputPanel.add(jTextFieldIdProveedor, gbc);
    row++;

    gbc.gridx = 0;
    gbc.gridy = row;
    inputPanel.add(jLabelNombreProveedor, gbc);
    gbc.gridx = 1;
    inputPanel.add(jComboBoxProveedores, gbc);
    row++;

    gbc.gridx = 0;
    gbc.gridy = row;
    inputPanel.add(jLabelDireccion, gbc);
    gbc.gridx = 1;
    inputPanel.add(jTextFieldDireccion, gbc);
    row++;

    gbc.gridx = 0;
    gbc.gridy = row;
    inputPanel.add(jLabelTelefono, gbc);
    gbc.gridx = 1;
    inputPanel.add(jTextFieldTelefono, gbc);
    row++;

    gbc.gridx = 0;
    gbc.gridy = row;
    inputPanel.add(jLabelEstado, gbc);
    gbc.gridx = 1;
    inputPanel.add(jTextFieldEstado, gbc);

    // Panel de la tabla (derecha)
    String[] columnNames = {"ID Producto", "Nombre Producto", "Cantidad", "Precio", "Subtotal", "Total"};
    tableModel = new DefaultTableModel(columnNames, 0);
    jTableProductos = new JTable(tableModel);
    jTableProductos.setPreferredScrollableViewportSize(new Dimension(400, 300)); // Ajusta el tamaño máximo de la tabla
    jTableProductos.setFillsViewportHeight(true);
    JScrollPane tableScrollPane = new JScrollPane(jTableProductos);

    JPanel tablePanel = new JPanel(new BorderLayout());
    tablePanel.add(tableScrollPane, BorderLayout.CENTER);

    // Panel de botones (inferior)
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
    buttonPanel.add(jButtonCargarProducto);
    buttonPanel.add(jButtonNuevoProducto);
    buttonPanel.add(jButtonNuevoProveedor);
    buttonPanel.add(jButtonGuardarCompra);
    buttonPanel.add(jButtonCancelar);
    buttonPanel.add(jButtonEliminarProducto); // Agrega el botón "Eliminar Producto"

    // Panel de contenido principal
    JPanel contentPanel = new JPanel(new BorderLayout());
    contentPanel.add(inputPanel, BorderLayout.WEST);
    contentPanel.add(tablePanel, BorderLayout.CENTER);
    contentPanel.add(buttonPanel, BorderLayout.SOUTH);

    add(contentPanel, BorderLayout.CENTER);

    // Configuración para centrado y tamaño de la ventana
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Dimension screenSize = toolkit.getScreenSize();
    int width = 1000; // Ajusta el ancho según sea necesario
    int height = 600; // Ajusta la altura según sea necesario
    setSize(width, height);

    int x = (screenSize.width - width) / 2;
    int y = (screenSize.height - height) / 2;
    setLocation(x, y);

    setTitle("Compra");
    setClosable(true);
    setIconifiable(true);
    setResizable(true);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    // Acción del botón "Cargar Producto"

jButtonCargarProducto.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // Obtener datos de los campos de texto
            String idProducto = jTextFieldIdProducto.getText().trim();
            String nombreProducto = jTextFieldNombreProducto.getText().trim();
            String descripcion = jTextFieldDescripcion.getText().trim();
            String categoria = jTextFieldCategoria.getText().trim();
            String cantidadStr = jTextFieldCantidad.getText().trim();
            String precioStr = jTextFieldPrecio.getText().trim();

            // Validación de campos
            if (idProducto.isEmpty() || nombreProducto.isEmpty() || descripcion.isEmpty() || categoria.isEmpty()
                    || cantidadStr.isEmpty() || precioStr.isEmpty()) {
                JOptionPane.showMessageDialog(Compra.this, "Por favor, complete todos los campos.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int cantidad;
            double precio;

            try {
                cantidad = Integer.parseInt(cantidadStr);
                precio = Double.parseDouble(precioStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(Compra.this, "Cantidad o Precio inválidos.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Calcular el subtotal y total
            double subtotal = cantidad * precio;
            double total = subtotal;  // Si es el primer producto, total es igual al subtotal

            // Agregar fila a la tabla con los valores como números (sin formato de moneda)
            tableModel.addRow(new Object[]{idProducto, nombreProducto, cantidad, precio, subtotal, total});

            // Limpiar campos
            jTextFieldIdProducto.setText("");
            jTextFieldNombreProducto.setText("");
            jTextFieldDescripcion.setText("");
            jTextFieldCategoria.setText("");
            jTextFieldCantidad.setText("");
            jTextFieldPrecio.setText("");
            jTextFieldStock.setText(""); // Asegúrate de limpiar también el campo de stock

      
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(Compra.this, "Error al cargar el producto.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
});




    // Acción del botón "Cancelar"
    jButtonCancelar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Limpiar todos los campos de texto
            jTextFieldIdProducto.setText("");
            jTextFieldNombreProducto.setText("");
            jTextFieldDescripcion.setText("");
            jTextFieldCategoria.setText("");
            jTextFieldCantidad.setText("");
            jTextFieldPrecio.setText("");
            jTextFieldStock.setText("");
            jTextFieldIdProveedor.setText("");
            jTextFieldDireccion.setText("");
            jTextFieldTelefono.setText("");
            jTextFieldEstado.setText("");
            jComboBoxProveedores.setSelectedIndex(-1); // Desmarcar el JComboBox
            tableModel.setRowCount(0); // Limpiar la tabla
        }
    });

    // Acción del botón "Eliminar Producto"
    jButtonEliminarProducto.addActionListener(new ActionListener() {
       @Override
    public void actionPerformed(ActionEvent e) {
        // Obtiene las filas seleccionadas
        int[] selectedRows = jTableProductos.getSelectedRows();
        
        if (selectedRows.length == 1) { // Si solo hay una fila seleccionada
            int selectedRow = selectedRows[0];
            tableModel.removeRow(selectedRow);
            jButtonEliminarProducto.setEnabled(false); // Deshabilitar después de eliminar
        } else if (selectedRows.length > 1) { // Si se seleccionaron más de una fila
            JOptionPane.showMessageDialog(Compra.this, 
                "Por favor, seleccione solo una fila para eliminar.",
                "Advertencia", JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(Compra.this, 
                "Seleccione una fila para eliminar.",
                "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }
    });

    // Configuración del ListSelectionListener para el JTable
    jTableProductos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (jTableProductos.getSelectedRow() != -1) {
                jButtonEliminarProducto.setEnabled(true); // Habilitar cuando se selecciona una fila
            } else {
                jButtonEliminarProducto.setEnabled(false); // Deshabilitar cuando no hay selección
            }
        }
    });

    // Configuración inicial
    setVisible(true);
}
    
        
    

    
    private void initProveedores() {
    jComboBoxProveedores.setEditable(false); // Deshabilitar la edición directa del JComboBox
    cargarTodosLosProveedores(); // Cargar todos los proveedores al inicio

    // ActionListener para gestionar la selección del proveedor
    jComboBoxProveedores.addActionListener(e -> {
        String selectedProveedor = (String) jComboBoxProveedores.getSelectedItem();
        if (selectedProveedor != null) {
            cargarDatosProveedor(selectedProveedor);
        }
    });

    // Manejo del clic simple para mostrar el popup
    jComboBoxProveedores.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            jComboBoxProveedores.setPopupVisible(true); // Mostrar el popup
        }
    });

    // Cerrar el popup al perder el foco
    jComboBoxProveedores.addFocusListener(new FocusAdapter() {
        @Override
        public void focusLost(FocusEvent e) {
            String selectedProveedor = (String) jComboBoxProveedores.getSelectedItem();
            if (selectedProveedor == null) {
                JOptionPane.showMessageDialog(null, "Por favor, seleccione un proveedor de la lista.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                jComboBoxProveedores.requestFocus(); // Volver a solicitar el foco en el JComboBox
            }
        }
    });


    // Añadir el DocumentListener para auto-completar producto al cambiar el texto
    jTextFieldIdProducto.getDocument().addDocumentListener(new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
            autoCompletarProducto();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            autoCompletarProducto();
        }
        

        @Override
        public void changedUpdate(DocumentEvent e) {
            autoCompletarProducto();
        }
    });

    // ActionListener para manejar la selección en el JComboBox
    jComboBoxProveedores.addActionListener(e -> {
        String selectedProveedor = (String) jComboBoxProveedores.getSelectedItem();
        if (selectedProveedor != null && !selectedProveedor.isEmpty()) {
            cargarDatosProveedor(selectedProveedor);
        }
    });
}
// Darle tiempo al JComboBox para que no se actualice al milisegundo
private void scheduleFilter(String filter) {
    if (timer != null) {
        timer.cancel(); // Cancelar cualquier tarea programada anterior
    }
    timer = new Timer();
    timer.schedule(new TimerTask() {
        @Override
        public void run() {
            SwingUtilities.invokeLater(() -> filtrarProveedores(filter)); // Ejecutar en el hilo de la interfaz
        }
    }, DELAY); // Retraso en milisegundos
}



// Filtra los proveedores
private void filtrarProveedores(String filter) {
    DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) jComboBoxProveedores.getModel();
    model.removeAllElements(); // Limpiar el modelo actual

    if (!filter.isEmpty()) {
        try (Connection con = conexionBD.getConnection();
             PreparedStatement stmt = con.prepareStatement("SELECT NombreProveedor FROM proveedor WHERE NombreProveedor LIKE ? ORDER BY NombreProveedor")) {
            stmt.setString(1, filter + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    model.addElement(rs.getString("NombreProveedor")); // Añadir proveedores filtrados
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Mostrar el popup solo si hay coincidencias
    jComboBoxProveedores.setPopupVisible(model.getSize() > 0); 
}



  // Método para cargar todos los proveedores al JComboBox
private void cargarTodosLosProveedores() {
    DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) jComboBoxProveedores.getModel();
    model.removeAllElements(); // Limpiar elementos existentes

    try (Connection con = conexionBD.getConnection();
         PreparedStatement stmt = con.prepareStatement("SELECT NombreProveedor FROM proveedor ORDER BY NombreProveedor")) {
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                model.addElement(rs.getString("NombreProveedor")); // Cargar todos los proveedores
            }
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}

// Método para cargar datos del proveedor seleccionado
private void cargarDatosProveedor(String nombreProveedor) {
    try (Connection con = conexionBD.getConnection();
         PreparedStatement stmt = con.prepareStatement("SELECT * FROM proveedor WHERE NombreProveedor = ?")) {
        stmt.setString(1, nombreProveedor);

        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                // Supongamos que tienes campos de texto para Id, Dirección, Teléfono y Estado
                jTextFieldIdProveedor.setText(rs.getString("IdProveedor"));
                jTextFieldDireccion.setText(rs.getString("Direccion"));
                jTextFieldTelefono.setText(rs.getString("Telefono"));
                jTextFieldEstado.setText(rs.getInt("Estado") == 1 ? "Activo" : "Inactivo");
            }
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al cargar datos del proveedor: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}


    private void setFechaSistema() {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        jTextFieldFecha.setText(formatoFecha.format(new Date()));
    }
    
    
    
   // productos
   private void autoCompletarProducto() {
    String IdProductoStr = jTextFieldIdProducto.getText();
    if (!IdProductoStr.isEmpty()) {
        try {
            int IdProducto = Integer.parseInt(IdProductoStr);
            Connection conexion = conexionBD.getConnection(); // Asegúrate de que esta línea obtenga una conexión válida
            
            // Consulta SQL con JOIN para obtener la información del producto y su stock
            String sql = "SELECT p.NombreProducto, p.Descripcion, s.Cantidad, c.NombreCategoria " +
                         "FROM producto p " +
                         "JOIN stock s ON p.IdProducto = s.IdProducto " +
                         "JOIN categoria c ON p.IdCategoria = c.IdCategoria " + // Unir con la tabla categoría
                         "WHERE p.IdProducto = ?";
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setInt(1, IdProducto);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                jTextFieldNombreProducto.setText(rs.getString("NombreProducto"));
                jTextFieldDescripcion.setText(rs.getString("Descripcion"));
                jTextFieldStock.setText(rs.getString("Cantidad")); // Muestra la cantidad de stock
                jTextFieldCategoria.setText(rs.getString("NombreCategoria")); // Muestra el nombre de la categoría
            } else {
                jTextFieldNombreProducto.setText("");
                jTextFieldDescripcion.setText("");
                jTextFieldStock.setText("");
                jTextFieldCategoria.setText("");
            }

            rs.close();
            pst.close();
            conexion.close();
            
            // Configura los campos para que no sean editables
            jTextFieldNombreProducto.setEditable(false);
            jTextFieldDescripcion.setEditable(false);
            jTextFieldStock.setEditable(false);
            jTextFieldCategoria.setEditable(false);

        } catch (NumberFormatException ex) {
            // En caso de que el ID no sea un número
            jTextFieldNombreProducto.setText("");
            jTextFieldDescripcion.setText("");
            jTextFieldStock.setText("");
            jTextFieldCategoria.setText("");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al conectar con la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        // Limpiar campos si no hay ID de producto
        jTextFieldNombreProducto.setText("");
        jTextFieldDescripcion.setText("");
        jTextFieldStock.setText("");
        jTextFieldCategoria.setText("");
    }
}
   
   
   
   
   
private void guardarCompra() {
    // Obtener el ID del usuario y el ID del proveedor
    String idUsuario = obtenerIdUsuario(); // Método para obtener el ID del usuario actual
    String idProveedor = jTextFieldIdProveedor.getText(); 

    // Obtener la fecha del sistema en formato yyyy-MM-dd
    String fecha = java.time.LocalDate.now().toString();

    // Validar que los campos no estén vacíos
    if (idUsuario.isEmpty() || idProveedor.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos necesarios.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Variables para la conexión y los PreparedStatements
    Connection con = null;
    PreparedStatement psCompra = null;
    PreparedStatement psDetalle = null;
    PreparedStatement psStock = null;
    PreparedStatement psProducto = null; // Para obtener el porcentaje del precio de venta

    try {
        // Obtener la conexión a la base de datos
        con = conexionBD.getConnection();
        con.setAutoCommit(false); // Inicia la transacción

        // Insertar la compra en la tabla `compra`
        String sqlCompra = "INSERT INTO compra (IdUsuario, IdProveedor, Fecha, monto) VALUES (?, ?, ?, ?)";
        psCompra = con.prepareStatement(sqlCompra, Statement.RETURN_GENERATED_KEYS);
        psCompra.setString(1, idUsuario);
        psCompra.setString(2, idProveedor);
        psCompra.setString(3, fecha);

        // Calcular el monto total de la compra
        double montoTotal = calcularMontoTotal();
        psCompra.setDouble(4, montoTotal);
        psCompra.executeUpdate(); // Ejecutar la inserción de la compra

        // Obtener el ID de la compra recién insertada
        ResultSet rs = psCompra.getGeneratedKeys();
        if (rs.next()) {
            int idCompra = rs.getInt(1);

            // Insertar los detalles de los productos en la tabla `compraproductodetalle`
            String sqlDetalle = "INSERT INTO compraproductodetalle (IdCompra, IdProducto, Cantidad, Monto) VALUES (?, ?, ?, ?)";
            psDetalle = con.prepareStatement(sqlDetalle);

            // Iterar sobre las filas de la tabla para obtener los datos de cada producto
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                String idProducto = tableModel.getValueAt(i, 0).toString();
                int cantidad = Integer.parseInt(tableModel.getValueAt(i, 2).toString());

                // Obtener el precio del producto
                String precioStr = tableModel.getValueAt(i, 3).toString();

                // Eliminar el símbolo de moneda ($) y convertir el valor a Double
                precioStr = precioStr.replace("$", "").replace(",", "").trim(); // Elimina el símbolo de moneda y las comas
                double precio = Double.parseDouble(precioStr); // Convierte a double

                // Obtener el porcentaje de precio de venta del producto
                String sqlProducto = "SELECT Porcentaje FROM producto WHERE IdProducto = ?";
                psProducto = con.prepareStatement(sqlProducto);
                psProducto.setInt(1, Integer.parseInt(idProducto));
                ResultSet rsProducto = psProducto.executeQuery();

                double porcentajeVenta = 0;
                if (rsProducto.next()) {
                    porcentajeVenta = rsProducto.getDouble("Porcentaje");
                }

                // Calcular el precio de venta
                double precioVenta = precio + (precio * (porcentajeVenta / 100));

                // Insertar los detalles del producto
                psDetalle.setInt(1, idCompra);
                psDetalle.setInt(2, Integer.parseInt(idProducto));
                psDetalle.setInt(3, cantidad);
                psDetalle.setDouble(4, precio);
                psDetalle.addBatch();

                // Verificar si el producto ya existe en `stock`
                String sqlBuscarStock = "SELECT Cantidad, Precio FROM stock WHERE IdProducto = ?";
                psStock = con.prepareStatement(sqlBuscarStock);
                psStock.setInt(1, Integer.parseInt(idProducto));
                ResultSet rsStock = psStock.executeQuery();

                if (rsStock.next()) {
                    // Si el producto existe, actualizar la cantidad y el precio de venta
                    String sqlActualizarStock = "UPDATE stock SET Cantidad = Cantidad + ?, Precio = ?, Precio_Venta = ? WHERE IdProducto = ?";
                    psStock = con.prepareStatement(sqlActualizarStock);
                    psStock.setInt(1, cantidad);
                    psStock.setDouble(2, precio);
                    psStock.setDouble(3, precioVenta); // Actualizar el precio de venta
                    psStock.setInt(4, Integer.parseInt(idProducto));
                } else {
                    // Si el producto no existe, insertar en `stock`
                    String sqlInsertarStock = "INSERT INTO stock (IdProducto, Cantidad, Precio, Precio_Venta) VALUES (?, ?, ?, ?)";
                    psStock = con.prepareStatement(sqlInsertarStock);
                    psStock.setInt(1, Integer.parseInt(idProducto));
                    psStock.setInt(2, cantidad);
                    psStock.setDouble(3, precio);
                    psStock.setDouble(4, precioVenta); // Insertar el precio de venta
                }
                psStock.executeUpdate(); // Ejecutar la actualización o inserción en `stock`
            }

            psDetalle.executeBatch(); // Ejecutar el lote de inserciones de detalles

            // Confirmar la transacción
            con.commit();
            JOptionPane.showMessageDialog(this, "Compra guardada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            limpiarCamposYTabla(); // Limpiar los campos y la tabla
            setFechaSistema(); // Reestablecer la fecha para que no quede en blanco

        } else {
            JOptionPane.showMessageDialog(this, "No se pudo obtener el ID de la compra.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    } catch (SQLException e) {
        e.printStackTrace();
        if (con != null) {
            try {
                con.rollback(); // Revertir la transacción en caso de error
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
        }
        JOptionPane.showMessageDialog(this, "Error al guardar la compra.", "Error", JOptionPane.ERROR_MESSAGE);

    } finally {
        try {
            // Cerrar los recursos
            if (psCompra != null) psCompra.close();
            if (psDetalle != null) psDetalle.close();
            if (psStock != null) psStock.close();
            if (psProducto != null) psProducto.close(); // Cerrar PreparedStatement de producto
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}




private void limpiarCamposYTabla() {
    // Limpiar campos de texto
    jTextFieldIdProducto.setText("");
    jTextFieldNombreProducto.setText("");
    jTextFieldDescripcion.setText("");
    jTextFieldStock.setText("");
    jTextFieldCategoria.setText("");
    jTextFieldIdProveedor.setText("");
    jTextFieldDireccion.setText("");
    jTextFieldTelefono.setText("");
    jTextFieldEstado.setText("");
    jTextFieldFecha.setText("");
    
    // Limpiar la tabla
    tableModel.setRowCount(0); // Asumiendo que `tableModel` es el modelo de tu JTable
}





// Método para calcular el monto total y devolverlo en formato de número
private double calcularMontoTotal() {
    double montoTotal = 0.0;
    for (int i = 0; i < tableModel.getRowCount(); i++) {
        double subtotal = Double.parseDouble(tableModel.getValueAt(i, 4).toString());
        montoTotal += subtotal;
    }
    return montoTotal;
}

// Método para obtener el monto total con formato de moneda para mostrarlo en la interfaz
private String obtenerMontoTotalFormateado() {
    double montoTotal = calcularMontoTotal();
    DecimalFormat formatoDinero = new DecimalFormat("#,##0.00");
    return "$" + formatoDinero.format(montoTotal); // Formato con símbolo de moneda
}


  
  
  
// Método para obtener el ID del usuario actual
private String obtenerIdUsuario() {
    
    return "1"; // Ejemplo de ID de usuario
}

// Método para obtener el precio del producto
private double obtenerPrecioProducto(int idProducto) {
    double precio = 0.0;
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        con = conexionBD.getConnection();
        String sql = "SELECT Precio FROM stock WHERE IdProducto = ?";
        ps = con.prepareStatement(sql);
        ps.setInt(1, idProducto);
        rs = ps.executeQuery();

        if (rs.next()) {
            precio = rs.getDouble("Precio");
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

    return precio;
}

   
  // Definición del método agregar
   private void jButtonNuevoProductoActionPerformed(java.awt.event.ActionEvent evt) {
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

   
  // Clase AgregarProductoDialog
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







// AGREGAR PROVEEDOR NUEVO
   public class AgregarProveedorDialog extends JDialog {
    private JTextField txtNombreProveedor;
    private JTextField txtDireccion;
    private JTextField txtTelefono;
    private JButton btnAgregar;
    private JButton btnCancelar;
    private boolean added = false;
    public AgregarProveedorDialog(Frame parent) {
        super(parent, true);
        setTitle("Agregar Proveedor");
        setSize(400, 250);
        setLayout(new BorderLayout());

        // Panel para los campos de texto usando GridBagLayout
        JPanel panelCampos = new JPanel(new GridBagLayout());
        add(panelCampos, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espaciado entre componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        // Nombre Proveedor
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelCampos.add(new JLabel("Nombre Proveedor:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        txtNombreProveedor = new JTextField();
        panelCampos.add(txtNombreProveedor, gbc);

        // Dirección
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        panelCampos.add(new JLabel("Dirección:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        txtDireccion = new JTextField();
        panelCampos.add(txtDireccion, gbc);

        // Teléfono
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        panelCampos.add(new JLabel("Teléfono:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        txtTelefono = new JTextField();
        panelCampos.add(txtTelefono, gbc);

        // Panel para los botones
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        add(panelBoton, BorderLayout.SOUTH);

        btnAgregar = new JButton("Agregar");
        btnAgregar.setEnabled(false);
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

            private void checkForChanges() {
                boolean allFieldsFilled = !txtNombreProveedor.getText().trim().isEmpty() &&
                                          !txtDireccion.getText().trim().isEmpty() &&
                                          !txtTelefono.getText().trim().isEmpty();
                btnAgregar.setEnabled(allFieldsFilled);
            }
        };

        txtNombreProveedor.getDocument().addDocumentListener(documentListener);
        txtDireccion.getDocument().addDocumentListener(documentListener);
        txtTelefono.getDocument().addDocumentListener(documentListener);

        setLocationRelativeTo(parent);
    }

    public boolean isAdded() {
        return added;
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
   
   
   private void jButtonNuevoProveedorActionPerformed(java.awt.event.ActionEvent evt) {
    // Mostrar el diálogo para agregar un proveedor
    AgregarProveedorDialog dialog = new AgregarProveedorDialog((Frame) SwingUtilities.getWindowAncestor(this));
    dialog.setVisible(true);

    // Verificar si el usuario ha decidido agregar un proveedor
    if (dialog.isAdded()) {
        String nombreProveedor = dialog.getNombreProveedor();
        String direccion = dialog.getDireccion();
        String telefono = dialog.getTelefono();

        // Validar que los campos no estén vacíos
        if (nombreProveedor.trim().isEmpty() || direccion.trim().isEmpty() || telefono.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos deben estar completos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Connection conexion = null;
        PreparedStatement pst = null;

        try {
            // Establecer conexión a la base de datos
            conexion = conexionBD.getConnection();

            // Consulta SQL para insertar un nuevo proveedor
            String consultaProveedor = "INSERT INTO proveedor (NombreProveedor, Direccion, Telefono, Estado) VALUES (?, ?, ?, 1)";
            pst = conexion.prepareStatement(consultaProveedor);
            pst.setString(1, nombreProveedor);
            pst.setString(2, direccion);
            pst.setString(3, telefono);
            int filasAfectadas = pst.executeUpdate();

            // Verificar si la inserción del proveedor fue exitosa
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(this, "Proveedor agregado exitosamente.");
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo agregar el proveedor.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al agregar el proveedor a la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Cerrar recursos usando el método cerrarRecursos de conexionBD
            conexionBD.cerrarRecursos(null, pst, conexion);
        }
    }
}


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 600);

            JDesktopPane desktopPane = new JDesktopPane();
            frame.add(desktopPane);

            Compra compraFrame = new Compra();
            desktopPane.add(compraFrame);
            compraFrame.setVisible(true);

            frame.setVisible(true);
        });
    }
}


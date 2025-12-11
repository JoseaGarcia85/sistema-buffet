package Buscar;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.sql.*;
import Conexion.conexionBD;

public class AumentoPorcentaje extends JInternalFrame {
    private JTextField codigoProductoField, porcentajeField;
    private JTextField nombreProductoField; // Campo para el nombre del producto
    private JButton btnBuscar, btnGuardar, btnCancelar;
    private JComboBox<String> tipoAumentoComboBox;
    private JComboBox<String> proveedorComboBox; // ComboBox para proveedores
    private JComboBox<String> categoriaComboBox; // ComboBox para categorías

    public AumentoPorcentaje() {
        setTitle("Aumento de Precio");
        setSize(400, 520); // Ajustar tamaño
        setResizable(true); // Permitir redimensionar la ventana
        setClosable(true); // Permitir cerrar la ventana
        setMaximizable(true); // Permitir maximizar la ventana
        setIconifiable(true); // Permitir minimizar la ventana

        // Centrar el JInternalFrame al abrirlo
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width - getWidth()) / 2, (screenSize.height - getHeight()) / 2);

        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // ComboBox para seleccionar tipo de aumento
        tipoAumentoComboBox = new JComboBox<>(new String[]{"Producto", "Proveedor", "Categoría"});
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(tipoAumentoComboBox, gbc);

        // Panel para Código Producto
        JPanel codigoProductoPanel = crearPanelConBorde("Código Producto");
        codigoProductoField = new JTextField(10);
        btnBuscar = new JButton("Buscar");
        codigoProductoPanel.add(codigoProductoField);
        codigoProductoPanel.add(btnBuscar);
        gbc.gridy = 1;
        add(codigoProductoPanel, gbc);

        // Panel para Nombre del Producto
        JPanel nombreProductoPanel = crearPanelConBorde("Nombre del Producto");
        nombreProductoField = new JTextField(10);
        nombreProductoField.setEditable(false); // No editable
        nombreProductoPanel.add(nombreProductoField);
        gbc.gridy = 2;
        add(nombreProductoPanel, gbc);

        // Panel para Porcentaje
        JPanel porcentajePanel = crearPanelConBorde("Porcentaje de Aumento (%)");
        porcentajeField = new JTextField(10);
        porcentajePanel.add(porcentajeField);
        gbc.gridy = 3;
        add(porcentajePanel, gbc);

        // Panel para Proveedor
        JPanel proveedorPanel = crearPanelConBorde("Proveedor");
        proveedorComboBox = new JComboBox<>();
        proveedorPanel.add(proveedorComboBox);
        gbc.gridy = 4;
        add(proveedorPanel, gbc);

        // Panel para Categoría
        JPanel categoriaPanel = crearPanelConBorde("Categoría");
        categoriaComboBox = new JComboBox<>();
        categoriaPanel.add(categoriaComboBox);
        gbc.gridy = 5;
        add(categoriaPanel, gbc);

        // Panel de botones
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        btnGuardar = new JButton("Guardar");
        btnCancelar = new JButton("Cancelar");
        buttonPanel.add(btnGuardar);
        buttonPanel.add(btnCancelar);
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        add(buttonPanel, gbc);

        // Acción para buscar según el tipo seleccionado
        btnBuscar.addActionListener(e -> buscarPorTipo());

        // Acción para guardar y cancelar
        btnGuardar.addActionListener(e -> guardar());
        btnCancelar.addActionListener(e -> dispose());

        // Activar o desactivar el botón Guardar según los campos
        agregarDocumentListener(codigoProductoField);
        agregarDocumentListener(porcentajeField);

        btnGuardar.setEnabled(false);
        
        // Cargar categorías al iniciar
        cargarCategorias();
        cargarProveedores();
        
        // Listener para cambiar los combobox según el tipo
        tipoAumentoComboBox.addActionListener(e -> actualizarComponentesPorTipo());
        
        // Inicialmente, ocultar el botón de búsqueda y el campo de texto
        actualizarComponentesPorTipo();
    }

    private JPanel crearPanelConBorde(String texto) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), texto, TitledBorder.LEFT, TitledBorder.TOP));
        return panel;
    }

    private void agregarDocumentListener(JTextField textField) {
        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                toggleGuardarButton();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                toggleGuardarButton();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                toggleGuardarButton();
            }
        });
    }

    private void toggleGuardarButton() {
    String codigo = codigoProductoField.getText().trim();
    String porcentaje = porcentajeField.getText().trim();
    boolean codigoValido = !codigo.isEmpty() && codigo.matches("\\d+");
    boolean porcentajeValido = !porcentaje.isEmpty() && porcentaje.matches("\\d+(\\.\\d+)?") && Double.parseDouble(porcentaje) > 0;
    boolean productoValido = !nombreProductoField.getText().isEmpty();

    String tipo = (String) tipoAumentoComboBox.getSelectedItem();
    boolean habilitarBoton = false;

    if ("Producto".equals(tipo)) {
        habilitarBoton = codigoValido && porcentajeValido && productoValido;
    } else if ("Proveedor".equals(tipo)) {
        habilitarBoton = porcentajeValido && proveedorComboBox.getSelectedItem() != null;
    } else if ("Categoría".equals(tipo)) {
        habilitarBoton = porcentajeValido && categoriaComboBox.getSelectedItem() != null;
    }

    btnGuardar.setEnabled(habilitarBoton);
}

private void buscarPorTipo() {
    String tipo = (String) tipoAumentoComboBox.getSelectedItem();
    String codigo = codigoProductoField.getText().trim(); // Cambiar de id a codigo

    if (tipo != null) {
        switch (tipo) {
            case "Producto":
                String nombreProducto = buscarProductoPorCodigo(codigo);
                if (nombreProducto != null) {
                    nombreProductoField.setText(nombreProducto);
                    proveedorComboBox.setVisible(false);
                    categoriaComboBox.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(this, "Producto no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "Proveedor":
                cargarProveedores();
                nombreProductoField.setText(""); // Limpiar campo de producto
                proveedorComboBox.setVisible(true);
                categoriaComboBox.setVisible(false);
                break;
            case "Categoría":
                cargarCategorias();
                nombreProductoField.setText(""); // Limpiar campo de producto
                proveedorComboBox.setVisible(false);
                categoriaComboBox.setVisible(true);
                break;
        }
    }
}

private void cargarProveedores() {
    proveedorComboBox.removeAllItems();
    String consulta = "SELECT NombreProveedor FROM proveedor";
    try (Connection conexion = conexionBD.getConnection();
         Statement stmt = conexion.createStatement();
         ResultSet rs = stmt.executeQuery(consulta)) {
        while (rs.next()) {
            proveedorComboBox.addItem(rs.getString("NombreProveedor"));
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al cargar proveedores: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}

private void cargarCategorias() {
    categoriaComboBox.removeAllItems();
    String consulta = "SELECT NombreCategoria FROM categoria"; // Ajusta esto según tu tabla
    try (Connection conexion = conexionBD.getConnection();
         Statement stmt = conexion.createStatement();
         ResultSet rs = stmt.executeQuery(consulta)) {
        while (rs.next()) {
            categoriaComboBox.addItem(rs.getString("NombreCategoria"));
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al cargar categorías: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}

private void guardar() {
    String porcentaje = porcentajeField.getText().trim();
    String tipo = (String) tipoAumentoComboBox.getSelectedItem();
    Integer idProducto = null;

    // Validar que el porcentaje es un número válido
    if (porcentaje.isEmpty() || !porcentaje.matches("\\d+(\\.\\d+)?")) {
        JOptionPane.showMessageDialog(this, "Por favor, ingrese un porcentaje válido.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Intentar obtener el ID del producto si es necesario
    if ("Producto".equals(tipo)) {
        String codigoProducto = codigoProductoField.getText().trim();
        if (!codigoProducto.isEmpty() && !nombreProductoField.getText().isEmpty()) {
            idProducto = obtenerIdProducto(codigoProducto);
            if (idProducto != null) {
                actualizarPrecio(null, porcentaje, idProducto);
                JOptionPane.showMessageDialog(this, "Precio actualizado correctamente.", "Guardado", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Producto no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un producto válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else if ("Proveedor".equals(tipo)) {
        String nombreProveedor = (String) proveedorComboBox.getSelectedItem();
        if (nombreProveedor != null) {
            actualizarPrecio(nombreProveedor, porcentaje, null);
            JOptionPane.showMessageDialog(this, "Precios y porcentajes actualizados por proveedor correctamente.", "Guardado", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un proveedor válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else if ("Categoría".equals(tipo)) {
        String nombreCategoria = (String) categoriaComboBox.getSelectedItem();
        if (nombreCategoria != null) {
            actualizarPrecio(nombreCategoria, porcentaje, null);
            JOptionPane.showMessageDialog(this, "Precios y porcentajes actualizados por categoría correctamente.", "Guardado", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione una categoría válida.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}


private Integer obtenerIdProducto(String codigoProducto) {
    Integer idProducto = null;
    String sql = "SELECT IdProducto FROM producto WHERE IdProducto = ?"; // Asegúrate de que estás buscando por el campo correcto
    
    try (Connection conn = conexionBD.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, codigoProducto);
        ResultSet rs = pstmt.executeQuery();
        
        if (rs.next()) {
            idProducto = rs.getInt("IdProducto");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return idProducto;
}




private void actualizarPrecio(String nombreProveedorOCategoria, String porcentaje, Integer idProducto) {
    try (Connection conn = conexionBD.getConnection()) {
        String tipo = (String) tipoAumentoComboBox.getSelectedItem();
        
        // Determinar las consultas según el tipo
        String sqlActualizarPrecio = "";
        String sqlActualizarPorcentaje = "";
        
        if ("Proveedor".equals(tipo)) {
            sqlActualizarPrecio = "UPDATE stock s " +
                "SET Precio_Venta = Precio + (Precio * ? / 100) " +
                "WHERE IdProducto IN (SELECT p.IdProducto FROM producto p " +
                "JOIN proveedor pr ON p.IdProveedor = pr.IdProveedor " +
                "WHERE pr.NombreProveedor = ?)";

            sqlActualizarPorcentaje = "UPDATE producto p " +
                "SET Porcentaje = ? " +  
                "WHERE IdProveedor IN (SELECT IdProveedor FROM proveedor WHERE NombreProveedor = ?)";
        } else if ("Categoría".equals(tipo)) {
            sqlActualizarPrecio = "UPDATE stock s " +
                "SET Precio_Venta = Precio + (Precio * ? / 100) " +
                "WHERE IdProducto IN (SELECT p.IdProducto FROM producto p " +
                "JOIN categoria c ON p.IdCategoria = c.IdCategoria " +
                "WHERE c.NombreCategoria = ?)";

            sqlActualizarPorcentaje = "UPDATE producto p " +
                "SET Porcentaje = ? " +  
                "WHERE IdCategoria IN (SELECT IdCategoria FROM categoria WHERE NombreCategoria = ?)";
        } else if ("Producto".equals(tipo) && idProducto != null) {
            sqlActualizarPrecio = "UPDATE stock s " +
                "SET Precio_Venta = Precio + (Precio * ? / 100) " +
                "WHERE IdProducto = ?";  

            sqlActualizarPorcentaje = "UPDATE producto p " +
                "SET Porcentaje = ? " +  
                "WHERE IdProducto = ?";
        } else {
            return; // Salir si no es un tipo válido
        }

        // Actualizar precios
        try (PreparedStatement pstmtPrecio = conn.prepareStatement(sqlActualizarPrecio)) {
            pstmtPrecio.setDouble(1, Double.parseDouble(porcentaje));
            if ("Producto".equals(tipo) && idProducto != null) {
                pstmtPrecio.setInt(2, idProducto); // Ajustar para el producto
            } else {
                pstmtPrecio.setString(2, nombreProveedorOCategoria); // Para proveedor o categoría
            }
            pstmtPrecio.executeUpdate();
        }

        // Actualizar porcentaje
        try (PreparedStatement pstmtPorcentaje = conn.prepareStatement(sqlActualizarPorcentaje)) {
            pstmtPorcentaje.setDouble(1, Double.parseDouble(porcentaje));
            if ("Producto".equals(tipo) && idProducto != null) {
                pstmtPorcentaje.setInt(2, idProducto); // Ajustar para el producto
            } else {
                pstmtPorcentaje.setString(2, nombreProveedorOCategoria); // Para proveedor o categoría
            }
            pstmtPorcentaje.executeUpdate();
        }
    } catch (SQLException e) {
        e.printStackTrace(); // Agrega esto para ayudar a depurar errores
        JOptionPane.showMessageDialog(this, "Error al actualizar precios: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}






private String buscarProductoPorCodigo(String codigo) {
    String nombre = null;
    String consulta = "SELECT NombreProducto FROM producto WHERE IdProducto = ?";
    try (Connection conexion = conexionBD.getConnection();
         PreparedStatement pstmt = conexion.prepareStatement(consulta)) {
        pstmt.setString(1, codigo);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            nombre = rs.getString("NombreProducto");
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al buscar producto: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    return nombre;
}

private void actualizarComponentesPorTipo() {
    String tipo = (String) tipoAumentoComboBox.getSelectedItem();
    if ("Producto".equals(tipo)) {
        btnBuscar.setEnabled(true);
        codigoProductoField.setEnabled(true);
        proveedorComboBox.setVisible(false);
        categoriaComboBox.setVisible(false);
    } else if ("Proveedor".equals(tipo)) {
        btnBuscar.setEnabled(false);
        codigoProductoField.setEnabled(false);
        proveedorComboBox.setVisible(true);
        categoriaComboBox.setVisible(false);
    } else if ("Categoría".equals(tipo)) {
        btnBuscar.setEnabled(false);
        codigoProductoField.setEnabled(false);
        proveedorComboBox.setVisible(false);
        categoriaComboBox.setVisible(true);
    } else {
        btnBuscar.setEnabled(false);
        codigoProductoField.setEnabled(false);
        proveedorComboBox.setVisible(false);
        categoriaComboBox.setVisible(false);
    }
}}
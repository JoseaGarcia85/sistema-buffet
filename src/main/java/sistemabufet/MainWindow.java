package sistemabufet;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.*;
import Conexion.conexionBD; // Importar la clase ConexionBD desde el paquete conexion
import javax.swing.ImageIcon;



public class MainWindow extends JInternalFrame {

    private JTextField campoCodigoBarras;
    private JTextField campoCantidad;
    private JTextField campoSubtotal;
    private JTextField campoTotal;
    private JTable tablaProductos;
    private JTextField campoFecha;
    private JTextField campoTipo;
    private JTextField campoNumero;
    private JTextArea areaObservaciones;
    private DefaultTableModel model;
    private JLabel etiquetaTotalSuma; // Declarar JLabel para el total
    private JComboBox<String> comboTipo;
    private JButton botonQuitar;


    public MainWindow() {
        setTitle("Sistema de Facturación - Cajero: Administrador");
        setSize(1200, 650);
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setLayout(new BorderLayout());

        // Panel superior
        JPanel panelSuperior = new JPanel(new GridBagLayout());
        panelSuperior.setBorder(BorderFactory.createTitledBorder("Datos del Producto"));
        panelSuperior.setBackground(new Color(230, 230, 250));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel etiquetaCodigoBarras = new JLabel("Código de barras:");
        campoCodigoBarras = new JTextField(15);
        JLabel etiquetaCantidad = new JLabel("Cantidad:");
        campoCantidad = new JTextField(5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelSuperior.add(etiquetaCodigoBarras, gbc);

        gbc.gridx = 1;
        panelSuperior.add(campoCodigoBarras, gbc);

        gbc.gridx = 2;
        panelSuperior.add(etiquetaCantidad, gbc);

        gbc.gridx = 3;
        panelSuperior.add(campoCantidad, gbc);

        JButton botonBuscar = new JButton("Buscar artículo");
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        panelSuperior.add(botonBuscar, gbc);
        
        campoTipo = new JTextField(15);
        campoTipo.setText("Efectivo"); // Establecer el texto inicial
        campoTipo.setEditable(false); // Hacer que el campo no sea editable

        gbc.gridx = 6;
        panelSuperior.add(new JLabel("Tipo:"), gbc);
        gbc.gridx = 7;
        panelSuperior.add(campoTipo, gbc);

        add(panelSuperior, BorderLayout.NORTH);

        // Panel centro
        JPanel panelCentro = new JPanel(new BorderLayout());
        panelCentro.setBorder(BorderFactory.createTitledBorder("Lista de Productos"));
        panelCentro.setBackground(new Color(240, 255, 255));

        String[] columnNames = {"Código", "Descripción", "Cantidad", "Unitario", "Importe"};
        model = new DefaultTableModel(columnNames, 0);
        tablaProductos = new JTable(model);
        tablaProductos.setPreferredScrollableViewportSize(new Dimension(500, 200)); // Ajuste del tamaño de la tabla
        tablaProductos.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        // Ajustar el tamaño de las columnas
        TableColumnModel columnModel = tablaProductos.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);  // Código
        columnModel.getColumn(1).setPreferredWidth(200); // Descripción
        columnModel.getColumn(2).setPreferredWidth(50);  // Cantidad
        columnModel.getColumn(3).setPreferredWidth(100); // Unitario
        columnModel.getColumn(4).setPreferredWidth(100); // Importe

        JScrollPane scrollPane = new JScrollPane(tablaProductos);
        panelCentro.add(scrollPane, BorderLayout.CENTER);

        JPanel panelBotonesTabla = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBotonesTabla.setBackground(new Color(240, 255, 255));
        
        
        botonQuitar = new JButton("Quitar");
        botonQuitar.setEnabled(false);
        
        
      
        
        panelBotonesTabla.add(botonQuitar);

        panelCentro.add(panelBotonesTabla, BorderLayout.SOUTH);

        add(panelCentro, BorderLayout.CENTER);

       // Panel inferior
        JPanel panelInferior = new JPanel(new GridLayout(1, 2, 10, 10));

        // Panel datos del comprobante
        JPanel panelDatosComprobante = new JPanel(new GridLayout(4, 2, 10, 10));
        panelDatosComprobante.setBorder(BorderFactory.createTitledBorder("Datos del Comprobante"));
        panelDatosComprobante.setBackground(new Color(230, 230, 250));

        JLabel etiquetaFecha = new JLabel("Fecha:");
        campoFecha = new JTextField();
        campoFecha.setEditable(false); // Para que no se pueda editar manualmente
        actualizarFechaHora();
        JLabel etiquetaTipo = new JLabel("Tipo de Pago:");
        String[] tipos = {"Efectivo", "Mercado Pago", "Efectivo y Mercado Pago"};
        comboTipo = new JComboBox<>(tipos);
        comboTipo.setSelectedIndex(0); // Selección por defecto
        etiquetaTipo.setText("Tipo de Pago: " + comboTipo.getSelectedItem());

        comboTipo.addActionListener(e -> {
        String tipoSeleccionado = (String) comboTipo.getSelectedItem();
        campoTipo.setText(tipoSeleccionado);

        });
 
        JLabel etiquetaNumero = new JLabel("Nro Factura.:");
        campoNumero = new JTextField();
        campoNumero.setEditable(false);
        JLabel etiquetaObservaciones = new JLabel("Observaciones:");
        areaObservaciones = new JTextArea(3, 20);

        panelDatosComprobante.add(etiquetaFecha);
        panelDatosComprobante.add(campoFecha);
        panelDatosComprobante.add(new JLabel("Tipo:"));
        panelDatosComprobante.add(comboTipo);

        panelDatosComprobante.add(etiquetaNumero);
        panelDatosComprobante.add(campoNumero);
        panelDatosComprobante.add(etiquetaObservaciones);
        panelDatosComprobante.add(new JScrollPane(areaObservaciones));

        panelInferior.add(panelDatosComprobante);

        // Panel totales
        JPanel panelTotales = new JPanel(new BorderLayout());
        panelTotales.setBorder(BorderFactory.createTitledBorder("Totales"));
        panelTotales.setBackground(new Color(240, 255, 255));

        JPanel panelCamposTotales = new JPanel(new GridLayout(3, 2, 10, 10));
        panelCamposTotales.setBackground(new Color(240, 255, 255));

        JLabel etiquetaSubtotal = new JLabel("Subtotal:");
        campoSubtotal = new JTextField();
        campoSubtotal.setEditable(false);

        panelInferior.add(etiquetaSubtotal);
        JLabel etiquetaTotal = new JLabel("Total:");
        campoTotal = new JTextField();
        campoTotal.setEditable(false);

        panelCamposTotales.add(etiquetaSubtotal);
        panelCamposTotales.add(campoSubtotal);
        panelCamposTotales.add(etiquetaTotal);
        panelCamposTotales.add(campoTotal);

        panelTotales.add(panelCamposTotales, BorderLayout.CENTER);

        // Crear el JLabel para el total suma y agregarlo al borde inferior derecho
        etiquetaTotalSuma = new JLabel("Total Suma: 0.00");
        etiquetaTotalSuma.setFont(new Font("Arial", Font.BOLD, 24)); // Cambia la fuente y el tamaño de la etiqueta
        JPanel panelEtiquetaTotalSuma = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelEtiquetaTotalSuma.setBackground(new Color(240, 255, 255));
        panelEtiquetaTotalSuma.add(etiquetaTotalSuma);
        panelTotales.add(panelEtiquetaTotalSuma, BorderLayout.SOUTH);

        panelInferior.add(panelTotales);
        
        actualizarNumeroFactura();

        // Añadir el botón Cobrar en el panel de totales
        JButton botonCobrar = new JButton("Cobrar");
        panelTotales.add(botonCobrar, BorderLayout.NORTH); // Añadir el botón en la parte superior del panel de totales
        botonCobrar.setPreferredSize(new Dimension(100, 70)); // Cambia el tamaño del botón a 100x50
        botonCobrar.setBackground(Color.BLUE); // Cambiar el color de fondo del botón a rojo
        botonCobrar.setForeground(Color.WHITE); // Cambiar el color del texto del botón a blanco para mayor contraste

        // Verificar si la tabla está vacía para activar/desactivar el botón "Quitar"
        tablaProductos.getModel().addTableModelListener(e -> {
            if (tablaProductos != null && tablaProductos.getRowCount() >= 0) {
                botonQuitar.setEnabled(true);
            } else {
                botonQuitar.setEnabled(false);
            }
        });
        
        botonCobrar.addActionListener((ActionEvent e) -> finalizarVenta());
        botonQuitar.addActionListener(e -> quitarProducto());

        add(panelInferior, BorderLayout.SOUTH);

        // Acción del botón Buscar artículo
        botonBuscar.addActionListener((ActionEvent e) -> buscarArticulo());
        }
        
    
    
        private void actualizarFechaHora() {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date now = new Date();
            campoFecha.setText(sdf.format(now));
        }
        
         // buscar 
// Importar el ícono de advertencia

private void buscarArticulo() {
    String codigoBarra = campoCodigoBarras.getText().trim();
    String campocantidad = campoCantidad.getText().trim();
    String tipo = (String) comboTipo.getSelectedItem();
    campoTipo.setText(tipo);

    if (codigoBarra.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Ingrese un código de barras", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    if (campocantidad.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Ingrese la cantidad", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    try {
        int cantidad = Integer.parseInt(campocantidad);

        try (Connection conexion = conexionBD.getConnection()) {
            String consulta = "SELECT s.Cantidad AS StockCantidad, p.IdProducto, p.Descripcion, s.Precio_Venta " +
                              "FROM stock s " +
                              "JOIN producto p ON s.IdProducto = p.IdProducto " +
                              "WHERE p.IdProducto = ?";
            PreparedStatement pst = conexion.prepareStatement(consulta);
            pst.setString(1, codigoBarra);

            ResultSet rs = pst.executeQuery();
            boolean productoEncontrado = false;

            if (rs.next()) {
                productoEncontrado = true;
                int idProducto = rs.getInt("IdProducto");
                String descripcion = rs.getString("Descripcion");

                // Obtener el valor de Precio_Venta como Object para verificar si es NULL
                Object precioVentaObj = rs.getObject("Precio_Venta");
                float precioVenta = (precioVentaObj != null) ? (float) precioVentaObj : 0.0f;

                int stockCantidad = rs.getInt("StockCantidad");

                // Verificar si el precio es nulo o cero
                if (precioVenta == 0) {
                    JOptionPane.showMessageDialog(this, "El producto '" + descripcion + "' no tiene precio establecido. No se puede vender.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Verificar si el stock es cero
                if (stockCantidad == 0) {
                    JOptionPane.showMessageDialog(this, "El producto '" + descripcion + "' está agotado. No hay unidades disponibles.", "Sin stock", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Verificar que la cantidad solicitada no supere el stock disponible
                if (cantidad > stockCantidad) {
                    JOptionPane.showMessageDialog(this, "No hay suficiente stock disponible. Cantidad en stock: " + stockCantidad, "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Simular la actualización del stock
                int stockSimulado = stockCantidad;

                boolean encontradoEnTabla = false;

                // Buscar si el producto ya está en la tabla de venta
                for (int i = 0; i < model.getRowCount(); i++) {
                    if ((int) model.getValueAt(i, 0) == idProducto) {
                        int cantidadActual = (int) model.getValueAt(i, 2); 
                        cantidadActual += cantidad;

                        if (cantidadActual > stockSimulado) {
                            JOptionPane.showMessageDialog(this, "No hay suficiente stock disponible. Cantidad en stock: " + stockSimulado, "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        float importeNuevo = cantidadActual * precioVenta;
                        model.setValueAt(cantidadActual, i, 2); 
                        model.setValueAt(precioVenta, i, 3); 
                        model.setValueAt(importeNuevo, i, 4); 

                        encontradoEnTabla = true;
                        stockSimulado -= cantidad;  // Restar la cantidad al stock simulado
                        break;
                    }
                }

                // Si no está en la tabla, agregar una nueva fila
                if (!encontradoEnTabla) {
                    float importe = cantidad * precioVenta;
                    model.addRow(new Object[]{idProducto, descripcion, cantidad, precioVenta, importe});
                    stockSimulado -= cantidad;  // Restar la cantidad al stock simulado
                }

                // Verificar si el stock simulado es menor o igual a 20 para mostrar alerta de stock bajo
                if (stockSimulado <= 20) {
                    String mensaje = "<html><body><span style='color:red; font-size:14px; font-weight:bold;'>¡ALERTA!</span><br>QUEDAN SOLO <b>" + stockSimulado + "</b> UNIDADES EN STOCK</body></html>";
                    ImageIcon iconoAdvertencia = new ImageIcon("ruta/al/icono/advertencia.png");
                    JOptionPane.showMessageDialog(this, mensaje, "Advertencia de Stock Bajo", JOptionPane.WARNING_MESSAGE, iconoAdvertencia);
                }

                actualizarTotal();
            }

            if (!productoEncontrado) {
                JOptionPane.showMessageDialog(this, "No se encontró el producto con el código de barras ingresado", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al conectar con la base de datos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "La cantidad debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

// Al finalizar la compra, actualizar el stock en la base de datos
public void finalizarCompra() {
    try (Connection conexion = conexionBD.getConnection()) {
        for (int i = 0; i < model.getRowCount(); i++) {
            int idProducto = (int) model.getValueAt(i, 0);
            int cantidadVendida = (int) model.getValueAt(i, 2);

            String actualizarStock = "UPDATE stock SET Cantidad = Cantidad - ? WHERE IdProducto = ?";
            PreparedStatement pstActualizar = conexion.prepareStatement(actualizarStock);
            pstActualizar.setInt(1, cantidadVendida);
            pstActualizar.setInt(2, idProducto);
            pstActualizar.executeUpdate();
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error al conectar con la base de datos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}




        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        





// Método para actualizar el total acumulado



//fin de la busqueda

        
        
    
   private void quitarProducto() {
        DefaultTableModel model = (DefaultTableModel) tablaProductos.getModel();

        int selectedRow = tablaProductos.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un producto para quitar", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            model.removeRow(selectedRow);
            actualizarTotal();
        }

        // Después de quitar la fila, actualiza el estado del botón
        if (model.getRowCount() == 0) {
            botonQuitar.setEnabled(false);
        }
    }

    
    private void actualizarTotal() {
        float totalSuma = 0;
        float subtotal = 0;

        for (int i = 0; i < model.getRowCount(); i++) {
            subtotal += (float) model.getValueAt(i, 4);
            // subtotal += totalSuma;
        }
                double total = subtotal; // Ajusta esto si hay impuestos, descuentos, etc.

       campoSubtotal.setText(String.format("%.2f", subtotal));
        campoTotal.setText(String.format("%.2f", total));
        etiquetaTotalSuma.setText(String.format("Total Suma: %.2f", total)); // Actualizar el JLabel
    }
    
    
   
    
    ///FINALIZAR VENTA
    // Método para finalizar la venta
private void finalizarVenta() {
    if (model.getRowCount() == 0 || parseFloat(campoTotal.getText()) == 0) {
        JOptionPane.showMessageDialog(this, "No hay productos en la venta o el valor total es 0. No se puede finalizar la venta.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Crear los botones personalizados
    Object[] options = {"Sí", "No"};
    
    int opcion = JOptionPane.showOptionDialog(this, 
        "¿Desea finalizar la venta?", 
        "Confirmación", 
        JOptionPane.YES_NO_OPTION, 
        JOptionPane.QUESTION_MESSAGE, 
        null, 
        options, // Aquí se pasan los botones personalizados
        options[1]); // Botón que se selecciona por defecto (No)

    // Verifica si se ha seleccionado "Sí"
    if (opcion == 0) { // Si 0 corresponde al botón "Sí"
        registrarVenta(); // Llama al método para registrar la venta
        actualizarStock(); // Llama al método para actualizar el stock
        JOptionPane.showMessageDialog(this, "Venta finalizada con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        limpiarCampos(); // Limpia los campos después de finalizar la venta
        actualizarNumeroFactura(); // Actualizar el número de comprobante para la siguiente venta
    }
}


private float parseFloat(String text) {
    text = text.replace(",", "."); // Reemplaza la coma con un punto
    try {
        return Float.parseFloat(text);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Error en el formato del total. No se puede finalizar la venta.", "Error", JOptionPane.ERROR_MESSAGE);
        return 0;
    }
}



private void registrarVenta() {
    String tipoComprobante = (String) comboTipo.getSelectedItem();
    String observaciones = areaObservaciones.getText().trim();
    Date fechaVenta = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String fechaVentaString = sdf.format(fechaVenta);
    String totalStr = campoTotal.getText().trim().replace(",", ".");
    float total = Float.parseFloat(totalStr);
    int idUsuario = 1; // Aquí deberías obtener el ID del usuario que realiza la venta, actualmente es 1 como ejemplo

    String numeroComprobante = campoNumero.getText(); // Obtener el número de comprobante del campo

    try (Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/buffet", "root", "1234")) {
        // Insertar en la tabla venta
        String sqlInsertVenta = "INSERT INTO venta (NumeroComprobante, Fecha, Monto, IdUsuario) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pst = conexion.prepareStatement(sqlInsertVenta, Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, numeroComprobante);
            pst.setString(2, fechaVentaString);
            pst.setFloat(3, total);
            pst.setInt(4, idUsuario);
            pst.executeUpdate();

            // Obtener el ID generado automáticamente
            ResultSet generatedKeys = pst.getGeneratedKeys();
            int idVenta = 0;
            if (generatedKeys.next()) {
                idVenta = generatedKeys.getInt(1);
            }

            // Insertar detalles de pago en la tabla ventapagodetalle
            String sqlInsertDetallePago = "INSERT INTO ventapagodetalle (IdVenta, IdMedioPago, Monto) VALUES (?, ?, ?)";
            try (PreparedStatement pstDetallePago = conexion.prepareStatement(sqlInsertDetallePago)) {
                pstDetallePago.setInt(1, idVenta);
                int idMedioPago = comboTipo.getSelectedIndex() + 1; // Obtener el ID de medio de pago (1-indexed)
                pstDetallePago.setInt(2, idMedioPago);
                pstDetallePago.setFloat(3, total);
                pstDetallePago.executeUpdate();
            }

            // Insertar detalles de productos en la tabla ventaproductodetalle
            String sqlInsertProductoDetalle = "INSERT INTO ventaproductodetalle (IdVentaProducto, IdVenta, IdProducto, Cantidad, PrecioUnitario) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement pstProductoDetalle = conexion.prepareStatement(sqlInsertProductoDetalle)) {
                // Iterar sobre las filas del modelo de la tabla para obtener los productos
                for (int i = 0; i < model.getRowCount(); i++) {
                    int idProducto = (int) model.getValueAt(i, 0); // ID del producto
                    int cantidad = (int) model.getValueAt(i, 2); // Cantidad vendida
                    float precioUnitario = (float) model.getValueAt(i, 3); // Precio unitario

                    pstProductoDetalle.setInt(1, 0); // Dejar que la base de datos genere el IdVentaProducto automáticamente si es AUTO_INCREMENT
                    pstProductoDetalle.setInt(2, idVenta);
                    pstProductoDetalle.setInt(3, idProducto);
                    pstProductoDetalle.setInt(4, cantidad);
                    pstProductoDetalle.setFloat(5, precioUnitario);
                    pstProductoDetalle.executeUpdate();
                }
            }
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error al registrar la venta: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}






    private void actualizarStock() {
        try (Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/buffet", "root", "1234")) {
            for (int i = 0; i < model.getRowCount(); i++) {
                int idProducto = (int) model.getValueAt(i, 0);
                int cantidadVendida = (int) model.getValueAt(i, 2);

                String sqlUpdateStock = "UPDATE stock SET Cantidad = Cantidad - ? WHERE IdProducto = ?";
                try (PreparedStatement pst = conexion.prepareStatement(sqlUpdateStock)) {
                    pst.setInt(1, cantidadVendida);
                    pst.setInt(2, idProducto);
                    pst.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar el stock: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
  
    //// FIN CODIGO FINALIZAR VENTA 
    

    
    
    ///codigo numero factura
    private void actualizarNumeroFactura() {
    try (Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/buffet", "root", "1234")) {
        String sql = "SELECT MAX(NumeroComprobante) FROM venta"; // Obtener el último número de comprobante registrado
        try (PreparedStatement pst = conexion.prepareStatement(sql)) {
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String ultimoComprobante = rs.getString(1);
                if (ultimoComprobante != null && !ultimoComprobante.isEmpty()) {
                    // Incrementar el número de comprobante
                    int numero = Integer.parseInt(ultimoComprobante) + 1;
                    campoNumero.setText(String.format("%010d", numero)); // Formatear el número a 10 dígitos
                } else {
                    // Si no hay ningún comprobante registrado aún, empezar desde 1
                    campoNumero.setText("0000000001");
                }
            }
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error al obtener el próximo número de comprobante: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    } catch (NumberFormatException ex) {
        // Manejar la excepción de NumberFormatException, por ejemplo, estableciendo un valor predeterminado
        campoNumero.setText("0000000001");
    }
}



    
    ////fin codigo numero de factura
    
    
    
    private void limpiarCampos() {
        campoCodigoBarras.setText("");
        campoCantidad.setText("");
        campoSubtotal.setText("");
        model.setRowCount(0); // Limpiar la tabla
        etiquetaTotalSuma.setText("Total Suma: 0.00");
        campoFecha.setText("");
        campoTipo.setText("");
        campoNumero.setText("");
        areaObservaciones.setText("");
        campoTipo.setText("");
        campoNumero.setText("0000000001");
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Sistema de Facturación");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1300, 700);
            frame.setLayout(new BorderLayout());

            JDesktopPane desktopPane = new JDesktopPane();
            frame.add(desktopPane, BorderLayout.CENTER);

            MainWindow mainWindow = new MainWindow();
            desktopPane.add(mainWindow);
            mainWindow.setVisible(true);

            frame.setVisible(true);
        });
    }
}
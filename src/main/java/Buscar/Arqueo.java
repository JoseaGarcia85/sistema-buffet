package Buscar;

import Conexion.conexionBD;
import com.toedter.calendar.JDateChooser; // Importar para usar el JDateChooser
import java.sql.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Arqueo extends JInternalFrame {

    private JTextArea textAreaResultados;
    private JButton btnRealizarArqueo;
    private JButton btnCancelar;
    private JLabel labelEstadoArqueo;
    private JDateChooser dateChooser;  // Componente para seleccionar la fecha
    private double totalVentasPrevio = 0;
    private java.sql.Date fechaUltimoArqueo;
    private JTextField textFieldFecha;
    
    public Arqueo() {
        
        setTitle("Arqueo de Ventas");
        setSize(800, 600);
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);

        // Centro la ventana en la pantalla
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - getWidth()) / 2;
        int y = (screenSize.height - getHeight()) / 2;
        setLocation(x, y);

        // Inicializar el JTextField para la fecha
        textFieldFecha = new JTextField();
        
        // Obtener la fecha de hoy utilizando LocalDate
        LocalDate fechaHoy = LocalDate.now();
        
        // Definir el formato para la fecha
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        // Establecer la fecha de hoy en el JTextField
        textFieldFecha.setText(fechaHoy.format(formatoFecha));
        
        // Configurar el tamaño del JTextField
        textFieldFecha.setColumns(10);

        JMenuBar menuBar = new JMenuBar();
        JMenu menuArchivo = new JMenu("Archivo");
        JMenuItem itemSalir = new JMenuItem("Salir");
        itemSalir.addActionListener(e -> dispose());
        menuArchivo.add(itemSalir);
        menuBar.add(menuArchivo);
        setJMenuBar(menuBar);

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));

        btnRealizarArqueo = new JButton("Realizar Arqueo");
        btnRealizarArqueo.addActionListener(this::realizarArqueo);
        btnRealizarArqueo.setEnabled(false); // Inicialmente deshabilitado

        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose()); // Cerrar la ventana al cancelar

        labelEstadoArqueo = new JLabel();
        labelEstadoArqueo.setFont(new Font("Arial", Font.BOLD, 14));
        labelEstadoArqueo.setHorizontalAlignment(SwingConstants.CENTER);

        // Añadir JDateChooser para seleccionar la fecha
        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("yyyy-MM-dd");
        dateChooser.setPreferredSize(new Dimension(150, 30));

        // Establecer la fecha de hoy también en el JDateChooser
        dateChooser.setDate(java.sql.Date.valueOf(fechaHoy));  // Convertir LocalDate a java.sql.Date

        panelBotones.add(new JLabel("Seleccionar fecha:"));
        panelBotones.add(dateChooser);
        panelBotones.add(btnRealizarArqueo);
        panelBotones.add(btnCancelar);

        textAreaResultados = new JTextArea();
        textAreaResultados.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textAreaResultados);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        panelPrincipal.add(labelEstadoArqueo, BorderLayout.NORTH);
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        add(panelPrincipal);

        // Cargar el último arqueo y verificar el estado
        cargarUltimoArqueo();
    }
    
    
    
    ///tipo `pagos
    private void cargarUltimoArqueo() {
        Connection conexion = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        StringBuilder resultados = new StringBuilder();

        try {
            // Establecer conexión
            conexion = conexionBD.getConnection();

            // Consulta para obtener el último arqueo
            String sqlUltimoArqueo = "SELECT fecha, total_ventas, total_caja, observaciones FROM arqueos_ventas ORDER BY fecha DESC LIMIT 1";
            pst = conexion.prepareStatement(sqlUltimoArqueo);
            rs = pst.executeQuery();

            if (rs.next()) {
                fechaUltimoArqueo = rs.getDate("fecha");
                resultados.append("Último Arqueo:\n");
                resultados.append("Fecha: ").append(fechaUltimoArqueo).append("\n");
                resultados.append("Total Ventas: $").append(rs.getDouble("total_ventas")).append("\n");
                resultados.append("Total Caja: $").append(rs.getDouble("total_caja")).append("\n");
                resultados.append("Observaciones: ").append(rs.getString("observaciones")).append("\n");
                totalVentasPrevio = rs.getDouble("total_ventas");

                // Verificar si se pueden realizar más arqueos
                verificarVentasDesdeUltimoArqueo(conexion, fechaUltimoArqueo);
            } else {
                resultados.append("No hay arqueos registrados aún.\n");
                btnRealizarArqueo.setEnabled(true); // Habilitar arqueo si no hay registros
            }

            textAreaResultados.setText(resultados.toString());

        } catch (SQLException e) {
            e.printStackTrace();
            textAreaResultados.setText("Error al cargar el último arqueo: " + e.getMessage());
        } finally {
            conexionBD.cerrarRecursos(rs, pst, conexion);
        }
    }

    private void verificarVentasDesdeUltimoArqueo(Connection conexion, java.sql.Date fechaUltimoArqueo) {
    PreparedStatement pst = null;
    ResultSet rs = null;

    try {
        // Consulta para verificar si hay ventas nuevas desde el último arqueo
        String sqlVerificarVentas = "SELECT SUM(v.Monto) AS TotalVentas " +
                "FROM venta v " +
                "WHERE DATE(v.Fecha) >= ?";  
        
        pst = conexion.prepareStatement(sqlVerificarVentas);
        pst.setDate(1, fechaUltimoArqueo);
        rs = pst.executeQuery();

        if (rs.next()) {
            double totalVentas = rs.getDouble("TotalVentas");

            if (totalVentas > 0) {
                labelEstadoArqueo.setText("Se pueden realizar nuevos arqueos.");
                btnRealizarArqueo.setEnabled(true); // Habilitar botón si hay nuevas ventas
            } else {
                labelEstadoArqueo.setText("No se han creado nuevas ventas desde el último arqueo.");
                btnRealizarArqueo.setEnabled(false); // Deshabilitar si no hay ventas nuevas
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
        labelEstadoArqueo.setText("Error al verificar ventas: " + e.getMessage());
    } finally {
        conexionBD.cerrarRecursos(rs, pst, conexion);
    }
}


    public void realizarArqueo(ActionEvent e) {
    Connection conexion = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    StringBuilder resultados = new StringBuilder();

    double totalVentasEfectivo = 0;
    double totalVentasMercadoPago = 0;
    double totalVentasAmbos = 0;

    try {
        conexion = conexionBD.getConnection();

        // Obtener la fecha seleccionada del JDateChooser
        java.sql.Date fechaSeleccionada = new java.sql.Date(dateChooser.getDate().getTime());

        // Consulta para obtener las ventas del día en efectivo
        String sqlEfectivo = "SELECT v.IdVenta, v.Fecha, vp.Cantidad, p.NombreProducto, vp.PrecioUnitario, " +
                "(vp.Cantidad * vp.PrecioUnitario) AS Monto " +
                "FROM venta v " +
                "JOIN ventaproductodetalle vp ON v.IdVenta = vp.IdVenta " +
                "JOIN producto p ON vp.IdProducto = p.IdProducto " +
                "JOIN ventapagodetalle vpago ON v.IdVenta = vpago.IdVenta " +
                "WHERE DATE(v.Fecha) = ? AND vpago.IdMedioPago = 1";  // Efectivo

        resultados.append("Ventas del día en efectivo:\n");
        pst = conexion.prepareStatement(sqlEfectivo);
        pst.setDate(1, fechaSeleccionada);  // Usar la fecha seleccionada
        rs = pst.executeQuery();
        totalVentasEfectivo = mostrarResultados(rs, resultados);
        resultados.append("Total de ventas en efectivo: $").append(totalVentasEfectivo).append("\n\n");

        // Consulta para obtener las ventas del día con Mercado Pago
        String sqlMercadoPago = "SELECT v.IdVenta, v.Fecha, vp.Cantidad, p.NombreProducto, vp.PrecioUnitario, " +
                "(vp.Cantidad * vp.PrecioUnitario) AS Monto " +
                "FROM venta v " +
                "JOIN ventaproductodetalle vp ON v.IdVenta = vp.IdVenta " +
                "JOIN producto p ON vp.IdProducto = p.IdProducto " +
                "JOIN ventapagodetalle vpago ON v.IdVenta = vpago.IdVenta " +
                "WHERE DATE(v.Fecha) = ? AND vpago.IdMedioPago = 2";  // Mercado Pago

        resultados.append("Ventas del día con Mercado Pago:\n");
        pst = conexion.prepareStatement(sqlMercadoPago);
        pst.setDate(1, fechaSeleccionada);  // Usar la fecha seleccionada
        rs = pst.executeQuery();
        totalVentasMercadoPago = mostrarResultados(rs, resultados);
        resultados.append("Total de ventas con Mercado Pago: $").append(totalVentasMercadoPago).append("\n\n");

        // Consulta para obtener las ventas del día con ambos medios de pago
        String sqlAmbos = "SELECT v.IdVenta, v.Fecha, vp.Cantidad, p.NombreProducto, vp.PrecioUnitario, " +
                "(vp.Cantidad * vp.PrecioUnitario) AS Monto " +
                "FROM venta v " +
                "JOIN ventaproductodetalle vp ON v.IdVenta = vp.IdVenta " +
                "JOIN producto p ON vp.IdProducto = p.IdProducto " +
                "JOIN ventapagodetalle vpago ON v.IdVenta = vpago.IdVenta " +
                "WHERE DATE(v.Fecha) = ? AND vpago.IdMedioPago = 3";  // Efectivo y Mercado Pago

        resultados.append("Ventas del día con ambos medios de pago:\n");
        pst = conexion.prepareStatement(sqlAmbos);
        pst.setDate(1, fechaSeleccionada);  // Usar la fecha seleccionada
        rs = pst.executeQuery();
        totalVentasAmbos = mostrarResultados(rs, resultados);
        resultados.append("Total de ventas (Efectivo y Mercado Pago): $").append(totalVentasAmbos).append("\n\n");

        // Sumar los totales de las tres formas de pago
        double totalVentas = totalVentasEfectivo + totalVentasMercadoPago + totalVentasAmbos;

        // Mostrar el total vendido al final
        resultados.append("Total vendido (Efectivo + Mercado Pago + Efectivo y Mercado Pago): $").append(totalVentas).append("\n");

        textAreaResultados.setText(resultados.toString());

        // Verificar si se debe actualizar el arqueo o realizar uno nuevo
        if (fechaUltimoArqueo != null && fechaUltimoArqueo.equals(fechaSeleccionada)) {
            actualizarArqueo(conexion, totalVentas, totalVentas); // totalCaja puede ser el mismo total para simplificar
            labelEstadoArqueo.setText("Arqueo actualizado con éxito.");
        } else {
            // Guardar resultados en la tabla arqueos_ventas
            guardarArqueo(conexion, totalVentas, totalVentas); // totalCaja puede ser el mismo total para simplificar
            labelEstadoArqueo.setText("Arqueo realizado con éxito.");
        }

        btnRealizarArqueo.setEnabled(false); // Deshabilitar después de realizar el arqueo

    } catch (SQLException ex) {
        ex.printStackTrace();
        textAreaResultados.setText("Error al realizar el arqueo: " + ex.getMessage());
    } finally {
        conexionBD.cerrarRecursos(rs, pst, conexion);
    }
}


private static double mostrarResultados(ResultSet rs, StringBuilder resultados) throws SQLException {
    double total = 0;

    // Agregar un margen adicional en la primera línea
    resultados.append("\n"); // Espacio adicional
    resultados.append(String.format("%-10s  %-20s  %-30s  %-12s  %-20s  %-15s%n",
            "ID Venta", "Fecha", "Producto", "Cantidad", "Precio Unitario", "Monto"));
    resultados.append("---------------------------------------------------------------------------------------------\n");

    while (rs.next()) {
        int idVenta = rs.getInt("IdVenta");
        java.sql.Date fecha = rs.getDate("Fecha");
        double monto = rs.getDouble("Monto");
        String producto = rs.getString("NombreProducto");
        int cantidad = rs.getInt("Cantidad");
        double precioUnitario = rs.getDouble("PrecioUnitario");

        // Truncar el nombre del producto si es demasiado largo
        if (producto.length() > 30) {
            producto = producto.substring(0, 27) + "..."; // Truncar a 27 y agregar "..."
        }

        // Añadir cada fila de resultados con formato adecuado
        resultados.append(String.format("%-10d  %-20s  %-30s  %-12d  %-20.2f  %-15.2f%n",
                idVenta, fecha, producto, cantidad, precioUnitario, monto));
        total += monto;
    }
    return total;
}





    private void guardarArqueo(Connection conexion, double totalVentas, double totalCaja) {
        PreparedStatement pst = null;

        try {
            String sqlInsert = "INSERT INTO arqueos_ventas (fecha, total_ventas, total_caja, observaciones) VALUES (CURDATE(), ?, ?, 'Arqueo diario')";
            pst = conexion.prepareStatement(sqlInsert);
            pst.setDouble(1, totalVentas);
            pst.setDouble(2, totalCaja);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexionBD.cerrarRecursos(null, pst, null);
        }
    }

    private void actualizarArqueo(Connection conexion, double totalVentas, double totalCaja) {
        PreparedStatement pst = null;

        try {
            String sqlUpdate = "UPDATE arqueos_ventas SET total_ventas = ?, total_caja = ? WHERE fecha = CURDATE()";
            pst = conexion.prepareStatement(sqlUpdate);
            pst.setDouble(1, totalVentas);
            pst.setDouble(2, totalCaja);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexionBD.cerrarRecursos(null, pst, null);
        }
    }
}

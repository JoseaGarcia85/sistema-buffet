package Buscar;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import com.toedter.calendar.JDateChooser;
import java.sql.Connection;
import java.sql.PreparedStatement;
import Conexion.conexionBD;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.NumberFormat;
import java.text.ParseException;



public class BuscarVentasConCalendario extends JInternalFrame {

    public BuscarVentasConCalendario() {
        super("Buscar Ventas", true, true, true, true);
        setSize(1000, 700);
        setLayout(new BorderLayout());
        
        // Llamar directamente al diálogo de calendario cuando se abre la ventana
        abrirDialogoCalendario();
    }

    
private void abrirDialogoCalendario() {
    JDialog dialogo = new JDialog((JFrame) SwingUtilities.getWindowAncestor(this), "Seleccionar Rango de Fecha y Hora", false);
    dialogo.setSize(350, 350);
    dialogo.setLayout(new BorderLayout());
    dialogo.setLocationRelativeTo(this);

    JPanel panelPrincipal = new JPanel(new BorderLayout(15, 15));
    panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

    // Panel para el rango de fechas
    JPanel panelFechas = new JPanel(new GridLayout(2, 2, 10, 10));
    panelFechas.setBorder(BorderFactory.createTitledBorder("Seleccione el Rango de Fecha"));

    JLabel labelFechaInicio = new JLabel("Fecha Inicio:");
    labelFechaInicio.setFont(new Font("Arial", Font.BOLD, 14));
    JDateChooser dateChooserInicio = new JDateChooser();
    dateChooserInicio.setDateFormatString("yyyy-MM-dd");
    panelFechas.add(labelFechaInicio);
    panelFechas.add(dateChooserInicio);

    JLabel labelFechaFin = new JLabel("Fecha Fin:");
    labelFechaFin.setFont(new Font("Arial", Font.BOLD, 14));
    JDateChooser dateChooserFin = new JDateChooser();
    dateChooserFin.setDateFormatString("yyyy-MM-dd");
    panelFechas.add(labelFechaFin);
    panelFechas.add(dateChooserFin);

    panelPrincipal.add(panelFechas, BorderLayout.NORTH);

    // Panel para el rango de hora
    JPanel panelHora = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
    panelHora.setBorder(BorderFactory.createTitledBorder("Seleccione el Rango de Hora"));

    JLabel labelDesde = new JLabel("Desde:");
    panelHora.add(labelDesde);

    // Configura la hora de inicio a las 00:00
    SpinnerDateModel modelDesde = new SpinnerDateModel();
    JSpinner spinnerDesde = new JSpinner(modelDesde);
    spinnerDesde.setEditor(new JSpinner.DateEditor(spinnerDesde, "HH:mm"));
    try {
        modelDesde.setValue(new SimpleDateFormat("HH:mm").parse("00:00")); // Establece 00:00 como valor predeterminado
    } catch (ParseException e) {
        e.printStackTrace();
    }
    panelHora.add(spinnerDesde);

    JLabel labelHasta = new JLabel("Hasta:");
    panelHora.add(labelHasta);

    // Configura la hora de fin a las 23:00
    SpinnerDateModel modelHasta = new SpinnerDateModel();
    JSpinner spinnerHasta = new JSpinner(modelHasta);
    spinnerHasta.setEditor(new JSpinner.DateEditor(spinnerHasta, "HH:mm"));
    try {
        modelHasta.setValue(new SimpleDateFormat("HH:mm").parse("23:00")); // Establece 23:00 como valor predeterminado
    } catch (ParseException e) {
        e.printStackTrace();
    }

    panelHora.add(spinnerHasta);

    panelPrincipal.add(panelHora, BorderLayout.CENTER);

    // Panel de botones
    JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
    JButton botonConfirmar = new JButton("Confirmar", new ImageIcon("confirm_icon.png"));  // Asegúrate de tener el ícono
    JButton botonCancelar = new JButton("Cancelar", new ImageIcon("cancel_icon.png"));
    botonConfirmar.setToolTipText("Confirmar selección de fechas y horas");
    botonCancelar.setToolTipText("Cancelar y cerrar el diálogo");

    panelBotones.add(botonConfirmar);
    panelBotones.add(botonCancelar);
    panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

    dialogo.add(panelPrincipal, BorderLayout.CENTER);

    // Acción al confirmar
    botonConfirmar.addActionListener(event -> {
        Date fechaInicio = dateChooserInicio.getDate();
        Date fechaFin = dateChooserFin.getDate();

        if (fechaInicio == null || fechaFin == null) {
            JOptionPane.showMessageDialog(dialogo, "Por favor seleccione ambas fechas.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        String fechaInicioStr = formatoFecha.format(fechaInicio);
        String fechaFinStr = formatoFecha.format(fechaFin);

        Date horaDesde = (Date) spinnerDesde.getValue();
        Date horaHasta = (Date) spinnerHasta.getValue();
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");

        String horaDesdeStr = formatoHora.format(horaDesde);
        String horaHastaStr = formatoHora.format(horaHasta);

        mostrarDialogoTabla((JFrame) SwingUtilities.getWindowAncestor(this), this, fechaInicioStr, fechaFinStr, horaDesdeStr, horaHastaStr);
        dialogo.dispose();
    });

    botonCancelar.addActionListener(event -> dialogo.dispose());

    dialogo.setVisible(true);
}


// Actualiza el método mostrarDialogoTabla para que acepte el rango de fechas y horas
private static void mostrarDialogoTabla(JFrame marcoPadre, JInternalFrame ventanaVentas, String fechaInicio, String fechaFin, String horaDesde, String horaHasta) {
    JDialog dialogoTabla = new JDialog(marcoPadre, "Ventas en el Rango Seleccionado", true);
    dialogoTabla.setSize(1200, 500);
    dialogoTabla.setLayout(new BorderLayout());
    dialogoTabla.setLocationRelativeTo(marcoPadre);

    // Eliminamos la columna "ID Venta" del encabezado
    DefaultTableModel modelo = new DefaultTableModel(new String[]{"Numero Comprobante", "Fecha y Hora", "Monto"}, 0);

    // Verificar que las fechas y horas ingresadas sean válidas
    if (fechaInicio == null || fechaFin == null || horaDesde == null || horaHasta == null) {
        JOptionPane.showMessageDialog(marcoPadre, "Debe seleccionar el rango de fecha y hora completo.", "Error", JOptionPane.WARNING_MESSAGE);
        return;
    }

    try (Connection conexion = conexionBD.getConnection()) {
        // Ajusta la consulta para utilizar el formato correcto
        String query = "SELECT NumeroComprobante, Fecha, Monto FROM venta " +
                       "WHERE Fecha BETWEEN ? AND ?";

        // Concatenar las horas con las fechas para obtener el rango completo
        String fechaHoraDesde = fechaInicio + " " + horaDesde + ":00"; // Agrega los segundos
        String fechaHoraHasta = fechaFin + " " + horaHasta + ":59"; // Agrega los segundos

        try (PreparedStatement pst = conexion.prepareStatement(query)) {
            pst.setString(1, fechaHoraDesde); // Fecha y hora de inicio
            pst.setString(2, fechaHoraHasta);  // Fecha y hora de fin

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String numeroComprobante = rs.getString("NumeroComprobante");
                Timestamp fechaHoraVenta = rs.getTimestamp("Fecha");
                double monto = rs.getDouble("Monto");

                String montoFormateado = NumberFormat.getCurrencyInstance().format(monto);

                // Formatear la fecha y hora
                SimpleDateFormat formatoFechaHora = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                String fechaHoraFormateada = formatoFechaHora.format(fechaHoraVenta);

                // Añadimos la fila sin incluir el ID
                modelo.addRow(new Object[]{numeroComprobante, fechaHoraFormateada, montoFormateado});
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(dialogoTabla, "Error al obtener las ventas de la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
    }

    JTable tabla = new JTable(modelo);
    tabla.setFillsViewportHeight(true);
    tabla.setRowHeight(25);

    // Cambiar el color de fondo de las celdas de los encabezados
    tabla.getTableHeader().setBackground(Color.gray); 
    tabla.getTableHeader().setForeground(Color.WHITE); 

    tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    JScrollPane panelDesplazamiento = new JScrollPane(tabla);
    dialogoTabla.add(panelDesplazamiento, BorderLayout.CENTER);

    JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JButton botonSeleccionar = new JButton("Seleccionar");
    botonSeleccionar.setEnabled(false);
    panelBotones.add(botonSeleccionar);

    JButton botonCancelar = new JButton("Cancelar");
    panelBotones.add(botonCancelar);
    dialogoTabla.add(panelBotones, BorderLayout.SOUTH);

    tabla.getSelectionModel().addListSelectionListener(event -> botonSeleccionar.setEnabled(tabla.getSelectedRow() != -1));

    botonSeleccionar.addActionListener(event -> {
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada >= 0) {
            // Obtén los detalles necesarios sin utilizar el ID de la venta
            String numeroComprobante = String.valueOf(tabla.getValueAt(filaSeleccionada, 0));
            mostrarDetallesVentaDialogo(marcoPadre, numeroComprobante);
        }
    });

    botonCancelar.addActionListener(event -> {
        dialogoTabla.dispose();
        ventanaVentas.dispose();
    });

    dialogoTabla.setVisible(true);
}

 
 
private static void mostrarDetallesVentaDialogo(JFrame marcoPadre, String idVenta) {
    JDialog dialogoDetalles = new JDialog(marcoPadre, "Detalles de la Venta", true);
    dialogoDetalles.setSize(1500, 600);
    dialogoDetalles.setLayout(new BorderLayout());
    dialogoDetalles.setLocationRelativeTo(marcoPadre);

    // Crear el modelo de la tabla para los detalles de la venta, sin la columna "ID Venta"
    DefaultTableModel modeloDetalles = new DefaultTableModel(
        new String[]{"ID Producto", "Nombre Producto", "Descripción", "Precio Unitario", "Cantidad", "Precio", "Nombre Medio Pago", "Total"}, 0);

    Connection conexion = null;
    PreparedStatement pst = null;

    double totalFactura = 0.0; // Variable para acumular el total

    // Declarar currencyFormat aquí
    NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();

    try {
        conexion = conexionBD.getConnection();
        
        // Consulta SQL para obtener los detalles de la venta, sin seleccionar "IdVenta"
        String queryDetalles = "SELECT " +
                                "vd.IdProducto, " +
                                "p.NombreProducto, " +
                                "p.Descripcion, " +
                                "vd.PrecioUnitario, " +
                                "vd.Cantidad, " +
                                "vd.PrecioUnitario * vd.Cantidad AS Precio, " +
                                "mp.NombreMedioPago " +
                                "FROM ventaproductodetalle vd " +
                                "JOIN producto p ON vd.IdProducto = p.IdProducto " +
                                "JOIN venta v ON vd.IdVenta = v.IdVenta " +
                                "JOIN ventapagodetalle vpd ON v.IdVenta = vpd.IdVenta " +
                                "JOIN mediopago mp ON vpd.IdMedioPago = mp.IdMedioPago " +
                                "WHERE vd.IdVenta = ?";

        pst = conexion.prepareStatement(queryDetalles);
        pst.setString(1, idVenta);  // Filtrar por ID de venta

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            double precioUnitario = rs.getDouble("PrecioUnitario");
            double precio = rs.getDouble("Precio");
            modeloDetalles.addRow(new Object[]{
                rs.getInt("IdProducto"),
                rs.getString("NombreProducto"),
                rs.getString("Descripcion"),
                currencyFormat.format(precioUnitario), // Formato de precio unitario
                rs.getInt("Cantidad"),
                currencyFormat.format(precio), // Formato de precio total
                rs.getString("NombreMedioPago"),
                ""  // Deja el total vacío aquí
            });
            totalFactura += precio;  // Acumula el total
        }
        rs.close();
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(dialogoDetalles, "Error al obtener los detalles de la venta", "Error", JOptionPane.ERROR_MESSAGE);
    } finally {
        try {
            if (pst != null) pst.close();
            if (conexion != null) conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Agregar la fila final con el total
    String totalFacturaFormateado = currencyFormat.format(totalFactura);
    modeloDetalles.addRow(new Object[]{
        "", "", "", "", "", "", "Total", totalFacturaFormateado  // Muestra el total en la última columna
    });

    // Crear la tabla y agregarla al diálogo
    JTable tablaDetalles = new JTable(modeloDetalles);
    tablaDetalles.setFillsViewportHeight(true);  // Para llenar el área visible
    tablaDetalles.setRowHeight(25);  // Mayor altura para mejor visibilidad
    // Cambiar el color de fondo de las celdas de los encabezados
    tablaDetalles.getTableHeader().setBackground(Color.gray); // Cambia 'Color.CYAN' por el color que desees
    tablaDetalles.getTableHeader().setForeground(Color.WHITE); // Opcional: Cambia el color del texto del encabezado

    JScrollPane panelDesplazamiento = new JScrollPane(tablaDetalles);
    dialogoDetalles.add(panelDesplazamiento, BorderLayout.CENTER);

    // Panel para los botones
    JPanel panelBotonesDetalles = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JButton botonCerrar = new JButton("Cerrar");
    panelBotonesDetalles.add(botonCerrar);

    dialogoDetalles.add(panelBotonesDetalles, BorderLayout.SOUTH);

    botonCerrar.addActionListener(event -> dialogoDetalles.dispose());

    dialogoDetalles.setVisible(true);
}
}


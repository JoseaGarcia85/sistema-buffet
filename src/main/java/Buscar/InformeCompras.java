package Buscar;


import Conexion.conexionBD;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.Date;
import javax.swing.table.DefaultTableCellRenderer;

public class InformeCompras extends JInternalFrame {  // Cambiado de JFrame a JInternalFrame

    private JTable tablaCompras;
    private DefaultTableModel tableModel;
    private JDateChooser txtFechaInicio;
    private JDateChooser txtFechaFin;
    private JComboBox<String> comboBoxProveedores;
    private JButton btnFiltrar;
    private JButton btnLimpiar;

    public InformeCompras() {
        initComponents();
        cargarProveedores();
        cargarDatosCompras(); // Carga inicial sin filtros
    }

    private void initComponents() {
        setTitle("Informe de Compras");
        setClosable(true); // Hace la ventana cerrable
        setIconifiable(true); // Hace la ventana minimizable
        setResizable(true); // Permite redimensionar la ventana
        setMaximizable(true); // Permite maximizar la ventana
        setSize(1000, 700);
    // Si el JInternalFrame está dentro de un JDesktopPane, calcula la posición centrada.
        if (getParent() instanceof JDesktopPane) {
            JDesktopPane desktopPane = (JDesktopPane) getParent();
            int x = (desktopPane.getWidth() - getWidth()) / 2;
            int y = (desktopPane.getHeight() - getHeight()) / 2;
            setLocation(x, y); // Coloca el JInternalFrame en el centro del JDesktopPane
        }
        String[] columnNames = {"ID Compra", "Nombre Proveedor", "Fecha", "Monto"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaCompras = new JTable(tableModel);

        // Centrar los datos en la tabla
        tablaCompras.setDefaultRenderer(Object.class, new CenteredTableCellRenderer());

        // Aplicar el renderer al encabezado de la tabla
        tablaCompras.getTableHeader().setDefaultRenderer(new HeaderRenderer());

        // Listener para mostrar detalles al seleccionar una fila
        tablaCompras.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = tablaCompras.getSelectedRow();
                if (selectedRow != -1) {
                    int idCompra = (int) tableModel.getValueAt(selectedRow, 0);
                    mostrarDetallesCompra(idCompra);
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(tablaCompras);

        // Panel para filtros
        JPanel panelFiltros = new JPanel(new FlowLayout());
        panelFiltros.add(new JLabel("Fecha Inicio:"));
        txtFechaInicio = new JDateChooser();
        txtFechaInicio.setPreferredSize(new Dimension(150, 25));
        JTextField dateFieldInicio = (JTextField) txtFechaInicio.getDateEditor().getUiComponent();
        dateFieldInicio.setHorizontalAlignment(SwingConstants.CENTER);
        panelFiltros.add(txtFechaInicio);

        panelFiltros.add(new JLabel("Fecha Fin:"));
        txtFechaFin = new JDateChooser();
        txtFechaFin.setPreferredSize(new Dimension(150, 25));
        JTextField dateFieldFin = (JTextField) txtFechaFin.getDateEditor().getUiComponent();
        dateFieldFin.setHorizontalAlignment(SwingConstants.CENTER);
        panelFiltros.add(txtFechaFin);

        panelFiltros.add(new JLabel("Proveedor:"));
        comboBoxProveedores = new JComboBox<>();
        panelFiltros.add(comboBoxProveedores);

        btnFiltrar = new JButton("Filtrar");
        btnFiltrar.addActionListener(e -> filtrarCompras());
        panelFiltros.add(btnFiltrar);

        btnLimpiar = new JButton("Limpiar Búsqueda");
        btnLimpiar.addActionListener(e -> limpiarBusqueda());
        panelFiltros.add(btnLimpiar);

        add(panelFiltros, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    // Renderer para la cabecera de la tabla
    class HeaderRenderer extends DefaultTableCellRenderer {
        public HeaderRenderer() {
            setBackground(Color.LIGHT_GRAY); // Establece el color de fondo
            setForeground(Color.BLUE); // Establece el color del texto
            setHorizontalAlignment(SwingConstants.CENTER); // Centra el texto
        }
    }

    // Renderer para centrar los datos en las celdas
    class CenteredTableCellRenderer extends DefaultTableCellRenderer {
        public CenteredTableCellRenderer() {
            setHorizontalAlignment(SwingConstants.CENTER);
        }
    }

    private void cargarProveedores() {
        try (Connection con = conexionBD.getConnection();
             PreparedStatement stmt = con.prepareStatement("SELECT NombreProveedor FROM proveedor ORDER BY NombreProveedor")) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                comboBoxProveedores.addItem(rs.getString("NombreProveedor"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar los proveedores.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarDatosCompras() {
    tableModel.setRowCount(0);
    try (Connection con = conexionBD.getConnection();
         PreparedStatement stmt = con.prepareStatement("SELECT c.IdCompra, p.NombreProveedor, c.Fecha, c.Monto " +
                               "FROM compra c " +
                               "JOIN proveedor p ON c.IdProveedor = p.IdProveedor " +
                               "ORDER BY c.Fecha DESC")) {
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            int idCompra = rs.getInt("IdCompra");
            String nombreProveedor = rs.getString("NombreProveedor"); 
            Date fecha = rs.getDate("Fecha");
            double monto = rs.getDouble("Monto");
            tableModel.addRow(new Object[]{idCompra, nombreProveedor, fecha, monto});
        }
        
        // Ocultar columnas ID Compra e ID Proveedor
        ocultarColumnas();
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al cargar los datos de las compras.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

// Método para ocultar las columnas de ID
private void ocultarColumnas() {
    // Ocultar la columna ID Compra (índice 0)
    tablaCompras.getColumnModel().getColumn(0).setMinWidth(0);
    tablaCompras.getColumnModel().getColumn(0).setMaxWidth(0);
    tablaCompras.getColumnModel().getColumn(0).setPreferredWidth(0);
}


    private void filtrarCompras() {
        Date fechaInicio = txtFechaInicio.getDate();
        Date fechaFin = txtFechaFin.getDate();
        String proveedorSeleccionado = (String) comboBoxProveedores.getSelectedItem();

        if (fechaInicio == null || fechaFin == null) {
            JOptionPane.showMessageDialog(this, "Por favor, introduzca fechas válidas.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (fechaInicio.after(fechaFin)) {
            JOptionPane.showMessageDialog(this, "La fecha de inicio no puede ser posterior a la fecha de fin.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        StringBuilder sql = new StringBuilder("SELECT IdCompra, IdProveedor, Fecha, Monto FROM compra WHERE 1=1");
        if (fechaInicio != null) {
            sql.append(" AND Fecha >= ?");
        }
        if (fechaFin != null) {
            sql.append(" AND Fecha <= ?");
        }
        if (proveedorSeleccionado != null && !proveedorSeleccionado.isEmpty()) {
            sql.append(" AND IdProveedor = (SELECT IdProveedor FROM proveedor WHERE NombreProveedor = ?)");
        }
        sql.append(" ORDER BY Fecha DESC");

        try (Connection con = conexionBD.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql.toString())) {
            int index = 1;
            if (fechaInicio != null) {
                stmt.setDate(index++, new java.sql.Date(fechaInicio.getTime()));
            }
            if (fechaFin != null) {
                stmt.setDate(index++, new java.sql.Date(fechaFin.getTime()));
            }
            if (proveedorSeleccionado != null && !proveedorSeleccionado.isEmpty()) {
                stmt.setString(index, proveedorSeleccionado);
            }

            ResultSet rs = stmt.executeQuery();
            tableModel.setRowCount(0);
            if (!rs.isBeforeFirst()) {
                JOptionPane.showMessageDialog(this, "No hay compras realizadas en la fecha seleccionada.", "Información", JOptionPane.INFORMATION_MESSAGE);
            } else {
                while (rs.next()) {
                    int idCompra = rs.getInt("IdCompra");
                    int idProveedor = rs.getInt("IdProveedor");
                    Date fecha = rs.getDate("Fecha");
                    double monto = rs.getDouble("Monto");
                    tableModel.addRow(new Object[]{idCompra, idProveedor, fecha, monto});
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al filtrar las compras.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarBusqueda() {
        txtFechaInicio.setDate(null);
        txtFechaFin.setDate(null);
        comboBoxProveedores.setSelectedIndex(-1);
        cargarDatosCompras();
    }

private void mostrarDetallesCompra(int idCompra) {
    DefaultTableModel modeloDetalles = new DefaultTableModel();
    modeloDetalles.addColumn("Producto");
    modeloDetalles.addColumn("Cantidad");
    modeloDetalles.addColumn("Precio Unitario");
    modeloDetalles.addColumn("Monto");

    double totalMonto = 0; // Variable para acumular el total de los montos

    try (Connection con = conexionBD.getConnection();
         PreparedStatement stmt = con.prepareStatement(
                 "SELECT p.NombreProducto, cpd.Cantidad, s.Precio, cpd.Monto " +
                 "FROM compraproductodetalle cpd " +
                 "JOIN compra c ON c.IdCompra = cpd.IdCompra " +
                 "JOIN producto p ON cpd.IdProducto = p.IdProducto " +
                 "JOIN stock s ON p.IdProducto = s.IdProducto " +
                 "WHERE c.IdCompra = ?")) {

        stmt.setInt(1, idCompra);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            String nombreProducto = rs.getString("NombreProducto");
            int cantidad = rs.getInt("Cantidad");
            double precioUnitario = rs.getDouble("Precio");
            double monto = precioUnitario * cantidad;
            totalMonto += monto; // Acumular el monto en el total
            modeloDetalles.addRow(new Object[]{nombreProducto, cantidad, precioUnitario, monto});
        }

        JTable tablaDetalles = new JTable(modeloDetalles);
        JScrollPane scrollPaneDetalles = new JScrollPane(tablaDetalles);

        // Crear un JLabel para mostrar el total con una fuente más grande
        JLabel labelTotal = new JLabel("Total Monto: " + totalMonto);
        labelTotal.setFont(new Font("Arial", Font.BOLD, 18)); // Ajustar el tamaño de la fuente

        // Crear un panel para agregar la tabla y el total
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPaneDetalles, BorderLayout.CENTER);
        panel.add(labelTotal, BorderLayout.SOUTH);

        // Mostrar en un JDialog
        JDialog dialogoDetalles = new JDialog();
        dialogoDetalles.setTitle("Detalles de la Compra");
        dialogoDetalles.setSize(600, 400);
        dialogoDetalles.add(panel);
        dialogoDetalles.setModal(true);
        dialogoDetalles.setLocationRelativeTo(null);
        dialogoDetalles.setVisible(true);
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al cargar los detalles de la compra.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
}


package Buscar;

import Conexion.conexionBD;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
/**
 *
 * @author Nadia
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Estadistica extends JInternalFrame {
    private JTable table;
    private DefaultTableModel tableModel;

    public Estadistica() {
         // Configura el frame interno aquí
        setTitle("Estadísticas");
        setSize(300, 200);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setVisible(true);

        // Crear los JCheckBox
        JCheckBox productoCheckBox = new JCheckBox("Producto");
        JCheckBox categoriaCheckBox = new JCheckBox("Categoría");
        JCheckBox proveedorCheckBox = new JCheckBox("Proveedor");
        
        // Crear un ButtonGroup para asegurar selección única
        ButtonGroup group = new ButtonGroup();
        group.add(productoCheckBox);
        group.add(categoriaCheckBox);
        group.add(proveedorCheckBox);
        
        // Crear un panel con BoxLayout y borde
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder("Opciones"));

        // Agregar los JCheckBox al frame
        panel.add(productoCheckBox);
        panel.add(categoriaCheckBox);
        panel.add(proveedorCheckBox);
        add(panel);

        // Agregar ActionListener a cada JCheckBox
        productoCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (productoCheckBox.isSelected()) {
                    abrirVentana("Producto");
                }
            }
        });

        categoriaCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (categoriaCheckBox.isSelected()) {
                    abrirVentana("Categoria");
                }
            }
        });

        proveedorCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (proveedorCheckBox.isSelected()) {
                    abrirVentana("Proveedor");
                }
            }
        });
        
    }
    
     // Método para abrir una nueva ventana
    private void abrirVentana(String tipo) {
        
        if(tipo == "Producto"){
            Estadistica.ProductoVentana productoVentana = new Estadistica.ProductoVentana();
            productoVentana.setVisible(true);  
        }
        if(tipo == "Categoria"){
            Estadistica.CategoriaVentana categoriaVentana = new Estadistica.CategoriaVentana();
            categoriaVentana.setVisible(true);  
        }
         if(tipo == "Proveedor"){
            Estadistica.ProveedorVentana proveedorVentana = new Estadistica.ProveedorVentana();
            proveedorVentana.setVisible(true);  
        }
    }

    public class ProductoVentana extends JFrame {

    public ProductoVentana() {
        setTitle("Estadística de Producto");
        setSize(350, 300);
        setLocation(100, 100);
        // Configura el JTable y el modelo de la tabla
        tableModel = new DefaultTableModel(new Object[]{"Periodo", "IdProducto", "NombreProducto", "TotalVendido"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Cargar datos de la base de datos
        try {
            // Conexión a la base de datos
            Connection conn = conexionBD.getConnection();

            // Consultas para obtener datos diarios, semanales y mensuales
            String queryDay = "SELECT 'Hoy' AS Periodo, vd.IdProducto, p.NombreProducto, SUM(vd.Cantidad) AS TotalVendido " +
                              "FROM ventaproductodetalle vd " +
                              "JOIN venta v ON vd.IdVenta = v.IdVenta " +
                              "JOIN producto p ON p.IdProducto = vd.IdProducto " +
                              "WHERE DATE(v.Fecha) = CURDATE() " +
                              "GROUP BY vd.IdProducto, p.NombreProducto " +
                              "ORDER BY TotalVendido DESC " +
                              "LIMIT 1;";

            String queryWeek = "SELECT 'Esta Semana' AS Periodo, vd.IdProducto, p.NombreProducto, SUM(vd.Cantidad) AS TotalVendido " +
                               "FROM ventaproductodetalle vd " +
                               "JOIN venta v ON vd.IdVenta = v.IdVenta " +
                               "JOIN producto p ON p.IdProducto = vd.IdProducto " +
                               "WHERE YEARWEEK(v.Fecha, 1) = YEARWEEK(CURDATE(), 1) " +
                               "GROUP BY vd.IdProducto, p.NombreProducto " +
                               "ORDER BY TotalVendido DESC " +
                               "LIMIT 1;";

            String queryMonth = "SELECT 'Este Mes' AS Periodo, vd.IdProducto, p.NombreProducto, SUM(vd.Cantidad) AS TotalVendido " +
                                "FROM ventaproductodetalle vd " +
                                "JOIN venta v ON vd.IdVenta = v.IdVenta " +
                                "JOIN producto p ON p.IdProducto = vd.IdProducto " +
                                "WHERE MONTH(v.Fecha) = MONTH(CURDATE()) AND YEAR(v.Fecha) = YEAR(CURDATE()) " +
                                "GROUP BY vd.IdProducto, p.NombreProducto " +
                                "ORDER BY TotalVendido DESC " +
                                "LIMIT 1;";

            // Ejecutar y procesar las consultas
            procesarConsulta(conn, queryDay);
            procesarConsulta(conn, queryWeek);
            procesarConsulta(conn, queryMonth);

            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void procesarConsulta(Connection conn, String query) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        // Llenar el modelo de la tabla con los datos obtenidos
        while (rs.next()) {
            String periodo = rs.getString("Periodo");
            String idProducto = rs.getString("IdProducto");
            String nombreProducto = rs.getString("NombreProducto");
            int totalVendido = rs.getInt("TotalVendido");
            tableModel.addRow(new Object[]{periodo, idProducto, nombreProducto, totalVendido});
        }

        rs.close();
        pstmt.close();
    }
    }
    
    public class CategoriaVentana extends JFrame {

    public CategoriaVentana() {
        setTitle("Estadística de Categoría");
        setSize(400, 300);
        setLocation(100, 100);
        // Configura el JTable y el modelo de la tabla
        tableModel = new DefaultTableModel(new Object[]{"Periodo", "IdCategoria", "NombreCategria", "TotalVendido"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Cargar datos de la base de datos
        try {
            // Conexión a la base de datos
            Connection conn = conexionBD.getConnection();

            // Consultas para obtener datos diarios, semanales y mensuales
            String queryDay ="SELECT 'Hoy' AS Periodo,c.IdCategoria, c.NombreCategoria, SUM(vd.Cantidad) AS TotalVendido " +
                       "FROM ventaproductodetalle vd " +
                       "JOIN venta v ON vd.IdVenta = v.IdVenta " +
                       "JOIN producto p ON p.IdProducto = vd.IdProducto " +
                       "JOIN categoria c ON p.IdCategoria = p.IdCategoria " +
                       "WHERE DATE(v.Fecha) = CURDATE() " +
                       "GROUP BY c.NombreCategoria " +
                       "ORDER BY TotalVendido DESC " +
                       "LIMIT 5;";
            
             String queryWeek ="SELECT 'Esta semana' AS Periodo,c.IdCategoria, c.NombreCategoria, SUM(vd.Cantidad) AS TotalVendido " +
                       "FROM ventaproductodetalle vd " +
                       "JOIN venta v ON vd.IdVenta = v.IdVenta " +
                       "JOIN producto p ON p.IdProducto = vd.IdProducto " +
                       "JOIN categoria c ON p.IdCategoria = c.IdCategoria " +
                     "WHERE YEARWEEK(v.Fecha, 1) = YEARWEEK(CURDATE(), 1) " +
                               "GROUP BY vd.IdProducto, p.NombreProducto " +
                               "ORDER BY TotalVendido DESC " +
                               "LIMIT 5;";
             
             String queryMonth ="SELECT 'Este mes' AS Periodo,c.IdCategoria, c.NombreCategoria, SUM(vd.Cantidad) AS TotalVendido " +
                       "FROM ventaproductodetalle vd " +
                       "JOIN venta v ON vd.IdVenta = v.IdVenta " +
                       "JOIN producto p ON p.IdProducto = vd.IdProducto " +
                       "JOIN categoria c ON p.IdCategoria = c.IdCategoria " +
                      "WHERE MONTH(v.Fecha) = MONTH(CURDATE()) AND YEAR(v.Fecha) = YEAR(CURDATE()) " +
                                "GROUP BY vd.IdProducto, p.NombreProducto " +
                                "ORDER BY TotalVendido DESC " +
                                "LIMIT 5;";

            // Ejecutar y procesar las consultas
            procesarConsulta(conn, queryDay);
            procesarConsulta(conn, queryWeek);
            procesarConsulta(conn, queryMonth);

            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void procesarConsulta(Connection conn, String query) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        // Llenar el modelo de la tabla con los datos obtenidos
        while (rs.next()) {
            String periodo = rs.getString("Periodo");
            String idCategoria = rs.getString("IdCategoria");
            String nombreCategoria= rs.getString("NombreCategoria");
            int totalVendido = rs.getInt("TotalVendido");
            tableModel.addRow(new Object[]{periodo, idCategoria, nombreCategoria, totalVendido});
        }

        rs.close();
        pstmt.close();
    }
    }
    
    public class ProveedorVentana extends JFrame {

    public ProveedorVentana() {
        setTitle("Estadística de Proveedor");
        setSize(400, 300);
        setLocation(100, 100);
        // Configura el JTable y el modelo de la tabla
        tableModel = new DefaultTableModel(new Object[]{"Periodo", "IdProveedor", "NombreProveedor", "TotalVendido"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Cargar datos de la base de datos
        try {
            // Conexión a la base de datos
            Connection conn = conexionBD.getConnection();

            // Consultas para obtener datos diarios, semanales y mensuales
            String queryDay ="SELECT 'Hoy' AS Periodo,pro.IdProveedor, pro.NombreProveedor, SUM(vd.Cantidad) AS TotalVendido " +
                       "FROM ventaproductodetalle vd " +
                       "JOIN venta v ON vd.IdVenta = v.IdVenta " +
                       "JOIN producto p ON p.IdProducto = vd.IdProducto " +
                       "JOIN proveedor pro ON p.IdProveedor = pro.IdProveedor " +
                       "WHERE DATE(v.Fecha) = CURDATE() " +
                       "GROUP BY pro.NombreProveedor " +
                       "ORDER BY TotalVendido DESC " +
                       "LIMIT 1;";
            
             String queryWeek ="SELECT 'Esta semana' AS Periodo,pro.IdProveedor, pro.NombreProveedor, SUM(vd.Cantidad) AS TotalVendido " +
                       "FROM ventaproductodetalle vd " +
                       "JOIN venta v ON vd.IdVenta = v.IdVenta " +
                       "JOIN producto p ON p.IdProducto = vd.IdProducto " +
                       "JOIN proveedor pro ON p.IdProveedor = pro.IdProveedor " +
                        "WHERE YEARWEEK(v.Fecha, 1) = YEARWEEK(CURDATE(), 1) " +
                       "GROUP BY pro.NombreProveedor " +
                               "ORDER BY TotalVendido DESC " +
                               "LIMIT 1;";
             
             String queryMonth ="SELECT 'Este mes' AS Periodo,pro.IdProveedor, pro.NombreProveedor, SUM(vd.Cantidad) AS TotalVendido " +
                       "FROM ventaproductodetalle vd " +
                       "JOIN venta v ON vd.IdVenta = v.IdVenta " +
                       "JOIN producto p ON p.IdProducto = vd.IdProducto " +
                       "JOIN proveedor pro ON p.IdProveedor = pro.IdProveedor " +
                        "WHERE MONTH(v.Fecha) = MONTH(CURDATE()) AND YEAR(v.Fecha) = YEAR(CURDATE()) " +
                       "GROUP BY pro.NombreProveedor " +
                                "ORDER BY TotalVendido DESC " +
                                "LIMIT 1;";

            // Ejecutar y procesar las consultas
            procesarConsulta(conn, queryDay);
            procesarConsulta(conn, queryWeek);
            procesarConsulta(conn, queryMonth);

            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void procesarConsulta(Connection conn, String query) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        // Llenar el modelo de la tabla con los datos obtenidos
        while (rs.next()) {
            String periodo = rs.getString("Periodo");
            String idProveedor= rs.getString("IdProveedor");
            String nombreProveedor= rs.getString("NombreProveedor");
            int totalVendido = rs.getInt("TotalVendido");
            tableModel.addRow(new Object[]{periodo, idProveedor, nombreProveedor, totalVendido});
        }

        rs.close();
        pstmt.close();
    }
    }
}
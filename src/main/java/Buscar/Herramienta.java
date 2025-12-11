package Buscar;




/// SOLO FUE UNA PRUEBA PARA SDACARLE EL SUBMENU PERO NO HACE NADA ESTE CODIGO

//// HAY QUE HACER TODO EL CODIGO DE LA CLASE PRA LA FUNCIONES DE HERRAMIENTA





import Conexion.conexionBD;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.*;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;




public class Herramienta extends JInternalFrame  {
    private DefaultTableModel model;
    private JTable jTabla1;


    public Herramienta() {
        initComponents();
        pack();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        botonBuscarInactivo = new javax.swing.JButton();
         botonBuscarActivo = new javax.swing.JButton();
        idLabel = new javax.swing.JLabel();
        Id = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        botonQuitar = new javax.swing.JButton();
        botonModificar = new javax.swing.JButton();
        botonSalir = new javax.swing.JButton();
        botonListarActivos = new javax.swing.JButton();
        botonListarInactivos = new javax.swing.JButton();
        botonAgregar = new javax.swing.JButton();
        botonLimpiarTabla = new javax.swing.JButton();
        botonEliminarProveedor = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximizable(true);
        setTitle("Configuraciones de Proveedores");
        setFont(new java.awt.Font("Lucida Sans Unicode", 0, 17)); // NOI18N

        botonBuscarActivo.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 17)); // NOI18N
        botonBuscarActivo.setText("Buscar Activo");
        botonBuscarActivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarActivoActionPerformed(evt);
            }
        });
        
        botonBuscarInactivo.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 17)); // NOI18N
        botonBuscarInactivo.setText("Buscar Inactivo");
        botonBuscarInactivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarInactivoActionPerformed(evt);
            }
        });

        idLabel.setFont(new java.awt.Font("Lucida Sans Unicode", 2, 18)); // NOI18N
        idLabel.setForeground(new java.awt.Color(70, 80, 72));
        idLabel.setText("Id(codigo proveedor)");

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
               "IdProveedor", "NombreProvedor", "Direccion", "Telefono"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class,java.lang.String.class
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
            tabla.getColumnModel().getColumn(0).setHeaderValue("Id Proveedor");
            tabla.getColumnModel().getColumn(1).setHeaderValue("Nombre Proveedor");
            tabla.getColumnModel().getColumn(2).setHeaderValue("Direccion");
            tabla.getColumnModel().getColumn(3).setHeaderValue("Telefono");
        }

        botonQuitar.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 17)); // NOI18N
        botonQuitar.setText("Quitar ");
        if (tabla.getRowCount() == 0) {
            botonQuitar.setEnabled(false);
        }

        botonModificar.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 17)); // NOI18N
        botonModificar.setText("Modificar");
        if (tabla.getRowCount() == 0) {
            botonModificar.setEnabled(false);
        }

        botonSalir.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 18)); // NOI18N
        botonSalir.setText("Salir");
        botonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });

        botonListarActivos.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 17)); // NOI18N
        botonListarActivos.setText("Listar Activos");
        botonListarActivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listarProveedoresActivosActionPerformed(evt);
            }
        });
        
        botonListarInactivos.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 17)); // NOI18N
        botonListarInactivos.setText("Listar Inactivos");
        botonListarInactivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listarProveedoresInactivosActionPerformed(evt);
            }
        });

        botonAgregar.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 17)); // NOI18N
        botonAgregar.setText("Agregar");
        botonAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarProveedorActionPerformed(evt);
            }
        });

        botonLimpiarTabla.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 17)); // NOI18N
        botonLimpiarTabla.setText("Limpiar Tabla");
        if (tabla.getRowCount() == 0) {
            botonLimpiarTabla.setEnabled(false);
        }
        
        botonEliminarProveedor.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 17)); // NOI18N
        botonEliminarProveedor.setText("Eliminar Proveedor");
        if (tabla.getRowCount() == 0) {
            botonEliminarProveedor.setEnabled(false);
        }
        
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
                        .addComponent(botonBuscarActivo))
                         .addComponent(botonBuscarInactivo)
                    
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 956, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonListarActivos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                     .addComponent(botonListarInactivos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                           
                        .addComponent(botonQuitar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botonModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botonAgregar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(botonEliminarProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botonSalir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                            .addGap(10)
                            .addComponent(botonBuscarActivo))
                            .addComponent(botonBuscarInactivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    
                        .addGap(42, 42, 42))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(botonLimpiarTabla)
                            .addComponent(botonListarActivos))
                            .addComponent(botonListarInactivos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                            
                        .addComponent(botonQuitar)
                        .addGap(12, 12, 12)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(botonModificar)
                        .addGap(36, 36, 36)
                        .addComponent(botonAgregar)
                            .addGap(36, 36, 36)
                             .addComponent(botonEliminarProveedor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botonSalir))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>                        

    private void buscarActivoActionPerformed(java.awt.event.ActionEvent evt) {                                         
    // Obtener el valor del campo de texto para el id proveedor
    String id = Id.getText();
    
    // Consulta SQL para buscar el proveedor por su id
    String consulta = "SELECT IdProveedor, NombreProveedor, Direccion,Telefono FROM proveedor WHERE IdProveedor = ? && Estado = 1";

    Connection conexion = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    try {
        // Usar la clase conexionBD para ejecutar la consulta
        rs = conexionBD.ejecutarConsulta(consulta, id);
        
        // Procesar el resultado de la consulta
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        model.setRowCount(0);
        boolean proveedorEncontrado = false;

        while (rs.next()) {
            proveedorEncontrado = true;

            int idProveedor = rs.getInt("IdProveedor");
            String nombreProveedor = rs.getString("NombreProveedor");
            String direccion = rs.getString("Direccion");
            String telefono = rs.getString("Telefono");

            // Verificar si el proveedor ya está en la tabla
            boolean proveedorEnTabla = false;
            for (int i = 0; i < model.getRowCount(); i++) {
                Object valorCelda = model.getValueAt(i, 1);
                if (valorCelda != null && valorCelda instanceof Integer && (int) valorCelda == idProveedor) {
                    proveedorEnTabla = true;
                    break;
                }
            }

            if (proveedorEnTabla) {
                JOptionPane.showMessageDialog(null, "El proveedor ya está en la tabla.", "Proveedor duplicado", JOptionPane.WARNING_MESSAGE);
            } else {
                Object[] fila = { idProveedor, nombreProveedor, direccion, telefono };
                model.addRow(fila); // Agregar el proveedor encontrado a la tabla
                botonQuitar.setEnabled(true);//Agrego esta línea para volver a habilitar el botón de quitar
                botonQuitar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    quitarProveedorTablaActionPerformed(evt);
                }
            });
                botonModificar.setEnabled(true);//Agrego esta línea para volver a habilitar el botón de quitar
                botonModificar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    modificarProveedorActionPerformed(evt);
                }
            });
                botonLimpiarTabla.setEnabled(true);//Agrego esta línea para volver a habilitar el botón de quitar
                botonLimpiarTabla.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    limpiarTablaActionPerformed(evt);
                }
            });
                botonEliminarProveedor.setEnabled(true);//Agrego esta línea para volver a habilitar el botón de quitar
        botonEliminarProveedor.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    eliminarProveedorActionPerformed(evt);
                }
            });
                
            }
        }

        if (!proveedorEncontrado) {
            JOptionPane.showMessageDialog(null, "No se encontró proveedor con el id ingresado.", "Proveedor no encontrado", JOptionPane.WARNING_MESSAGE);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al buscar el proveedor.", "Error", JOptionPane.ERROR_MESSAGE);
    } finally {
        // Cerrar los recursos
        conexionBD.cerrarRecursos(rs, pst, conexion);
    }
}
                     
     private void buscarInactivoActionPerformed(java.awt.event.ActionEvent evt) {                                         
    // Obtener el valor del campo de texto para el id proveedor
    String id = Id.getText();
    
    // Consulta SQL para buscar el proveedor por su id
    String consulta = "SELECT IdProveedor, NombreProveedor, Direccion, Telefono FROM proveedor WHERE IdProveedor = ? && Estado = 0";

    Connection conexion = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    try {
        // Usar la clase conexionBD para ejecutar la consulta
        rs = conexionBD.ejecutarConsulta(consulta, id);
        
        // Procesar el resultado de la consulta
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        model.setRowCount(0);
        boolean proveedorEncontrado = false;

        while (rs.next()) {
            proveedorEncontrado = true;

            int idProveedor = rs.getInt("IdProveedor");
            String nombreProveedor = rs.getString("NombreProveedor");
            String direccion = rs.getString("Direccion");
            String telefono = rs.getString("Telefono");

            // Verificar si el proveedor ya está en la tabla
            boolean proveedorEnTabla = false;
            for (int i = 0; i < model.getRowCount(); i++) {
                Object valorCelda = model.getValueAt(i, 1);
                if (valorCelda != null && valorCelda instanceof Integer && (int) valorCelda == idProveedor) {
                    proveedorEnTabla = true;
                    break;
                }
            }

            if (proveedorEnTabla) {
                JOptionPane.showMessageDialog(null, "El proveedor ya está en la tabla.", "Proveedor duplicado", JOptionPane.WARNING_MESSAGE);
            } else {
                Object[] fila = { idProveedor, nombreProveedor, direccion, telefono };
                model.addRow(fila); // Agregar el proveedor encontrado a la tabla
                botonQuitar.setEnabled(true);//Agrego esta línea para volver a habilitar el botón de quitar
                botonQuitar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    quitarProveedorTablaActionPerformed(evt);
                }
            });
                botonModificar.setEnabled(true);//Agrego esta línea para volver a habilitar el botón de quitar
                botonModificar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    modificarProveedorActionPerformed(evt);
                }
            });
                botonLimpiarTabla.setEnabled(true);//Agrego esta línea para volver a habilitar el botón de quitar
                botonLimpiarTabla.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    limpiarTablaActionPerformed(evt);
                }
            });
                botonEliminarProveedor.setEnabled(true);//Agrego esta línea para volver a habilitar el botón de quitar
        botonEliminarProveedor.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    eliminarProveedorActionPerformed(evt);
                }
            });
                
            }
        }

        if (!proveedorEncontrado) {
            JOptionPane.showMessageDialog(null, "No se encontraron proveedor con el id ingresado.", "Proveedor no encontrado", JOptionPane.WARNING_MESSAGE);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al buscar el proveedor.", "Error", JOptionPane.ERROR_MESSAGE);
    } finally {
        // Cerrar los recursos
        conexionBD.cerrarRecursos(rs, pst, conexion);
    }
}

    private void CodigoActionPerformed(java.awt.event.ActionEvent evt) {                                       
        // TODO add your handling code here:
    }                                      

    private void quitarProveedorTablaActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        ///eleimina de la lista
          int selectedRow = tabla.getSelectedRow();
        if (selectedRow >= 0) {
            DefaultTableModel model = (DefaultTableModel) tabla.getModel();
            model.removeRow(selectedRow); // Eliminar la fila de la tabla
            JOptionPane.showMessageDialog(null, "Proveedor eliminado de la lista.", "Eliminación exitosa", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un proveedor de la tabla para eliminar.", "Proveedor no seleccionado", JOptionPane.WARNING_MESSAGE);
        }
    }                                        

    private void modificarProveedorActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        //modificar
        int selectedRow = tabla.getSelectedRow();
    if (selectedRow >= 0) {
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();

        // Obtener los datos del proveedor seleccionado
        int idProveedor = (int) model.getValueAt(selectedRow, 0);
        String nombreProveedor = (String) model.getValueAt(selectedRow, 1);
        String direccion = (String) model.getValueAt(selectedRow, 2);
        String telefono = (String) model.getValueAt(selectedRow, 3);

        // Abrir el diálogo de modificación
        ModificarProveedorDialog modificarDialog = new ModificarProveedorDialog((JFrame) SwingUtilities.getWindowAncestor(this), nombreProveedor, direccion, telefono);
        modificarDialog.setVisible(true);

        if (modificarDialog.isModified()) {
            // Obtener los datos modificados
            String nuevoNombreProveedor = modificarDialog.getNombreProveedor();
            String nuevaDireccion = modificarDialog.getDireccion();
            String nuevoTelefono = modificarDialog.getTelefono();

            // Actualizar la tabla
            model.setValueAt(nuevoNombreProveedor, selectedRow, 0);
            model.setValueAt(nuevaDireccion, selectedRow, 2);
            model.setValueAt(nuevoTelefono, selectedRow, 3);

            // Actualizar la base de datos
            try {
                Connection conexion = conexionBD.getConnection();                
                String consulta = "UPDATE proveedor SET NombreProveedor = ?, Direccion = ?,Telefono  = ? WHERE IdProveedor = ?";
                PreparedStatement pst = conexion.prepareStatement(consulta);
                pst.setString(1, nuevoNombreProveedor);
                pst.setString(2, nuevaDireccion);
                pst.setString(3, nuevoTelefono);
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
    private boolean isModified = false;

    public ModificarProveedorDialog(Frame parent, String nombreProveedor, String direccion, String telefono) {
        ///modificar proveedor
        super(parent, true);
        setTitle("Modificar Proveeddor");
        setLayout(new GridLayout(4, 2, 10, 10));
        setSize(300, 200);

        add(new JLabel("Nombre Proveedor:"));
        txtNombreProveedor = new JTextField(nombreProveedor);
        add(txtNombreProveedor);

        add(new JLabel("Dirección:"));
        txtDireccion = new JTextField(String.valueOf(direccion));
        add(txtDireccion);

        add(new JLabel("Telefon:"));
        txtTelefono = new JTextField(String.valueOf(telefono));
        add(txtTelefono);

        btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isModified = true;
                dispose();
            }
        });
        add(btnGuardar);

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
    }
     
    
    
    private void salirActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // Cerrar la ventana
        dispose();
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
        
        
        botonQuitar.setEnabled(true);//Agrego esta línea para volver a habilitar el botón de quitar
        botonQuitar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    quitarProveedorTablaActionPerformed(evt);
                }
            });
        botonModificar.setEnabled(true);//Agrego esta línea para volver a habilitar el botón de quitar
        botonModificar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    modificarProveedorActionPerformed(evt);
                }
            });
        botonLimpiarTabla.setEnabled(true);//Agrego esta línea para volver a habilitar el botón de quitar
        botonLimpiarTabla.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    limpiarTablaActionPerformed(evt);
                }
            });
        
         botonEliminarProveedor.setEnabled(true);//Agrego esta línea para volver a habilitar el botón de quitar
        botonEliminarProveedor.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    eliminarProveedorActionPerformed(evt);
                }
            });

        // Cerrar recursos
        rs.close();
        pst.close();
        conexion.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al listar proveedores: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    }                
    
    private void listarProveedoresInactivosActionPerformed(java.awt.event.ActionEvent evt) {                                         
        ///LISTA TODO LOS PROVEEDORES INACTIVOS
         try {
        // Establecer conexión a la base de datos
        Connection conexion = conexionBD.getConnection();
        // Consulta SQL para obtener todos los proveedores
        String consulta = "SELECT IdProveedor,NombreProveedor,Direccion,Telefono " +
                          "FROM proveedor    WHERE Estado = 0 ";
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
        
        
        botonQuitar.setEnabled(true);//Agrego esta línea para volver a habilitar el botón de quitar
        botonQuitar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    quitarProveedorTablaActionPerformed(evt);
                }
            });
        botonModificar.setEnabled(true);//Agrego esta línea para volver a habilitar el botón de quitar
        botonModificar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    modificarProveedorActionPerformed(evt);
                }
            });
        botonLimpiarTabla.setEnabled(true);//Agrego esta línea para volver a habilitar el botón de quitar
        botonLimpiarTabla.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    limpiarTablaActionPerformed(evt);
                }
            });
        
         botonEliminarProveedor.setEnabled(true);//Agrego esta línea para volver a habilitar el botón de quitar
        botonEliminarProveedor.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    eliminarProveedorActionPerformed(evt);
                }
            });

        // Cerrar recursos
        rs.close();
        pst.close();
        conexion.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al listar proveedores: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    }  
    
    private void agregarProveedorActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // Abrir el diálogo de modificación
        AgregarProveedorDialog modificarDialog = new AgregarProveedorDialog((JFrame) SwingUtilities.getWindowAncestor(this));
        modificarDialog.setVisible(true);
        
            // Obtener los datos modificados
            String nombreProveedor = modificarDialog.getNombreProveedor();
            String direccion = modificarDialog.getDireccion();
            String telefono = modificarDialog.getTelefono();

         
            // Actualizar la base de datos
            try {
                Connection conexion = conexionBD.getConnection();                
                String consulta = "INSERT INTO proveedor (NombreProveedor,Direccion,Telefono) VALUES (?,?,?)";

                PreparedStatement pst = conexion.prepareStatement(consulta);
                pst.setString(1, nombreProveedor);
                pst.setString(2, direccion);
                pst.setString(2, telefono);
                pst.executeUpdate();


                pst.close();
                conexion.close();
                
                JOptionPane.showMessageDialog(this, "Proveedor agregado con éxito");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error al agregar el proveedor: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        
    }

    public class AgregarProveedorDialog extends JDialog {
    private JTextField txtNombreProveedor;
    private JTextField txtDireccion;
    private JTextField txtTelefono;
    private JButton btnGuardar;

    public AgregarProveedorDialog(Frame parent) {
        ///modificar proveedor
        super(parent, true);
        setTitle("Nuevo Proveedor");
        setLayout(new GridLayout(4, 2, 10, 10));
        setSize(300, 200);

        add(new JLabel("Nombre Proveedor:"));
        txtNombreProveedor = new JTextField();
        add(txtNombreProveedor);

        add(new JLabel("Dirección:"));
        txtDireccion = new JTextField();
        add(txtDireccion);

        add(new JLabel("Teléfono:"));
        txtTelefono = new JTextField();
        add(txtTelefono);

        btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        add(btnGuardar);

        setLocationRelativeTo(parent);
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

    private void limpiarTablaActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        //LIMPIAR TABLA
    DefaultTableModel model = (DefaultTableModel) tabla.getModel();
    model.setRowCount(0);
    botonQuitar.setEnabled(false);
    botonModificar.setEnabled(false);
    botonLimpiarTabla.setEnabled(false);
    botonEliminarProveedor.setEnabled(false);
    }       
    
    private void eliminarProveedorActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        ///eleimina de la lista
          int selectedRow = tabla.getSelectedRow();
    if (selectedRow >= 0) {
 // Actualizar la base de datos
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


                pst.close();
                conexion.close();
                
                JOptionPane.showMessageDialog(this, "Proveedor eliminado con éxito");
                    } 
                
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error al eliminar el proveedor: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        
    } else {
        JOptionPane.showMessageDialog(null, "Seleccione un proveedor de la tabla para eliminar.", "Proveedor no seleccionado", JOptionPane.WARNING_MESSAGE);
    }
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
    private javax.swing.JButton botonBuscarInactivo;
     private javax.swing.JButton botonBuscarActivo;
    private javax.swing.JButton botonQuitar;
    private javax.swing.JButton botonModificar;
    private javax.swing.JButton botonSalir;
    private javax.swing.JButton botonListarActivos;
    private javax.swing.JButton botonListarInactivos;
    private javax.swing.JButton botonAgregar;
    private javax.swing.JButton botonLimpiarTabla;
     private javax.swing.JButton botonEliminarProveedor;
    private javax.swing.JLabel idLabel;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tabla;
    // End of variables declaration                   
}


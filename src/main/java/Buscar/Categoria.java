package Buscar;

import Inicio.form2;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import Conexion.conexionBD;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.UnsupportedLookAndFeelException;

public class Categoria extends JInternalFrame  {
    private DefaultTableModel model;
    private JTable jTabla1;


    public Categoria() {
        // Configuración del internal frame
        setTitle("Configuraciones de Categoria");
        setClosable(true);
        setMaximizable(true);
        setIconifiable(true);
        setResizable(true);
        setSize(800, 600);
        setLocation(100, 100);
        initComponents();
        pack();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    
    private void initComponents() {
        jButton1 = new JButton("Buscar");
        jButton2 = new JButton("Desactivar categoria");
        jButton3 = new JButton("Modificar");
        jButton5 = new JButton("Listar");
        jButton6 = new JButton("Agregar");
        jButton7 = new JButton("Limpiar Tabla");
        jLabel2 = new JLabel("ID Categoria");
        Codigo = new JTextField();
        jScrollPane2 = new JScrollPane();
        jTable1 = new JTable();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setFont(new java.awt.Font("Lucida Sans Unicode", 0, 17));

        jButton1.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 17));
        jButton1.setEnabled(false);
        jButton1.addActionListener(evt -> jButton1ActionPerformed(evt));

        Codigo.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                verificarTexto();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                verificarTexto();
            }
            @Override
            public void insertUpdate(DocumentEvent e) {
                verificarTexto();
            }

            private void verificarTexto() {
                jButton1.setEnabled(!Codigo.getText().trim().isEmpty());
            }
        });

        jLabel2.setFont(new java.awt.Font("Lucida Sans Unicode", 2, 18));
        jLabel2.setForeground(new java.awt.Color(78, 80, 72));

        Codigo.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 17));
        Codigo.addActionListener(evt -> CodigoActionPerformed(evt));

        jTable1.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 18));
        jTable1.setModel(new DefaultTableModel(
            new Object [][] {},
            new String [] {"Id Categoria", "Nombre Categoria"}
        ) {
            Class[] types = new Class[] {Integer.class, String.class};
            boolean[] canEdit = new boolean[] {false, false};

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        jScrollPane2.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setHeaderValue("Id Categoria");
            jTable1.getColumnModel().getColumn(1).setHeaderValue("Nombre Categoria");
        }

        jButton2.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 17));
        jButton2.setEnabled(false);
        jButton2.addActionListener(evt -> jButton2ActionPerformed(evt));

        jButton3.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 17));
        jButton3.setEnabled(false);
        jButton3.addActionListener(evt -> jButton3ActionPerformed(evt));

        jButton5.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 17));
        jButton5.addActionListener(evt -> jButton5ActionPerformed(evt));

        jButton6.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 17));
        jButton6.addActionListener(evt -> jButton6ActionPerformed(evt));

        jButton7.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 17));
        jButton7.setEnabled(false);
        jButton7.addActionListener(evt -> jButton7ActionPerformed(evt));

        JMenuBar jMenuBar1 = new JMenuBar();
        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(158, 158, 158)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(Codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 956, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(16, 16, 16))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton7)
                .addGap(187, 187, 187))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))
                        .addGap(42, 42, 42))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton7)
                            .addComponent(jButton5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(12, 12, 12)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)
                        .addGap(36, 36, 36)
                        .addComponent(jButton6))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(58, Short.MAX_VALUE))
        );
    }
    
    
    
    
    
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) { 
    String IdCategoria = Codigo.getText().trim();
    
    if (IdCategoria.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Por favor, ingrese un ID de categoría.", "Campo vacío", JOptionPane.WARNING_MESSAGE);
        return;  // No procede si el campo está vacío
    }

    Connection conexion = null;
    PreparedStatement pst = null;
    try {
        int dCategoria = Integer.parseInt(IdCategoria);
        conexion = conexionBD.getConnection();

        // Consulta para obtener el estado de la categoría
        String consulta = "SELECT IdCategoria, NombreCategoria, Estado FROM categoria WHERE IdCategoria = ?";
        pst = conexion.prepareStatement(consulta);        
        pst.setInt(1, dCategoria);
        ResultSet rs = pst.executeQuery();

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        boolean CategoriaEncontrado = false;

        while (rs.next()) {
            CategoriaEncontrado = true;

            int Idcategoria = rs.getInt("IdCategoria");
            String NombreCategoria = rs.getString("NombreCategoria");
            int EstadoCategoria = rs.getInt("Estado");  // Obtener el estado de la categoría

            // Si la categoría está desactivada (Estado = 0)
            if (EstadoCategoria == 0) {
                int respuesta = JOptionPane.showConfirmDialog(null, 
                    "La categoría está desactivada. ¿Desea habilitarla nuevamente?", 
                    "Habilitar Categoría", JOptionPane.YES_NO_OPTION);

                if (respuesta == JOptionPane.YES_OPTION) {
                    // Si el usuario elige 'Sí', actualizar el estado de la categoría a 1 (habilitada)
                    String updateConsulta = "UPDATE categoria SET Estado = 1 WHERE IdCategoria = ?";
                    try (PreparedStatement updatePst = conexion.prepareStatement(updateConsulta)) {
                        updatePst.setInt(1, Idcategoria);
                        updatePst.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Categoría habilitada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "La categoría no se habilitará.", "Cancelado", JOptionPane.INFORMATION_MESSAGE);
                    return; // Salir si el usuario no quiere habilitarla
                }
            }

            // Verificar si la categoría ya está en la tabla
            boolean CategoriaEnTabla = false;
            for (int i = 0; i < model.getRowCount(); i++) {
                Object valorCelda = model.getValueAt(i, 0);
                
                if (valorCelda != null && valorCelda instanceof Integer) {
                    int idEnTabla = (Integer) valorCelda;
                    if (idEnTabla == Idcategoria) {
                        CategoriaEnTabla = true;
                        break;
                    }
                }
            }

            // Si la categoría no está en la tabla, agregarla
            if (CategoriaEnTabla) {
                JOptionPane.showMessageDialog(null, "La categoría ya está en la tabla.", "Categoría duplicada", JOptionPane.WARNING_MESSAGE);
            } else {
                Object[] fila = {Idcategoria, NombreCategoria};
                model.addRow(fila);
            }
        }

        if (!CategoriaEncontrado) {
            JOptionPane.showMessageDialog(null, "No se encontró categoría con el ID ingresado.", "Categoría no encontrada", JOptionPane.WARNING_MESSAGE);
        }

        if (model.getRowCount() > 0) {
            updateButtonVisibility();  // Actualizar visibilidad de los botones
        }
        
        rs.close();
        pst.close();
        conexion.close();
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al buscar la categoría.", "Error", JOptionPane.ERROR_MESSAGE);
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(null, "El ID de la categoría debe ser un número entero.", "Formato inválido", JOptionPane.ERROR_MESSAGE);
    }
}
   
    //desactivar categoria

    private void CodigoActionPerformed(java.awt.event.ActionEvent evt) {                                       
        // TODO add your handling code here:
    }                                      
   
private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
    int selectedRow = jTable1.getSelectedRow();
    if (selectedRow >= 0) {
        // Obtener el IdCategoria de la fila seleccionada
        int idCategoria = (int) jTable1.getValueAt(selectedRow, 0); // Asumiendo que el ID de la categoría está en la primera columna

        // Establecer la consulta para desactivar la categoría (Estado = 0)
        String consulta = "UPDATE categoria SET Estado = 0 WHERE IdCategoria = ?";

        try (Connection conexion = conexionBD.getConnection(); // Usar conexionBD para obtener la conexión
             PreparedStatement pst = conexion.prepareStatement(consulta)) {
            
            // Establecer el parámetro de la consulta
            pst.setInt(1, idCategoria);

            // Ejecutar la actualización
            pst.executeUpdate();

            // Eliminar la fila de la tabla (solo visualmente)
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.removeRow(selectedRow);

            JOptionPane.showMessageDialog(null, "Categoría desactivada con éxito.", "Desactivación exitosa", JOptionPane.INFORMATION_MESSAGE);
            
            // Llamar a updateButtonVisibility() después de modificar la tabla
            updateButtonVisibility();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al desactivar la categoría: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(null, "Seleccione una categoría de la tabla para desactivar.", "Categoría no seleccionada", JOptionPane.WARNING_MESSAGE);
    }
}

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {                                         
    // Verificar si se ha seleccionado exactamente una fila
        int[] selectedRows = jTable1.getSelectedRows();
        if (selectedRows.length == 1) {
            int selectedRow = selectedRows[0]; // Solo se debe seleccionar una fila

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

            // Obtener los datos de la categoría seleccionada
            int idCategoria = (int) model.getValueAt(selectedRow, 0); // Asumimos que IdCategoria está en la columna 0
            String nombreCategoria = (String) model.getValueAt(selectedRow, 1); // Asumimos que NombreCategoria está en la columna 1

            // Abrir el diálogo de modificación
            ModificarCategoriaDialog modificarDialog = new ModificarCategoriaDialog((JFrame) SwingUtilities.getWindowAncestor(this), idCategoria, nombreCategoria);
            modificarDialog.setVisible(true);

            if (modificarDialog.isModified()) {
                // Obtener los datos modificados
                String nuevoNombreCategoria = modificarDialog.getNombreCategoria();

                // Actualizar la tabla
                model.setValueAt(nuevoNombreCategoria, selectedRow, 1); // Asumimos que NombreCategoria está en la columna 1

                Connection conexion = null;
                PreparedStatement pst = null;
                // Actualizar la base de datos
                try {
                    conexion = conexionBD.getConnection();
                    String consulta = "UPDATE categoria SET NombreCategoria = ? WHERE IdCategoria = ?";
                    pst = conexion.prepareStatement(consulta);
                    pst.setString(1, nuevoNombreCategoria);
                    pst.setInt(2, idCategoria); // Aquí usamos el ID original para buscar la categoría
                    pst.executeUpdate();

                    pst.close();
                    conexion.close();

                } 
                catch (SQLException e) {
                    JOptionPane.showMessageDialog(this, "Error al actualizar la categoría: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if (selectedRows.length > 1) {
            JOptionPane.showMessageDialog(this, "Seleccione solo una categoría para modificar.", "Selección múltiple", JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una categoría de la tabla para modificar.", "Categoría no seleccionada", JOptionPane.WARNING_MESSAGE);
        }

    }

    /// MODIFICAR LO DE CATEGORIAS QUE SE DESCOMPAGINO TODO EL DISEÑO Y NO APRARECE EL BOTON DE LISTAR                           
   public class ModificarCategoriaDialog extends JDialog {
    private JTextField txtNombreCategoria;
    private JButton btnGuardar;
    private JButton btnCancelar;
    private boolean isModified = false;

    public ModificarCategoriaDialog(Frame parent, int idCategoria, String nombreCategoria) {
        super(parent, true);
        setTitle("Modificar Categoría");
        setSize(400, 200); // Ajusta el tamaño del diálogo
        setLayout(new BorderLayout()); // Utiliza BorderLayout para una disposición más flexible

        // Panel para los campos de texto usando GridBagLayout
        JPanel panelCampos = new JPanel(new GridBagLayout());
        add(panelCampos, BorderLayout.CENTER); // Agrega el panel al centro del diálogo

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espaciado entre componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 1.0; // Permite que el componente se expanda horizontalmente

    
        // Etiqueta y campo de texto para Nombre Categoría
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelCampos.add(new JLabel("Nombre Categoría:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        txtNombreCategoria = new JTextField(nombreCategoria);
        txtNombreCategoria.setPreferredSize(new Dimension(200, 25)); // Ajusta el tamaño preferido del campo de texto
        panelCampos.add(txtNombreCategoria, gbc);

        // Panel para los botones
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        add(panelBoton, BorderLayout.SOUTH); // Agrega el panel al sur del diálogo

        btnGuardar = new JButton("Guardar");
        btnGuardar.setVisible(false); // Inicialmente, el botón "Guardar" está oculto
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isModified = true;
                dispose();
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
        txtNombreCategoria.getDocument().addDocumentListener(new DocumentListener() {
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
                if (!txtNombreCategoria.getText().equals(nombreCategoria)) {
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

    public String getNombreCategoria() {
        return txtNombreCategoria.getText();
    }

    
}

private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {                                         
    // LISTA TODAS LAS CATEGORÍAS CON ESTADO 1
    Connection conexion = null;
    PreparedStatement pst = null;

    try {
        // Establecer conexión a la base de datos usando conexionBD
        conexion = conexionBD.getConnection();
        
        // Consulta SQL para obtener solo las categorías con Estado = 1
        String consulta = "SELECT IdCategoria, NombreCategoria FROM categoria WHERE Estado = 1";
        
        pst = conexion.prepareStatement(consulta);
        ResultSet rs = pst.executeQuery();

        // Limpiar la tabla antes de agregar nuevos datos
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0); // Limpiar las filas existentes

        // Procesar resultados y agregar a la tabla
        while (rs.next()) {
            int idcategoria = rs.getInt("IdCategoria");
            String nombrecategoria = rs.getString("NombreCategoria");

            // Agregar fila al modelo de la tabla
            model.addRow(new Object[]{idcategoria, nombrecategoria});
        }

        // Actualizar visibilidad de botones
        updateButtonVisibility();

        // Cerrar recursos
        rs.close();
        pst.close();
        conexion.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al listar la categoría: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}  
 
    
    // Definición del método agregar
   private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {
    // Mostrar el diálogo para agregar una categoría
    AgregarCategoriaDialog dialog = new AgregarCategoriaDialog(this);
    dialog.setVisible(true);

    // Verificar si el usuario ha decidido agregar una categoría
    if (dialog.isAdded()) {
        String nombreCategoria = dialog.getNombreCategoria();

        // Validar que el nombre de la categoría no esté vacío
        if (nombreCategoria.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre de la categoría no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Connection conexion = null;
        PreparedStatement pst = null;
        // Intentar agregar la categoría a la base de datos
        try {
            // Establecer conexión a la base de datos
            conexion = conexionBD.getConnection();

             // Consulta SQL para insertar una nueva categoría
            String consulta = "INSERT INTO categoria (NombreCategoria) VALUES (?)";
            pst = conexion.prepareStatement(consulta);
            pst.setString(1, nombreCategoria);
            int filasAfectadas = pst.executeUpdate();


            // Verificar si la inserción fue exitosa
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(this, "Categoría agregada exitosamente.");

                // Opcional: Actualizar la tabla para reflejar la nueva categoría
                // ActualizarTabla(); // Si tienes un método para actualizar la tabla

            } else {
                JOptionPane.showMessageDialog(this, "No se pudo agregar la categoría.", "Error", JOptionPane.ERROR_MESSAGE);
            }

            // Cerrar recursos
            pst.close();
            conexion.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al agregar la categoría a la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Cerrar recursos usando el método cerrarRecursos de conexionBD
            conexionBD.cerrarRecursos(null, pst, conexion);
        }
    }
}

   public class AgregarCategoriaDialog extends JDialog {
    private JTextField txtNombreCategoria;
    private JButton btnAgregar;
    private JButton btnCancelar;
    private boolean added = false;

    public AgregarCategoriaDialog(JInternalFrame parent) {
        super((Frame) SwingUtilities.getWindowAncestor(parent), true);
        setTitle("Agregar Categoría");
        setSize(400, 200);
        setLayout(new BorderLayout());

        // Panel para los campos de texto usando GridBagLayout
        JPanel panelCampos = new JPanel(new GridBagLayout());
        add(panelCampos, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espaciado entre componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelCampos.add(new JLabel("Nombre Categoría:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        txtNombreCategoria = new JTextField();
        panelCampos.add(txtNombreCategoria, gbc);

        // Panel para los botones
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        add(panelBoton, BorderLayout.SOUTH);

        btnAgregar = new JButton("Agregar");
        btnAgregar.setEnabled(false);
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isCategoriaExists(txtNombreCategoria.getText())) {
                    JOptionPane.showMessageDialog(AgregarCategoriaDialog.this,
                            "La categoría ya existe en la base de datos.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    added = true;
                    dispose();
                }
            }
        });
        panelBoton.add(btnAgregar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());
        panelBoton.add(btnCancelar);

        txtNombreCategoria.getDocument().addDocumentListener(new DocumentListener() {
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
                if (!txtNombreCategoria.getText().trim().isEmpty()) {
                    btnAgregar.setEnabled(true);
                } else {
                    btnAgregar.setEnabled(false);
                }
            }
        });

        setLocationRelativeTo(parent);
    }

    public boolean isAdded() {
        return added;
    }

    public String getNombreCategoria() {
        return txtNombreCategoria.getText();
    }

    private boolean isCategoriaExists(String nombreCategoria) {
        
        String sql = "SELECT COUNT(*) FROM categoria WHERE NombreCategoria = ?";
        try (Connection conn = conexionBD.getConnection(); // Usa el método getConnection de conexionBD

            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombreCategoria);
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

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        //LIMPIAR TABLA
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        updateButtonVisibility();
    }  
    
    
    
    private void updateButtonVisibility() {
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    boolean hasRows = model.getRowCount() > 0;

    jButton2.setEnabled(hasRows); // Botón "Quitar"
    jButton3.setEnabled(hasRows); // Botón "Modificar"
    jButton7.setEnabled(hasRows); // Botón "Limpiar"
    }

    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(form2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(form2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(form2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(form2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        try {
            // Establecer el Look and Feel de FlatLaf
            javax.swing.UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        // Crear y mostrar la ventana
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new form2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JTextField Codigo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    // End of variables declaration                   
}

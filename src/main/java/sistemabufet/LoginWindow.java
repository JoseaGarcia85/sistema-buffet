package sistemabufet;

import Inicio.form2;
import Inicio.formVendedor;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import Conexion.conexionBD; 

public class LoginWindow extends JFrame {
    private JTextField campoUsuario;
    private JPasswordField campoContrasena;
    private JButton botonIniciarSesion;
    private boolean esAdmin;

    public LoginWindow() {
        setTitle("Inicio de Sesión");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        add(panel);
        colocarComponentes(panel);

        setVisible(true);
    }

    private void colocarComponentes(JPanel panel) {
        panel.setLayout(null);

        JLabel etiquetaUsuario = new JLabel("Usuario:");
        etiquetaUsuario.setBounds(10, 20, 80, 25);
        panel.add(etiquetaUsuario);

        campoUsuario = new JTextField(20);
        campoUsuario.setBounds(100, 20, 165, 25);
        panel.add(campoUsuario);

        JLabel etiquetaContrasena = new JLabel("Contraseña:");
        etiquetaContrasena.setBounds(10, 50, 80, 25);
        panel.add(etiquetaContrasena);

        campoContrasena = new JPasswordField(20);
        campoContrasena.setBounds(100, 50, 165, 25);
        panel.add(campoContrasena);

        botonIniciarSesion = new JButton("Iniciar Sesión");
        botonIniciarSesion.setBounds(80, 80, 150, 25);
        panel.add(botonIniciarSesion);

        botonIniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = campoUsuario.getText();
                String contraseña = new String(campoContrasena.getPassword());
                if (autenticar(usuario, contraseña)) {
                    if (esAdmin) {
                        JOptionPane.showMessageDialog(null, "Inicio de sesión como administrador exitoso");
                        dispose(); // Cierra la ventana de inicio de sesión después de iniciar sesión correctamente
                        SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                form2 ventanaForm2 = new form2();
                                ventanaForm2.setLocationRelativeTo(null); // Centra la ventana form2 en la pantalla
                                ventanaForm2.setVisible(true); // Muestra la ventana form2
                            }
                        });
                    } else {
                        JOptionPane.showMessageDialog(null, "Inicio de sesión como vendedor exitoso");
                        dispose(); // Cierra la ventana de inicio de sesión después de iniciar sesión correctamente
                        SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                formVendedor ventanaVendedor = new formVendedor();
                                ventanaVendedor.setLocationRelativeTo(null); // Centra la ventana formVendedor en la pantalla
                                ventanaVendedor.setVisible(true); // Muestra la ventana formVendedor
                            }
                        });
                    }
                } 
            }
        });
    }

    private boolean autenticar(String usuario, String contrasena) {
        // Consulta SQL para verificar usuario y contraseña
        String query = "SELECT * FROM usuario WHERE NombreUsuario = ? AND Contrasenia = ?";

        try (Connection conexion = conexionBD.getConnection();
             PreparedStatement pst = conexion.prepareStatement(query)) {

            pst.setString(1, usuario);
            pst.setString(2, contrasena);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    int estado = rs.getInt("Estado");
                    if (estado == 1) { // Usuario activo
                        int rol = rs.getInt("IdRol");
                        esAdmin = (rol == 1); // Si el rol es 1, es administrador
                        return true; // Usuario válido y activo
                    } else { // Usuario desactivado
                        JOptionPane.showMessageDialog(null, "El usuario está desactivado", "Error", JOptionPane.ERROR_MESSAGE);
                        return false; // Detenemos aquí el flujo para evitar el otro mensaje
                    }
                } else {
                    // Si no se encuentra el usuario
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error en la conexión con la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Este bloque ya no debería alcanzarse, porque el flujo se detiene antes
        return false;
    }




    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LoginWindow();
            }
        });
    }
}

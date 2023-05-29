package interfaces;

import javax.swing.*;
import utilidad.DAO;
import java.sql.SQLException;
import java.util.HashMap;

public class PantallaActualizarDatos extends JFrame {
    JTextField telefonoField;
    JPasswordField contrasenaField;
    JTextField pesoField;
    JTextField alturaField;
    JTextField sexoField;
    JTextField nivelActividadField;
    JTextField objetivoField;
    JTextField objetivoCaloriasField;

    public PantallaActualizarDatos() {
        setSize(400, 600);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel telefonoLabel = new JLabel("Telefono:");
        telefonoLabel.setBounds(100, 60, 80, 30);
        getContentPane().add(telefonoLabel);

        telefonoField = new JTextField();
        telefonoField.setBounds(200, 60, 120, 30);
        getContentPane().add(telefonoField);

        JLabel contrasenaLabel = new JLabel("Contrasena:");
        contrasenaLabel.setBounds(100, 100, 80, 30);
        getContentPane().add(contrasenaLabel);

        contrasenaField = new JPasswordField();
        contrasenaField.setBounds(200, 100, 120, 30);
        getContentPane().add(contrasenaField);

        JLabel pesoLabel = new JLabel("Peso:");
        pesoLabel.setBounds(100, 140, 80, 30);
        getContentPane().add(pesoLabel);

        pesoField = new JTextField();
        pesoField.setBounds(200, 140, 120, 30);
        getContentPane().add(pesoField);

        JLabel alturaLabel = new JLabel("Altura:");
        alturaLabel.setBounds(100, 180, 80, 30);
        getContentPane().add(alturaLabel);

        alturaField = new JTextField();
        alturaField.setBounds(200, 180, 120, 30);
        getContentPane().add(alturaField);

        JLabel sexoLabel = new JLabel("Sexo:");
        sexoLabel.setBounds(100, 220, 80, 30);
        getContentPane().add(sexoLabel);

        sexoField = new JTextField();
        sexoField.setBounds(200, 220, 120, 30);
        getContentPane().add(sexoField);

        JLabel nivelActividadLabel = new JLabel("Nivel Actividad:");
        nivelActividadLabel.setBounds(100, 260, 100, 30);
        getContentPane().add(nivelActividadLabel);

        nivelActividadField = new JTextField();
        nivelActividadField.setBounds(200, 260, 120, 30);
        getContentPane().add(nivelActividadField);

        JLabel objetivoLabel = new JLabel("Objetivo:");
        objetivoLabel.setBounds(100, 300, 80, 30);
        getContentPane().add(objetivoLabel);

        objetivoField = new JTextField();
        objetivoField.setBounds(200, 300, 120, 30);
        getContentPane().add(objetivoField);

        JLabel objetivoCaloriasLabel = new JLabel("Objetivo Calorias:");
        objetivoCaloriasLabel.setBounds(100, 340, 120, 30);
        getContentPane().add(objetivoCaloriasLabel);

        objetivoCaloriasField = new JTextField();
        objetivoCaloriasField.setBounds(200, 340, 120, 30);
        getContentPane().add(objetivoCaloriasField);

        JButton actualizarButton = new JButton("Actualizar");
        actualizarButton.setBounds(140, 380, 120, 30);
        getContentPane().add(actualizarButton);
        actualizarButton.addActionListener(e -> {
            HashMap<String, Object> datosAModificar = new HashMap<>();
            datosAModificar.put("telefono", telefonoField.getText());
            datosAModificar.put("contrasena", new String(contrasenaField.getPassword()));
            datosAModificar.put("peso", pesoField.getText());
            datosAModificar.put("altura", alturaField.getText());
            datosAModificar.put("sexo", sexoField.getText());
            datosAModificar.put("nivelActividad", nivelActividadField.getText());
            datosAModificar.put("objetivo", objetivoField.getText());
            datosAModificar.put("objetivoCalorias", objetivoCaloriasField.getText());

            HashMap<String, Object> restricciones = new HashMap<>();
            // Aquí tendrás que especificar las restricciones de la actualización.
            // restricciones.put("nombre_actual", "valor_actual");

            try {
                DAO.actualizar("nombre_de_tu_tabla", datosAModificar, restricciones);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        JButton botonVolver = new JButton("Volver");
        botonVolver.setBounds(140, 420, 120, 30);
        getContentPane().add(botonVolver);
        botonVolver.addActionListener(e -> {
            new PantallaPrincipal().setVisible(true);
            this.setVisible(false);
        });
    }
}

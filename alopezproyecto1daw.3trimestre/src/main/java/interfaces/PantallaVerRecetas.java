package interfaces;

import javax.swing.JButton;
import javax.swing.JFrame;

public class PantallaVerRecetas extends JFrame {
    public PantallaVerRecetas() {
        setSize(400, 400);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JButton botonVolver = new JButton("Volver");
        botonVolver.setBounds(141, 84, 80, 30);
        getContentPane().add(botonVolver);
        botonVolver.addActionListener(e -> {
            new PantallaPrincipal().setVisible(true);
            this.setVisible(false);
        });
    }
}

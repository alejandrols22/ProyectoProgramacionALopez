package interfaces;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PantallaVerProgreso extends JFrame {

    private TimeSeries series;
    private JTextField weightField;
    private JDateChooser dateChooser;

    public PantallaVerProgreso() {
        series = new TimeSeries("Peso");
        final TimeSeriesCollection dataset = new TimeSeriesCollection(this.series);
        final JFreeChart chart = createChart(dataset);

        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 270));

        // Crea los campos de texto para el peso y el selector de fecha
        weightField = new JTextField(10);
        dateChooser = new JDateChooser();

        // Crea el botón "Agregar"
        JButton addButton = new JButton("Agregar");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String weightText = weightField.getText();
                Date selectedDate = dateChooser.getDate();

                if (!weightText.isEmpty() && selectedDate != null) {
                    try {
                        double weight = Double.parseDouble(weightText);
                        addWeightMeasurement(selectedDate, weight);
                        chartPanel.repaint();
                        weightField.setText("");
                        dateChooser.setDate(null);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(PantallaVerProgreso.this, "Por favor, ingrese un valor numérico válido para el peso.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(PantallaVerProgreso.this, "Por favor, ingrese el peso y la fecha.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Crea el botón "Volver"
        JButton backButton = new JButton("Volver");
        backButton.addActionListener(e -> {
            new PantallaPrincipal().setVisible(true);
            this.setVisible(false);
        });

        // Crea el mensaje para el usuario
        JLabel userMessage = new JLabel("Para ver el progreso, debes añadir al menos dos veces datos del cambio que lleves.");
        userMessage.setFont(new Font("Serif", Font.BOLD, 20));

        // Crea el panel para ingresar los datos
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Peso:"));
        inputPanel.add(weightField);
        inputPanel.add(new JLabel("Fecha:"));
        inputPanel.add(dateChooser);
        inputPanel.add(addButton);

        // Añade el panel del gráfico, el botón "Volver", el mensaje y el panel de entrada de datos al contenedor principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(chartPanel, BorderLayout.CENTER);
        mainPanel.add(backButton, BorderLayout.SOUTH);
        mainPanel.add(userMessage, BorderLayout.NORTH);
        mainPanel.add(inputPanel, BorderLayout.EAST);

        setContentPane(mainPanel);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Abre la ventana maximizada
    }

    private JFreeChart createChart(final TimeSeriesCollection dataset) {
        return ChartFactory.createTimeSeriesChart(
                "Progreso de peso",
                "Fecha",
                "Peso",
                dataset,
                true,
                true,
                false
        );
    }

    public void addWeightMeasurement(Date date, double weight) {
        Day day = new Day(date);
        this.series.addOrUpdate(day, weight);
    }

    public void showScreen() {
        this.pack();
        this.setVisible(true);
    }

    public void hideScreen() {
        this.setVisible(false);
    }
}

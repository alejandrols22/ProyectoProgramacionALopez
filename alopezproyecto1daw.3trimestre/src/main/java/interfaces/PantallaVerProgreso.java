package interfaces;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class PantallaVerProgreso extends JFrame {

    private TimeSeries series;

    public PantallaVerProgreso() {
        series = new TimeSeries("Peso");
        final TimeSeriesCollection dataset = new TimeSeriesCollection(this.series);
        final JFreeChart chart = createChart(dataset);

        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 270));

        // Crea el botón "Volver"
        JButton backButton = new JButton("Volver");
        backButton.setBounds(141, 84, 80, 30);
        backButton.addActionListener(e -> {
            new PantallaPrincipal().setVisible(true);
            this.setVisible(false);
        });
        
        

        // Crea el mensaje para el usuario
        JLabel userMessage = new JLabel("Para que la gráfica muestre datos, el usuario debe actualizar sus datos en PantallaActualizarDatos");
        userMessage.setFont(new Font("Serif", Font.BOLD, 20));

        // Añade el panel del gráfico, el botón y el mensaje al contenedor principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(chartPanel, BorderLayout.CENTER);
        mainPanel.add(backButton, BorderLayout.SOUTH);
        mainPanel.add(userMessage, BorderLayout.NORTH);

        setContentPane(mainPanel);
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

package interfaces;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class PantallaPerfil extends JFrame {
    private static final String CONFIG_FILE = "bdconfig.ini";

    private Connection getConnection() throws SQLException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader(CONFIG_FILE));
        String ip = reader.readLine();
        int puerto = Integer.parseInt(reader.readLine());
        String nombreBD = reader.readLine();
        String user = reader.readLine();
        String password = reader.readLine();
        reader.close();

        String url = "jdbc:mysql://" + ip + ":" + puerto + "/" + nombreBD;
        return DriverManager.getConnection(url, user, password);
    }

    private Object[][] getData() throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery("SELECT Usuario.edad, Usuario.peso, Usuario.altura, Usuario.sexo, Usuario.nivel_actividad, Usuario.objetivo, Usuario.objetivo_diario_calorias, Usuario.email, Usuario.contraseña, Usuario.telefono, Entidad.nombre AS entidad_nombre FROM Usuario JOIN Entidad ON Usuario.entidadId = Entidad.id");

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            resultSet.last();
            int rowCount = resultSet.getRow();
            resultSet.beforeFirst();

            Object[][] data = new Object[rowCount][columnCount - 1]; 

            int rowIndex = 0;
            while (resultSet.next()) {
                for (int i = 2; i <= columnCount; i++) { 
                    data[rowIndex][i - 2] = resultSet.getObject(i);
                }
                rowIndex++;
            }

            return data;
        } catch (IOException e) {
            e.printStackTrace();
            return new Object[0][0];
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public PantallaPerfil() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        try {
            Object[][] data = getData();

            getContentPane().setBackground(new Color(50, 50, 50));

            JTable usuariosTable = new JTable(new UsuariosTableModel(data));
            JScrollPane scrollPane = new JScrollPane(usuariosTable);
            getContentPane().add(scrollPane, BorderLayout.CENTER);

            JButton volverButton = new JButton("Volver");
            volverButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    PantallaPrincipal pantallaPrincipal = new PantallaPrincipal();
                    pantallaPrincipal.setVisible(true);
                    dispose();
                }
            });
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(volverButton);
            getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar los usuarios", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private class UsuariosTableModel extends AbstractTableModel {
        private Object[][] data;
        private String[] columnNames;

        public UsuariosTableModel(Object[][] data) {
            this.data = data;
            this.columnNames = getColumnNames();
        }

        private String[] getColumnNames() {
            try {
                Connection connection = getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT Usuario.edad, Usuario.peso, Usuario.altura, Usuario.sexo, Usuario.nivel_actividad, Usuario.objetivo, Usuario.objetivo_diario_calorias, Usuario.email, Usuario.contraseña, Usuario.telefono, Entidad.nombre AS entidad_nombre FROM Usuario JOIN Entidad ON Usuario.entidadId = Entidad.id LIMIT 1");
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();

                String[] columnNames = new String[columnCount - 1]; // Reducir el número de columnas en 1

                for (int i = 2; i <= columnCount; i++) { // Empezar desde 2 para omitir el ID
                    columnNames[i - 2] = metaData.getColumnName(i);
                }

                resultSet.close();
                statement.close();
                connection.close();

                return columnNames;
            } catch (SQLException | IOException e) {
                e.printStackTrace();
                return new String[0];
            }
        }

        @Override
        public int getRowCount() {
            return data.length;
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return data[rowIndex][columnIndex];
        }

        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PantallaPerfil pantalla = new PantallaPerfil();
            pantalla.setVisible(true);
        });
    }
}

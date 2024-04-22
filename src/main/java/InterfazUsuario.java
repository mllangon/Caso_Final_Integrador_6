import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InterfazUsuario extends JFrame {
    private JPanel mainPanel;
    private JTextField txtPrimerElemento, txtSegundoElemento, txtVenta, txtClave, txtValor;
    private JTextArea areaPares, areaVentas, areaMapaResultados;
    private JComboBox<String> cmbOrdenamiento;
    private List<String> ventas;
    private JTabbedPane tabbedPane;

    public InterfazUsuario() {
        super("Gestión y Análisis de Datos Multidimensionales");
        ventas = new ArrayList<>();
        prepareGUI();
    }

    private void prepareGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        tabbedPane = new JTabbedPane();
        tabbedPane.add("Gestión de Pares", createDataManagementPanel(loadIcon("src/smain/resources/descargas.png")));
        tabbedPane.add("Gestión de Ventas", createSalesPanel(loadIcon("src/smain/resources/ventas.png")));
        tabbedPane.add("Mapas y Asociación", createMapPanel(loadIcon("src/smain/resources/mapas.png")));
        tabbedPane.add("Indexación y Visualización", createIndexacionPanel(loadIcon("src/smain/resources/indexacion.png")));

        setContentPane(tabbedPane);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                showWelcomeScreen();
            }
        });
    }

    private ImageIcon loadIcon(String path) {
        URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    private void showWelcomeScreen() {
        JOptionPane.showMessageDialog(this,
                "Bienvenido a la Gestión y Análisis de Datos",
                "Bienvenido",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private JPanel createDataManagementPanel(ImageIcon icon) {
        JPanel panel = new TabPanel(icon);
        txtPrimerElemento = new JTextField();
        txtSegundoElemento = new JTextField();
        JButton btnAgregar = new JButton("Agregar Pareja");
        JButton btnEliminar = new JButton("Eliminar Pareja");
        areaPares = new JTextArea(10, 30);
        areaPares.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaPares);

        panel.setLayout(new GridLayout(5, 2));
        panel.add(new JLabel("Primer Elemento:"));
        panel.add(txtPrimerElemento);
        panel.add(new JLabel("Segundo Elemento:"));
        panel.add(txtSegundoElemento);
        panel.add(btnAgregar);
        panel.add(btnEliminar);
        panel.add(new JLabel());
        panel.add(scrollPane);

        btnAgregar.addActionListener(e -> {
            String primerElemento = txtPrimerElemento.getText().trim();
            String segundoElemento = txtSegundoElemento.getText().trim();
            areaPares.append(primerElemento + " - " + segundoElemento + "\n");
            txtPrimerElemento.setText("");
            txtSegundoElemento.setText("");
        });

        btnEliminar.addActionListener(e -> areaPares.setText(""));

        return panel;
    }

    private JPanel createSalesPanel(ImageIcon icon) {
        JPanel panel = new TabPanel(icon);
        txtVenta = new JTextField();
        JButton btnAgregarVenta = new JButton("Agregar Venta");
        JButton btnOrdenarVentas = new JButton("Ordenar Ventas");
        cmbOrdenamiento = new JComboBox<>(new String[]{"Ascendente", "Descendente"});
        areaVentas = new JTextArea(10, 30);
        areaVentas.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaVentas);

        panel.setLayout(new GridLayout(5, 2));
        panel.add(new JLabel("Venta:"));
        panel.add(txtVenta);
        panel.add(new JLabel("Ordenamiento:"));
        panel.add(cmbOrdenamiento);
        panel.add(btnAgregarVenta);
        panel.add(btnOrdenarVentas);
        panel.add(new JLabel());
        panel.add(scrollPane);

        btnAgregarVenta.addActionListener(e -> {
            String venta = txtVenta.getText().trim();
            ventas.add(venta);
            areaVentas.append(venta + "\n");
            txtVenta.setText("");
        });

        btnOrdenarVentas.addActionListener(e -> {
            Collections.sort(ventas);
            areaVentas.setText("");
            for (String v : ventas) {
                areaVentas.append(v + "\n");
            }
        });

        return panel;
    }

    private JPanel createMapPanel(ImageIcon icon) {
        JPanel panel = new TabPanel(icon);
        txtClave = new JTextField();
        txtValor = new JTextField();
        JButton btnAgregarMapa = new JButton("Agregar Asociación");
        JButton btnBuscarMapa = new JButton("Buscar Asociación");
        areaMapaResultados = new JTextArea(10, 30);
        areaMapaResultados.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaMapaResultados);

        panel.setLayout(new GridLayout(5, 2));
        panel.add(new JLabel("Clave:"));
        panel.add(txtClave);
        panel.add(new JLabel("Valor:"));
        panel.add(txtValor);
        panel.add(btnAgregarMapa);
        panel.add(btnBuscarMapa);
        panel.add(new JLabel());
        panel.add(scrollPane);

        btnAgregarMapa.addActionListener(e -> {
            String clave = txtClave.getText().trim();
            String valor = txtValor.getText().trim();
            areaMapaResultados.append(clave + " => " + valor + "\n");
            txtClave.setText("");
            txtValor.setText("");
        });

        btnBuscarMapa.addActionListener(e -> {
            String clave = txtClave.getText().trim();
            String valor = "Valor asociado no encontrado"; // Simulate search
            areaMapaResultados.append("Buscar '" + clave + "': " + valor + "\n");
        });

        return panel;
    }

    private JPanel createIndexacionPanel(ImageIcon icon) {
        JPanel panel = new TabPanel(icon);
        JButton btnIndexar = new JButton("Indexar Directorio");
        JButton btnMostrarArchivos = new JButton("Mostrar Archivos Indexados");
        JTextArea areaVisualizacionArchivos = new JTextArea(10, 50);
        areaVisualizacionArchivos.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaVisualizacionArchivos);

        panel.setLayout(new BorderLayout());
        panel.add(btnIndexar, BorderLayout.NORTH);
        panel.add(btnMostrarArchivos, BorderLayout.CENTER);
        panel.add(scrollPane, BorderLayout.SOUTH);

        btnIndexar.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedDirectory = fileChooser.getSelectedFile();
                // Simulate indexing operation
                JOptionPane.showMessageDialog(panel, "Directorio indexado con éxito.");
            }
        });

        btnMostrarArchivos.addActionListener(e -> {
            // Simulate file display operation
            areaVisualizacionArchivos.setText("Archivo1.txt\nArchivo2.txt\nArchivo3.txt");
        });

        return panel;
    }

    private class TabPanel extends JPanel {
        public TabPanel(ImageIcon icon) {
            super();
            setLayout(new BorderLayout());
            if (icon != null) {
                JLabel labelIcon = new JLabel(icon);
                add(labelIcon, BorderLayout.WEST);
            }
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setPaint(new GradientPaint(0, 0, Color.RED, getWidth(), getHeight(), Color.BLACK));
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InterfazUsuario().setVisible(true));
    }
}

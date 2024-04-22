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

import Gestion_Datos.GestorDatos;
import Mapas_Asociacion.GestorMapas;
import Indexacion_Visualizacion.IndexadorArchivos;
import Indexacion_Visualizacion.VisualizadorArchivos;

public class InterfazUsuario extends JFrame {
    private JPanel mainPanel;
    private JTextField txtPrimerElemento, txtSegundoElemento, txtVenta, txtClave, txtValor;
    private JButton btnAgregar, btnEliminar, btnAgregarVenta, btnOrdenarVentas, btnAgregarMapa, btnBuscarMapa;
    private JTextArea areaPares, areaVentas, areaMapaResultados;
    private JComboBox<String> cmbOrdenamiento;
    private List<String> ventas = new ArrayList<>();
    private GestorDatos<String, Integer> gestorDatos = new GestorDatos<>();
    private GestorMapas gestorMapas = new GestorMapas();
    private IndexadorArchivos indexadorArchivos = new IndexadorArchivos();
    private VisualizadorArchivos visualizadorArchivos = new VisualizadorArchivos();
    public InterfazUsuario() {
        super("Gestión y Análisis de Datos Multidimensionales");
        prepareGUI();
    }

    private void prepareGUI() {
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setPaint(new GradientPaint(0, 0, Color.RED, getWidth(), getHeight(), Color.BLACK));
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        setContentPane(mainPanel);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.add("Gestión de Pares", createDataManagementPanel(loadIcon("/src/main/resources/descarga.png")));
        tabbedPane.add("Gestión de Ventas", createSalesPanel(loadIcon("/src/main/resources/ventas.png")));
        tabbedPane.add("Mapas y Asociación", createMapPanel(loadIcon("/src/main/resources/mapa.png")));
        tabbedPane.add("Indexación y Visualización", createIndexacionPanel(loadIcon("/src/main/resources/indexacion.png")));

        mainPanel.add(tabbedPane, BorderLayout.CENTER);

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
        JDialog welcomeDialog = new JDialog(this, "Bienvenido", true);
        welcomeDialog.setLayout(new BorderLayout());
        welcomeDialog.setSize(400, 300);
        welcomeDialog.setLocationRelativeTo(this);
        JLabel welcomeLabel = new JLabel("Bienvenido a la Gestión y Análisis de Datos", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 20));
        welcomeDialog.add(welcomeLabel, BorderLayout.CENTER);
        Timer timer = new Timer(2000, e -> welcomeDialog.dispose());
        timer.setRepeats(false);
        timer.start();
        welcomeDialog.setVisible(true);
    }


    private JPanel createDataManagementPanel(ImageIcon imageIcon) {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        txtPrimerElemento = new JTextField();
        txtSegundoElemento = new JTextField();
        btnAgregar = new JButton("Agregar Pareja");
        btnEliminar = new JButton("Eliminar Pareja");
        areaPares = new JTextArea(5, 30);
        areaPares.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaPares);

        panel.add(new JLabel("Primer Elemento (String):"));
        panel.add(txtPrimerElemento);
        panel.add(new JLabel("Segundo Elemento (Integer):"));
        panel.add(txtSegundoElemento);
        panel.add(btnAgregar);
        panel.add(btnEliminar);
        panel.add(scrollPane);

        btnAgregar.addActionListener(this::agregarPareja);
        btnEliminar.addActionListener(this::eliminarPareja);

        return panel;
    }

    private JPanel createSalesPanel(ImageIcon imageIcon) {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        txtVenta = new JTextField();
        btnAgregarVenta = new JButton("Agregar Venta");
        btnOrdenarVentas = new JButton("Ordenar Ventas");
        cmbOrdenamiento = new JComboBox<>(new String[]{"Ascendente", "Descendente"});
        areaVentas = new JTextArea(5, 30);
        areaVentas.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaVentas);

        panel.add(new JLabel("Nueva Venta:"));
        panel.add(txtVenta);
        panel.add(btnAgregarVenta);
        panel.add(cmbOrdenamiento);
        panel.add(btnOrdenarVentas);
        panel.add(scrollPane);

        btnAgregarVenta.addActionListener(this::agregarVenta);
        btnOrdenarVentas.addActionListener(this::ordenarVentas);

        return panel;
    }

    private JPanel createMapPanel(ImageIcon imageIcon) {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        txtClave = new JTextField();
        txtValor = new JTextField();
        btnAgregarMapa = new JButton("Agregar Asociación");
        btnBuscarMapa = new JButton("Buscar Asociación");
        areaMapaResultados = new JTextArea(5, 30);
        areaMapaResultados.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaMapaResultados);

        panel.add(new JLabel("Clave:"));
        panel.add(txtClave);
        panel.add(new JLabel("Valor:"));
        panel.add(txtValor);
        panel.add(btnAgregarMapa);
        panel.add(btnBuscarMapa);
        panel.add(scrollPane);

        btnAgregarMapa.addActionListener(this::agregarAsociacion);
        btnBuscarMapa.addActionListener(this::buscarAsociacion);

        return panel;
    }

    private JPanel createIndexacionPanel(ImageIcon imageIcon) {
        JPanel panel = new JPanel(new BorderLayout());
        JButton btnIndexar = new JButton("Indexar Directorio");
        JButton btnMostrarArchivos = new JButton("Mostrar Archivos Indexados");
        JTextArea areaVisualizacionArchivos = new JTextArea(10, 50);
        areaVisualizacionArchivos.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaVisualizacionArchivos);

        panel.add(btnIndexar, BorderLayout.NORTH);
        panel.add(btnMostrarArchivos, BorderLayout.CENTER);
        panel.add(scrollPane, BorderLayout.SOUTH);

        btnIndexar.addActionListener(e -> indexarDirectorio());
        btnMostrarArchivos.addActionListener(e -> mostrarArchivos(areaVisualizacionArchivos));

        return panel;
    }

    // Methods for managing pairs
    private void agregarPareja(ActionEvent e) {
        String primerElemento = txtPrimerElemento.getText().trim();
        String segundoElemento = txtSegundoElemento.getText().trim();
        areaPares.append(primerElemento + " - " + segundoElemento + "\n");
        txtPrimerElemento.setText("");
        txtSegundoElemento.setText("");
    }

    private void eliminarPareja(ActionEvent e) {
        areaPares.setText(""); // Clears the text area
    }

    // Methods for managing sales
    private void agregarVenta(ActionEvent e) {
        String venta = txtVenta.getText().trim();
        if (!venta.isEmpty()) {
            ventas.add(venta);
            txtVenta.setText("");
            areaVentas.append(venta + "\n");
        }
    }

    private void ordenarVentas(ActionEvent e) {
        Collections.sort(ventas);
        areaVentas.setText(String.join("\n", ventas));
    }

    // Methods for managing key-value associations
    private void agregarAsociacion(ActionEvent e) {
        String clave = txtClave.getText().trim();
        String valor = txtValor.getText().trim();
        gestorMapas.agregarAsociacion(clave, valor);
        areaMapaResultados.append(clave + " => " + valor + "\n");
        txtClave.setText("");
        txtValor.setText("");
    }

    private void buscarAsociacion(ActionEvent e) {
        String clave = txtClave.getText().trim();
        String valor = gestorMapas.recuperarValor(clave);
        areaMapaResultados.append("Valor para '" + clave + "': " + valor + "\n");
    }

    // Methods for indexing and displaying files
    private void indexarDirectorio() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedDirectory = fileChooser.getSelectedFile();
            indexadorArchivos.indexarDirectorio(selectedDirectory);
            JOptionPane.showMessageDialog(this, "Directorio indexado con éxito.");
        }
    }

    private void mostrarArchivos(JTextArea displayArea) {
        List<String> rutas = indexadorArchivos.getListaRutas();
        visualizadorArchivos.ordenarYMostrarArchivos(rutas);
        displayArea.setText(String.join("\n", rutas));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(InterfazUsuario::new);
    }
}

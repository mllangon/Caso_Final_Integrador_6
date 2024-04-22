import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Asegúrate de que estas clases estén correctamente definidas e importadas.
import Mapas_Asociacion.GestorMapas;
import Indexacion_Visualizacion.IndexadorArchivos;
import Indexacion_Visualizacion.VisualizadorArchivos;

public class InterfazUsuario extends JFrame {
    private JTextField txtPrimerElemento, txtSegundoElemento;
    private JTextArea areaPares;
    private JButton btnAgregar, btnEliminar;

    private JTextField txtVenta;
    private JButton btnAgregarVenta, btnOrdenarVentas;
    private JComboBox<String> cmbOrdenamiento;
    private JTextArea areaVentas;

    private JTextField txtClave, txtValor;
    private JButton btnAgregarMapa, btnBuscarMapa;
    private JTextArea areaMapaResultados;

    private List<String> ventas;
    private GestorMapas gestorMapas;
    private IndexadorArchivos indexadorArchivos;
    private VisualizadorArchivos visualizadorArchivos;

    public InterfazUsuario() {
        super("Gestión y Análisis de Datos Multidimensionales");
        ventas = new ArrayList<>();
        gestorMapas = new GestorMapas();
        indexadorArchivos = new IndexadorArchivos();
        visualizadorArchivos = new VisualizadorArchivos();
        initializeUI();
    }

    private void initializeUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel panelDatos = createDataManagementPanel();
        JPanel panelVentas = createSalesPanel();
        JPanel panelMapas = createMapPanel();
        JPanel panelIndexacion = createIndexacionPanel();

        tabbedPane.addTab("Gestión de Pares", panelDatos);
        tabbedPane.addTab("Gestión de Ventas", panelVentas);
        tabbedPane.addTab("Mapas y Asociación", panelMapas);
        tabbedPane.addTab("Indexación y Visualización", panelIndexacion);

        add(tabbedPane);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createDataManagementPanel() {
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

    private JPanel createSalesPanel() {
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

    private JPanel createMapPanel() {
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

    private JPanel createIndexacionPanel() {
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

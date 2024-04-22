import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import Mapas_Asociacion.GestorMapas;

public class InterfazUsuario extends JFrame {
    private JTextField txtPrimerElemento, txtSegundoElemento;
    private JTextArea areaPares;
    private JButton btnAgregar, btnEliminar, btnMostrar;

    private JTextField txtVenta;
    private JButton btnAgregarVenta, btnOrdenarVentas;
    private JComboBox<String> cmbOrdenamiento;
    private JTextArea areaVentas;

    private JTextField txtClave, txtValor;
    private JButton btnAgregarMapa, btnBuscarMapa;
    private JTextArea areaMapaResultados;

    private List<String> ventas;
    private GestorMapas gestorMapas;

    public InterfazUsuario() {
        super("Gestión y Análisis de Datos Multidimensionales");
        ventas = new ArrayList<>();
        gestorMapas = new GestorMapas();
        initializeUI();
    }

    private void initializeUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel panelDatos = createDataManagementPanel();
        JPanel panelVentas = createSalesPanel();
        JPanel panelMapas = createMapPanel();

        tabbedPane.addTab("Gestión de Pares", panelDatos);
        tabbedPane.addTab("Gestión de Ventas", panelVentas);
        tabbedPane.addTab("Mapas y Asociación", panelMapas);

        add(tabbedPane);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createDataManagementPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));

        txtPrimerElemento = new JTextField();
        txtSegundoElemento = new JTextField();
        btnAgregar = new JButton("Agregar Pareja");
        btnEliminar = new JButton("Eliminar Pareja");
        btnMostrar = new JButton("Mostrar Parejas");
        areaPares = new JTextArea(5, 30);
        areaPares.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaPares);

        panel.add(new JLabel("Primer Elemento (String):"));
        panel.add(txtPrimerElemento);
        panel.add(new JLabel("Segundo Elemento (Integer):"));
        panel.add(txtSegundoElemento);
        panel.add(btnAgregar);
        panel.add(btnEliminar);
        panel.add(btnMostrar);
        panel.add(scrollPane);

        btnAgregar.addActionListener(this::agregarPareja);
        btnEliminar.addActionListener(this::eliminarPareja);

        return panel;
    }

    private JPanel createSalesPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));

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
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));

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

    private void agregarPareja(ActionEvent e) {
        String primerElemento = txtPrimerElemento.getText();
        String segundoElemento = txtSegundoElemento.getText();
        areaPares.append(primerElemento + " - " + segundoElemento + "\n");
        txtPrimerElemento.setText("");
        txtSegundoElemento.setText("");
    }

    private void eliminarPareja(ActionEvent e) {
        String primerElemento = txtPrimerElemento.getText();
        String segundoElemento = txtSegundoElemento.getText();
        areaPares.setText(areaPares.getText().replace(primerElemento + " - " + segundoElemento + "\n", ""));
        txtPrimerElemento.setText("");
        txtSegundoElemento.setText("");
    }

        private void agregarVenta(ActionEvent e) {
        String venta = txtVenta.getText();
        if (!venta.isEmpty()) {
            ventas.add(venta);
            txtVenta.setText("");
            areaVentas.append(venta + "\n");
        }
    }

    private void ordenarVentas(ActionEvent e) {
        boolean ascendente = cmbOrdenamiento.getSelectedItem().equals("Ascendente");
        ventas.sort(ascendente ? String::compareTo : (a, b) -> b.compareTo(a));
        areaVentas.setText(String.join("\n", ventas));
    }

    private void agregarAsociacion(ActionEvent e) {
        String clave = txtClave.getText();
        String valor = txtValor.getText();
        gestorMapas.agregarAsociacion(clave, valor);
        areaMapaResultados.append("Asociación agregada: " + clave + " -> " + valor + "\n");
        txtClave.setText("");
        txtValor.setText("");
    }

    private void buscarAsociacion(ActionEvent e) {
        String clave = txtClave.getText();
        String resultado = gestorMapas.recuperarValor(clave);
        areaMapaResultados.append("Valor para '" + clave + "': " + resultado + "\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(InterfazUsuario::new);
    }
}

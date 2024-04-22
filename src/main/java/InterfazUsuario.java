import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class InterfazUsuario extends JFrame {
    private JTextField txtPrimerElemento;
    private JTextField txtSegundoElemento;
    private JTextArea areaPares;
    private JButton btnAgregar;
    private JButton btnEliminar;
    private JButton btnMostrar;

    private JTextField txtVenta;
    private JButton btnAgregarVenta;
    private JButton btnOrdenarVentas;
    private JComboBox<String> cmbOrdenamiento;
    private JTextArea areaVentas;

    private List<String> ventas;

    public InterfazUsuario() {
        super("Gestión y Análisis de Datos Multidimensionales");
        ventas = new ArrayList<>();
        initializeUI();
    }

    private void initializeUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(5, 5));

        // Panel de gestión de datos
        JPanel panelDatos = createDataManagementPanel();
        // Panel de ventas
        JPanel panelVentas = createSalesPanel();

        // Panel de resultados
        JPanel panelResultados = new JPanel();
        panelResultados.setLayout(new GridLayout(1, 2));
        areaPares = new JTextArea(10, 20);
        areaVentas = new JTextArea(10, 20);
        panelResultados.add(new JScrollPane(areaPares));
        panelResultados.add(new JScrollPane(areaVentas));

        // Adding to frame
        add(panelDatos, BorderLayout.WEST);
        add(panelVentas, BorderLayout.EAST);
        add(panelResultados, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createDataManagementPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));

        txtPrimerElemento = new JTextField();
        txtSegundoElemento = new JTextField();
        btnAgregar = new JButton("Agregar Pareja");
        btnEliminar = new JButton("Eliminar Pareja");
        btnMostrar = new JButton("Mostrar Parejas");

        panel.add(new JLabel("Primer Elemento (String):"));
        panel.add(txtPrimerElemento);
        panel.add(new JLabel("Segundo Elemento (Integer):"));
        panel.add(txtSegundoElemento);
        panel.add(btnAgregar);
        panel.add(btnEliminar);
        panel.add(btnMostrar);

        btnAgregar.addActionListener(this::agregarPareja);
        btnEliminar.addActionListener(this::eliminarPareja);
        btnMostrar.addActionListener(this::mostrarPares);

        return panel;
    }

    private JPanel createSalesPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));

        txtVenta = new JTextField();
        btnAgregarVenta = new JButton("Agregar Venta");
        btnOrdenarVentas = new JButton("Ordenar Ventas");
        cmbOrdenamiento = new JComboBox<>(new String[]{"Ascendente", "Descendente"});

        panel.add(new JLabel("Nueva Venta:"));
        panel.add(txtVenta);
        panel.add(btnAgregarVenta);
        panel.add(btnOrdenarVentas);
        panel.add(new JLabel("Ordenamiento:"));
        panel.add(cmbOrdenamiento);

        btnAgregarVenta.addActionListener(this::agregarVenta);
        btnOrdenarVentas.addActionListener(this::ordenarVentas);

        return panel;
    }

    private void agregarPareja(ActionEvent e) {
        String primerElemento = txtPrimerElemento.getText();
        Integer segundoElemento = Integer.parseInt(txtSegundoElemento.getText());
        areaPares.append(primerElemento + " - " + segundoElemento + "\n");
    }

    private void eliminarPareja(ActionEvent e) {
        areaPares.setText("");
    }

    private void mostrarPares(ActionEvent e) {
        // Simulate display logic
    }

    private void agregarVenta(ActionEvent e) {
        String venta = txtVenta.getText();
        if (!venta.isEmpty()) {
            ventas.add(venta);
            txtVenta.setText("");
            mostrarVentas();
        }
    }

    private void ordenarVentas(ActionEvent e) {
        boolean ascendente = cmbOrdenamiento.getSelectedItem().equals("Ascendente");
        ventas.sort(ascendente ? String::compareTo : (a, b) -> b.compareTo(a));
        mostrarVentas();
    }

    private void mostrarVentas() {
        areaVentas.setText("");
        ventas.forEach(v -> areaVentas.append(v + "\n"));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(InterfazUsuario::new);
    }
}

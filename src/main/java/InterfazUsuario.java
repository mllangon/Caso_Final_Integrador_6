import Gestion_Datos.GestorDatos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfazUsuario extends JFrame {
    private GestorDatos<String, Integer> gestorDatos;
    private JTextField txtPrimerElemento;
    private JTextField txtSegundoElemento;
    private JTextArea areaPares;
    private JButton btnAgregar;
    private JButton btnEliminar;
    private JButton btnMostrar;

    public InterfazUsuario() {
        super("Gestión de Pares");
        gestorDatos = new GestorDatos<>();
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());

        // Panel para entradas
        JPanel panelEntradas = new JPanel(new GridLayout(3, 2));
        panelEntradas.add(new JLabel("Primer Elemento (String):"));
        txtPrimerElemento = new JTextField();
        panelEntradas.add(txtPrimerElemento);
        panelEntradas.add(new JLabel("Segundo Elemento (Integer):"));
        txtSegundoElemento = new JTextField();
        panelEntradas.add(txtSegundoElemento);

        // Panel para botones
        JPanel panelBotones = new JPanel(new FlowLayout());
        btnAgregar = new JButton("Agregar Pareja");
        btnEliminar = new JButton("Eliminar Pareja");
        btnMostrar = new JButton("Mostrar Parejas");
        panelBotones.add(btnAgregar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnMostrar);

        // Area de texto para mostrar pares
        areaPares = new JTextArea(10, 30);
        areaPares.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaPares);

        add(panelEntradas, BorderLayout.NORTH);
        add(panelBotones, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        // Eventos de los botones
        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarPareja();
            }
        });
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eliminarPareja();
            }
        });
        btnMostrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarPares();
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void agregarPareja() {
        String primerElemento = txtPrimerElemento.getText();
        try {
            Integer segundoElemento = Integer.parseInt(txtSegundoElemento.getText());
            gestorDatos.agregarPareja(primerElemento, segundoElemento);
            txtPrimerElemento.setText("");
            txtSegundoElemento.setText("");
            JOptionPane.showMessageDialog(this, "Pareja agregada correctamente.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El segundo elemento debe ser un número entero.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarPareja() {
        String primerElemento = txtPrimerElemento.getText();
        try {
            Integer segundoElemento = Integer.parseInt(txtSegundoElemento.getText());
            gestorDatos.eliminarPareja(primerElemento, segundoElemento);
            txtPrimerElemento.setText("");
            txtSegundoElemento.setText("");
            JOptionPane.showMessageDialog(this, "Pareja eliminada correctamente.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El segundo elemento debe ser un número entero.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarPares() {
        areaPares.setText("Parejas actual:\n" + gestorDatos.mostrarPares());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new InterfazUsuario();
            }
        });
    }
}

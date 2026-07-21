package com.hackathon.agenda.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VentanaPrincipal extends JFrame {

    private JPanel mainPanel;

    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtTelefono;

    private JButton btnAgregar;
    private JButton btnLimpiar;
    private JButton btnBuscar;
    private JButton btnModificar;
    private JButton btnEliminar;
    private JButton btnVerContactos;

    private JTable tablaContactos;
    private DefaultTableModel modeloTabla;

    private JLabel lblEstado;
    private JLabel lblEspaciosLibres;

    public VentanaPrincipal() {
        configurarVentana();
        crearComponentes();
    }

    private void configurarVentana() {
        setTitle("Agenda Telefonica MVC");
        setSize(850, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

    private void crearComponentes() {
        mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel panelTitulo = crearPanelTitulo();
        JPanel panelFormulario = crearPanelFormulario();
        JPanel panelTabla = crearPanelTabla();
        JPanel panelEstado = crearPanelEstado();

        mainPanel.add(panelTitulo, BorderLayout.NORTH);
        mainPanel.add(panelFormulario, BorderLayout.WEST);
        mainPanel.add(panelTabla, BorderLayout.CENTER);
        mainPanel.add(panelEstado, BorderLayout.SOUTH);

        setContentPane(mainPanel);
    }

    private JPanel crearPanelTitulo() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel titulo = new JLabel("Agenda Telefonica", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 26));

        panel.add(titulo, BorderLayout.CENTER);

        return panel;
    }

    private JPanel crearPanelFormulario() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Gestion de contactos"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblNombre = new JLabel("Nombre:");
        JLabel lblApellido = new JLabel("Apellido:");
        JLabel lblTelefono = new JLabel("Telefono:");

        txtNombre = new JTextField(18);
        txtApellido = new JTextField(18);
        txtTelefono = new JTextField(18);

        btnAgregar = new JButton("Agregar");
        btnLimpiar = new JButton("Limpiar");
        btnBuscar = new JButton("Buscar");
        btnModificar = new JButton("Modificar telefono");
        btnEliminar = new JButton("Eliminar");
        btnVerContactos = new JButton("Ver contactos");

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(lblNombre, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(txtNombre, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(lblApellido, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(txtApellido, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(lblTelefono, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(txtTelefono, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(btnAgregar, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(btnLimpiar, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(btnBuscar, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(btnModificar, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(btnEliminar, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        panel.add(btnVerContactos, gbc);

        return panel;
    }

    private JPanel crearPanelTabla() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Lista de contactos"));

        modeloTabla = new DefaultTableModel(
                new Object[]{"Nombre", "Apellido", "Telefono"},
                0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaContactos = new JTable(modeloTabla);
        tablaContactos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(tablaContactos);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel crearPanelEstado() {
        JPanel panel = new JPanel(new GridLayout(2, 1));

        lblEstado = new JLabel("Sistema listo.");
        lblEstado.setForeground(new Color(0, 128, 0));

        lblEspaciosLibres = new JLabel("Espacios libres: 10");

        panel.add(lblEstado);
        panel.add(lblEspaciosLibres);

        return panel;
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public JTextField getTxtApellido() {
        return txtApellido;
    }

    public JTextField getTxtTelefono() {
        return txtTelefono;
    }

    public JButton getBtnAgregar() {
        return btnAgregar;
    }

    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public JButton getBtnModificar() {
        return btnModificar;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public JButton getBtnVerContactos() {
        return btnVerContactos;
    }

    public JTable getTablaContactos() {
        return tablaContactos;
    }

    public DefaultTableModel getModeloTabla() {
        return modeloTabla;
    }

    public JLabel getLblEstado() {
        return lblEstado;
    }

    public JLabel getLblEspaciosLibres() {
        return lblEspaciosLibres;
    }
}

package com.hackathon.agenda.controlador;

import com.hackathon.agenda.modelo.Agenda;
import com.hackathon.agenda.modelo.Contacto;
import com.hackathon.agenda.vista.VentanaPrincipal;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.List;

public class AgendaControlador {

    private final Agenda agenda;
    private final VentanaPrincipal vista;

    public AgendaControlador(Agenda agenda, VentanaPrincipal vista) {
        this.agenda = agenda;
        this.vista = vista;

        iniciarEventos();
        actualizarTabla();
        actualizarEspaciosLibres();
    }

    private void iniciarEventos() {
        vista.getBtnAgregar().addActionListener(this::agregarContacto);
        vista.getBtnBuscar().addActionListener(this::buscarContacto);
        vista.getBtnModificar().addActionListener(this::modificarTelefono);
        vista.getBtnEliminar().addActionListener(this::eliminarContacto);
        vista.getBtnLimpiar().addActionListener(e -> limpiarFormulario());

        vista.getTablaContactos().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                cargarDatosDesdeTabla();
            }
        });
    }

    private void agregarContacto(ActionEvent e) {
        try {
            String nombre = obtenerNombre();
            String apellido = obtenerApellido();
            String telefono = obtenerTelefono();

            Contacto contacto = new Contacto(nombre, apellido, telefono);

            agenda.anadirContacto(contacto);

            mostrarExito("Contacto agregado correctamente.");
            actualizarTabla();
            actualizarEspaciosLibres();
            limpiarFormulario();

        } catch (IllegalArgumentException | IllegalStateException ex) {
            mostrarError(ex.getMessage());
        } catch (Exception ex) {
            mostrarError("Ocurrió un error inesperado al agregar el contacto.");
        }
    }

    private void buscarContacto(ActionEvent e) {
        try {
            String nombre = obtenerNombre();
            String apellido = obtenerApellido();

            String telefono = agenda.buscarContacto(nombre, apellido);

            if (telefono != null) {
                vista.getTxtTelefono().setText(telefono);
                mostrarExito("Contacto encontrado.");
            } else {
                mostrarError("No existe un contacto con ese nombre y apellido.");
            }

        } catch (Exception ex) {
            mostrarError("Ocurrió un error inesperado al buscar el contacto.");
        }
    }

    private void modificarTelefono(ActionEvent e) {
        try {
            String nombre = obtenerNombre();
            String apellido = obtenerApellido();
            String nuevoTelefono = obtenerTelefono();

            agenda.modificarTelefono(nombre, apellido, nuevoTelefono);

            mostrarExito("Teléfono modificado correctamente.");
            actualizarTabla();
            limpiarFormulario();

        } catch (IllegalArgumentException ex) {
            mostrarError(ex.getMessage());
        } catch (Exception ex) {
            mostrarError("Ocurrió un error inesperado al modificar el contacto.");
        }
    }

    private void eliminarContacto(ActionEvent e) {
        try {
            String nombre = obtenerNombre();
            String apellido = obtenerApellido();
            String telefono = obtenerTelefono();

            Contacto contacto = new Contacto(nombre, apellido, telefono);

            boolean eliminado = agenda.eliminarContacto(contacto);

            if (eliminado) {
                mostrarExito("Contacto eliminado correctamente.");
                actualizarTabla();
                actualizarEspaciosLibres();
                limpiarFormulario();
            } else {
                mostrarError("No se encontró el contacto para eliminar.");
            }

        } catch (IllegalArgumentException ex) {
            mostrarError(ex.getMessage());
        } catch (Exception ex) {
            mostrarError("Ocurrió un error inesperado al eliminar el contacto.");
        }
    }

    private void cargarDatosDesdeTabla() {
        int filaSeleccionada = vista.getTablaContactos().getSelectedRow();

        if (filaSeleccionada >= 0) {
            String nombre = vista.getTablaContactos().getValueAt(filaSeleccionada, 0).toString();
            String apellido = vista.getTablaContactos().getValueAt(filaSeleccionada, 1).toString();
            String telefono = vista.getTablaContactos().getValueAt(filaSeleccionada, 2).toString();

            vista.getTxtNombre().setText(nombre);
            vista.getTxtApellido().setText(apellido);
            vista.getTxtTelefono().setText(telefono);

            mostrarExito("Contacto seleccionado.");
        }
    }

    private void actualizarTabla() {
        vista.getModeloTabla().setRowCount(0);

        List<Contacto> contactos = agenda.listarContactos();

        for (Contacto contacto : contactos) {
            Object[] fila = {
                    contacto.getNombre(),
                    contacto.getApellido(),
                    contacto.getTelefono()
            };

            vista.getModeloTabla().addRow(fila);
        }
    }

    private void actualizarEspaciosLibres() {
        vista.getLblEspaciosLibres().setText(
                "Espacios libres: " + agenda.espaciosLibres()
        );
    }

    private void limpiarFormulario() {
        vista.getTxtNombre().setText("");
        vista.getTxtApellido().setText("");
        vista.getTxtTelefono().setText("");
        vista.getTablaContactos().clearSelection();
        mostrarExito("Formulario limpio.");
    }

    private String obtenerNombre() {
        return vista.getTxtNombre().getText().trim();
    }

    private String obtenerApellido() {
        return vista.getTxtApellido().getText().trim();
    }

    private String obtenerTelefono() {
        return vista.getTxtTelefono().getText().trim();
    }

    private void mostrarError(String mensaje) {
        vista.getLblEstado().setForeground(Color.RED);
        vista.getLblEstado().setText(mensaje);
    }

    private void mostrarExito(String mensaje) {
        vista.getLblEstado().setForeground(new Color(0, 128, 0));
        vista.getLblEstado().setText(mensaje);
    }
}

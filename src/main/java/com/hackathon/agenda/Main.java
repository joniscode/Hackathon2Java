package com.hackathon.agenda;

import com.hackathon.agenda.controlador.AgendaControlador;
import com.hackathon.agenda.modelo.Agenda;
import com.hackathon.agenda.vista.VentanaPrincipal;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Agenda agenda = new Agenda();
            VentanaPrincipal vista = new VentanaPrincipal();
            new AgendaControlador(agenda, vista);
            vista.setVisible(true);
        });
    }
}

package com.hackathon.agenda.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Agenda {

    private final List<Contacto> contactos;
    private final int capacidadMaxima;

    // Constructor por defecto: 10 contactos
    public Agenda() {
        this(10);
    }

    // Constructor con capacidad configurable
    public Agenda(int capacidad) {
        this.contactos = new ArrayList<>();
        this.capacidadMaxima = capacidad;
    }

    // Validaciones para agregar un nuevo contacto
    public void anadirContacto(Contacto c) {
        if (agendaLlena()) {
            throw new IllegalStateException("La agenda esta llena. No se pueden agregar mas contactos.");
        }

        if (c == null) {
            throw new IllegalArgumentException("No ha ingresado un contacto valido.");
        }

        if (existeContacto(c)) {
            throw new IllegalArgumentException("El contacto ya se encuentra registrado.");
        }

        contactos.add(c);
    }

    // Verificacion de contacto existente
    public boolean existeContacto(Contacto c) {
        if (c == null) return false;
        return contactos.contains(c);
    }

    // Lista de contactos almacenados
    public List<Contacto> listarContactos() {
        List<Contacto> listaOrdenada = new ArrayList<>(contactos);
        Collections.sort(listaOrdenada);
        return listaOrdenada;
    }

    // Busqueda del contacto
    public String buscarContacto(String nombre, String apellido) {
        if (nombre == null || apellido == null) return null;

        for (Contacto c : contactos) {
            if (c.getNombre().equalsIgnoreCase(nombre.trim())
                    && c.getApellido().equalsIgnoreCase(apellido.trim())) {
                return c.getTelefono();
            }
        }

        return null;
    }

    // Eliminar un contacto
    public boolean eliminarContacto(Contacto c) {
        if (c == null) return false;
        return contactos.remove(c);
    }

    // Modificar telefono
    public void modificarTelefono(String nombre, String apellido, String nuevoTelefono) {

        if (nombre == null || apellido == null) {
            throw new IllegalArgumentException("Por favor ingresar nombre y apellido.");
        }

        Contacto contactoEncontrado = null;

        for (Contacto c : contactos) {
            if (c.getNombre().equalsIgnoreCase(nombre.trim())
                    && c.getApellido().equalsIgnoreCase(apellido.trim())) {

                contactoEncontrado = c;
                break;
            }
        }

        if (contactoEncontrado != null) {
            contactoEncontrado.setTelefono(nuevoTelefono);
        } else {
            throw new IllegalArgumentException("No se encontro el contacto.");
        }
    }

    public boolean agendaLlena() {
        return contactos.size() >= capacidadMaxima;
    }

    public int espaciosLibres() {
        return capacidadMaxima - contactos.size();
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }
}
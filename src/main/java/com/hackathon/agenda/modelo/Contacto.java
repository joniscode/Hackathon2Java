package com.hackathon.agenda.modelo;

import java.util.Objects;
import java.util.regex.Pattern;

public class Contacto implements Comparable<Contacto> {

    // Acepta: "+34 600 000 000", "+34600000000", "600 000 000", "600000000"
    private static final String TELEFONO_REGEX = "^(\\+\\d{1,3}\\s?)?\\d{3}\\s?\\d{3}\\s?\\d{3}$";
    private static final Pattern TELEFONO_PATTERN = Pattern.compile(TELEFONO_REGEX);

    private String nombre;
    private String apellido;
    private String telefono;

    public Contacto(String nombre, String apellido, String telefono) {
        setNombre(nombre);
        setApellido(apellido);
        setTelefono(telefono);
    }

    // ---------- Getters ----------

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    // ---------- Setters con validación ----------

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        this.nombre = nombre.trim();
    }

    public void setApellido(String apellido) {
        if (apellido == null || apellido.trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido no puede estar vacío.");
        }
        this.apellido = apellido.trim();
    }

    public void setTelefono(String telefono) {
        if (telefono == null || !TELEFONO_PATTERN.matcher(telefono.trim()).matches()) {
            throw new IllegalArgumentException(
                    "Formato de teléfono inválido. Ejemplos válidos: +34 600 000 000 o 600000000."
            );
        }
        this.telefono = telefono.trim();
    }

    // ---------- Igualdad basada en nombre + apellido  ----------

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contacto contacto = (Contacto) o;
        return nombre.equalsIgnoreCase(contacto.nombre)
                && apellido.equalsIgnoreCase(contacto.apellido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre.toLowerCase(), apellido.toLowerCase());
    }

    // ---------- Orden natural: apellido y luego nombre ----------

    @Override
    public int compareTo(Contacto otro) {
        int compararApellido = this.apellido.compareToIgnoreCase(otro.apellido);

        if (compararApellido != 0) {
            return compararApellido;
        }

        return this.nombre.compareToIgnoreCase(otro.nombre);
    }

    @Override
    public String toString() {
        return apellido + ", " + nombre + " - " + telefono;
    }
}
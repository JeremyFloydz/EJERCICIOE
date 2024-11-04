package es.jeremy.ejee;

import java.util.Objects;

/**
 * La clase Persona representa a una persona con un nombre, apellidos y edad.
 * Proporciona métodos para obtener y establecer sus atributos, así como para
 * comparar dos objetos Persona.
 */
public class Persona {
    private String nombre;    // Nombre de la persona
    private String apellidos; // Apellidos de la persona
    private int edad;        // Edad de la persona

    /**
     * Constructor de la clase Persona.
     *
     * @param nombre    El nombre de la persona.
     * @param apellidos Los apellidos de la persona.
     * @param edad      La edad de la persona.
     */
    public Persona(String nombre, String apellidos, int edad) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
    }

    // Getters

    /**
     * Obtiene el nombre de la persona.
     *
     * @return El nombre de la persona.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene los apellidos de la persona.
     *
     * @return Los apellidos de la persona.
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Obtiene la edad de la persona.
     *
     * @return La edad de la persona.
     */
    public int getEdad() {
        return edad;
    }

    // Setters

    /**
     * Establece el nombre de la persona.
     *
     * @param nombre El nuevo nombre de la persona.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Establece los apellidos de la persona.
     *
     * @param apellidos Los nuevos apellidos de la persona.
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * Establece la edad de la persona.
     *
     * @param edad La nueva edad de la persona.
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * Compara este objeto Persona con otro objeto.
     *
     * @param obj El objeto a comparar.
     * @return true si ambos objetos son iguales, false de lo contrario.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Verifica si son la misma instancia
        if (!(obj instanceof Persona)) return false; // Verifica si el objeto es una instancia de Persona
        Persona persona = (Persona) obj;
        return edad == persona.edad &&
                Objects.equals(nombre, persona.nombre) && // Maneja la comparación segura a null
                Objects.equals(apellidos, persona.apellidos); // Maneja la comparación segura a null
    }

    /**
     * Genera un código hash para el objeto Persona.
     *
     * @return El código hash de la persona.
     */
    @Override
    public int hashCode() {
        return Objects.hash(nombre, apellidos, edad);
    }
}

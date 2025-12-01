package org.empresa.facturasspring.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "empleados")
public class Empleado {

    @Id
    @NotBlank(message = "El DNI es obligatorio")
    @Column(nullable = false, unique = true)
    private String dni;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(nullable = false)
    private String nombre;

    @NotBlank(message = "El sexo es obligatorio")
    @Pattern(regexp = "^[MF]$", message = "El sexo debe ser M o F")
    @Column(nullable = false, length = 1)
    private String sexo;

    @NotNull(message = "La categoría es obligatoria")
    @Min(value = 1, message = "La categoría debe ser entre 1 y 10")
    @Max(value = 10, message = "La categoría debe ser entre 1 y 10")
    @Column(nullable = false)
    private Integer categoria;

    @NotNull(message = "Los años son obligatorios")
    @Min(value = 0, message = "Los años no pueden ser negativos")
    @Column(nullable = false)
    private Integer anyos;


    public Empleado() {}

    public Empleado(String dni, String nombre, String sexo, Integer categoria, Integer anyos) {
        this.dni = dni;
        this.nombre = nombre;
        this.sexo = sexo;
        this.categoria = categoria;
        this.anyos = anyos;
    }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }

    public Integer getCategoria() { return categoria; }
    public void setCategoria(Integer categoria) { this.categoria = categoria; }

    public Integer getAnyos() { return anyos; }
    public void setAnyos(Integer anyos) { this.anyos = anyos; }

    @Override
    public String toString() {
        return "Empleado{" +
                "dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", sexo='" + sexo + '\'' +
                ", categoria=" + categoria +
                ", anyos=" + anyos +
                '}';
    }
}
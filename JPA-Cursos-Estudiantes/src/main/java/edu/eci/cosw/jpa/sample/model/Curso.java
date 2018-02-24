package edu.eci.cosw.jpa.sample.model;
// Generated Mar 9, 2016 7:01:57 AM by Hibernate Tools 4.3.1


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Cursos generated by hbm2java
 */
@Entity
@Table(name = "CURSOS")
public class Curso implements java.io.Serializable {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "nemonico")
    private String nemonico;


    @ManyToMany
    @JoinTable(name="ESTUDIANTES_CURSOS",
            joinColumns=
            @JoinColumn(name="CURSOS_id", referencedColumnName="id"),
            inverseJoinColumns=
            @JoinColumn(name="ESTUDIANTES_codigo", referencedColumnName="codigo")
    )
    private Set<Estudiante> estudiantes = new HashSet<>(0);

    public Curso() {
    }


    public Curso(int id, String nombre, String nemonico) {
        this.id = id;
        this.nombre = nombre;
        this.nemonico = nemonico;
    }

    public Curso(int id, String nombre, String nemonico, Set<Estudiante> estudianteses) {
        this.id = id;
        this.nombre = nombre;
        this.nemonico = nemonico;
        this.estudiantes = estudianteses;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNemonico() {
        return this.nemonico;
    }

    public void setNemonico(String nemonico) {
        this.nemonico = nemonico;
    }

    public Set<Estudiante> getEstudianteses() {
        return this.estudiantes;
    }

    public void setEstudianteses(Set<Estudiante> estudianteses) {
        this.estudiantes = estudianteses;
    }


}



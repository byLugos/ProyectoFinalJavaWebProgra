package com.example.proyectofinalv2.persistence;

import com.example.proyectofinalv2.logic.Subject;

import java.util.List;

public interface DataBaseInterface<T> extends AutoCloseable{

    //METODOS PARA EL CRUD DE LAS ACTIVIDADES
    List<T> getActivities(); //LISTO
    T findActivity(String id); //LISTO
    int addActivities(T t); //LISTO
    void delete(String id); //LISTO
    void uptadte(T t);


    //METODOS PARA EL CRUD DE LAS MATERIAS
    List<Subject>getSubjects();
    Subject findSubject(String id);

    int addSubjects(Subject s);

    void deleteSubject(String id);



}

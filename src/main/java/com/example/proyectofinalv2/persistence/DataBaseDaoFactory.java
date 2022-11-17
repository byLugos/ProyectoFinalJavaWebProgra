package com.example.proyectofinalv2.persistence;

public class DataBaseDaoFactory {

    public DataBaseInterface createDataBaseDao(){
        return new DataBaseDaoImplements();
    }
}


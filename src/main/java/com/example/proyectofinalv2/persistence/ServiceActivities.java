package com.example.proyectofinalv2.persistence;

import com.example.proyectofinalv2.logic.Activity;

import java.util.List;

public class ServiceActivities {

    private DataBaseDaoImplements activityDaoImplements;

    public ServiceActivities() {
        activityDaoImplements = activityDaoImplements;
    }

    public List<Activity> getAll() {

        List<Activity> list = activityDaoImplements.getActivities();

        return list;
    }
    public boolean save(String... data) {
        String actId = data[0];
        String tipoAct = data[1];
        String nombreAct = data[2];
        String descripAct = data[3];
        int ponderado = Integer.parseInt(data[4]);
        String fecha = data[5];
        float califAct = Float.parseFloat(data[6]);
        String subId = data[7];
        return activityDaoImplements.addActivities(new Activity(actId,tipoAct,nombreAct,descripAct,ponderado,
                null, califAct,subId))>0;

    }
}

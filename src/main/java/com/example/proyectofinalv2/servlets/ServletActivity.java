package com.example.proyectofinalv2.servlets;
import com.example.proyectofinalv2.logic.Activity;
import com.example.proyectofinalv2.persistence.DataBaseDaoFactory;
import com.example.proyectofinalv2.persistence.ServiceActivities;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "ServletSubject", value = "/ServletSubject")

public class ServletActivity extends HttpServlet {

    private final DataBaseDaoFactory factory = new DataBaseDaoFactory();

    private ServiceActivities serviceActivities;

    public void init() throws ServletException {serviceActivities = new ServiceActivities();}

    //metodo para obtener los datos de la web
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/plain");

        PrintWriter out = response.getWriter();

        List<Activity> activities = serviceActivities.getAll();

        Gson gson = new Gson();

        out.print(gson.toJson( activities));


    }

    //Metodo para agregar participantes a la base de datos
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/json");

        String actId = request.getParameter("actId");// Es auto incremental pero no esta funcionando
        String tipoAct = request.getParameter("tipoAct");
        String nombreAct = request.getParameter("nombreAct");
        String descripAct = request.getParameter("descripAct");
        String ponderado = request.getParameter("ponderado");
        String fecha = request.getParameter("fecha");
        String califAct = request.getParameter("califAct");
        String subId = request.getParameter("subId");//El ID de la materia no de como obtenerlo

        SimpleDateFormat formatter = new SimpleDateFormat(fecha);
        Date fechaa;
        try {
            fechaa = formatter.parse(fecha);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Activity a = new Activity(actId, tipoAct, nombreAct, descripAct, Integer.parseInt(ponderado), fechaa, Float.parseFloat(califAct), subId);

        factory.createDataBaseDao().addActivities(a);


        try( PrintWriter out = response.getWriter()){
            if( serviceActivities.save(actId,tipoAct,nombreAct,descripAct,ponderado,fecha,califAct,subId)){
                out.print("{\"state\":true}");
            }else{
                out.print("{\"state\":false}");
            }
        }


    }

}
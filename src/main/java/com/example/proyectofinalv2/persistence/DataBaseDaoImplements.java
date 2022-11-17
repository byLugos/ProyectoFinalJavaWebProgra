package com.example.proyectofinalv2.persistence;
import com.example.proyectofinalv2.logic.Activity;
import com.example.proyectofinalv2.logic.Subject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseDaoImplements implements DataBaseInterface<Activity> {

    /**
     * Atributo estático que contiene el DRIVER de MySql
     */
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    /**
     * Atributo estático que contiene la URL del servidor
     */
    private final String URL = "jdbc:mysql://localhost:3306/sistema_notas?allowPublicKeyRetrieval=true&useSSL=false";

    /**
     * Atributo que debe ser cambiado según el usuario, para la base de datos
     */
    private final String USER = "root";

    /**
     * Atributo que debe ser cambiado segun la contraseña del usuario
     */
    private final String PASSWORD = "7355608byLugos";

    /**
     * Metodo que retorna en una lista, las actividades en la base de datos
     * @return lista que contiene las actividades de la base de datos
     */
    @Override
    public List<Activity> getActivities() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (
                Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                Statement statement = connection.createStatement();
        ) {
            String query = "SELECT * FROM actividades";
            ResultSet resultSet = statement.executeQuery(query);
            List<Activity> list = new ArrayList<>();
            while (resultSet.next()) {
                getSubjects();
                String actId = resultSet.getString(1);
                String tipoAct = resultSet.getString("tipoAct");
                String nombreAct = resultSet.getString("nombreAct");
                String descript = resultSet.getString("descripAct");
                int ponderado = resultSet.getInt(5);
                Date fecha = resultSet.getDate(6);
                float calificacion = resultSet.getFloat(7);
                String subId = resultSet.getString(8);
                list.add(new Activity(actId, tipoAct, nombreAct,
                        descript, ponderado, fecha, calificacion, subId
                ));
            }
            return list;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    /**
     * Método para encontrar una actividad accediendo por el ID
     * @param id Como acceso para buscar
     * @return Objeto de tipo actividad
     */
    @Override
    public Activity findActivity(String id) {
        if (getActivities() != null) {
            for (Activity ac : getActivities()) {
                if (ac.getActId().equals(id)) {
                    return ac;
                }
            }
        }
        return null;
    }

    /**
     * Método para agregar una activdad a la base de datos de actividades
     * @param activity Como parámetro a agregar a la base de datos
     * @return Entero
     */
    @Override
    public int addActivities(Activity activity) {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection connection =
                     DriverManager.getConnection(URL, USER, PASSWORD);
        ) {
            Statement statement = connection.createStatement();
            String actId = activity.getActId();
            String tipoAct = activity.getTipoAct();
            String nombreAct = activity.getNombreAct();
            String descripAct = activity.getDescripcionAct();
            int ponderado = activity.getPonderadoAct();
            Date fecha = (Date) activity.getFechaAct();
            float calificacion = activity.getCalificacionAct();
            String idSub = activity.getIdMateria();

            final String query = "INSERT INTO actividades VALUES(" +
                    "" + "'" + actId + "','" + tipoAct + "','" + nombreAct +
                    "','" + descripAct + "','" + ponderado + "','" + fecha + "','" +
                    calificacion + "','" +
                    idSub+ "')";

            System.out.println(query);

            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    /**
     * Método para eliminar una fila de la base de datos de actividades según el ID
     * @param id Como parámetro para eliminar la fila, según el ID
     */
    @Override
    public void delete(String id) {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection connection =
                     DriverManager.getConnection(URL, USER, PASSWORD);
        ) {
            Statement statement = connection.createStatement();
            String query = "DELETE from actividades WHERE actId=" + id;
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Método para "actualizar", "realizar cambios" en la base de datos de actividades
     * según el objeto
     * @param activity Cómo objeto, para ser cambiado en la base de datos
     */
    //FALTA
    @Override
    public void uptadte(Activity activity) {

    }



    //METODOS PARA EL CRUD DE LAS MATERIAS----------------------------------

    /**
     * Método que retorna en una lista, las materias de la base de datos
     * @return Una lista con la información de materias de la base de datos
     */
    //LISTO
    @Override
    public List<Subject> getSubjects() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (
                Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                Statement statement = connection.createStatement();
        ) {
            String query = "SELECT * FROM materia";
            ResultSet resultSet = statement.executeQuery(query);
            List<Subject> list = new ArrayList<>();
            while (resultSet.next()) {
                String subId = resultSet.getString(1);
                String subName = resultSet.getString("materiaNombre");
                float finalNote = resultSet.getFloat(3);
                list.add(new Subject(subId, subName, finalNote));
            }
            return list;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    /**
     * Método para buscar una materia en la base de datos materia, según el ID
     * @param id Como parámetro de búsqueda
     * @return Un objeto de tipo Materia
     */
    //LISTO
    @Override
    public Subject findSubject(String id) {
        if (getSubjects() != null) {
            for (Subject su : getSubjects()) {
                if (su.getSubjId().equals(id)) {
                    return su;
                }
            }
        }
        return null;
    }

    /**
     * Método para agregar una materia a la base de datos materia
     * @param s Como parámetro un objeto que se agrega a la base de datos
     * @return Entero
     */

    @Override
    public int addSubjects(Subject s) {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection connection =
                     DriverManager.getConnection(URL, USER, PASSWORD);
        ) {
            Statement statement = connection.createStatement();
            int subId = Integer.parseInt(s.getSubjId());
            String nameSub = s.getNameSubj();
            float finalNote = s.getNotaFinalSubj();

            final String query = "INSERT INTO materia VALUES(" +
                    "" + "'" + subId + "','" + nameSub + "','" + finalNote+ "')";

            System.out.println(query);
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    /**
     * Método para eliminar una fila de la base de datos de materia
     * @param id Como parámetro de búsqueda para eliminar la fila
     */
    @Override
    public void deleteSubject(String id) {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection connection =
                     DriverManager.getConnection(URL, USER, PASSWORD);
        ) {
            Statement statement = connection.createStatement();
            String query = "DELETE from materia WHERE materia_id=" + id;
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    /**
     * No se hizo uso de este método
     * @throws Exception
     */
    @Override
    public void close() throws Exception {

    }

}

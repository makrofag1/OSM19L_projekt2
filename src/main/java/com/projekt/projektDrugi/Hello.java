package com.projekt.projektDrugi;



import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.websocket.server.PathParam;
import javax.xml.crypto.Data;
import java.sql.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Controller
public class Hello {

    public static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    public static final String JDBC_URL = "jdbc:derby:baza";
    @RequestMapping("/addandshow")
    public String addAndShow() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        Connection connection = DriverManager.getConnection(JDBC_URL);
        connection.createStatement().execute("insert into DOCTORS(NAME, SURNAME, LOGIN, PASSWORD) values "+
                "('Kamil','Kowalski','loginlekarz','haslo123')");
        ResultSet resultSet = connection.createStatement().executeQuery("select NAME from DOCTORS");

        ResultSetMetaData rsmd = resultSet.getMetaData();
        System.out.println("querying SELECT * FROM XXX");
        int columnsNumber = rsmd.getColumnCount();
        while (resultSet.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) System.out.print(",  ");
                String columnValue = resultSet.getString(i);
                System.out.print(columnValue + " " + rsmd.getColumnName(i));
            }
            System.out.println("");
        }
        connection.close();
        return "chyba OK";
    }

    @RequestMapping(path ="/add", method = RequestMethod.POST)
    public String add() throws SQLException, ClassNotFoundException {
        Database.addPatient("abc","cdf","Badanie bazy pomysłów",123456789);
        //<a href="@{/addPatient}" onclick="return confirm('Are you sure you want to delete this item?');">Delete</a>

        return "redirect:/show";
    }

    @RequestMapping(path ="/modifyPatient", method = RequestMethod.GET)
    public String modifyPatient(Model model,@RequestParam(value = "name") String  name,@RequestParam(value = "id") String  id,
                                @RequestParam(value = "surname") String  surname,
                                @RequestParam(value = "pesel") String  pesel,@RequestParam(value = "examination") String  examination,
                                @RequestParam(value = "dosage") String  dosage, @RequestParam(value = "observation") String  observation)
            throws SQLException, JSONException, ClassNotFoundException, InterruptedException {
        System.out.println(id);
        Integer idInt = Integer.parseInt(id);
        Database.updatePatientNameById(idInt,name);
        Database.updatePatientSurnameById(idInt,surname);
        Database.updatePatientPeselById(idInt,Integer.parseInt(pesel));
        Database.updatePatientExaminationNameById(idInt,examination);
        Database.updatePatientDosageById(idInt,Float.parseFloat(dosage));
        Database.updatePatientObservationById(idInt,observation);
        //String url = "redirect:/show/selectedRow?id="+id;
        return "/";
    }

    @RequestMapping(path ="/show/selectedRow", method = RequestMethod.GET)
    public String selectedRow(Model model,@RequestParam(value = "id") String  id) throws SQLException, JSONException, ClassNotFoundException {
        List<Patient> listPatients = Database.getPatientsList();
        List<Examination> listExaminations = Database.getExaminationsList();
        List<Patient> patient = Database.getPatientsListById(Integer.parseInt(id));
        model.addAttribute("patients",listPatients);
        model.addAttribute("examinations",listExaminations);
        model.addAttribute("isSelected",true);
        model.addAttribute("patientSelected",patient);
        return "widokLekarzaZWybranymPacjentem";
    }

    @RequestMapping(path = "/show", method = RequestMethod.GET)
    public String show(Model model) throws SQLException, JSONException, ClassNotFoundException {
        List<Patient> listPatients = Database.getPatientsList();
        List<Examination> listExaminations = Database.getExaminationsList();
        model.addAttribute("patients",listPatients);
        model.addAttribute("examinations",listExaminations);
        return "widokLekarza";
    }

    @RequestMapping("/")
    public String nothing(){
        return "nothing";
    }

    @RequestMapping("/delete")
    public String delete() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        Connection connection = DriverManager.getConnection(JDBC_URL);
        connection.createStatement().execute("delete from PATIENTS");
        return "redirect:/show";
    }



}
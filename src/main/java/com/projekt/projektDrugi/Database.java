package com.projekt.projektDrugi;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

public class Database {

    private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String JDBC_URL = "jdbc:derby:src/main/BazaDanych";

    public static void addPatient(String name, String surname, String examinationName, Float dosage, String observation, Integer PESEL) throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        Connection connection = DriverManager.getConnection(JDBC_URL);
        String query = "insert into PATIENTS(NAME, SURNAME, EXAMINATION_NAME, DOSAGE, OBSERVATION, PESEL, GROUP_TYPE) values "+
                "(?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,name);
        preparedStatement.setString(2,surname);
        preparedStatement.setString(3,examinationName);
        preparedStatement.setFloat(4,dosage);
        preparedStatement.setString(5,observation);
        preparedStatement.setInt(6,PESEL);
        preparedStatement.setString(7,getRandomPatientGroup());
        preparedStatement.execute();
        connection.close();
    }
    public static void addPatient(String name, String surname, String examinationName, Integer PESEL) throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        Connection connection = DriverManager.getConnection(JDBC_URL);
        String query = "insert into PATIENTS(NAME, SURNAME, EXAMINATION_NAME, PESEL, GROUP_TYPE) values "+
                "(?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,name);
        preparedStatement.setString(2,surname);
        preparedStatement.setString(3,examinationName);
        preparedStatement.setInt(4,PESEL);
        preparedStatement.setString(5,getRandomPatientGroup());
        preparedStatement.execute();
        connection.close();
    }
    public static void removePatient(Integer Pesel) throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        Connection connection = DriverManager.getConnection(JDBC_URL);
        String query = "DELETE FROM PATIENTS WHERE PATIENTS.PESEL = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,Pesel);
        preparedStatement.execute();
        connection.close();
    }
    public static JSONArray getPatientJSONArray(Integer patientPesel) throws ClassNotFoundException, SQLException, JSONException {
        Class.forName(DRIVER);
        Connection connection = DriverManager.getConnection(JDBC_URL);
        String query = "SELECT * FROM PATIENTS WHERE PATIENTS.PESEL = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,patientPesel);
        ResultSet resultSet = preparedStatement.executeQuery();

        JSONArray jsonArray = new JSONArray();
        while (resultSet.next()) {
            int total_rows = resultSet.getMetaData().getColumnCount();
            JSONObject object = new JSONObject();
            for (int i =0; i < total_rows; i++){
                object.put(resultSet.getMetaData().getColumnLabel(i+1).toLowerCase(),resultSet.getObject(i+1));
            }
            jsonArray.put(object);
        }
        connection.close();
        return jsonArray;
    }

    public static void updatePatientName(Integer Pesel, String newName) throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        Connection connection = DriverManager.getConnection(JDBC_URL);
        String query = "UPDATE PATIENTS SET NAME = ? WHERE PESEL = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,newName);
        preparedStatement.setInt(2,Pesel);
        preparedStatement.execute();
        connection.close();
    }
    public static void updatePatientSurname(Integer Pesel, String newSurname) throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        Connection connection = DriverManager.getConnection(JDBC_URL);
        String query = "UPDATE PATIENTS SET SURNAME = ? WHERE PESEL = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,newSurname);
        preparedStatement.setInt(2,Pesel);
        preparedStatement.execute();
        connection.close();
    }
    public static void updatePatientExaminationName(Integer Pesel, String newExaminationName) throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        Connection connection = DriverManager.getConnection(JDBC_URL);
        String query = "UPDATE PATIENTS SET EXAMINATION_NAME = ? WHERE PESEL = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,newExaminationName);
        preparedStatement.setInt(2,Pesel);
        preparedStatement.execute();
        connection.close();
    }
    public static void updatePatientDosage(Integer Pesel, Float newDosage) throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        Connection connection = DriverManager.getConnection(JDBC_URL);
        String query = "UPDATE PATIENTS SET DOSAGE = ? WHERE PESEL = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setFloat(1,newDosage);
        preparedStatement.setInt(2,Pesel);
        preparedStatement.execute();
        connection.close();
    }
    public static void updatePatientObservation(Integer Pesel, String newObservation) throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        Connection connection = DriverManager.getConnection(JDBC_URL);
        String query = "UPDATE PATIENTS SET OBSERVATION = ? WHERE PESEL = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,newObservation);
        preparedStatement.setInt(2,Pesel);
        preparedStatement.execute();
        connection.close();
    }
    public static void updatePatientPesel(Integer Pesel, Integer newPesel) throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        Connection connection = DriverManager.getConnection(JDBC_URL);
        String query = "UPDATE PATIENTS SET PESEL = ? WHERE PESEL = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,newPesel);
        preparedStatement.setInt(2,Pesel);
        preparedStatement.execute();
        connection.close();
    }

    public static void updatePatientNameById(Integer id, String newName) throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        Connection connection = DriverManager.getConnection(JDBC_URL);
        String query = "UPDATE PATIENTS SET NAME = ? WHERE PATIENT_ID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,newName);
        preparedStatement.setInt(2,id);
        preparedStatement.execute();
        connection.close();
    }
    public static void updatePatientSurnameById(Integer id, String newSurname) throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        Connection connection = DriverManager.getConnection(JDBC_URL);
        String query = "UPDATE PATIENTS SET SURNAME = ? WHERE PATIENT_ID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,newSurname);
        preparedStatement.setInt(2,id);
        preparedStatement.execute();
        connection.close();
    }
    public static void updatePatientExaminationNameById(Integer id, String newExaminationName) throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        Connection connection = DriverManager.getConnection(JDBC_URL);
        String query = "UPDATE PATIENTS SET EXAMINATION_NAME = ? WHERE PATIENT_ID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,newExaminationName);
        preparedStatement.setInt(2,id);
        preparedStatement.execute();
        connection.close();
    }
    public static void updatePatientDosageById(Integer id, Float newDosage) throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        Connection connection = DriverManager.getConnection(JDBC_URL);
        String query = "UPDATE PATIENTS SET DOSAGE = ? WHERE PATIENT_ID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setFloat(1,newDosage);
        preparedStatement.setInt(2,id);
        preparedStatement.execute();
        connection.close();
    }
    public static void updatePatientObservationById(Integer id, String newObservation) throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        Connection connection = DriverManager.getConnection(JDBC_URL);
        String query = "UPDATE PATIENTS SET OBSERVATION = ? WHERE PATIENT_ID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,newObservation);
        preparedStatement.setInt(2,id);
        preparedStatement.execute();
        connection.close();
    }
    public static void updatePatientPeselById(Integer id, Integer newPesel) throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        Connection connection = DriverManager.getConnection(JDBC_URL);
        String query = "UPDATE PATIENTS SET PESEL = ? WHERE PATIENT_ID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,newPesel);
        preparedStatement.setInt(2,id);
        preparedStatement.execute();
        connection.close();
    }

    private static String getRandomPatientGroup(){
        int max = 1;
        int min = 0;
        int x = (int)(Math.random()*((max-min)+1))+min;
        if (x==0){
            return "Placebo";
        }
        return "Lek";
    }
    public static List getPatientsList() throws ClassNotFoundException, SQLException, JSONException {
        Class.forName(DRIVER);
        Connection connection = DriverManager.getConnection(JDBC_URL);
        //String query = "SELECT * FROM PATIENTS WHERE PATIENTS.PESEL = ?";
        String query = "SELECT * FROM PATIENTS";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        //preparedStatement.setInt(1,patientPesel);
        ResultSet resultSet = preparedStatement.executeQuery();
        List list = Patient.createList(resultSet);
        connection.close();
        return list;
    }
    public static List getPatientsListById(Integer id) throws ClassNotFoundException, SQLException, JSONException {
        Class.forName(DRIVER);
        Connection connection = DriverManager.getConnection(JDBC_URL);
        String query = "SELECT * FROM PATIENTS WHERE PATIENT_ID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        List list = Patient.createList(resultSet);
        connection.close();
        return list;
    }


    public static void addExamination(String examinationName, Integer doctorID, Date dateOfStart, Date dateOfEnd) throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        Connection connection = DriverManager.getConnection(JDBC_URL);
        String query = "insert into EXAMINATIONS(EXAMINATION_NAME, DOCTOR_ID, DATE_OF_START, DATE_OF_END) values "+
                "(?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,examinationName);
        preparedStatement.setInt(2,doctorID);
        preparedStatement.setDate(3,dateOfStart);
        preparedStatement.setDate(4,dateOfEnd);
        preparedStatement.execute();
        connection.close();
    }
    public static void addExamination(String examinationName, Integer doctorID, Date dateOfStart) throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        Connection connection = DriverManager.getConnection(JDBC_URL);
        String query = "insert into EXAMINATIONS(EXAMINATION_NAME, DOCTOR_ID, DATE_OF_START) values "+
                "(?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,examinationName);
        preparedStatement.setInt(2,doctorID);
        preparedStatement.setDate(3,dateOfStart);
        preparedStatement.execute();
        connection.close();
    }
    public static void updateExaminationExaminationName(String examinationName, String newExaminationName) throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        Connection connection = DriverManager.getConnection(JDBC_URL);
        String query = "UPDATE EXAMINATIONS SET EXAMINATION_NAME = ? WHERE EXAMINATION_NAME = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,newExaminationName);
        preparedStatement.setString(2,examinationName);
        preparedStatement.execute();
        query = "UPDATE PATIENTS SET EXAMINATION_NAME = ? WHERE EXAMINATION_NAME = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,newExaminationName);
        preparedStatement.setString(2,examinationName);
        preparedStatement.execute();
        connection.close();
    }
    public static void updateExaminationDateOfStart(String examinationName, Date newDateOfStart) throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        Connection connection = DriverManager.getConnection(JDBC_URL);
        String query = "UPDATE EXAMINATIONS SET DATE_OF_START = ? WHERE EXAMINATION_NAME = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setDate(1,newDateOfStart);
        preparedStatement.setString(2,examinationName);
        preparedStatement.execute();
        connection.close();
    }
    public static void updateExaminationDateOfEnd(String examinationName, Date newDateOfEnd) throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        Connection connection = DriverManager.getConnection(JDBC_URL);
        String query = "UPDATE EXAMINATIONS SET DATE_OF_END = ? WHERE EXAMINATION_NAME = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setDate(1,newDateOfEnd);
        preparedStatement.setString(2,examinationName);
        preparedStatement.execute();
        connection.close();
    }
    public static void removeExamination(String examinationName) throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        Connection connection = DriverManager.getConnection(JDBC_URL);
        String query = "DELETE FROM EXAMINATIONS WHERE EXAMINATIONS.EXAMINATION_NAME = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,examinationName);
        preparedStatement.execute();
        connection.close();
    };
    public static JSONArray getExaminationJSONArray(String examinationName) throws ClassNotFoundException, SQLException, JSONException {
        Class.forName(DRIVER);
        Connection connection = DriverManager.getConnection(JDBC_URL);
        String query = "SELECT * FROM EXAMINATIONS WHERE EXAMINATIONS.EXAMINATION_NAME = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,examinationName);
        ResultSet resultSet = preparedStatement.executeQuery();

        JSONArray jsonArray = new JSONArray();
        while (resultSet.next()) {
            int total_rows = resultSet.getMetaData().getColumnCount();
            JSONObject object = new JSONObject();
            for (int i =0; i < total_rows; i++){
                object.put(resultSet.getMetaData().getColumnLabel(i+1).toLowerCase(),resultSet.getObject(i+1));
            }
            jsonArray.put(object);
        }
        connection.close();
        return jsonArray;
    }
    public static List getExaminationsList() throws ClassNotFoundException, SQLException, JSONException {
        Class.forName(DRIVER);
        Connection connection = DriverManager.getConnection(JDBC_URL);
        //String query = "SELECT * FROM PATIENTS WHERE PATIENTS.PESEL = ?";
        String query = "SELECT * FROM EXAMINATIONS";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        //preparedStatement.setInt(1,patientPesel);
        ResultSet resultSet = preparedStatement.executeQuery();
        List list = Examination.createList(resultSet);
        connection.close();
        return list;
    }


    public static void addDoctor(String name, String surname, String login, String password, Integer pesel) throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        Connection connection = DriverManager.getConnection(JDBC_URL);
        String query = "insert into DOCTORS(NAME, SURNAME, LOGIN, PASSWORD, PESEL) values "+
                "(?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,name);
        preparedStatement.setString(2,surname);
        preparedStatement.setString(3,login);
        preparedStatement.setString(4,password);
        preparedStatement.setInt(5,pesel);
        preparedStatement.execute();
        connection.close();
    }
    public static void addDoctor(String name, String surname, Integer pesel) throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        Connection connection = DriverManager.getConnection(JDBC_URL);
        String query = "insert into DOCTORS(NAME, SURNAME, PESEL) values "+
                "(?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,name);
        preparedStatement.setString(2,surname);
        preparedStatement.setInt(3,pesel);
        preparedStatement.execute();
        connection.close();
    }
    public static void updateDoctorName(Integer Pesel, String newName) throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        Connection connection = DriverManager.getConnection(JDBC_URL);
        String query = "UPDATE DOCTORS SET NAME = ? WHERE PESEL = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,newName);
        preparedStatement.setInt(2,Pesel);
        preparedStatement.execute();
        connection.close();
    }
    public static void updateDoctorSurname(Integer Pesel, String newName) throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        Connection connection = DriverManager.getConnection(JDBC_URL);
        String query = "UPDATE DOCTORS SET SURNAME = ? WHERE PESEL = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,newName);
        preparedStatement.setInt(2,Pesel);
        preparedStatement.execute();
        connection.close();
    }
    public static void updateDoctorLogin(Integer Pesel, String newName) throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        Connection connection = DriverManager.getConnection(JDBC_URL);
        String query = "UPDATE DOCTORS SET LOGIN = ? WHERE PESEL = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,newName);
        preparedStatement.setInt(2,Pesel);
        preparedStatement.execute();
        connection.close();
    }
    public static void updateDoctorPassword(Integer Pesel, String newName) throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        Connection connection = DriverManager.getConnection(JDBC_URL);
        String query = "UPDATE DOCTORS SET PASSWORD = ? WHERE PESEL = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,newName);
        preparedStatement.setInt(2,Pesel);
        preparedStatement.execute();
        connection.close();
    }
    public static void updateDoctorPesel(Integer Pesel, String newName) throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        Connection connection = DriverManager.getConnection(JDBC_URL);
        String query = "UPDATE DOCTORS SET PESEL = ? WHERE PESEL = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,newName);
        preparedStatement.setInt(2,Pesel);
        preparedStatement.execute();
        connection.close();
    }
    public static void removeDoctor(Integer Pesel) throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        Connection connection = DriverManager.getConnection(JDBC_URL);
        String query = "DELETE FROM DOCTORS WHERE DOCTORS.PESEL = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,Pesel);
        preparedStatement.execute();
        connection.close();
    }
    public static JSONArray getDoctorJSONArray(Integer pesel) throws ClassNotFoundException, SQLException, JSONException {
        Class.forName(DRIVER);
        Connection connection = DriverManager.getConnection(JDBC_URL);
        String query = "SELECT * FROM DOCTORS WHERE DOCTORS.PESEL = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,pesel);
        ResultSet resultSet = preparedStatement.executeQuery();

        JSONArray jsonArray = new JSONArray();
        while (resultSet.next()) {
            int total_rows = resultSet.getMetaData().getColumnCount();
            JSONObject object = new JSONObject();
            for (int i =0; i < total_rows; i++){
                object.put(resultSet.getMetaData().getColumnLabel(i+1).toLowerCase(),resultSet.getObject(i+1));
            }
            jsonArray.put(object);
        }
        connection.close();
        return jsonArray;
    }
    public static List getDoctorsList() throws ClassNotFoundException, SQLException, JSONException {
        Class.forName(DRIVER);
        Connection connection = DriverManager.getConnection(JDBC_URL);
        //String query = "SELECT * FROM PATIENTS WHERE PATIENTS.PESEL = ?";
        String query = "SELECT * FROM DOCTORS";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        //preparedStatement.setInt(1,patientPesel);
        ResultSet resultSet = preparedStatement.executeQuery();
        List list = Doctor.createList(resultSet);
        connection.close();
        return list;
    }

}


package com.projekt.projektDrugi;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Patient{
    public Integer patientId;
    public String name;
    public String surname;
    public String examinationName;
    public Float dosage;
    public String observation;
    public Integer pesel;
    public String group;

    public Patient(Integer patientId, String name, String surname, String examinationName, Float dosage, String observation, Integer pesel, String group) {
        this.patientId = patientId;
        this.name = name;
        this.surname = surname;
        this.examinationName = examinationName;
        this.dosage = dosage;
        this.observation = observation;
        this.pesel = pesel;
        this.group = group;
    }

    public static List createList(ResultSet resultSet) throws SQLException {
        List<Patient> patients = new ArrayList<>();
        while (resultSet.next()) {
            Integer patientId = resultSet.getInt("PATIENT_ID");
            String name = resultSet.getString("NAME");
            String surname = resultSet.getString("SURNAME");
            String examinationName = resultSet.getString("EXAMINATION_NAME");
            Float dosage = resultSet.getFloat("DOSAGE");
            String observation = resultSet.getString("OBSERVATION");
            Integer pesel = resultSet.getInt("PESEL");
            String group = resultSet.getString("GROUP_TYPE");
            Patient patient = new Patient(patientId,name,surname,examinationName,dosage,observation,pesel,group);
            patients.add(patient);
        }
        return patients;
    }
}

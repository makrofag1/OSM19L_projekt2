package com.projekt.projektDrugi;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Doctor {
    public Integer doctorId;
    public String name;
    public String surname;
    public String login;
    public String password;
    public Integer pesel;

    public Doctor(Integer doctorId, String name, String surname, String login, String password, Integer pesel) {
        this.doctorId = doctorId;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.pesel = pesel;
    }


    public static List createList(ResultSet resultSet) throws SQLException {
        List<Doctor> doctors = new ArrayList<>();
        while (resultSet.next()) {
            Integer doctorId = resultSet.getInt("DOCTOR_ID");
            String name = resultSet.getString("NAME");
            String surname = resultSet.getString("SURNAME");
            String login = resultSet.getString("LOGIN");
            String password = resultSet.getString("PASSWORD");
            Integer pesel = resultSet.getInt("PESEL");
            Doctor doctor = new Doctor(doctorId,name,surname,login,password,pesel);
            doctors.add(doctor);
        }
        return doctors;
    }
}

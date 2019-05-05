package com.projekt.projektDrugi;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Examination{
    public Integer examinationId;
    public String examinationName;
    public Integer doctorId;
    public Date dateOfStart;
    public Date dateOfEnd;

    public Examination(Integer examinationId, String examinationName, Integer doctorId, Date dateOfStart, Date dateOfEnd) {
        this.examinationId = examinationId;
        this.examinationName = examinationName;
        this.doctorId = doctorId;
        this.dateOfStart = dateOfStart;
        this.dateOfEnd = dateOfEnd;
    }


    public static List createList(ResultSet resultSet) throws SQLException {
        List<Examination> examinations = new ArrayList<>();
        while (resultSet.next()) {
            Integer examinationId = resultSet.getInt("EXAMINATION_ID");
            String examinationName = resultSet.getString("EXAMINATION_NAME");
            Integer doctorId = resultSet.getInt("DOCTOR_ID");
            Date dateOfStart = resultSet.getDate("DATE_OF_START");
            Date dateOfEnd = resultSet.getDate("DATE_OF_END");
            Examination examination = new Examination(examinationId,examinationName,doctorId,dateOfStart,dateOfEnd);
            examinations.add(examination);
        }
        return examinations;
    }
}

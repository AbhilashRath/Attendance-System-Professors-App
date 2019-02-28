package com.halo.AttenGoTeacher;

public class Student {
    private String Name;
    private String RollNo;
    private Integer Chemistry;


    public Student() {
        //empty constructor needed
    }

    public Student(String Name, String RollNo, Integer Chemistry) {
        this.Name = Name;
        this.RollNo = RollNo;
        this.Chemistry = Chemistry;



    }

    public String getName() {
        return Name;
    }

    public String getRollNo() {
        return RollNo;
    }
    public Integer getChemistry() {
        return Chemistry;
    }

}

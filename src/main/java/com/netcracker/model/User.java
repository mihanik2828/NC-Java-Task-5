package com.netcracker.model;


public class User {



    private String name;
    private String surname;
    private String secondName;
    private int age;
    private String email;
    private double salary;
    private String workPlacel;

    public User() {
    }

    public User(String surname, String name, String secondName, int age, double salary, String email, String workPlacel) {
        this.surname = surname;
        this.name = name;
        this.secondName = secondName;
        this.age = age;
        this.salary = salary;
        this.email = email;
        this.workPlacel = workPlacel;
    }

    public User(String[] str){
        name=str[0];
        surname=str[1];
        secondName=str[2];
        age=Integer.parseInt(str[3]);
        email=str[4];
        salary=Double.parseDouble(str[5]);
        workPlacel=str[6];
    }


    public String toText() {
        return name + '&' + surname +  '&'+ secondName + '&'+ age +'&'+ email +'&'+ salary + '&'+ workPlacel ;
    }

    @Override
    public String toString() {
        return "User{" +
                "Surname='" + surname + '\'' +
                ", Name='" + name + '\'' +
                ", SecondName='" + secondName + '\'' +
                ", Age=" + age +
                ", Salary=" + salary +
                ", Email='" + email + '\'' +
                ", Workplace='" + workPlacel + '\'' +
                '}';
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWorkPlacel() {
        return workPlacel;
    }

    public void setWorkPlacel(String workPlacel) {
        this.workPlacel = workPlacel;
    }
}

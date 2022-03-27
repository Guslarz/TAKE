/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Guslarz
 */
public class Student {
    
    private String name;
    private String surname;
    private double gradesAvg;
    
    public Student(String name, String surname, double gradesAvg) {
        this.name = name;
        this.surname = surname;
        this.gradesAvg = gradesAvg;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @param surname the surname to set
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * @return the gradesAvg
     */
    public double getGradesAvg() {
        return gradesAvg;
    }

    /**
     * @param gradesAvg the gradesAvg to set
     */
    public void setGradesAvg(double gradesAvg) {
        this.gradesAvg = gradesAvg;
    }
    
}

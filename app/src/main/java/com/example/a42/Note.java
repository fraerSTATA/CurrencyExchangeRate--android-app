package com.example.a42;

import java.io.Serializable;
import java.sql.Time;

public class Note implements Serializable {
    private String CharCode;
    private Integer Nominal;
    private String Name;
    private double Value;
    private double Previous;
    private double coef;


    public Note(String CharCode, Integer Nominal,String Name,double Value,double Previous,double coef) {
        this.CharCode = CharCode;
        this.Nominal = Nominal;
        this.Name = Name;
        this.Value  = Value;
        this.Previous = Previous;
        this.coef = coef;
    }

    public double getPrevious() {
        return Previous;
    }

    public void setPrevious(double prev) {
        this.Previous = prev;
    }

    public void setValue(double value) {
        Value = value;
    }

    public double getValue() {
        return Value;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getName() {
        return Name;
    }

    public void setNominal(Integer nominal) {
        Nominal = nominal;
    }

    public Integer getNominal() {
        return Nominal;
    }

    public void setCharCode(String charCode) {
        CharCode = charCode;
    }

    public String getCharCode() {
        return CharCode;
    }

    public void setCoef(double coef) {
        this.coef = coef;
    }

    public double getCoef() {
        return coef;
    }
}

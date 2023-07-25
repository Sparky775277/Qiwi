package main;

import java.util.Date;

public class Currency {
    private long NumCode;

    private Long Nominal;

    private String Name;

    private String firstName;

    private String CharCode;

    private Date date;

    private String ID;


    private String Value;

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }


    public long getNumCode() {
        return NumCode;
    }

    public void setNumCode(long numCode) {
        NumCode = numCode;
    }

    public Long getNominal() {
        return Nominal;
    }

    public void setNominal(Long nominal) {
        Nominal = nominal;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCharCode() {
        return CharCode;
    }

    public void setCharCode(String charCode) {
        CharCode = charCode;
    }

    @Override
    public String toString() {
        return "CharCode='" + CharCode + '\'' +
                ", Value=" + Value
                ;
    }
}

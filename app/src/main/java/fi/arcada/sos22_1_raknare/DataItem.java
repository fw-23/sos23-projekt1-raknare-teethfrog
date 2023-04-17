package fi.arcada.sos22_1_raknare;

import android.text.Editable;

public class DataItem {

    private String name;
    private double value;

    public DataItem(String name, double value) {
        this.name = name;
        this.value = value;
    }

    public DataItem(Editable text, Editable text1) {
    }


    public String getName() {
        return name;
    }
    public double getValue() {
        return value;
    }

}

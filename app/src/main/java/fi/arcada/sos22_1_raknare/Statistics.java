package fi.arcada.sos22_1_raknare;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class Statistics {
    public static double calcMin(ArrayList<Double> values)  {
        double currentNmbr = values.get(0);
        for (int i = 0; i < values.size(); i++) {
            if(currentNmbr > values.get(i))
                currentNmbr = values.get(i);
        }
        return currentNmbr;
    }

    public static double calcMax(ArrayList<Double> values)  {
        double currentNmbr = values.get(0);
        for (int i = 0; i < values.size(); i++) {
            if(currentNmbr < values.get(i))
                currentNmbr = values.get(i);
        }
        return currentNmbr;
    }

    public static double calcAverage(ArrayList<Double> values)  {
        double sum = 0;
        double average = 0;
        for (int i = 0; i < values.size(); i++) {
            sum += values.get(i);
        }
        average = sum/values.size();
        return average;
    }

    public static ArrayList<Double> sortValues(ArrayList<Double> values) {
        ArrayList<Double> sorted = new ArrayList<>(values);
        Collections.sort(sorted);
        return sorted;
    }

    public static double calcMedian(ArrayList<Double> values) {
        ArrayList<Double> sorted = sortValues(values);
        int middle = sorted.size()/2;
        double median = sorted.get(middle);
        if (sorted.size() % 2 == 0) {
            median = (sorted.get(middle) + sorted.get(middle-1)) / 2;
        }
        return median;
    }

    public static ArrayList<Double> calcMode(ArrayList<Double> values) {
        ArrayList<Double> currentMode = new ArrayList<>();
        int maxDupCount = 0;
        for (int i = 0; i < values.size(); i++) {
            double value = values.get(i);
            int count = 1;
            for (int j = i + 1; j < values.size(); j++) {
                if (values.get(j) == value) count++;
                if (count > maxDupCount) {
                    currentMode.clear();
                    currentMode.add(value);
                    maxDupCount = count;
                } else if (count == maxDupCount) {
                    currentMode.add(value);
                }
            }
        }
        return currentMode;
    }
    public static double calcStdev(ArrayList<Double> values) {
        double average = calcAverage(values);
        double sumOfDiff = 0;
        for (int i = 0; i < values.size(); i++) {
            sumOfDiff += Math.pow(values.get(i)-average, 2);
        }
        double variance = sumOfDiff / values.size();

        return Math.sqrt(variance);
    }
}

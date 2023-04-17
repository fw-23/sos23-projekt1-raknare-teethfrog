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
        if (values.size() == 1) {
            currentMode.add(values.get(0));
        } else {
            for (int i = 0; i < values.size(); i++) {
                double value = values.get(i);
                int count = 1;
                for (int j = i + 1; j < values.size(); j++) {
                    if (values.get(j) == value) {
                        count++;
                    }
                    if (count > maxDupCount) {
                        currentMode.clear();
                        currentMode.add(value);
                        maxDupCount = count;
                    } else if (count == maxDupCount) {
                        currentMode.add(value);
                    }
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

    public static double calcLQ(ArrayList<Double> values) {
        ArrayList<Double> sorted = sortValues(values);
        int n = values.size();
        double LQ;
        if(n >= 4) {
            if(n%2==0) {
                double LQval1 = values.get(n/4);
                double LQval2 = values.get((n+1)/4);
                LQ = (LQval1+LQval2)/2;

            } else {
                LQ = values.get((n+1)/4);
            }
        } else {
            LQ = Double.parseDouble("Provide 4 or more values to calculate quartiles.");
        }
        return LQ;
    }

    public static double calcUQ(ArrayList<Double> values) {
        ArrayList<Double> sorted = sortValues(values);
        int n = values.size();
        double UQ;
        if(n >= 4) {
            if (n % 2 == 0) {
                double HQval1 = values.get((3 * (n)) / 4);
                double HQval2 = values.get((3 * (n + 1)) / 4);
                UQ = (HQval1 + HQval2) / 2;

            } else {
                UQ = values.get((3 * (n + 1)) / 4);
            }
        } else {
            UQ = Double.parseDouble("Provide 4 or more values to calculate quartiles.");
        }
        return UQ;
    }

    public static double calcQR(ArrayList<Double> values) {
        ArrayList<Double> sorted = sortValues(values);
        double LQ = calcLQ(values);
        double UQ = calcUQ(values);
        double QR;

        if (sorted.size() >= 4) {
            if (LQ > UQ) {
                double temp = LQ;
                LQ = UQ;
                UQ = temp;
            }
            QR = UQ - LQ;
        } else {
            QR = Double.parseDouble("Provide 4 or more values to calculate quartiles.");
        }
        return QR;
    }
}

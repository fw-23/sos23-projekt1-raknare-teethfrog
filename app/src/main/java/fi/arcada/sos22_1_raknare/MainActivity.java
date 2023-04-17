package fi.arcada.sos22_1_raknare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView outputText;
    TextView inputDesc;
    TextView inputVal;
    RecyclerView recyclerView;

    ArrayList<DataItem> dataItems = new ArrayList<>();
    ArrayList<Double> values = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        outputText = findViewById(R.id.outCalc);
        recyclerView = findViewById(R.id.dataItemsRecyclerView);

        CustomAdapter adapter = new CustomAdapter(this, dataItems);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public void Statistics(View view) {
        ArrayList<Double> mode = Statistics.calcMode(values);

        double lowerQuartile, upperQuartile, interRangeQuartile;
        if (values.size() >= 4) {
            lowerQuartile = Statistics.calcLQ(values);
            upperQuartile = Statistics.calcUQ(values);
            interRangeQuartile = Statistics.calcQR(values);
        } else {
            lowerQuartile = Double.NaN;
            upperQuartile = Double.NaN;
            interRangeQuartile = Double.NaN;
        }

        outputText.setText(String.format("Min: %.2f\nMax: %.2f\nAverage: %.2f\nMedian: %.2f\nMode: %s\nStandard Deviation: %.2f\nLower Quartile: %s\nUpper Quartile: %s\nInter Range Quartile: %s",
                Statistics.calcMin(values),
                Statistics.calcMax(values),
                Statistics.calcAverage(values),
                Statistics.calcMedian(values),
                mode.get(0),
                Statistics.calcStdev(values),
                Double.isNaN(lowerQuartile) ? "Not enough data" : String.format("%.2f", lowerQuartile),
                Double.isNaN(upperQuartile) ? "Not enough data" : String.format("%.2f", upperQuartile),
                Double.isNaN(interRangeQuartile) ? "Not enough data" : String.format("%.2f", interRangeQuartile)
        ));
    }



    public void AddData(View view) {
        EditText desc = findViewById(R.id.inpDesc);
        EditText val = findViewById(R.id.inpVal);

        String descText = desc.getText().toString();
        double valText = Double.parseDouble(val.getText().toString());

        dataItems.add(new DataItem(descText, valText));
        values.add(valText);

        CustomAdapter adapter = new CustomAdapter(this, dataItems);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


}
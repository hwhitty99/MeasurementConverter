package com.whittaker.measurementconverter;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
        implements OnEditorActionListener{

    private TextView unit1Label;
    private TextView unit2Label;
    private EditText unit1EditText;
    private TextView unit2TextView;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = findViewById(R.id.radioGroup);
        unit1Label = findViewById(R.id.unit1Label);
        unit2Label = findViewById(R.id.unit2Label);
        unit1EditText = findViewById(R.id.unit1EditText);
        unit2TextView = findViewById(R.id.unit2TextView);

        unit1EditText.setOnEditorActionListener(this);
    }

    //converts given value and displays the converted number
    //based on the selected radio button
    public void convert(View v) {

        int radioId = radioGroup.getCheckedRadioButtonId();
        double conversionRatio;

        //sets the two unit labels and conversion ratio based
        //on the selected radio button
        switch (radioId) {
            case R.id.radioButton1:
                unit1Label.setText(R.string.milesR);
                unit2Label.setText(R.string.kiloR);
                conversionRatio = 1.6093;
                break;
            case R.id.radioButton2:
                unit1Label.setText(R.string.kiloR);
                unit2Label.setText(R.string.milesR);
                conversionRatio = 0.6214;
                break;
            case R.id.radioButton3:
                unit1Label.setText(R.string.inchR);
                unit2Label.setText(R.string.centR);
                conversionRatio = 2.54;
                break;
            case R.id.radioButton4:
                unit1Label.setText(R.string.centR);
                unit2Label.setText(R.string.inchR);
                conversionRatio = 0.3937;
                break;
            default:
                unit1Label.setText("Unit 1");
                unit2Label.setText("Unit 2");
                conversionRatio = 0;
        }

        String unit1String = unit1EditText.getText().toString();
        double unit1Double;
        double unit2Double;

        if (unit1String.equals("")) {
            unit1Double = 0;
        } else {
            unit1Double = Double.parseDouble(unit1String);
        }

        //converts number in unit 1 units to unit 2 units
        unit2Double = unit1Double * conversionRatio;
        String unit2String = Double.toString(unit2Double);

        //displays converted number
        unit2TextView.setText(unit2String);

    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE ||
                actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
            convert(v);
        }
        return false;
    }

    //displays converted nuber when device is rotated
    @Override
    public void onResume() {
        super.onResume();
        convert(unit1EditText);
    }
}

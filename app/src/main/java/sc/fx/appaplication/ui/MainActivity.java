package sc.fx.appaplication.ui;

import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import sc.fx.appaplication.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.v("MainActivity", "onCreate");

        EditText editText = findViewById(R.id.input_text_main);
        Button getText = findViewById(R.id.buttonCalc);
        TextView textView = findViewById(R.id.textView);
        getText.setOnClickListener(v -> {
            switchActivities();
        });



    }


    private void switchActivities() {
        Intent switchActivityIntent = new Intent(this, CalculatorActivity.class);
        startActivity(switchActivityIntent);
    }


}
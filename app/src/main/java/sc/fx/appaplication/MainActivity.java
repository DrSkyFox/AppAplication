package sc.fx.appaplication;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.v("MainActivity", "onCreate");

        EditText editText = findViewById(R.id.input_text_main);
        Button getText = findViewById(R.id.button);
        TextView textView = findViewById(R.id.textView);
        getText.setOnClickListener(v -> {
            String text = editText.getText().toString();
            textView.setText(text);
        });

    }


}
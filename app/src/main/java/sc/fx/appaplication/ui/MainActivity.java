package sc.fx.appaplication.ui;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import sc.fx.appaplication.R;
import sc.fx.appaplication.model.ThemeRepositoryImpl;
import sc.fx.appaplication.model.enums.Theme;
import sc.fx.appaplication.model.interfaces.ThemeRepository;

public class MainActivity extends AppCompatActivity {

    private ThemeRepository themeRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        themeRepository = ThemeRepositoryImpl.getInstance(getApplicationContext());
        setTheme(themeRepository.getSavedTheme().getThemeRes());

        Log.v("MainActivity", "onCreate");

        Button toCalculate = findViewById(R.id.to_Calc);
        Button toTheme = findViewById(R.id.to_Theme);
        Button toFirstLess = findViewById(R.id.to_FirstLesson);


        toCalculate.setOnClickListener(v -> {
            MainActivity.this.startActivity(new Intent(MainActivity.this, CalculatorActivity.class));
        });

        toFirstLess.setOnClickListener(v -> {
            MainActivity.this.startActivity(new Intent(MainActivity.this, FirstLessonActivity.class));
        });

        ActivityResultLauncher<Intent> themeLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == Activity.RESULT_OK) {
                Intent intent = result.getData();

                Theme selectedTheme = (Theme) intent.getSerializableExtra(ThemeActivity.EXTRA_THEME);

                themeRepository.saveTheme(selectedTheme);

                recreate();
            }

        });

        findViewById(R.id.to_Theme).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ThemeActivity.class);
                intent.putExtra(ThemeActivity.EXTRA_THEME, themeRepository.getSavedTheme());

                themeLauncher.launch(intent);
            }
        });

    }


//    private void switchActivities() {
//        Intent switchActivityIntent = new Intent(this, CalculatorActivity.class);
//        startActivity(switchActivityIntent);
//    }


}
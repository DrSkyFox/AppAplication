package sc.fx.appaplication.ui;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import sc.fx.appaplication.R;
import sc.fx.appaplication.model.CalculateOperation;
import sc.fx.appaplication.model.ThemeRepositoryImpl;
import sc.fx.appaplication.model.enums.Theme;
import sc.fx.appaplication.model.enums.TypeOperation;
import sc.fx.appaplication.model.interfaces.ThemeRepository;
import sc.fx.appaplication.ui.interfaces.ICalculateView;
import sc.fx.appaplication.ui.presenters.CalculatePresenter;

import java.util.HashMap;
import java.util.Map;


public class CalculatorActivity extends AppCompatActivity implements ICalculateView {

    private TextView resultView;

    private CalculatePresenter calculatePresenter;

    private ThemeRepository themeRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        resultView = findViewById(R.id.calc_view);

        Map<Integer, Integer> digitsButtonMap = new HashMap<Integer, Integer>();
        digitsMap(digitsButtonMap);

        themeRepository = ThemeRepositoryImpl.getInstance(this);
        setTheme(themeRepository.getSavedTheme().getThemeRes());


        calculatePresenter = new CalculatePresenter(this, new CalculateOperation());

        View.OnClickListener digitsClickListener = v -> calculatePresenter.onDigitPressed(digitsButtonMap.get(v.getId()));
        setDigitsButtonListener(digitsClickListener);

        Map<Integer, TypeOperation> operationsButtonMap = new HashMap<>();
        operationMap(operationsButtonMap);

        View.OnClickListener operationsClickListener = v -> calculatePresenter.onOperationPressed(operationsButtonMap.get(v.getId()));
        setOperationButtonsListener(operationsClickListener);


        findViewById(R.id.key_dot).setOnClickListener(v -> calculatePresenter.onDotPressed());

        findViewById(R.id.key_doCalc).setOnClickListener(v -> calculatePresenter.onCalcPressed());

        findViewById(R.id.key_doClear).setOnClickListener(v -> calculatePresenter.onClearPressed());


        ActivityResultLauncher<Intent> themeLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == Activity.RESULT_OK) {
                Intent intent = result.getData();
                Theme selectedTheme = (Theme) intent.getSerializableExtra(SelectTheme.EXTRA_THEME);
                themeRepository.saveTheme(selectedTheme);
                recreate();
            }
        });


    }

    private void setOperationButtonsListener(View.OnClickListener operationsClickListener) {
        findViewById(R.id.key_plus).setOnClickListener(operationsClickListener);
        findViewById(R.id.key_minus).setOnClickListener(operationsClickListener);
        findViewById(R.id.key_multiply).setOnClickListener(operationsClickListener);
        findViewById(R.id.key_divide).setOnClickListener(operationsClickListener);
    }

    private void operationMap(Map<Integer, TypeOperation> operationsButtonMap) {
        operationsButtonMap.put(R.id.key_plus, TypeOperation.ADD);
        operationsButtonMap.put(R.id.key_minus, TypeOperation.SUBTRACT);
        operationsButtonMap.put(R.id.key_multiply, TypeOperation.MULTIPLY);
        operationsButtonMap.put(R.id.key_divide, TypeOperation.DIVIDE);
    }

    private void setDigitsButtonListener(View.OnClickListener digitsClickListener) {
        findViewById(R.id.key_0).setOnClickListener(digitsClickListener);
        findViewById(R.id.key_1).setOnClickListener(digitsClickListener);
        findViewById(R.id.key_2).setOnClickListener(digitsClickListener);
        findViewById(R.id.key_3).setOnClickListener(digitsClickListener);
        findViewById(R.id.key_4).setOnClickListener(digitsClickListener);
        findViewById(R.id.key_5).setOnClickListener(digitsClickListener);
        findViewById(R.id.key_6).setOnClickListener(digitsClickListener);
        findViewById(R.id.key_7).setOnClickListener(digitsClickListener);
        findViewById(R.id.key_8).setOnClickListener(digitsClickListener);
        findViewById(R.id.key_9).setOnClickListener(digitsClickListener);
    }

    private void digitsMap(Map<Integer, Integer> digitsButtonMap) {
        digitsButtonMap.put(R.id.key_0,0);
        digitsButtonMap.put(R.id.key_1,1);
        digitsButtonMap.put(R.id.key_2,2);
        digitsButtonMap.put(R.id.key_3,3);
        digitsButtonMap.put(R.id.key_4,4);
        digitsButtonMap.put(R.id.key_5,5);
        digitsButtonMap.put(R.id.key_6,6);
        digitsButtonMap.put(R.id.key_7,7);
        digitsButtonMap.put(R.id.key_8,8);
        digitsButtonMap.put(R.id.key_9,9);
    }

    @Override
    public void showResult(String result) {
        resultView.setText(result);
    }

}
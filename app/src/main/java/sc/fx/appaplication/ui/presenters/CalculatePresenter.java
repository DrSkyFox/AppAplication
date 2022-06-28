package sc.fx.appaplication.ui.presenters;

import sc.fx.appaplication.model.enums.TypeOperation;
import sc.fx.appaplication.model.interfaces.ICalculateOperations;
import sc.fx.appaplication.ui.interfaces.ICalculateView;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Objects;


public class CalculatePresenter {

    private final DecimalFormat formatter = new DecimalFormat("#.#######################");
    private final ICalculateView calculateView;
    private ICalculateOperations calculateOperation;

    private double argOne;

    private double argTwo;

    private Boolean dotPressed = false;
    private TypeOperation typeOperation;

    public CalculatePresenter(ICalculateView calculateView, ICalculateOperations calculateOperation) {
        this.calculateView = calculateView;
        this.calculateOperation = calculateOperation;
    }

    public void onDigitPressed(Integer digit) {
        if(Objects.isNull(typeOperation)) {
            argOne = setDigit(argOne, digit);
            showFormatted(argOne);
        } else {
            argTwo = setDigit(argTwo, digit);
            showFormatted(argTwo);
        }
    }

    private double setDigit(double currentDigit, Integer digit) {
        int countNumAfterDot = 0;
        if(!dotPressed) {
            currentDigit = currentDigit * 10 + digit;
        } else {
            countNumAfterDot= BigDecimal.valueOf(currentDigit).scale();
            if(countNumAfterDot == 1 && checkZero(currentDigit)) {
                currentDigit = currentDigit + 0.1 * digit;
            } else {
                currentDigit = currentDigit + Math.pow(0.1, (countNumAfterDot+1)) * digit;
            }
        }
        return currentDigit;
    }

    private boolean checkZero(double currentDigit) {
        int temp = (int) currentDigit;
        if(currentDigit/temp == 1.0) {
            return true;
        }
        return false;
    }

    public void onOperationPressed(TypeOperation typeOperation) {
        if(typeOperation != null) {
            argOne = calculateOperation.perform(argOne, argTwo, typeOperation);
            showFormatted(argOne);
        }
        argTwo = 0.0;
        dotPressed = false;
        this.typeOperation = typeOperation;
    }

    public void onDotPressed() {
        dotPressed = true;
    }

    public void onCalcPressed() {
        if(typeOperation == null) {
            return;
        }
        argOne = calculateOperation.perform(argOne, argTwo, typeOperation);
        argTwo = 0.0;
        typeOperation = null;
        dotPressed = false;
        showFormatted(argOne);
    }


    private void showFormatted(double value) {
        calculateView.showResult(formatter.format(value));
    }

    public void onClearPressed() {
        argOne = 0.0;
        argTwo = 0.0;
        dotPressed = false;
        showFormatted(0.0);
    }
}

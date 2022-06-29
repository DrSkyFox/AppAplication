package sc.fx.appaplication.model;

import sc.fx.appaplication.model.enums.TypeOperation;
import sc.fx.appaplication.model.interfaces.ICalculateOperations;

public class CalculateOperation implements ICalculateOperations {

    @Override
    public double perform(double x, double y, TypeOperation typeOperation) {
        switch (typeOperation) {
            case ADD:
                return add(x,y);
            case SUBTRACT:
                return subtract(x,y);
            case MULTIPLY:
                return multiply(x,y);
            case DIVIDE:
                return divide(x,y);
        }
        return 0.0;
    }

    private double add(double x, double y) {
        return x + y;
    }

    private double subtract(double x, double y) {
        return x - y;
    }

    private double multiply(double x, double y) {
        return x * y;
    }

    private double divide(double x, double y) {
        if(y != 0) {
            return x / y;
        } else return 0.0;
    }
}

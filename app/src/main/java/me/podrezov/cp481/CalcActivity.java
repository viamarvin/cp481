package me.podrezov.cp481;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.lang.Math;

public class CalcActivity extends AppCompatActivity {
    TextView resultField;
    Double operand = null;
    String numberField = "";
    String fullNumber = "";
    String lastOperation = "=";
    Boolean divisionByZero = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calc);

        resultField = (TextView) findViewById(R.id.resultField);
        resultField.setText("");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("OPERATION", lastOperation);
        if (operand != null) {
            outState.putDouble("OPERAND", operand);
        }

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        lastOperation = savedInstanceState.getString("OPERAND");
        operand = savedInstanceState.getDouble("OPERAND");
        resultField.setText(operand.toString());
    }

    public void onNumberClick(View view) {
        Button button = (Button) view;
        numberField = button.getText().toString();
        fullNumber = fullNumber.toString() + numberField.toString();

        if (divisionByZero) {
            resultField.setText("");
            divisionByZero = false;
        }

        resultField.append(numberField);
        if (lastOperation.equals("=") && operand != null) {
            operand = null;
        }
    }

    public void onOperationClick(View view) {
        Button button = (Button) view;
        String op = button.getText().toString();
        String test = null;
        Integer index;

        if (divisionByZero) {
            resultField.setText("");
            divisionByZero = false;
        }

        test = resultField.getText().toString();
        if (test.length() > 0) {
            if (fullNumber.equals("0") && lastOperation.equals("/")) {
                onClearClick(view);
                resultField.setText(R.string.division_by_zero);
                divisionByZero = true;

                lastOperation = "=";
            } else {
                if (fullNumber.equals("") && !lastOperation.equals("=")) {
                    index = test.lastIndexOf(lastOperation);
                    if (index != -1) {
                        test = test.substring(0, index - 1);
                        test = test + ' ' + op + ' ';
                    }

                    resultField.setText(test);
                } else {
                    if (!op.equals("=")) {
                        resultField.append(' ' + op + ' ');
                    }
                }

                if (fullNumber.length() > 0) {
                    fullNumber = fullNumber.replace(',', '.');
                    try {
                        performOperation(Double.valueOf(fullNumber), op);
                    } catch (NumberFormatException ex) {
                        numberField = "";
                        fullNumber = "";
                    }
                }

                lastOperation = op;
            }
        }
    }

    public void onClearClick(View view) {
        operand = null;

        resultField.setText("");
        numberField = "";
        divisionByZero = false;
        fullNumber = "";
    }

    private void performOperation(Double number, String operation) {
        Double mod = null;
        Integer div = null;
        String str = null;

        if (operand == null) {
            operand = number;
        } else {
            if (lastOperation.equals("=")) {
                lastOperation = operation;
            }

            switch (lastOperation) {
                case "=":
                    operand = number;
                    break;
                case "/":
                    if (number == 0) {
                        operand = 0.0;
                    } else {
                        operand = operand / number;
                    }
                    break;
                case "*":
                    operand = operand * number;
                    break;
                case "-":
                    operand = operand - number;
                    break;
                case "+":
                    operand = operand + number;
                    break;
                case "mod":
                    mod = operand % number;
                    div = (int) (operand / number);

                    operand = div.doubleValue();
                    break;
            }
        }

        if (operation.equals("=")) {
            if (lastOperation.equals("mod")) {
                str = operand.toString() + " (" + String.format("%.1f", mod) + ")";
            } else {
                str = operand.toString();
            }

            resultField.setText(str.replace('.', ','));
        }

        fullNumber = "";
        numberField = "";
    }
}

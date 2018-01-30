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
    TextView operandField;
    EditText numberField;
    Double operand = null;
    String lastOperation = "=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calc);

        resultField = (TextView) findViewById(R.id.resultField);
        operandField = (TextView) findViewById(R.id.operandField);
        numberField = (EditText) findViewById(R.id.numberField);
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
        numberField.append(button.getText());

        if (lastOperation.equals("=") && operand != null) {
            operand = null;
        }
    }

    public void onOperationClick(View view) {
        Button button = (Button) view;
        String op = button.getText().toString();
        String number = numberField.getText().toString();

        if (number.length() > 0) {
            number = number.replace(',', '.');
            try {
                performOperation(Double.valueOf(number), op);
            } catch (NumberFormatException ex) {
                numberField.setText("");
            }
        }

        lastOperation = op;
    }

    public void onClearClick(View view) {
        operand = null;

        resultField.setText("");
        numberField.setText("");
        operandField.setText("");
    }

    private void performOperation(Double number, String operation) {
        Long intNumber = null;
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
                    operand = operand % number;
                    break;
                case "div":
                    intNumber =  Math.round(operand / number);
                    operand = intNumber.doubleValue();
                    break;
            }
        }

        resultField.setText(operand.toString().replace('.', ','));
        numberField.setText("");
        operandField.setText(lastOperation);
    }
}

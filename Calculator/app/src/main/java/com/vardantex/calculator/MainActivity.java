package com.vardantex.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText result;
    private EditText newNumber;
    private TextView displayOperation;

    //Variables to hold the operand calculations
    private String pendingOperation = "=";
   //Operand are the numbers that are being calculated through the operatos sent to the result
    private Double operand1 = null;
    private Double operand2 = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set the id objects
        result = (EditText) findViewById(R.id.result);
        newNumber = (EditText) findViewById(R.id.newNumber);
        displayOperation = (TextView) findViewById(R.id.operation);

        //Create the buttons for the calculator
        Button button0 = (Button) findViewById(R.id.button0);
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);
        Button button5 = (Button) findViewById(R.id.button5);
        Button button6 = (Button) findViewById(R.id.button6);
        Button button7 = (Button) findViewById(R.id.button7);
        Button button8 = (Button) findViewById(R.id.button8);
        Button button9 = (Button) findViewById(R.id.button9);
        Button buttonDecimal = (Button) findViewById(R.id.buttonDecimal);

        //Operation Buttons
        Button buttonMultiply = (Button) findViewById(R.id.buttonMultiply);
        Button buttonMinus = (Button) findViewById(R.id.buttonMinus);
        Button buttonEqual = (Button) findViewById(R.id.buttonEqual);
        Button buttonDivide = (Button) findViewById(R.id.buttonDivide);
        Button buttonPlus = (Button) findViewById(R.id.buttonPlus);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Create a button b in the onclick
                Button b = (Button) v;
                //Cast the new value of the selected button into newNumber
                newNumber.append(b.getText().toString());

            }
        };// listener

        button0.setOnClickListener(listener);
        button1.setOnClickListener(listener);
        button2.setOnClickListener(listener);
        button3.setOnClickListener(listener);
        button4.setOnClickListener(listener);
        button5.setOnClickListener(listener);
        button6.setOnClickListener(listener);
        button7.setOnClickListener(listener);
        button8.setOnClickListener(listener);
        button9.setOnClickListener(listener);
        buttonDecimal.setOnClickListener(listener);


        //Create a second listener for the operations
        View.OnClickListener opListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Set a button b to connect to View v
                Button b = (Button) v;
                String op = b.getText().toString();
                String value = newNumber.getText().toString();
                //Check if the value length returned nothing
               try {
                   Double doubleValue = Double.valueOf(value);
                    performOperation(doubleValue, op);

               } catch (NumberFormatException e) {
                    newNumber.setText("");

                }
                pendingOperation = op;
                displayOperation.setText(pendingOperation);

            }
        };

        //Set the operation buttons to opListener
         buttonEqual.setOnClickListener(opListener);
         buttonMultiply.setOnClickListener(opListener);
         buttonMinus.setOnClickListener(opListener);
         buttonPlus.setOnClickListener(opListener);
         buttonDivide.setOnClickListener(opListener);
    }

    //Create the perform Operation method for the button operators
    private void performOperation(Double value, String operation) {

        if(null == operand1){
            //Connect operand1 to value of (Double)
            operand1 = value;
        } else {

                //Check if pendingOperation (=) was clicked and set to method operation
                if(pendingOperation.equals("=")){
                    pendingOperation = operation;
                    
                }
                //Once = is set, add the switch statements to implement the other operators + - * / 
                switch (pendingOperation) {
                    case "=":
                        operand1 = operand2;
                        break;
                    case "/":
                        if (value == 0){
                            operand1 = 0.0;
                        } else {
                            operand1 /= value;
                        }
                        break;
                    case "*":
                        operand1 *= value;
                        break;
                    case "+":
                        operand1 += value;
                        break;
                    case"-":
                        operand1 -= value;
                        break;

                }// switch

        
            result.setText(operand1.toString());
            newNumber.setText("");


        }

    }
}
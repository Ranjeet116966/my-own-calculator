package com.example.calculator;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import org.mariuszgromada.math.mxparser.*;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText display;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       display = findViewById(R.id.input);
       display.setShowSoftInputOnFocus(false); //default keyboard no longer pops up after invoking this method

       display.setOnClickListener(new View.OnClickListener() {

           //clearing the default text whenever user input something
           @Override
           public void onClick(View view) {
               //if resource file string is equal to the display string
               if(getString(R.string.display).equals(display.getText().toString())){
                   display.setText("");
               }
           }

    });


    }

    private void updateText(String strToAdd){
        String oldString = display.getText().toString();
        int cursorPosition = display.getSelectionStart(); //to get the cursor position on display
        String leftString = oldString.substring(0,cursorPosition); //(i,j-1) position
        String rightString = oldString.substring(cursorPosition);
        if(getString(R.string.display).equals(display.getText().toString())){
            display.setText(strToAdd);
            display.setSelection(cursorPosition+1);
        }else{
            display.setText(String.format("%s%s%s",leftString,strToAdd,rightString));
            display.setSelection(cursorPosition+1); //set the cursor position
        }
    }

    public void zeroBtn(View view){
        updateText("0");
    }

    public void oneBtn(View view){
        updateText("1");
    }

    public void twoBtn(View view){
        updateText("2");
    }

    public void threeBtn(View view){
        updateText("3");
    }

    public void fourBtn(View view){
        updateText("4");
    }

    public void fiveBtn(View view){
        updateText("5");
    }

    public void sixBtn(View view){
        updateText("6");
    }

    public void sevenBtn(View view){
        updateText("7");
    }

    public void eightBtn(View view){
        updateText("8");
    }

    public void nineBtn(View view){
        updateText("9");
    }

    public void subtractBtn(View view){
        updateText("-");
    }

    public void addBtn(View view){
        updateText("+");
    }

    public void divideBtn(View view){
        updateText("÷");
    }

    public void multiplyBtn(View view){
        updateText("×");
    }

    public void parenthesisBtn(View view){
        int cursorPosition = display.getSelectionStart();
        int textLength = display.getText().length();
        int openParenthesis,closeParenthesis;
        openParenthesis = closeParenthesis = 0;
        for(int i = 0; i < cursorPosition; i++) {
            if (display.getText().toString().charAt(i) == '('){
                openParenthesis++;
            }
            if(display.getText().toString().charAt(i) == ')'){
                closeParenthesis++;
            }
        }

        if(openParenthesis == closeParenthesis || display.getText().toString().substring(textLength-1,textLength).equals("(")){
            updateText("(");
        }
        else if(closeParenthesis < openParenthesis && !display.getText().toString().substring(textLength-1,textLength).equals("(")){
            updateText(")");
        }

        //update cursor position
        display.setSelection(cursorPosition+1);
    }

    public void exponentBtn(View view){
        updateText("^");
    }

    public void pointBtn(View view){
        updateText(".");
    }

    public void equalsBtn(View view){
         String userExpression = display.getText().toString();

         //replacing div and mul symbol
        userExpression = userExpression.replaceAll("÷","/");
        userExpression = userExpression.replaceAll("×","*");

        Expression expression = new Expression(userExpression);
        String result = String.valueOf(expression.calculate());
        display.setText(result);
        display.setSelection(result.length());
    }

    public void plusMinusBtn(View view){
        updateText("+-");
    }

    public void clearBtn(View view){
        display.setText("");
    }

    public void backspaceBTN(View view){
        int cursorPosition = display.getSelectionStart();
        int textLength = display.getText().length();

        if(cursorPosition != 0 && textLength != 0){
            //this class allows us to replace different character within string
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(cursorPosition-1,cursorPosition,""); //update with empty string
            display.setText(selection);
            display.setSelection(cursorPosition-1);
        }
    }

}
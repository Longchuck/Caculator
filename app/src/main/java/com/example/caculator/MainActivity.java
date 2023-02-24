package com.example.caculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    MaterialButton edtso0,edtso1,edtso2,edtso3,edtso4,edtso5,edtso6,edtso7,edtso8,edtso9;
    MaterialButton buttonC, buttonAC, buttonResult, buttonPlus, buttonDevision, buttonSub, buttonMul, buttonDot, buttonPercent;
    TextView resultTv, solutionTv;
    private String currentNumber = "";
    private String currentOperation = "";
    private double result = 0.0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTv = (TextView) findViewById(R.id.tv_result);
        solutionTv =(TextView) findViewById(R.id.tv_solution);

        assignId(edtso0,R.id.btn_0);
        assignId(edtso1,R.id.btn_1);
        assignId(edtso2,R.id.btn_2);
        assignId(edtso3,R.id.btn_3);
        assignId(edtso4,R.id.btn_4);
        assignId(edtso5,R.id.btn_5);
        assignId(edtso6,R.id.btn_6);
        assignId(edtso7,R.id.btn_7);
        assignId(edtso8,R.id.btn_8);
        assignId(edtso9,R.id.btn_9);
        assignId(buttonDot,R.id.btn_dot);

        assignId(buttonC,R.id.btn_C);
        assignId(buttonAC,R.id.btn_back);
        assignId(buttonResult,R.id.btn_result);
        assignId(buttonPlus,R.id.btn_plus);
        assignId(buttonDevision,R.id.btn_devision);
        assignId(buttonSub,R.id.btn_sub);
        assignId(buttonMul,R.id.btn_multi);
        assignId(buttonPercent,R.id.btn_percent);


    }
    void assignId(MaterialButton  btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener((View.OnClickListener)this);
    }


    @Override
    public void onClick(View view) {
        MaterialButton btn = (MaterialButton) view;
        String buttonText = btn.getText().toString();
        String dataToCalculate = solutionTv.getText().toString();
        String resultRecently = resultTv.getText().toString();

        if (buttonText.equals("C")){
            solutionTv.setText("");
            resultTv.setText("0");
            currentNumber="";
            currentOperation="";

            return;

        }
        if(isOperation(buttonText)){
            // Tiếp tục thực hiện phép tính dựa trên kết quả trước đó
            if(!resultRecently.equals("")){
                dataToCalculate = resultRecently;
                solutionTv.setText(resultRecently);
                resultTv.setText("0");
            }
            if (!currentNumber.equals("")) {
                if (!currentOperation.equals("")) {
                    calculate();
                    currentNumber=(String.valueOf(result));
                }
                result = Double.parseDouble(currentNumber);
                currentNumber = "";
                currentOperation = buttonText;
            }
        }
        if(isNumeric(buttonText.charAt(0))) {
            currentNumber +=buttonText;
        }

        if(buttonText.equals("=")){
            calculate();
            resultTv.setText(String.valueOf(result));
            return;
        }
        else {
            dataToCalculate += buttonText;
        }
        solutionTv.setText(dataToCalculate);
       /* String finalResult = getResult(dataToCalculate);

        if(!finalResult.equals("Err")){
            resultTv.setText(finalResult);
        }*/
    }

    boolean isOperation (String operation){
        if (operation.equals("+")|| operation.equals("-")|| operation.equals("/")||operation.equals("x")){
            return true;
        }
        return false;
    }

    private void calculate() {
        if (!currentOperation.equals("") && !currentNumber.equals("")) {
            double operand = Double.parseDouble(currentNumber);
            if (currentOperation.equals("+")) {
                result += operand;
            } else if (currentOperation.equals("-")) {
                result -= operand;
            } else if (currentOperation.equals("*")) {
                result *= operand;
            } else if (currentOperation.equals("/")) {
                result /= operand;
            }
            currentNumber = "";
            currentOperation = "";
        }
    }

    private boolean isNumeric(char c)
    {
        if((c <= '9' && c >= '0') || c == '.')
            return true;
        return false;
    }





/* Cách theo bài hướng dẫn Youtube ban đầu
    String getResult(String data){

        try{
            Context context  = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult =  context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception e){
            return "Err";
        }
    }*/
}
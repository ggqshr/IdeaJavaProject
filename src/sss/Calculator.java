package sss;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.regex.Pattern;

/**
 * Created by ggq on 2017/5/31.
 */
public class Calculator {
    private String firstNumber;
    private String secondNumber;
    private char operator;
    private HashMap<String, String> errors = new HashMap<>();

    public String calculator() {
        BigDecimal result = null;
        BigDecimal first = new BigDecimal(firstNumber);
        BigDecimal second = new BigDecimal(secondNumber);
        switch (getOperator()) {
            case '+':
                result = first.add(second);
                break;
            case '-':
                result = first.subtract(second);
                break;
            case '*':
                result = first.multiply(second);
                break;
            case '/':
                if ("0".equals(second)) {
                    throw new RuntimeException("除数不能为0");
                }
                result = first.divide(second);
                break;
            default:
                break;
        }
        return result.toString();
    }

    public boolean validate() {
        boolean flag = true;
        Pattern p = Pattern.compile("\\d+");
        if (firstNumber == null || "".equals(firstNumber)) {
            errors.put("firstNumber", "第一个运算符不能为空");
            flag = false;
        } else if (!p.matcher(firstNumber).matches()) {
            errors.put("firstNumber", "第一个运算符必须为数值");
            flag = false;
        }
        if (secondNumber == null || "".equals(secondNumber)) {
            errors.put("secondNumber", "第二个运算符不能为空");
            flag = false;
        } else if (!p.matcher(secondNumber).matches()) {
            errors.put("secondNumber", "第二个运算符必须为数值");
            flag = false;
        }
        return flag;
    }

    public String getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(String firstNumber) {
        this.firstNumber = firstNumber;
    }

    public String getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(String secondNumber) {
        this.secondNumber = secondNumber;
    }

    public char getOperator() {
        return operator;
    }

    public void setOperator(char operator) {
        this.operator = operator;
    }

    public HashMap<String, String> getErrors() {
        return errors;
    }

    public void setErrors(HashMap<String, String> errors) {
        this.errors = errors;
    }
}

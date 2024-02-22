package com.example.stackcalculator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MathOperations {

    public static String infixToPostfix(String infix) throws InvalidExpression {
        StringBuilder postfixForm = new StringBuilder();
        LinkedStack  <Character> helpStack = new LinkedStack<Character>();
        char result = '#';

        Pattern numberPattern=Pattern.compile("\\d");
        Matcher numbers=numberPattern.matcher(infix);
        boolean n=numbers.find();
        Pattern PIandEPattern=Pattern.compile("(?i)[πe]");
        Matcher PiE=PIandEPattern.matcher(infix);
        boolean p=PiE.find();
        Pattern OperatorPattern=Pattern.compile("[-+!×÷^]");
        Matcher operator=OperatorPattern.matcher(infix);
        boolean op=operator.find();

        Pattern justNumbers=Pattern.compile("\\b(?:\\d+|π|e)\\b\\s+\\b(?:\\d+|π|e)\\b");
        Matcher justNumber=justNumbers.matcher(infix);

        if (!MathOperations.checkParenthesesBalance(infix))
        {
            throw new InvalidExpression("The parentheses are not balanced in the Expression!!");
        }


        if (!n && !p && !op){throw new InvalidExpression("There is no Number,neither Operator are in the Expression!!");}
        if (((n || p) || (justNumber.find())) && !op){throw new InvalidExpression("There is no Operator in the Expression!!");}
        if (!n && !p){throw new InvalidExpression("There is no Number in the Expression!!");}

        for (int i = 0; i < infix.length(); i++) {

            char temp = infix.charAt(i);

            if (temp == '0' || temp == '1' || temp == '2' || temp == '3' || temp == '4' || temp == '5' || temp == '6' || temp == '7' || temp == '8' || temp == '9'|| temp == '.'|| temp == 'e' || temp == 'π') {
                postfixForm.append(infix.charAt(i));
                continue;
            }
            //----------------------------------------------------------------
            if (temp == '(') {
                helpStack.addFirst('(');
                continue;
            }
            //----------------------------------------------------------------
            if (!helpStack.isEmpty()) {
                    if (temp == ')') {
                        while (result!='(' && !helpStack.isEmpty()) {
                            result = helpStack.pop();
                            if (result != '(' && result != ')') {
                                postfixForm.append(" ");
                                postfixForm.append(result);
                            }
                        }
                        result = '#';
                        continue;
                }
                //----------------------------------------------------------------
                if (temp == '!') {
                    while (helpStack.peek() != '(' && result!='(' && !helpStack.isEmpty() && helpStack.peek() == '!') {
                            result = helpStack.pop();
                            if (result != '(' && result != ')') {
                                postfixForm.append(" ");
                                postfixForm.append(result);
                            }
                        }
                        result = '#';
                        helpStack.push('!');
                        continue;
                }
                //----------------------------------------------------------------
                if (temp == '^') {
                        while (helpStack.peek() != '(' && result!='(' && (helpStack.peek() == '!' || helpStack.peek() == '^')) {
                            result = helpStack.pop();
                            if (result != '(' && result != ')') {
                                postfixForm.append(" ");
                                postfixForm.append(result);
                            }
                            if (helpStack.isEmpty())
                            {
                                break;
                            }
                        }
                        result = '#';
                        helpStack.push('^');
                        postfixForm.append(" ");
                        continue;
                }
                //----------------------------------------------------------------
                if (temp == '÷' || temp == '×') {
                        while (helpStack.peek() != '(' && result!='(' && helpStack.peek() != '+' && helpStack.peek() != '-' && !helpStack.isEmpty()) {
                            result = helpStack.pop();
                            if (result != '(' && result != ')') {
                                postfixForm.append(" ");
                                postfixForm.append(result);
                            }
                            if (helpStack.isEmpty())
                            {
                                break;
                            }
                        }
                        result = '#';
                        helpStack.push(infix.charAt(i));
                        postfixForm.append(" ");
                        continue;
                }
                //----------------------------------------------------------------
                if (temp == '+' || temp == '-') {
                        while (helpStack.peek() != '(' && result!='(' && !helpStack.isEmpty()) {
                            result = helpStack.pop();
                            if (result != '(' && result != ')') {
                                postfixForm.append(" ");
                                postfixForm.append(result);
                            }

                            if (helpStack.isEmpty())
                            {
                                break;
                            }
                        }
                        postfixForm.append(" ");
                        result = '#';
                        helpStack.push(infix.charAt(i));
                }
            }
            else
                if (temp == '!' || temp == '+' || temp == '-' || temp == '÷' || temp == '×' || temp == '^')
                {
                    postfixForm.append(" ");
                    helpStack.addFirst(infix.charAt(i));
                }
        }

        //after that when the inputs end we pop all remaining element and append them to the Postfix form
        while (!helpStack.isEmpty()) {
            if (helpStack.peek()!='(')
            {
                postfixForm.append(" ");
                postfixForm.append(helpStack.pop());
            }
            else
            {
                helpStack.pop();
            }
        }
        return postfixForm.toString();
    }

    //a function that split the postfix function and evaluate it
    public static String postfixEvaluator(String postfix) throws InvalidExpression {
        //first split the postfix String that we separate operands and operators with spaces
        String[] postfixComponents = postfix.split(" ");
        LinkedStack<Double> numbers = new LinkedStack<Double>();
        LinkedStack<String> operator = new LinkedStack<String>();
        //1 2 +  3 - × 1 1 + ^ -
        for (int i = 0; i < postfixComponents.length; i++) {

            String tempPre = "";
            if (i > 0) {
                tempPre = postfixComponents[i - 1];
                if (tempPre.equals("") && i>1)
                {
                    tempPre=postfixComponents[i-2];
                }
            }
            String temp = postfixComponents[i];
            if (temp.equals("")){continue;}



            if (numbers.isEmpty()) {
                if (temp.contains("×") || temp.contains("!") || temp.contains("^") || temp.contains("÷")) {
                    throw new InvalidExpression("We cannot use " + temp + " in the first of the expressions");
                }
                if (temp.contains("-")) {
                    operator.push(temp);
                }
                else {
                    if (temp.contains("π")) {

                        if (!operator.isEmpty())
                        {
                            if (operator.peek().contains("-")) {
                                operator.pop();
                                numbers.push(-Math.PI);
                            }
                        }

                        else {
                            numbers.push(Math.PI);
                        }
                    }
                }

                if (temp.contains("e")) {
                    if (!operator.isEmpty())
                    {
                        if (operator.peek().contains("-")) {
                            operator.pop();
                            numbers.push(-Math.E);
                        }
                    }
                    else {
                        numbers.push(Math.E);
                    }
                }


                if (!temp.contains("π") && !temp.contains("e")) {
                    //برای منفی های قبل از عبارت
                    if (!operator.isEmpty()) {
                        if (operator.peek().contains("-")) {
                            operator.pop();
                            numbers.push(-Double.parseDouble(temp));
                        }
                    } else {
                        numbers.push(Double.parseDouble(temp));
                    }
                }

            } else {
                if (temp.contains("×") || temp.contains("!") || temp.contains("^") || temp.contains("÷") || temp.contains("-") || temp.contains("+")) {
                    if (temp.contains("!")) {
                        double tempNum = numbers.pop();
                        int temp1=(int)tempNum;
                        if (temp1<0){
                            if (!numbers.isEmpty())
                            {
                                temp1+=numbers.pop();
                            }
                        }
                        if (temp1<0)
                        {
                            temp1=-temp1;
                        }

                        tempNum = MathOperations.factorial(temp1);
                        numbers.push(tempNum);
                    }
                    //if + or -
                    if (temp.contains("+") || temp.contains("-")) {
                        //if the size of stack is more than 1
                        if (numbers.getTop() > 0) {
                            if (temp.contains("+")) {
                                double temp1 = numbers.pop();
                                double temp2 = numbers.pop();
                                numbers.push(temp1 + temp2);
                            } else {
                                if (tempPre.contains("×") || tempPre.contains("!") || tempPre.contains("^") || tempPre.contains("÷") || tempPre.contains("+")) {
                                    double temp1 = numbers.pop();
                                    double temp2 = numbers.pop();
                                    numbers.push(temp2 - temp1);
                                } else {
                                    numbers.push(-numbers.pop());
                                    if (postfixComponents.length-1 > i)
                                    {
                                        if (!postfixComponents[i + 1].equals(""))
                                        {
                                            if (postfixComponents[i+1].contains("×") || postfixComponents[i+1].contains("!") || postfixComponents[i+1].contains("^") || postfixComponents[i+1].contains("÷"))
                                            {
                                                numbers.push(numbers.pop()+ numbers.pop());
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        // if the size is 1
                        else {
                            double temp1 = numbers.pop();
                            if (temp.contains("+")) {
                                numbers.push(temp1);
                            } else {
                                numbers.push(-temp1);
                            }
                        }
                    }

                    //if × or ÷
                    if (temp.contains("×") || temp.contains("÷")) {
                        //if the size of stack is more than 1
                        if (numbers.getTop() > 0) {
                            if (temp.contains("×")) {
                                double temp1 = numbers.pop();
                                double temp2 = numbers.pop();
                                numbers.push(temp1 * temp2);
                            } else {

                                double temp1 = numbers.pop();
                                double temp2 = numbers.pop();
                                if (temp1==0)
                                {
                                    throw new InvalidExpression("Division by Zero Is Not Allowed!!");
                                }
                                else
                                {
                                    numbers.push(temp2 / temp1);
                                }

                            }
                        }
                        // if the size is 1
                        else {
                            if (temp.contains("×")) {
                                throw new InvalidExpression("We cannot perform Multiple operator in one number!!");

                            } else {
                                throw new InvalidExpression("We cannot perform Division operator in one number!!");
                            }
                        }
                    }

                    //if ^
                    if (temp.contains("^")) {
                        //if the size of stack is more than 1
                        if (numbers.getTop() > 0) {
                            double temp1 = numbers.pop();
                            double temp2 = numbers.pop();
                            numbers.push(Math.pow(temp2, temp1));

                        }
                        // if the size is 1
                        else {
                            throw new InvalidExpression("We cannot perform Pow operator in one number!!");
                        }
                        }
                    }
                //numbers
                else {
                    if (temp.contains("π")) {
                        if (!operator.isEmpty())
                        {
                            if (operator.peek().contains("-")) {
                                operator.pop();
                                numbers.push(-Math.PI);
                            }
                        }
                        else {
                            numbers.push(Math.PI);
                        }
                    }


                    if (temp.contains("e")) {
                        if (!operator.isEmpty())
                        {
                            if (operator.peek().contains("-")) {
                                operator.pop();
                                numbers.push(-Math.E);
                            }
                        }
                         else {
                            numbers.push(Math.E);
                        }
                    }

                    if (!temp.contains("π") && !temp.contains("e")) {
                        //برای منفی های قبل از عبارت
                        if (!operator.isEmpty()) {
                            if (operator.peek().contains("-")) {
                                operator.pop();
                                numbers.push(-Double.parseDouble(temp));
                            }
                        } else {
                            numbers.push(Double.parseDouble(temp));
                        }
                    }
                }
            }
        }
        if(numbers.size()>1)
        {
            double temp=0.0;
            while(!numbers.isEmpty())
            {
                temp+=numbers.pop();
            }
            numbers.push(temp);
        }
        return String.valueOf(numbers.pop());
    }




    public static long factorial(int n)
    {
        if (n==1 || n==0)
        {
            return 1;
        }
        else
        {
            return n*factorial(n-1);
        }
    }

    public static boolean checkParenthesesBalance(String expression)
    {
        StringBuilder parenthesis= new StringBuilder();

        LinkedStack<Character> stack=new LinkedStack<>();

        for (int x = 0; x < expression.length(); x++) {
            if (expression.charAt(x)=='(' || expression.charAt(x)==')')
            {
                parenthesis.append(expression.charAt(x));
            }
        }

        for (int i = 0; i < parenthesis.length(); i++) {

            if (stack.getTop() != -1) {

                if (parenthesis.charAt(i) == ')') {
                    parenthesis.deleteCharAt(i);
                    parenthesis.deleteCharAt(i - 1);
                    i -= 2;
                    stack.pop();
                } else if (parenthesis.charAt(i) == '(') {
                    stack.push(parenthesis.charAt(i));
                }

            } else if (parenthesis.charAt(i) == '(') {
                stack.push(parenthesis.charAt(i));
            }
            else
            {
                return false;
            }
        }

        return parenthesis.length()==0;
    }

}

import java.util.ArrayList;
import java.util.List;

public class CalculatorController 
{
    private List<String> clean = new ArrayList<String>();    

    public double rechnen(String input) 
    {
        int n = input.length();

        for (int i = 0; i < n; i++) 
        {
            clean.add(Character.toString(input.charAt(i)));
        }

        Double a;
        int b;

        if (input.charAt(0) == '-' && input.charAt(1) != '-') 
        {
            a = Double.parseDouble(clean.get(1)) * -1;
            clean.set(0, Double.toString(a));
            clean.remove(1);
            b = 1;

            while (clean.get(b).matches("[0-9]+")) 
            {
                a = Double.parseDouble(clean.get(b - 1)) * 10 - Double.parseDouble(clean.get(b));
                clean.set(b - 1, Double.toString(a));
                clean.remove(b);
            }
        } 
        else         
            b = 0;
        
        for (int i = 0; i < n; i++) 
        {
            if (i < clean.size() - 1 && clean.get(i).matches("[0-9]+")) 
            {
                while (i != clean.size() - 1 && clean.get(i + 1).matches("[0-9]+")) {
                    a = Double.parseDouble(clean.get(i)) * 10 + Integer.parseInt(clean.get(i + 1));
                    clean.set(i, Double.toString(a));
                    clean.remove(i + 1);
                }
            }            
        }

        for (int i = 0; i < clean.size(); i++) 
        {
            while ("*/".contains(clean.get(i))) 
            {
                rechnenpunkt(clean.get(i), i);
                if (i != 1) 
                    i -= 2;
                else 
                    i--;                
            }
        }

        for (int i = 0; i < clean.size(); i++) 
        {
            if (clean.get(i) == "-" && clean.get(i + 1) == "-") 
            {
                clean.set(i, "+");
                clean.remove(i + 1);
            }

            while ("+-".contains(clean.get(i))) 
            {
                rechnenstrich(clean.get(i), i);
                if (i != 1) 
                    i -= 2;
                 else 
                    i--; 
            }
        }
        return Double.parseDouble(clean.get(0));
    }

    private void rechnenpunkt(String operand, int index) 
    {
        double a = Double.parseDouble(clean.get(index - 1));
        double b = Double.parseDouble(clean.get(index + 1));
        double c;

        switch (operand) 
        {
            case "*": 
                c = a * b;
                break;            
            case "/": 
                c = a / b;
                break;            
            default:
                throw new IllegalArgumentException("Unexpected value: " + operand);
        }

        clean.set(index, Double.toString(c));
        clean.remove(index - 1);
        clean.remove(index);
    }

    private void rechnenstrich(String operand, int index) 
    {
        double a = Double.parseDouble(clean.get(index - 1));
        double b = Double.parseDouble(clean.get(index + 1));
        double c;
        switch (operand) 
        {
            case "+": 
                c = a + b;
                break;
            
            case "-": 
                c = a - b;
                break;
            
            default:
                throw new IllegalArgumentException("Unexpected value: " + operand);
        }

        clean.set(index, Double.toString(c));
        clean.remove(index - 1);
        clean.remove(index);
    }

    //////////////////////////////////////////////////////////////////////////////////////////

    public String calculateInput(String input)
    {
        while(input.contains("("))
        {
            String subExpression = replaceParanthesis(input);
            String result = Double.toString(rechnen(subExpression));
            input = input.replace("(" + subExpression + ")", result);
        }
        return Double.toString(rechnen(input));
    }

    private String replaceParanthesis(String input)
    { 
        int paranthesisCount = 0;
        int tempIteratorVariable = 0;
        
        for (int i = 0; i < input.length(); i++) 
        {
            if (input.charAt(i) == '(')
            {
                paranthesisCount++;
                tempIteratorVariable = i+1;

                while (paranthesisCount != 0) 
                {
                    if (input.charAt(tempIteratorVariable) == '(')                    
                        paranthesisCount++;
                    
                    else if (input.charAt(tempIteratorVariable) == ')')                    
                        paranthesisCount--;                        
                                         
                    tempIteratorVariable++;                                
                }
                return replaceParanthesis(input.substring(i+1, tempIteratorVariable-1));                
            }
        }
        return input;
    }          
}
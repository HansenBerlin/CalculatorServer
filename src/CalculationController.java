import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class CalculationController extends UnicastRemoteObject implements IServerImplementation
{
    public CalculationController() throws RemoteException
    {

    }

    public String createClearStringWithoutParanthesis(String input) throws ScriptException
    {
		System.out.println("Request to calculate: " + input);
        while(input.contains("("))
        {
            String replace = "(" + replaceParanthesis(input) + ")";
            String result = calculateArrayWithoutParanthesis(replace);
            input = input.replace(replace, result);
        }
        return calculateArrayWithoutParanthesis(input);
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


	// wird noch von Corentins Logik ersetzt
    private String calculateArrayWithoutParanthesis(String evaluate) throws ScriptException
    {
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        String testReturn = engine.eval(evaluate).toString();
        System.out.println(testReturn);
        return testReturn;
    } 
    
    
    public boolean checkForValidUserInput(String input)
    {
        int paranthesis = 0;

        for (int i = 0; i < input.length(); i++) 
        {
            if (input.charAt(i) == '(')
                paranthesis++;
            else if (input.charAt(i) == ')')
                paranthesis--;            
        }

        if (paranthesis == 0)
            return true;
        else
            return false;
    }
    
}

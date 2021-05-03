import java.rmi.Remote;
import java.rmi.RemoteException;
import javax.script.ScriptException;


public interface IServerImplementation extends Remote
{	
	public boolean checkForValidUserInput(String input) throws RemoteException;	
	public String createClearStringWithoutParanthesis(String input) throws RemoteException, ScriptException;
}

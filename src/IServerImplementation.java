import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServerImplementation extends Remote
{	
	public String calculateUserInput(String userInput) throws RemoteException;
}

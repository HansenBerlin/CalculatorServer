import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServerImplementation extends Remote
{	
	public String calculateUserInput(String userInput) throws RemoteException;
	public String sendMessageConnectionEstablished() throws RemoteException;
	public String getServerIP() throws RemoteException;
	public long measureResponseTime() throws RemoteException;

}

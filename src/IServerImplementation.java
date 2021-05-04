import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;

public interface IServerImplementation extends Remote
{	
	public String calculateUserInput(String userInput) throws RemoteException;
	public void printEstablishConnectionMessage() throws RemoteException, ServerNotActiveException;
	public String[] getServerAndClientIP() throws RemoteException, ServerNotActiveException;
	public String getServerDateAndTime() throws RemoteException;
}

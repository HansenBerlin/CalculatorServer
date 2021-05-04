import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;

public class ServerCalculatorService extends UnicastRemoteObject implements IServerImplementation
{

    protected ServerCalculatorService() throws RemoteException 
    {
        super();
    }

    public static void main(String[] args) throws RemoteException, MalformedURLException, ServerNotActiveException 
    {				
		System.out.println("RMI server started");
        try 
        { 
            LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
            System.out.println("Java RMI registry created");
        } 
        catch (RemoteException e) 
        {
            System.out.println("Java RMI registry already exists.");
        }
           
        ServerCalculatorService server = new ServerCalculatorService(); 
        Naming.rebind("rmi://localhost/Server", server);
        System.out.println("PeerServer bound in registry");
	}  
    
    public String calculateUserInput(String input) throws RemoteException
    {
        System.out.println("Request to calculate: " + input);
        var calculator = new CalculatorController();
        String result = calculator.calculateInput(input);
        return result;
    }

    public String sendMessageConnectionEstablished() throws RemoteException 
    {
        return null;
    }

    public String getServerIP() throws RemoteException 
    {
        
        return null;
    }

    public long measureResponseTime() throws RemoteException 
    {
        return System.currentTimeMillis();
    }      
    
    
}
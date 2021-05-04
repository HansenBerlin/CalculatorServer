import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServerCalculatorService extends UnicastRemoteObject implements IServerImplementation
{
    static String serverEndpoint = "";

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
        System.out.println("Server bound in registry");
        serverEndpoint = server.ref.remoteToString();
	}  
    
    public String calculateUserInput(String input) throws RemoteException
    {
        System.out.println("Request to calculate: " + input);
        var calculator = new CalculatorController();
        String result = calculator.calculateInput(input);
        return result;
    }

    public void printEstablishConnectionMessage() throws RemoteException, ServerNotActiveException 
    {
        System.out.println("Client with IP " + RemoteServer.getClientHost() + " successfully connected to server");
    }

    public String[] getServerAndClientIP() throws RemoteException, ServerNotActiveException 
    {                
        String serverIP = serverEndpoint;
        for (int i = 38; i < serverIP.length()+38; i++) 
        {
            String temp = serverIP.substring(i, i+1); 
            if (!temp.matches("[0-9]+") && !temp.equals("."))
                serverIP = serverIP.substring(38, i);            
        }

        return new String[]{serverIP, RemoteServer.getClientHost()};
    }

    public String getServerDateAndTime() throws RemoteException 
    {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss:SSS");
        return formatter.format(new Date());
    } 
}
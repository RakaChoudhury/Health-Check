import java.net.*;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
public class AddServer
{
 public AddServer() {
     try {
         
       AddServerIntf c = new AddServerImpl();
      // Runtime.getRuntime().exec("rmiregistry 2020");
       LocateRegistry.createRegistry(1099);
       Naming.rebind("rmi://localhost:1099/AddServer", c);
     
          }

          catch (Exception e) {
       System.out.println("Trouble: " + e);
     }
   }

   public static void main(String args[]) {
     new AddServer();
   }
}


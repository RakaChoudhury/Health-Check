import java.rmi.*;
public interface AddServerIntf extends Remote
{
int concat(int i) throws RemoteException;
}
import java.rmi.*;
import java.rmi.server.*;
public class AddServerImpl extends UnicastRemoteObject implements
AddServerIntf
{
public AddServerImpl() throws RemoteException
{
super();
}
@Override
public int concat(int i) throws RemoteException
{

    return i+1;
}}
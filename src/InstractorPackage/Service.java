package InstractorPackage;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Service {

    public static void main(String[] args){
        try {
            LocateRegistry.createRegistry(5555);
            Interface stub = new ServerCode();
            InetAddress ip = InetAddress.getLocalHost();
            String ipaddress = ip.getHostAddress();
            System.out.println(ipaddress);
            String url = "rmi://"+ipaddress+":5555/InstructorService";
            Context ctx = new InitialContext();
            ctx.bind(url,stub);
            System.out.println("Server is Starting............");
        }catch (RemoteException |NamingException e){
            e.printStackTrace();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
}

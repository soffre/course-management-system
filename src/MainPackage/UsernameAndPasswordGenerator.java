package MainPackage;

import InstractorPackage.Interface;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.util.Random;

public class UsernameAndPasswordGenerator {
    Interface ss;
    public UsernameAndPasswordGenerator(){
        try {
            Context ctx = new InitialContext();
            InetAddress ip = InetAddress.getLocalHost();
            String ipaddress = ip.getHostAddress();
            Interface ii = (Interface) ctx.lookup("rmi://" + ipaddress + ":5555/InstructorService");
            this.ss = ii;
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    public  String generateRandomNumber() {
        Random random = new Random();
        int randomNumber = 10 + random.nextInt(90); // Generate a 2-digit random number
        return String.valueOf(randomNumber);
    }

    public  String createUsername(String firstName, String lastName) {
        String baseUsername = firstName.substring(0, 1).toUpperCase() + lastName.toLowerCase();

        String username = baseUsername + generateRandomNumber();

try {
    if (ss.isUserNameExist(username)) {
        username = baseUsername + generateRandomNumber();
    }
}catch (RemoteException rs){
    rs.printStackTrace();
}
        return username;
    }

    public String generatePassword(String firstName) {
        String password = firstName.substring(0, 3).toLowerCase() +"@" + generateRandomNumber();
        return password;
    }
}


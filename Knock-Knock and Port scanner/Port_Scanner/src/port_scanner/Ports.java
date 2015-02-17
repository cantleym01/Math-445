package port_scanner;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Ports implements Runnable {
    private final int bottomPort;
    private final int topPort;
    private String ip;
    
    public Ports(int bottom, int top, String _ip) {
        bottomPort = bottom;
        topPort = top;
        ip = _ip;
    } 
    
    public void run() {
        //run through all of the ports
        for (int i = bottomPort; i < topPort; i++) {
            try {
                Socket sockItToMe = new Socket();
                
                //this way will connect to local host just like in the "new Socket(ip, port)"
                //but this way can connect throught the internet as well :D
                sockItToMe.connect(new InetSocketAddress(ip, i), 1000);

                System.out.println("Port: " + i + " is in use.");

                sockItToMe.close();
            }
            catch (IOException e) {
                //this is here if want to print what is closed as well
                //System.out.println ("Port: " + i + " not in use.");
            }
        }
    }
}

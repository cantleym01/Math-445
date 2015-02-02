package port_scanner;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Port_Scanner {

    private static int bottomPort = 0;
    private static int topPort = 2000;
    private static int threads = 4;
    
    public static void main(String[] args) {
        Scanner scan = new Scanner( System.in );
        
        //prompt user w/ 1 time instructions
        System.out.println ("Enter the IP address to scan the ports for.");
        System.out.println ("This scanner will scan ports 0 - 2000");
        System.out.println ("This scanner will also run on 4 threads");
        System.out.println ("Enter Q to quit.");
        
        //take input as long as the user does not want to quit
        while (true) {
            String ip = scan.next(); //get input
            
            if (ip.equals("Q")) { //if user's input is quit, exit the program
                break;
            }
            
            for (int i = 0; i < threads; i++) {
                //specified port range for threads
                int top = 0;
                int bottom = 0;
                
                switch (i) {
                    case 0: //first thread
                        top = topPort/threads;
                        bottom = bottomPort;
                        break;
                    case 1: //second thread
                        top = (topPort/threads) * 2;
                        bottom = topPort/threads;
                        break;
                    case 2: //third thread
                        top = (topPort/threads) * 3;
                        bottom = (topPort/threads) * 2;
                        break;
                    case 3: //fourth thread
                        top = topPort;
                        bottom = (topPort/threads) * 3;
                        break;
                    default: //kill it with proverbial fire
                        System.out.println("ERROR: TOO MANY THREADS");
                        System.exit(0);
                }
                
                Thread t = new Thread (new Ports(bottom, top, ip));
                t.start();
                System.out.println("Thread " + i + " created: searching ports " + bottom + " - " + top);
            }
        }
    }
}
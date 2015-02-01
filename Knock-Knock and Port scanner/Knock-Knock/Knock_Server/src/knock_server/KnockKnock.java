package knock_server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Random;

public class KnockKnock implements Runnable{
    private Socket socketToMe;
    private Scanner in;
    private PrintWriter out;
    private final int jokeNumber = 10;
    private String[] joke;
    private String[] punchline;
    private Random rng = new Random();
    
    public KnockKnock(Socket baby) throws IOException {
        socketToMe = baby; //gotta have fun while working
        in = new Scanner(socketToMe.getInputStream());
        out = new PrintWriter(socketToMe.getOutputStream());
        
        joke = new String[jokeNumber];
        punchline = new String[jokeNumber];
        
        //just add more jokes for whenever you want by hard coding
        //I cannot think of anything elegent
        joke[0] = "JOKESON";
        punchline[0] = "YOU!";
        joke[1] = "I DON'T HAVE A JOKE";
        punchline[1] = "I'M NOT FUNNY!";
        joke[2] = "ART";
        punchline[2] = "2 D2";
        joke[3] = "TOBY";
        punchline[3] = "OR NOT TOBY, THAT IS THE QUESTION!";
        joke[4] = "HOWIE";
        punchline[4] = "GONNA HIDE THIS BODY?";
        joke[5] = "PUTCHER";
        punchline[5] = "HANDS UP, THIS IS A ROBBERY!";
        joke[6] = "I C";
        punchline[6] = "WEINER";
        joke[7] = "CENTIPEDE";
        punchline[7] = "ON THE CHRISTMAS TREE";
        joke[8] = "SHELBY";
        punchline[8] = "COMIN' ROUND THE MOUNTAIN WHEN SHE COMES";
        joke[9] = "ORANGE";
        punchline[9] = "YOU GLAD I DIDN'T SAY BANANA?";
    }
    
    public void run() {
        while (true) {
            int randomJoke = rng.nextInt(10);
            
            out.println("KNOCK KNOCK!");
            out.flush();
            
            Boolean goOn = false;
            
            while (!goOn) {
                if (!in.hasNext()) {
                    return;
                }
                
                String command = in.nextLine();
                
                if ("WHO'S THERE?".equals(command)) {
                    out.println(joke[randomJoke]);
                    out.flush();
                    goOn = true;
                }
                else if ("STAHP".equals(command)) {
                    try {
                        socketToMe.close();
                    } 
                    catch (IOException ex) {
                        Logger.getLogger(KnockKnock.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.exit(0);
                }
                else {
                    out.println("NO!!! THAT IS NOT HOW THE JOKE GOES!!! ENTER AGAIN!!!");
                    out.flush();
                }
            }
            
            goOn = false;
            
            while (!goOn) {
                String command = in.nextLine();

                if (command.equals(joke[randomJoke] + " WHO?")) {
                    out.println(joke[randomJoke] + " " + punchline[randomJoke]);
                    out.flush();
                    goOn = true;
                }
                else if ("STAHP".equals(command)) {
                    try {
                        socketToMe.close();
                    } 
                    catch (IOException ex) {
                        Logger.getLogger(KnockKnock.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.exit(0);
                }
                else {
                    out.println("NO!!! THAT IS NOT HOW THE JOKE GOES!!! ENTER AGAIN!!!");
                    out.flush();
                }
            }
        }
    }
}

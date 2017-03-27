import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.Scanner;

/**
 * Created by Daniel on 3/19/2017.
 */
public class reldat_client {
    public static void main(String[] args) throws IOException {
        int port = Integer.parseInt(args[1]);
        int windowSize = Integer.parseInt(args[2]);
        boolean connected = true;
        //socket
        //reldat_socket ds = new reldat_socket(port, windowSize);
        DatagramSocket ds = new DatagramSocket();

        //read first line of txt file
//        BufferedReader br = new BufferedReader(new FileReader(args[2]));
//        StringBuilder sb = new StringBuilder();
//        String line = br.readLine();

        //create the string of the text
//        while (line != null) {
//            sb.append(line);
//            sb.append(" ");
//            line = br.readLine();
//        }
//        String msg = sb.toString().toLowerCase();


        int tries = 0;


        while (connected) {
            try {
                //send the packet of the message to the server
                InetAddress address = InetAddress.getByName(args[0]);

                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine();
                if (input.substring(0, 9).equals("transform")) {
                    String file = input.substring(10);
                    System.out.println(file);
                    //read first line of txt file
                    BufferedReader br = new BufferedReader(new FileReader("src/" + file));
                    StringBuilder sb = new StringBuilder();
                    String line = br.readLine();

                    //create the string of the text
                    while (line != null) {
                        sb.append(line);
                        sb.append(" ");
                        line = br.readLine();
                    }
                    String txt = sb.toString();
                    System.out.println(txt);
                    //change this
                    DatagramPacket dp = new DatagramPacket(txt.getBytes(), txt.length(), address, Integer.parseInt(args[1]));
                    ds.send(dp);
                    ds.setSoTimeout(2000);
                    //receive then change file to -received.txt
                    byte[] b = new byte[1024];
                    DatagramPacket dp2 = new DatagramPacket(b, b.length);
                    ds.receive(dp2);
                    PrintWriter pw = new PrintWriter(file.substring(0, file.length() - 4) + "-received.txt","UTF-8");
                    pw.println(new String(dp2.getData()));
                    ds.close();
                    System.exit(0);
                    break;
                } else if (input.equals("disconnect")) {
                    //TODO other stuff
                    connected = false;
                }


            } catch (SocketTimeoutException e) {
                //retry until there is a response or tried for less than three times for a response
                if (tries < 3) {
                    System.out.println("The server has not answered in the last two seconds.");
                    System.out.println("retrying...");
                    tries++;
                    //after 3 tries, print that there was no response
                } else {
                    System.out.println("No response.");
                    ds.close();
                    System.exit(0);
                }
            }
        }

        //print the result

    }
}

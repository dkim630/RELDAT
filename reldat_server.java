import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;

/**
 * Created by Daniel on 3/19/2017.
 */
public class reldat_server {
    public static void main(String[] args) throws IOException {
        //socket
        DatagramSocket ds = new DatagramSocket(Integer.parseInt(args[0]));
        int windowSize = Integer.parseInt(args[1]);
        while (true) {

            //Retrieve the message from client
            byte[] b = new byte[1024];
            DatagramPacket dp = new DatagramPacket(b, b.length);
            ds.receive(dp);
            String txt = new String(dp.getData(), 0, dp.getLength());
            System.out.println(txt);


            DatagramPacket dp2;
            if (txt.length() == 0 || txt.length() >= 1000 || !txt.chars().allMatch(c -> c < 128)) {
                String errorMsg = "0 -1 ERROR";
                dp2 = new DatagramPacket(errorMsg.getBytes(), errorMsg.length(), dp.getAddress(), dp.getPort());
                ds.send(dp2);
            } else {
                txt = txt.toLowerCase();
                
                //send information to client
                byte[] b2 = txt.getBytes();
                dp2 = new DatagramPacket(b2, b2.length, dp.getAddress(), dp.getPort());
                ds.send(dp2);
            }

        }

    }
}

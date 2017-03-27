import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Created by Daniel on 3/23/2017.
 */
public class reldat_socket extends DatagramSocket {
    private int windowSize;
    private int seqNum = 0;

    public reldat_socket(int port, int windowSize) throws SocketException {
        super(port);
        this.windowSize = windowSize;
        if (seqNum == Integer.MAX_VALUE) {
            this.seqNum = 0;
        } else {
            this.seqNum = seqNum++;
        }

    }
}

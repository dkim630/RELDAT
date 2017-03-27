/**
 * Created by Daniel on 3/20/2017.
 */
public class reldat_packet {
    private int src;
    private int dst;
    private int seqNum;
    private int windowSize;
    private int checksum;
    private byte[] data;

    private boolean SYN;
    private boolean ACK;
    private boolean FIN;

    public reldat_packet(int src, int dst, int seqNum, int windowSize, int checksum) {
        this.src = src;
        this.dst = dst;
        this.seqNum = seqNum;
        this.windowSize = windowSize;
        this.checksum = checksum;
    }

    public reldat_packet(int src, int dst, int seqNum, int windowSize, int checksum, byte[] data) {
        this.src = src;
        this.dst = dst;
        this.seqNum = seqNum;
        this.windowSize = windowSize;
        this.checksum = checksum;
        this.data = data;
    }

    public int getSrc() {
        return src;
    }

    public void setSrc(int src) {
        this.src = src;
    }

    public int getDst() {
        return dst;
    }

    public void setDst(int dst) {
        this.dst = dst;
    }

    public int getSeqNum() {
        return seqNum;
    }

    public void setSeqNum(int seqNum) {
        this.seqNum = seqNum;
    }

    public int getWindowSize() {
        return windowSize;
    }

    public void setWindowSize(int windowSize) {
        this.windowSize = windowSize;
    }

    public int getChecksum() {
        return checksum;
    }

    public void setChecksum(int checksum) {
        this.checksum = checksum;
    }

    public boolean isSYN() {
        return SYN;
    }

    public void setSYN(boolean SYN) {
        this.SYN = SYN;
    }

    public boolean isACK() {
        return ACK;
    }

    public void setACK(boolean ACK) {
        this.ACK = ACK;
    }

    public boolean isFIN() {
        return FIN;
    }

    public void setFIN(boolean FIN) {
        this.FIN = FIN;
    }
}

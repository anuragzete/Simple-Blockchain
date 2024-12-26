
import java.util.Date;

public class block {
    public String hash;
    public String previousHash;
    private String data;
    private long timeStamp;
    private int nounce;

    block(String data, String prevHash) {
        this.data = data;
        this.previousHash = prevHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    public String calculateHash() {
        String calculatedHash = generateSign.applySHA256(previousHash + Long.toString(timeStamp) + Integer.toString(nounce) + data);

        return calculatedHash;
    }

    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0');
        while (!hash.substring(0, difficulty).equals(target)) {
            nounce++;
            hash = calculateHash();
        }
        System.out.println("block mined!");
    }
}

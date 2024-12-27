import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;

public class transaction {
    public PublicKey sender;
    public PublicKey receiver;
    public float fundsValue;
    public String transactionID;
    public byte[] signature;

    private static int sequence = 0;

    public ArrayList<TransactionInput> inputs = new ArrayList<TransactionInput>();
    public ArrayList<TransactionOutput> outputs = new ArrayList<TransactionOutput>();

    transaction(PublicKey sender, PublicKey receiver, float fundsValue, ArrayList<TransactionInput> inputs) {
        this.sender = sender;
        this.receiver = receiver;
        this.fundsValue = fundsValue;
        this.inputs = inputs;
    }

    private String calulateHash() {
        sequence++;
        return stringUtil.applySha256(
                stringUtil.getStringFromKey(sender) +
                        stringUtil.getStringFromKey(reciepient) +
                        Float.toString(value) + sequence
        );
    }

    public void generateSignature(PrivateKey privateKey) {
        String data = stringUtil.getStringFromKey(sender) + stringUtil.getStringFromKey(reciepient) + Float.toString(value)	;
        signature = stringUtil.applyECDSASig(privateKey,data);
    }

    public boolean verifiySignature() {
        String data = stringUtil.getStringFromKey(sender) + stringUtil.getStringFromKey(reciepient) + Float.toString(value)	;
        return stringUtil.verifyECDSASig(sender, data, signature);
    }
}

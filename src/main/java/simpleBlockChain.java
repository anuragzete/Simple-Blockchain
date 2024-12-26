
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class simpleBlockChain {
    public static ArrayList<block> blockChain = new ArrayList<>();
    public static int difficulty = 7;

    public static void main(String[] args) {
        block genesisBlock = new block("hi there,it's anurag","0");
        genesisBlock.mineBlock(difficulty);
        blockChain.add(genesisBlock);

        block secondBlock = new block("i am developing a simple blockchain",genesisBlock.hash);
        secondBlock.mineBlock(difficulty);
        blockChain.add(secondBlock);

        block thirdBlock = new block("it is going well",secondBlock.hash);
        thirdBlock.mineBlock(difficulty);
        blockChain.add(thirdBlock);

        String jsonBlockChain = new GsonBuilder().setPrettyPrinting().create().toJson(blockChain);
        System.out.println(jsonBlockChain);

        System.out.println("is blockChain valid? " + isBlockChainValid());
    }

    public static boolean isBlockChainValid(){
        block currentBlock;
        block previousBlock;

        for (int i = 1; i < blockChain.size(); i++) {
            currentBlock = blockChain.get(i);
            previousBlock = blockChain.get(i-1);

            if(!currentBlock.hash.equals(currentBlock.calculateHash())){
                return false;
            }
            if(!previousBlock.hash.equals(currentBlock.previousHash)){
                return false;
            }
        }

        return true;
    }
}

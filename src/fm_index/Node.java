package fm_index;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Adam Groves
 */
public class Node {
    
    Node left;
    Node right;
    boolean[] bitVector;
    HashMap<Character, Boolean> alphabetMap;
    
    public Node(String s){
        encode(s);
        calculateBitVector(s);
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
    
    public boolean[] getBitVector() {
        return bitVector;
    }

    public void setBitVector(boolean[] bitVector) {
        this.bitVector = bitVector;
    }
   
    public boolean getEncoding(char c) {
        return alphabetMap.get(c);
    }
    
    private void calculateBitVector(String s){
        bitVector = new boolean[s.length()];
        
        for (int i = 0; i < s.length(); i++) {
            bitVector[i] = alphabetMap.get(s.charAt(i));
        }
    }
    
    /**
     * Create alphabet map that maps character -> integer
     * The first half of the alphabet is mapped to 0, the second half is mapped to 1
     * @param s The string to create alphabet mapping from.
     */
    private void encode(String s){
        // Create alphabet string
        String alpha = "";
        for (int i = 0; i < s.length(); i++) {
            if(!alpha.contains(Character.toString(s.charAt(i)))){
                alpha += String.valueOf(s.charAt(i));
            }
        }
        char[] chars = alpha.toCharArray();
        Arrays.sort(chars);
        
        String alphabet = new String(chars);
        
        // Create map of alphabet character -> encoded bit
        this.alphabetMap = new HashMap<>();
        boolean bitValue;
        if (alphabet.length() == 1) {
            alphabetMap.put(alphabet.charAt(0), false);
        }
        else{
            for (int i = 0; i < alphabet.length(); i++) {
                bitValue = i < alphabet.length()/2 ? false : true;
                alphabetMap.put(alphabet.charAt(i), bitValue);
            }
        }
    }
    
    public void printAlphabet(){
        for (Map.Entry<Character, Boolean> entry : alphabetMap.entrySet()) {
            System.out.println(entry.getKey());
        }
        System.out.println("");
    }
    
    public void printBitVector(){
        for (int i = 0; i < bitVector.length; i++) {
            System.out.print(bitVector[i]);
        }
        System.out.println("");
    }
}
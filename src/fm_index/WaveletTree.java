package fm_index;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Adam Groves
 */
public class WaveletTree {

    Node root;
    String s;
    
    public WaveletTree(String s) {
        this.s = s;
        root = constructTree(s, new Node(s));
    }
    
    /**
     * Construct a wavelet tree
     * @param s The string to use to initialize 'node' (root)
     * @param node The node that roots the tree
     * @return 
     */
    private Node constructTree(String s, Node node) {
        if(!(countLetters(s) <= 2)){
   
            String sLeft = "";
            String sRight = "";

            for (int i = 0; i < s.length(); i++) {
                if (node.getEncoding(s.charAt(i))) {
                    sRight += s.charAt(i);
                }
                else
                    sLeft += s.charAt(i);
            }
            
            node.left = constructTree(sLeft, new Node(sLeft));
            node.right = constructTree(sRight, new Node(sRight));
            
        }
        return node;
    }
    
    /**
     * Rank method that returns the number of occurrences of 'c' within the range of (0, index)
     * @param c
     * @param index
     * @return 
     */
    public int rank(char c, int index){
        return iRank(c, index, root);
    }
    
    /**
     * Internal rank method used for recursively traversing tree to find rank
     * @param c
     * @param index
     * @param node
     * @return 
     */
    private int iRank(char c, int index, Node node){
        if (node == null) {
            return index;
        }
        else{
            boolean encoding = node.getEncoding(c);
            int count = 0;
            for (int i = 0; i < index; i++) {
                if(node.bitVector[i] == encoding){
                    count++;
                }
            }

            if(encoding == false)
                return iRank(c, count, node.left);
            else
                return iRank(c, count, node.right);
        }
    }
    
    
    
    /**
     * Counts the number of letters that are present in the string 's'
     * @param s The string to count the letters on
     * @return 
     */
    private int countLetters(String s){
        HashMap<Character, Integer> letters = new HashMap<Character, Integer>();
        char[] chars = s.toCharArray();

        for(int i=0; i<chars.length;i++)
        {
            if(!letters.containsKey(chars[i]))
            {
                letters.put(chars[i], 1);
            }
            letters.put(chars[i], letters.get(chars[i])+1);
        }

        return letters.size();
    }

    
    /**
     * Print the wavelet tree in order
     * @param node The node to start the traversal on
     */
    public void inOrderPrint(Node node){
        if (node.left != null) {
            inOrderPrint(node.left);
        }
        
        for (int i = 0; i < node.bitVector.length; i++) {
            System.out.print(node.bitVector[i] ? 1 : 0);
        }
        System.out.println("");
        
        if (node.right != null) {
            inOrderPrint(node.right);
        }
    }
}
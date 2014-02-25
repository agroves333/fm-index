package fm_index;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author Adam Groves
 */
public class FMIndex {
    
    WaveletTree wt;
    String sequence;
    SuffixArray sa;
    String query;
    char q;
    int s;
    int e;
    C c;
    HashMap<Integer, Integer> pos;
    
    public FMIndex(String sequence) {
        this.sequence = sequence;
        pos = new HashMap<>();
        
        // Create suffix array (Also creates BW Text)
        sa = new SuffixArray(sequence);
        
        // Get BW Text from suffix array
        String bwt = sa.getBWT();
        
        // Create C dictionary
        c = new C(sequence);
        
        // Create wavelet tree from the BW Text
        // This compresses the BW Text and allows for O(1) time retrieval of rank(x, i)
        wt = new WaveletTree(bwt);
       
        // We no longer need bwt since it is represented as a wavelet tree and is compressed.
        // Remove reference to BW Text so that garbage collector will clean up space in memory if needed.
//        bwt = null;
          
        // Initialize start and end indexes
        s = 0;
        e = sequence.length();
    }
    
    
    /**
     * Perform search query
     * @param query 
     */
    public int[] search(String query){
        this.query = query;
        
        // Iterate the query string backwards
        for (int i = query.length(); i > 0 ; i--) {
            q = query.charAt(i - 1);
            s = c.occurrence.get(q) + wt.rank(q, s - 1) + 1;
            e = c.occurrence.get(q) + wt.rank(q, e);
            
            // If end index becomes larger than start index, then query not found
            if (e < s) {
                System.out.println("Query: \"" + query + "\" not found");
                System.exit(0);
            }
        }
        
        // Return indeces range
        return new int[]{s,e};
    }
    
    /**
     * Get Last to First row mapping of M table (array of rotations)
     * @param i
     * @return 
     */
    private int LF(int i){
        Character[] alphabet = wt.root.alphabetMap.keySet().toArray(new Character[0]);
        char li = 0;
        for (int j = 1; j < alphabet.length; j++) {
            int rank1 = wt.rank(alphabet[j].charValue(), i);
            int rank2 = wt.rank(alphabet[j].charValue(), i-1);
            if(rank1 != rank2){
                li = alphabet[j].charValue();
            }
        }
        return c.occurrence.get(li);
    }
    
    /**
     * Uncompress BW Text from Wavelet Tree
     * @return 
     */
    private String getBWT(){
        Character[] alphabet = wt.root.alphabetMap.keySet().toArray(new Character[0]);
        String bwt = "";
        
        for (int i = 0; i < wt.root.bitVector.length+1; i++) {
            for (int j = 0; j < alphabet.length; j++) {
                int rank1 = wt.rank(alphabet[j].charValue(), i);
                int rank2 = wt.rank(alphabet[j].charValue(), i-1);
                if(rank1 != rank2){
                    bwt += alphabet[j];
                }
            }
        }
        return bwt;
    }
    
    /**
     * Reconstruct the original compressed string
     * @return 
     */
    public String reconstructS(){
        char[] last = getBWT().toCharArray();
        char[] first = Arrays.copyOf(last, last.length);
        Arrays.sort(first);
        
        // Create wavelet trees out of first and last columns in order to perform quick ranks.
        WaveletTree firstWT = new WaveletTree(first.toString());
        
        String original = "";
        
        // Get first letter in S
        int lastIndex = 0;
        char firstLetter = 0;
        for (int i = 0; i < last.length; i++) {
            if(last[i] == '$'){
                firstLetter = first[i];
                original += firstLetter;
                lastIndex = i;
                break;
            }
        }
        
        for(int k = 0; k < last.length -1; k++){
            
            int count = 0;
            for (int i = 0; i <= lastIndex; i++) {
                if (first[i] == original.charAt(original.length()-1)) {
                    count++;
                }
            }

            int count2 = 0;
            for (int i = 0; i < last.length; i++) {
                if (last[i] == original.charAt(original.length()-1)) {
                    count2++;
                    if(count2 == count){
                        original += first[i];
                        lastIndex = i;
                        break;
                    }
                }
            }
        }
        
        return original;
    }
    
    
//    private void generatePos(){
//        for (int i = 0; i < sequence.length(); i++) {
//            pos.put(i, e)
//        }
//    }
    
    private int pos(int i){
        int index = i;
        int t = 0;
        while (true) {
            
        }
    }
}

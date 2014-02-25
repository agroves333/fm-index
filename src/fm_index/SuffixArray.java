package fm_index;

import java.util.*;

/**
 *
 * @author Adam Groves
 */
public class SuffixArray{
    String s;
    String[] m;
    String bwt = "";
    
    public SuffixArray(String s){
       this.s = s;
       this.m = new String[s.length()];
       
       // generate the suffixArray of the given string "s"
       generateSuffixes();
       
       // Sort the prefixes
       Arrays.sort(m);
       
       // create the Burrows-Wheeler Text
       generateBWT();
    }
    
    public String getBWT(){
        return this.bwt;
    }
    
    
    private void generateSuffixes(){
        String tempS = this.s;
        
        for (int i = 0; i < s.length(); i++) {
            m[i] = tempS;
            // Rotate String
            tempS = tempS.substring(1) + tempS.charAt(0);
        }

    }
    
    private void generateBWT(){
       
        for (int i = 0; i < s.length(); i++) {
            bwt += m[i].charAt(s.length()-1);
        }
    }
    
    
    public void printBWT(){
        System.out.println(bwt);
    }
}

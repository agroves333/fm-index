package fm_index;

import java.util.*;

/**
 *
 * @author Adam Groves
 */
public class SuffixArray_old{
    String s;
    LinkedHashMap<Integer, String> suffixArray;
    String bwt = "";
    
    public SuffixArray_old(String s){
       this.s = s;
       this.suffixArray = new LinkedHashMap<>();
       
       // generate the suffixArray of the given string "s"
       generateSuffixes();
       
       // Sort the prefixes
       sortByValue();
       
       // create the Burrows-Wheeler Text
       generateBWT();
    }
    
    public String getBWT(){
        return this.bwt;
    }
    
    private void generateSuffixes(){
        String tempS = this.s;
        
        for (int i = 0; i < s.length(); i++) {
            this.suffixArray.put(i, tempS);
            // Rotate String
            tempS = tempS.substring(1) + tempS.charAt(0);
        }
        
        // Remove characters after $
//        for (Entry<Integer, String> entry : suffixArray.entrySet()) {
//            entry.setValue(entry.getValue().substring(0, entry.getValue().indexOf("$")+1));            
//        }
    }
    
    private void generateBWT(){
       
        Collection<String> values = suffixArray.values();
        for (String value : values) {
            bwt += value.charAt(s.length()-1);
        }
    }
    
    private void sortByValue(){
        List<Map.Entry<Integer, String>> list = new LinkedList<>( suffixArray.entrySet());
        
        Collections.sort( list, new Comparator<Map.Entry<Integer, String>>(){
            @Override
            public int compare( Map.Entry<Integer, String> o1, Map.Entry<Integer, String> o2 )
            {
                return (o1.getValue()).compareTo( o2.getValue());
            }
        });

        LinkedHashMap<Integer, String> result = new LinkedHashMap<>();
        
        for (Map.Entry<Integer, String> entry : list){
            result.put( entry.getKey(), entry.getValue() );
        }
        suffixArray = result;
    }
    
    public void printBWT(){
        System.out.println(bwt);
    }
}

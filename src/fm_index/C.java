package fm_index;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Adam Groves
 */
public class C {

    HashMap<Character, Integer> occurrence;
    String s;

    public C(String s) {
        this.s = s;
        generateC();
    }

    private void generateC() {
        // Create alphabet string
        occurrence = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            occurrence.put(s.charAt(i), 0);
        }
        
        for (int i = 0; i < s.length(); i++) {
            
            Character[] keys = occurrence.keySet().toArray(new Character[0]);
            
            for (int j = 0; j < keys.length; j++) {
                if(s.charAt(i) < keys[j]){
                    occurrence.put(keys[j], occurrence.get(keys[j].toString().charAt(0)) + 1);
                }
            }
        }
    }

    public void print() {
        for (Map.Entry<Character, Integer> entry : occurrence.entrySet()) {
            System.out.println(entry.toString());
        }
    }
}

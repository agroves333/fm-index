package fm_index;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Adam Groves
 */
public class GenerateData {

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try (PrintWriter writer = new PrintWriter("sequence.dat", "UTF-8")) {
            
            Random r = new Random();
            char[] alphabet = new char[]{'A', 'C', 'G', 'T'};
            
            for (int i = 0; i < 1000; i++) {
                int index = new Random().nextInt(alphabet.length);
                char random = (alphabet[index]);
                writer.print(random);
            }
            writer.close();
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            Logger.getLogger(GenerateData.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}

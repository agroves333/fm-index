/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fm_index;

import java.util.Scanner;

/**
 *
 * @author Adam Groves
 */
public class FMTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        System.out.println("Enter text");
        Scanner in = new Scanner(System.in);
        
        String s = in.next();
        
        FMIndex fmi = new FMIndex("mississippi$");
        
        System.out.println("Original text: " + fmi.reconstructS());
    }
}

package testScripts;
import java.util.ArrayList;
import java.util.Arrays;

public class VowelState {
/*
 * Takes in a word such as Hello and returns a list with a bool attached to a letter:
 * [(H,F) , (e,T) ..]
 */
    String word;
    private static int lone_e_counter = 0;
    ArrayList<Tuple> arrayTuples = new ArrayList<Tuple>();
    public VowelState(String word) {
        //for each letter create a (letter, bool) and append to an array 
        String[] stringArrays = word.split("");
        for(String l : stringArrays) {
        	isAVowel(l);
        }
        
    }
    
    public void isAVowel(String l) {
         String vowels = new String("aeiouy");

             if (l.equals("e") ) {
            	 //Do logic related to e
            	 if (lone_e_counter == 0) {
            		 //first time
            		 Tuple t = new Tuple(l,true);
                     arrayTuples.add(t);
                     lone_e_counter++;
            	 }
            	 else if (lone_e_counter >=1) {
            		 //After first time
            		 Tuple f = new Tuple(l,false);
            		 arrayTuples.add(f);
            	 }
             }
             else if(vowels.indexOf(l) >= 0) {
            	 //Vowel
            	 Tuple t = new Tuple(l,true);
                 arrayTuples.add(t);
             }
             else if ( vowels.indexOf(l) < 0 ) {
            	 //Not a vowel
            	 Tuple f = new Tuple(l,false);
            	 arrayTuples.add(f);
             }
    }
    
    public void getArrayTuples() {
    	for(Tuple t : arrayTuples) {
    		System.out.println("(" + t.letter + " , " + t.vBool + ")");
    	}
    }
    
    
}

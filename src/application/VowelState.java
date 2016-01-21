package application;

import java.util.ArrayList;

public class VowelState {
/*
 * Takes in a word such as Hello and returns a list with a bool attached to a letter:
 * [(H,F) , (e,T) ..]
 */
	String word;
	ArrayList<Tuple> arrayTuples = new ArrayList<Tuple>();
	public VowelState(String word) {
		//for each letter create a (letter, bool) and append to an array 
		String[] stringArrays = word.split("");
		
		for (String l : stringArrays) {
			if (isAVowel(l)) {
			
				Tuple t = new Tuple(l,true);
				arrayTuples.add(t);
			}
			else {
				Tuple f = new Tuple(l, false);
				arrayTuples.add(f);
			}
		}
	}
	
	 public boolean isAVowel(String l) {
		 String[] vowels = {"a", "e", "i", "o", "u", "y"};
		 for (String letter : vowels) {
			 if (l.equals(letter)) {
				 return true;
			 }
		 }
		 return false;
		
	}
	
}

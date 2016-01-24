package document;

/** 
 * A class that represents a text document
 * @author UC San Diego Intermediate Programming MOOC team
 */
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public abstract class Document {

	private String text;
	
	/** Create a new document from the given text.
	 * Because this class is abstract, this is used only from subclasses.
	 * @param text The text of the document.
	 */
	protected Document(String text)
	{
		this.text = text;
	}
	
	/** Returns the tokens that match the regex pattern from the document 
	 * text string.
	 * @param pattern A regular expression string specifying the 
	 *   token pattern desired
	 * @return A List of tokens from the document text that match the regex 
	 *   pattern
	 */
	protected List<String> getTokens(String pattern)
	{
		ArrayList<String> tokens = new ArrayList<String>();
		Pattern tokSplitter = Pattern.compile(pattern);
		Matcher m = tokSplitter.matcher(text);
		
		while (m.find()) {
			tokens.add(m.group());
		}
		
		return tokens;
	}
	
	// This is a helper function that returns the number of syllables
	// in a word.  You should write this and use it in your 
	// BasicDocument class.
	// You will probably NOT need to add a countWords or a countSentences method
	// here.  The reason we put countSyllables here because we'll use it again
	// next week when we implement the EfficientDocument class.
	protected int countSyllables(String word)
	{
        // TODO: Implement this method so that you can call it from the 
        /*
         * Return number is syllables in a word
         **Syllable: contigous sequence of 1 or more vowels 
         ***exception: lone "e" at the end of a word UNLESS the word 
         ***has no other vowels.    
         */
        
        boolean vowelState = isVowel(word.charAt(0)); //get initial state
        int count_lone_e = 0;
        int syllables = vowelState?1:0; //initialize counter based off initial state
        char[] wordArray = word.toCharArray();
        for (int i = 0 ; i< wordArray.length; i++) {
        	//for each word if the state of the character is not equal to the previous state 
        	///if its a vowel increment the counter
        	///update the vowel state
        	char c = wordArray[i];
            if (isVowel(c) != vowelState) {
                if(isVowel(c)) {
                	if (getE(c) ) {
                		count_lone_e++;
                	}
                	else {
                		syllables++;
                	}
                }
                vowelState = isVowel(c);
            }
        }
        
        if (count_lone_e > 0) {
        	if(count_lone_e == 1) {
        		count_lone_e--;
        	}
        	System.out.println("count_lone_e = " + count_lone_e);
        	return syllables + count_lone_e;
        }
        return syllables;
    }
    
    private boolean isVowel(char c) 
    {
    	return "aiouye".indexOf(Character.toLowerCase(c)) != -1;
    }
    
    private boolean getE(char c) {
    	return "e".indexOf(Character.toLowerCase(c)) != -1;
    }
	
	
	/** A method for testing
	 * 
	 * @param doc The Document object to test
	 * @param syllables The expected number of syllables
	 * @param words The expected number of words
	 * @param sentences The expected number of sentences
	 * @return true if the test case passed.  False otherwise.
	 */
	public static boolean testCase(Document doc, int syllables, int words, int sentences)
	{
		System.out.println("Testing text: ");
		System.out.print(doc.getText() + "\n....");
		boolean passed = true;
		int syllFound = doc.getNumSyllables();
		int wordsFound = doc.getNumWords();
		int sentFound = doc.getNumSentences();
		if (syllFound != syllables) {
			System.out.println("\nIncorrect number of syllables.  Found " + syllFound 
					+ ", expected " + syllables);
			passed = false;
		}
		if (wordsFound != words) {
			System.out.println("\nIncorrect number of words.  Found " + wordsFound 
					+ ", expected " + words);
			passed = false;
		}
		if (sentFound != sentences) {
			System.out.println("\nIncorrect number of sentences.  Found " + sentFound 
					+ ", expected " + sentences);
			passed = false;
		}
		
		if (passed) {
			System.out.println("passed.\n");
		}
		else {
			System.out.println("FAILED.\n");
		}
		return passed;
	}
	
	
	/** Return the number of words in this document */
	public abstract int getNumWords();
	
	/** Return the number of sentences in this document */
	public abstract int getNumSentences();
	
	/** Return the number of syllables in this document */
	public abstract int getNumSyllables();
	
	/** Return the entire text of this document */
	public String getText()
	{
		return this.text;
	}
	
	/** return the Flesch readability score of this document */
	public double getFleschScore()
	{
	    // TODO: Implement this method
		double words = getNumWords();
		double sentences = getNumSentences();
		double words_sentences = (1.015*(words / sentences));
		
		double syllables = getNumSyllables();
		double syllables_words = 84.6*(syllables / words);
		
		double FleschScore = (206.835 - words_sentences - syllables_words);
	    return FleschScore;
	}
	
	
	
}

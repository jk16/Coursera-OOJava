package testScripts;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Document 
{
    private String text;

    public Document(String text2) {
        text = "Here is some text. Do we understand it? Let's get excited!";
    }

    public String getText() {
        return text;

    }

        /** Return the number of words in this document */
    public abstract int getNumWords();
    
    /** Return the number of sentences in this document */
    public abstract int getNumSentences();
    
    /** Return the number of syllables in this document */
    public abstract int getNumSyllables();

    protected int countSyllables(String word) {
        // TODO: Implement this method so that you can call it from the 
        /*
         * Return number is syllables in a word
         **Syllable: contigous sequence of 1 or more vowels 
         ***exception: lone "e" at the end of a word UNLESS the word 
         ***has no other vowels.    
         */
        
        boolean vowelState = isVowel(word.charAt(0));
        int syllables = vowelState?1:0;
        for (char c : word.toCharArray()) {
            if (isVowel(c) != vowelState) {
                syllables++;
            }
            vowelState = isVowel(c);
        }
        return syllables;
    }
    
    private boolean isVowel(char c) 
    {
        return "aeiouy".indexOf(Character.toLowerCase(c)) != -1;
    }

    protected List<String> getTokens(String pattern) {   
        ArrayList<String> tokens = new ArrayList<String>();
        Pattern tokSplitter = Pattern.compile(pattern);
        Matcher m = tokSplitter.matcher(text);
        
        while (m.find()) {
            tokens.add(m.group());
        }
        
        return tokens;
    }

}
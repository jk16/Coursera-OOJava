package testScripts;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Document 
{
    private String text;

    public Document() {
        text = "Here is some text. Do we understand it? Let's get excited!";
    }

    public String getText() {
        return text;

    }

    public int getNumWords() {
        List<String> tokens = getTokens("[a-zA-Z]+");
        return tokens.size();
    }

    public int getNumSentences() {
        //Sentence: Ends with "(.!?)"
        List<String> tokens = getTokens("[^!?.]+");
        return tokens.size();
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

    public int getNumSyllables()
    {
        //TODO: Implement this method.  See the Module 1 support videos 
        // if you need help.
        //see countSyllables method in Document.java
        int syllableCounter = 0;
        List<String> words = getTokens("[a-zA-Z]+");
        for (String word: words) {
            syllableCounter += countSyllables(word);
        }
        
        return syllableCounter;
    }

    public int countSyllables(String word)
    {
        // TODO: Implement this method so that you can call it from the 
        /*
         * Return number is syllables in a word
         **Syllable: contigous sequence of 1 or more vowels 
         ***exception: lone "e" at the end of a word UNLESS the word 
         ***has no other vowels.    
         */
        VowelState w = new VowelState(word);
        System.out.println(w);
        
        
        return 0;
    }

}
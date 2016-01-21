package testScripts;

import java.util.List;

public class BasicDocument extends Document
{
    public BasicDocument(String text) {
        super(text);
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
    public int getNumSyllables() {
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
    public static void main(String[] args) {
        BasicDocument bc = new  BasicDocument("Hello my name is Jonathan. Lets get started!");
        
    }

}

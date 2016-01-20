package testScripts;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
  //comments
public abstract class Document {
    private static String text;

    public Document() {
    	super();
    	System.out.println("called ctor");
        text = "Here is some text. Do we understand it? Let's get excited!";
    }
    
    public static String getText() {
        return text;
    }

//    public int getNumWords() {
//        List<String> tokens = getTokens("[a-zA-Z]+");
//        return tokens.size();
//    }
//
//    public int getNumSentences() {
//        //Sentence: Ends with "(.!?)"
//        List<String> tokens = getTokens("[^!?.]+");
//        return tokens.size();
//    }
//
//    protected List<String> getTokens(String pattern) {   
//        ArrayList<String> tokens = new ArrayList<String>();
//        Pattern tokSplitter = Pattern.compile(pattern);
//        Matcher m = tokSplitter.matcher(text);
//        
//        while (m.find()) {
//            tokens.add(m.group());
//        }
//        
//        return tokens;
//    }
}
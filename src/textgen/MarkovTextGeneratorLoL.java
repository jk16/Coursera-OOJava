package textgen;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

/** 
 * An implementation of the MTG interface that uses a list of lists.
 * @author UC San Diego Intermediate Programming MOOC team 
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList; 
	private ListNode dummyNode = new ListNode("DUMMY_NODE");
	// The starting "word"
	private String starter;
	
	// The random number generator
	private Random rnGenerator;
	
	public MarkovTextGeneratorLoL(Random generator)
	{
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}
	
	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText)
	{
		// TODO: Implement this method
		//init
		String[] listWords = sourceText.replaceAll("[^a-zA-Z ]", "").split("\\s+");
		//split by spaces
//		String[] listWords = sourceText.split("[\\s]+");

		//initialize
		String starter = listWords[0];
		String prevWord = starter;
		
		//add a ListNode with the first word in listWords to wordList
		wordList.add(new ListNode(listWords[0]));
		
		ListNode currentNode;
		String w;
		///for all the words
		for (int i=1; i<listWords.length; i++) {
			w = listWords[i];
			
			//is prevWord inside wordList?
			boolean prevWordInsideWordList = prevWordInsideWordList(prevWord);
//			if the prevWord is in the wordList:
//				get prevWord
//				place "w" after that prevWord
			if(prevWordInsideWordList) {
				currentNode = getNode(prevWord);
				currentNode.addNextWord(w);
			}
			else {
//				prevWord is not in the wordList:
//				add prevWord to the wordList
				System.out.println("prevWord is: " + prevWord +" and w = " + w );
				wordList.add(new ListNode(prevWord));
//				get the prevWord Node
				currentNode = getNode(prevWord);
//				add "w" after the prevWord Node
//				System.out.println(w);
				currentNode.addNextWord(w);
			}
			
			prevWord = w;
			
		}
			
	 } //end train
	public ListNode getNode(String prevWord) {
//		for ListNode n in wordList:
		for (ListNode n: wordList) {
//			if prevWord == n.getWord():
			if(prevWord.toLowerCase().equals(n.getWord().toLowerCase())) {
//				return n;
				return n;
			}
		}
		return null;
	}
	
	public boolean prevWordInsideWordList(String prevWord) {
//		for ListNode n in wordList:
		for (ListNode n : wordList) {
//			if prevWord == n.getWord():
			if(prevWord.toLowerCase().equals(n.getWord().toLowerCase())) {
				return true;
			}
		}
		
		return false;
	}
	
	public List<ListNode> getwordList() {
		return wordList;
	}

	
	/** 
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) {
		if(wordList.size() == 0)
			return null;
//		set "currWord" to be the starter word
		starter = wordList.get(0).getWord();
		String currWord = starter;
//		set "output" to be ""
		String outputText = "";
//		add "currWord" to output
		ListNode currNode;
//		while you need more words
		for(int i=0; i<numWords; i++) {
//		    find the "node" corresponding to "currWord" in the list
			currNode = findNode(currWord);
			if(currNode == null) {
				//go back to the beggining
				currNode = findNode(starter);
			}
			
			//if word in currNode is null
				
//		    select a random word "w" from the "wordList" for "node"
			String nextWord = currNode.getRandomNextWord(rnGenerator);
			System.out.println(currNode.getWord());
//		    add "w" to the "output"
			outputText += nextWord + " ";
//		    set "currWord" to be "w" 
			currWord = nextWord;
//		    increment number of words added to the list
		}
		return outputText;
	}
	
	private ListNode findNode(String key) {
		for(ListNode n : wordList) {
			if(n.getWord().equals(key))
				return n;
		}
		
		
		return null;
	}
	
	
	// Can be helpful for debugging
	@Override
	public String toString()
	{
		String toReturn = "";
		for (ListNode n : wordList)
		{
			toReturn += n.toString();
		}
		return toReturn;
	}
	
	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText)
	{
		// TODO: Implement this method.
		wordList = new LinkedList<ListNode>();
		train(sourceText);
	}
	
	// TODO: Add any private helper methods you need here.
	
	
	/**
	 * This is a minimal set of tests.  Note that it can be difficult
	 * to test methods/classes with randomized behavior.   
	 * @param args
	 */
	public static void main(String[] args)
	{
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
//		System.out.println(textString);
		gen.train(textString);
//		System.out.println(gen);
//		System.out.println(gen.generateText(20));
		String textString2 = "You say yes, I say no, "+
				"You say stop, and I say go, go, go, "+
				"Oh no. You say goodbye and I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"I say high, you say low, "+
				"You say why, and I say I don't know. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"Why, why, why, why, why, why, "+
				"Do you say goodbye. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"You say yes, I say no, "+
				"You say stop and I say go, go, go. "+
				"Oh, oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello,";
//		System.out.println(textString2);
		gen.retrain(textString2);
//		System.out.println(gen);
//		System.out.println(gen.generateText(20));
	}

}

/** Links a word to the next words in the list 
 * You should use this class in your implementation. */

class ListNode
{
    // The word that is linking to the next words
	private String word;
	
	// The next words that could follow it
	private List<String> nextWords;
	
	ListNode(String word)
	{
		this.word = word;
		nextWords = new LinkedList<String>();
	}
	
	public String getWord()
	{
		return word.replaceAll("[^a-zA-Z ]", "");
	}

	public void addNextWord(String nextWord)
	{
		nextWords.add(nextWord);
	}
	
	public String getRandomNextWord(Random generator)
	{
		// TODO: Implement this method
	    //if there are no words throw an exception
		boolean noWords = nextWords.size() == 0;
		if(noWords)
			return null;
		
		int i = generator.nextInt(nextWords.size());
	    return nextWords.get(i);
	}

	public String toString()
	{
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}
	
}



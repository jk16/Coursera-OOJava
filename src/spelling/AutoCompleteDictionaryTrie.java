package spelling;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author You
 *
 */
public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    

    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
	}
    
	
	public TrieNode getRoot() {
		return root;
	}
	
	public boolean charIsInTrieNode(char c, TrieNode t) {
		//if its not null then its in the TrieNide so return true
		return t.getChild(c) != null;
	}
    
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should ignore the word's case.
	 * That is, you should convert the string to all lower case as you insert it. */
	public boolean addWord(String word)
	{
		
		if (word.length() == 0) {
			if(root.endsWord())
				return false;
			
			root.setEndsWord(true);
			return true;
		}
		
		//get the first letter of a word
		char c = Character.toLowerCase(word.charAt(0));
		//starting from the root, use charInTrieNide to keep track of each TrieNode
		TrieNode charInTrieNode = root;
		charInTrieNode.insert(c);
		charInTrieNode = charInTrieNode.getChild(c);
		//use finalIndex to determine if its a word
		boolean finalIndex ;
		
		if(word.length() == 1) {
			//one letter word
			charInTrieNode.setEndsWord(true);
		}
			
		
		for(int i=1; i<word.length();i++){
			c = Character.toLowerCase(word.charAt(i));
			//on the first iteration check to see if the word exists:
			if(charIsInTrieNode(c, charInTrieNode)) {
				System.out.println("charIsInTrieNode(c, charInTrieNode)");
				//use this TrieNode to add to this TrieNode
				
				finalIndex = i == (word.length()-1);
				if(finalIndex) {
					//get the TrieNode with that char
					charInTrieNode = charInTrieNode.getChild(c);
					//set its EndsWord variable to true
					charInTrieNode.setEndsWord(true);
				}
				charInTrieNode = charInTrieNode.getChild(c);
				c = Character.toLowerCase(word.charAt(i));
				continue;
			}
			
			//check if letter is inside the TrieNode
			if (charInTrieNode.getChild(c) == null) {
				//if its not in the trie node:
				//	insert character into the trie node
				charInTrieNode.insert(c);
				//check if its a word:
				//	* if its a word then it will be at the last letter
				finalIndex = i == (word.length()-1);
				//if its at the last letter
				if(finalIndex) {
					//get the TrieNode with that char
					charInTrieNode = charInTrieNode.getChild(c);
					//set its EndsWord variable to true
					charInTrieNode.setEndsWord(true);
					continue;
				}
				//if its not the last letter
					
				charInTrieNode = charInTrieNode.getChild(c);
				charInTrieNode.setEndsWord(false);
			
			}
		}
		
	    return true;
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
	    //TODO: Implement this method
	    return 0;
	}
	
	
	/** Returns whether the string is a word in the trie */
	@Override
	public boolean isWord(String s) 
	{
	    // TODO: Implement this method
		if (s == "") {
			System.out.println("SPACE");
			return false;
		}
        char c = Character.toLowerCase(s.charAt(0));
        TrieNode currTrieNode = root;
        currTrieNode.insert(c);
        currTrieNode = currTrieNode.getChild(c);
        
        TrieNode TrieNodeHasChar;
        for(int i=1; i<s.length();i++) {
        	c = Character.toLowerCase(s.charAt(i));
        	TrieNodeHasChar = currTrieNode.getChild(c);
        	
        	
			if(charIsInTrieNode(c, currTrieNode)) {
//				System.out.println("A TrieNide contains this character: " + c );
				currTrieNode = currTrieNode.getChild(c);
				continue;
			} 
			else {
				return false;
			}
        }
		return currTrieNode.endsWord();
	}

	/** 
	 *  * Returns up to the n "best" predictions, including the word itself,
     * in terms of length
     * If this string is not in the trie, it returns null.
     * @param text The text to use at the word stem
     * @param n The maximum number of predictions desired.
     * @return A list containing the up to n best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
    	 // This method should implement the following algorithm:
    	 // 1. Find the stem in the trie.  If the stem does not appear in the trie, return an
    	 //    empty list
    	 TrieNode currentTrie = root;
    	 TrieNode nextTrie = null;
    	 List<String> completionsList = new ArrayList<String>();
    	 Character c = null;
    	 for (Character l: prefix.toCharArray()) {
    		 c = l.toLowerCase(l);
    		 nextTrie = currentTrie.getChild(c);
    		 if(nextTrie == null) {
    			 return completionsList;
    		 }
    		 currentTrie = nextTrie;
    	 }
    	 // 2. Once the stem is found, perform a breadth first search to generate completions
    	 //    using the following algorithm:
    	 //     * Create a queue (LinkedList)
//    	 		* add the node that completes the stem to the back
    	 //       of the list.
    	 LinkedList<TrieNode> queue = new LinkedList<TrieNode>();
    	 queue.add(currentTrie);
    	 //    Create a list of completions to return (initially empty)
    	 //    While the queue is not empty and you don't have enough completions:
    	 int n = 0;
    	 while(!queue.isEmpty() && n<numCompletions) {    	 
    	 //       remove the first Node from the queue
    		 nextTrie = queue.removeFirst();
    	 //       If it is a word, add it to the completions list
    		 if(nextTrie.endsWord()) {
    			 completionsList.add(nextTrie.getText());
    			 n++;
    		 }
    	 //       Add all of its child nodes to the back of the queue
    		 for (Character nextChar : nextTrie.getValidNextCharacters()) {
    			 queue.add(nextTrie.getChild(nextChar));
    		 }
    	 }
    	 // Return the list of completions
    	 
         return completionsList;
     }

 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
 	

	
}
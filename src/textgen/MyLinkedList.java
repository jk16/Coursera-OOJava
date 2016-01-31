package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TOD: Implement this method
		size = -1;
        head = new LLNode<E>(null);
        tail = new LLNode<E>(null);
        
        head.next = tail;
        tail.prev = head;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		// TOD: Implement this method
		
		//initialize newTempNode and lastNode
		LLNode<E> newTempNode = new LLNode<E>(element);
		LLNode<E> lastNode = tail.prev;
		
		lastNode.next = newTempNode;
		newTempNode.prev = lastNode;
		newTempNode.next = tail;
		tail.prev = newTempNode;
		
		size++;
		
		return false;
	}
	
	public void printLL() {
		LLNode<E> fromHead = head.next;
		LLNode<E> fromTail = tail.prev;
		for(int i=0; i<=size; i++){
			System.out.println("fromHead = " + fromHead.data);
			fromHead = fromHead.next;
			
		}
		
		for(int i=0; i<=size; i++) {
			System.out.println("from tail = " + fromTail.data);
			fromTail = fromTail.prev;
		}
	}
	
	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		// TOD: Implement this method.
		if(index <0 || index>size || size < 0 ) {
			throw new IndexOutOfBoundsException("Index out of range!!");
		}
		
		LLNode<E> tempNode = head.next;//first Node
		
		for(int i=0; i<=index; i++){
			if(i == index) {
				break;
			}
			tempNode = tempNode.next;
		}
		return tempNode.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		// TODO: Implement this method
//		If elements exist at that index, you will move elements at that index (and beyond) up,
		//go to (index - 1) and insert then point the LLInserted to everything else
		if(index <= 0){
			throw new IndexOutOfBoundsException("Must be greater than 0!");
		}
		LLNode<E> tempNodePrev = head.next; //Head/Tail are sentinel nodes, they do not count as LL
		LLNode<E> tempNodeAfter;
		//loop until at index - 1
		int indexBeforeInsertIndex = index - 1;
		LLNode<E> newNode = new LLNode<E>(element);
		for(int i=0; i<indexBeforeInsertIndex; i++) {
			if(i==indexBeforeInsertIndex) {
				break;
			}
			tempNodePrev = tempNodePrev.next; 
									
		}//end for loop
		//Point to after we point from {..B}-->element
		tempNodeAfter = tempNodePrev.next;
		
		tempNodePrev.next = newNode; //{..B}-->element
		newNode.prev = tempNodePrev;//{..B}<--element
		
		newNode.next = tempNodeAfter;// element -->{D..}
		tempNodeAfter.prev = newNode; // element<--{D..}
		size++;
		
	}


	/** Return the size of the list */
	public int size() 
	{
		// TOD: Implement this method
		return size + 1;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		// TOD: Implement this method
		LLNode<E> TempNodeToRemove = head.next; //first Node
		LLNode<E> left;
		LLNode<E> right;
		
		if(index > size || index < 0) {
			throw new IndexOutOfBoundsException("Improper value!");
		}
		
		for(int i=0; i<index-1; i++){
			if(i == index) {
				break;
			}
			TempNodeToRemove = TempNodeToRemove.next;
		}
		//H--> A--> B--> C--> D--> T
		
		LLNode<E> prevNode = TempNodeToRemove.prev;
		LLNode<E>nextNode = TempNodeToRemove.next;
		
		prevNode.next = nextNode;
		nextNode.prev = prevNode;
		
		
		size--;
		return TempNodeToRemove.data;
	}
	

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// TODO: Implement this method
		//return the old value held at that index
		LLNode<E> temp = head.next;
		
		for(int i=0; i<index; i++) {
			if (index == i) {
				break;
			}
			temp = temp.next;
		}
		E old = temp.data;
		temp.data = element;
		System.out.println(old);
		return old;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor
	
	
	
	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
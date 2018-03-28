// File LinkedDequeueTest.java 
/**
 *  This class represents a Dequeue ("double-ended queue) datatype implemented using a singly-linked
 *  list with appropriate operations.
 *
 * @author:	Henry Leitner, Seth McNaughton
 * @version: Last modified on April 26, 2014
 * Implements a queue with addable and removable parts at the head and tail of the queue.
 */


public class LinkedDequeueTest
{
	public static void main (String [] args)
	{
		LinkedDequeue q = new LinkedDequeue();
		if (q.isEmpty()) 
			System.out.println("The queue is empty."); 
		else 
			System.out.println("The queue is not empty.");
		
		System.out.println("The size of the queue is: " + q.size());
		q.tailAdd("a");
		q.tailAdd("b");
		q.tailAdd("3");
		System.out.println("The size of the queue is: "+ q.size());
		System.out.println("Peek at tail: " + q.tailPeek());
		System.out.println("Peek at head: " + q.headPeek());
		
		if (q.isEmpty()) 
			System.out.println("The queue is empty."); 
		else 
			System.out.println("The queue is not empty.");		
		
		System.out.print(q);
		q.headAdd("d");
		System.out.print(q);
		System.out.println("The size of the queue is: "+ q.size());
		
		System.out.println("Peek at tail: " + q.tailPeek());
		System.out.println("Peek at head: " + q.headPeek());
		System.out.println("Object removed from head: " + q.headRemove());
		System.out.println("Object removed from head: " + q.headRemove());
		System.out.println("Object removed from tail: " + q.tailRemove());
		System.out.println("Peek at tail: " + q.tailPeek());
		System.out.println("Peek at head: " + q.headPeek());
		System.out.println("Object removed from tail: " + q.tailRemove());
		
		if (q.isEmpty()) 
			System.out.println("The queue is empty."); 
		else 
			System.out.println("The queue is not empty.");
		System.out.println("The size of the queue is: " + q.size());

	}
}


class LinkedDequeue
{
	private QueueNode tail;		//end of queue
	private QueueNode head;		//start of queue
	private int count;

	/**
	*  The QueueNode class is an inner class implemented to model a queue node;
	*  it can contain an Object type of data, and also holds the link to the
	*  next node in the queue.  If there are no other nodes, the link will be null.
	*/
	class QueueNode        // an inner class
	{
		private Object item;
		private QueueNode link;
	}


	/**
	*  This constructor for the class will set up the needed instance variables
	*  which begin with no nodes present and thus are set to null.
	*/
	public LinkedDequeue ()
	{
		tail = head = null;
		count = 0;
	}


	/**
	*  This method will construct a new QueueNode and add it onto the head
	*  of the queue, essentially a FastPass at Disney. If it is the first node 
	*  added into the queue, both head and tail will reference it, otherwise it is added
	*  using the head variable.  The node counter is also updated.
	*
	*  @param   o     The Object to be added as part of a new QueueNode
	*/
	public void headAdd (Object o)
	{
		QueueNode temp = new QueueNode();
		temp.item = o;
		temp.link = null;

		if (head == null) head = tail = temp;
		else
		{
			temp.link = head;
			head = temp;
		}
		count++ ;
		System.out.println("Object added to head: " + o.toString());
	}

	
	/**
	* This method will return the first node in the queue. If the queue is empty, it will
	* return null.
	*/
	public Object headPeek()
	{
		if ( isEmpty() ) return null;
		else
		{
			return head.item;
		}
	}


	/**
	*  This method will remove an item from the head of the queue.  
	*  In doing so, the queue variables are reset to detach the node,
	*  and the Object which it contains is then returned.  The queue
	*  counter is also updated to reflect the removal.
	*
	*  @return     The Object which was just removed from the queue.
	*/
	public Object headRemove ()
	{
		if ( isEmpty() ) return null;
		else
		{
			Object tempItem = head.item;
			head = head.link;
			if (head == null)   tail = null;
			count-- ;
			return tempItem;
		}
	}


	/**
	*  This method will test for an empty queue and return a boolean result.
	*
	*  @return     true for an empty list; false if the queue contains QueueNodes.
	*/
	public boolean isEmpty()
	{
		return ( count == 0 );
	}


	/**
	*  This method will evaluate and return the number of elements in the Dequeue
	*
	*  @return     An int describing the current number of nodes in the queue
	*/
	public int size()
	{
		return count;
	}


	/**
	*  This method will construct a new QueueNode and add it onto the tail
	*  of the queue (standard FIFO behavior). If it is the first node added into
	*  the queue, both head and tail will reference it, otherwise it is added
	*  using the tail variable.  The node counter is also updated.
	*
	*  @param   o     The Object to be added as part of a new QueueNode
	*/
	public void tailAdd (Object o)
	{
		QueueNode temp = new QueueNode();
		temp.item = o;
		temp.link = null;

		if (tail == null) head = tail = temp;
		else
		{
			tail.link = temp;
			tail = temp;
		}
		count++ ;
		System.out.println("Object added to tail: " + o.toString());
	}

	/** 
	* This method will return the last node in the queue. If the queue is empty, it will
	* return null.
	*/
	public Object tailPeek ()
	{
		if (isEmpty() ) 
     	{
     		return null;
     	}
        else 
        {
			return tail.item;
		}
	}


	/**
	*  This method will remove an item from the tail of the queue.  
	*  In doing so, the queue variables are reset to detach the node,
	*  and the Object which it contains is then returned.  The queue
	*  counter is also updated to reflect the removal.
	*
	*  @return     The Object which was just removed from the queue.
	*/
	public Object tailRemove ()
	{
     	if (isEmpty() ) 
     	{
     		return null;
     	}
        else 
        {
			Object tempItem = tail.item;
			QueueNode p = head;
			count-- ;
			if (p.link == null) 	head = tail = null;
			else
			{
				while (p.link.link != null)
				{
					p = p.link;
				}
				tail = p;
				
			}

			p.link = null;
			return tempItem;
		}
	}


	/**
	* This method writes the list of Objects contained in the queue.
	*/
	public String toString ()
	{
		String s = "Queue contains: \n";
		QueueNode nextNode = head;
		
		while (nextNode != null)
		{
			s += "  " + nextNode.item.toString() + "\n" ;
			nextNode = nextNode.link;
		}
		return s;
	}

}


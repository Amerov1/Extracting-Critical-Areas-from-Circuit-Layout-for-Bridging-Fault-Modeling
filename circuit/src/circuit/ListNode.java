package circuit;

import circuit.*;

public class ListNode extends Rectangle {
	private ListNode next;
   private ListNode last;
	private int ID;

	public ListNode(int x, int y, int width, int height) {
		super(x, y, width, height);
		this.next = null;
	}

	public ListNode getNext() {
		return this.next;
	}
public ListNode getLast()
{
	return this.last;
}

public void setLast(ListNode last)
{
	this.last=last;
}

	public void setNext(ListNode p) {
		this.next = p;
	}
	public void  setID(int id)
	{
		this.ID=id;
	}
	public int getID()
	{
		return this.ID;
	}
}

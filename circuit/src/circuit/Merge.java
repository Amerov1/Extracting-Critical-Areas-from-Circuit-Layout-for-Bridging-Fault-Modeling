package circuit;

import java.util.ArrayList;

public class Merge {
   //This methods for sorting Rectangles
	public void MergeSort(Rectangle[] rects) {
		int inputLength = rects.length;
		if (inputLength < 2)
			return;
		int midIndex = inputLength / 2;
		Rectangle leftHalf[] = new Rectangle[midIndex];
		Rectangle rightHalf[] = new Rectangle[inputLength - midIndex];
		for (int i = 0; i < midIndex; i++) {
			leftHalf[i] = rects[i];
		}
		for (int j = midIndex; j < inputLength; j++) {
			rightHalf[j - midIndex] = rects[j];
		}
		MergeSort(leftHalf);
		MergeSort(rightHalf);
		merging(rects, leftHalf, rightHalf);
	}
	private void merging(Rectangle original[], Rectangle left[],
			Rectangle right[]) {
		int lengthLeft = left.length;
		int lengthRight = right.length;
		int i = 0, j = 0, k = 0;
		while (i < lengthLeft && j < lengthRight) {// left[i].getDistance()<=right[j].getDistance()
			if (// left[i].getDistance()==right[j].getDistance()
			left[i].getTopLeft().getX() <= right[j].getTopLeft().getX()
					&& left[i].getTopLeft().getY() <= right[j].getTopLeft()
							.getY()) {
				original[k] = left[i];
				i++;
			} else {
				original[k] = right[j];
				j++;
			}
			k++;
		}
		while (i < lengthLeft) {
			original[k] = left[i];
			i++;
			k++;
		}
		while (j < lengthRight) {
			original[k] = right[j];
			j++;
			k++;
		}

	}
	//This methods checks if the two rectangles are mergeable.
	public boolean canMerge(Rectangle r1, Rectangle r2) {
		if (r1 == null || r2 == null) {
			return false;
		}
		if (r1.getBottomRight().getY() == r2.getBottomLeft().getY()
				&& r1.getTopRight().getY() == r2.getTopLeft().getY()
				&& r1.getTopRight().getX() >= r2.getTopLeft().getX()
				&& r1.getTopLeft().getX() <= r2.getTopLeft().getX()
				&& r1.getBottomRight().getX() >= r2.getBottomLeft().getX()) {

			return true;
		} else if (r2.getTopLeft().getX() == r1.getBottomLeft().getX()
				&& r2.getTopRight().getX() == r1.getBottomRight().getX()
				&& r2.getTopRight().getY() <= r1.getBottomRight().getY()
				&& r2.getTopRight().getY() >= r1.getTopRight().getY()
				&& r2.getTopLeft().getY() <= r1.getBottomLeft().getY()) {

			return true;
		}
		return false;
	}
     //This methods merges two rectangles into one.
	public Rectangle merge(Rectangle r1, Rectangle r2) {
		Rectangle temp = null;
		if (r1.getBottomRight().getY() == r2.getBottomLeft().getY()
				&& r1.getTopRight().getY() == r2.getTopLeft().getY()
				&& r1.getTopRight().getX() >= r2.getTopLeft().getX()
				&& r1.getTopLeft().getX() <= r2.getTopLeft().getX()
				&& r1.getBottomRight().getX() >= r2.getBottomLeft().getX()) { 
			temp = new Rectangle(r1.getTopLeft().getX(),
					r1.getTopLeft().getY(), r2.getTopRight().getX()
							- r1.getTopLeft().getX(), r2.getBottomRight()
							.getY() - r2.getTopRight().getY());
			temp.setDistance(r1.getDistance());
			return temp;
		} else if (r2.getTopLeft().getX() == r1.getBottomLeft().getX()
				&& r2.getTopRight().getX() == r1.getBottomRight().getX()
				&& r2.getTopRight().getY() <= r1.getBottomRight().getY()
				&& r2.getTopRight().getY() >= r1.getTopRight().getY()
				&& r2.getTopLeft().getY() <= r1.getBottomLeft().getY()) { 
			temp = new Rectangle(r1.getTopLeft().getX(), r1.getTopRight()
					.getY(), r1.getTopRight().getX() - r1.getTopLeft().getX(),
					r2.getBottomRight().getY() - r1.getTopRight().getY());
			temp.setDistance(r1.getDistance());
			return temp;
		}
		return null;
	}
    //This method takes a list of Rectangles, merges all mergeable rectangles and returns the list.
	public Rectangle[] mergeAll(Rectangle rects[]) {

		Rectangle temp = new Rectangle(0, 0, 0, 0);
		ArrayList<Rectangle> rs = new ArrayList<Rectangle>(rects.length);

		MergeSort(rects);

		for (int i = 0; i < rects.length; i++) {
			temp = rects[i];
			for (int j = i + 1; j < rects.length; j++) {
				if (temp != null && rects[j] != null) {
					if (canMerge(temp, rects[j])
							&& temp.getDistance() == rects[j].getDistance()) {
						temp = merge(temp, rects[j]);
						rects[j] = null;
					}

				}

			}
			if (temp != null)
				rs.add(temp);
			rects[i] = null;
		}

		for (int i = 0; i < rects.length; i++) {
			if (rects[i] != null) {
				rs.add(rects[i]);
			}
		}
		Rectangle rsNew[] = new Rectangle[rs.size()];
		rsNew = rs.toArray(rsNew);
		return rsNew;

	}
	//This method check if two segments can be merged.
	public int canMergeLineSegments(ListNode n1,ListNode n2)
	{
		if(n1!=null&&n2!=null)
		if(n1.getID()==n2.getID())
		{
			return n1.getID();
		}
		return 0;
	}
	//This method merges two segments
	public ListNode mergeLineSegments(ListNode n1,ListNode n2)
	{
		
		if(canMergeLineSegments(n1,n2)==1)
		{
			return new ListNode(n1.getTopLeft().getX(),n1.getTopLeft().getY(),n2.getTopRight().getX()-n1.getTopLeft().getX(),n1.getHeight());
		} else if(canMergeLineSegments(n1,n2)==2)
		{
			return new ListNode(n1.getTopLeft().getX(),n1.getTopLeft().getY(),n1.getWidth(),n2.getBottomLeft().getY()-n1.getTopLeft().getY());
		} else if(canMergeLineSegments(n1,n2)==3)
		{
			return new ListNode(n2.getTopLeft().getX(),n2.getTopLeft().getY(),n2.getWidth(),n1.getBottomLeft().getY()-n2.getTopLeft().getY());
		}
		
		return null;	
	}
       //This method merge all mergeable segments of one path 
	public SinglyLinkedList mergePathSegments(SinglyLinkedList path, int x,
			int y, int bigX, int bigY) {
		ListNode temp = path.getHead();
		boolean button = true;
		SinglyLinkedList newPath = new SinglyLinkedList();
		ListNode temp2 = temp;
		ListNode temp3 = null;
		while (temp != null) {

			if (canMergeLineSegments(temp2, temp) == 0 && temp != temp2) {

				newPath.insertEelements(mergeLineSegments(temp2, temp3));
				temp3 = temp;
				temp2 = temp;
				temp = temp.getNext();

				button = false;
			}
			if (button) {
				temp3 = temp;
				temp = temp.getNext();
			}
			button = true;
		}
		if (canMergeLineSegments(temp2, temp3) != 0) {

			newPath.insertEelements(mergeLineSegments(temp2, temp3));
		} else {
			if (temp3 == temp2)
				newPath.insertEelements(temp3);
		}

		newPath.setPathRange(x, y, bigX, bigY);

		return newPath;
	}
}

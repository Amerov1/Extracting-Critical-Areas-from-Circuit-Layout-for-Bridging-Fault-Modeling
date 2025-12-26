package circuit;

import java.io.ObjectInputStream.GetField;

public class Overlap {

        //This method checks if two rectangles overlap with each other.
	public  static boolean RectOverlap(Rectangle r1, Rectangle r2)//RRAA
	{
		if(r1!=null &&r2!=null)
		if(r1.getTopLeft().getX()<r2.getBottomRight().getX()
				&&r1.getBottomRight().getX()>r2.getTopLeft().getX()
				&&r1.getTopLeft().getY()<r2.getBottomRight().getY()
				&&r1.getBottomRight().getY()>r2.getTopLeft().getY())
		{
			return true;
		}
		return false;

	}
	//This method checks if a rectangle overlap with a path.
	public static boolean pathOverlapping(Rectangle r1,SinglyLinkedList path)
	{
		if(path!=null){
		ListNode phead=path.getHead();
		if(r1!=null)
		while(phead!=null)
		{
			
			if(RectOverlap(r1,phead))
			{
				return true;
			}
			phead=phead.getNext();
			
		}}
		return false;
	}
	//This method checks if two paths overlap with each other.
	public static boolean pathToPathOverlap(SinglyLinkedList path1,SinglyLinkedList path2)
	{
	if(path1!=null && path2 !=null)
	{	for(ListNode rect1=path1.getHead();rect1!=null;rect1=rect1.getNext())
		{
			for(ListNode rect2=path2.getHead();rect2!=null;rect2=rect2.getNext())
			{
				if(RectOverlap(rect1,rect2))
				{
					return true;
				}
			}
		}
	}
		return false;
	}
	//This method checks if a rectangle overlaps with a set of paths
	public  static boolean rectWithPaths(Rectangle rect1,SinglyLinkedList paths[])
	{
		if(paths.length==0)
		{
			return false;
		}
		for(int i=0;i<paths.length;i++)
		{     
			if(paths[i]!=null)
			if(pathOverlapping(rect1,paths[i]))
			{
				return true;
			}
		}
		return false;
	}
	//The method checks whether a surface overlaps with a path region.
	public static boolean regionWithPaths(Rectangle region,SinglyLinkedList paths [])
	{
		if(paths.length==0)
		{
			return false;
		}
		for(int i=0;i<paths.length;i++)
		{     
			if(paths[i]!=null)
			if(RectOverlap(region,paths[i].getPathRange()))
			{
				return true;
			}
		}
		return false;
	}
}

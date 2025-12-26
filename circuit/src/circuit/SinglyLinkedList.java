package circuit;

import Drawing.DrawingCanvas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.*; 

public class SinglyLinkedList  {
	DrawingCanvas draw;
private ListNode head;
private ListNode last;
	private double dist;
	public static int threshold;
	public Distance distance;
	public Overlap overlap;
	public Merge merge;
	public static Point range;
	private Rectangle pathRange;
	public SinglyLinkedList()
	{
		distance= new Distance(this);
		overlap=new Overlap();
		merge=new Merge();
	}

	public void setPathRange(int x, int y, int width, int height) {
		pathRange = new Rectangle(x, y, width, height);

	}

	public void setPathRange(Rectangle pathRange) {
		this.pathRange = pathRange;
	}

	public Rectangle getPathRange() {
		return this.pathRange;
	}

	public ListNode getHead() {
		return head;
	}

	public ListNode getLast() {
		return last;
	}

	public double getDist() {
		return this.dist;
	}

	public void setDist(double d) {
		this.dist = d;
	}

	// This method is for printing an element.
	public void printElements() {
		if (head == null) {
			System.out.println("head is still empty");
			return;
		}
		ListNode current = head;
		while (current != null) {
			System.out.println(current);
			current = current.getNext();
		}
	}

	// This method is for inserting a new element.
	public void insertEelements(ListNode n) {
		if (head == null) {
			head = n;
			return;
		} else {
			ListNode current = head;
			while (current.getNext() != null) {
				current = current.getNext();
			}
			current.setNext(n);
			// last=n;
		}
	}
	public int  findLength() {
		int len = 0;
		if (head == null) {
		
		} else {
			ListNode current = head;
			while (current != null) {
				len += 1;
				current = current.getNext();
			}
			 
		}
		return len;
	}
   //This method is for generating surfaces. (the first part)
	public Rectangle[] generateSurfaces(Rectangle r1,Rectangle r2,int length)
	{
		ArrayList<Rectangle> r =new ArrayList<Rectangle>(length*2);
		int i=0;
	 if(r2.getBottomLeft().getY()>r1.getBottomRight().getY()
			&& r2.getTopLeft().getY()<r1.getBottomRight().getY()
		//	&& getDist()==(int)getDist()
			&&r2.getTopLeft().getX()>r1.getBottomRight().getX()
			&&r1.getTopLeft().getY()< r2.getTopLeft().getY())
		{//System.out.println("--1--");
			 r.add(new Rectangle(r1.getBottomRight().getX(),r2.getTopLeft().getY(),r2.getTopLeft().getX()-r1.getBottomRight().getX(),r1.getBottomRight().getY()-r2.getTopLeft().getY()));//1
		r.get(i).setDistance(r2.getTopLeft().getX()-r1.getBottomRight().getX());
	    r.get(i).setCase(1);
        i++;   
		}else if(r1.getTopRight().getY()>r2.getTopLeft().getY()
				&&r1.getTopRight().getY()<r2.getBottomLeft().getY()
				&&r1.getBottomRight().getY()>r2.getBottomLeft().getY()
				&&r1.getTopRight().getX()<r2.getBottomLeft().getX()
			//	&&getDist()==(int)getDist()
				)
		{//System.out.println("--3--");
		 r.add(new Rectangle(r1.getTopRight().getX(),r1.getTopRight().getY(),r2.getBottomLeft().getX()- r1.getTopRight().getX(),r2.getBottomLeft().getY()-r1.getTopRight().getY()));//2
		 r.get(i).setDistance(r2.getBottomLeft().getX()- r1.getTopRight().getX());
		 r.get(i).setCase(1);
		 i++;
		
		
		}if(//getDist()==(int)getDist()&&
				r2.getTopRight().getY()<r1.getTopLeft().getY()
				&&r2.getBottomLeft().getY()>r1.getTopLeft().getY()
				&&r2.getBottomRight().getX()<r1.getTopLeft().getX()
				&&r2.getBottomRight().getY()<r1.getBottomLeft().getY())
		{//System.out.println("--2--");
			r.add(new Rectangle(r2.getBottomRight().getX(),r1.getTopLeft().getY(),r1.getTopLeft().getX() - r2.getBottomRight().getX(),r2.getBottomRight().getY()-r1.getTopLeft().getY()));//3
		    r.get(i).setDistance(r1.getTopLeft().getX() - r2.getBottomRight().getX());
		    r.get(i).setCase(1);
		    i++;
		
		}else if(//getDist()==(int)getDist()&&
				r2.getTopRight().getY()>r1.getTopLeft().getY()
				&&r1.getBottomLeft().getY()>r2.getTopRight().getY()
				&&r2.getTopRight().getX()<r1.getTopLeft().getX()
				&&r2.getBottomRight().getY()>r1.getBottomLeft().getY())
		{//System.out.println("--4--");
			r.add(new Rectangle(r2.getTopRight().getX(),r2.getTopRight().getY(),r1.getBottomLeft().getX()-r2.getTopRight().getX(),r1.getBottomLeft().getY()-r2.getTopRight().getY()));//4 wrong!!! height negativ
		    r.get(i).setDistance(r1.getBottomLeft().getX()-r2.getTopRight().getX());
		    r.get(i).setCase(1);
		    i++;
		
		}if(//getDist()==(int)getDist()&&
				r2.getBottomRight().getY()>r1.getBottomRight().getY()
				&&r2.getTopRight().getY()>r1.getBottomRight().getY()
				&&r2.getTopRight().getX()<r1.getBottomRight().getX()
				&&r2.getTopRight().getX()>r1.getBottomLeft().getX()
				&&r2.getTopLeft().getX()<r1.getBottomLeft().getX())
		{//System.out.println("--5--");
		 r.add(new Rectangle(r1.getBottomLeft().getX(),r1.getBottomLeft().getY(),r2.getTopRight().getX()-r1.getBottomLeft().getX(),r2.getTopRight().getY()-r1.getBottomLeft().getY()));//5
		 r.get(i).setDistance(r2.getTopRight().getY()-r1.getBottomLeft().getY());
		 r.get(i).setCase(1);
		 i++;
		
		}else if(r2.getTopLeft().getY()>r1.getBottomLeft().getY()
				&&r2.getTopRight().getX()>r1.getBottomRight().getX()
				&&r1.getBottomLeft().getX()<r2.getTopLeft().getX() 
				&& r1.getBottomRight().getX()>r2.getTopLeft().getX()
			//	&&getDist()==(int)getDist()
				)
		{//System.out.println("--6--");
			r.add(new Rectangle(r2.getTopLeft().getX(),r1.getBottomLeft().getY(),r1.getBottomRight().getX()-r2.getTopLeft().getX(),r2.getTopLeft().getY()-r1.getBottomRight().getY()));//6
		 r.get(i).setDistance(r2.getTopLeft().getY()-r1.getBottomRight().getY());
		 r.get(i).setCase(1);
		 i++;
		}if(r2.getBottomLeft().getY()<r1.getTopRight().getY()
				&&r1.getTopRight().getX()>r2.getBottomLeft().getX()
				&&r1.getTopRight().getX()<r2.getBottomRight().getX()
				&&r1.getTopLeft().getX()<r2.getBottomLeft().getX()
				&&r2.getBottomLeft().getY()<r1.getBottomLeft().getY()
			//	&&getDist()==(int)getDist()
				)
		{//System.out.println("--7--");
			r.add(new Rectangle(r2.getBottomLeft().getX(),r2.getBottomLeft().getY(),r1.getTopRight().getX()-r2.getBottomLeft().getX(),r1.getTopRight().getY()-r2.getBottomLeft().getY()));//7
		 r.get(i).setDistance(r1.getTopRight().getY()-r2.getBottomLeft().getY());
		 r.get(i).setCase(1);
		 i++;
		
		}else if(//getDist()==(int)getDist()&&
				r1.getTopLeft().getX()<r2.getBottomRight().getX()
				&&r1.getTopLeft().getY()>r2.getBottomRight().getY()
				&&r1.getTopLeft().getX()>r2.getBottomLeft().getX()
				&&r1.getBottomLeft().getY()>r2.getBottomRight().getY()
				&&r1.getTopRight().getX()>r2.getBottomRight().getX())
		{//System.out.println("--8--");
			r.add(new Rectangle(r1.getTopLeft().getX(),r2.getBottomRight().getY(),r2.getBottomRight().getX()-r1.getTopLeft().getX(),r1.getTopLeft().getY()-r2.getBottomRight().getY()));//8
		 r.get(i).setDistance(r1.getTopLeft().getY()-r2.getBottomRight().getY());	
		 r.get(i).setCase(1);
		 i++;
			
		}if (r2.getTopLeft().getX()>r1.getTopRight().getX()
		&&r2.getBottomLeft().getX()>r1.getBottomRight().getX()
		&&r2.getTopLeft().getY()>=r1.getTopRight().getY()
		&&r2.getBottomLeft().getY()<=r1.getBottomRight().getY()
		&&r2.getBottomLeft().getY()>r1.getTopRight().getY()) {
			
			//System.out.println("--9--"); 
			r.add(new Rectangle(r1.getTopRight().getX(),r2.getTopLeft().getY(),(int)distance.distanceBetweenLineAndPoint(r1.getRight(),r2.getTopLeft()),r2.getBottomLeft().getY()-r2.getTopLeft().getY()));
			r.get(i).setDistance((int)distance.distanceBetweenLineAndPoint(r1.getRight(),r2.getTopLeft()));
			r.get(i).setCase(1);
		i++;
		} else if (r1.getTopLeft().getX()>r2.getTopRight().getX()
				&&r1.getBottomLeft().getX()>r2.getBottomRight().getX()
				&&r1.getTopLeft().getY()>=r2.getTopRight().getY()
				&&r1.getBottomLeft().getY()<=r2.getBottomRight().getY()) {
			
			//System.out.println("--10--"); 
			
			r.add(new Rectangle(r2.getTopRight().getX(),r1.getTopLeft().getY(),(int)distance.distanceBetweenLineAndPoint(r2.getRight(),r1.getBottomLeft()),r1.getBottomLeft().getY()-r1.getTopLeft().getY()));
			r.get(i).setDistance((int)distance.distanceBetweenLineAndPoint(r2.getRight(),r1.getBottomLeft()));
			r.get(i).setCase(1);
			i++;
		}if (	r2.getTopRight().getX()<r1.getTopLeft().getX()
				&&r2.getTopRight().getY()>=r1.getTopLeft().getY()
				&&r2.getBottomRight().getY()<=r1.getBottomLeft().getY()
				&&r2.getBottomRight().getX()<r1.getBottomLeft().getX()) {
			
		//	System.out.println("--11--"); 

			r.add(new Rectangle(r2.getTopRight().getX(),r2.getTopRight().getY(),(int)distance.distanceBetweenLineAndPoint(r1.getLeft(),r2.getBottomRight()),r2.getBottomRight().getY()-r2.getTopRight().getY()));
			r.get(i).setDistance((int)distance.distanceBetweenLineAndPoint(r1.getLeft(),r2.getBottomRight()));
			r.get(i).setCase(1);
		i++;
		} else if (r1.getTopRight().getX()<r2.getTopLeft().getX()
				&&r1.getTopRight().getY()>=r2.getTopLeft().getY()
				&&r1.getBottomRight().getY()<=r2.getBottomLeft().getY()
				&&r1.getBottomRight().getX()<r2.getBottomLeft().getX()) {
			
		//	System.out.println("--12--"); 
			r.add(new Rectangle(r1.getTopRight().getX(),r1.getTopRight().getY(),(int)distance.distanceBetweenLineAndPoint(r2.getLeft(),r1.getBottomRight()),r1.getBottomRight().getY()-r1.getTopRight().getY()));
			r.get(i).setDistance((int)distance.distanceBetweenLineAndPoint(r2.getLeft(),r1.getBottomRight()));
			r.get(i).setCase(1);
			i++;
		}if (r1.getTopLeft().getY()<r2.getTopLeft().getY()
				&&r1.getBottomLeft().getY()<r2.getTopLeft().getY()
				&&r2.getTopLeft().getX()>=r1.getBottomLeft().getX()
				&&r2.getTopLeft().getX()<r1.getBottomRight().getX()
				&&r2.getTopRight().getX()<=r1.getBottomRight().getX()) {
			
		//	System.out.println("--13--"); 
			r.add(new Rectangle(r2.getTopLeft().getX(),r1.getBottomLeft().getY(),r2.getTopRight().getX()-r2.getTopLeft().getX(),(int)distance.distanceBetweenLineAndPoint(r1.getBottom(),r2.getTopLeft())));
			r.get(i).setDistance((int)distance.distanceBetweenLineAndPoint(r1.getBottom(),r2.getTopLeft()));
			r.get(i).setCase(1);
		i++;
		} else if (r2.getTopLeft().getY()<r1.getTopLeft().getY()
				&&r2.getBottomLeft().getY()<r1.getTopLeft().getY()
				&&r1.getTopLeft().getX()>=r2.getBottomLeft().getX()
				&&r1.getTopLeft().getX()<r2.getBottomRight().getX()
				&&r1.getTopRight().getX()<=r2.getBottomRight().getX()) {
			
		//	System.out.println("--14--"); 
			r.add(new Rectangle(r1.getTopLeft().getX(),r2.getBottomLeft().getY(),r1.getTopRight().getX()-r1.getTopLeft().getX(),(int)distance.distanceBetweenLineAndPoint(r2.getBottom(),r1.getTopLeft())));
			r.get(i).setDistance((int)distance.distanceBetweenLineAndPoint(r2.getBottom(),r1.getTopLeft()));
			r.get(i).setCase(1);
			i++;
		}if (r2.getBottomLeft().getY()<r1.getTopLeft().getY()
				&&r2.getBottomRight().getX()<=r1.getTopRight().getX()
				&&r2.getBottomLeft().getX()<r1.getTopRight().getX()
				&&r2.getBottomLeft().getX()>=r1.getTopLeft().getX()
				&&r1.getTopRight().getY()>r2.getBottomRight().getY()) {
			
			//System.out.println("--15--");
			r.add(new Rectangle(r2.getBottomLeft().getX(),r2.getBottomLeft().getY(),r2.getBottomRight().getX()-r2.getBottomLeft().getX(),(int)distance.distanceBetweenLineAndPoint(r1.getTop(),r2.getBottomRight())));
			r.get(i).setDistance((int)distance.distanceBetweenLineAndPoint(r1.getTop(),r2.getBottomRight()));
			r.get(i).setCase(1);
		i++;
		} else if (r1.getBottomLeft().getY()<r2.getTopLeft().getY()
				&&r1.getBottomRight().getX()<=r2.getTopRight().getX()
				&&r1.getBottomLeft().getX()<r2.getTopRight().getX()
				&&r1.getBottomLeft().getX()>=r2.getTopLeft().getX()
				&&r2.getTopRight().getY()>r1.getBottomRight().getY()) {
			
			//System.out.println("--16--");
			r.add( new Rectangle(r1.getBottomLeft().getX(),r1.getBottomLeft().getY(),r1.getBottomRight().getX()-r1.getBottomLeft().getX(),(int)distance.distanceBetweenLineAndPoint(r2.getTop(),r1.getBottomLeft())));
			r.get(i).setDistance((int)distance.distanceBetweenLineAndPoint(r2.getTop(),r1.getBottomLeft()));
			r.get(i).setCase(1);
			i++;
		}
			return PointFromCorner(r1,r2,i,r);
	}
	//This method is for generating surfaces. (the second part)
	public  Rectangle[] PointFromCorner(Rectangle r1,Rectangle r2,int leng,ArrayList<Rectangle> r)
	{
		
		if(r2.getBottomRight().getY()<r1.getTopRight().getY()&&r2.getBottomRight().getX()<r1.getTopRight().getX())
		{
			Rectangle temp= new Rectangle(r2.getBottomRight().getX(),r2.getBottomRight().getY(),r1.getTopRight().getX()-r2.getBottomRight().getX(),r1.getTopRight().getY()-r2.getBottomRight().getY());
		 temp.setDistance(Double.parseDouble(String.format("%.2f",distance.distance(r2.getBottomRight(),r1.getTopRight())).replace(",", ".")));
		   if(temp.getDistance()<=threshold)
			{
			//   System.out.println("Neno0  + Distance => "+temp.getDistance());
			   r.add(temp);
			   r.get(leng).setCase(2);
			 leng++;
			}
		}else if(r1.getBottomRight().getY()<r2.getTopRight().getY()&&r1.getBottomRight().getX()<r2.getTopRight().getX())
		{
			Rectangle temp= new Rectangle(r1.getBottomRight().getX(),r1.getBottomRight().getY(),r2.getTopRight().getX()-r1.getBottomRight().getX(),r2.getTopRight().getY()-r1.getBottomRight().getY());
		 temp.setDistance(Double.parseDouble(String.format("%.2f",distance.distance(r1.getBottomRight(),r2.getTopRight())).replace(",", ".")));
		   if(temp.getDistance()<=threshold)
			{
			//   System.out.println("Neno0  + Distance => "+temp.getDistance());
			   r.add(temp);
			   r.get(leng).setCase(2);
			 leng++;
			}
		}
		
		if(r2.getBottomLeft().getY()<r1.getBottomRight().getY()&&r2.getBottomLeft().getX()>r1.getBottomRight().getX())
		{
			Rectangle temp= new Rectangle(r1.getBottomRight().getX(),r2.getBottomLeft().getY(),r2.getBottomLeft().getX()-r1.getBottomRight().getX(),r1.getBottomRight().getY()-r2.getBottomLeft().getY());
		 temp.setDistance(Double.parseDouble(String.format("%.2f",distance.distance(r2.getBottomLeft(),r1.getBottomRight())).replace(",", ".")));
		   if(temp.getDistance()<=threshold)
			{
		//	   System.out.println("Neno010  + Distance => "+temp.getDistance());
			   r.add(temp);
			   r.get(leng).setCase(2);
			 leng++;
			}
		}else if(r1.getBottomLeft().getY()<r2.getBottomRight().getY()&&r1.getBottomLeft().getX()>r2.getBottomRight().getX())
		{
			Rectangle temp= new Rectangle(r2.getBottomRight().getX(),r1.getBottomLeft().getY(),r1.getBottomLeft().getX()-r2.getBottomRight().getX(),r2.getBottomRight().getY()-r1.getBottomLeft().getY());
		 temp.setDistance(Double.parseDouble(String.format("%.2f",distance.distance(r1.getBottomLeft(),r2.getBottomRight())).replace(",", ".")));
		   if(temp.getDistance()<=threshold)
			{
			//   System.out.println("Neno010  + Distance => "+temp.getDistance());
			   r.add(temp);
			   r.get(leng).setCase(2);
			 leng++;
			}
		}
		if(r1.getBottomRight().getY()<r2.getTopRight().getY()&&r1.getBottomRight().getX()>r2.getTopRight().getX())
		{
			Rectangle temp= new Rectangle(r2.getTopRight().getX(),r1.getBottomRight().getY(),r1.getBottomRight().getX()-r2.getTopRight().getX(),r2.getTopRight().getY()-r1.getBottomRight().getY());
		 temp.setDistance(Double.parseDouble(String.format("%.2f",distance.distance(r1.getBottomRight(),r2.getTopRight())).replace(",", ".")));
		   if(temp.getDistance()<=threshold)
			{
			 //  System.out.println("Neno1  + Distance => "+temp.getDistance());
			   r.add(temp);
			   r.get(leng).setCase(2);
			 leng++;
			}
		} else if(r2.getBottomRight().getY()<r1.getTopRight().getY()&&r2.getBottomRight().getX()>r1.getTopRight().getX())
		{
			Rectangle temp= new Rectangle(r1.getTopRight().getX(),r2.getBottomRight().getY(),r2.getBottomRight().getX()-r1.getTopRight().getX(),r1.getTopRight().getY()-r2.getBottomRight().getY());
		 temp.setDistance(Double.parseDouble(String.format("%.2f",distance.distance(r2.getBottomRight(),r1.getTopRight())).replace(",", ".")));
		   if(temp.getDistance()<=threshold)
			{
			//   System.out.println("Neno1  + Distance => "+temp.getDistance());
			   r.add(temp);
			   r.get(leng).setCase(2);
			 leng++;
			}
		}
		
		if(r1.getBottomLeft().getY()<r2.getTopLeft().getY()&&r1.getBottomLeft().getX()<r2.getTopLeft().getX())
	{
		Rectangle temp= new Rectangle(r1.getBottomLeft().getX(),r1.getBottomLeft().getY(),r2.getTopLeft().getX()-r1.getBottomLeft().getX(),r2.getTopLeft().getY()-r1.getBottomLeft().getY());
	 temp.setDistance(Double.parseDouble(String.format("%.2f",distance.distance(r2.getTopLeft(),r1.getBottomLeft())).replace(",", ".")));
	   if(temp.getDistance()<=threshold)
		{
		//   System.out.println("Neno2  + Distance => "+temp.getDistance());
		   r.add(temp);
		   r.get(leng).setCase(2);
		 leng++;
		}
	} else if(r2.getBottomLeft().getY()<r1.getTopLeft().getY()&&r2.getBottomLeft().getX()<r1.getTopLeft().getX())
	{
		Rectangle temp= new Rectangle(r2.getBottomLeft().getX(),r2.getBottomLeft().getY(),r1.getTopLeft().getX()-r2.getBottomLeft().getX(),r1.getTopLeft().getY()-r2.getBottomLeft().getY());
	 temp.setDistance(Double.parseDouble(String.format("%.2f",distance.distance(r1.getTopLeft(),r2.getBottomLeft())).replace(",", ".")));
	   if(temp.getDistance()<=threshold)
		{
	//	  System.out.println("Neno2  + Distance => "+temp.getDistance());
		   r.add(temp);
		   r.get(leng).setCase(2);
		 leng++;
		}
	}  if(r1.getBottomRight().getX()<r2.getBottomLeft().getX()&&r1.getBottomRight().getY()>r2.getBottomLeft().getY())
		{
			Rectangle temp= new Rectangle(r1.getBottomRight().getX(),r2.getBottomLeft().getY(),r2.getBottomLeft().getX()-r1.getBottomRight().getX(),r1.getBottomRight().getY()-r2.getBottomLeft().getY());
		 temp.setDistance(Double.parseDouble(String.format("%.2f",distance.distance(r2.getBottomLeft(),r1.getBottomRight())).replace(",", ".")));

		 if(temp.getDistance()<=threshold)
		{ //System.out.println("Neno3  + Distance => "+temp.getDistance());
		r.add(temp);
		 r.get(leng).setCase(2);
		 leng++;
		}
		}else  if(r2.getBottomRight().getX()<r1.getBottomLeft().getX()&&r2.getBottomRight().getY()>r1.getBottomLeft().getY())
		{
			Rectangle temp= new Rectangle(r2.getBottomRight().getX(),r1.getBottomLeft().getY(),r1.getBottomLeft().getX()-r2.getBottomRight().getX(),r2.getBottomRight().getY()-r1.getBottomLeft().getY());
		temp.setDistance(Double.parseDouble(String.format("%.2f",distance.distance(r1.getBottomLeft(),r2.getBottomRight())).replace(",", ".")));
		if(temp.getDistance()<=threshold)
		{ //System.out.println("Neno4  + Distance => "+temp.getDistance());
		r.add(temp);
		 r.get(leng).setCase(2);
		 leng++;
		}
		}
	
Rectangle rReturn[]=new Rectangle[r.size()];
	return r.toArray(rReturn);
	}
	

	// This method is for removing unwanted surfaces.
	public Rectangle[] clean(Rectangle r[], SinglyLinkedList[] paths) {

		List<Rectangle> temp = new ArrayList<Rectangle>(r.length);
		for (int i = 0; i < r.length; i++) {
			temp.add(r[i]);
		}
		for (Iterator<Rectangle> iter = temp.iterator(); iter.hasNext();)
		/**/
		{
			Rectangle element = iter.next();
			if (element != null) {
				for (int j = 0; j < paths.length; j++) {
					if (Overlap.RectOverlap(element, paths[j].getPathRange()))
						if (Overlap.pathOverlapping(element, paths[j])) {
							iter.remove();
							break;

						}

				}
			}
		}
		Rectangle[] rResult = new Rectangle[temp.size()];
		rResult = temp.toArray(rResult);
		return rResult;
	}

	private int numberOfPaths(String s) {
		int i = 1;
		FileReader f;
		try {
			f = new FileReader(s);
			BufferedReader bf = new BufferedReader(f);
			String str = bf.readLine();
			while (str != null) {
				str = bf.readLine();
				if (str != null)
					if (str.isEmpty()) {
						i++;
					}
			}
			f.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return i;
	}

	// This method is for loading paths from a text file.
	public SinglyLinkedList[] load(String s) {
		int j = 0;
		int arr[] = new int[4];
		SinglyLinkedList paths[] = null;
		try {
			BufferedReader bf = new BufferedReader(new FileReader(s));
			String str = bf.readLine();
			// System.out.println("Number of paths : "+numberOfPaths(s));
			paths = new SinglyLinkedList[numberOfPaths(s)];
			paths[j] = new SinglyLinkedList();
			while (str != null) {
				if (str.contains("Range : ")) {
					str = str.replaceAll("[a-zA-Z:\\s]", "");
					// System.out.println(str);
					arr[0] = Integer.parseInt((str.split(",")[0]));

					arr[1] = Integer.parseInt((str.split(",")[1]));

					arr[2] = Integer.parseInt((str.split(",")[2]));

					arr[3] = Integer.parseInt((str.split(",")[3]));
					paths[j].setPathRange(new Rectangle(arr[0], arr[1], arr[2],
							arr[3]));
					str = bf.readLine();
				}
				if (!str.isEmpty() && str != null) {
					arr[0] = Integer.parseInt((str.split(",")[0]));

					arr[1] = Integer.parseInt((str.split(",")[1]));

					arr[2] = Integer.parseInt((str.split(",")[2]));

					arr[3] = Integer.parseInt((str.split(",")[3]));

					paths[j].insertEelements(new ListNode(arr[0], arr[1],
							arr[2], arr[3]));
				}
				// str=bf.readLine();
				if (str != null)
					if (str.isEmpty()) {
						j++;
						paths[j] = new SinglyLinkedList();
					}

				// System.out.println(j);

				str = bf.readLine();
			}
			bf.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return paths;
	}

	// This method writes a path into a text file.
	public static void writePathIntoFile(SinglyLinkedList path) {
		try {
			BufferedWriter br = new BufferedWriter(new FileWriter(
					"C:\\Users\\Alaa\\Desktop\\paths\\1000_P3.txt", true));
			// System.out.println("Range : "+path.getPathRange().getX()+","+path.getPathRange().getY()+","+path.getPathRange().getWidth()+","+path.getPathRange().getHeight());
			br.write("Range : " + path.getPathRange().getX() + ","
					+ path.getPathRange().getY() + ","
					+ path.getPathRange().getWidth() + ","
					+ path.getPathRange().getHeight());
			br.write("\n");
			for (ListNode segment = path.getHead(); segment != null; segment = segment
					.getNext()) {
				// System.out.println(segment.getX()+","+segment.getY()+","+segment.getWidth()+","+segment.getHeight());
				br.write(segment.getX() + "," + segment.getY() + ","
						+ segment.getWidth() + "," + segment.getHeight());
				// System.out.println("");
				br.write("\n");

			}
			br.write("\n");
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// This method writes paths into a text file.
	public static void writePathsIntoFile(SinglyLinkedList paths[]) {
		for (int i = 0; i < paths.length; i++) {
			writePathIntoFile(paths[i]);
		}

	}
  //This method generates a path.
	public static SinglyLinkedList generatePath(int length,
			SinglyLinkedList paths[], Merge m, Point XYRange)//
	{
		range = XYRange;
		int minX = 10000;
		int minY = 10000;
		int bigX = 0;
		int bigY = 0;
		int swap;
		Random random = new Random();
		int direction = 1;
		int lastDirection = direction;
		int x = random.nextInt(XYRange.getX()) + 10;
		int y = random.nextInt(XYRange.getY()) + 200;
		int width = 100;
		int height = 10;
		SinglyLinkedList path = new SinglyLinkedList();
		for (int i = 0; i < length; i++) {

			while (Overlap.rectWithPaths(new ListNode(x, y, width, height),
					paths)
					&& i == 0
					|| Overlap.regionWithPaths(new Rectangle(x, y, width,
							height), paths) && i == 0) {
				x = random.nextInt(XYRange.getX()) + 10;
				y = random.nextInt(XYRange.getY()) + 700;
			}

			while (direction == 3 && lastDirection == 2 || direction == 2
					&& lastDirection == 3) {
				direction = random.nextInt(3) + 1;
			}

			if (lastDirection == 1 && direction != 1 || lastDirection != 1
					&& direction == 1) {
				swap = width;
				width = height;
				height = swap;
				lastDirection = direction;
			}
			if (direction == 3) {
				y -= 90;

			}

			if (Overlap.rectWithPaths(new ListNode(x, y, width, height), paths)) {

				path = m.mergePathSegments(path, minX, minY, bigX - minX, bigY
						- minY);

				return path;
			} else {

				ListNode n = new ListNode(x, y, width, height);
				n.setID(direction);
				if (direction == 1) {
					if (bigX < n.getBottomRight().getX()) {
						bigX = n.getBottomRight().getX();
					}
					if (bigY < n.getBottomRight().getY()) {
						bigY = n.getBottomRight().getY();
					}
					if (minX > n.getTopLeft().getX()) {
						minX = n.getTopLeft().getX();
					}
					if (minY > n.getTopLeft().getY()) {
						minY = n.getTopLeft().getY();
					}
				} else if (direction == 2) {
					if (bigX < n.getBottomRight().getX()) {
						bigX = n.getBottomRight().getX();
					}
					if (bigY < n.getBottomRight().getY()) {
						bigY = n.getBottomRight().getY();
					}
					if (minX > n.getTopLeft().getX()) {
						minX = n.getTopLeft().getX();
					}
					if (minY > n.getTopLeft().getY()) {
						minY = n.getTopLeft().getY();
					}
				} else if (direction == 3) {
					if (bigX < n.getBottomRight().getX()) {
						bigX = n.getBottomRight().getX();
					}
					if (bigY < n.getBottomRight().getY()) {
						bigY = n.getBottomRight().getY();
					}
					if (minX > n.getTopLeft().getX()) {
						minX = n.getTopLeft().getX();
					}
					if (minY > n.getTopLeft().getY()) {
						minY = n.getTopLeft().getY();
					}
				}
				path.insertEelements(n);
			}

			if (direction == 1) {
				x = x + 100;

			}
			if (direction == 2) {
				y = y + 100;
			}
			direction = random.nextInt(3) + 1;
		}

		path = m.mergePathSegments(path, minX, minY, bigX - minX, bigY - minY);

		return path;
	}
    //This method generates a list of paths
	public static SinglyLinkedList[] generatepaths(int numberOfPaths, Merge m,
			Point XYWindow) {
		SinglyLinkedList paths[] = new SinglyLinkedList[numberOfPaths];
		Random random = new Random();

		for (int i = 0; i < numberOfPaths; i++) {
			paths[i] = generatePath(random.nextInt(5) + 6, paths, m, XYWindow);
		}
		return paths;
	}
    //This method writes rectangles into a text file.
	public void writeRectanglesInToFile(Rectangle rects[]) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("rect1.txt",
					true));
			for (Rectangle rect : rects) {
				if (rect != null) {
					bw.write(rect.getX() + "," + rect.getY() + ","
							+ rect.getWidth() + "," + rect.getHeight());
					bw.write("\n");
				}
			}
			bw.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    //This method loads rectangles from a text file.
	public Rectangle[] loadRects(String s, int size) {
		ArrayList<Rectangle> rs = new ArrayList<Rectangle>(size);
		try {
			BufferedReader br = new BufferedReader(new FileReader(s));

			try {
				String line = br.readLine();
				int x;
				int y;
				int width;
				int height;
				while (line != null && line != "") {
					x = Integer.parseInt(line.split(",")[0]);
					y = Integer.parseInt(line.split(",")[1]);
					width = Integer.parseInt(line.split(",")[2]);
					height = Integer.parseInt(line.split(",")[3]);

					rs.add(new Rectangle(x, y, width, height));
					line = br.readLine();
				}
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rs.trimToSize();
		Rectangle rss[] = new Rectangle[rs.size()];
		rss = rs.toArray(rss);
		return rss;
	}

	/*public static void main(String[] args) {
		// SinglyLinkedList path =new SinglyLinkedList();
		// SinglyLinkedList paths []=
		// path.load("C:\\Users\\Alaa\\Desktop\\paths\\50_P3.txt");

		Point windowSize = new Point(2200, 2200);
		SinglyLinkedList paths[] = new SinglyLinkedList[1000];
		range = windowSize;

		paths = generatepaths(50, new Merge(), windowSize);
		threshold = 1000;
		Distance d = new Distance(new SinglyLinkedList());
		Rectangle[] rs = d.betweenPaths(paths);
		rs = paths[0].clean(rs, paths);
		Merge m = new Merge();
		rs = m.mergeAll(rs);
		new DrawingCanvas(paths[0], paths, m, d, windowSize);

	}*/

}


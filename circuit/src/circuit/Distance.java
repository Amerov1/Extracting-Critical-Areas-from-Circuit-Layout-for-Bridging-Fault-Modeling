package circuit;

import java.util.ArrayList;
//This class provides all methods responsible for calculating distances.
public class Distance {

	public SinglyLinkedList ls;
	public static ArrayList<Rectangle> regions=new ArrayList<Rectangle>(20);
	public Distance(SinglyLinkedList ls)
	{
		this.ls= ls;
	}
	//This methods calculates the distance between two points
	public double distance(Point p1, Point p2) {

		int dx = p1.getX() - p2.getX();
		int dy = p1.getY() - p2.getY();
		return Math.sqrt(dx * dx + dy * dy);
	}
	//This Method calculates the distance between a line and a point
	public double distanceBetweenLineAndPoint(Line l, Point p) {
		if (l.getStart().getX() < l.getEnd().getX()&& l.getStart().getY() == l.getEnd().getY())// if the line is
															// horizontal
		{//System.out.println("horizontal");
			if (p.getX() >= l.getEnd().getX() )// IF the point is on the right	                                                              // align with the line
			{//System.out.println(" horizontal  1");
				return distance(p,l.getEnd());
			} else if (p.getX() <= l.getStart().getX())
			{//System.out.println(" horizontal  2");
				return distance(l.getStart(), p);
			}else if (p.getX() > l.getStart().getX()
					&& p.getX() < l.getEnd().getX()
					&& p.getY() != l.getStart().getY())// 11
			{//System.out.println(" horizontal  11");
				return Math.abs(l.getStart().getY() - p.getY());
			}
		} else if (l.getStart().getY() < l.getEnd().getY()&& l.getStart().getX() == l.getEnd().getX())// if the line is vertical
		{
			if (  p.getY() <= l.getStart().getY())
			{//System.out.println("vertical   1");
				return distance(p,l.getStart());//1
			}else if (p.getY() >= l.getEnd().getY())
			{//System.out.println("vertical  3 ");
				return distance(p,l.getEnd());//3
			}else if (p.getX() != l.getStart().getX()
					&& p.getY() > l.getStart().getY()
					&& p.getY() < l.getEnd().getY())// 11
			{//System.out.println("vertical  11  ");
				return Math.abs(p.getX() - l.getStart().getX());
			}
		}

		return 0;
	}

	// This method finds the smallest distance between two lines
	public double distanceBetweenTwoLines(Line l1, Line l2) {

		double p1 = distanceBetweenLineAndPoint(l1, l2.getStart());
		double p2 = distanceBetweenLineAndPoint(l1, l2.getEnd());
		double p3 = distanceBetweenLineAndPoint(l2, l1.getStart());
		double p4 = distanceBetweenLineAndPoint(l2, l1.getEnd());
		double p5 = Math.min(p1, p2);
		double p6 = Math.min(p3, p4);
		return Math.min(p5, p6);
	}

	// This method finds the smallest distance between two rectangles
	public double distanceBetweenRects(Rectangle rect1, Rectangle rect2) {
		double minDistance = Double.MAX_VALUE;
		// Calculate distances between edges and corners
		double[] distances = new double[16];

		// Distances between edges of rect1 and corners of rect2
		distances[0] = distanceBetweenTwoLines(rect1.getLeft(), rect2.getLeft());
		distances[1] = distanceBetweenTwoLines(rect1.getRight(),
				rect2.getLeft());
		distances[2] = distanceBetweenTwoLines(rect1.getTop(), rect2.getLeft());
		distances[3] = distanceBetweenTwoLines(rect1.getBottom(),
				rect2.getLeft());
		distances[4] = distanceBetweenTwoLines(rect1.getLeft(), rect2.getTop());
		distances[5] = distanceBetweenTwoLines(rect1.getRight(), rect2.getTop());
		distances[6] = distanceBetweenTwoLines(rect1.getTop(), rect2.getTop());
		distances[7] = distanceBetweenTwoLines(rect1.getBottom(),
				rect2.getTop());
		distances[8] = distanceBetweenTwoLines(rect1.getLeft(),
				rect2.getBottom());
		distances[9] = distanceBetweenTwoLines(rect1.getRight(),
				rect2.getBottom());
		distances[10] = distanceBetweenTwoLines(rect1.getTop(),
				rect2.getBottom());
		distances[11] = distanceBetweenTwoLines(rect1.getBottom(),
				rect2.getBottom());
		distances[12] = distanceBetweenTwoLines(rect1.getLeft(),
				rect2.getRight());
		distances[13] = distanceBetweenTwoLines(rect1.getRight(),
				rect2.getRight());
		distances[14] = distanceBetweenTwoLines(rect1.getTop(),
				rect2.getRight());
		distances[15] = distanceBetweenTwoLines(rect1.getBottom(),
				rect2.getRight());
		// Find the minimum distance among all distances
		for (double distance : distances) {

			if (distance < minDistance) {
				minDistance = distance;
			}
		}

		return minDistance;

	}


	// The method returns a list of paired segments from two paths.
	public ArrayList<Rects> DistanceBetweenTwoPaths(SinglyLinkedList p1,SinglyLinkedList p2) {
		if(Overlap.pathToPathOverlap(p1,p2))
		{
			return null;
		}
		if(p1!=null&&p2!=null)
		{	
		ArrayList<Rects> myRects=new ArrayList<Rects>(p1.findLength()*p2.findLength());
		ListNode p1Onehead = p1.getHead() ;
			
		while (p1Onehead != null)
			{
				ListNode p2Twohead = p2.getHead();
				if(Overlap.RectOverlap((Rectangle)p1Onehead,(Rectangle)p2Twohead))
				{					
					return null;
				}else{
					while (p2Twohead != null) {
						if(SinglyLinkedList.threshold>= distanceBetweenRects((Rectangle)p1Onehead, (Rectangle)p2Twohead))
						myRects.add(new Rects(p1Onehead,p2Twohead,(int )distanceBetweenRects(p1Onehead, p2Twohead)));

					p2Twohead = p2Twohead.getNext();
				}
					p1Onehead = p1Onehead.getNext();
				}
				
				
			}
	
			myRects.trimToSize();
		return myRects;
		}
		return null;
	}
	//This methods calculate the distances between a list of paths
	public Rectangle[] betweenPaths(SinglyLinkedList paths[]) {
		Rectangle[] allR = null;
		ArrayList<Rects> rs = new ArrayList<Rects>();
		ArrayList<Rectangle> temp = new ArrayList<Rectangle>();
		int myLength = 0;

		for (int i = 0; i < paths.length - 1; i++) {
			for (int j = i + 1; j < paths.length; j++) {
				if (NeighborhoodCheck(paths[i].getPathRange(),
						paths[j].getPathRange())) {
					rs = DistanceBetweenTwoPaths(paths[i], paths[j]);
					if (rs != null) {
						myLength = paths[i].findLength();
						if (paths[j].findLength() > paths[i].findLength()) {
							myLength = paths[j].findLength();
						}

						for (int k = 0; k < rs.size(); k++) {
							allR = paths[i].generateSurfaces(rs.get(k).getR1(),
									rs.get(k).getR2(), myLength);

							if (allR != null)
								for (Rectangle r : allR) {
									if (r != null) {
										temp.add(r);
									}
								}
						}
					}
				}
			}
		}
		Rectangle rectangle[] = new Rectangle[temp.size()];
		rectangle = temp.toArray(rectangle);
		return rectangle;
	}
//This methods checks if two paths are neighbored
	public boolean NeighborhoodCheck(Rectangle pathRange, Rectangle susPathRange) {
		Rectangle pathRangeSmall = new Rectangle(pathRange.getX(),
				pathRange.getY(), pathRange.getWidth() * 1 / 2,
				pathRange.getHeight() * 1 / 2);
		Rectangle rBL = new Rectangle(pathRangeSmall.getTopLeft().getX()
				- SinglyLinkedList.threshold - 100, pathRangeSmall.getTopLeft()
				.getY() - SinglyLinkedList.threshold - 100,
				SinglyLinkedList.threshold * 2 + (pathRange.getWidth() * 2)
						+ 200, SinglyLinkedList.threshold * 2
						+ (pathRange.getHeight() * 2) + 200);
		if (Overlap.RectOverlap(rBL, susPathRange)) {
			return true;
		}

		return false;
	}

}

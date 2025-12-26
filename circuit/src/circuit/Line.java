/**
 * 
 */
package circuit;

/**
 * @author Alaa
 *
 */
public class Line {

	private Point start;
	private Point end;

	public Line(Point start, Point end) {
		this.start = start;
		this.end = end;
	}
  //This method returns the startPoint of a line
	public Point getStart() {
		return start;
	}
	//This method returns the endPoint of a line
	public Point getEnd() {
		return end;
	}
	//This method sets the startPoint of a line
	public void setStart(Point start) {
		this.start = start;
	}
	//This method sets the endPoint of a line
	public void setEnd(Point end) {
		this.end = end;
	}

	@Override
	public String toString() {
		return "Line [start=" + start + ", end=" + end + "]";
	}

}

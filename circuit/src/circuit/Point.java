package circuit;

public class Point {

	private int x;
	private int y;

	public Point() {
		this.x = 0;
		this.y = 0;
	}

	public Point(int x, int y) {
		this.x = x;
		this.y = y;

	}

	public Point addition(Point a) {
		int x = a.getX() + this.getX();
		int y = a.getY() + this.getY();
		Point newOne = new Point(x, y);
		return newOne;
	}

	public Point subtraction(Point b) {
		int x = this.getX() - b.getX();
		int y = this.getY() - b.getY();
		Point newOne = new Point(x, y);
		return newOne;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
}

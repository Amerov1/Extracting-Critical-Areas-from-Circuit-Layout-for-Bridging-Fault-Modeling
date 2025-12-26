package circuit;


public class Rectangle {



	private int x;
	private int y;
	private int width;
	private int height;
	private Point topLeft;
	private Point topRight;
	private Point bottomRight;
	private Point bottomLeft;
	private Line left;
	private Line right;
	private Line bottom;
	private Line top;
	private double distance;
   private int howGenerated;
	public Rectangle(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.topLeft = new Point(x, y);
		this.topRight = new Point(x + width, y);
		this.bottomRight = new Point(x + width, y + height);
		this.bottomLeft = new Point(x, y + height);
		this.top = new Line(topLeft, topRight);
		this.bottom = new Line(bottomLeft, bottomRight);
		this.left = new Line(topLeft, bottomLeft);
		this.right = new Line(topRight, bottomRight);
		this.distance=0;
		howGenerated=0;

	}
	public void setCase(int how)
	{
		this.howGenerated=how;
	}
	public int getCase()
	{
		return this.howGenerated;
	}
	public void setDistance(int d)
	{
		this.distance=d;
	}
	//This method defines or changes the distance of a surface.
	public void setDistance(double distance2) {
		// TODO Auto-generated method stub
		this.distance=distance2;
	}
	//This method returns the distance of a rectangle (surface).
	public double getDistance()
	{
		return this.distance;
	}
	//This method returns the left line
	public Line getLeft() {
		return left;
	}
	//This method returns the right line
	public Line getRight() {
		return right;
	}
	//This method returns the bottom line
	public Line getBottom() {
		return bottom;
	}
	//This method returns the top line
	public Line getTop() {
		return top;
	}

	public int getX() {
		return x;
	}
	public void setX(int x)
	{
		this.x=x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y)
	{
		this.y=y;
	}
	//This method returns the height of a rectangle.
	public int getHeight() {
		return height;
	}
	//This method returns the width of a rectangle.
	public int getWidth() {
		return width;
	}
	//This method returns the top left point of a rectangle
	public Point getTopLeft() {
		return this.topLeft;
	}
	//This method returns the top right point of a rectangle
	public Point getTopRight() {
		return this.topRight;
	}
	//This method returns the bottom right point of a rectangle
	public Point getBottomRight() {
		return this.bottomRight;
	}
	//This method returns the bottom left point of a rectangle
	public Point getBottomLeft() {
		return this.bottomLeft;
	}
	public Rectangle getRectangle()
	{
		return this;
	}
	@Override
	public String toString() {
		return "Rectangle \n[x=" + x + ",\n y=" + y + ",\n width=" + width
				+ ",\n height=" + height + ",\n topLeft=" + topLeft
				+ ",\n topRight=" + topRight + ",\n bottomRight=" + bottomRight
				+ ",\n bottomLeft=" + bottomLeft + "]";
	}

}

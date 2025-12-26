package circuit;

public class Rects {
	private Rectangle r1;
	private Rectangle r2;
	private int distance;
	Rects()
	{
		this.r1=null;
		this.r2=null;
		this.distance=0;
	}
	public void  setDistance(int d)
	{
		this.distance=d;
	}
	
	public int getDistance()
	{
		return this.distance;
	}
	Rects(Rectangle r1 , Rectangle r2,int d)
	{
		this.r1=r1;
		this.r2=r2;
		this.distance=d;
		
	}
	public Rectangle getR1()
	{
		return r1;
	}
	public Rectangle getR2()
	{
		return r2;
	}

}

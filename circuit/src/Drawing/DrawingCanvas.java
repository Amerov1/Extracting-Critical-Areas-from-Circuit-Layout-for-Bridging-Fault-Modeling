package Drawing;
import circuit.*;
import circuit.Point;
import circuit.Rectangle;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.*;

import circuit.SinglyLinkedList;
public class DrawingCanvas  extends JComponent implements ChangeListener, KeyListener ,ActionListener{
	public SinglyLinkedList sll ;
	public List<SinglyLinkedList> paths;
	public List<Rectangle> rects;
	public Merge merge;
	public Distance distance ;
	JFrame myFrame;
	JPanel panel;
	JScrollPane scroll;
	MySlider mySlider;
	MySwitchButton mySwitchButton;
	JCheckBox checkBox ;
	MyTextField textField;
	Point windowSize;
	SinglyLinkedList []pathss;
	public  DrawingCanvas(SinglyLinkedList p1 ,SinglyLinkedList []pathss, Merge merge, Distance distance,Point windowSize) {
		this.windowSize= windowSize;
		mySlider=new MySlider(this); 
		textField=new MyTextField(mySlider);
		mySwitchButton= new MySwitchButton(mySlider);
		sll = p1;
		   this.distance=new Distance(sll);
		   this.merge=merge;
	        myFrame = new JFrame();
	        panel = new JPanel();
	        panel.setBounds(10,10, 700, 100);
	         scroll = new JScrollPane(panel);
	         scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
	         scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	 //        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	  //       int preferredWidth = (int) (screenSize.getWidth() * 2);
	  //       int preferredHeight = (int) (screenSize.getHeight() * 2);
	      //   scroll.setPreferredSize(new Dimension(preferredWidth, preferredHeight));

	        checkBox= new JCheckBox();
	        textField.addKeyListener(this);
	        mySwitchButton.addActionListener(this);
	        mySwitchButton.setFocusable(false);
	        checkBox.setText("Show distances");
	        checkBox.setFocusable(false);
	        mySlider.addChangeListener(this);
this.pathss=pathss;
	        SinglyLinkedList.threshold=0;
	        paths = new ArrayList<>();
           setPaths(pathss);
	        rects = new ArrayList<>();
	     //   myFrame.setTitle("First Try");
	     //   myFrame.setSize(1000, 1000);
	      //  myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        // Set the layout manager of the frame to BorderLayout
	        myFrame.setLayout(new BorderLayout());

	        // Add the DrawingCanvas to the center
	       // myFrame.add(this, BorderLayout.CENTER);
	       panel.setLayout(new FlowLayout(FlowLayout.LEADING,20,10));
	        panel.add(mySwitchButton);
	        panel.add(mySlider);
	        panel.add(textField);
	        panel.add(checkBox);
	        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	        JScrollPane scrollPane = new JScrollPane(this);
	        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
	        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	        myFrame.add(panel,BorderLayout.NORTH);
	        myFrame.getContentPane().add(scrollPane,BorderLayout.CENTER);
	        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        myFrame.setSize(windowSize.getX()*2, windowSize.getY()*2);
	        myFrame.setLocationRelativeTo(null);
	        myFrame.setVisible(true);
	        
}

public void setPaths(SinglyLinkedList []pathss)
{
	for(int i =0;i<pathss.length;i++)
	{//System.out.println(pathss[i].getPathRange());
		paths.add(pathss[i]);
	}
}
public Dimension getPreferredSize() {
    return new Dimension(windowSize.getX()+(windowSize.getX()*1/2), windowSize.getY()+( windowSize.getY()*1/2)); // Set your preferred size here
}
public List<SinglyLinkedList> getPaths()
{
return paths;
}
public void doSomethingForTextField()
{
SinglyLinkedList [] allPaths=new SinglyLinkedList[getPaths().size()];
allPaths=getPaths().toArray(allPaths);
 Rectangle rs[]=  distance.betweenPaths(allPaths);
  Rectangle rr[]=  sll.clean(rs, allPaths);
 Rectangle rrNew[]=merge.mergeAll(rr);
 
	for(Rectangle r1 : rrNew)
	{
		if(r1!=null)
		{
		
		setRect(r1);
		
		}
	}
	
}

public void doSomethingForButtom()
{
 	SinglyLinkedList.threshold=windowSize.getX()+windowSize.getY();
SinglyLinkedList [] allPaths=new SinglyLinkedList[getPaths().size()];
allPaths=getPaths().toArray(allPaths);
 Rectangle rs[]=  distance.betweenPaths(allPaths);
  Rectangle rr[]=  sll.clean(rs, allPaths);
 Rectangle rrNew[]=merge.mergeAll(rr);
 
	for(Rectangle r1 : rrNew)
	{
		if(r1!=null)
		{
		
		setRect(r1);
		
		}
	}
	
}
	 public void addPath(SinglyLinkedList path)
	 {
		 getPaths().add(path); 
	 }
	 
public void setRect(Rectangle r)
{
	rects.add(r);
}
public List<Rectangle> getRect( )
{
	return rects  ;
}
//This method draws/renders graphics  on a container/frame.
	public  void paint(Graphics g) {
	    Graphics2D g2d = (Graphics2D) g;

	      for (SinglyLinkedList path : pathss) {
            ListNode currentNode = path.getHead();
            while (currentNode != null) {
                // Draw the rectangles for the current path
                g2d.setPaint(new Color(123, 50, 250));
                g2d.fillRect(currentNode.getX(), currentNode.getY(), currentNode.getWidth(), currentNode.getHeight());

                g2d.setPaint(new Color(0, 0, 0));
                g2d.drawRect(currentNode.getX(), currentNode.getY(), currentNode.getWidth(), currentNode.getHeight());

                currentNode = currentNode.getNext();
            }
        }
        for(Rectangle rect :getRect())//this for writing the distance of each surface inside the surface.
        {
        	if(rect!=null)
          	  {String distanceString = String.valueOf(rect.getDistance());
            FontMetrics fontMetrics = g2d.getFontMetrics();
            int xCenter = rect.getX() + rect.getWidth() / 2 - fontMetrics.stringWidth(distanceString) / 2;
            int yCenter = rect.getY() + rect.getHeight() / 2 + fontMetrics.getAscent() / 2;
            if(rect.getDistance()<=SinglyLinkedList.threshold)
     {
    	 g2d.setPaint(Color.yellow);
         g2d.fillRect(rect.getTopLeft().getX(),rect.getTopLeft().getY(),(int) rect.getWidth(), rect.getHeight());
         g2d.setPaint(new Color(0, 0, 0));
         g2d.drawRect(rect.getTopLeft().getX(),rect.getTopLeft().getY(),(int) rect.getWidth(), rect.getHeight());
         
         if(checkBox.isSelected())
         {
        	if(rect.getDistance()==(int)rect.getDistance()){
        		int x1=(int)rect.getDistance();
         g2d.drawString(""+x1,xCenter+6,yCenter);}else
         {g2d.drawString(""+rect.getDistance(),xCenter,yCenter);}
         repaint();
         }else
         {
        	 repaint();
         }
         }
    }
    	
    }
	}
	//This method updates the value of threshold  using a slider.
	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
	//	System.out.println("hello from the super class");
		mySlider.stateChanged(e);
		repaint();
		
	}


	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	//This method updates the value of threshold after entering new value throw textfield.
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("look "+e.getKeyCode());
		textField.keyReleased(e);
		doSomethingForTextField();
		repaint();
		
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
    //This method updates the state of the button
	@Override
	public void actionPerformed(ActionEvent arg0) {
		mySwitchButton.actionPerformed(arg0);
		// System.out.println("mySwitchButton.isSelected()"+mySwitchButton.isSelected());
		if (mySwitchButton.isSelected()) {
			doSomethingForButtom();
		}
		repaint();
	}
}
package Drawing;

import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import circuit.Distance;
import circuit.Rectangle;
import circuit.SinglyLinkedList;

public class MySlider extends JSlider implements ChangeListener{

	DrawingCanvas dc;
public	MySlider(DrawingCanvas dc)
	{	       
	setMinimum(0);
	setMaximum(450);
	setValue(0);
     setPreferredSize(new Dimension(450, 50));
    setPaintTicks(true);
    setMinorTickSpacing(10);
    setPaintTrack(true);
    setMajorTickSpacing(30);
    setPaintLabels(true);
    addChangeListener(this);
		this.dc=dc;
	}
          //This method updates the value of the threshold using a slider.
	@Override
	public void stateChanged(ChangeEvent e) {
		if(isEnabled())
			SinglyLinkedList.threshold=getValue();
		SinglyLinkedList [] allPaths=new SinglyLinkedList[dc.getPaths().size()];
		allPaths=dc.getPaths().toArray(allPaths);
		     Rectangle rs[]=  dc.distance.betweenPaths(allPaths);
		     
	 	     Rectangle rr[]=  dc.sll.clean(rs, allPaths);
		     Rectangle rrNew[]= dc.merge.mergeAll(rr);
				for(Rectangle r1 : rrNew)
				{
					if(r1!=null)
					{
					dc.getRect().add(r1);
					}
				}
		
	}

}

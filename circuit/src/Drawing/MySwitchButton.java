package Drawing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JToggleButton;

import circuit.SinglyLinkedList;

public class MySwitchButton extends JToggleButton implements ActionListener{
//	public JToggleButton switchButton;
	public MySlider slider;
	public MySwitchButton(MySlider slider)
	{
		this.slider=slider;
		setText("Turn on");

        setBounds(10, 20, 200, 100);
        
	}
//This method for pressing bottom,hence set Threshold to a certain value.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (isSelected()) {
            setText("Turn off");
            slider.setValue(0);
            slider.setEnabled(false);
            
            
        } else {
            setText("Turn on");
            SinglyLinkedList.threshold=0;
            slider.setEnabled(true);
          
        }
    }
}

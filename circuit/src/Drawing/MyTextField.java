package Drawing;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

import circuit.SinglyLinkedList;

public class MyTextField extends JTextField implements KeyListener {
MySlider slider;
	MyTextField(MySlider  slider)
	{this.slider=slider;
         setPreferredSize(new Dimension(45,25));
         setFont(new Font("Consolas",Font.PLAIN,14));
         
	}
	//This method checks if the given input is numeric.
	public boolean isNumeric(String str) {
        // Use a regular expression to check if the string contains only numeric characters
        return str.matches("\\d+");
    }
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
    //This method allows for entering and updating the value of the threshold through a text field.
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(slider.isEnabled())
		{
		if(getText()!=null&& isNumeric(getText()))
		{System.out.println("from textfield");
			if(e.getKeyCode()==10){
			 	slider.setValue((Integer.parseInt(getText())));
				SinglyLinkedList.threshold=Integer.parseInt(getText());
			setText("");
			}
			System.out.println(SinglyLinkedList.threshold);
			
		} 
	}
}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}

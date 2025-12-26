package circuit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Drawing.DrawingCanvas;
public class Main implements ActionListener{
JFrame jframe=new JFrame();
JPanel jpanel= new JPanel();

JButton selectButton= new JButton("Select");
JPanel panel=new JPanel();
	Main()
	{
		selectButton.setBounds(10,10,180,150);
		selectButton.setFocusable(false);
		selectButton.addActionListener(this);
		jframe.add(selectButton);
		jframe.setDefaultCloseOperation(jframe.EXIT_ON_CLOSE);
		jframe.setSize(220, 220);
		jframe.setLayout(null);
		jframe.setVisible(true);
		jframe.setResizable(true);

	//	panel.setSize(460, 460);	
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==selectButton)
		{
			JFileChooser fileChooser= new JFileChooser();
			int response=fileChooser.showOpenDialog(null);
			if(response==JFileChooser.APPROVE_OPTION)
				{File file=new File(fileChooser.getSelectedFile().getAbsolutePath());
			SinglyLinkedList paths []= new SinglyLinkedList().load(fileChooser.getSelectedFile().getAbsolutePath());//"C:\\Users\\Alaa\\Desktop\\Paths.txt"
			Merge m = new Merge();
			new DrawingCanvas(paths[0],paths,m,new Distance(new SinglyLinkedList()),new Point(2200, 2200));
			jframe.setVisible(false);
				}
		}
		
	}
	public static void main(String []args)
	{
		new Main();
	}

}

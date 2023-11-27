package notguitcgcard;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class FieldButton extends JButton{
	private int num;
	private static ImageIcon fimage = new ImageIcon(Main.class.getResource("../images/아군 카드 필드.png"));
	FieldButton(ArrayList<Integer> gameState,int num){
		super(fimage);
		this.num=num;
		setBounds(50+150*num,300,150,230);
		this.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(gameState.get(3)==1) {
				gameState.set(3, 2);
				gameState.set(2, num);
				}
			}
		});
	}
}

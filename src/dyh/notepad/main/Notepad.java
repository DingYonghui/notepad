package dyh.notepad.main;

import java.awt.EventQueue;

import dyh.notepad.ui.FrmMain;

/**
 * ���࣬����main�������������
 * @author ding
 *
 */
public class Notepad {
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmMain frame = new FrmMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}

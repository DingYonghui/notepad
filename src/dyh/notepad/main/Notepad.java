package dyh.notepad.main;

import java.awt.EventQueue;

import dyh.notepad.ui.FrmMain;

/**
 * 主类，包含main方法，程序入口
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

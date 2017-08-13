package dyh.notepad.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dyh.notepad.handler.FrmFontHandler;
import dyh.notepad.ui.FrmFont;

/**
 * ��������������
 * ʵ�ּ����������࣬
 * 1������ͼ�ν��洫��������
 * 2��������ҵ����������Ӧ�ķ���
 * @author ding
 *
 */
public class FrmFontControl implements ListSelectionListener,ActionListener{


	FrmFont frmFont = null ;
	FrmFontHandler frmFontImpl = null;
	
	public FrmFontControl(FrmFont frmFont){
		this.frmFont = frmFont ;
		init() ;
	}
	
	private void init(){
		frmFontImpl = new FrmFontHandler();
	}
	
	public void valueChanged(ListSelectionEvent e) {
		
		String fontName = frmFont.getSelectedFontName();
		int fontStyle = frmFont.getSelectedFontStyle();
		int fontSize = frmFont.getSelectedFontSize();	
		frmFontImpl.updateTextPaneFont(fontName, fontStyle, fontSize);
		//����ʾ����ǩ
		frmFont.updatelblExample(fontName,fontStyle,fontSize);
		
	}

	public void actionPerformed(ActionEvent e) {
		
		String requset = e.getActionCommand() ;
		
		if(requset.equals("ȷ��")){
			frmFontImpl.setTextPaneFont();
		}			
		frmFont.dispose();			
	}
	
}

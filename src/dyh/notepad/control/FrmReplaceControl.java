package dyh.notepad.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import dyh.notepad.handler.FrmReplaceHandler;
import dyh.notepad.handler.FrmSearchHandler;
import dyh.notepad.ui.FrmMain;
import dyh.notepad.ui.FrmReplace;

/**
 * �滻�����������
 * ʵ�ּ����������࣬
 * 1������ͼ�ν��洫��������
 * 2��������ҵ����������Ӧ�ķ���
 * @author ding
 *
 */
public class FrmReplaceControl implements ActionListener,DocumentListener {

	FrmReplace frmReplace = null ;
	FrmReplaceHandler frmReplaceHandler = null ;
	FrmSearchHandler frmSearchHandler =null ;
	
	public FrmReplaceControl(FrmReplace frmReplace){
		this.frmReplace = frmReplace ;
		init() ;
	}
		
	private void init() {
		frmReplaceHandler = new FrmReplaceHandler();
		frmSearchHandler = new FrmSearchHandler();
	}
	
	/**
	 * ActionListener�Ĵ�����
	 */
	public void actionPerformed(ActionEvent e) {
		
		String request = e.getActionCommand() ;
		
		if(request.equals("������һ��(F)")){
			
		    boolean isMatchCase = frmReplace.isMatchCase() ;
			String allText = FrmMain.textPane.getText() ; 
			String needSearchText = frmReplace.textSearch.getText() ;
			
			String selectedText = null ;
			try {			
				//Ĭ��Ϊ  ���²���
				selectedText = frmSearchHandler.searchByDown(allText,
						needSearchText, isMatchCase);			    
			} catch (Exception e1) {			
				e1.printStackTrace();
			}
			
			//����鵽������Ϊ��
			if(selectedText==null){
		    	 JOptionPane.showMessageDialog(FrmMain.textPane,
				    		"�Ҳ���   ��"+needSearchText+"��","��ʾ",JOptionPane.INFORMATION_MESSAGE);
		    	 return ;
			}
			
			FrmMain.textPane.repaint();
			
		}
		else if(request.equals("�滻(R)")){	//�Ȳ��� ���滻				
			
			String allText = FrmMain.textPane.getText() ;
			String needSearchText = frmReplace.textSearch.getText() ;
			boolean isMatchCase = frmReplace.isMatchCase();
			
			//�Ȳ���
			String selectedText = null;
			try {
				//Ĭ��Ϊ  ���²���
				selectedText = frmSearchHandler.searchByDown(allText,
						needSearchText, isMatchCase);		   
			} catch (Exception e1) {	
				e1.printStackTrace();
			}
			
			//����鵽������Ϊ��
			if(selectedText==null){
		    	 JOptionPane.showMessageDialog(FrmMain.textPane,
				    		"�Ҳ���   ��"+needSearchText+"��","��ʾ",JOptionPane.INFORMATION_MESSAGE);
		    	 return ;
			}
			
			//���滻Ϊ	replaceWith	
			String replaceWith = frmReplace.textReplace.getText();			
			frmReplaceHandler.replace(selectedText,replaceWith);
		    
			FrmMain.textPane.repaint();
							
		}
		else if(request.equals("ȫ���滻(A)")){	
			
			String allText = FrmMain.textPane.getText();
			String needSearchText = frmReplace.textSearch.getText();
			String replaceWith = frmReplace.textReplace.getText();
			boolean isMatchCase = frmReplace.isMatchCase() ;
			
			frmReplaceHandler.replaceAll( allText , needSearchText,
					replaceWith, isMatchCase);	
			
			FrmMain.textPane.repaint();
		}
		else if(request.equals("ȡ��")){
			frmReplace.dispose();		
		}
		
	}
	
	/**
	 * DocumentListener�Ĵ�����
	 */
	public void insertUpdate(DocumentEvent e) {		
		
		if(!frmReplace.textSearch.getText().equals("")){		
			frmReplace.setButtonEnable_SearchNext_Replace_ReplaceAll(true);		
		}		
		
	}
	
	public void removeUpdate(DocumentEvent e) {	
		if(frmReplace.textSearch.getText().equals("")){
			frmReplace.setButtonEnable_SearchNext_Replace_ReplaceAll(false);	
		}		
	}

	public void changedUpdate(DocumentEvent e) {			
	}
	
	
}

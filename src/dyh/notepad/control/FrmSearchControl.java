package dyh.notepad.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import dyh.notepad.handler.FrmSearchHandler;
import dyh.notepad.ui.FrmMain;
import dyh.notepad.ui.FrmSearch;

/**
 * ���ҽ����������
 * ʵ�ּ����������࣬
 * 1������ͼ�ν��洫��������
 * 2��������ҵ����������Ӧ�ķ���
 * @author ding
 *
 */
public class FrmSearchControl implements ActionListener,DocumentListener{

	FrmSearch frmSearch = null ;
	FrmSearchHandler frmSearchImpl = null ;
	
	public FrmSearchControl(FrmSearch frmSearch) {
		this.frmSearch = frmSearch ;
		init() ;
	}
	
	public void init(){
		frmSearchImpl = new FrmSearchHandler() ;
	}

	/**-------------ActionListener���¼�����-------------------*/
	public void actionPerformed(ActionEvent e) {
		
		String request = e.getActionCommand() ;
		
		if(request.equals("������һ��")){     //������һ��
			
			//���²���  or ���ϲ���
			boolean isSearchByDown = frmSearch.isSearchByDown() ;
			//�Ƿ����ִ�Сд
		    boolean isMatchCase = frmSearch.isMatchCase() ;
		    //�ı���ȫ������
			String allText = FrmMain.textPane.getText() ; 
			//��Ҫ���ҵ��ı�
			String needSearchText = frmSearch.textSearch.getText() ;
			
			String selectText = null ;
			try {
				//���ô������Search����
				selectText = frmSearchImpl.Search(allText, 
						needSearchText, isSearchByDown, isMatchCase);	
			} catch (Exception e1) {
				e1.printStackTrace();
		    }
			
			if(selectText == null)
				JOptionPane.showMessageDialog(FrmMain.textPane,
			    		"�Ҳ���"+needSearchText,"��ʾ",JOptionPane.INFORMATION_MESSAGE);
				
			FrmMain.textPane.repaint();
		}
		
		else if(request.equals("ȡ��")){//ȡ��
			frmSearch.dispose();
		}		
	}
			
	/**-------------DocumentListener���¼�����-----------------*/
	public void insertUpdate(DocumentEvent e) {
		//�����Ҫ���ҵ��ı���Ϊ���ַ�
		if(!frmSearch.textSearch.getText().equals("")){
			frmSearch.btnSearchNextOne.setEnabled(true);	
			frmSearch.repaint();
		}
						
	}
	public void removeUpdate(DocumentEvent e) {
		//�����Ҫ���ҵ��ı�Ϊ���ַ�
		if(frmSearch.textSearch.getText().equals("")){
			frmSearch.btnSearchNextOne.setEnabled(false);
			frmSearch.repaint();
		}				
	}

	public void changedUpdate(DocumentEvent e) {
		// TODO �Զ����ɵķ������		
	}
}

package dyh.notepad.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;

import dyh.notepad.handler.FrmMainHandler;
import dyh.notepad.ui.FrmMain;

/**
 * �������������
 * ʵ�ּ����������࣬
 * 1������ͼ�ν��洫��������
 * 2��������ҵ����������Ӧ�ķ���
 * @author ding
 *
 */
public class FrmMainControl implements ActionListener,DocumentListener,MouseListener, UndoableEditListener{
	
	//������
	private FrmMain frmMain ;
	//����������Ĺ���ʵ����	
	private FrmMainHandler frmMainHandler ;
		
	/**
	 * ���췽��
	 * @param frmMain
	 */
	public FrmMainControl(FrmMain frmMain){
		this.frmMain = frmMain ;
		init() ;
	}
		
	/**
	 * ��ʼ������
	 */
	public void init(){
		//��������������Ĺ���ʵ����
		frmMainHandler = new FrmMainHandler()  ;		
	}
	
    /**
     * ActionListener�ķ���
     */
	public void actionPerformed(ActionEvent e) {
        //�õ�ͼ�������������
		String request = e.getActionCommand();
		
	    if(request.equals("�½�(N)")){        //�½�
	    	//���������洦������  �½�  �ķ���    	
	    	frmMainHandler.doNewBuilt(frmMain);
	    }	
		else if(request.equals("��(O)") ){  //��		
			frmMainHandler.doOpen(frmMain);
		}
		else if(request.equals("����(S)")){   //����
			frmMainHandler.doSave(frmMain);
		}
		else if(request.equals("���Ϊ(A)")){  //���Ϊ
			frmMainHandler.doSaveAs(frmMain);
		}
		else if(request.equals("�˳�(X)")){   //�˳�
			System.exit(0);
		}
		else if(request.equals("����(U)")){   //����
			frmMainHandler.doCancel();		
		}
		else if(request.equals("����(T)")){   //����
			frmMainHandler.doCut();
		}
		else if(request.equals("����(C)")){   //����
			frmMainHandler.doCopy();
		}
		else if(request.equals("ճ��(P)")){   //ճ��
			frmMainHandler.doPaste();	
		}
		else if(request.equals("ɾ��(L)")){   //ɾ��
			frmMainHandler.doDelete();		
		}
		else if(request.equals("����(F)")){     //����
			frmMainHandler.clickSearch();
		}
		else if(request.equals("������һ��(N)")){   //������һ��
			frmMainHandler.clickSearchNext();
		}
		else if(request.equals("�滻(R)")){   //�滻
			frmMainHandler.clickReplace();
		}
		else if(request.equals("ȫѡ(A)")){      //ȫѡ
			frmMainHandler.doSecletAll();
		}
		else if(request.equals("ʱ��/����(D)")){  //ʱ������
			frmMainHandler.doTimeAndDate();
		}
		else if(request.equals("�Զ�����(W)")){   //�Զ�����
			frmMainHandler.doLineWrap();
		}
        else if(request.equals("����(F)")){  	   //����
        	frmMainHandler.clickFont();      		
		}
        else if(request.equals("״̬��(S)")){    //״̬��
        	boolean isSelectStatus = frmMain.isSelecteStatus();
        	frmMainHandler.doStatus(isSelectStatus );
        	FrmMain.textPane.repaint();
        }
        else if(request.equals("���ڼ��±�(A)")){   //���ڼ��±�
        	JOptionPane.showMessageDialog(frmMain,"    ���ߣ�����          ʱ�䣺2015-05-30  ",
    				"���ڼ��±�",JOptionPane.PLAIN_MESSAGE);
        }	   	    	    	    			  
	}
	
	/**
	 * DocumentListener�ķ���
	 */
	public void insertUpdate(DocumentEvent e) {		
		//���ҡ�������һ�����滻������ı������ı�������Щ�˵���ɱ༭��
		if(!FrmMain.textPane.getText().equals("")){
			frmMain.setItemEnable_Search_SearchNext_Replace(true);
		}		
	}
	public void removeUpdate(DocumentEvent e) {		
		//������ı���û���ı�������Щ�˵���ɱ༭��
		if(FrmMain.textPane.getText().equals("")){
			frmMain.setItemEnable_Search_SearchNext_Replace(false);
		}		
	}	
	public void changedUpdate(DocumentEvent e) {	
	}
	
	/**
	 * MouseListener�ķ���
	 */
	public void mouseClicked(MouseEvent e) {//1:���   2:�м�   3:�Ҽ�
		if(e.getButton() == MouseEvent.BUTTON3){   //��������ı������һ�ʱ
			frmMainHandler.checkItems(frmMain);
		}  						
	}	
	public void mousePressed(MouseEvent e) {	
	}
	public void mouseReleased(MouseEvent e) {			
	}

	public void mouseEntered(MouseEvent e) {	    //�������뵽���༭���˵�ʱ
		frmMainHandler.checkItems(frmMain);
	}
	public void mouseExited(MouseEvent e) {
		// TODO �Զ����ɵķ������		
	}

	/**
	 * UndoableEditListener�ķ��������ڳ���
	 */
	public void undoableEditHappened(UndoableEditEvent e) {	
		frmMainHandler.undoManager.addEdit(e.getEdit());
	}
   
}
	

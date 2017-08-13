package dyh.notepad.handler;

import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.undo.UndoManager;

import dyh.notepad.ui.FrmFont;
import dyh.notepad.ui.FrmMain;
import dyh.notepad.ui.FrmReplace;
import dyh.notepad.ui.FrmSearch;
import dyh.notepad.util.Constants;
import dyh.notepad.util.LineNumberBorder;
/**
 * �����洦��ľ��������
 * @author ding
 *
 */
public class FrmMainHandler {
	
	//��������������	
	public UndoManager undoManager = new UndoManager();

	//���ҽ���
	FrmSearch frmSearch = null ;
	//�滻����
	FrmReplace frmReplace = null ;
	//�������
	FrmFont frmFont = null;
		
	/**
	 * ���½����Ĵ������
	 * @param mainFrm
	 */
	public void doNewBuilt(FrmMain mainFrm){
			
		//�ı������ı䣨����ǰ�ı��뱻������ı�����ͬ��
		if(isTextChange(mainFrm)){
			
			int n = JOptionPane.showConfirmDialog(mainFrm, "�Ƿ񽫸��ı��浽  �ޱ��⣿",
					"���±�",JOptionPane.YES_NO_CANCEL_OPTION);	
			
			if(n == JOptionPane.YES_OPTION){
				doSave(mainFrm);			
			}
			else if(n == JOptionPane.CANCEL_OPTION){
				return ;
			}
		}
		
		//�½�������ı���
		FrmMain.textPane.setText("");
														
	}
	
	/**
	 * �ı��������Ƿ����ı�
	 * @param mainFrm
	 * @return
	 */
	public boolean isTextChange(FrmMain mainFrm){
			
		//����ı����ݷ����ı�
		if(!Constants.SAVED_TEXT.equals(FrmMain.textPane.getText()))
			return true;
		else
			return false;
		
	}
	
	/**
	 * ���򿪡��Ĵ������
	 * @param mainFrm
	 */
	public void doOpen(FrmMain frmMain){
		
		//����Ƿ����޸�
		if(isTextChange(frmMain)){
			
			int n = JOptionPane.showConfirmDialog(frmMain, "�ı������Ѹı䣬�Ƿ���Ҫ���棿",
					"���±�",JOptionPane.YES_NO_CANCEL_OPTION);
			if(n == JOptionPane.YES_OPTION){
				doSave(frmMain);			
			}	
			else if(n == JOptionPane.CANCEL_OPTION){
				return ;
			}
						
		}
		
		// ����һ������ָ��������ļ��Ի��򴰿ڣ����ڼ����ļ���
		FileDialog filedialog = new FileDialog
				(frmMain,"��",FileDialog.LOAD);
		filedialog.setVisible(true);
		
		//��ȡ�ļ��Ի������û�ѡ�е��ļ����ڵ�·��
		String filePath = filedialog.getDirectory();
		//��ȡ�Ի������û�ѡ�е��ļ���
		String fileName = filedialog.getFile();
		try{
			BufferedReader br = new BufferedReader(new FileReader
					(filePath+fileName));
			//���ļ��ж�һ����Ϣ
			String line = br.readLine();
			String allText = "" ;
			//ѭ�����ļ��е����ݣ�����ʾ���ı�����
			while(line != null){
				allText = allText + line + "\n" ;
				//����һ��
				line = br.readLine();
			}
			FrmMain.textPane.setText(allText);
			Constants.SAVED_TEXT = allText ;
			//�ر�������
			br.close();
		}catch(Exception e){
			e.printStackTrace();	
		}
				
	}
			
	/**
	 * �����桱�Ĵ������
	 * @param frmMain 
	 */
	public void doSave(FrmMain frmMain){
		
		//���û�б�����������Ϊ
		if(Constants.SAVED_FILE_PATH == null){
			doSaveAs(frmMain);
		}
		//����б��������ֱ�ӱ���
		else{
			try{
				 
				 String temp = FrmMain.textPane.getText();
				 Constants.SAVED_TEXT = temp ;
				 //����һ���ļ������������д�ļ�
				 BufferedWriter bw = new BufferedWriter(new FileWriter(Constants.SAVED_FILE_PATH)) ;
				 //���ı����е���Ϣд���ļ���
				 bw.write(temp);
				 bw.flush();
				 //�ر������
				 bw.close();
			 }
			 catch(Exception e){
				 e.printStackTrace();
			 }	
		}
		  
	}
	
	/**
	 * �����Ϊ���Ĵ������
	 * @param frmMain
	 */
	public void doSaveAs(FrmMain frmMain){
				
		try{
			  /**
			   * FileDialog ����ʾһ���Ի��򴰿ڣ��û����Դ���ѡ���ļ���
			   * ��������һ��ģʽ�Ի��򣬵�Ӧ�ó�������� show ��������ʾ�Ի���ʱ��
			   * ������������Ӧ�ó���ֱ���û�ѡ��һ���ļ��� 
			   * FileDialog.SAVE�˳���ֵָʾ�ļ��Ի��򴰿ڵ������ǲ���Ҫд����ļ���
			   */
			  FileDialog filedialog = new FileDialog(frmMain,"���Ϊ",FileDialog.SAVE);
			  filedialog.setVisible(true);
			  
			  //��ȡ���ļ��Ի����Ŀ¼��
			  String directory = filedialog.getDirectory();	
			  if(directory == null)
				  return ;
			  //��ȡ���ļ��Ի����ѡ���ļ���
			  String fileName = filedialog.getFile(); 
			  if(fileName == null )
				  return ;
			  String fileType = ".txt";
			  String allText = FrmMain.textPane.getText() ;		  		  
			  String filePath = directory+fileName+fileType ;
			    			  
			  Constants.SAVED_TEXT = allText;
			  Constants.SAVED_FILE_PATH = filePath ;
			 		  
			  //�������������
			  BufferedWriter bfw = new BufferedWriter(new FileWriter(filePath));
			  //���ı��������д�뵽��filePath����
			  bfw.write(allText);
			  bfw.flush();
			  //�ر������
			  bfw.close();
				 			     			   			 
		}catch(Exception e){
		    JOptionPane.showMessageDialog(FrmMain.textPane,"�������","����",JOptionPane.WARNING_MESSAGE);			     
			e.printStackTrace();
		}
		
	}
		
	/**
	 * ���������Ĵ������
	 * @param undoManager 
	 * @param frmMain 
	 */
    public void doCancel(){  
    	
    	if(undoManager.canUndo()){	   		
    		undoManager.undo();	
		}   
    	      	
    }
    
	/**
	 * �����С��Ĵ������
	 */
    public void doCut(){
    	FrmMain.textPane.cut();  	
     }
    
    /**
     * �����ơ��Ĵ������
     */
    public  void doCopy(){  
    	FrmMain.textPane.copy();
    }
    
    /**
     * ��ճ�����Ĵ������
     */
    public void doPaste(){   	
    	FrmMain.textPane.paste();
    }
    
    /**
     * ��ɾ�����Ĵ������
     */
    public void doDelete() {  			
		FrmMain.textPane.replaceSelection("");	
	}
    
    /**
     * ��ȫѡ���Ĵ������
     */
    public void doSecletAll(){
    	FrmMain.textPane.selectAll();
    	FrmMain.textPane.setSelectedTextColor(Color.blue);
    }
    
    /**
	 * ʱ��/����
	 */
	public void doTimeAndDate() {
		FrmMain.textPane.replaceSelection( new Date().toString());
	}

    /**
     * ���Զ����С��Ĵ������
     */
    public void doLineWrap(){
    	 /* if(notepadUI.getChckbxmntmLineWrap().isSelected()){
    		 
    		  FrmMain.textPane.setLineWrap(true);   
    		  FrmMain.textPane.setWrapStyleWord(true);//���в�����
    	  }
    	  else {
    		  FrmMain.textPane.setLineWrap(false);  	   
    	  }*/
     }  
    
    /**
     * ��״̬�����Ĵ������
     * @param isSelectStatus 
     */
    public void doStatus(boolean isSelectStatus){    //δ���      
  	 
    	if(isSelectStatus){
    		FrmMain.textPane.setBorder(new LineNumberBorder());
    	}
    	else{
    		FrmMain.textPane.setBorder(null);
    	}
    	
    	//Highlighter highLight = FrmMain.textPane.getHighlighter();
    	
   } 
    	
	/**
	 * �����塱�Ĳ���,��������Ի���
	 */
	public void clickFont(){
		
		if(frmFont == null ){
			frmFont = new FrmFont();
		}	
		frmFont.setVisible(true); 	
	}
	
	/**
	 * �����ҡ��Ĳ���,�������ҶԻ���
	 */
	public void clickSearch() {
		
		if(frmSearch == null){
			frmSearch = new FrmSearch();
		}
		frmSearch.setVisible(true);	
	}
	
    /**
     * ��������һ�����Ĳ���,�����Ի���
     */
    public void clickSearchNext(){
    
    	//���û�в��Ҽ�¼����ִ���µĲ���
    	if(frmSearch == null ){
    		clickSearch() ;
    	}
    	
    	//���û�в��Ҽ�¼����ֱ�Ӳ���
    	String allText = FrmMain.textPane.getText();
    	String needSearchText = frmSearch.textSearch.getText();
    	boolean isMatchCase = frmSearch.isMatchCase();
    	boolean isSearchByDown = frmSearch.isSearchByDown();
    	
    	FrmSearchHandler frmSearchHandler = new FrmSearchHandler();
    	String selectText = null ;
    	try {
    		selectText = frmSearchHandler.Search(allText, needSearchText, isSearchByDown, isMatchCase);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	if(selectText == null)
			JOptionPane.showMessageDialog(FrmMain.textPane,
		    		"�Ҳ���"+needSearchText,"��ʾ",JOptionPane.INFORMATION_MESSAGE);
    	   	
    }
    
    /**
     * ���滻���Ĳ���,�����Ի���
     */
    public void clickReplace(){
    	
    	if(frmReplace == null ){
    		frmReplace = new FrmReplace();
    	}
    	frmReplace.setVisible(true);	
    }
			
	/**
	 * ��⸴�ơ����С�ɾ����������ճ���˵����Ƿ�ɲ���
	 * @param frmMain 
	 */
    public void checkItems(FrmMain frmMain){
		//���ơ����С�ɾ������ѡ���ı�ʱ�ɱ༭����ѡ���ı�ʱ���ɱ༭��
		if(FrmMain.textPane.getSelectedText() != null){
			frmMain.setItemEnable_Copy_Cut_Delete(true) ;
		}
		else{  
			frmMain.setItemEnable_Copy_Cut_Delete(false) ;
		}
				
		//����
		if(undoManager.canUndo()){	
			frmMain.setItemEnable_Cancel(true);
		}else{
			frmMain.setItemEnable_Cancel(false);
		}		
		
		//Toolkit�����౻���ڽ���������󶨵��ض��������߰�ʵ�֡�getDefaultToolkit()��ȡĬ�Ϲ��߰���
	    //Toolkit toolKit = Toolkit.getDefaultToolkit();
		//��ȡϵͳ������
		//Clipboard clipBoard = toolKit.getSystemClipboard();
		//ճ��
		if(Toolkit.getDefaultToolkit().
				getSystemClipboard().isDataFlavorAvailable
				(DataFlavor.stringFlavor)){ //������������������򷵻�true
			frmMain.setItemEnable_Paste(true);
		}else{
			frmMain.setItemEnable_Paste(false);
		}
	}        
		
}


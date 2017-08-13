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
 * 替换界面控制器：
 * 实现几个监听者类，
 * 1、接收图形界面传来的请求，
 * 2、并调用业务处理类中相应的方法
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
	 * ActionListener的处理方法
	 */
	public void actionPerformed(ActionEvent e) {
		
		String request = e.getActionCommand() ;
		
		if(request.equals("查找下一个(F)")){
			
		    boolean isMatchCase = frmReplace.isMatchCase() ;
			String allText = FrmMain.textPane.getText() ; 
			String needSearchText = frmReplace.textSearch.getText() ;
			
			String selectedText = null ;
			try {			
				//默认为  向下查找
				selectedText = frmSearchHandler.searchByDown(allText,
						needSearchText, isMatchCase);			    
			} catch (Exception e1) {			
				e1.printStackTrace();
			}
			
			//如果查到的内容为空
			if(selectedText==null){
		    	 JOptionPane.showMessageDialog(FrmMain.textPane,
				    		"找不到   “"+needSearchText+"”","提示",JOptionPane.INFORMATION_MESSAGE);
		    	 return ;
			}
			
			FrmMain.textPane.repaint();
			
		}
		else if(request.equals("替换(R)")){	//先查找 再替换				
			
			String allText = FrmMain.textPane.getText() ;
			String needSearchText = frmReplace.textSearch.getText() ;
			boolean isMatchCase = frmReplace.isMatchCase();
			
			//先查找
			String selectedText = null;
			try {
				//默认为  向下查找
				selectedText = frmSearchHandler.searchByDown(allText,
						needSearchText, isMatchCase);		   
			} catch (Exception e1) {	
				e1.printStackTrace();
			}
			
			//如果查到的内容为空
			if(selectedText==null){
		    	 JOptionPane.showMessageDialog(FrmMain.textPane,
				    		"找不到   “"+needSearchText+"”","提示",JOptionPane.INFORMATION_MESSAGE);
		    	 return ;
			}
			
			//后替换为	replaceWith	
			String replaceWith = frmReplace.textReplace.getText();			
			frmReplaceHandler.replace(selectedText,replaceWith);
		    
			FrmMain.textPane.repaint();
							
		}
		else if(request.equals("全部替换(A)")){	
			
			String allText = FrmMain.textPane.getText();
			String needSearchText = frmReplace.textSearch.getText();
			String replaceWith = frmReplace.textReplace.getText();
			boolean isMatchCase = frmReplace.isMatchCase() ;
			
			frmReplaceHandler.replaceAll( allText , needSearchText,
					replaceWith, isMatchCase);	
			
			FrmMain.textPane.repaint();
		}
		else if(request.equals("取消")){
			frmReplace.dispose();		
		}
		
	}
	
	/**
	 * DocumentListener的处理方法
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

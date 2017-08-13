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
 * 查找界面控制器：
 * 实现几个监听者类，
 * 1、接收图形界面传来的请求，
 * 2、并调用业务处理类中相应的方法
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

	/**-------------ActionListener的事件处理-------------------*/
	public void actionPerformed(ActionEvent e) {
		
		String request = e.getActionCommand() ;
		
		if(request.equals("查找下一个")){     //查找下一个
			
			//向下查找  or 向上查找
			boolean isSearchByDown = frmSearch.isSearchByDown() ;
			//是否区分大小写
		    boolean isMatchCase = frmSearch.isMatchCase() ;
		    //文本的全部内容
			String allText = FrmMain.textPane.getText() ; 
			//需要查找的文本
			String needSearchText = frmSearch.textSearch.getText() ;
			
			String selectText = null ;
			try {
				//调用处理类的Search方法
				selectText = frmSearchImpl.Search(allText, 
						needSearchText, isSearchByDown, isMatchCase);	
			} catch (Exception e1) {
				e1.printStackTrace();
		    }
			
			if(selectText == null)
				JOptionPane.showMessageDialog(FrmMain.textPane,
			    		"找不到"+needSearchText,"提示",JOptionPane.INFORMATION_MESSAGE);
				
			FrmMain.textPane.repaint();
		}
		
		else if(request.equals("取消")){//取消
			frmSearch.dispose();
		}		
	}
			
	/**-------------DocumentListener的事件处理-----------------*/
	public void insertUpdate(DocumentEvent e) {
		//如果需要查找的文本不为空字符
		if(!frmSearch.textSearch.getText().equals("")){
			frmSearch.btnSearchNextOne.setEnabled(true);	
			frmSearch.repaint();
		}
						
	}
	public void removeUpdate(DocumentEvent e) {
		//如果需要查找的文本为空字符
		if(frmSearch.textSearch.getText().equals("")){
			frmSearch.btnSearchNextOne.setEnabled(false);
			frmSearch.repaint();
		}				
	}

	public void changedUpdate(DocumentEvent e) {
		// TODO 自动生成的方法存根		
	}
}

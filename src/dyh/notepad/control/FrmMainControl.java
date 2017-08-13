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
 * 主界面控制器：
 * 实现几个监听者类，
 * 1、接收图形界面传来的请求，
 * 2、并调用业务处理类中相应的方法
 * @author ding
 *
 */
public class FrmMainControl implements ActionListener,DocumentListener,MouseListener, UndoableEditListener{
	
	//主界面
	private FrmMain frmMain ;
	//处理主界面的功能实现类	
	private FrmMainHandler frmMainHandler ;
		
	/**
	 * 构造方法
	 * @param frmMain
	 */
	public FrmMainControl(FrmMain frmMain){
		this.frmMain = frmMain ;
		init() ;
	}
		
	/**
	 * 初始化方法
	 */
	public void init(){
		//创建处理主界面的功能实现类
		frmMainHandler = new FrmMainHandler()  ;		
	}
	
    /**
     * ActionListener的方法
     */
	public void actionPerformed(ActionEvent e) {
        //得到图形主界面的请求
		String request = e.getActionCommand();
		
	    if(request.equals("新建(N)")){        //新建
	    	//调用主界面处理类中  新建  的方法    	
	    	frmMainHandler.doNewBuilt(frmMain);
	    }	
		else if(request.equals("打开(O)") ){  //打开		
			frmMainHandler.doOpen(frmMain);
		}
		else if(request.equals("保存(S)")){   //保存
			frmMainHandler.doSave(frmMain);
		}
		else if(request.equals("另存为(A)")){  //另存为
			frmMainHandler.doSaveAs(frmMain);
		}
		else if(request.equals("退出(X)")){   //退出
			System.exit(0);
		}
		else if(request.equals("撤消(U)")){   //撤消
			frmMainHandler.doCancel();		
		}
		else if(request.equals("剪切(T)")){   //剪切
			frmMainHandler.doCut();
		}
		else if(request.equals("复制(C)")){   //复制
			frmMainHandler.doCopy();
		}
		else if(request.equals("粘贴(P)")){   //粘贴
			frmMainHandler.doPaste();	
		}
		else if(request.equals("删除(L)")){   //删除
			frmMainHandler.doDelete();		
		}
		else if(request.equals("查找(F)")){     //查找
			frmMainHandler.clickSearch();
		}
		else if(request.equals("查找下一个(N)")){   //查找下一个
			frmMainHandler.clickSearchNext();
		}
		else if(request.equals("替换(R)")){   //替换
			frmMainHandler.clickReplace();
		}
		else if(request.equals("全选(A)")){      //全选
			frmMainHandler.doSecletAll();
		}
		else if(request.equals("时间/日期(D)")){  //时间日期
			frmMainHandler.doTimeAndDate();
		}
		else if(request.equals("自动换行(W)")){   //自动换行
			frmMainHandler.doLineWrap();
		}
        else if(request.equals("字体(F)")){  	   //字体
        	frmMainHandler.clickFont();      		
		}
        else if(request.equals("状态栏(S)")){    //状态栏
        	boolean isSelectStatus = frmMain.isSelecteStatus();
        	frmMainHandler.doStatus(isSelectStatus );
        	FrmMain.textPane.repaint();
        }
        else if(request.equals("关于记事本(A)")){   //关于记事本
        	JOptionPane.showMessageDialog(frmMain,"    作者：丁丁          时间：2015-05-30  ",
    				"关于记事本",JOptionPane.PLAIN_MESSAGE);
        }	   	    	    	    			  
	}
	
	/**
	 * DocumentListener的方法
	 */
	public void insertUpdate(DocumentEvent e) {		
		//查找、查找下一个、替换（如果文本区有文本，则这些菜单项可编辑）
		if(!FrmMain.textPane.getText().equals("")){
			frmMain.setItemEnable_Search_SearchNext_Replace(true);
		}		
	}
	public void removeUpdate(DocumentEvent e) {		
		//（如果文本区没有文本，则这些菜单项不可编辑）
		if(FrmMain.textPane.getText().equals("")){
			frmMain.setItemEnable_Search_SearchNext_Replace(false);
		}		
	}	
	public void changedUpdate(DocumentEvent e) {	
	}
	
	/**
	 * MouseListener的方法
	 */
	public void mouseClicked(MouseEvent e) {//1:左键   2:中键   3:右键
		if(e.getButton() == MouseEvent.BUTTON3){   //当鼠标在文本域中右击时
			frmMainHandler.checkItems(frmMain);
		}  						
	}	
	public void mousePressed(MouseEvent e) {	
	}
	public void mouseReleased(MouseEvent e) {			
	}

	public void mouseEntered(MouseEvent e) {	    //当鼠标进入到“编辑”菜单时
		frmMainHandler.checkItems(frmMain);
	}
	public void mouseExited(MouseEvent e) {
		// TODO 自动生成的方法存根		
	}

	/**
	 * UndoableEditListener的方法，用于撤消
	 */
	public void undoableEditHappened(UndoableEditEvent e) {	
		frmMainHandler.undoManager.addEdit(e.getEdit());
	}
   
}
	

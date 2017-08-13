package dyh.notepad.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dyh.notepad.handler.FrmFontHandler;
import dyh.notepad.ui.FrmFont;

/**
 * 字体界面控制器：
 * 实现几个监听者类，
 * 1、接收图形界面传来的请求，
 * 2、并调用业务处理类中相应的方法
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
		//更新示例标签
		frmFont.updatelblExample(fontName,fontStyle,fontSize);
		
	}

	public void actionPerformed(ActionEvent e) {
		
		String requset = e.getActionCommand() ;
		
		if(requset.equals("确定")){
			frmFontImpl.setTextPaneFont();
		}			
		frmFont.dispose();			
	}
	
}

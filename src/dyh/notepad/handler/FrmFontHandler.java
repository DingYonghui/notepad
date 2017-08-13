package dyh.notepad.handler;

import java.awt.Font;

import dyh.notepad.ui.FrmMain;

/**
 * 字体界面处理的具体操作类
 * @author ding
 *
 */
public class FrmFontHandler  {
	
	private String fontName = null ;   //字体名字
	private int fontStyle = 0 ;     //字形
	private int fontSize = 0 ;      //字体大小
		
	public FrmFontHandler(){
		init();
	}
	
	public void init(){
		fontName = FrmMain.textPane.getFont().getFontName() ;  
		fontStyle = FrmMain.textPane.getFont().getStyle();     
		fontSize = FrmMain.textPane.getFont().getSize(); 
	}
		
	/**
	 * 点击字体、字形、字体大小列表时更新变量
	 * @param fontName
	 * @param fontStyle
	 * @param fontSize
	 */
	public void updateTextPaneFont(String fontName,int fontStyle,int fontSize){
		this.fontName = fontName ;  
		this.fontStyle = fontStyle ;     
		this.fontSize = fontSize ; 
	}
	
	/**
	 * 设置主界面文本域的字体
	 */
	public void setTextPaneFont(){
		FrmMain.textPane.setFont(new Font(fontName,fontStyle,fontSize));
		FrmMain.textPane.repaint();
	}
	
	

	
	
}

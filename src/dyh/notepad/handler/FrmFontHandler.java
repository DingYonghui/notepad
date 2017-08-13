package dyh.notepad.handler;

import java.awt.Font;

import dyh.notepad.ui.FrmMain;

/**
 * ������洦��ľ��������
 * @author ding
 *
 */
public class FrmFontHandler  {
	
	private String fontName = null ;   //��������
	private int fontStyle = 0 ;     //����
	private int fontSize = 0 ;      //�����С
		
	public FrmFontHandler(){
		init();
	}
	
	public void init(){
		fontName = FrmMain.textPane.getFont().getFontName() ;  
		fontStyle = FrmMain.textPane.getFont().getStyle();     
		fontSize = FrmMain.textPane.getFont().getSize(); 
	}
		
	/**
	 * ������塢���Ρ������С�б�ʱ���±���
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
	 * �����������ı��������
	 */
	public void setTextPaneFont(){
		FrmMain.textPane.setFont(new Font(fontName,fontStyle,fontSize));
		FrmMain.textPane.repaint();
	}
	
	

	
	
}

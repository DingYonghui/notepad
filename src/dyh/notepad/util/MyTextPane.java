package dyh.notepad.util;


import java.awt.Color;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 * 扩展的JTextPane
 * 实现关键字高亮
 * @author ding
 *
 */
public class MyTextPane extends JTextPane{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6775339437598960844L;
	
	//声明样式文档               StyledDocument用作通用样式化文档的接口。 
	private StyledDocument doc;
			
	//关键字属性（样式）     MutableAttributeSet独特属性的可变集合的通用接口
	private MutableAttributeSet keyAttr ;
	//关键符号属性
	private MutableAttributeSet keySymbolAttr ;
	//一般文本属性
	private MutableAttributeSet normalAttr ;
	
	//关键字集合
	private Set<String> keywords,keySymbol;
	//关键字数组
	private String[] keys = {"public","private","new","null","protected","class","for","int","while","do",
							"static","void","float","double","true","false","null","boolean","if","else"
							,"try","catch","import","package"};
	
	//JAVA模式
	private boolean isJavaModel=true;

	/**
	 * 构造方法
	 */
	public  MyTextPane() {
		
		//获取textPane文档
		doc = (StyledDocument)this.getDocument();
		
		// MutableAttributeSet 的直接实现。 
		keyAttr = new SimpleAttributeSet();
		keySymbolAttr = new SimpleAttributeSet();
		normalAttr = new SimpleAttributeSet();
		
		//设置关键字样式 
		StyleConstants.setFontFamily(keyAttr, "Arial");		
		//添加关键字前景色
		StyleConstants.setForeground(keyAttr, new Color(255,100,100));
		StyleConstants.setBold(keyAttr, true);
		
		//添加关键符号前景色
		StyleConstants.setForeground(keySymbolAttr, Color.BLUE);	
		StyleConstants.setFontFamily(keySymbolAttr, "serif");
		StyleConstants.setBold(keySymbolAttr, true);
		
		//添加一般文本前景色
		StyleConstants.setForeground(normalAttr, Color.black);
		//添加一般文本字体
		StyleConstants.setFontFamily(normalAttr, "宋体");
		//一般文本字体是否加粗
		StyleConstants.setBold(normalAttr, false);
			
		//关键字集合
		keywords = new HashSet<String>();
		for(int i = 0; i< keys.length; i++)
			keywords.add(keys[i]);
		
		//关键符号
		keySymbol = new HashSet<String>();
		keySymbol.add("{");
		keySymbol.add("}");
		keySymbol.add("(");
		keySymbol.add(")");
		
		//为document添加监听者
		doc.addDocumentListener(new JNotepadDocumentListener());
		
	}
	
	/**
	 *  监听者类
	 * @author ding
	 *
	 */ 
	private class JNotepadDocumentListener implements DocumentListener {
	
		public void insertUpdate(DocumentEvent e) {
			
			//发生插入位置    返回文档中更改开始的偏移量。
			int pos = e.getOffset();
			//发生长度    返回更改的长度。
			int len = e.getLength();
			//调用着色方法
			coloring(pos,len);

		}

		
		public void removeUpdate(DocumentEvent e) {
			//发生删除位置
			int pos = e.getOffset();
			//发生长度
			//len = e.getLength();
			//调用着色方法
			coloring(pos,0);
			
		}
		
		public void changedUpdate(DocumentEvent e) {

		}
		
				
		public void coloring(int pos,int len){
			//---------取得操作后影响到的单词
			//单词的开始的索引
			int start = indexOfWordStart(pos);
			//单词的结束的索引
			int end = indexOfWordEnd(pos+len);
			
			char ch;
			
			while(start < end){
				
				ch = getCharAt(start);
				
				if(Character.isLetter(ch) || ch=='_')
					//如果是单词或下划线就有可能是需要着色的单词
					//返回处理后的下标
					start = coloringWord(start);
				else{
					//如果不是单词就着普通颜色  特殊符号？
					SwingUtilities.invokeLater(new ColorTask(start,1,keySymbolAttr));
					//位置往下移
					start++;
				}
			}
		}
		
		/**
		 * 判断是否关键单词，如果是则着色
		 * @param pos
		 * @return	单词结束位置，以便进行下一个单词的查找
		 */
		private int coloringWord(int pos) {
			//获取pos所在单词的结束索引
			int end = indexOfWordEnd(pos);
			//取得单词
			try {
				String word = doc.getText(pos, end-pos);
				if(keywords.contains(word))			//判断是否是关键字
					SwingUtilities.invokeLater(new ColorTask(pos,end-pos,keyAttr));
				else
					SwingUtilities.invokeLater(new ColorTask(pos,end-pos,normalAttr));
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
			return end;
		}


		/**
		 * 取得 pos下标所有单词的  结束下标或索引
		 * @param pos
		 * @return
		 */
		private int indexOfWordEnd(int pos) {
			for(; isWordCharacter(pos); pos++);
			return pos;
		}
 
		/**
		 * 取得下标为pos时，所在单词  开始的下标
		 * @param pos
		 * @return
		 */
		private int indexOfWordStart(int pos) {
			
			//由pos开始向前找第一个非单词字符
			for(; pos>0 && isWordCharacter(pos-1); pos--){
				
			}
			return pos;
		}

		/**
		 * 
		 * @param pos
		 * @return
		 */
		private boolean isWordCharacter(int pos) {
			
			char c=getCharAt(pos);
			
			if(Character.isLetter(c)|| Character.isDigit(c) || c=='_')
				return true;
			
			return false;
		}
		
		/**
		 * 取得下标在pos处的字符值
		 * @param pos
		 * @return
		 */
		private char getCharAt(int pos) {
			char c=0;
			try {
				c = doc.getText(pos, 1).charAt(0);
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
			return c;
		}

	}
		
	/**
	 * 停止JAVA模式的方法
	 * @param isJavaModel
	 */
	public void setStartModel(boolean isJavaModel) {
		
		this.isJavaModel = isJavaModel;
		doc.setCharacterAttributes(0, doc.getLength(), normalAttr, true);	
	}
	
	/**
	 *  着色共用线程类
	 * @author ding
	 *
	 */ 
	class ColorTask implements Runnable{
		
		private int start,length;
		//文本属性
		private MutableAttributeSet attribute;
		public ColorTask(int start, int len, MutableAttributeSet attribute) {
			this.start = start;
			this.length = len;
			this.attribute = attribute;
		}

		public void run() {
			if(isJavaModel)
				doc.setCharacterAttributes(start, length, attribute, true);
			else
				doc.setCharacterAttributes(0, doc.getLength(), normalAttr, true);
			
		}
		
	}
	

}

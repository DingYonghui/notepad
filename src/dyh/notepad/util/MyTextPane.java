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
 * ��չ��JTextPane
 * ʵ�ֹؼ��ָ���
 * @author ding
 *
 */
public class MyTextPane extends JTextPane{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6775339437598960844L;
	
	//������ʽ�ĵ�               StyledDocument����ͨ����ʽ���ĵ��Ľӿڡ� 
	private StyledDocument doc;
			
	//�ؼ������ԣ���ʽ��     MutableAttributeSet�������ԵĿɱ伯�ϵ�ͨ�ýӿ�
	private MutableAttributeSet keyAttr ;
	//�ؼ���������
	private MutableAttributeSet keySymbolAttr ;
	//һ���ı�����
	private MutableAttributeSet normalAttr ;
	
	//�ؼ��ּ���
	private Set<String> keywords,keySymbol;
	//�ؼ�������
	private String[] keys = {"public","private","new","null","protected","class","for","int","while","do",
							"static","void","float","double","true","false","null","boolean","if","else"
							,"try","catch","import","package"};
	
	//JAVAģʽ
	private boolean isJavaModel=true;

	/**
	 * ���췽��
	 */
	public  MyTextPane() {
		
		//��ȡtextPane�ĵ�
		doc = (StyledDocument)this.getDocument();
		
		// MutableAttributeSet ��ֱ��ʵ�֡� 
		keyAttr = new SimpleAttributeSet();
		keySymbolAttr = new SimpleAttributeSet();
		normalAttr = new SimpleAttributeSet();
		
		//���ùؼ�����ʽ 
		StyleConstants.setFontFamily(keyAttr, "Arial");		
		//��ӹؼ���ǰ��ɫ
		StyleConstants.setForeground(keyAttr, new Color(255,100,100));
		StyleConstants.setBold(keyAttr, true);
		
		//��ӹؼ�����ǰ��ɫ
		StyleConstants.setForeground(keySymbolAttr, Color.BLUE);	
		StyleConstants.setFontFamily(keySymbolAttr, "serif");
		StyleConstants.setBold(keySymbolAttr, true);
		
		//���һ���ı�ǰ��ɫ
		StyleConstants.setForeground(normalAttr, Color.black);
		//���һ���ı�����
		StyleConstants.setFontFamily(normalAttr, "����");
		//һ���ı������Ƿ�Ӵ�
		StyleConstants.setBold(normalAttr, false);
			
		//�ؼ��ּ���
		keywords = new HashSet<String>();
		for(int i = 0; i< keys.length; i++)
			keywords.add(keys[i]);
		
		//�ؼ�����
		keySymbol = new HashSet<String>();
		keySymbol.add("{");
		keySymbol.add("}");
		keySymbol.add("(");
		keySymbol.add(")");
		
		//Ϊdocument��Ӽ�����
		doc.addDocumentListener(new JNotepadDocumentListener());
		
	}
	
	/**
	 *  ��������
	 * @author ding
	 *
	 */ 
	private class JNotepadDocumentListener implements DocumentListener {
	
		public void insertUpdate(DocumentEvent e) {
			
			//��������λ��    �����ĵ��и��Ŀ�ʼ��ƫ������
			int pos = e.getOffset();
			//��������    ���ظ��ĵĳ��ȡ�
			int len = e.getLength();
			//������ɫ����
			coloring(pos,len);

		}

		
		public void removeUpdate(DocumentEvent e) {
			//����ɾ��λ��
			int pos = e.getOffset();
			//��������
			//len = e.getLength();
			//������ɫ����
			coloring(pos,0);
			
		}
		
		public void changedUpdate(DocumentEvent e) {

		}
		
				
		public void coloring(int pos,int len){
			//---------ȡ�ò�����Ӱ�쵽�ĵ���
			//���ʵĿ�ʼ������
			int start = indexOfWordStart(pos);
			//���ʵĽ���������
			int end = indexOfWordEnd(pos+len);
			
			char ch;
			
			while(start < end){
				
				ch = getCharAt(start);
				
				if(Character.isLetter(ch) || ch=='_')
					//����ǵ��ʻ��»��߾��п�������Ҫ��ɫ�ĵ���
					//���ش������±�
					start = coloringWord(start);
				else{
					//������ǵ��ʾ�����ͨ��ɫ  ������ţ�
					SwingUtilities.invokeLater(new ColorTask(start,1,keySymbolAttr));
					//λ��������
					start++;
				}
			}
		}
		
		/**
		 * �ж��Ƿ�ؼ����ʣ����������ɫ
		 * @param pos
		 * @return	���ʽ���λ�ã��Ա������һ�����ʵĲ���
		 */
		private int coloringWord(int pos) {
			//��ȡpos���ڵ��ʵĽ�������
			int end = indexOfWordEnd(pos);
			//ȡ�õ���
			try {
				String word = doc.getText(pos, end-pos);
				if(keywords.contains(word))			//�ж��Ƿ��ǹؼ���
					SwingUtilities.invokeLater(new ColorTask(pos,end-pos,keyAttr));
				else
					SwingUtilities.invokeLater(new ColorTask(pos,end-pos,normalAttr));
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
			return end;
		}


		/**
		 * ȡ�� pos�±����е��ʵ�  �����±������
		 * @param pos
		 * @return
		 */
		private int indexOfWordEnd(int pos) {
			for(; isWordCharacter(pos); pos++);
			return pos;
		}
 
		/**
		 * ȡ���±�Ϊposʱ�����ڵ���  ��ʼ���±�
		 * @param pos
		 * @return
		 */
		private int indexOfWordStart(int pos) {
			
			//��pos��ʼ��ǰ�ҵ�һ���ǵ����ַ�
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
		 * ȡ���±���pos�����ַ�ֵ
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
	 * ֹͣJAVAģʽ�ķ���
	 * @param isJavaModel
	 */
	public void setStartModel(boolean isJavaModel) {
		
		this.isJavaModel = isJavaModel;
		doc.setCharacterAttributes(0, doc.getLength(), normalAttr, true);	
	}
	
	/**
	 *  ��ɫ�����߳���
	 * @author ding
	 *
	 */ 
	class ColorTask implements Runnable{
		
		private int start,length;
		//�ı�����
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

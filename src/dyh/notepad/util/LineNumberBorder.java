package dyh.notepad.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.border.AbstractBorder;

/**
 * �кŵĴ���
 * �̳в���д��    AbstractBorder��
 * ����ʱֻ�� textPane.setBorder(new LineNumberBorder());
 * @author ding
 *
 */
public class LineNumberBorder extends AbstractBorder{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3649581537220540020L;

	public LineNumberBorder(){
		
	}
	
	/**
	 * insert����ʱ�����߽�ı�ʾ��ʽ����ָ�����������������Ե�����Ŀռ�
	 */
	//�˷�����ʵ����ʱ�Զ�����
	//�˷�����ϵ���߿��Ƿ�ռ������Ŀռ�
	public Insets getBorderInsets(Component cp){
		
		return getBorderInsets(cp,new Insets(0,0,0,0));
		
	}
	
    public Insets getBorderInsets(Component cp,Insets insets){
		
		if(cp instanceof JTextPane){
			//�����к���߱߾�
			insets.left = 30 ;
		}
		return insets ;
	}

    public boolean isBorderOpaque(){ return false ; }
    
    //�߿�Ļ��Ʒ���
    //�˷�������ʵ��
    public void paintBorder(Component cp,Graphics g,
    		int x,int y,int width,int height){
    	//��ȡ��ǰ��������ı߽����
    	java.awt.Rectangle clip = g.getClipBounds();
    	FontMetrics fm = g.getFontMetrics();
    	int fontHeight = fm.getHeight();
    	int ybaseline = y + fm.getAscent();
    	int startingLineNumber = (clip.y/fontHeight) + 1 ;
    	
    	if(startingLineNumber != 1){
    		ybaseline = y + startingLineNumber*fontHeight-(fontHeight-fm.getAscent());
    	}
    	
    	int yend = ybaseline + height ;
    	if(yend > (y+height)){
    		yend = y + height ;
    	}
    	g.setColor(Color.blue);
    	
    	//�����к�
    	while(ybaseline<yend){
    		
    		String lbl = padLabel(startingLineNumber,0,true);
    		g.drawString(lbl, 0, ybaseline);
    		ybaseline += fontHeight ;
    		startingLineNumber ++ ;
    	}
    }
    
    //Ѱ���ʺϵ����ֿ��
    private int lineNumberWidth(JTextArea jta){
    	
    	int lineCount = Math.max(jta.getRows(), jta.getLineCount());
    	
    	return jta.getFontMetrics(jta.getFont()).stringWidth(lineCount+"");
    	
    }
    
    private String padLabel(int lineNumber, int length, boolean addSpace){
    	
    	StringBuffer buffer = new StringBuffer();
    	buffer.append(lineNumber);
    	for(int count=(length-buffer.length());count > 0;count--){
    		buffer.insert(0,' ');    		
    	}
    	if(addSpace){
    		buffer.append(' ');
    	}
    	return buffer.toString();
    }
     
}

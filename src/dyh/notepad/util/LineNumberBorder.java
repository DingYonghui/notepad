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
 * 行号的处理
 * 继承并重写了    AbstractBorder类
 * 调用时只需 textPane.setBorder(new LineNumberBorder());
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
	 * insert对象时容器边界的表示形式。它指容器必须在其各个边缘留出的空间
	 */
	//此方法在实例化时自动调用
	//此方法关系到边框是否占用组件的空间
	public Insets getBorderInsets(Component cp){
		
		return getBorderInsets(cp,new Insets(0,0,0,0));
		
	}
	
    public Insets getBorderInsets(Component cp,Insets insets){
		
		if(cp instanceof JTextPane){
			//设置行号左边边距
			insets.left = 30 ;
		}
		return insets ;
	}

    public boolean isBorderOpaque(){ return false ; }
    
    //边框的绘制方法
    //此方法必须实现
    public void paintBorder(Component cp,Graphics g,
    		int x,int y,int width,int height){
    	//获取当前剪贴区域的边界矩形
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
    	
    	//绘制行号
    	while(ybaseline<yend){
    		
    		String lbl = padLabel(startingLineNumber,0,true);
    		g.drawString(lbl, 0, ybaseline);
    		ybaseline += fontHeight ;
    		startingLineNumber ++ ;
    	}
    }
    
    //寻找适合的数字宽度
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

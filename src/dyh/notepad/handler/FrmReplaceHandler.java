package dyh.notepad.handler;

import javax.swing.JOptionPane;

import dyh.notepad.ui.FrmMain;

/**
 * 替换界面处理的具体操作类
 * @author ding
 *
 */
public class FrmReplaceHandler {
		
	/**
	 * “替换”的处理操作
	 * @param SearchedText
	 * @param replaceWith
	 */
	public void replace(String selectedText, String replaceWith){
					
		//如果被查找到的文本不为空，即可执行替换
		if(selectedText != null){
			FrmMain.textPane.replaceSelection(replaceWith);
		}
			
	}
	
	/**
	 * “全部替换”的处理操作
	 * @param allTxt
	 * @param isMatchCase 
	 * @param needSearchTxt
	 * @param replacedStr
	 */
	public void replaceAll(String allText ,String needSearchText,
			String replaceWith, boolean isMatchCase)  {
		
		//将光标放到编辑区开头	
		FrmMain.textPane.moveCaretPosition(0);
		
		//查找的位置
		int position = 0 ;
		//替换的个数
		int replaceCount = 0;
		//查找的处理对象
		FrmSearchHandler frmSearchHandlernew = new FrmSearchHandler();
		
		while (position < allText.length()) {
		
			//查找到的文本，同时也被选中
			String selectedText = null;
			try {
				selectedText = frmSearchHandlernew.searchByDown(allText, needSearchText, isMatchCase);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//如果能查到，则替换
			if(selectedText != null){
				replace(selectedText, replaceWith);
				replaceCount ++ ;
			}
			
			position ++ ;
			
			allText = FrmMain.textPane.getText();
		}
		
		if(replaceCount==0){
			JOptionPane.showMessageDialog(FrmMain.textPane,
		    		"找不到   “"+needSearchText+"”","提示",
		    		JOptionPane.INFORMATION_MESSAGE);
		}else{
			JOptionPane.showMessageDialog(FrmMain.textPane, 
					"共替换了"+replaceCount+"个", "提示", 
					JOptionPane.INFORMATION_MESSAGE);
		}
		
				
	}

}

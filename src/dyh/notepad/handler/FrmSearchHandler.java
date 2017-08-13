package dyh.notepad.handler;

import dyh.notepad.ui.FrmMain;

/**
 * 查找界面处理的具体操作类
 * @author ding
 *
 */
public class FrmSearchHandler {
	

	/**
	 * 查找
	 * @param allText
	 * @param needSearchText
	 * @param isSearchByDown
	 * @param isMatchCase
	 * @return 
	 * @throws Exception
	 */
	public String Search(String allText, String needSearchText, 
			boolean isSearchByDown,boolean isMatchCase) throws Exception{
		//向下查找
		if(isSearchByDown){
			return searchByDown(allText, needSearchText, isMatchCase);
		}
		//向上查找
		else{
			return searchByUp(allText, needSearchText, isMatchCase);
		}
			
	}
	
	/**
	 *  向下查找
	 * @param allText
	 * @param needSearchText
	 * @param isMatchCase
	 * @return
	 * @throws Exception
	 */
	public String searchByDown(String allText,String needSearchText, boolean isMatchCase) throws Exception{
			
        String temp1,temp2;
			        
        temp1 = allText.toUpperCase();	        //文本域中全部内容的大写
        temp2 = needSearchText.toUpperCase();	//需查找内容的大写
		
		// "不区分大小写"
		if (!isMatchCase) {
			allText = temp1;
			needSearchText = temp2;						
		} 
		
		//获取当前位置
		int findStartPosition = FrmMain.textPane.getCaretPosition();
		
		//a为光标指针，b为关键字长度
		int position = 0 ;
		int needSearchTextLength = needSearchText.length();
		
		if (FrmMain.textPane.getSelectedText() == null) {
			position = allText.indexOf(needSearchText, findStartPosition);
		} else {
			position = allText.indexOf(needSearchText, findStartPosition - needSearchText.length() + 1);
		}
		
		if (position > -1) {
			FrmMain.textPane.setCaretPosition(position);
			//将查找结果选择标记
			FrmMain.textPane.select(position, position + needSearchTextLength);		
			
			return FrmMain.textPane.getSelectedText();
		}else{//若无，则返回-1						   
		    return null ;
	    }
		
				  	    	
	}
	
	/**
	 * 向上查找
	 * @param allText
	 * @param needSearchText
	 * @param isMatchCase
	 * @return
	 * @throws Exception
	 */
	private String searchByUp(String allText,String needSearchText, boolean isMatchCase)throws Exception{
		
		String temp1,temp2;
        
        temp1 = allText.toUpperCase();	        //文本域中全部内容的大写
        temp2 = needSearchText.toUpperCase();	//需查找内容的大写
		
		// "不区分大小写"
		if (!isMatchCase) {
			allText = temp1;
			needSearchText = temp2;						
		} 
		
		//获取当前位置
		int findStartPosition = FrmMain.textPane.getCaretPosition();
		
		//a为光标指针，b为要查找的文本长度
		int position = 0 ;
		int needSearchTextLength = needSearchText.length();
		
		if (FrmMain.textPane.getSelectedText() == null) {	//是否在选择的文本中选择
			position = allText.lastIndexOf(needSearchText, findStartPosition - 1);
		} else {										
			position = allText.lastIndexOf(needSearchText, findStartPosition - needSearchTextLength - 1);
		}
		
		if (position > -1) {
			FrmMain.textPane.setCaretPosition(position);
			//将查找结果选择标记
			FrmMain.textPane.select(position, position + needSearchTextLength);
			return FrmMain.textPane.getSelectedText();
	    } else {	//若无，则返回-1				
	        return null ;
	    }
	
	}
		
}

package dyh.notepad.handler;

import dyh.notepad.ui.FrmMain;

/**
 * ���ҽ��洦��ľ��������
 * @author ding
 *
 */
public class FrmSearchHandler {
	

	/**
	 * ����
	 * @param allText
	 * @param needSearchText
	 * @param isSearchByDown
	 * @param isMatchCase
	 * @return 
	 * @throws Exception
	 */
	public String Search(String allText, String needSearchText, 
			boolean isSearchByDown,boolean isMatchCase) throws Exception{
		//���²���
		if(isSearchByDown){
			return searchByDown(allText, needSearchText, isMatchCase);
		}
		//���ϲ���
		else{
			return searchByUp(allText, needSearchText, isMatchCase);
		}
			
	}
	
	/**
	 *  ���²���
	 * @param allText
	 * @param needSearchText
	 * @param isMatchCase
	 * @return
	 * @throws Exception
	 */
	public String searchByDown(String allText,String needSearchText, boolean isMatchCase) throws Exception{
			
        String temp1,temp2;
			        
        temp1 = allText.toUpperCase();	        //�ı�����ȫ�����ݵĴ�д
        temp2 = needSearchText.toUpperCase();	//��������ݵĴ�д
		
		// "�����ִ�Сд"
		if (!isMatchCase) {
			allText = temp1;
			needSearchText = temp2;						
		} 
		
		//��ȡ��ǰλ��
		int findStartPosition = FrmMain.textPane.getCaretPosition();
		
		//aΪ���ָ�룬bΪ�ؼ��ֳ���
		int position = 0 ;
		int needSearchTextLength = needSearchText.length();
		
		if (FrmMain.textPane.getSelectedText() == null) {
			position = allText.indexOf(needSearchText, findStartPosition);
		} else {
			position = allText.indexOf(needSearchText, findStartPosition - needSearchText.length() + 1);
		}
		
		if (position > -1) {
			FrmMain.textPane.setCaretPosition(position);
			//�����ҽ��ѡ����
			FrmMain.textPane.select(position, position + needSearchTextLength);		
			
			return FrmMain.textPane.getSelectedText();
		}else{//���ޣ��򷵻�-1						   
		    return null ;
	    }
		
				  	    	
	}
	
	/**
	 * ���ϲ���
	 * @param allText
	 * @param needSearchText
	 * @param isMatchCase
	 * @return
	 * @throws Exception
	 */
	private String searchByUp(String allText,String needSearchText, boolean isMatchCase)throws Exception{
		
		String temp1,temp2;
        
        temp1 = allText.toUpperCase();	        //�ı�����ȫ�����ݵĴ�д
        temp2 = needSearchText.toUpperCase();	//��������ݵĴ�д
		
		// "�����ִ�Сд"
		if (!isMatchCase) {
			allText = temp1;
			needSearchText = temp2;						
		} 
		
		//��ȡ��ǰλ��
		int findStartPosition = FrmMain.textPane.getCaretPosition();
		
		//aΪ���ָ�룬bΪҪ���ҵ��ı�����
		int position = 0 ;
		int needSearchTextLength = needSearchText.length();
		
		if (FrmMain.textPane.getSelectedText() == null) {	//�Ƿ���ѡ����ı���ѡ��
			position = allText.lastIndexOf(needSearchText, findStartPosition - 1);
		} else {										
			position = allText.lastIndexOf(needSearchText, findStartPosition - needSearchTextLength - 1);
		}
		
		if (position > -1) {
			FrmMain.textPane.setCaretPosition(position);
			//�����ҽ��ѡ����
			FrmMain.textPane.select(position, position + needSearchTextLength);
			return FrmMain.textPane.getSelectedText();
	    } else {	//���ޣ��򷵻�-1				
	        return null ;
	    }
	
	}
		
}

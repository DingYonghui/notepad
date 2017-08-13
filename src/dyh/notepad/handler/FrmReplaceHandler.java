package dyh.notepad.handler;

import javax.swing.JOptionPane;

import dyh.notepad.ui.FrmMain;

/**
 * �滻���洦��ľ��������
 * @author ding
 *
 */
public class FrmReplaceHandler {
		
	/**
	 * ���滻���Ĵ������
	 * @param SearchedText
	 * @param replaceWith
	 */
	public void replace(String selectedText, String replaceWith){
					
		//��������ҵ����ı���Ϊ�գ�����ִ���滻
		if(selectedText != null){
			FrmMain.textPane.replaceSelection(replaceWith);
		}
			
	}
	
	/**
	 * ��ȫ���滻���Ĵ������
	 * @param allTxt
	 * @param isMatchCase 
	 * @param needSearchTxt
	 * @param replacedStr
	 */
	public void replaceAll(String allText ,String needSearchText,
			String replaceWith, boolean isMatchCase)  {
		
		//�����ŵ��༭����ͷ	
		FrmMain.textPane.moveCaretPosition(0);
		
		//���ҵ�λ��
		int position = 0 ;
		//�滻�ĸ���
		int replaceCount = 0;
		//���ҵĴ������
		FrmSearchHandler frmSearchHandlernew = new FrmSearchHandler();
		
		while (position < allText.length()) {
		
			//���ҵ����ı���ͬʱҲ��ѡ��
			String selectedText = null;
			try {
				selectedText = frmSearchHandlernew.searchByDown(allText, needSearchText, isMatchCase);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//����ܲ鵽�����滻
			if(selectedText != null){
				replace(selectedText, replaceWith);
				replaceCount ++ ;
			}
			
			position ++ ;
			
			allText = FrmMain.textPane.getText();
		}
		
		if(replaceCount==0){
			JOptionPane.showMessageDialog(FrmMain.textPane,
		    		"�Ҳ���   ��"+needSearchText+"��","��ʾ",
		    		JOptionPane.INFORMATION_MESSAGE);
		}else{
			JOptionPane.showMessageDialog(FrmMain.textPane, 
					"���滻��"+replaceCount+"��", "��ʾ", 
					JOptionPane.INFORMATION_MESSAGE);
		}
		
				
	}

}

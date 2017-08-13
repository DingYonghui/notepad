package dyh.notepad.handler;

import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.undo.UndoManager;

import dyh.notepad.ui.FrmFont;
import dyh.notepad.ui.FrmMain;
import dyh.notepad.ui.FrmReplace;
import dyh.notepad.ui.FrmSearch;
import dyh.notepad.util.Constants;
import dyh.notepad.util.LineNumberBorder;
/**
 * 主界面处理的具体操作类
 * @author ding
 *
 */
public class FrmMainHandler {
	
	//管理撤消操作的类	
	public UndoManager undoManager = new UndoManager();

	//查找界面
	FrmSearch frmSearch = null ;
	//替换界面
	FrmReplace frmReplace = null ;
	//字体界面
	FrmFont frmFont = null;
		
	/**
	 * “新建”的处理操作
	 * @param mainFrm
	 */
	public void doNewBuilt(FrmMain mainFrm){
			
		//文本发生改变（即当前文本与被保存的文本不相同）
		if(isTextChange(mainFrm)){
			
			int n = JOptionPane.showConfirmDialog(mainFrm, "是否将更改保存到  无标题？",
					"记事本",JOptionPane.YES_NO_CANCEL_OPTION);	
			
			if(n == JOptionPane.YES_OPTION){
				doSave(mainFrm);			
			}
			else if(n == JOptionPane.CANCEL_OPTION){
				return ;
			}
		}
		
		//新建即清空文本域
		FrmMain.textPane.setText("");
														
	}
	
	/**
	 * 文本域内容是否发生改变
	 * @param mainFrm
	 * @return
	 */
	public boolean isTextChange(FrmMain mainFrm){
			
		//如果文本内容发生改变
		if(!Constants.SAVED_TEXT.equals(FrmMain.textPane.getText()))
			return true;
		else
			return false;
		
	}
	
	/**
	 * “打开”的处理操作
	 * @param mainFrm
	 */
	public void doOpen(FrmMain frmMain){
		
		//检查是否有修改
		if(isTextChange(frmMain)){
			
			int n = JOptionPane.showConfirmDialog(frmMain, "文本内容已改变，是否需要保存？",
					"记事本",JOptionPane.YES_NO_CANCEL_OPTION);
			if(n == JOptionPane.YES_OPTION){
				doSave(frmMain);			
			}	
			else if(n == JOptionPane.CANCEL_OPTION){
				return ;
			}
						
		}
		
		// 创建一个具有指定标题的文件对话框窗口，用于加载文件。
		FileDialog filedialog = new FileDialog
				(frmMain,"打开",FileDialog.LOAD);
		filedialog.setVisible(true);
		
		//获取文件对话框中用户选中的文件所在的路径
		String filePath = filedialog.getDirectory();
		//获取对话框中用户选中的文件名
		String fileName = filedialog.getFile();
		try{
			BufferedReader br = new BufferedReader(new FileReader
					(filePath+fileName));
			//从文件中读一行信息
			String line = br.readLine();
			String allText = "" ;
			//循环读文件中的内容，并显示到文本域中
			while(line != null){
				allText = allText + line + "\n" ;
				//读下一行
				line = br.readLine();
			}
			FrmMain.textPane.setText(allText);
			Constants.SAVED_TEXT = allText ;
			//关闭输入流
			br.close();
		}catch(Exception e){
			e.printStackTrace();	
		}
				
	}
			
	/**
	 * “保存”的处理操作
	 * @param frmMain 
	 */
	public void doSave(FrmMain frmMain){
		
		//如果没有保存过，则另存为
		if(Constants.SAVED_FILE_PATH == null){
			doSaveAs(frmMain);
		}
		//如果有保存过，则直接保存
		else{
			try{
				 
				 String temp = FrmMain.textPane.getText();
				 Constants.SAVED_TEXT = temp ;
				 //创建一个文件输出流，用于写文件
				 BufferedWriter bw = new BufferedWriter(new FileWriter(Constants.SAVED_FILE_PATH)) ;
				 //将文本域中的信息写入文件中
				 bw.write(temp);
				 bw.flush();
				 //关闭输出流
				 bw.close();
			 }
			 catch(Exception e){
				 e.printStackTrace();
			 }	
		}
		  
	}
	
	/**
	 * “另存为”的处理操作
	 * @param frmMain
	 */
	public void doSaveAs(FrmMain frmMain){
				
		try{
			  /**
			   * FileDialog 类显示一个对话框窗口，用户可以从中选择文件。
			   * 由于它是一个模式对话框，当应用程序调用其 show 方法来显示对话框时，
			   * 它将阻塞其余应用程序，直到用户选择一个文件。 
			   * FileDialog.SAVE此常量值指示文件对话框窗口的作用是查找要写入的文件。
			   */
			  FileDialog filedialog = new FileDialog(frmMain,"另存为",FileDialog.SAVE);
			  filedialog.setVisible(true);
			  
			  //获取此文件对话框的目录名
			  String directory = filedialog.getDirectory();	
			  if(directory == null)
				  return ;
			  //获取此文件对话框的选定文件名
			  String fileName = filedialog.getFile(); 
			  if(fileName == null )
				  return ;
			  String fileType = ".txt";
			  String allText = FrmMain.textPane.getText() ;		  		  
			  String filePath = directory+fileName+fileType ;
			    			  
			  Constants.SAVED_TEXT = allText;
			  Constants.SAVED_FILE_PATH = filePath ;
			 		  
			  //创建缓冲输出流
			  BufferedWriter bfw = new BufferedWriter(new FileWriter(filePath));
			  //将文本域的内容写入到（filePath）中
			  bfw.write(allText);
			  bfw.flush();
			  //关闭输出流
			  bfw.close();
				 			     			   			 
		}catch(Exception e){
		    JOptionPane.showMessageDialog(FrmMain.textPane,"读入错误","警告",JOptionPane.WARNING_MESSAGE);			     
			e.printStackTrace();
		}
		
	}
		
	/**
	 * “撤消”的处理操作
	 * @param undoManager 
	 * @param frmMain 
	 */
    public void doCancel(){  
    	
    	if(undoManager.canUndo()){	   		
    		undoManager.undo();	
		}   
    	      	
    }
    
	/**
	 * “剪切”的处理操作
	 */
    public void doCut(){
    	FrmMain.textPane.cut();  	
     }
    
    /**
     * “复制”的处理操作
     */
    public  void doCopy(){  
    	FrmMain.textPane.copy();
    }
    
    /**
     * “粘贴”的处理操作
     */
    public void doPaste(){   	
    	FrmMain.textPane.paste();
    }
    
    /**
     * “删除”的处理操作
     */
    public void doDelete() {  			
		FrmMain.textPane.replaceSelection("");	
	}
    
    /**
     * “全选”的处理操作
     */
    public void doSecletAll(){
    	FrmMain.textPane.selectAll();
    	FrmMain.textPane.setSelectedTextColor(Color.blue);
    }
    
    /**
	 * 时间/日期
	 */
	public void doTimeAndDate() {
		FrmMain.textPane.replaceSelection( new Date().toString());
	}

    /**
     * “自动换行”的处理操作
     */
    public void doLineWrap(){
    	 /* if(notepadUI.getChckbxmntmLineWrap().isSelected()){
    		 
    		  FrmMain.textPane.setLineWrap(true);   
    		  FrmMain.textPane.setWrapStyleWord(true);//断行不断字
    	  }
    	  else {
    		  FrmMain.textPane.setLineWrap(false);  	   
    	  }*/
     }  
    
    /**
     * “状态栏”的处理操作
     * @param isSelectStatus 
     */
    public void doStatus(boolean isSelectStatus){    //未完成      
  	 
    	if(isSelectStatus){
    		FrmMain.textPane.setBorder(new LineNumberBorder());
    	}
    	else{
    		FrmMain.textPane.setBorder(null);
    	}
    	
    	//Highlighter highLight = FrmMain.textPane.getHighlighter();
    	
   } 
    	
	/**
	 * “字体”的操作,弹出字体对话框
	 */
	public void clickFont(){
		
		if(frmFont == null ){
			frmFont = new FrmFont();
		}	
		frmFont.setVisible(true); 	
	}
	
	/**
	 * “查找”的操作,弹出查找对话框
	 */
	public void clickSearch() {
		
		if(frmSearch == null){
			frmSearch = new FrmSearch();
		}
		frmSearch.setVisible(true);	
	}
	
    /**
     * “查找下一个”的操作,弹出对话框
     */
    public void clickSearchNext(){
    
    	//如果没有查找记录，则执行新的查找
    	if(frmSearch == null ){
    		clickSearch() ;
    	}
    	
    	//如果没有查找记录，则直接查找
    	String allText = FrmMain.textPane.getText();
    	String needSearchText = frmSearch.textSearch.getText();
    	boolean isMatchCase = frmSearch.isMatchCase();
    	boolean isSearchByDown = frmSearch.isSearchByDown();
    	
    	FrmSearchHandler frmSearchHandler = new FrmSearchHandler();
    	String selectText = null ;
    	try {
    		selectText = frmSearchHandler.Search(allText, needSearchText, isSearchByDown, isMatchCase);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	if(selectText == null)
			JOptionPane.showMessageDialog(FrmMain.textPane,
		    		"找不到"+needSearchText,"提示",JOptionPane.INFORMATION_MESSAGE);
    	   	
    }
    
    /**
     * “替换”的操作,弹出对话框
     */
    public void clickReplace(){
    	
    	if(frmReplace == null ){
    		frmReplace = new FrmReplace();
    	}
    	frmReplace.setVisible(true);	
    }
			
	/**
	 * 检测复制、剪切、删除、撤消、粘贴菜单项是否可操作
	 * @param frmMain 
	 */
    public void checkItems(FrmMain frmMain){
		//复制、剪切、删除（有选中文本时可编辑，无选中文本时不可编辑）
		if(FrmMain.textPane.getSelectedText() != null){
			frmMain.setItemEnable_Copy_Cut_Delete(true) ;
		}
		else{  
			frmMain.setItemEnable_Copy_Cut_Delete(false) ;
		}
				
		//撤消
		if(undoManager.canUndo()){	
			frmMain.setItemEnable_Cancel(true);
		}else{
			frmMain.setItemEnable_Cancel(false);
		}		
		
		//Toolkit的子类被用于将各种组件绑定到特定本机工具包实现。getDefaultToolkit()获取默认工具包。
	    //Toolkit toolKit = Toolkit.getDefaultToolkit();
		//获取系统剪贴板
		//Clipboard clipBoard = toolKit.getSystemClipboard();
		//粘贴
		if(Toolkit.getDefaultToolkit().
				getSystemClipboard().isDataFlavorAvailable
				(DataFlavor.stringFlavor)){ //如果剪贴板内有内容则返回true
			frmMain.setItemEnable_Paste(true);
		}else{
			frmMain.setItemEnable_Paste(false);
		}
	}        
		
}


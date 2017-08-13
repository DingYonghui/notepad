package dyh.notepad.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

import dyh.notepad.control.FrmMainControl;
import dyh.notepad.util.MyTextPane;

/**
 * 记事本主界面类
 * @author ding
 *
 */
public class FrmMain extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6419920947814772433L;
	
	private JPanel contentPane;                  
	
	private	JMenuItem mntmNewBuilt ; //新建
	private JMenuItem mntmOpen ;     //打开
	private JMenuItem mntmSave ;     //保存
	private JMenuItem mntmSaveAs ;   //另存为
	private JMenuItem mntmExit ;     //退出
	
	private JMenuItem mntmCancel ;   //撤消
	private JMenuItem mntmCut ;      //剪切
	private JMenuItem mntmCopy ;     //复制
	private JMenuItem mntmPaste ;    //粘贴
	private JMenuItem mntmDelete ;   //删除
	private JMenuItem mntmSearch ;     //查找
	private JMenuItem mntmSearchNext ; //查找下一个
	private JMenuItem mntmReplace ;  //替换
	private JMenuItem mntmSelectAll ;//全选
	private JMenuItem mntmTimeAndDate ;//时间日期
	
	private JCheckBoxMenuItem chckbxmntmLineWrap ; //自动换行
	private JMenuItem mntmFont ;                   //字体
	
	private JCheckBoxMenuItem chckbxmntmStatus;    //状态栏
	
	private JMenuItem mntmAboutNotepad ;           //关于记事本
	
	public static JTextPane textPane ;     //文本域
	
	private JLabel lblShowStatus ;  //显示状态的标签
	
	//弹出式菜单中的菜单项
	private JMenuItem mntmpopCancel ;     //撤消
	private JMenuItem mntmpopCut ;        //剪切				
	private JMenuItem mntmpopCopy ;       //复制				
	private JMenuItem mntmpopPaste ;      //粘贴
	private JMenuItem mntmpopDelete;      //删除
	private JMenuItem mntmpopSelectAll;   //全选
	
	/**
	 * Create the frame.
	 */
	public FrmMain() {
		
		//事件处理对象
		FrmMainControl control=new FrmMainControl(this);
		
		setTitle("\u8BB0\u4E8B\u672C");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350, 100, 650, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
			
		//菜单栏
		JMenuBar menuBar = new JMenuBar();
		//设置菜单栏
		setJMenuBar(menuBar);
		
		//文件菜单
		JMenu mnFile = new JMenu("\u6587\u4EF6(F)");
		mnFile.setMnemonic('F');
		menuBar.add(mnFile);
		
		//新建
		mntmNewBuilt = new JMenuItem("\u65B0\u5EFA(N)");
		mntmNewBuilt.setMnemonic('N');
		mntmNewBuilt.setMnemonic(KeyEvent.VK_N);
		mntmNewBuilt.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		mntmNewBuilt.addActionListener(control);
		mnFile.add(mntmNewBuilt);
		
		//打开
		mntmOpen = new JMenuItem("\u6253\u5F00(O)");
		mntmOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		mntmOpen.setMnemonic(KeyEvent.VK_O);
		mntmOpen.addActionListener(control);
		mnFile.add(mntmOpen);
		
		//保存
		mntmSave = new JMenuItem("\u4FDD\u5B58(S)");
		mntmSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mntmSave.setMnemonic('S');
		mntmSave.setMnemonic(KeyEvent.VK_S);
		mntmSave.addActionListener(control);
		mnFile.add(mntmSave);
		
		//另存为
		mntmSaveAs = new JMenuItem("\u53E6\u5B58\u4E3A(A)");
		mntmSaveAs.setMnemonic('A');
		mntmSaveAs.addActionListener(control);
		mnFile.add(mntmSaveAs);
		
		//分割线
		JSeparator separator = new JSeparator();
		mnFile.add(separator);
		
		//页面设置
		//JMenuItem mntmPageSetup = new JMenuItem("\u9875\u9762\u8BBE\u7F6E");
		//mntmPageSetup.addActionListener(new ActionListener() {
		//	public void actionPerformed(ActionEvent e) {
		//		PageSetupUI pageSetup = new PageSetupUI();
		//		pageSetup.setVisible(true);
		//	}
		//});
		//mnFile.add(mntmPageSetup);
		
		//打印
		//JMenuItem mntmPrint = new JMenuItem("\u6253\u5370\uFF08P\uFF09         Ctrl+P");
		//mnFile.add(mntmPrint);
		
		//分割线
		//JSeparator separator_1 = new JSeparator();
		//mnFile.add(separator_1);
		
		//退出
		mntmExit = new JMenuItem("\u9000\u51FA(X)");
		mntmExit.setMnemonic('X');	
		mntmExit.addActionListener(control);
		mnFile.add(mntmExit);
		
		//编辑
		JMenu mnEdit = new JMenu("\u7F16\u8F91(E)");
		mnEdit.addMouseListener(control);
		mnEdit.setMnemonic('E');
		menuBar.add(mnEdit);		
		
		//撤消
		mntmCancel = new JMenuItem("\u64A4\u6D88(U)");
		mntmCancel.setMnemonic('U');
		mntmCancel.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK));
		mntmCancel.setEnabled(false);
		mntmCancel.addActionListener(control);
		mnEdit.add(mntmCancel);
		
		//分割线
		JSeparator separator_2 = new JSeparator();
		mnEdit.add(separator_2);
		
		//剪切
		mntmCut = new JMenuItem("\u526A\u5207(T)");
		mntmCut.setMnemonic('T');
		mntmCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
		mntmCut.setEnabled(false);
		mntmCut.addActionListener(control);
		mnEdit.add(mntmCut);
		
		//复制
		mntmCopy = new JMenuItem("\u590D\u5236(C)");
		mntmCopy.setMnemonic('C');
		mntmCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
		mntmCopy.setEnabled(false);
		mntmCopy.addActionListener(control);
		mnEdit.add(mntmCopy);
			
		//粘贴
		mntmPaste = new JMenuItem("\u7C98\u8D34(P)");
		mntmPaste.setMnemonic('P');
		mntmPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));
		mntmPaste.setEnabled(false);
		mntmPaste.addActionListener(control);
		mnEdit.add(mntmPaste);
		
		//删除
		mntmDelete = new JMenuItem("\u5220\u9664(L)");
		mntmDelete.setMnemonic('L');
		mntmDelete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
		mntmDelete.setEnabled(false);
		mntmDelete.addActionListener(control);
		mnEdit.add(mntmDelete);
		
		//分割线
		JSeparator separator_3 = new JSeparator();
		mnEdit.add(separator_3);
		
		//查找
		mntmSearch = new JMenuItem("\u67E5\u627E(F)");
		mntmSearch.setMnemonic('F');
		mntmSearch.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK));
		mntmSearch.setEnabled(false);
		mntmSearch.addActionListener(control);
		mnEdit.add(mntmSearch);
		
		//查找下一个
		mntmSearchNext = new JMenuItem("\u67E5\u627E\u4E0B\u4E00\u4E2A(N)");
		mntmSearchNext.setMnemonic('N');
		mntmSearchNext.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0));
		mntmSearchNext.setEnabled(false);
		mntmSearchNext.addActionListener(control);
		mnEdit.add(mntmSearchNext);
		
		//替换
		mntmReplace = new JMenuItem("\u66FF\u6362(R)");
		mntmReplace.setMnemonic('R');
		mntmReplace.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_MASK));
		mntmReplace.setEnabled(false);
		mntmReplace.addActionListener(control);
		mnEdit.add(mntmReplace);
		
		//转到
		//JMenuItem mntmGoto = new JMenuItem("\u8F6C\u5230\uFF08G\uFF09                Ctrl+G");
		//mntmGoto.setEnabled(false);
		//mnCompile.add(mntmGoto);
		
		//分割线
		JSeparator separator_4 = new JSeparator();
		mnEdit.add(separator_4);
		
		//全选
		mntmSelectAll = new JMenuItem("\u5168\u9009(A)");
		mntmSelectAll.setMnemonic('A');
		mntmSelectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		mntmSelectAll.setEnabled(false);
		mntmSelectAll.addActionListener(control);
		mnEdit.add(mntmSelectAll);
		
		// 时间/日期
		mntmTimeAndDate = new JMenuItem("\u65F6\u95F4/\u65E5\u671F(D)");
		mntmTimeAndDate.setMnemonic('D');
		mntmTimeAndDate.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
		mntmTimeAndDate.addActionListener(control);
		mnEdit.add(mntmTimeAndDate);
		
		//格式
		JMenu mnForm = new JMenu("\u683C\u5F0F(O)");
		mnForm.setMnemonic('O');
		menuBar.add(mnForm);
		
		//自动换行
		chckbxmntmLineWrap = new JCheckBoxMenuItem("\u81EA\u52A8\u6362\u884C(W)");
		chckbxmntmLineWrap.setMnemonic('W');
		chckbxmntmLineWrap.addActionListener(control);
		mnForm.add(chckbxmntmLineWrap);
		
		//字体
		mntmFont = new JMenuItem("\u5B57\u4F53(F)");
		mntmFont.setMnemonic('F');
		mntmFont.addActionListener(control);
		mnForm.add(mntmFont);
		
		//查看
		JMenu mnSee = new JMenu("\u67E5\u770B(V)");
		mnSee.setMnemonic('V');
		menuBar.add(mnSee);
		
		//状态栏
		chckbxmntmStatus = new JCheckBoxMenuItem("\u72B6\u6001\u680F(S)");
		chckbxmntmStatus.setMnemonic('S');
		chckbxmntmStatus.addActionListener(control);
		mnSee.add(chckbxmntmStatus);
		
		//帮助
		JMenu mnHelp = new JMenu("\u5E2E\u52A9(H)");
		mnHelp.setMnemonic('H');
		menuBar.add(mnHelp);
		
		//查看帮助
		//JMenuItem mntmViewHelp = new JMenuItem("\u67E5\u770B\u5E2E\u52A9\uFF08H\uFF09");
		//mnHelp.add(mntmViewHelp);
		
		//关于记事本
		mntmAboutNotepad = new JMenuItem("\u5173\u4E8E\u8BB0\u4E8B\u672C(A)");
		mntmAboutNotepad.setMnemonic('A');
		mntmAboutNotepad.addActionListener(control);
		mnHelp.add(mntmAboutNotepad);
			
		//文本域的滚动窗格
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		//文本域
		textPane = new MyTextPane();
		scrollPane.setViewportView(textPane);
		textPane.addMouseListener(control);
		textPane.getDocument().addDocumentListener(control);
		textPane.getDocument().addUndoableEditListener(control);
		textPane.setFont(new Font("宋体",Font.PLAIN,18));
		
		
		//弹出式菜单
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(textPane, popupMenu);
		//撤消
		mntmpopCancel = new JMenuItem("\u64A4\u6D88(U)");
		mntmpopCancel.setMnemonic('U');
		mntmpopCancel.setEnabled(false);
		mntmpopCancel.addActionListener(control);
		popupMenu.add(mntmpopCancel);
		//分割线
		JSeparator separator_1 = new JSeparator();
		popupMenu.add(separator_1);
		//剪切
		mntmpopCut = new JMenuItem("\u526A\u5207(T)");
		mntmpopCut.setMnemonic('T');
		mntmpopCut.setEnabled(false);
		mntmpopCut.addActionListener(control);
		popupMenu.add(mntmpopCut);
		//复制
		mntmpopCopy = new JMenuItem("\u590D\u5236(C)");
		mntmpopCopy.setMnemonic('C');
		mntmpopCopy.setEnabled(false);
		mntmpopCopy.addActionListener(control);
		popupMenu.add(mntmpopCopy);
		//粘贴
		mntmpopPaste = new JMenuItem("\u7C98\u8D34(P)");
		mntmpopPaste.setMnemonic('P');
		mntmpopPaste.setEnabled(false);
		mntmpopPaste.addActionListener(control);
		popupMenu.add(mntmpopPaste);
		//删除
		mntmpopDelete = new JMenuItem("\u5220\u9664(L)");
		mntmpopDelete.setMnemonic('D');
		mntmpopDelete.setEnabled(false);
		mntmpopDelete.addActionListener(control);
		popupMenu.add(mntmpopDelete);
		//分割线
		JSeparator separator_5 = new JSeparator();
		popupMenu.add(separator_5);
		//全选
		mntmpopSelectAll = new JMenuItem("\u5168\u9009(A)");
		mntmpopSelectAll.setMnemonic('A');
		mntmpopSelectAll.setEnabled(false);
		mntmpopSelectAll.addActionListener(control);
		popupMenu.add(mntmpopSelectAll);
		
		//显示状态的标签
		lblShowStatus = new JLabel("\u663E\u793A\u884C\u53F7");
		lblShowStatus.setVisible(false);
		contentPane.add(lblShowStatus, BorderLayout.SOUTH);	
	}

	//弹出式菜单的处理方法
	private static void addPopup(Component component, final JPopupMenu popup) {
		//内容窗格添加监听者
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
			
	
	/**
	 * 查找、查找下一个、替换菜单项是否可编辑（有文本时可编辑，无文本时不可编辑）
	 * @param yn
	 */
	public void setItemEnable_Search_SearchNext_Replace(boolean isEnable){
		mntmSearch.setEnabled(isEnable);
		mntmSearchNext.setEnabled(isEnable);
		mntmReplace.setEnabled(isEnable);
		repaint();
	}
	
	/**
	 * 复制、剪切、删除菜单项是否可编辑（有选中文本时可编辑，无选中文本时不可编辑）
	 */
	public void setItemEnable_Copy_Cut_Delete(boolean isEnable){
		mntmCopy.setEnabled(isEnable);
		mntmCut.setEnabled(isEnable);	
		mntmDelete.setEnabled(isEnable);
		mntmSelectAll.setEnabled(!isEnable);        //全选
		mntmpopCopy.setEnabled(isEnable);
		mntmpopCut.setEnabled(isEnable);
		mntmpopDelete.setEnabled(isEnable);
		mntmpopSelectAll.setEnabled(!isEnable);    //全选
		repaint();
	}
	
	/**
	 * 撤消菜单项是否可编辑
	 */
	public void setItemEnable_Cancel(boolean isEnable){
		mntmCancel.setEnabled(isEnable);    
		mntmpopCancel.setEnabled(isEnable);
		repaint();
	}
	
	/**
	 * 粘贴菜单项是否可编辑
	 */
	public void setItemEnable_Paste(boolean isEnable){
		mntmPaste.setEnabled(isEnable);     
		mntmpopPaste.setEnabled(isEnable); 
		repaint();
	}
	
	/**
	 * 状态栏是否选中
	 * @return
	 */
	public boolean isSelecteStatus(){
		if(chckbxmntmStatus.isSelected())
			return true ;
		else
			return false;	
	}
}

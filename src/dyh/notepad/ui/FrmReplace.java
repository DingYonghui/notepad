package dyh.notepad.ui;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

import dyh.notepad.control.FrmReplaceControl;

/**
 * 替换界面类：
 * @author ding
 *
 */
public class FrmReplace extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4839867346027315935L;

	private final JPanel contentPanel = new JPanel();
	
	public JTextField textSearch;           //查找
	public JTextField textReplace;          //替换
	
	private JCheckBox chckbxMatchCase ;     //区分大小写
	private JButton btnSearchNext ;         //查找下一个
	private JButton btnReplace;             //替换
	private JButton btnReplaceAll ;         //全部替换
	private JButton btnCancel ;             //取消
	
	/**
	 * Create the dialog.
	 */
	public FrmReplace() {
		
		//实例化监听者
        FrmReplaceControl frmReplaceControl = new FrmReplaceControl(this) ;
		
		setTitle("\u66FF\u6362");
		setBounds(400, 400, 469, 253);
		setResizable(false);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblFind = new JLabel("\u67E5\u627E\u5185\u5BB9(N):");
		lblFind.setBounds(10, 10, 72, 28);
		contentPanel.add(lblFind);
		
		textSearch = new JTextField();
		textSearch.getDocument().addDocumentListener(frmReplaceControl);
		textSearch.setBounds(92, 11, 197, 28);	
		contentPanel.add(textSearch);
		textSearch.setColumns(10);
		
		JLabel lblReplace = new JLabel("\u66FF\u6362(P):");
		lblReplace.setBounds(10, 48, 72, 28);
		contentPanel.add(lblReplace);
		
		textReplace = new JTextField();
		textReplace.setBounds(92, 47, 197, 31);
		contentPanel.add(textReplace);
		textReplace.setColumns(10);
		
		chckbxMatchCase = new JCheckBox("\u533A\u5206\u5927\u5C0F\u5199(C)");
		chckbxMatchCase.setBounds(10, 133, 115, 36);
		chckbxMatchCase.setSelected(false);
		chckbxMatchCase.addActionListener(frmReplaceControl);
		contentPanel.add(chckbxMatchCase);
		
		btnSearchNext = new JButton("\u67E5\u627E\u4E0B\u4E00\u4E2A(F)");
		btnSearchNext.setEnabled(false);
		btnSearchNext.setBounds(299, 10, 111, 36);
		btnSearchNext.addActionListener(frmReplaceControl);
		contentPanel.add(btnSearchNext);
		
		btnReplace = new JButton("\u66FF\u6362(R)");
		btnReplace.setEnabled(false);
		btnReplace.setBounds(299, 56, 111, 36);
		btnReplace.addActionListener(frmReplaceControl);
		contentPanel.add(btnReplace);
		
		btnReplaceAll = new JButton("\u5168\u90E8\u66FF\u6362(A)");
		btnReplaceAll.setEnabled(false);
		btnReplaceAll.setBounds(299, 102, 111, 36);
		btnReplaceAll.addActionListener(frmReplaceControl);
		contentPanel.add(btnReplaceAll);
		
		btnCancel = new JButton("\u53D6\u6D88");
		btnCancel.setBounds(299, 148, 115, 36);
		btnCancel.addActionListener(frmReplaceControl);
		contentPanel.add(btnCancel);
	}
	
	/**
	 * 区分大小写是否被选中
	 * @return
	 */
	public boolean isMatchCase() {
		boolean isMatchCase = false ;
		if(chckbxMatchCase.isSelected())
			isMatchCase = true ;
		return isMatchCase; 
	}
	
	/**
	 * 设置查找下一个、替换、替换全部按钮是否可编辑
	 * @param isEnable
	 */
	public void setButtonEnable_SearchNext_Replace_ReplaceAll(boolean isEnable){
	    btnSearchNext.setEnabled(isEnable);
		btnReplace.setEnabled(isEnable);
		btnReplaceAll.setEnabled(isEnable);
	}
}

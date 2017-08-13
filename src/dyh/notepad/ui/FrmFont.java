package dyh.notepad.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;

import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import dyh.notepad.control.FrmFontControl;

/**
 * 字体设置界面类： 字体、字形、大小
 * 
 * @author ding
 * 
 */
public class FrmFont extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7918105174909787293L;

	private final JPanel contentPanel = new JPanel();

	private JTextField txtFontName; // 字体名字文本框
	private JTextField txtFontStyle; // 字形文本框
	private JTextField txtFontSize; // 字体大小框
	
	private JLabel lblExample; // 示例标签
	private JButton btnOK; // 确定
	private JButton btnCancel; // 取消
	
	private String[] Names; // 字体名字 字符串数组，用于保存系统的字体名字
	private String[] Styles; // 字形 字符串数组
	private String[] Sizes; // 字体大小 字符串数组
	private JList<String> listFontName; // 字体名字列表
	private JList<String> listFontStyle; // 字形列表
	private JList<String> listFontSize; // 字体大小列表

	/**
	 * Create the dialog.
	 */
	public FrmFont() {// 创建字体对话框

		//监听者
		FrmFontControl frmFontControl = new FrmFontControl(this);

		setBounds(100, 100, 448, 460);
		// 不可改变大小
		setResizable(false);
		// 未关闭时其它窗口不可用
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);

		// 字体标签
		JLabel lblFont = new JLabel("\u5B57\u4F53(F):");
		lblFont.setBounds(10, 10, 66, 21);
		contentPanel.add(lblFont);
		// 字体文本框
		txtFontName = new JTextField();
		txtFontName.setBounds(10, 33, 151, 21);
		// 获得主界面文本域字体名字 并显示在文本框中
		txtFontName.setText(FrmMain.textPane.getFont().getFontName());
		contentPanel.add(txtFontName);
		txtFontName.setColumns(10);
		/**
		 * 获取本地字体,GraphicsEnvironment 类
		 */
		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		// 获取本地字体fimaly
		Names = ge.getAvailableFontFamilyNames();
		// 字体列表
		listFontName = new JList<String>(Names);
		listFontName.setBounds(10, 55, 107, 69);
		int seclectedFontNameIndex = 0 ;
		for(int i =0;i<Names.length;i++){
			if(FrmMain.textPane.getFont().getFontName().equals(Names[i]))
				seclectedFontNameIndex = i ;
		}
		listFontName.setSelectedIndex(seclectedFontNameIndex);
		listFontName.addListSelectionListener(frmFontControl);
		contentPanel.add(listFontName);
		// 字体文本框的滚动窗格
		JScrollPane scrollPaneFont = new JScrollPane(listFontName);
		scrollPaneFont.setBounds(10, 56, 151, 138);
		contentPanel.add(scrollPaneFont);

		// 字形标签
		JLabel lblGlyph = new JLabel("\u5B57\u5F62(Y):");
		lblGlyph.setBounds(187, 10, 60, 30);
		contentPanel.add(lblGlyph);
		// 字形文本框
		txtFontStyle = new JTextField();
		txtFontStyle.setBounds(187, 33, 151, 21);
		// 获得文本域字体的字形并赋给str
		String str = null;
		if (FrmMain.textPane.getFont().getStyle() == Font.PLAIN)
			str = "常规";
		else if (FrmMain.textPane.getFont().getStyle() == Font.BOLD)
			str = "粗体";
		else if (FrmMain.textPane.getFont().getStyle() == Font.ITALIC)
			str = "倾斜";
		else if (FrmMain.textPane.getFont().getStyle() == Font.BOLD
				+ Font.ITALIC)
			str = "粗偏斜体";
		txtFontStyle.setText(str);
		contentPanel.add(txtFontStyle);
		txtFontStyle.setColumns(10);
		// 字形列表
		listFontStyle = new JList();
		Styles = new String[] { "\u5E38\u89C4", "\u503E\u659C", "\u7C97\u4F53",
				"\u7C97\u504F\u659C\u4F53" };
		listFontStyle.setModel(new AbstractListModel() {

			public int getSize() {
				return Styles.length;
			}

			public Object getElementAt(int index) {
				return Styles[index];
			}
		});
		listFontStyle.setBounds(0, 0, 1, 1);
		int seclectedStyleIndex = 0 ;
		for(int i =0;i<Styles.length;i++){
			
			if(FrmMain.textPane.getFont().getStyle()==transformStyle_str_to_int(Styles[i]))
				seclectedStyleIndex = i ;
		}
		listFontStyle.setSelectedIndex(seclectedStyleIndex);
		listFontStyle.addListSelectionListener(frmFontControl);
		contentPanel.add(listFontStyle);

		// 字形滚动窗格
		JScrollPane scrollPaneStyle = new JScrollPane(listFontStyle);
		scrollPaneStyle.setBounds(187, 56, 151, 138);
		contentPanel.add(scrollPaneStyle);

		// 字体大小标签
		JLabel lblSize = new JLabel("\u5927\u5C0F(S):");
		lblSize.setBounds(351, 10, 66, 21);
		contentPanel.add(lblSize);
		// 字体大小文本框
		txtFontSize = new JTextField();
		txtFontSize.setBounds(351, 33, 66, 21);
		// 获得文本域字体的大小并初始化fontSize
		txtFontSize.setText(FrmMain.textPane.getFont().getSize() + "");
		contentPanel.add(txtFontSize);
		txtFontSize.setColumns(10);
		// 字体大小列表
		listFontSize = new JList();
		Sizes = new String[] { "8", "9", "10", "11", "12", "14", "16", "18",
				"20", "22", "24", "26", "28", "36", "48", "72" };
		// 初号、小初、一号。。。。。。
		// , "\u521D\u53F7", "\u5C0F\u521D", "\u4E00\u53F7",
		// "\u5C0F\u4E00", "\u4E8C\u53F7", "\u5C0F\u4E8C",
		// "\u4E09\u53F7", "\u5C0F\u4E09", "\u56DB\u53F7",
		// "\u5C0F\u56DB", "\u4E94\u53F7", "\u5C0F\u4E94",
		// "\u516D\u53F7", "\u5C0F\u516D", "\u4E03\u53F7",
		// "\u516B\u53F7"
		listFontSize.setModel(new AbstractListModel() {

			public int getSize() {
				return Sizes.length;
			}

			public Object getElementAt(int index) {
				return Sizes[index];
			}
		});
		listFontSize.setBounds(375, 86, 1, 1);
		int seclectedSizeIndex = 0 ;
		for(int i =0;i<Sizes.length;i++){
			if(FrmMain.textPane.getFont().getSize()==Integer.parseInt(Sizes[i]))
				seclectedSizeIndex = i ;
		}
		listFontSize.setSelectedIndex(seclectedSizeIndex);
		listFontSize.addListSelectionListener(frmFontControl);
		contentPanel.add(listFontSize);

		// 字体大小滚动窗格
		JScrollPane scrollPaneSize = new JScrollPane(listFontSize);
		scrollPaneSize.setBounds(351, 56, 66, 138);
		contentPanel.add(scrollPaneSize);

		// 示例面板
		JPanel panelExample = new JPanel();
		panelExample.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "\u793A\u4F8B",
				TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		panelExample.setBounds(187, 214, 230, 86);
		contentPanel.add(panelExample);
		panelExample.setLayout(null);

		// 示例标签
		lblExample = new JLabel("       \u4E3A\u4EBA\u6C11\u670D\u52A1");
		lblExample.setFont(new Font("宋体", Font.PLAIN, 14));
		lblExample.setSize(230, 86);
		panelExample.add(lblExample);

		// 按钮面板
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 391, 434, 30);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);

		// 确定按钮
		btnOK = new JButton("\u786E\u5B9A");
		btnOK.setBounds(237, 5, 83, 23);
		btnOK.addActionListener(frmFontControl);
		panel_1.add(btnOK);

		// 取消按钮
		btnCancel = new JButton("\u53D6\u6D88");
		btnCancel.setBounds(330, 5, 83, 23);
		btnCancel.addActionListener(frmFontControl);
		panel_1.add(btnCancel);

		// 脚本标签
		JLabel label_1 = new JLabel("\u811A\u672C:");
		label_1.setBounds(187, 301, 54, 15);
		contentPanel.add(label_1);

		// 脚本组合框
		JComboBox comboBoxJiaoBen = new JComboBox();
		comboBoxJiaoBen.setModel(new DefaultComboBoxModel(
				new String[] { "\u4E2D\u6587 GB2312" }));
		comboBoxJiaoBen.setBounds(187, 318, 230, 21);
		contentPanel.add(comboBoxJiaoBen);

		initList();

	}
	
	/**
	 * 初始化字体、字形、大小列表
	 */
	private void initList() {
			
		int selectedNameIndex = 0; // 被选中的字体名字索引
		int selectedStyleIndex = 0; // 被选中的字形索引
		int selectedSizeIndex = 0; // 被选中的字体大小索引

		for (int i = 0; i < Names.length; i++) {
			if (FrmMain.textPane.getFont().getName().equals(Names[i])) {
				selectedNameIndex = i;
			}
		}

		int stytle = 0;
		for (int j = 0; j < Styles.length; j++) {

			if (Styles[j].equals("常规"))
				stytle = Font.PLAIN;
			else if (Styles[j].equals("粗体"))
				stytle = Font.BOLD;
			else if (Styles[j].equals("倾斜"))
				stytle = Font.ITALIC;
			else if (Styles[j].equals("粗偏斜体"))
				stytle = (Font.BOLD + Font.ITALIC);

			if (FrmMain.textPane.getFont().getStyle() == stytle) {
				selectedStyleIndex = j;
			}
		}

		for (int k = 0; k < Sizes.length; k++) {
			if (FrmMain.textPane.getFont().getSize() == Integer
					.parseInt(Sizes[k])) {
				selectedSizeIndex = k;
			}
		}
		listFontName.setSelectedIndex(selectedNameIndex);
		listFontStyle.setSelectedIndex(selectedStyleIndex);
		listFontSize.setSelectedIndex(selectedSizeIndex);
	}

	/**
	 * 得到被选中的字体名字
	 * @return
	 */
	public String getSelectedFontName(){
		
		String selectedFontName = null ;
		//获得被选中的字体名字
		selectedFontName = Names[listFontName.getSelectedIndex()];
		//字体文本框重置
		txtFontName.setText(selectedFontName);
		txtFontName.requestFocus();
		
		return selectedFontName;
		
	}
	
	/**
	 * 得到被选中的字形
	 * @return
	 */
	public int getSelectedFontStyle(){
		int selectedFontStyle = 0 ;
		String temp = null ;	
		temp =Styles[listFontStyle.getSelectedIndex()];
		selectedFontStyle = transformStyle_str_to_int(temp);
		//字形文本框重置
		txtFontStyle.setText(Styles[listFontStyle.getSelectedIndex()]);
		txtFontStyle.requestFocus();
		return selectedFontStyle;
	}
	
	/**
	 * 得到被选中的字体大小
	 * @return
	 */
	public int getSelectedFontSize(){
		String selectedFontSize = null ;	
		selectedFontSize = Sizes[listFontSize.getSelectedIndex()];
		txtFontSize.setText(selectedFontSize);
		return Integer.parseInt(selectedFontSize);
	}
	
	/**
	 * 更新示例标签的文字样式
	 * @param fontName
	 * @param fontStyle
	 * @param fontSize
	 */
	public void updatelblExample(String fontName,int fontStyle,
			int fontSize) {
		lblExample.setFont(new Font(fontName,fontStyle,fontSize));
		repaint();
	}
	
	//将字符串形式的字形名字转换为 其代表的 数值
	public int transformStyle_str_to_int(String Style) {
		
		if (Style.equals("常规"))
			return Font.PLAIN;
		else if (Style.equals("粗体"))
			return Font.BOLD;
		else if (Style.equals("倾斜"))
			return Font.ITALIC;
		else
			return (Font.BOLD + Font.ITALIC);
		
	}
	
}

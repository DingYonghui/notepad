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
 * �������ý����ࣺ ���塢���Ρ���С
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

	private JTextField txtFontName; // ���������ı���
	private JTextField txtFontStyle; // �����ı���
	private JTextField txtFontSize; // �����С��
	
	private JLabel lblExample; // ʾ����ǩ
	private JButton btnOK; // ȷ��
	private JButton btnCancel; // ȡ��
	
	private String[] Names; // �������� �ַ������飬���ڱ���ϵͳ����������
	private String[] Styles; // ���� �ַ�������
	private String[] Sizes; // �����С �ַ�������
	private JList<String> listFontName; // ���������б�
	private JList<String> listFontStyle; // �����б�
	private JList<String> listFontSize; // �����С�б�

	/**
	 * Create the dialog.
	 */
	public FrmFont() {// ��������Ի���

		//������
		FrmFontControl frmFontControl = new FrmFontControl(this);

		setBounds(100, 100, 448, 460);
		// ���ɸı��С
		setResizable(false);
		// δ�ر�ʱ�������ڲ�����
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);

		// �����ǩ
		JLabel lblFont = new JLabel("\u5B57\u4F53(F):");
		lblFont.setBounds(10, 10, 66, 21);
		contentPanel.add(lblFont);
		// �����ı���
		txtFontName = new JTextField();
		txtFontName.setBounds(10, 33, 151, 21);
		// ����������ı����������� ����ʾ���ı�����
		txtFontName.setText(FrmMain.textPane.getFont().getFontName());
		contentPanel.add(txtFontName);
		txtFontName.setColumns(10);
		/**
		 * ��ȡ��������,GraphicsEnvironment ��
		 */
		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		// ��ȡ��������fimaly
		Names = ge.getAvailableFontFamilyNames();
		// �����б�
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
		// �����ı���Ĺ�������
		JScrollPane scrollPaneFont = new JScrollPane(listFontName);
		scrollPaneFont.setBounds(10, 56, 151, 138);
		contentPanel.add(scrollPaneFont);

		// ���α�ǩ
		JLabel lblGlyph = new JLabel("\u5B57\u5F62(Y):");
		lblGlyph.setBounds(187, 10, 60, 30);
		contentPanel.add(lblGlyph);
		// �����ı���
		txtFontStyle = new JTextField();
		txtFontStyle.setBounds(187, 33, 151, 21);
		// ����ı�����������β�����str
		String str = null;
		if (FrmMain.textPane.getFont().getStyle() == Font.PLAIN)
			str = "����";
		else if (FrmMain.textPane.getFont().getStyle() == Font.BOLD)
			str = "����";
		else if (FrmMain.textPane.getFont().getStyle() == Font.ITALIC)
			str = "��б";
		else if (FrmMain.textPane.getFont().getStyle() == Font.BOLD
				+ Font.ITALIC)
			str = "��ƫб��";
		txtFontStyle.setText(str);
		contentPanel.add(txtFontStyle);
		txtFontStyle.setColumns(10);
		// �����б�
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

		// ���ι�������
		JScrollPane scrollPaneStyle = new JScrollPane(listFontStyle);
		scrollPaneStyle.setBounds(187, 56, 151, 138);
		contentPanel.add(scrollPaneStyle);

		// �����С��ǩ
		JLabel lblSize = new JLabel("\u5927\u5C0F(S):");
		lblSize.setBounds(351, 10, 66, 21);
		contentPanel.add(lblSize);
		// �����С�ı���
		txtFontSize = new JTextField();
		txtFontSize.setBounds(351, 33, 66, 21);
		// ����ı�������Ĵ�С����ʼ��fontSize
		txtFontSize.setText(FrmMain.textPane.getFont().getSize() + "");
		contentPanel.add(txtFontSize);
		txtFontSize.setColumns(10);
		// �����С�б�
		listFontSize = new JList();
		Sizes = new String[] { "8", "9", "10", "11", "12", "14", "16", "18",
				"20", "22", "24", "26", "28", "36", "48", "72" };
		// ���š�С����һ�š�����������
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

		// �����С��������
		JScrollPane scrollPaneSize = new JScrollPane(listFontSize);
		scrollPaneSize.setBounds(351, 56, 66, 138);
		contentPanel.add(scrollPaneSize);

		// ʾ�����
		JPanel panelExample = new JPanel();
		panelExample.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "\u793A\u4F8B",
				TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		panelExample.setBounds(187, 214, 230, 86);
		contentPanel.add(panelExample);
		panelExample.setLayout(null);

		// ʾ����ǩ
		lblExample = new JLabel("       \u4E3A\u4EBA\u6C11\u670D\u52A1");
		lblExample.setFont(new Font("����", Font.PLAIN, 14));
		lblExample.setSize(230, 86);
		panelExample.add(lblExample);

		// ��ť���
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 391, 434, 30);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);

		// ȷ����ť
		btnOK = new JButton("\u786E\u5B9A");
		btnOK.setBounds(237, 5, 83, 23);
		btnOK.addActionListener(frmFontControl);
		panel_1.add(btnOK);

		// ȡ����ť
		btnCancel = new JButton("\u53D6\u6D88");
		btnCancel.setBounds(330, 5, 83, 23);
		btnCancel.addActionListener(frmFontControl);
		panel_1.add(btnCancel);

		// �ű���ǩ
		JLabel label_1 = new JLabel("\u811A\u672C:");
		label_1.setBounds(187, 301, 54, 15);
		contentPanel.add(label_1);

		// �ű���Ͽ�
		JComboBox comboBoxJiaoBen = new JComboBox();
		comboBoxJiaoBen.setModel(new DefaultComboBoxModel(
				new String[] { "\u4E2D\u6587 GB2312" }));
		comboBoxJiaoBen.setBounds(187, 318, 230, 21);
		contentPanel.add(comboBoxJiaoBen);

		initList();

	}
	
	/**
	 * ��ʼ�����塢���Ρ���С�б�
	 */
	private void initList() {
			
		int selectedNameIndex = 0; // ��ѡ�е�������������
		int selectedStyleIndex = 0; // ��ѡ�е���������
		int selectedSizeIndex = 0; // ��ѡ�е������С����

		for (int i = 0; i < Names.length; i++) {
			if (FrmMain.textPane.getFont().getName().equals(Names[i])) {
				selectedNameIndex = i;
			}
		}

		int stytle = 0;
		for (int j = 0; j < Styles.length; j++) {

			if (Styles[j].equals("����"))
				stytle = Font.PLAIN;
			else if (Styles[j].equals("����"))
				stytle = Font.BOLD;
			else if (Styles[j].equals("��б"))
				stytle = Font.ITALIC;
			else if (Styles[j].equals("��ƫб��"))
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
	 * �õ���ѡ�е���������
	 * @return
	 */
	public String getSelectedFontName(){
		
		String selectedFontName = null ;
		//��ñ�ѡ�е���������
		selectedFontName = Names[listFontName.getSelectedIndex()];
		//�����ı�������
		txtFontName.setText(selectedFontName);
		txtFontName.requestFocus();
		
		return selectedFontName;
		
	}
	
	/**
	 * �õ���ѡ�е�����
	 * @return
	 */
	public int getSelectedFontStyle(){
		int selectedFontStyle = 0 ;
		String temp = null ;	
		temp =Styles[listFontStyle.getSelectedIndex()];
		selectedFontStyle = transformStyle_str_to_int(temp);
		//�����ı�������
		txtFontStyle.setText(Styles[listFontStyle.getSelectedIndex()]);
		txtFontStyle.requestFocus();
		return selectedFontStyle;
	}
	
	/**
	 * �õ���ѡ�е������С
	 * @return
	 */
	public int getSelectedFontSize(){
		String selectedFontSize = null ;	
		selectedFontSize = Sizes[listFontSize.getSelectedIndex()];
		txtFontSize.setText(selectedFontSize);
		return Integer.parseInt(selectedFontSize);
	}
	
	/**
	 * ����ʾ����ǩ��������ʽ
	 * @param fontName
	 * @param fontStyle
	 * @param fontSize
	 */
	public void updatelblExample(String fontName,int fontStyle,
			int fontSize) {
		lblExample.setFont(new Font(fontName,fontStyle,fontSize));
		repaint();
	}
	
	//���ַ�����ʽ����������ת��Ϊ ������ ��ֵ
	public int transformStyle_str_to_int(String Style) {
		
		if (Style.equals("����"))
			return Font.PLAIN;
		else if (Style.equals("����"))
			return Font.BOLD;
		else if (Style.equals("��б"))
			return Font.ITALIC;
		else
			return (Font.BOLD + Font.ITALIC);
		
	}
	
}

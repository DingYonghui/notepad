package dyh.notepad.ui;

import java.awt.BorderLayout;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import dyh.notepad.control.FrmSearchControl;

/**
 * ���ҽ����ࣺ
 * @author ding
 *
 */
public class FrmSearch extends JDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5549677896287855128L;

	private final JPanel contentPanel = new JPanel();	
	private final ButtonGroup bgDirection = new ButtonGroup();
	
	public JTextField textSearch;         //�����ı���
	public JButton btnSearchNextOne ;             //������һ��
	private JCheckBox checkBoxMatchCase ;    //���ִ�Сд
	private JRadioButton rdbtnUp ;               //����
	private JRadioButton rdbtnDown ;             //����
	private JButton btnCancel ;                  //ȡ��

	
	/**
	 * Create the dialog.
	 * @param notepadUI 
	 */
	public FrmSearch() {
		
		FrmSearchControl frmSearchControl = new FrmSearchControl(this);
						
		setTitle("\u67E5\u627E");
		setBounds(400, 300, 550, 193);
		setResizable(false);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblFind = new JLabel("\u67E5\u627E\u5185\u5BB9\uFF08N\uFF09\uFF1A");
		lblFind.setBounds(10, 28, 127, 28);
		contentPanel.add(lblFind);
		
		textSearch = new JTextField();
		textSearch.setBounds(135, 27, 273, 31);
		//textSearch.setFont(new Font("����",Font.LAYOUT_LEFT_TO_RIGHT,20));
		textSearch.getDocument().addDocumentListener(frmSearchControl);
		contentPanel.add(textSearch);
		textSearch.setColumns(10);

		//������һ��
		btnSearchNextOne = new JButton("\u67E5\u627E\u4E0B\u4E00\u4E2A");
		btnSearchNextOne.setBounds(418, 28, 112, 28);
		btnSearchNextOne.setEnabled(false);
		btnSearchNextOne.addActionListener(frmSearchControl);
		contentPanel.add(btnSearchNextOne);
		
		//���ִ�Сд		
		checkBoxMatchCase = new JCheckBox("\u533A\u5206\u5927\u5C0F\u5199\uFF08C\uFF09");
		checkBoxMatchCase.setBounds(10, 98, 127, 44);
		checkBoxMatchCase.addActionListener(frmSearchControl);
		contentPanel.add(checkBoxMatchCase);
		
		JPanel panelDirection = new JPanel();
		panelDirection.setBorder(new TitledBorder(null, "\u65B9\u5411", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelDirection.setBounds(168, 73, 240, 69);
		contentPanel.add(panelDirection);
		panelDirection.setLayout(null);
		
		//����
		rdbtnUp = new JRadioButton("����(U)");
		bgDirection.add(rdbtnUp);
		rdbtnUp.setBounds(6, 26, 113, 23);		
		rdbtnUp.addActionListener(frmSearchControl);
		panelDirection.add(rdbtnUp);
		
		//���� 
		rdbtnDown = new JRadioButton("����(D)");
		bgDirection.add(rdbtnDown);
		rdbtnDown.setBounds(121, 26, 113, 23);
		rdbtnDown.setSelected(true);
		rdbtnDown.addActionListener(frmSearchControl);
		panelDirection.add(rdbtnDown);
		
		//ȡ��
		btnCancel = new JButton("\u53D6\u6D88");
		btnCancel.setBounds(418, 66, 112, 28);
		btnCancel.addActionListener(frmSearchControl);
		contentPanel.add(btnCancel);
		
	}

	/**
	 * ���²��ҡ����ϲ���    ����   ��һ��ѡ��
	 * @return
	 */
	public boolean isSearchByDown(){
		/**
		 * ʵ�� Enumeration �ӿڵĶ���
		 * ������һϵ��Ԫ�أ�һ������һ����
		 * �������� nextElement ����������һϵ�е�����Ԫ�ء� 		
		 */
		Enumeration<AbstractButton> et = bgDirection.getElements();
		JRadioButton rd;
		
		while(true){
			
			rd = (JRadioButton)et.nextElement();	
			
			if( rd.isSelected() == true){
				
				if(rd.getActionCommand().equals("����(D)"))
					return true;
				else
					return false;				
			}
			

		}
			
	}

	
	/**
	 * ���ִ�Сд�Ƿ�ѡ��
	 * @return
	 */
	public boolean isMatchCase() {
		
		if(checkBoxMatchCase.isSelected())
			return true ;
		else
			return false ; 
	} 
	
}

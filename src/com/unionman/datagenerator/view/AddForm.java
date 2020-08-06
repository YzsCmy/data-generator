package com.unionman.datagenerator.view;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.unionman.datagenerator.dao.DetailDao;
import com.unionman.datagenerator.dao.RecordDao;
import com.unionman.datagenerator.entity.Detail;
import com.unionman.datagenerator.entity.Record;
import com.unionman.datagenerator.utils.Dbutils;
import com.unionman.datagenerator.utils.StringUtils;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import java.awt.CardLayout;

public class AddForm extends JFrame {

	private JPanel contentPane;
	private JTextField snPreTxt;
	private JTextField macPreTxt;
	private JLabel lblSn_3;
	private JTextField snStartTxt;
	private JLabel lblMac_3;
	private JTextField macStartTxt;
	private JLabel lblSn_2;
	private JTextField typeTxt;
	private JLabel lblMac_2;
	private JTextField numTxt;
	private JButton generateBtn;
	private JButton saveBtn;
	
	private boolean isSave = false;
	private HashMap<String, String> map = new HashMap<>();
	private List<Detail> details = new ArrayList<>();
	private Record  record = new Record();
	
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private RecordDao recordDao = new RecordDao();
	private DetailDao detailDao = new DetailDao();
	private JTable detailTab;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
//		Integer i = ;
//		i=i+0x1;
		String hex = "aa";
		String format = String.format("%5s", hex).replace(" ", "0");
		int i = Integer.parseInt(hex, 16);
//		String hex = i.toHexString(i);
		System.out.println(i);
		System.out.println(format);
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AddForm frame = new AddForm(new MainForm());
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
	}

	/**
	 * Create the frame.
	 */
	public AddForm(final MainForm form) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				form.setEnabled(true);
				form.refreshMain();
			}
		});
		setTitle("AddForm");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 985, 404);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(SystemColor.textHighlightText));
		
		JLabel lblSn = new JLabel("sn前缀：");
		lblSn.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		JLabel lblMac = new JLabel("mac前缀：");
		lblMac.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		snPreTxt = new JTextField();
		snPreTxt.setColumns(10);
		
		macPreTxt = new JTextField();
		macPreTxt.setColumns(10);
		
		lblSn_3 = new JLabel("sn开始流水号：");
		lblSn_3.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		snStartTxt = new JTextField();
		snStartTxt.setColumns(10);
		
		lblMac_3 = new JLabel("mac开始流水号：");
		lblMac_3.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		macStartTxt = new JTextField();
		macStartTxt.setColumns(10);
		
		lblSn_2 = new JLabel("机型信息：");
		lblSn_2.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		typeTxt = new JTextField();
		typeTxt.setColumns(10);
		
		lblMac_2 = new JLabel("生成数量：");
		lblMac_2.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		numTxt = new JTextField();
		numTxt.setColumns(10);
		
		generateBtn = new JButton("生成");
		generateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generate();
			}
		});
		
		saveBtn = new JButton("保存");
		saveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblMac)
							.addGap(3)
							.addComponent(macPreTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblSn)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(snPreTxt)))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblMac_3)
						.addComponent(lblSn_3))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(snStartTxt)
						.addComponent(macStartTxt, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblSn_2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(typeTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblMac_2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(numTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(37)
					.addComponent(generateBtn, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
					.addGap(37)
					.addComponent(saveBtn, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
					.addGap(177))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(22)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(typeTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_panel.createSequentialGroup()
											.addGap(2)
											.addComponent(lblSn_2)))
									.addGap(27)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup()
											.addGap(2)
											.addComponent(lblMac_2))
										.addComponent(numTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup()
											.addGap(2)
											.addComponent(lblSn_3))
										.addComponent(snStartTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup()
											.addGap(29)
											.addComponent(lblMac_3))
										.addGroup(gl_panel.createSequentialGroup()
											.addGap(27)
											.addComponent(macStartTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblSn)
										.addComponent(snPreTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGap(27)
									.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblMac)
										.addComponent(macPreTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(saveBtn, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
								.addComponent(generateBtn, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(40, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 975, Short.MAX_VALUE)
					.addGap(6))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 957, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE))
		);
		
		detailTab = new JTable();
		detailTab.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"type", "sn", "mac", "sn\u524D\u7F00", "mac\u524D\u7F00", "sn\u6D41\u6C34\u53F7", "mac\u6D41\u6C34\u53F7", "\u751F\u6210\u65F6\u95F4"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(detailTab);
		contentPane.setLayout(gl_contentPane);
	}
	
	private void generate() {
		//输入框内容有改变，则生成
		if(isChange()) {
			//是否已保存
			details.clear();
			isSave = false;
			String snPre = snPreTxt.getText();
			String macPre = macPreTxt.getText();
			String snStart = snStartTxt.getText();
			String macStart = macStartTxt.getText();
			String type = typeTxt.getText();
			if(StringUtils.isEmpty(numTxt.getText())) {
				JOptionPane.showMessageDialog(null, "配置不能为空！");
				return;
			}
			int num = Integer.parseInt(numTxt.getText());
			//判断输入
			if(StringUtils.isEmpty(snPre)) {
				JOptionPane.showMessageDialog(null, "配置不能为空！");
				return;
			}
			if(StringUtils.isEmpty(macPre)) {
				JOptionPane.showMessageDialog(null, "配置不能为空！");
				return;
			}
			if(StringUtils.isEmpty(snStart)) {
				JOptionPane.showMessageDialog(null, "配置不能为空！");
				return;
			}
			if(StringUtils.isEmpty(macStart)) {
				JOptionPane.showMessageDialog(null, "配置不能为空！");
				return;
			}
			if(StringUtils.isEmpty(type)) {
				JOptionPane.showMessageDialog(null, "配置不能为空！");
				return;
			}
			
			map.put("snPre", snPre);
			map.put("macPre", macPre);
			map.put("snStart", snStart);
			map.put("macStart", macStart);
			map.put("type", type);
			map.put("num", num+"");
			//封装主记录
			record.setCreate_time(new Date().getTime());
			record.setMac_prefix(macPre);
			record.setMac_start(macStart);
			record.setNum(num);
			record.setSn_prefix(snPre);
			record.setSn_start(snStart);
			record.setType(type);
			
			//dtm
			DefaultTableModel dtm=(DefaultTableModel) detailTab.getModel();
			dtm.setRowCount(0);
			for(int i=0;i<num;i++) {
				Detail detail = new Detail();
				String snSerial = String.format("%05d", Integer.parseInt(snStart)+i);
				String sn = snPre+ snSerial;
				String hex = Integer.toHexString((Integer.parseInt(macStart, 16)+i));
				String macSerial = String.format("%4s", hex).replace(" ", "0");
				String mac = macPre+ macSerial;
				//渲染表格
				Vector<String> v=new Vector<String>();
				v.add(type);
				v.add(sn);
				v.add(mac);
				v.add(snPre);
				v.add(macPre);
				v.add(snSerial);
				v.add(macSerial);
				v.add(format.format(new Date().getTime()));
				dtm.addRow(v);
				
				//封装detail
				detail.setSn(sn);
				detail.setMac(mac);
				detail.setType(type);
				detail.setMac_prefix(macPre);
				detail.setSn_prefix(snPre);
				detail.setSn_serial(snSerial);
				detail.setMac_serial(macSerial);
				detail.setCreate_time(new Date().getTime());
//				detail.setRecordId(recordId);
				details.add(detail);
			}
		}
		
	}
	private void save() {
		if(isSave) {
			JOptionPane.showMessageDialog(null, "保存成功！");
			return;
		}
		if(details.isEmpty()) {
			return ;
		}
		
		Connection con=Dbutils.getCon();//获取数据库连接
		
		try {
			//保存主记录
			int recordId = recordDao.save(con, record);
			//保存明细记录
			for(Detail detail:details) {
				detail.setRecord_id(recordId);
				detailDao.save(con, detail);
			}
			JOptionPane.showMessageDialog(null, "保存成功！");
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "保存失败！");
			throw new RuntimeException(e.getMessage());
		}finally {
			try {
				Dbutils.closeCon(con);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		isSave = true;
	}
	
	
	private boolean isChange() {
		String snPre = snPreTxt.getText();
		String macPre = macPreTxt.getText();
		String snStart = snStartTxt.getText();
		String macStart = macStartTxt.getText();
		String type = typeTxt.getText();
		String num = numTxt.getText();
		if(map.isEmpty()) {
			return true;
		}
		if(!map.get("snPre").equals(snPre)) {
			return true;
		}
		if(!map.get("macPre").equals(macPre)) {
			return true;
		}
		if(!map.get("snStart").equals(snStart)) {
			return true;
		}
		if(!map.get("macStart").equals(macStart)) {
			return true;
		}
		if(!map.get("type").equals(type)) {
			return true;
		}
		if(!map.get("num").equals(num)) {
			return true;
		}
		return false;
	}
}

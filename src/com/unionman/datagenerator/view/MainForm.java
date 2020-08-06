package com.unionman.datagenerator.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.FlowLayout;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dialog.ModalExclusionType;

import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.unionman.datagenerator.dao.DetailDao;
import com.unionman.datagenerator.dao.RecordDao;
import com.unionman.datagenerator.entity.Detail;
import com.unionman.datagenerator.entity.Record;
import com.unionman.datagenerator.utils.Dbutils;

import java.awt.Panel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.JScrollPane;
import java.awt.SystemColor;

public class MainForm extends JFrame {

	private JPanel contentPane;
	private JTable mainTab;
	private JTable detailTab;

	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private RecordDao recordDao = new RecordDao();
	private DetailDao detailDao = new DetailDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainForm frame = new MainForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainForm() {
		setTitle("MainForm");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 666, 404);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		final MainForm me = this;
		JButton addBtn = new JButton("新增");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 设置禁用
				me.setEnabled(false);
				me.setModalExclusionType(ModalExclusionType.NO_EXCLUDE);
				AddForm addForm = new AddForm(me);
				addForm.setVisible(true);

			}
		});

		JButton button = new JButton("删除");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});

		JButton btnexcel = new JButton("导出明细到excel");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(67).addComponent(addBtn).addGap(48)
						.addComponent(button).addGap(49).addComponent(btnexcel).addGap(240)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(button)
								.addComponent(addBtn).addComponent(btnexcel))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);
		btnexcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					export();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					throw new RuntimeException(e1.getMessage());
				}
			}
		});

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(230, 230, 230)), "\u4E3B\u8BB0\u5F55",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new LineBorder(new Color(230, 230, 230)), "\u660E\u7EC6\u8BB0\u5F55",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE)
				.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE)
				.addComponent(panel_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 122, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)));

		JScrollPane scrollPane_1 = new JScrollPane();

		detailTab = new JTable();
		detailTab.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "id", "\u673A\u578B", "sn", "mac", "sn\u524D\u7F00", "mac\u524D\u7F00",
						"sn\u6D41\u6C34\u53F7", "mac\u6D41\u6C34\u53F7", "\u751F\u6210\u65F6\u95F4",
						"\u4E3B\u8BB0\u5F55id" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false, false,
					false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane_1.setViewportView(detailTab);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING).addComponent(scrollPane_1,
				GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE));
		gl_panel_2.setVerticalGroup(
				gl_panel_2.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_2.createSequentialGroup().addGap(5)
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 671, Short.MAX_VALUE)));
		panel_2.setLayout(gl_panel_2);

		JScrollPane scrollPane = new JScrollPane();

		mainTab = new JTable();
		mainTab.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "id", "\u673A\u578B", "sn\u524D\u7F00", "mac\u524D\u7F00",
						"sn\u5F00\u59CB\u6D41\u6C34\u53F7", "mac\u5F00\u59CB\u6D41\u6C34\u53F7",
						"\u751F\u6210\u6570\u91CF", "\u751F\u6210\u65F6\u95F4" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		mainTab.getColumnModel().getColumn(5).setPreferredWidth(101);
		mainTab.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				int row = mainTab.getSelectedRow();
				if (row != -1) {
					String recordId = (String) mainTab.getValueAt(row, 0);
					refreshDetail(recordId);
				}
			}
		});
		scrollPane.setViewportView(mainTab);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addComponent(scrollPane,
				GroupLayout.DEFAULT_SIZE, 646, Short.MAX_VALUE));
		gl_panel_1.setVerticalGroup(
				gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_1.createSequentialGroup()
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE).addContainerGap()));
		panel_1.setLayout(gl_panel_1);
		contentPane.setLayout(gl_contentPane);
		refreshMain();
	}

	public void refreshMain() {
		// 获取主记录
		Connection con = Dbutils.getCon();
		DefaultTableModel dtm = (DefaultTableModel) mainTab.getModel();
		dtm.setRowCount(0);
		List<Record> rst;
		try {
			rst = recordDao.findAll(con);
		} finally {
			try {
				Dbutils.closeCon(con);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		// 填充表格，显示数据
		for (Record u : rst) {
			Vector<String> v = new Vector<String>();
			v.add(u.getId().toString());
			v.add(u.getType());
			v.add(u.getSn_prefix());
			v.add(u.getMac_prefix());
			v.add(u.getSn_start());
			v.add(u.getMac_start());
			// 日期转换
			Long time = u.getCreate_time();
			Date date = new Date();
			System.out.println(time);
			date.setTime(time);
			String dateString = format.format(date);
			v.add(u.getNum() + "");
			v.add(dateString);
			dtm.addRow(v);
		}

	}

	public void refreshDetail(String recordId) {
		// 获取明细记录
		Connection con = Dbutils.getCon();
		DefaultTableModel dtm = (DefaultTableModel) detailTab.getModel();
		dtm.setRowCount(0);
		List<Detail> rst;
		try {
			rst = detailDao.findAllByRecordId(con, recordId);
		} finally {
			try {
				Dbutils.closeCon(con);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		// 填充表格，显示数据
		for (Detail u : rst) {
			Vector<String> v = new Vector<String>();
			v.add(u.getId().toString());
			v.add(u.getType());
			v.add(u.getSn());
			v.add(u.getMac());
			v.add(u.getSn_prefix());
			v.add(u.getMac_prefix());
			v.add(u.getSn_serial());
			v.add(u.getMac_serial());
			// 日期转换
			Long time = u.getCreate_time();
			Date date = new Date();
			date.setTime(time);
			String dateString = format.format(date);
			v.add(dateString);
			v.add(u.getRecord_id() + "");
			dtm.addRow(v);
		}

	}

	public void delete() {
		// 删除记录
		int[] nums = detailTab.getSelectedRows();// 得到选中的数据行
		if (nums.length == 0) {
			JOptionPane.showMessageDialog(null, "请选择要删除的数据！");
			return;
		}
		int confNum = JOptionPane.showConfirmDialog(null, "您确定要删除吗？");
		if (confNum == 0) {
			Connection con = Dbutils.getCon();// 获取数据库连接
			try {
				con.setAutoCommit(false);// 开启事务
				for (int num : nums) {// 遍历删除所选行
					String id = (String) detailTab.getValueAt(num, 0);
					detailDao.deleteById(con, id);// 操作数据库，执行相应sql，完成删除

				}
				con.commit();// 事务提交
			} catch (Exception e1) {
				try {
					con.rollback();// 若删除失败，回滚事务
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "删除失败！");
				throw new RuntimeException(e1);
			} finally {
				try {
					Dbutils.closeCon(con);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			JOptionPane.showMessageDialog(null, "删除成功！");
			// 重新填充表格

			int selectedRow = mainTab.getSelectedRow();
			String recordId = (String) mainTab.getValueAt(selectedRow, 0);
			// 刷新明细记录
			refreshDetail(recordId);
		}
	}

	public void export() throws Exception {
		// 导出明细到EXCEL
		String recordId = (String) mainTab.getValueAt(mainTab.getSelectedRow(), 0);
		String fileName = recordId + "_" + format.format(new Date()) + ".xls";
		File file = new File("/home/umlinux/Desktop/" + fileName);

		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet hs = wb.createSheet();
		TableModel tm = detailTab.getModel();
		int row = tm.getRowCount();
		int cloumn = tm.getColumnCount();
		HSSFCellStyle style = wb.createCellStyle();
		HSSFFont font = wb.createFont();
		font.setFontHeightInPoints((short) 11);
		style.setFont(font);
		HSSFCellStyle style1 = wb.createCellStyle();
		HSSFFont font1 = wb.createFont();
		font1.setFontHeightInPoints((short) 15);
		style1.setFont(font);

		for (int i = 0; i < row + 1; i++) {
			HSSFRow hr = hs.createRow(i);
			for (int j = 0; j < cloumn; j++) {
				if (i == 0) {
					String value = tm.getColumnName(j);
					int len = value.length();
					hs.setColumnWidth((short) j, (short) (len * 400)); // 列宽
					HSSFRichTextString srts = new HSSFRichTextString(value);
					HSSFCell hc = hr.createCell((short) j);
					hc.setCellStyle(style1);
					hc.setCellValue(srts);
				} else {
					if (tm.getValueAt(i - 1, j) != null) {
						String value = tm.getValueAt(i - 1, j).toString();
						HSSFRichTextString srts = new HSSFRichTextString(value);
						HSSFCell hc = hr.createCell((short) j);
						hc.setCellStyle(style);

						if (value.equals("") || value == null) {
							hc.setCellValue(new HSSFRichTextString(""));
						} else {
							hc.setCellValue(srts);
						}
					}
				}
			}
		}

		try {
			wb.write(file);
			
			JOptionPane.showMessageDialog(null, "导出成功！");
			System.out.println("write out to: " + file);
		} catch (IOException ex) {
			ex.printStackTrace();
		}finally {
			if (wb!=null) {
				wb.close();
			}
		}

	}

}

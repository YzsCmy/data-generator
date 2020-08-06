package com.unionman.datagenerator.test;

import java.sql.Connection;
import java.util.Date;

import org.junit.Test;

import com.unionman.datagenerator.dao.RecordDao;
import com.unionman.datagenerator.entity.Record;
import com.unionman.datagenerator.utils.Dbutils;

public class TestDao {
	
	private RecordDao recordDao = new RecordDao();
	
	@Test
	public void testId() {
		Connection con = Dbutils.getCon();
		Record record = new Record();
		record.setCreate_time(new Date().getTime());
		record.setMac_prefix("111");
		record.setMac_start("111");
		record.setNum(12);
		record.setMac_start("2w12");
		record.setSn_start("221");
		record.setType("test");
		int save = recordDao.save(con, record);
		System.out.println(save);
		Dbutils.closeCon(con);
	}
	
	public void export() {
		
	}

}

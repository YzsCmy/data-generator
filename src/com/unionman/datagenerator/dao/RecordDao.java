package com.unionman.datagenerator.dao;

import com.unionman.datagenerator.entity.Record;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class RecordDao {
	private QueryRunner query = new QueryRunner();
	

	public int save(Connection con,Record u){
		String sql = "INSERT INTO record VALUE(NULL,?,?,?,?,?,?,?)";
		Object[] params={u.getType(),u.getSnPrefix(),u.getMacPrefix(),
				u.getCreateTime(),u.getNum(),u.getSnStart(),u.getMacStart()};
		try {
			int id = (int) query.insert(con, sql, new ScalarHandler<>(),params);
			return id;
//			query.update(con, sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	public void delete(Connection con,String id){
		String sql = "delete from record where id=?";
		Object []params={Integer.parseInt(id)};
		try {
			query.update(con, sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	public List<Record> findAll(Connection con){
		String sql = "select * from record";
		try {
			return query.query(con, sql, new BeanListHandler<>(Record.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}



}

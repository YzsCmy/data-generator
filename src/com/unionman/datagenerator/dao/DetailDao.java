package com.unionman.datagenerator.dao;

import com.unionman.datagenerator.entity.Detail;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DetailDao {
	private QueryRunner query = new QueryRunner();
	

	public void save(Connection con,Detail u){
		String sql = "INSERT INTO detail VALUE(NULL,?,?,?,?,?,?,?,?,?)";
		Object[] params={u.getType(),u.getSn(),u.getMac(),u.getSnPrefix(),u.getMacPrefix(),
				u.getSnSerial(),u.getMacSerial(),u.getRecordId(),u.getCreateTime()};
		try {
			query.update(con, sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	public void deleteById(Connection con,String id){
		String sql = "delete from detail where id=?";
		Object []params={Integer.parseInt(id)};
		try {
			query.update(con, sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	public void deleteByRecordId(Connection con,String recordId){
	    String sql = "delete from detail where record_id=?";
		Object []params={Integer.parseInt(recordId)};
		try {
			query.update(con, sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	public List<Detail> findAll(Connection con){
		String sql = "select * from detail";
		try {
			return query.query(con, sql, new BeanListHandler<>(Detail.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	public List<Detail> findAllByRecordId(Connection con,String recordId){
		String sql = "select * from detail where record_id=?";
        Object []params={Integer.parseInt(recordId)};
		try {
			return query.query(con, sql, new BeanListHandler<>(Detail.class),params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}



}

package model.dao;

import model.dto.CardDTO;
import util.DBManager;

import java.sql.*;
import java.util.*;

public class CardDAO {
	
	private DBManager dbManager = new DBManager();
	private Connection conn = dbManager.getConn();
	private PreparedStatement psmt = null;
	private ResultSet rs = null;

	public void getClose() {

		try {
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public ArrayList<CardDTO> getCardsByPlayerId() {
		CardDTO dto = null;
		ArrayList<CardDTO> arr = new ArrayList<CardDTO>();

		try {

			String sql = "select * from CARD";

			psmt = conn.prepareStatement(sql);

			rs = psmt.executeQuery();


			while (rs.next()) {
				dto = new CardDTO();
				dto.setId(rs.getString("c_id"));
				dto.setName(rs.getString("c_name"));
				dto.setInfo(rs.getString("c_info"));
				dto.setType(rs.getString("c_type"));
				dto.setValue(rs.getInt("power"));
				arr.add(dto);

			}
		}  catch (SQLException e) {
			//e.printStackTrace();
			System.out.println("카드 불러오기 실패");
		} finally {
			getClose();
		}
		return arr;
		
	}
	
	public CardDTO getCard() {
		CardDTO dto = null;
		
		return dto;
	}
}

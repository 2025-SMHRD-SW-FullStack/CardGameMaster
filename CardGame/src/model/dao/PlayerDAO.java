package model.dao;

import model.dto.PlayerDTO;
import util.DBManager;

import java.sql.*;
public class PlayerDAO {
	private DBManager dbManager = new DBManager();
	private Connection conn = dbManager.getConn();
	private PreparedStatement psmt = null;
	private ResultSet rs = null;
	

	// 사용한 자원을 반납하는 메소드
		private void getClose() {

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
		// 회원가입
		public int join(PlayerDTO dto) {
			
			int result = 0;

			try {
				conn = dbManager.getConn();

				String sql = "INSERT INTO PLAYER VALUES(?,?,?,?)";

				psmt = conn.prepareStatement(sql);

				psmt.setString(1, dto.getId());
				psmt.setString(2, dto.getPw());
				psmt.setInt(3, dto.getWin());
				psmt.setInt(4, dto.getLose());

				result = psmt.executeUpdate();

			} catch (SQLIntegrityConstraintViolationException e) {
				System.out.println("이미 존재하는 아이디입니다.");
			} catch (SQLException e) {
				//e.printStackTrace();
				System.out.println("알수 없는 오류 발생");
			} finally {
				getClose();
			}

			return result;
		}
		
		// 로그인
		public PlayerDTO login(String user_id, String user_pw) {

			
			PlayerDTO dto = null;
			try {
				conn = dbManager.getConn();
				String sql = "select * from player where id=? and pw=?";

				psmt = conn.prepareStatement(sql);
				psmt.setString(1, user_id);
				psmt.setString(2, user_pw);

				rs = psmt.executeQuery();

				if (rs.next()) {

					dto = new PlayerDTO();
					dto.setId(rs.getString("id"));
					dto.setWin(rs.getInt("win"));
					dto.setLose(rs.getInt("lose"));

				}

			}  catch (SQLException e) {
				//e.printStackTrace();
				System.out.println("알수 없는 오류 발생");
			} finally {
				getClose();
			}
			return dto;

		}
		
		public void setScore(int win, int lose, String id) {
			try {
            	conn = dbManager.getConn();
                String sql = "update player set win = ?, lose = ? where id = ?"; 

                psmt = conn.prepareStatement(sql);

                psmt.setInt(1, win);
                psmt.setInt(2, lose);
                psmt.setString(3, id);

                psmt.executeUpdate();

            }  catch (SQLException e) {
                //e.printStackTrace();
            	System.out.println("알수 없는 오류 발생");
            } finally {
                getClose();
            }


        }
}
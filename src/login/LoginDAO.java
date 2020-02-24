package login;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import board.DBUtil;

public class LoginDAO {

	
//	로그인 기능
	public static MemberInfoVO login(String userID, String userPW) {

		MemberInfoVO vo = null;
		try {
			
			Connection conn = DBUtil.getMySQLConnection();
			String sql = "select * from memberINfo where userID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userID);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				vo = new MemberInfoVO();
				vo.setUserNo(rs.getInt("userNo"));
				vo.setUserID(rs.getString("userID"));
				vo.setUserPW(rs.getString("userPW"));
				vo.setNickName(rs.getString("nickName"));
				vo.setUserName(rs.getString("userName"));
				vo.setGender(rs.getBoolean("gender"));
			}

			DBUtil.close(conn);
			DBUtil.close(pstmt);
			DBUtil.close(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (vo == null) {
//			null일때 없는 ID, null 리턴
			JOptionPane.showMessageDialog(null, "등록되지 않은 ID입니다.");
			return vo;
		} else {
//			정보가 넘어왔을때 비밀번호 확인후 다를 때 null 리턴
			if (!vo.getUserPW().equals(userPW)) {
				JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.");
				vo = null;
				return vo;
//			아이디 비밀번호 일치시 vo클래스 넘겨주기
			} else {
				return vo;

			}
		}

	}

}
package quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import board.DBUtil;

public class EnteringDAO {

	public static boolean insert(QuizVO vo) {

//		문제, 정답, 오답1,2,3, 비밀번호가 입력되었나 검사
		boolean flag = true;
		if (vo.getQuiz().length() == 0) {
			flag = false;
			JOptionPane.showMessageDialog(null, "문제를 입력해주세요.");
		} else if (vo.getAnswer().length() == 0) {
			flag = false;
			JOptionPane.showMessageDialog(null, "정답을 입력해주세요.");
		} else if (vo.getAnswer().length() == 0) {
			flag = false;
			JOptionPane.showMessageDialog(null, "오답을 모두 입력해주세요.");
		} else if (vo.getwrong1().length() == 0) {
			flag = false;
			JOptionPane.showMessageDialog(null, "오답을 모두 입력해주세요.");
		} else if (vo.getwrong2().length() == 0) {
			flag = false;
			JOptionPane.showMessageDialog(null, "오답을 모두 입력해주세요.");
		} else if (vo.getwrong3().length() == 0) {
			flag = false;
			JOptionPane.showMessageDialog(null, "비밀번호를 설정해주세요.");
		}
//		모두 다 입력되었으면 저장한다.
		if (flag) {
			try {
				Connection conn = DBUtil.getMySQLConnection();
				String sql = "INSERT INTO quiz(quiz, answer, wrong1, wrong2, wrong3, quizpassword) "
						+ "VALUES(?, ?, ?, ?, ?, ?)";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, vo.getQuiz());
				pstmt.setString(2, vo.getAnswer());
				pstmt.setString(3, vo.getwrong1());
				pstmt.setString(4, vo.getwrong2());
				pstmt.setString(5, vo.getwrong3());
				pstmt.setString(6, vo.getquizPassword());
				pstmt.executeUpdate();
				JOptionPane.showMessageDialog(null, "문제가 저장되었습니다.");
				DBUtil.close(conn);
				DBUtil.close(pstmt);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	public static boolean update(QuizVO vo) {
//		문제, 정답, 오답1,2,3, 비밀번호가 입력되었나 검사
		boolean flag = true;
		if (vo.getQuiz().length() == 0) {
			flag = false;
			JOptionPane.showMessageDialog(null, "문제를 입력해주세요.");
		} else if (vo.getAnswer().length() == 0) {
			flag = false;
			JOptionPane.showMessageDialog(null, "정답을 입력해주세요.");
		} else if (vo.getAnswer().length() == 0) {
			flag = false;
			JOptionPane.showMessageDialog(null, "오답을 모두 입력해주세요.");
		} else if (vo.getwrong1().length() == 0) {
			flag = false;
			JOptionPane.showMessageDialog(null, "오답을 모두 입력해주세요.");
		} else if (vo.getwrong2().length() == 0) {
			flag = false;
			JOptionPane.showMessageDialog(null, "오답을 모두 입력해주세요.");
		} else if (vo.getwrong3().length() == 0) {
			flag = false;
			JOptionPane.showMessageDialog(null, "비밀번호를 설정해주세요.");
		}
//		모두 다 입력되었으면 저장한다.
		if (flag) {
			try {
				Connection conn = DBUtil.getMySQLConnection();
				int choice = vo.getIdx();
				String sql = "UPDATE quiz SET quiz = ?, answer = ?, wrong1 = ?, wrong2 = ?, wrong3 = ?, quizpassword =? WHERE idx = '"
						+ choice + "'";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, vo.getQuiz());
				pstmt.setString(2, vo.getAnswer());
				pstmt.setString(3, vo.getwrong1());
				pstmt.setString(4, vo.getwrong2());
				pstmt.setString(5, vo.getwrong3());
				pstmt.setString(6, vo.getquizPassword());
				pstmt.executeUpdate();
				JOptionPane.showMessageDialog(null, "수정완료!");
				DBUtil.close(conn);
				DBUtil.close(pstmt);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	public static void check(int choice, String passwordCheck) {
		try {
			Connection conn = DBUtil.getMySQLConnection();
			String check = "SELECT * FROM quiz WHERE idx = '" + choice + "'";
			PreparedStatement pstmt = conn.prepareStatement(check);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			if (rs.getString("quizpassword").equals(passwordCheck)) {

				QuizVO vo = new QuizVO();
				vo.setQuiz(rs.getString("quiz"));
				vo.setAnswer(rs.getString("answer"));
				vo.setwrong1(rs.getString("wrong1"));
				vo.setwrong2(rs.getString("wrong2"));
				vo.setwrong3(rs.getString("wrong3"));
				vo.setquizPassword(rs.getString("quizPassword"));
				vo.setIdx(rs.getInt("idx"));
				vo.setWriteDate(rs.getTimestamp("writedate"));

				EnteringMain entering1 = new EnteringMain(vo);
				entering1.getSaveButton().setText("수정");

			} else {
				JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}

	public static void cancel(int choice, String passwordCheck) {
		try {
			Connection conn = DBUtil.getMySQLConnection();
			String check = "SELECT quizpassword FROM quiz WHERE idx = '" + choice + "'";
			PreparedStatement pstmt = conn.prepareStatement(check);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			if (rs.getString("quizpassword").equals(passwordCheck)) {
				String sql = "DELETE FROM quiz WHERE idx = '" + choice + "'";
				PreparedStatement pstmt1 = conn.prepareStatement(sql);
				pstmt1.executeUpdate();
				JOptionPane.showMessageDialog(null, choice + "번 문제가 삭제되었습니다!");
			} else {
				JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}

}
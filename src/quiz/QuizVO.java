package quiz;


import java.util.Date;

// QuizVO, EnteringVO 합침

//문제 객체를 생성하는 클래스 
public class QuizVO {
	private String quiz , answer;
	private String wrong1, wrong2, wrong3;
	private String quizPassword;
	private Date writeDate;
	private int idx;

//	getters & setters	
	public String getQuiz() {
		return quiz;
	}
	public void setQuiz(String quiz) {
		this.quiz = quiz;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getwrong1() {
		return wrong1;
	}
	public void setwrong1(String wrong1) {
		this.wrong1 = wrong1;
	}
	public String getwrong2() {
		return wrong2;
	}
	public void setwrong2(String wrong2) {
		this.wrong2 = wrong2;
	}
	public String getwrong3() {
		return wrong3;
	}
	public void setwrong3(String wrong3) {
		this.wrong3 = wrong3;
	}
	public String getquizPassword() {
		return quizPassword;
	}
	public void setquizPassword(String quizPassword) {
		this.quizPassword = quizPassword;
	}
	public Date getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(Date writeData) {
		this.writeDate = writeData;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	@Override
	public String toString() {
		return "QuizVO [quiz=" + quiz + ", answer=" + answer + ", wrong1=" + wrong1 + ", wrong2=" + wrong2 + ", wrong3="
				+ wrong3 + ", quizPassword=" + quizPassword + ", writeDate=" + writeDate + ", idx=" + idx + "]";
	}

	
}

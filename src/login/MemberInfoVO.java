package login;

// MemberInfoVO , SinUPVO 합침
public class MemberInfoVO {

	private int userNo;
	private String userID;
	private String userPW;
	private String nickName;
	private String userName;
	private Boolean gender;
	private Boolean Gflag;

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUserPW() {
		return userPW;
	}

	public void setUserPW(String userPW) {
		this.userPW = userPW;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Boolean getGender() {
		return gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	public Boolean getGflag() {
		return Gflag;
	}

	public void setGflag(Boolean gflag) {
		Gflag = gflag;
	}

	@Override
	public String toString() {
		return "MemberInfoVO [userNo=" + userNo + ", userID=" + userID + ", userPW=" + userPW + ", nickName=" + nickName
				+ ", userName=" + userName + ", gender=" + gender + ", Gflag=" + Gflag + "]";
	}

}

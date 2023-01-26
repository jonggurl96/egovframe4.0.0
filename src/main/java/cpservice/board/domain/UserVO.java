package cpservice.board.domain;

/**
 * 세션에 보관될 VO 객체
 * @author jonggurl
 *
 */
public class UserVO {

	private String id;
	private String pw;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	@Override
	public String toString() {
		return "UserVO [id=" + id + ", pw=" + pw + "]";
	}
	
}

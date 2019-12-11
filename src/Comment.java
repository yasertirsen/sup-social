package sup;

public class Comment {
	
	private int commentId;
	private int postedById;
	private int profileId;
	private String postedByUsername;
	private String text;
	
	public Comment() {
		
	}

	public int getCommentId() {
		return commentId;
	}

	

	public String getPostedByUsername() {
		return postedByUsername;
	}

	public void setPostedByUsername(String postedByUsername) {
		this.postedByUsername = postedByUsername;
	}

	public Comment(int commentId, int postedById, int profileId, String postedByUsername, String text) {
		super();
		this.commentId = commentId;
		this.postedById = postedById;
		this.profileId = profileId;
		this.postedByUsername = postedByUsername;
		this.text = text;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public int getPostedById() {
		return postedById;
	}

	public void setPostedById(int postedById) {
		this.postedById = postedById;
	}

	public int getProfileId() {
		return profileId;
	}

	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	

}

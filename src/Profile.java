package sup;

import java.util.ArrayList;

public class Profile {
	
	private int id;
	private String bio;
	private ArrayList<Comment> comments = new ArrayList<Comment>();
	
	public Profile() {
		// TODO Auto-generated constructor stub
	}


	public Profile(int id, String bio, ArrayList<Comment> comments) {
		this.id = id;
		this.bio = bio;
		this.comments = comments;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}

	public ArrayList<Comment> getComments() {
		return comments;
	}

	public void setComments(ArrayList<Comment> comments) {
		this.comments = comments;
	}
	
}

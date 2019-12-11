package sup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class ObjectMapping extends ActionSupport implements SessionAware{
	private Connection connection;
	private Map<String, Object> session = new HashMap<String, Object>();
	private ArrayList<User> users = new ArrayList<User>();
	private ArrayList<User> friends = new ArrayList<User>();
	private ArrayList<Comment> comments = new ArrayList<Comment>();
	private User user = new User();
	private String bio;
	private Profile profile = new Profile();
	private Comment comment = new Comment();
	
	public String register() {
		//add functionality
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sup?serverTimezone=UTC"
					,"root","root");
					int profileId = 0;
					PreparedStatement createProfile = connection.prepareStatement("INSERT INTO profiles " 
					+ "(profile_id, bio)" + "VALUES(?, ?)");
					
					createProfile.setString(1, null);
					createProfile.setString(2, null);
					createProfile.executeUpdate();
					createProfile.close();
					Statement statement = connection.createStatement();
					ResultSet rs = statement.executeQuery("SELECT profile_id FROM profiles ORDER BY profile_id DESC LIMIT 1");
					while(rs.next()) {
						profileId = rs.getInt(1);
					}
					
					PreparedStatement createUser = connection.prepareStatement("INSERT INTO users " 
					+ "(user_id, username, password, email, profile_id)" + "VALUES(?, ?, ?, ?, ?)");
					
					createUser.setString(1, null);
					createUser.setString(2, user.getUsername());
					createUser.setString(3, user.getPassword());
					createUser.setString(4, user.getEmail());
					createUser.setInt(5, profileId);
					
					createUser.executeUpdate();
					createUser.close();
					profile.setId(profileId);
					user.setProfile(profile);
					session.put("currentUser", user.getUsername());
					session.put("currentUserObject", user);
		}
		catch(SQLException e){
			return "FAIL";
		}
		
		
		return "SUCCESS";
	}
	
	public String addComment() {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sup?serverTimezone=UTC"
					,"root","root");
					PreparedStatement createComment = connection.prepareStatement("INSERT INTO comments " 
					+ "(comment_id, posted_by_id, profile_id, text, posted_by_username)" + "VALUES(?, ?, ?, ?, ?)");

					createComment.setString(1, null);
					createComment.setInt(2, ((User)session.get("currentUserObject")).getId());
					createComment.setInt(3, ((User)session.get("currentFriendUserObject")).getProfile().getId());
					createComment.setString(4, comment.getText());
					createComment.setString(5, ((User)session.get("currentUserObject")).getUsername());
					
					createComment.executeUpdate();
					createComment.close();
					
					Statement statement = connection.createStatement();
					ResultSet rs = statement.executeQuery("SELECT * FROM comments ORDER BY comment_id DESC LIMIT 1");
					while(rs.next()) {
						comments.add(new Comment(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5)));
					}
					
					ResultSet rs1 = statement.executeQuery("SELECT * FROM users WHERE username = '" + ((User)session.get("currentFriendUserObject")).getUsername() +"'");
					
					while(rs1.next()) {
						user.setId(rs1.getInt(1));
						user.setUsername(rs1.getString(2));
						user.setPassword(rs1.getString(3));
						user.setEmail(rs1.getString(4));
						profile.setId(rs1.getInt(5));
					}
					
					ResultSet rs2 = statement.executeQuery("SELECT * FROM profiles WHERE profile_id = " + ((User)session.get("currentFriendUserObject")).getProfile().getId());
					while(rs2.next()) {
						profile.setBio(rs2.getString(2));
					}
					
					ResultSet rs3 = statement.executeQuery("SELECT * FROM comments WHERE profile_id = " + ((User)session.get("currentFriendUserObject")).getProfile().getId());
					while(rs3.next()) {
						comment.setCommentId(rs3.getInt(1));
						comment.setPostedById(rs3.getInt(2));
						comment.setProfileId(rs3.getInt(3));
						comment.setText(rs3.getString(4));
						comment.setPostedByUsername(rs3.getString(5));
						comments.add(comment);
						comment = new Comment();
					}
					
					profile.setComments(comments);
					user.setProfile(profile);
					session.put("currentFriendUser", user.getUsername());
					session.put("currentFriendUserObject", user);
					return "SUCCESS";
		}
		catch(SQLException e){
			return "FAIL";
		}
	}
	
	public String addCommentProfile() {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sup?serverTimezone=UTC"
					,"root","root");
					PreparedStatement createComment = connection.prepareStatement("INSERT INTO comments " 
					+ "(comment_id, posted_by_id, profile_id, text, posted_by_username)" + "VALUES(?, ?, ?, ?, ?)");

					createComment.setString(1, null);
					createComment.setInt(2, ((User)session.get("currentUserObject")).getId());
					createComment.setInt(3, ((User)session.get("currentUserObject")).getProfile().getId());
					createComment.setString(4, comment.getText());
					createComment.setString(5, ((User)session.get("currentUserObject")).getUsername());
					
					createComment.executeUpdate();
					createComment.close();
					
					Statement statement = connection.createStatement();
					ResultSet rs = statement.executeQuery("SELECT * FROM comments ORDER BY comment_id DESC LIMIT 1");
					while(rs.next()) {
						comments.add(new Comment(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5)));
					}
					
					ResultSet rs1 = statement.executeQuery("SELECT * FROM users WHERE username = '" + ((User)session.get("currentUserObject")).getUsername() +"'");
					
					while(rs1.next()) {
						user.setId(rs1.getInt(1));
						user.setUsername(rs1.getString(2));
						user.setPassword(rs1.getString(3));
						user.setEmail(rs1.getString(4));
						profile.setId(rs1.getInt(5));
					}
					
					ResultSet rs2 = statement.executeQuery("SELECT * FROM profiles WHERE profile_id = " + ((User)session.get("currentUserObject")).getProfile().getId());
					while(rs2.next()) {
						profile.setBio(rs2.getString(2));
					}
					
					ResultSet rs3 = statement.executeQuery("SELECT * FROM comments WHERE profile_id = " + ((User)session.get("currentUserObject")).getProfile().getId());
					while(rs3.next()) {
						comment.setCommentId(rs3.getInt(1));
						comment.setPostedById(rs3.getInt(2));
						comment.setProfileId(rs3.getInt(3));
						comment.setText(rs3.getString(4));
						comment.setPostedByUsername(rs3.getString(5));
						comments.add(comment);
						comment = new Comment();
					}
					
					profile.setComments(comments);
					user.setProfile(profile);
					session.put("currentFriendUser", user.getUsername());
					session.put("currentFriendUserObject", user);
					return "SUCCESS";
		}
		catch(SQLException e){
			return "FAIL";
		}
	}
	public String login() {
		//add functionality
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sup?serverTimezone=UTC"
					,"root","root");
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM users WHERE username = '" + user.getUsername() +"'");
			String databasePassword = "";
			while(rs.next()) {
				user.setId(rs.getInt(1));
				user.setUsername(rs.getString(2));
				databasePassword = rs.getString(3);
				user.setEmail(rs.getString(4));
				profile.setId(rs.getInt(5));
			}
			
			ResultSet rs2 = statement.executeQuery("SELECT * FROM profiles WHERE profile_id = " + profile.getId());
			while(rs2.next()) {
				profile.setBio(rs2.getString(2));
			}
			
			ResultSet rs3 = statement.executeQuery("SELECT * FROM comments WHERE profile_id = " + profile.getId());
			while(rs3.next()) {
				comment.setCommentId(rs3.getInt(1));
				comment.setPostedById(rs3.getInt(2));
				comment.setProfileId(rs3.getInt(3));
				comment.setText(rs3.getString(4));
				comment.setPostedByUsername(rs3.getString(5));
				comments.add(comment);
				comment = new Comment();
			}
			
			if(databasePassword.equals(user.getPassword())) {
				profile.setComments(comments);
				user.setProfile(profile);
				user.setPassword(databasePassword);
				users.add(user);
				session.put("currentUser", user.getUsername());
				session.put("currentUserObject", user);
				return "SUCCESS";
			}
		}
		catch(SQLException e){
			return "FAIL";
		}
		return "LOGIN ERROR";
		
	}
	
	public String users() {
		//add functionality
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sup?serverTimezone=UTC"
					,"root","root");
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM users WHERE username !='" + session.get("currentUser") +"'");
			while(rs.next()) {
				user.setId(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setEmail(rs.getString(4));
				profile.setId(rs.getInt(5));
				user.setProfile(profile);
				users.add(user);
				user = new User();
				profile = new Profile();
			}
			
				return "SUCCESS";
		}
		catch(SQLException e){
			return "FAIL";
		}
	}
	
	public String getFriendProfile() {

		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sup?serverTimezone=UTC"
					,"root","root");
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM users WHERE username = '" + user.getUsername() +"'");
			
			while(rs.next()) {
				user.setId(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setEmail(rs.getString(4));
				profile.setId(rs.getInt(5));
			}
			
			ResultSet rs2 = statement.executeQuery("SELECT * FROM profiles WHERE profile_id = " + profile.getId());
			while(rs2.next()) {
				profile.setBio(rs2.getString(2));
			}
			
			ResultSet rs3 = statement.executeQuery("SELECT * FROM comments WHERE profile_id = " + profile.getId());
			while(rs3.next()) {
				comment.setCommentId(rs3.getInt(1));
				comment.setPostedById(rs3.getInt(2));
				comment.setProfileId(rs3.getInt(3));
				comment.setText(rs3.getString(4));
				comment.setPostedByUsername(rs3.getString(5));
				comments.add(comment);
				comment = new Comment();
			}
			
			profile.setComments(comments);
			user.setProfile(profile);
			session.put("currentFriendUser", user.getUsername());
			session.put("currentFriendUserObject", user);
			return "SUCCESS";
			
		}
		catch(SQLException e){
			return "FAIL";
		}
	}
	
	
	public String addFriend() {
		//add functionality
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sup?serverTimezone=UTC"
					,"root","root");
			
			PreparedStatement addFriend = connection.prepareStatement("INSERT INTO relationship " 
					+ "(user_one_id, user_two_id, status)" + "VALUES(?, ?, ?)");
			if(((User)session.get("currentFriendUserObject")).getId() > ((User)session.get("currentUserObject")).getId()) {
				addFriend.setInt(1, ((User)session.get("currentUserObject")).getId());
				addFriend.setInt(2, ((User)session.get("currentFriendUserObject")).getId());
			}
			else {
				addFriend.setInt(1, ((User)session.get("currentFriendUserObject")).getId());
				addFriend.setInt(2, ((User)session.get("currentUserObject")).getId());
			}
			addFriend.setInt(3, 1);
			
					
			addFriend.executeUpdate();
			addFriend.close();
			
				return "SUCCESS";
			
		}
		catch(SQLException e){
			return "FAIL";
		}
	}
	
	public String editBio() {
		//add functionality
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sup?serverTimezone=UTC"
					,"root","root");
			
			PreparedStatement addBio = connection.prepareStatement("UPDATE profiles " 
					+ "SET bio = ? WHERE profile_id = ?");
			
				addBio.setString(1, bio);
				addBio.setInt(2, ((User)session.get("currentUserObject")).getProfile().getId());
				addBio.executeUpdate();
				addBio.close();
					
					((User)session.get("currentUserObject")).getProfile().setBio(bio);
				return "SUCCESS";
			
		}
		catch(SQLException e){
			return "FAIL";
		}
	}
	
	public String viewFriends() {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sup?serverTimezone=UTC"
					,"root","root");
			int currentUserId = ((User)session.get("currentUserObject")).getId();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM relationship WHERE (user_one_id = "+ currentUserId +" OR user_two_id = "
			+ currentUserId + ") AND status = 1");
			while(rs.next()) {
					friends.add(new User(rs.getInt(1), null, null, null, null));
					friends.add(new User(rs.getInt(2), null, null, null, null));
			}
			
			for(User friend: friends) {
				if(friend.getId() != currentUserId) {
					ResultSet rs2 = statement.executeQuery("SELECT * FROM users WHERE user_id = " + friend.getId());
					while(rs2.next()) {
						friend.setId(rs2.getInt(1));
						friend.setUsername(rs2.getString(2));
						friend.setPassword(rs2.getString(3));
						friend.setEmail(rs2.getString(4));
						profile.setId(rs2.getInt(5));
						friend.setProfile(profile);
						profile = new Profile();
					}
				}
			}
			
			for(int i = 0; i <friends.size(); i++) {
				if(friends.get(i).getId() == currentUserId)
					friends.remove(i);
			}
			
			session.put("friendsSession", friends);
			return "SUCCESS";
		}
		
		catch(SQLException e){
			return "FAIL";
		}
	}
	
	public ArrayList<User> getUsers() {
		return users;
	}
	
	public String unfriend() {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sup?serverTimezone=UTC"
					,"root","root");
			int currentUserId = ((User)session.get("currentUserObject")).getId();
			int currentFriendId = ((User)session.get("currentFriendUserObject")).getId();
			PreparedStatement unfriend = connection.prepareStatement("UPDATE relationship " 
					+ "SET status = 0 WHERE user_one_id = ? AND user_two_id = ?");
			
			if(currentUserId > currentFriendId) {
				unfriend.setInt(1, currentFriendId);
				unfriend.setInt(2, currentUserId);
			}
			else {
				unfriend.setInt(1, currentUserId);
				unfriend.setInt(2, currentFriendId);
			}
				unfriend.executeUpdate();
				unfriend.close();
				return "SUCCESS";
			
		}
		catch(SQLException e){
			return "FAIL";
		}
	}
	
	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}

	public ArrayList<User> getFriends() {
		return friends;
	}

	public void setFriends(ArrayList<User> friends) {
		this.friends = friends;
	}

	public ArrayList<Comment> getComments() {
		return comments;
	}

	public void setComments(ArrayList<Comment> comments) {
		this.comments = comments;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	@Override
	public void setSession(Map map) {
		session = map;
	}
	
	public String saveUser() {
		session.put("currentUser", user.getUsername());
		return "SUCCESS";
	}
	
	public String saveFriend() {
		session.put("currentFriend", user.getUsername());
		return "SUCCESS";
	}
	
	public String logoff() {
		//add functionality
		session.remove("currentUserObject");
		session.remove("currentUser");
		return "SUCCESS";
	}
	
}

package epam.jmp.muha.main;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import epam.jmp.muha.dao.impl.JdbcFriendshipsDAO;
import epam.jmp.muha.dao.impl.JdbcLikesDAO;
import epam.jmp.muha.dao.impl.JdbcPostsDAO;
import epam.jmp.muha.dao.impl.JdbcUserDAO;
import epam.jmp.muha.database.factory.RandomTimestampGenerator;
import epam.jmp.muha.database.factory.TableFactory;
import epam.jmp.muha.model.Friend;
import epam.jmp.muha.model.Like;
import epam.jmp.muha.model.Post;
import epam.jmp.muha.model.User;

public class Main 
{
	final static int POST_COUNT = 800;
	final static int USER_COUNT = 1000;
	final static int LIKES_COUNT = 300000;
	final static int FRIENDS_COUNT = 70000;
	
    public static void main( String[] args )
    {
    	ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
    	 
    	createTables(context);

    	insertData(context);
    	
    	JdbcUserDAO userDAO = (JdbcUserDAO) context.getBean("userDAO");
		           	
        List<User> popularUsers = userDAO.findPopularUsers(10, 300);
        
        for (User user : popularUsers) 
        {
        	System.out.println(user);
		}               
    }
    
    public static void createTables(ApplicationContext context)
    {
    	TableFactory tableFactory = (TableFactory) context.getBean("tableFactory");
        
        Map<String, String> userTableLines = new HashMap<String, String>();
        userTableLines.put("id", "INT");
        userTableLines.put("name", "VARCHAR(255)");
        userTableLines.put("surname", "VARCHAR(255)");
        userTableLines.put("birthdate", "TIMESTAMP");
        List<String>  userPrimaryList = new ArrayList<String>();
        userPrimaryList.add("id");
		tableFactory.createTable("user", userTableLines , userPrimaryList, "id");
		
        Map<String, String> postsTableLines = new HashMap<String, String>();
        postsTableLines.put("id", "INT");
        postsTableLines.put("userId", "INT");
        postsTableLines.put("text", "TEXT");
        postsTableLines.put("timestamp", "TIMESTAMP");
        List<String>  postsPrimaryList = new ArrayList<String>();
        postsPrimaryList.add("id");
		tableFactory.createTable("posts", postsTableLines , postsPrimaryList, "id");
		
        Map<String, String> likesTableLines = new HashMap<String, String>();
        likesTableLines.put("postID", "INT");
        likesTableLines.put("userId", "INT");
        likesTableLines.put("timestamp", "TIMESTAMP");
        List<String>  likesPrimaryList = new ArrayList<String>();
        likesPrimaryList.add("postID");
        likesPrimaryList.add("userId");
		tableFactory.createTable("likes", likesTableLines, likesPrimaryList, null);
		
        Map<String, String> friendshipsTableLines = new HashMap<String, String>();
        friendshipsTableLines.put("userId1", "INT");
        friendshipsTableLines.put("userId2", "INT");
        friendshipsTableLines.put("timestamp", "TIMESTAMP");
        List<String>  friendPrimaryList = new ArrayList<String>();
        friendPrimaryList.add("userId1");
        friendPrimaryList.add("userId2");
		tableFactory.createTable("friendships", friendshipsTableLines, friendPrimaryList, null);
    }
    
    public static void insertData(ApplicationContext context)
    {
    	Random rnd = new Random();
		JdbcUserDAO userDAO = (JdbcUserDAO) context.getBean("userDAO");
		for (int i = 1; i <= USER_COUNT; i++) 
		{
			Instant then = Instant.now().minus(1000, ChronoUnit.DAYS);
			Instant randomTimestamp = RandomTimestampGenerator.between(then, 800);
			Timestamp sqlDate = Timestamp.from(randomTimestamp);
			User user = new User(i, "User"+i+ " Name", "User"+i+ " SurName", sqlDate);
			userDAO.insert(user);
		}	
		
		JdbcPostsDAO postsDAO = (JdbcPostsDAO) context.getBean("postsDAO");
		List<Post> posts = new ArrayList<Post>();
		for (int i = 1; i <= POST_COUNT; i++) 
		{
			Instant then = Instant.now().minus(1000, ChronoUnit.DAYS);
			Instant randomTimestamp = RandomTimestampGenerator.between(then, 800);
			Timestamp sqlDate = Timestamp.from(randomTimestamp);
					
			int userid = rnd.nextInt(USER_COUNT);
			
			Post post = new Post(i, userid, "Some Text in Posts number: "+i, sqlDate);
			posts.add(post);
		}	
		postsDAO.insertList(posts);
		
		JdbcLikesDAO likesDAO = (JdbcLikesDAO) context.getBean("likesDAO");
		HashSet<Like> likes = new HashSet<Like>();
		while (likes.size() <= LIKES_COUNT) 
		{					
			int userid = rnd.nextInt(USER_COUNT);
			int postid = rnd.nextInt(POST_COUNT);
			Like like = new Like(postid, userid, null);
			likes.add(like);
		}
		for (Like like : likes) 
		{
			Instant then = Instant.now().minus(780, ChronoUnit.DAYS);
			Instant randomTimestamp = RandomTimestampGenerator.between(then, 300);
			Timestamp sqlDate = Timestamp.from(randomTimestamp);
			like.setTimestamp(sqlDate);
		}
		likesDAO.insertSet(likes);
		
		JdbcFriendshipsDAO friendshipsDAO = (JdbcFriendshipsDAO) context.getBean("friendshipsDAO");
		HashSet<Friend> friends = new HashSet<Friend>();
		for (int i = 1; i <= FRIENDS_COUNT; i++) 
		{			
			int user1id = rnd.nextInt(USER_COUNT);
			int user2id = rnd.nextInt(USER_COUNT);
			if(user1id != user2id)
			{
				Friend friend = null;			
				if (user1id < user2id)
				{
				  friend = new Friend(user1id, user2id, null);
				}
				else
				{
				  friend = new Friend(user2id, user1id, null);
				}
				friends.add(friend);
			}
		}
		for (Friend friend : friends)
		{
			Instant then = Instant.now().minus(480, ChronoUnit.DAYS);
			Instant randomTimestamp = RandomTimestampGenerator.between(then, 300);
			Timestamp sqlDate = Timestamp.from(randomTimestamp);
			friend.setTimestamp(sqlDate);
		}
		friendshipsDAO.insertSet(friends);
    }
    public static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }
}

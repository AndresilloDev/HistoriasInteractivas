package mx.edu.utez.historiasinteractivas.dao;

import mx.edu.utez.historiasinteractivas.model.Story;
import mx.edu.utez.historiasinteractivas.model.User;
import mx.edu.utez.historiasinteractivas.utils.DatabaseConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StoryDao {
    public Story findByCode(String code) throws SQLException {
        Story story = null;
        String query = "SELECT * FROM stories WHERE code=?";
        try (Connection con = DatabaseConnectionManager.getConnection();
        PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1, code);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                story = new Story();
                story.setEmail_user(rs.getString("email_user"));
                story.setStory_title(rs.getString("story_title"));
                story.setStory_description(rs.getString("story_description"));
                story.setStory_thumbnail(rs.getString("story_thumbnail"));
                story.setStory_type(rs.getInt("story_type"));
                story.setRelease_date(rs.getDate("release_date"));
                story.setLast_update(rs.getDate("last_update"));
                story.setJson(rs.getString("json"));

                return story;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Story> getAll(User user) throws SQLException {
        ArrayList<Story> stories = new ArrayList<Story>();
        String query = "SELECT * FROM stories WHERE email_user=?";
        try (Connection con = DatabaseConnectionManager.getConnection();
        PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1, user.getEmail());
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Story story = new Story();
                story.setEmail_user(rs.getString("email_user"));
                story.setStory_title(rs.getString("story_title"));
                story.setStory_description(rs.getString("story_description"));
                story.setStory_thumbnail(rs.getString("story_thumbnail"));
                story.setStory_type(rs.getInt("story_type"));
                story.setRelease_date(rs.getDate("release_date"));
                story.setLast_update(rs.getDate("last_update"));
                story.setJson(rs.getString("json"));
                stories.add(story);
            }
            return stories;
        }
    }


    public ArrayList<Story> getAllPublicStories(User user) throws SQLException {
        ArrayList<Story> stories = new ArrayList<Story>();
        String query = "SELECT * FROM stories WHERE email_user = ? AND story_type = 1";
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1, user.getEmail());
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Story story = new Story();
                story.setEmail_user(rs.getString("email_user"));
                story.setStory_title(rs.getString("story_title"));
                story.setStory_description(rs.getString("story_description"));
                story.setStory_thumbnail(rs.getString("story_thumbnail"));
                story.setStory_type(rs.getInt("story_type"));
                story.setRelease_date(rs.getDate("release_date"));
                story.setLast_update(rs.getDate("last_update"));
                story.setJson(rs.getString("json"));
                stories.add(story);
            }
            return stories;
        }
    }

    public ArrayList<Story> getAllRestrictedStories(User user) throws SQLException {
        ArrayList<Story> stories = new ArrayList<Story>();
        String query = "SELECT * FROM stories WHERE email_user = ? AND story_type = 2";
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1, user.getEmail());
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Story story = new Story();
                story.setEmail_user(rs.getString("email_user"));
                story.setStory_title(rs.getString("story_title"));
                story.setStory_description(rs.getString("story_description"));
                story.setStory_thumbnail(rs.getString("story_thumbnail"));
                story.setStory_type(rs.getInt("story_type"));
                story.setRelease_date(rs.getDate("release_date"));
                story.setLast_update(rs.getDate("last_update"));
                story.setJson(rs.getString("json"));
                stories.add(story);
            }
            return stories;
        }
    }

    public ArrayList<Story> getAllDraftStories(User user) throws SQLException {
        ArrayList<Story> stories = new ArrayList<Story>();
        String query = "SELECT * FROM stories WHERE email_user = ? AND story_type = 3";
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1, user.getEmail());
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Story story = new Story();
                story.setEmail_user(rs.getString("email_user"));
                story.setStory_title(rs.getString("story_title"));
                story.setStory_description(rs.getString("story_description"));
                story.setStory_thumbnail(rs.getString("story_thumbnail"));
                story.setStory_type(rs.getInt("story_type"));
                story.setRelease_date(rs.getDate("release_date"));
                story.setLast_update(rs.getDate("last_update"));
                story.setJson(rs.getString("json"));
                stories.add(story);
            }
            return stories;
        }
    }
}

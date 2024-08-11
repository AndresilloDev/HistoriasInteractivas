package mx.edu.utez.historiasinteractivas.dao;

import mx.edu.utez.historiasinteractivas.model.Scene;
import mx.edu.utez.historiasinteractivas.model.Story;
import mx.edu.utez.historiasinteractivas.model.User;
import mx.edu.utez.historiasinteractivas.utils.DatabaseConnectionManager;
import mx.edu.utez.historiasinteractivas.utils.GenerateStoryCode;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StoryDao {
    public boolean createStory(String email) throws SQLException {
        String query = "INSERT INTO historiasInteractivas.Stories ";
        try (Connection con = DatabaseConnectionManager.getConnection();
        PreparedStatement ps = con.prepareStatement(query)){
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Story findByCode(String code, User user) throws SQLException {
        Story story = null;
        String query = "SELECT * FROM historiasInteractivas.Stories WHERE id_story=? AND email_user = ?";
        try (Connection con = DatabaseConnectionManager.getConnection();
        PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1, code);
            ps.setString(2, user.getEmail());
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

    public Story findPublicStoryByCode(String id_story) throws SQLException {
        Story story = null;
        String query = "SELECT * FROM historiasInteractivas.Stories WHERE id_story = ? AND story_type = 1";
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1, id_story);
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
        String query = "SELECT * FROM historiasInteractivas.Stories WHERE email_user=?";
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
        String query = "SELECT * FROM historiasInteractivas.Stories WHERE email_user = ? AND story_type = 1";
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1, user.getEmail());
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Story story = new Story();
                story.setId_story(rs.getString("id_story"));
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
        String query = "SELECT * FROM historiasInteractivas.Stories WHERE email_user = ? AND story_type = 2";
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1, user.getEmail());
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Story story = new Story();
                story.setId_story(rs.getString("id_story"));
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
        String query = "SELECT * FROM historiasInteractivas.Stories WHERE email_user = ? AND story_type = 3";
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1, user.getEmail());
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Story story = new Story();
                story.setId_story(rs.getString("id_story"));
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

    public boolean isCodeUnique(String code) {
        String sql = "SELECT COUNT(*) FROM historiasInteractivas.Stories WHERE story_id = ?";
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, code);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) == 0; // Retorna true si no hay coincidencias
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String generateUniqueCode(int length) {
        String code;
        do {
            code = GenerateStoryCode.generateRandomCode(length);
        } while (!isCodeUnique(code));
        return code;
    }
    public void updateStory(String id, String title, String json) {
        String sql = "UPDATE historiasInteractivas.Stories SET story_title = ?, json = ? WHERE id = ?";
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, title);
            ps.setString(2, json);
            ps.setString(3, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean insertStory(String id_story, String email, String title, String json) {
        String query = "INSERT INTO historiasInteractivas.Stories (id_story, email_user, story_title, json) VALUES (?, ?, ?, ?)";
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, id_story);
            ps.setString(2, email);
            ps.setString(3, title);
            ps.setString(4, json);

            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

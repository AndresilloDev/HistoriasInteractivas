package mx.edu.utez.historiasinteractivas.dao;

import mx.edu.utez.historiasinteractivas.model.Story;
import mx.edu.utez.historiasinteractivas.model.User;
import mx.edu.utez.historiasinteractivas.utils.DatabaseConnectionManager;
import mx.edu.utez.historiasinteractivas.utils.RandomStringGenerator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StoryDao {

    public Story findByCode(String code, User user) throws SQLException {
        Story story = null;
        String query = "SELECT * FROM historiasInteractivas.Stories WHERE id_story=? AND email_user = ?";
        try (Connection con = DatabaseConnectionManager.getConnection();
        PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1, code);
            ps.setString(2, user.getEmail());
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {

                String id = rs.getString("id_story");
                String email = rs.getString("email_user");
                String title = rs.getString("story_title");
                String description = rs.getString("story_description");
                String thumbnail = rs.getString("story_thumbnail");
                int story_type = rs.getInt("story_type");
                Date release_date = rs.getDate("release_date");
                Date last_update = rs.getDate("last_update");
                String json = rs.getString("json");

                story = new Story(id, email, title, release_date, description, thumbnail, json, story_type, last_update);

                return story;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return story;
    }

    public Story findPublicStoryByCode(String id_story) throws SQLException {
        Story story = null;
        String query = "SELECT * FROM historiasInteractivas.Stories INNER JOIN historiasInteractivas.Users ON email_user = email WHERE id_story = ? AND story_type = 1 AND status = 1";
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1, id_story);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {

                String id = rs.getString("id_story");
                String email = rs.getString("email_user");
                String title = rs.getString("story_title");
                String description = rs.getString("story_description");
                String thumbnail = rs.getString("story_thumbnail");
                int story_type = rs.getInt("story_type");
                Date release_date = rs.getDate("release_date");
                Date last_update = rs.getDate("last_update");
                String json = rs.getString("json");

                story = new Story(id, email, title, release_date, description, thumbnail, json, story_type, last_update);

                return story;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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
        String sql = "SELECT COUNT(*) FROM historiasInteractivas.Stories WHERE id_story = ?";
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
    public boolean updateStory(Story story) {
        String sql = "UPDATE historiasInteractivas.Stories SET story_title = ?, json = ? WHERE id_story = ?";
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, story.getStory_title());
            ps.setString(2, story.getJson());
            ps.setString(3, story.getId_story());

            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean insertStory(Story story) {
        String query = "INSERT INTO historiasInteractivas.Stories (id_story, email_user, story_title, story_description, story_thumbnail, json) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, story.getId_story());
            ps.setString(2, story.getEmail_user());
            ps.setString(3, story.getStory_title());
            ps.setString(4, story.getStory_description());
            ps.setString(5, story.getStory_thumbnail());
            ps.setString(6, story.getJson());

            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean existsStory(String idStory) {
        String sql = "SELECT COUNT(*) FROM historiasInteractivas.Stories WHERE id_story = ? AND story_type=1";
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, idStory);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) == 1; // Retorna true si hay coincidencias
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean updateStoryStatus(String id_story, int newStatus, java.sql.Date release_date) throws SQLException {
        String sql = "UPDATE historiasInteractivas.Stories SET story_type = ?, release_date = ? WHERE id_story = ?";
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, newStatus);
            if (release_date != null) {
                statement.setDate(2, release_date);
            } else {
                statement.setNull(2, java.sql.Types.DATE);
            }
            statement.setString(3, id_story);

            return statement.executeUpdate() > 0;
        }
    }
}

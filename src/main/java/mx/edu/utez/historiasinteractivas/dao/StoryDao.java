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
        String query = "SELECT * FROM story WHERE code=?";
        try (Connection con = DatabaseConnectionManager.getConnection();
        PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1, code);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                story = new Story();
                story.setEmail_user(rs.getString("email_user"));
                story.setHistory_title(rs.getString("history_title"));
                story.setRelease_date(rs.getDate("release_date"));
                story.setHistory_description(rs.getString("history_description"));
                story.setHistory_thumbnail(rs.getString("history_thumbnail"));
                story.setJson(rs.getString("json"));

                return story;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Story> findAllStoriesByUser(User user) throws SQLException {
        ArrayList<Story> stories = new ArrayList<Story>();
        String query = "SELECT * FROM story WHERE email_user=?";
        try (Connection con = DatabaseConnectionManager.getConnection();
        PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1, user.getEmail());
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Story story = new Story();
                story.setEmail_user(rs.getString("email_user"));
                story.setHistory_title(rs.getString("history_title"));
                story.setRelease_date(rs.getDate("release_date"));
                story.setHistory_description(rs.getString("history_description"));
                story.setHistory_thumbnail(rs.getString("history_thumbnail"));
                story.setJson(rs.getString("json"));
                stories.add(story);
            }
            return stories;
        }
    }
}

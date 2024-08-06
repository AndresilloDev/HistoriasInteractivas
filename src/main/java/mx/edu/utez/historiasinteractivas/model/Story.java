package mx.edu.utez.historiasinteractivas.model;
import java.util.Date;
import java.sql.Blob;

public class Story {
    private int id_history;
    private String email_user;
    private String history_title;
    private Date release_date;
    private String history_description;
    private String history_thumbnail;
    private String json;

    public int getId_history() {
        return id_history;
    }

    public void setId_history(int id_history) {
        this.id_history = id_history;
    }

    public String getEmail_user() {
        return email_user;
    }

    public void setEmail_user(String email_user) {
        this.email_user = email_user;
    }

    public String getHistory_title() {
        return history_title;
    }

    public void setHistory_title(String history_title) {
        this.history_title = history_title;
    }

    public Date getRelease_time() {
        return release_date;
    }

    public void setRelease_date(Date release_date) {
        this.release_date = release_date;
    }

    public String getHistory_description() {
        return history_description;
    }

    public void setHistory_description(String history_description) {
        this.history_description = history_description;
    }

    public String getHistory_thumbnail() {
        return history_thumbnail;
    }

    public void setHistory_thumbnail(String history_thumbnail) {
        this.history_thumbnail = history_thumbnail;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }
}

package mx.edu.utez.historiasinteractivas.model;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Blob;
import java.util.List;

public class Story {
    private String id_story;
    private String email_user;
    private String story_title;
    private Date release_date;
    private String story_description;
    private String story_thumbnail;
    private String json;
    private int story_type;

    private Date last_update;

    private ArrayList<Scene> scenes;

    public String getId_story() {
        return id_story;
    }

    public void setId_story(String id_story) {
        this.id_story = id_story;
    }

    public String getEmail_user() {
        return email_user;
    }

    public void setEmail_user(String email_user) {
        this.email_user = email_user;
    }

    public String getStory_title() {
        return story_title;
    }

    public void setStory_title(String story_title) {
        this.story_title = story_title;
    }

    public Date getRelease_time() {
        return release_date;
    }

    public void setRelease_date(Date release_date) {
        this.release_date = release_date;
    }

    public String getStory_description() {
        return story_description;
    }

    public void setStory_description(String story_description) {
        this.story_description = story_description;
    }

    public String getStory_thumbnail() {
        return story_thumbnail;
    }

    public void setStory_thumbnail(String story_thumbnail) {
        this.story_thumbnail = story_thumbnail;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public Date getRelease_date() {
        return release_date;
    }

    public int getStory_type() {
        return story_type;
    }

    public void setStory_type(int story_type) {
        this.story_type = story_type;
    }

    public Date getLast_update() {
        return last_update;
    }

    public void setLast_update(Date last_update) {
        this.last_update = last_update;
    }

    public void addScene(Scene scene) {
        scenes.add(scene);
    }

    public ArrayList<Scene> getScenes() {
        return scenes;
    }

    public void setScenes(ArrayList<Scene> scenes) {
        this.scenes = scenes;
    }

    public ArrayList<Scene> parseJsonToScenes() {
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<Scene> scenes = new ArrayList<>();

        try {
            JsonNode rootNode = mapper.readTree(json);
            JsonNode scenesNode = rootNode.path("scenes");

            if (scenesNode.isArray()) {
                scenes = mapper.readValue(scenesNode.toString(), new TypeReference<>() {
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return scenes;
    }
    public Scene getScene(int destination_scene) {
        for (Scene scene : scenes) {
            if (scene.getId_scene() == destination_scene) {
                return scene;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Story{" +
                "id_story='" + id_story + '\'' +
                ", email_user='" + email_user + '\'' +
                ", story_title='" + story_title + '\'' +
                ", release_date=" + release_date +
                ", story_description='" + story_description + '\'' +
                ", story_thumbnail='" + story_thumbnail + '\'' +
                ", json='" + json + '\'' +
                ", story_type=" + story_type +
                ", last_update=" + last_update +
                ", scenes=" + scenes +
                '}';
    }
}

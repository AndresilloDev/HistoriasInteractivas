package mx.edu.utez.historiasinteractivas.model;

public class Event {
    private String category;
    private int key;
    private String loc;
    private String text;
    private String description;
    private String image;
    private String video;
    private String audio;
    private String link;

    // C O N S T R U C T O R E S

    public Event() {
    }

    public Event(String category, int key, String loc, String text, String description, String image, String video, String audio, String link) {
        this.category = category;
        this.key = key;
        this.loc = loc;
        this.text = text;
        this.description = description;
        this.image = image;
        this.video = video;
        this.audio = audio;
        this.link = link;
    }

    // G E T T E R S   Y   S E T T E R S

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}

package mx.edu.utez.historiasinteractivas.model;

public class Scene {

    private int id_scene;
    private String scene_name;
    private String scene_text;
    private String scene_audio;
    private String scene_image;
    private String scene_video;
    private String scene_link;
    private String first_choice;
    private String second_choice;
    private Integer destiny_scene_option_1;
    private Integer destiny_scene_option_2;

    public int getId_scene() {
        return id_scene;
    }

    public void setId_scene(int id_scene) {
        this.id_scene = id_scene;
    }

    public String getScene_name() {
        return scene_name;
    }

    public void setScene_name(String scene_name) {
        this.scene_name = scene_name;
    }

    public String getScene_text() {
        return scene_text;
    }

    public void setScene_text(String scene_text) {
        this.scene_text = scene_text;
    }

    public int getDestiny_scene_option_1() {
        return destiny_scene_option_1;
    }

    public void setDestiny_scene_option_1(int destiny_scene_option_1) {
        this.destiny_scene_option_1 = destiny_scene_option_1;
    }

    public int getDestiny_scene_option_2() {
        return destiny_scene_option_2;
    }

    public void setDestiny_scene_option_2(int destiny_scene_option_2) {
        this.destiny_scene_option_2 = destiny_scene_option_2;
    }

    public String getFirst_choice() {
        return first_choice;
    }

    public void setFirst_choice(String first_choice) {
        this.first_choice = first_choice;
    }

    public String getSecond_choice() {
        return second_choice;
    }

    public void setSecond_choice(String second_choice) {
        this.second_choice = second_choice;
    }

    public String getScene_image() {
        return scene_image;
    }

    public void setScene_image(String scene_image) {
        this.scene_image = scene_image;
    }

    public String getScene_audio() {
        return scene_audio;
    }

    public void setScene_audio(String scene_audio) {
        this.scene_audio = scene_audio;
    }

    public String getScene_video() {
        return scene_video;
    }

    public void setScene_video(String scene_video) {
        this.scene_video = scene_video;
    }

    public String getScene_link() {
        return scene_link;
    }

    public void setScene_link(String scene_link) {
        this.scene_link = scene_link;
    }

    @Override
    public String toString() {
        return "Scene{" +
                "id_scene=" + id_scene +
                ", scene_name='" + scene_name + '\'' +
                ", scene_text='" + scene_text + '\'' +
                ", scene_audio='" + scene_audio + '\'' +
                ", scene_image='" + scene_image + '\'' +
                ", scene_video='" + scene_video + '\'' +
                ", scene_link='" + scene_link + '\'' +
                ", first_choice='" + first_choice + '\'' +
                ", second_choice='" + second_choice + '\'' +
                ", destiny_scene_option_1=" + destiny_scene_option_1 +
                ", destiny_scene_option_2=" + destiny_scene_option_2 +
                '}';
    }
}
package mx.edu.utez.historiasinteractivas.model;
import java.sql.Blob;
public class Nodes {
    private int id_node;
    private int id_history;
    private String node_name;
    private String node_text;
    private int id_node_destiny;
    private String first_choice;
    private String second_choice;
    private Blob node_image;
    private Blob node_audio;

    public int getId_node() {
        return id_node;
    }

    public void setId_node(int id_node) {
        this.id_node = id_node;
    }

    public int getId_history() {
        return id_history;
    }

    public void setId_history(int id_history) {
        this.id_history = id_history;
    }

    public String getNode_name() {
        return node_name;
    }

    public void setNode_name(String node_name) {
        this.node_name = node_name;
    }

    public String getNode_text() {
        return node_text;
    }

    public void setNode_text(String node_text) {
        this.node_text = node_text;
    }

    public int getId_node_destiny() {
        return id_node_destiny;
    }

    public void setId_node_destiny(int id_node_destiny) {
        this.id_node_destiny = id_node_destiny;
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

    public Blob getNode_image() {
        return node_image;
    }

    public void setNode_image(Blob node_image) {
        this.node_image = node_image;
    }

    public Blob getNode_audio() {
        return node_audio;
    }

    public void setNode_audio(Blob node_audio) {
        this.node_audio = node_audio;
    }
}

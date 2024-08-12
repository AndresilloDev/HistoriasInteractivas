package mx.edu.utez.historiasinteractivas.model;

import java.util.ArrayList;

public class GraphLinksModel {
    private String class_name;
    private ArrayList<Event> nodeDataArray;
    private ArrayList<Link> linkDataArray;

    // C O N S T R U C T O R E S

    public GraphLinksModel() {
    }

    public GraphLinksModel(String class_name, ArrayList<Event> nodeDataArray, ArrayList<Link> linkDataArray) {
        this.class_name = class_name;
        this.nodeDataArray = nodeDataArray;
        this.linkDataArray = linkDataArray;
    }

    // G E T T E R S   Y   S E T T E R S

    public String getClass_name() {

        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public ArrayList<Event> getNodeDataArray() {
        return nodeDataArray;
    }

    public void setNodeDataArray(ArrayList<Event> nodeDataArray) {
        this.nodeDataArray = nodeDataArray;
    }

    public ArrayList<Link> getLinkDataArray() {
        return linkDataArray;
    }

    public void setLinkDataArray(ArrayList<Link> linkDataArray) {
        this.linkDataArray = linkDataArray;
    }

    public Event getEventByKey(int key) {
        for (Event event : nodeDataArray) {
            if (event.getKey() == key) {
                return event;
            }
        }
        return null; // Devuelve null si no se encuentra el evento con la key dada
    }

    public ArrayList<String> getTextOfLinkedEvents(int fromKey) {
        ArrayList<String> linkedTexts = new ArrayList<>();

        // Encontrar todas las llaves de eventos encadenados
        ArrayList<Integer> linkedKeys = new ArrayList<>();
        for (Link link : linkDataArray) {
            if (link.getFrom() == fromKey) {
                linkedKeys.add(link.getTo());
            }
        }

        // Encontrar los eventos por las llaves y obtener el atributo 'text'
        for (int key : linkedKeys) {
            for (Event event : nodeDataArray) {
                if (event.getKey() == key) {
                    linkedTexts.add(event.getText());
                }
            }
        }
        return linkedTexts.isEmpty() ? null : linkedTexts;
    }

    public ArrayList<Integer> getKeysOfLinkedEvents(int fromKey) {
        ArrayList<Integer> linkedKeys = new ArrayList<>();

        // Encontrar todas las llaves de eventos encadenados
        for (Link link : linkDataArray) {
            if (link.getFrom() == fromKey) {
                linkedKeys.add(link.getTo());
            }
        }

        return linkedKeys.isEmpty() ? null : linkedKeys;
    }

    @Override
    public String toString() {
        return "GraphLinksModel{" +
                "class_name='" + class_name + '\'' +
                ", nodeDataArray=" + nodeDataArray.toString() +
                ", linkDataArray=" + linkDataArray.toString() +
                '}';
    }
}

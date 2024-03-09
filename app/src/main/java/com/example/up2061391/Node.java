package com.example.up2061391;

public class Node {
    // declares all attributes in the Node constructor
    int ID;
    int yesID;
    int noID;
    String description;
    String question;

    public Node(int ID, int yesID, int noID, String description, String question) { // sets constructor with all values
        this.ID = ID;
        this.yesID = yesID;
        this.noID = noID;
        this.description = description;
        this.question = question;
    }

    public Node() { // empty constructor
    }

    @Override
    public String toString() { // returns node to string
        return "Node{" +
                "ID=" + ID +
                ", yesID=" + yesID +
                ", noID=" + noID +
                ", description='" + description + '\'' +
                ", question='" + question + '\'' +
                '}';
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getYesID() {
        return yesID;
    }

    public void setYesID(int yesID) {
        this.yesID = yesID;
    }

    public int getNoID() {
        return noID;
    }

    public void setNoID(int noID) {
        this.noID = noID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}

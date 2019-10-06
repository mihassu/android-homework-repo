package ru.mihassu.lesson6material;

public class ItemCardModel {

    private String title;
    private String description;
    private int imageID;

    public ItemCardModel(String title, String description, int imageID) {
        this.title = title;
        this.description = description;
        this.imageID = imageID;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getImageID() {
        return imageID;
    }
}

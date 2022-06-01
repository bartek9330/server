package com.fit_web_app.models.dto;

public class CourseDTO {
    private int id;
    private String name;
    private String description;
    private int numberOfSteps;
    private String image;

    public CourseDTO(int id, String name, String description, int numberOfSteps, byte[] image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.numberOfSteps = numberOfSteps;
        this.image = new String(image);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumberOfSteps() {
        return numberOfSteps;
    }

    public void setNumberOfSteps(int numberOfSteps) {
        this.numberOfSteps = numberOfSteps;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

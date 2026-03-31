package dev.horbatiuk.pcds.lab2.model;

public class Quest {
    private Long id;
    private String title;
    private String location;
    private String difficulty;
    private int durationMinutes;

    public Quest() {
    }

    public Quest(Long id, String title, String location, String difficulty, int durationMinutes) {
        this.id = id;
        this.title = title;
        this.location = location;
        this.difficulty = difficulty;
        this.durationMinutes = durationMinutes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }
}
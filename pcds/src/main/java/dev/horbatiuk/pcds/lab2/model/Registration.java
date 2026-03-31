package dev.horbatiuk.pcds.lab2.model;

public class Registration {
    private Long id;
    private Long questId;
    private Long teamId;
    private String date;
    private String status;

    public Registration() {
    }

    public Registration(Long id, Long questId, Long teamId, String date, String status) {
        this.id = id;
        this.questId = questId;
        this.teamId = teamId;
        this.date = date;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuestId() {
        return questId;
    }

    public void setQuestId(Long questId) {
        this.questId = questId;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
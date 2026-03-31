package dev.horbatiuk.pcds.lab2.model;

public class Team {
    private Long id;
    private String name;
    private int membersCount;
    private String captainName;

    public Team() {
    }

    public Team(Long id, String name, int membersCount, String captainName) {
        this.id = id;
        this.name = name;
        this.membersCount = membersCount;
        this.captainName = captainName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMembersCount() {
        return membersCount;
    }

    public void setMembersCount(int membersCount) {
        this.membersCount = membersCount;
    }

    public String getCaptainName() {
        return captainName;
    }

    public void setCaptainName(String captainName) {
        this.captainName = captainName;
    }
}
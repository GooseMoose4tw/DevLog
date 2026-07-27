package com.joshuakligman.model;

public class CodingSession {
    private int id;
    private String project;
    private String language;
    private String date;
    private String startTime;
    private String endTime;
    private int duration;
    private String feature;
    private String notes;
    private String bugsEncountered;
    private String bugsFixed;
    private String bugsRemaining;
    private int productivity;

    public CodingSession(int id, String project, String language, String date, String startTime, String endTime, int duration, String feature, String notes, String bugsEncountered, String bugsFixed, String bugsRemaining, int productivity) {
        this.id = id;
        this.project = project;
        this.language = language;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
        this.feature = feature;
        this.notes = notes;
        this.bugsEncountered = bugsEncountered;
        this.bugsFixed = bugsFixed;
        this.bugsRemaining = bugsRemaining;
        this.productivity = productivity;
    }

    @Override
    public String toString() {
        return "CodingSession{" +
                "id=" + id +
                ", project='" + project + '\'' +
                ", language='" + language + '\'' +
                ", date='" + date + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", duration=" + duration +
                ", feature='" + feature + '\'' +
                ", notes='" + notes + '\'' +
                ", bugsEncountered='" + bugsEncountered + '\'' +
                ", bugsFixed='" + bugsFixed + '\'' +
                ", bugsRemaining='" + bugsRemaining + '\'' +
                ", productivity=" + productivity +
                '}';
    }

    public CodingSession(String project, String language, String date, String startTime, String endTime,
                         int duration, String feature, String notes, String bugsEncountered, String bugsFixed, String bugsRemaining, int productivity) {

        this.project = project;
        this.language = language;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
        this.feature = feature;
        this.notes = notes;
        this.bugsEncountered = bugsEncountered;
        this.bugsFixed = bugsFixed;
        this.bugsRemaining = bugsRemaining;
        this.productivity = productivity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setBugsEncountered(String bugsEncountered) {
        this.bugsEncountered = bugsEncountered;
    }

    public void setBugsFixed(String bugsFixed) {
        this.bugsFixed = bugsFixed;
    }

    public void setBugsRemaining(String bugsRemaining) {
        this.bugsRemaining = bugsRemaining;
    }

    public void setProductivity(int productivity) {
        this.productivity = productivity;
    }

    public int getId() {
        return id;
    }

    public String getProject() {
        return project;
    }

    public String getLanguage() {
        return language;
    }

    public String getDate() {
        return date;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public int getDuration() {
        return duration;
    }

    public String getFeature() {
        return feature;
    }

    public String getNotes() {
        return notes;
    }

    public String getBugsEncountered() {
        return bugsEncountered;
    }

    public String getBugsFixed() {
        return bugsFixed;
    }

    public String getBugsRemaining() {
        return bugsRemaining;
    }

    public int getProductivity() {
        return productivity;
    }
}

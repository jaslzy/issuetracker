package com.example.issuetracker;



public class IssueDTO {

    //@NotNull(message = "Issue ID cannot be null")
    private Long id;

    private String title;
    private String description;

    public IssueDTO(){
        
    }

    public IssueDTO(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

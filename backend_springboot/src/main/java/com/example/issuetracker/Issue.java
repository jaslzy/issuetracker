package com.example.issuetracker;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Issue {
    @Id
    @Column(name = "keyno")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long keyno; // Primary key, auto-generated
  
    @Column(name = "id")
    private Long idField; // Set by the user, not the primary key
    
    @Column (name = "title")
    private String title;

    @Column(name = "desc")
    private String description;


    // Constructors, Getters and Setters
    public Issue() {
    }

    public Issue(Long id, String title, String description) {
        this.idField = id;
        this.title = title;
        this.description = description;
    }

    public Long getKeyno() {
        return keyno;
    }

    public void setKeyno(Long keyno) {
        this.keyno = keyno;
    }

    public Long getIdField() {
        return idField;
    }

    public void setIdField(Long id) {
        this.idField = id;
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

package com.cdac.E_Learning.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Note extends BaseEntity {

    private String title;
    private String url; // URL to the PDF file

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "chapter_id", nullable = false)
    private Chapter chapter;
    
    @Column(nullable = false)
    private int isActive = 1; // Default is active
}
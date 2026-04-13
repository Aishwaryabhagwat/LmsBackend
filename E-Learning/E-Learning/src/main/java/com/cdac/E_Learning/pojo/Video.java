package com.cdac.E_Learning.pojo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Video extends BaseEntity {

    private String title;
    private String url;
    private int duration; // Duration in minutes

    @Column(nullable = false)
    private int isActive;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "chapter_id", nullable = false)
    private Chapter chapter;

//    @OneToMany(mappedBy = "video", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<UserVideoProgress> videoProgresses;
}
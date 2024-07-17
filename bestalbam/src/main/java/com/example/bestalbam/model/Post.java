package com.example.bestalbam.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //画像url
    //★ファイルのアップロード
    //一旦今はStringで。
    @Column(nullable = false)
    private String picture;
    //撮影場所
    @Column(nullable = false)
    private String place;
    //ユーザーネーム
    @Column(nullable = false)
    private String name;
    //コメント
    @Column(nullable = false)
    private String comment;
    //★投稿時間
    @Column
    private LocalDate publishedDate;

    public Post(){
    }

    public Post(Long id, String picture, String place, String name, String comment, LocalDate day) {
        this.id = id;
        this.picture = picture;
        this.place = place;
        this.name = name;
        this.comment = comment;
        this.publishedDate = day;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
    }
}


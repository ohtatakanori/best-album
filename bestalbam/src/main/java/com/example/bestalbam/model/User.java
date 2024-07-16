package com.example.bestalbam.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    // 有効・無効フラグ
    @Column(nullable = false)
    private boolean enabled;

    // 循環参照エラー対策 @JsonManagedReferenceを追加
    // @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Photo> photos = new ArrayList<>();

    public User() {

    }

    public User(Long id, String username, String password, List<Photo> photos) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.photos = photos;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }
    
}


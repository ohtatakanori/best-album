package com.example.bestalbam.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.SQLRestriction;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
// import jakarta.validation.constraints.NotBlank;
// import jakarta.validation.constraints.Size;
// impimport jakarta.validation.constraints.Pattern;

@Entity
// 削除でfalseの場合は消す
@SQLRestriction("is_deleted = false")
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    // @NotBlank(message="ユーザー名の入力は必須です")
    // @Size(min = 1, max = 10, message="ユーザー名は1から10文字までにして下さい")
    private String username;

    @Column(nullable = false)
    // @Pattern(regexp = "^[a-zA-Z0-9\\\\-_.$%/]+$", message="半角英数字と一部の記号（-、_、.）のみしか使えません")
    // @NotBlank(message="パスワードは必須です")
    // @Size(min = 5, message="パスワードは5文字以上にしてください")
    private String password;

    // 削除
    @Column
    private boolean isDeleted = false;

    // 有効・無効フラグ
    @Column(nullable = false)
    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_authority",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "authority_id")
    )
    private Set<Authority> authorities;

    // 循環参照エラー対策 @JsonManagedReferenceを追加
    // @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Photo> photos = new ArrayList<>();


    public User() {

    }

    public User(Long id, String username, String password, boolean isDeleted, boolean enabled,
            Set<Authority> authorities, List<Photo> photos) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.isDeleted = isDeleted;
        this.enabled = enabled;
        this.authorities = authorities;
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
    
}


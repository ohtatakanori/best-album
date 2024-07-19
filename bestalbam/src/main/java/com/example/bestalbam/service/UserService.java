package com.example.bestalbam.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.bestalbam.model.Authority;
import com.example.bestalbam.model.User;
import com.example.bestalbam.repository.AuthorityRepository;
import com.example.bestalbam.repository.UserRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    // 編集
    @Autowired
    private EntityManager entityManager;

    // パスワードのハッシュ化
    public User addEnableStudentAndHashPassword(User user) {
        // 有効化
        user.setEnabled(true);
        // ハッシュ化するクラスの準備
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        // ハッシュ化
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        // パスワードの詰め直し
        user.setPassword(hashedPassword);

        Authority authority = authorityRepository.findByAuthority("ROLE_CONTRIBUTOR")
                .orElseThrow(() -> new EntityNotFoundException("Authority Not Found with name=USER"));

        user.setAuthorities(Set.of(authority));

        return userRepository.save(user);
    }

    // 一覧
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    // 追加
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // 編集
    public User findById(Long id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> user = cq.from(User.class);
        // ここからクエリをビルドする
        cq.select(user);
        cq.where(
                cb.equal(user.get("id"), id));
        // 実行する
        return entityManager.createQuery(cq).getSingleResult();
    }

    // 編集
    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User Not Found With id = " + id));
    }

    // 編集
    @Transactional
    public User update(User updateUser) {
        User user = findUserById(updateUser.getId());
        user.setUsername(updateUser.getUsername());
        return userRepository.save(user);
    }

    // @Transactional
    // public void delete(User deletedUser) {
    // User user = findUserById(deletedUser.getId());
    // userRepository.save(user);
    // }

    // // データ1件を取得する
    // public Optional<User> findByUsername(String username) {
    //     return userRepository.findByUsernameSQL(username);
    // }

    // // 論理削除
    // @Transactional
    // public void delete(User user) {
    //     // idしか受け取っていないのでidでDBから取得する
    //     Optional<User> ListUser = userRepository.findByUsernameSQL(user);
    //     if (ListUser.isPresent()) {
    //         // book(Id=1)が見つかった場合
    //         User user = ListUser.get();
    //         // 削除フラグを立てる
    //         user.setDeleted(true);
    //         // 削除(delete)ではなく保存(update)する
    //         userRepository.save(user);
    //     }
    // }

    // 論理削除
    @Transactional
    public void deleteUserById(Long id) {
        // idしか受け取っていないのでidでDBから取得する
        Optional<User> OptionalUser = userRepository.findById(id);
        if (OptionalUser.isPresent()) {
            // book(Id=1)が見つかった場合
            User user = OptionalUser.get();
            // 削除フラグを立てる
            user.setDeleted(true);
            // 削除(delete)ではなく保存(update)する
            userRepository.save(user);
        }
    }
}

package com.newsgroup.newsfeed.entity;

import com.newsgroup.newsfeed.config.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "users")
public class Users extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Long followNum;

    @Column(nullable = false)
    private Long followingNum;

    @Builder
    public Users(String email, String nickname, String password) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.followNum = 0L;
        this.followingNum = 0L;
    }

    public void updateNickname(String newNickname) {
        this.nickname = newNickname;
    }

    public void increaseFollowCount() {
        this.followNum++;
    }

    public void increaseFollowingCount() {
        this.followingNum++;
    }

    public void decreaseFollowCount() {
        this.followNum--;
    }

    public void decreaseFollowingCount() {
        this.followingNum--;
    }
}
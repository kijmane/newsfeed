package com.newsgroup.newsfeed.entity;

import com.newsgroup.newsfeed.config.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
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
    private String password;

    @Setter
    private Long followNum = 0L;

    @Setter
    private Long followingNum = 0L;

    public Users(String email, String nickname, String password) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
    }

    public void updateNickname(String newNickname) {
        this.nickname = newNickname;
    }

    public void setUserFollow() {
        this.followNum++;
    }

    public void setUserFollowing() {
        this.followingNum++;
    }

    public void setUserUnFollow() {
        this.followNum--;
    }

    public void setUserUnFollowing() {
        this.followingNum--;
    }
}

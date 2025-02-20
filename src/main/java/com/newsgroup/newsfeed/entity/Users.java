package com.newsgroup.newsfeed.entity;

import com.newsgroup.newsfeed.config.BaseEntity;
import com.newsgroup.newsfeed.dto.request.user.UserProfileRequest;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
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

    public Users setUser(UserProfileRequest dto) {
        if (dto.getEmail() != null) {
            this.email = dto.getEmail();
        }
        if (dto.getPassword() != null) {
            this.password = dto.getPassword();
        }
        if (dto.getNewNickname() != null) {
            this.nickname = dto.getNewNickname();
        }
        return this;
    }

    public void updateNickname(String newNickname) {
        this.nickname = newNickname;
    }

    public Long increaseFollowCount() {
        return ++this.followNum;
    }

    public Long increaseFollowingCount() {
        return ++this.followingNum;
    }

    public Long decreaseFollowCount() {
        return --this.followNum;
    }

    public Long decreaseFollowingCount() {
        return --this.followingNum;
    }
}
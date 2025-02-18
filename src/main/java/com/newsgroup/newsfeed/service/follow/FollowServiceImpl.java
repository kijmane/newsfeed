package com.newsgroup.newsfeed.service.follow;

import com.newsgroup.newsfeed.dto.requestDtos.follow.FollowedDto;
import com.newsgroup.newsfeed.entity.Follow;
import com.newsgroup.newsfeed.dto.requestDtos.follow.FollowDto;
import com.newsgroup.newsfeed.repository.FollowRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class FollowServiceImpl implements FollowService {
    private final FollowRepository followRepo;

    /**
     * 내가 팔로우 할 때 로직
     */
    public String follow(FollowDto dto) {
        Follow follow = new Follow(dto);
        dto.getFollowed().setUserFollow();
        followRepo.save(follow);

        String followedUserName = follow.getFollowed().getNickname();
        return followedUserName;
    }

    /**
     * 남이 팔로우 할 때 로직
     */
//    public String followed(FollowedDto dto) {
//        Follow follower = new Follow(dto);

//        followRepo.save(follower);


//    }

    private List<Follow> findAll() {
        return followRepo.findAll();
    }
}

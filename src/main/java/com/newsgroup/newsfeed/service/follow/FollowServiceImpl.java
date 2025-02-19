package com.newsgroup.newsfeed.service.follow;

import com.newsgroup.newsfeed.dto.respondDtos.follow.FollowRespDto;
import com.newsgroup.newsfeed.entity.Follow;
import com.newsgroup.newsfeed.dto.requestDtos.follow.FollowReqDto;
import com.newsgroup.newsfeed.entity.Users;
import com.newsgroup.newsfeed.enums.FollowEnum;
import com.newsgroup.newsfeed.repository.FollowRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
@Transactional
public class FollowServiceImpl implements FollowService {
    private final FollowRepository followRepo;

    /**
     * 팔로우 로직
     */
    @Override
    public FollowRespDto follow(FollowReqDto dto) {
        Follow follow = new Follow(dto);
        followRepo.save(follow);

        return new FollowRespDto(follow);
    }

    /**
     * 검색 로직
     */
    @Override
    public List<Users> searchFollowList(Users targetUser, FollowEnum followEnum) {
        return findAll().stream()
                .map(follow -> followEnum.equals(FollowEnum.followers)
                        ? follow.getFollower()
                        : follow.getFollowed()
                ).filter(users -> targetUser.getId().equals(users.getId()))
                .collect(Collectors.toList());
    }

    /**
     * 언팔로우(삭제) 로직
     */
    @Override
    public String unFollow(Users target, Users unfollowUser) {
        Follow targetFollower = findFollow(target, unfollowUser);
        deleteById(targetFollower.getId());
        return target.getNickname() + "님이 " + unfollowUser.getNickname() + " 님을 언팔로우 하였습니다.";
    }


    @Override
    public Follow findByNickname(String follower, String followed) {
        return findAll().stream()
                .filter(follow -> follow.getFollower().getNickname().equals(follower)
                        && follow.getFollowed().getNickname().equals(followed))
                .findFirst().orElse(null);
    }

    private Follow findFollow(Users follower, Users followed) {
        return findAll().stream()
                .filter(follow -> follow.getFollowed().getId().equals(followed.getId())
                        && follow.getFollower().getId().equals(follower.getId()))
                .findFirst().orElse(null);
    }

    private void deleteById(Long id) {
        followRepo.deleteById(id);
    }

    private List<Follow> findAll() {
        return followRepo.findAll();
    }
}

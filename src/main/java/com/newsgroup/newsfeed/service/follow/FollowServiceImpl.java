package com.newsgroup.newsfeed.service.follow;

import com.newsgroup.newsfeed.entity.Follow;
import com.newsgroup.newsfeed.dto.requestDtos.follow.FollowReqDto;
import com.newsgroup.newsfeed.entity.Users;
import com.newsgroup.newsfeed.enums.FollowEnum;
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
     * 팔로우 로직
     */
    @Override
    public String follow(FollowReqDto dto) {
        Follow follow = new Follow(dto);
        followRepo.save(follow);

        String follower = dto.getFollower().getNickname();
        String followed = dto.getFollowed().getNickname();
        return follower + " 님이 " + followed + " 님을 팔로우 하였습니다.";
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
                .toList();
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

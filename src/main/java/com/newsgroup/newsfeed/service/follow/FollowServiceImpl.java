package com.newsgroup.newsfeed.service.follow;

import com.newsgroup.newsfeed.dto.response.follow.FollowResponse;
import com.newsgroup.newsfeed.dto.response.follow.SearchFollowerResponse;
import com.newsgroup.newsfeed.dto.response.follow.UnFollowResponse;
import com.newsgroup.newsfeed.entity.Follow;
import com.newsgroup.newsfeed.dto.request.follow.FollowRequest;
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
    public FollowResponse follow(FollowRequest dto) {
        Follow follow = new Follow(dto);
        follow.getFollowed().increaseFollowCount();
        follow.getFollower().increaseFollowingCount();
        followRepo.save(follow);

        return new FollowResponse(follow);
    }

    /**
     * 검색 로직
     */
    @Override
    public List<SearchFollowerResponse> searchFollowList(Users targetUser, FollowEnum followEnum) {
        // Users -> SearchFollowerRespDto 변환
        return findAll().stream()
                .map(follow -> followEnum.equals(FollowEnum.followers)
                        ? follow.getFollower()
                        : follow.getFollowed()
                )
                .filter(users -> targetUser.getId().equals(users.getId()))
                .map(SearchFollowerResponse::new) // Users -> SearchFollowerRespDto 변환
                .toList();
    }

    /**
     * 언팔로우(삭제) 로직
     */
    @Override
    public UnFollowResponse unFollow(Users target, Users unfollowUser) {
        Follow targetFollow = findFollow(target, unfollowUser);
        targetFollow.getFollower().decreaseFollowingCount();
        targetFollow.getFollowed().decreaseFollowCount();

        UnFollowResponse unFollowRespDto = new UnFollowResponse(targetFollow);
        deleteById(targetFollow.getId());

        return unFollowRespDto;
    }


//    @Override
//    public Follow findByNickname(String follower, String followed) {
//        return findAll().stream()
//                .filter(follow -> follow.getFollower().getNickname().equals(follower)
//                        && follow.getFollowed().getNickname().equals(followed))
//                .findFirst().orElse(null);
//    }

    private Follow findFollow(Users follower, Users followed) {
        return findAll().stream()
                .filter(follow -> follow.getFollowed().getId().equals(followed.getId())
                        && follow.getFollower().getId().equals(follower.getId()))
                .findFirst().orElse(null);
    }

    private void deleteById(java.lang.Long id) {
        followRepo.deleteById(id);
    }

    private List<Follow> findAll() {
        return followRepo.findAll();
    }
}

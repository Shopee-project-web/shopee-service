package com.shopee.repository;

import com.shopee.entity.User;
import com.shopee.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
//    boolean updateUserProfileById(Long id);
    @Query()
    UserProfile findUserProfileByUser (User user);

    boolean existsUserProfileByUser(User user);
}

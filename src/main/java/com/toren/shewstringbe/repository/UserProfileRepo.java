package com.toren.shewstringbe.repository;

import com.toren.shewstringbe.models.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserProfileRepo extends JpaRepository<UserProfile, UUID> {
}

package com.toren.shewstringbe.service;

import com.toren.shewstringbe.models.UserProfile;
import com.toren.shewstringbe.repository.UserProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserProfileService {

    @Autowired
    UserProfileRepo userProfileRepo;

    public List<UserProfile> getAllUserProfiles() {
        return userProfileRepo.findAll();
    }

    public Optional<UserProfile> getUserProfileById(UUID id) {
        return userProfileRepo.findById(id);
    }

    public UserProfile createUserProfile(UserProfile userProfile) {
        return userProfileRepo.save(userProfile);
    }

    public void deleteUserProfile(UUID id) {
        userProfileRepo.deleteById(id);
    }

    public UserProfile updateUserProfile(UUID id, UserProfile userProfile) {
        return userProfileRepo.findById(id)
                .map( uP -> {
                    uP.setTransactions(userProfile.getTransactions());
                    uP.setCategories(userProfile.getCategories());
                    uP.setBankAccounts(userProfile.getBankAccounts());
                    uP.setFirstName(userProfile.getFirstName());
                    uP.setLastName(userProfile.getLastName());
                    return userProfileRepo.save(uP);
                })
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}

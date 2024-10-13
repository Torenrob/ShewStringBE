package com.toren.shewstringbe.service;

import com.toren.shewstringbe.models.UserProfile;
import com.toren.shewstringbe.repository.UserProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserProfileService implements UserDetailsService {

    private final UserProfileRepo userProfileRepo;

    @Autowired
    public UserProfileService(UserProfileRepo userProfileRepo) {
        this.userProfileRepo = userProfileRepo;
    }

    public List<UserProfile> getAllUserProfiles() {
        return userProfileRepo.findAll();
    }

    public Optional<UserProfile> getUserProfileById(UUID id) {
        return userProfileRepo.findById(id);
    }

    public Optional<UserProfile> getUserProfileByUserName(String username) {
        return userProfileRepo.findUserProfileByUsername(username);
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userProfileRepo.findUserProfileByUsername(username).orElseThrow();
    }
}

package com.toren.shewstringbe.service;

import com.toren.shewstringbe.dto.userdto.UserLoginDto;
import com.toren.shewstringbe.dto.userdto.UserRegisterDto;
import com.toren.shewstringbe.mapper.UserProfileMapper;
import com.toren.shewstringbe.models.UserProfile;
import com.toren.shewstringbe.repository.UserProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserProfileRepo userProfileRepo;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserProfileService userProfileService;
    private final UserProfileMapper userProfileMapper;

    @Autowired
    public AuthenticationService(UserProfileRepo userProfileRepo, PasswordEncoder passwordEncoder,
                                 AuthenticationManager authenticationManager, UserProfileService userProfileService,
                                 UserProfileMapper userProfileMapper) {
        this.userProfileRepo = userProfileRepo;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.userProfileService = userProfileService;
        this.userProfileMapper = userProfileMapper;
    }

    public UserProfile signup(UserRegisterDto userRegisterDto) {
        return userProfileService
                .createUserProfile(userProfileMapper.fromUserRegisterDtoToUserProfile(userRegisterDto));
    }

    public UserProfile loginAuth(UserLoginDto userLoginDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userLoginDto.getUsername(),userLoginDto.getPassword()));

        return userProfileRepo.findUserProfileByUsername(userLoginDto.getUsername()).orElseThrow();
    }
}
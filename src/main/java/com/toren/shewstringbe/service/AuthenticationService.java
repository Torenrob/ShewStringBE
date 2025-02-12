package com.toren.shewstringbe.service;

import com.toren.shewstringbe.dto.userdto.UserLoginDto;
import com.toren.shewstringbe.dto.userdto.UserRegisterDto;
import com.toren.shewstringbe.mapper.UserProfileMapper;
import com.toren.shewstringbe.models.UserProfile;
import com.toren.shewstringbe.repository.UserProfileRepo;
import lombok.extern.slf4j.Slf4j;

import java.util.NoSuchElementException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthenticationService {
    private final UserProfileRepo userProfileRepo;
    private final AuthenticationManager authenticationManager;
    private final UserProfileService userProfileService;
    private final UserProfileMapper userProfileMapper;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(UserProfileRepo userProfileRepo, AuthenticationManager authenticationManager,
                                UserProfileService userProfileService, UserProfileMapper userProfileMapper,
                                PasswordEncoder passwordEncoder) {
        this.userProfileRepo = userProfileRepo;
        this.authenticationManager = authenticationManager;
        this.userProfileService = userProfileService;
        this.userProfileMapper = userProfileMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UserProfile signup(UserRegisterDto userRegisterDto) {
        userRegisterDto.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));
        
        return userProfileService
                .createUserProfile(userProfileMapper.fromUserRegisterDtoToUserProfile(userRegisterDto));
    }

    public UserProfile loginAuth(UserLoginDto userLoginDto) throws NoSuchElementException, AuthenticationException {
        log.info("Attempt to Authenticate User");
        log.info(userLoginDto.getPassword());
        try{
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginDto.getUsername(),userLoginDto.getPassword()));
                log.info("User: " + userLoginDto.getUsername() + " authenticated");
                return userProfileRepo.findUserProfileByUsername(userLoginDto.getUsername()).orElseThrow(() -> new NoSuchElementException("User not found"));
        } catch (AuthenticationException exception) {
            log.info(exception.getMessage());
            throw new BadCredentialsException("User cannot be authenticated");
        }
    }
}

package com.toren.shewstringbe.mapper;

import com.toren.shewstringbe.dto.userdto.UserLoggedInDto;
import com.toren.shewstringbe.dto.userdto.UserRegisterDto;
import com.toren.shewstringbe.models.UserProfile;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserProfileMapper {
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserProfileMapper(ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UserProfile fromUserRegisterDtoToUserProfile(UserRegisterDto userRegisterDto) {
        UserProfile userProfile = modelMapper.map(userRegisterDto, UserProfile.class);

        userProfile.setPassword(passwordEncoder.encode(userProfile.getPassword()));

        return userProfile;
    }

    public UserLoggedInDto fromUserProfileToLoggedInDto(UserProfile userProfile, String token) {
        UserLoggedInDto userLoggedInDto = modelMapper.map(userProfile, UserLoggedInDto.class);

        userLoggedInDto.setToken(token);

        return userLoggedInDto;
    }
}

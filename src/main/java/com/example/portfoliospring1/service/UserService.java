package com.example.portfoliospring1.service;

import com.example.portfoliospring1.config.RedisUtil;
import com.example.portfoliospring1.controller.response.BaseException;
import com.example.portfoliospring1.controller.response.BaseResponseStatusEnum;
import com.example.portfoliospring1.domain.dto.UserDto;
import com.example.portfoliospring1.domain.dto.request.AddUserDto;
import com.example.portfoliospring1.domain.dto.request.CreateInquiryDto;
import com.example.portfoliospring1.domain.entity.Inquiry;
import com.example.portfoliospring1.domain.entity.User;
import com.example.portfoliospring1.repository.InquiryRepository;
import com.example.portfoliospring1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final MailUtil mailUtil;
    private final RedisUtil redisUtil;
    private final InquiryRepository inquiryRepository;

    public UserDto getUser(String nickname) {
        User user = userRepository.findByNickname(nickname);
        if (user == null) {
            return null;
        }

        return new UserDto(user);
    }

    public List<UserDto> getUsers() {
        List<User> users = userRepository.findAll();

        return users.stream().map(user -> new UserDto(user)).collect(Collectors.toList());
//        return users.stream().map(UserDto::new).collect(Collectors.toList());
    }

    public Long addUser(AddUserDto addUserDto) {
        if (!userRepository.findAllByNickname(addUserDto.getNickname()).isEmpty()) {
            throw new BaseException(BaseResponseStatusEnum.DUPLICATED_NICKNAME);
        }

        if (!userRepository.findAllByEmail(addUserDto.getEmail()).isEmpty()) {
            throw new BaseException(BaseResponseStatusEnum.DUPLICATED_EMAIL);
        }

        User user = new User();
        user.setNickname(addUserDto.getNickname());
        user.setEmail(addUserDto.getEmail());
        user.setPassword(addUserDto.getPassword());
        user.setAuthenticated(false);

        userRepository.save(user);

        mailUtil.sendWelcomeEmail(user.getNickname(), user.getEmail());

        return user.getId();
    }

    public String pushInquiry(CreateInquiryDto createInquiryDto) {
        User user = userRepository.findByNickname(createInquiryDto.getNickname());
        Inquiry inquiry = new Inquiry();
        inquiry.setTitle(createInquiryDto.getTitle());
        inquiry.setContent(createInquiryDto.getContent());
        inquiry.setUser(user);

        inquiryRepository.save(inquiry);

        mailUtil.sendInquiryEmail(user.getNickname(), user.getEmail());

        return "success";
    }

    public String createRandomNum() {
        Random random = new Random();
        int randomNumber = random.nextInt(1000000);
        String formatedNumber = String.format("%06d", randomNumber);

        return formatedNumber;
    }

    public String authenticatedByEmail(String nickname) {

        User user = userRepository.findByNickname(nickname);
        if (user == null) {
            return null;
        }

        String code = createRandomNum();
        if (redisUtil.existsData(user.getEmail())) {
            redisUtil.deleteData(user.getEmail());
        }

        mailUtil.sendCodeEmail(user.getNickname(), user.getEmail(), code);
        redisUtil.setDataExpire(user.getEmail(), code, 300);

        return "success";
    }

    public String checkCode(String nickname, String code) {
        User user = userRepository.findByNickname(nickname);
        if (user == null) {
            return null;
        }
        String data = redisUtil.getData(user.getEmail());
        if (data == null) {
            throw new BaseException(BaseResponseStatusEnum.EXPIRED_CODE);
        }
        if (!data.equals(code)) {
            throw new BaseException(BaseResponseStatusEnum.INVALID_CODE);
        }

        user.setAuthenticated(true);

        return "success";
    }
}

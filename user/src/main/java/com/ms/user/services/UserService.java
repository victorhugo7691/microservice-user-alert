package com.ms.user.services;

import com.ms.user.models.UserModel;
import com.ms.user.producers.UserProducer;
import com.ms.user.repositories.IUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class UserService {

    private final IUserRepository userRepository;

    private final UserProducer userProducer;

    public UserService(IUserRepository userRepository, UserProducer userProducer){
        this.userRepository = userRepository;
        this.userProducer = userProducer;
    }

    public UserModel save(UserModel user){
        var userResponse = this.userRepository.save(user);
        this.userProducer.publishMessageEmail(userResponse);

        return userResponse;
    }
}

package com.demo.profiledemo.service;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import javax.persistence.EntityNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.demo.profiledemo.model.User;
import com.demo.profiledemo.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    @NonNull
    private final UserRepository userRepository;

    @GraphQLQuery(name = "userById")
    public User getUserById(@GraphQLArgument(name = "id") Long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("User not exist"));
    }

    @GraphQLMutation(name = "updateUser")
    public User updateUser(@GraphQLArgument(name = "userToUpdate") User userToUpdate) {
        User userInDb = userRepository.findById(userToUpdate.getId())
            .orElseThrow(() -> new EntityNotFoundException("User not exist"));
        BeanUtils.copyProperties(userToUpdate, userInDb);
        return userInDb;
    }
}

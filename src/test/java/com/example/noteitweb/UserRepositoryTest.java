package com.example.noteitweb;

import com.example.noteitweb.entity.User;
import com.example.noteitweb.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;


@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation .class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveUserTest(){
        User user = new User();
        user.setFullName("rimple");
        user.setEmail("shrestha");
        user.setPassword("123456");

        user=userRepository.save(user);

        Assertions.assertThat(user.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void findbyIdTest(){
        User user = new User();
        user.setFullName("rimple");
        user.setEmail("shrestha");
        user.setPassword("123456");

        user=userRepository.save(user);
        System.out.println(user.getId());


        User userGet=userRepository.findById(user.getId()).get();
        Assertions.assertThat(userGet.getId()).isEqualTo(2);
    }
    @Test
    @Order(3)
    public void findAllUserData(){
        List<User> users=userRepository.findAll();
        Assertions.assertThat(users.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    public void updateUser(){
        User user = userRepository.findById(1).get();
        user.setFullName("ri");
        User userUpdated=userRepository.save(user);

        Assertions.assertThat(userUpdated.getFullName()).isEqualTo("ri");
    }

    @Test
    @Order(5)
    public void deleteUser(){
        User user = userRepository.findById(1).get();
        userRepository.delete(user);
        User deletedUser = userRepository.findById(1).orElse(null);
        Assertions.assertThat(deletedUser).isNull();

    }

}

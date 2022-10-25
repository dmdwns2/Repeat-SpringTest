package dao;

import domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {


    UserDao userDao = new UserDao();
    User user1 = new User("1", "kyeonghwan", "1123");
    User user2 = new User("2", "sohyun", "1234");
    User user3 = new User("3", "sujin", "4321");

    //Test Code를 ApplicationContext 사용하게 수정
    @Autowired
    ApplicationContext context;

    @BeforeEach
    void setup() {
        this.userDao = context.getBean("H2UserDao", UserDao.class);
        System.out.println("BeforeEach");
    }

    @Test
    void addAndSelect() throws SQLException, ClassNotFoundException {

        String id = "10";
        userDao.add(new User(id, "RaRa", "1234"));
        User user = userDao.findById(id);
        assertEquals("RaRa", user.getName());
    }

    @Test
    void addAndGet() throws SQLException, ClassNotFoundException {
        userDao.add(user1);
        assertEquals(1, user1.getId());

    }

}
package dao;

import domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes =  DaoFactory.class)
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
        System.out.println("Before Each");
    }

    @Test
    void userNull(){
        assertThrows(EmptyResultDataAccessException.class, () -> {
            userDao.deleteALl();
            userDao.findById("0");
        });
    }
    @Test
    void addAndSelect() throws SQLException{

        String id = "10";
        userDao.add(new User(id, "RaRa", "1234"));
        User user = userDao.findById(id);
        assertEquals("RaRa", user.getName());
    }

    @Test
    void addAndGet() throws SQLException{
        userDao.deleteALl();
        assertEquals(0, userDao.getCount());

        userDao.add(user1);
        assertEquals(1, user1.getId());

        User user = userDao.findById(user1.getId());

        assertEquals(user1.getName(), user.getName());
        assertEquals(user1.getPassword(), user.getPassword());
    }


}
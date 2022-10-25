package dao;

import domain.User;
import dao.UserDao;
import dao.DaoFactory;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.sql.SQLException;
import java.util.List;

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
        this.userDao = context.getBean("h2UserDao", UserDao.class);
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
    @Test
    void count() throws SQLException {
        userDao.deleteALl();
        assertEquals(0, userDao.getCount());

        userDao.add(user1);
        assertEquals(1, userDao.getCount());
        userDao.add(user2);
        assertEquals(2, userDao.getCount());
        userDao.add(user3);
        assertEquals(3, userDao.getCount());

    }
    @Test
    void findById() {
//        jdbc 테플릿 사용으로 인한 변경
        assertThrows(EmptyResultDataAccessException.class, () -> {
            userDao.findById("30");
        });
    }
    @Test
    @DisplayName("없을 때 빈 리스트 리턴 하느지 , 있을 때 개수만큼 리턴 하는지")
    void getAllTest() throws SQLException {
        userDao.deleteALl();
        List<User> user = userDao.getAll();
        assertEquals(0, user.size());
        userDao.add(user1);
        userDao.add(user2);
        userDao.add(user3);
        user = userDao.getAll();
        assertEquals(3, user.size());

    }

}
package se.frost.falldetectionproxyapi.repositories;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import se.frost.falldetectionproxyapi.entities.Contact;
import se.frost.falldetectionproxyapi.entities.User;
import se.frost.falldetectionproxyapi.utils.UserTestUtils;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;


    @Before
    public void setUp() throws Exception {
        System.out.println("Set up run");
        entityManager.persist(UserTestUtils.testUserWithId());
    }

    @Test
    public void whenFindByUserName_thenReturnUser() {
        //When
        User found = userRepository.findByUsername(UserTestUtils.TEST_USER_UNAME);
        //Then
        assertThat(found.getUsername()).isEqualTo(UserTestUtils.TEST_USER_UNAME);
    }

    @Test
    public void name() {
    }

    @Test
    public void whenUpdateUser_thenUpdateUser() {
        // Given
        User willBeUpdated = userRepository.findByUsername(UserTestUtils.TEST_USER_UNAME);

        // When
        willBeUpdated.setFirstName("Olle");
        willBeUpdated.getContacts().add(new Contact("Kalle", "Svensson", "kalle@svensson.se", "0706-257590"));
        willBeUpdated.getContacts().add(new Contact("Bert", "Kalrsson", "bert@karlsson.se", "0706-228833"));
        User updated = userRepository.save(willBeUpdated);

        // THen
        assertThat(updated.getFirstName()).isEqualTo("Olle");
        assertThat(updated.getContacts().size()).isEqualTo(2);
        assertThat(updated.getContacts().get(0).getFirstName()).isEqualTo("Kalle");

        // When
        updated.getContacts().remove(1);
        updated = userRepository.save(updated);

        // Then
        assertThat(updated.getContacts().size()).isEqualTo(1);


    }

    @After
    public void tearDown() throws Exception {
    }

}
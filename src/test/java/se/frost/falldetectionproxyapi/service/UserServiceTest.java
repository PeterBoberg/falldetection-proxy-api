//package se.frost.falldetectionproxyapi.service;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.TestConfiguration;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.test.context.junit4.SpringRunner;
//import se.frost.falldetectionproxyapi.entities.User;
//import se.frost.falldetectionproxyapi.exceptions.ApiException;
//import se.frost.falldetectionproxyapi.repositories.UserRepository;
//import se.frost.falldetectionproxyapi.service.users.UserService;
//import se.frost.falldetectionproxyapi.service.users.UserServiceImpl;
//import se.frost.falldetectionproxyapi.utils.UserTestUtils;
//
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.ArgumentMatchers.any;
//
//@RunWith(SpringRunner.class)
//public class UserServiceTest {
//
//    @TestConfiguration
//    static class UserServiceTestConfig {
//
//        @Bean
//        public UserService userService() {
//            return new UserServiceImpl();
//        }
//    }
//
//    @Autowired
//    private UserService userService;
//
//    @MockBean
//    private UserRepository userRepository;
//
//    @Before
//    public void setUp() throws Exception {
//
//    }
//
//    @Test
//    public void whenSavingUser_thenReturnSavedUser() {
//        // Given
//        User willBeSaved = UserTestUtils.testUserWithId();
//        Mockito.when(userRepository.save(willBeSaved)).thenReturn(willBeSaved);
//
//        // When
//        willBeSaved = userService.createForCurrentUser(willBeSaved);
//
//        // Then
//        assertThat(willBeSaved).isNotNull();
//    }
//
//    @Test
//    public void whenUpdateExistingUser_thenReturnUpdatedUser() {
//        // Given
//        User mockUpdated = UserTestUtils.testUserWithId();
//        mockUpdated.setFirstName("Bert");
//        Mockito.when(userRepository.existsById(mockUpdated.getId())).thenReturn(true);
//        Mockito.when(userRepository.save(any(User.class))).thenReturn(mockUpdated);
//
//        // When
//        User willBeUpdated = UserTestUtils.testUserWithId();
//        willBeUpdated.setFirstName("Bert");
//        willBeUpdated = userService.updateForCurrentUser(willBeUpdated);
//
//        // Then
//        assertThat(willBeUpdated).isNotNull();
//        assertThat(willBeUpdated.getFirstName()).isEqualTo("Bert");
//    }
//
//    @Test(expected = ApiException.class)
//    public void whenUpdatingNonExistingUser_thenThrowException() {
//        // Given
//        User nonExistigUser = UserTestUtils.testUserWithoutId();
//        nonExistigUser.setFirstName("Olle");
//
//        // When
//        User expected = userService.updateForCurrentUser(nonExistigUser);
//    }
//
//    @Test
//    public void whenValidId_thenUserShouldBeFound() {
//        // Given
//        User savedUser = UserTestUtils.testUserWithId();
//        Mockito.when(userRepository.findById(savedUser.getId())).thenReturn(Optional.of(savedUser));
//
//        // When
//        User found = userService.getForCurrentUser();
//
//        // Then
//        assertThat(found).isNotNull();
//        assertThat(found.getFirstName()).isEqualTo(UserTestUtils.TEST_USER_FIRST_NAME);
//    }
//
//    @Test(expected = ApiException.class)
//    public void whenInvalidId_thenThrowException() {
//        // Given
//        long idOfNonExistingUser = 1000;
//
//        // When
//        userService.getForCurrentUser();
//    }
//
//    @Test(expected = ApiException.class)
//    public void whenDeletingNonExistingUser_thenThrowException() {
//        // Given
//        long idOfNonExistingUser = 1000;
//
//        // When
//        userService.deleteForCurrentUser();
//    }
//
//    @After
//    public void tearDown() throws Exception {
//    }
//}
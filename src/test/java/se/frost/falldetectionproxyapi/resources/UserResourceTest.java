package se.frost.falldetectionproxyapi.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import se.frost.falldetectionproxyapi.entities.User;
import se.frost.falldetectionproxyapi.service.UserServiceImpl;
import se.frost.falldetectionproxyapi.utils.UserTestUtils;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserResource.class)
@AutoConfigureMockMvc
public class UserResourceTest {

    @TestConfiguration
    static class UserResourceTestConfig {

        @Bean
        public ObjectMapper objectMapper() {
            return new ObjectMapper();
        }

    }

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserServiceImpl userService;


    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void whenUserIsCreated_thenReturnUserWithIdSet() throws Exception {
        // Given
        given(userService.create(any(User.class))).willReturn(UserTestUtils.TEST_USER_WITH_ID);
        // When
        mvc.perform(
                post("/users")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .characterEncoding("utf-8")
                        .content(convertToJson(UserTestUtils.TEST_USER_WITHOUT_ID)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is(UserTestUtils.TEST_USER_WITH_ID.getFirstName())));

    }

    private byte[] convertToJson(User user) throws JsonProcessingException {
        return objectMapper.writeValueAsBytes(user);
    }

    @Test
    public void getUserById() {
    }

    @Test
    public void updateUser() {
    }

    @Test
    public void deleteUser() {
    }

    @Test
    public void setUserService() {
    }
}
package hh.sof03.bookstore;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest
@AutoConfigureMockMvc
public class WebLayerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testHomePage() throws Exception {
        this.mockMvc.perform(get("/"))
            .andDo(print())
            .andExpect(status().isFound())
            .andExpect(redirectedUrlPattern("**/login"))  // 
            .andDo(result -> {
                mockMvc.perform(get("/login"))
                       .andExpect(status().isOk())
                       .andExpect(content().string(containsString("Book Store")));
            });
    }

    @Test
    public void testLoginAndCategoryListPage() throws Exception {
        this.mockMvc.perform(get("/categorylist"))
                .andDo(print())
                .andExpect(status().isFound())  
                .andExpect(redirectedUrlPattern("**/login"))
                .andDo(result -> {
                    mockMvc.perform(post("/login")
                            .param("username", "user")
                            .param("password", "user")
                            .with(csrf()))
                            .andExpect(status().is3xxRedirection()) 
                            .andExpect(redirectedUrl("/booklist"));
                            }).andDo(result2 -> {
                                mockMvc.perform(get("/categorylist").with(user("user").roles("USER")))
                                       .andDo(print())
                                       .andExpect(status().isOk())
                                       .andExpect(content().string(containsString("Categories")));
                            });
    }

    @Test
    public void testLoginAndBookListPage() throws Exception {
        this.mockMvc.perform(get("/booklist"))
                .andDo(print())
                .andExpect(status().isFound())  
                .andExpect(redirectedUrlPattern("**/login"))
                .andDo(result -> {
                    mockMvc.perform(post("/login")
                            .param("username", "user")
                            .param("password", "user")
                            .with(csrf()))
                            .andExpect(status().is3xxRedirection())
                            .andExpect(redirectedUrl("/booklist"))
                            .andDo(result2 -> {
                                mockMvc.perform(get("/booklist").with(user("user").roles("USER")))
                                       .andDo(print())
                                       .andExpect(status().isOk())
                                       .andExpect(content().string(containsString("Books")));
                            });
                });                       
    }

    @Test
    public void testLoginPage() throws Exception {
        this.mockMvc.perform(get("/login"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("User Name")));  // Verifying that the login page has input for "User Name"
    }

    @Test
    public void testSignupPage() throws Exception {
        this.mockMvc.perform(get("/signup"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Sign-up")));  // Verifying that the signup page includes the "Sign-up" header
    }
}

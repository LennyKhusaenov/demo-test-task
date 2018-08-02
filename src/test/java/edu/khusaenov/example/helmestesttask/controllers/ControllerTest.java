package edu.khusaenov.example.helmestesttask.controllers;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import edu.khusaenov.example.helmestesttask.HelmesTestTaskApplication;
import edu.khusaenov.example.helmestesttask.model.User;
import edu.khusaenov.example.helmestesttask.repository.SectorRepository;
import edu.khusaenov.example.helmestesttask.repository.UserRepository;
import edu.khusaenov.example.helmestesttask.utils.DataCreator;
import java.util.Random;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * @author Khusaenov on 25.07.2018
 */
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@SpringBootTest(classes = HelmesTestTaskApplication.class)
@ActiveProfiles("test")
@TestPropertySource(value = "classpath:application-test.yml")
public class ControllerTest {

    private static final String HOME_URL = "/";
    private static final String HOME_HTML_NAME = "index";
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SectorRepository sectorRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void checkHomeHtmlReturnedAfterGetRequest() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name(HOME_HTML_NAME));
    }

    @Test
    public void checkResponseContainsAddedInRepoSectorValue() throws Exception {
        String testSectorId = getTestSectorId();
        saveSectorInRepo(testSectorId);
        mockMvc.perform(get(HOME_URL)).andExpect(status().isOk()).andExpect(view().name(
                HOME_HTML_NAME)).andExpect(content().string(containsString(testSectorId)));
    }


//    @Test
//    public void checkUserAddedToRepositoryAfterPostRequest() throws Exception {
//        String testSectorId = getTestSectorId();
//        saveSectorInRepo(testSectorId);
//        User user = new DataCreator().createUser(testSectorId);
//        mockMvc.perform(post(HOME_URL).param("name", user.getName())
//                .param("agreement", String.valueOf(user.getAgreement()))
//                .param("sectors", String.valueOf(user.getSectors().(0).getSectorId())))
//                .andExpect(status().is3xxRedirection());
//        Assert.assertEquals(user.getName(),
//                userRepository.findUserBySectorLike(user.getSectors().get(0)).orElse(new User()).getName());
//    }

    private String getTestSectorId() {
        return String.valueOf(new Random().nextInt(10));
    }

    private void saveSectorInRepo(String testSectorId) {
        sectorRepository.save(new DataCreator().createSector(testSectorId));
    }
}

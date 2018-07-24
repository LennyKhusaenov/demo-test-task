package edu.khusaenov.example.helmestesttask.controllers;


import edu.khusaenov.example.helmestesttask.model.Sector;
import edu.khusaenov.example.helmestesttask.model.User;
import edu.khusaenov.example.helmestesttask.repository.UserRepository;
import edu.khusaenov.example.helmestesttask.service.SectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Khusaenov on 20.07.2018
 */
@Controller
@RequestMapping("/")
public class UserController {

    private final UserRepository userRepository;
    private final SectorService sectorService;

    @Autowired
    public UserController(
            UserRepository userRepository,
            SectorService sectorService) {
        this.userRepository = userRepository;
        this.sectorService = sectorService;
    }

    @GetMapping(/*produces = MediaType.APPLICATION_JSON_VALUE*/)
    public String getUserInformation(Model model) {
        model.addAttribute("sectors", sectorService.getRecursiveSector());
        model.addAttribute("user", new User());
        return "index";
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String addNewUser(@ModelAttribute String user/*, @RequestHeader String sessionId*/) {
//        Optional<User> userFromDataBase = userRepository.findUserBySessionId(sessionId);
//        user.setSessionId(sessionId);
//        return userFromDataBase.map(user1 -> updateUserInformation(user, user1))
//                .orElseGet(() -> userRepository.save(user));
        return "index";
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    private User updateUserInformation(User user, User userFromDatabase) {

        userFromDatabase.setAgreement(user.getAgreement());
        userFromDatabase.setName(user.getName());
        userFromDatabase.setSectorId(user.getSectorId());
        return userRepository.save(userFromDatabase);
    }

}

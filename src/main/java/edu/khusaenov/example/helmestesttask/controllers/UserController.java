package edu.khusaenov.example.helmestesttask.controllers;


import edu.khusaenov.example.helmestesttask.model.User;
import edu.khusaenov.example.helmestesttask.repository.UserRepository;
import edu.khusaenov.example.helmestesttask.service.SectorService;
import javax.servlet.http.HttpSession;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@Slf4j
@Controller
@RequestMapping("/")
public class UserController {

    private static final String LOG_MESSAGE = "User with id = {}";
    private final UserRepository userRepository;
    private final SectorService sectorService;

    @Autowired
    public UserController(
            UserRepository userRepository,
            SectorService sectorService) {
        this.userRepository = userRepository;
        this.sectorService = sectorService;
    }

    @GetMapping
    public String getUserAndSectorInformation(Model model, @NonNull HttpSession httpSession) {
        User userFromDatabase = userRepository.findUserBySessionId(httpSession.getId())
                .orElse(new User());
        model.addAttribute("sectors", sectorService.getRecursiveSector());
        model.addAttribute("user", userFromDatabase);
        return "index";
    }

    @PostMapping
    public String addNewUser(@ModelAttribute("user") @NonNull User user,
            @NonNull HttpSession httpSession) {
        user.setSessionId(httpSession.getId());
        try {
            if (httpSession.isNew()) {
                userRepository.save(user);
                log.debug(LOG_MESSAGE + " was successfully saved", user.getSessionId());
            } else {
                updateUserInformation(user);
                log.debug(LOG_MESSAGE + " was updated", user.getSessionId());
            }
        } catch (Exception e) {
            log.error(LOG_MESSAGE + "was rejected", user.getSessionId());
        }
        return "redirect:/";
    }

    @PutMapping
    private User updateUserInformation(User user) {
        User update = user.copy();
        update.setAgreement(user.getAgreement());
        update.setName(user.getName());
        update.setSector(user.getSector());
        return userRepository.save(update);
    }

}

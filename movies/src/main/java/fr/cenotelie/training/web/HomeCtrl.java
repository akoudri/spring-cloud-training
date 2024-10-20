package fr.cenotelie.training.web;

import fr.cenotelie.training.dao.MessagesDAO;
import fr.cenotelie.training.dto.Login;
import fr.cenotelie.training.dto.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/")
public class HomeCtrl {

    @Autowired
    private MessagesDAO dao;

    @GetMapping
    public String index(ModelMap model) {
        model.addAttribute("messages", dao.getMessages());
        return "index";
    }

    @PostMapping
    public String createMessage(@ModelAttribute Message message) {
        log.info("adding new message with title " + message.getTitle());
        dao.addMessage(message);
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(@SessionAttribute(name="login", required = false) Login login, Model model) {
        return "forward:/";
    }
}

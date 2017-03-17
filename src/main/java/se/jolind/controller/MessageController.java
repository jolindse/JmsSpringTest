package se.jolind.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import se.jolind.domain.MessageInfo;
import se.jolind.domain.PostMessage;
import se.jolind.handlers.Producer;
import se.jolind.service.MessageService;

import java.util.List;

/**
 * Created by juan on 2017-03-14.
 */

@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private Producer producer;

    @ModelAttribute(name="messages")
    public List<MessageInfo> getMessages() {
        return messageService.getMessages();
    }

    @ModelAttribute(name="numMessages")
    public int getMessagesSize() {
        return messageService.getNumMessage();
    }

    @GetMapping(value = {"/","/message"})
    public String messageForm(Model model) {
        model.addAttribute("postMessage", new PostMessage());
        return "postMessage";
    }

    @PostMapping(value = "/message")
    public String sendMessage(@ModelAttribute PostMessage msg) {
        System.out.println("Should send text.");
        System.out.println(msg);
        producer.send(msg.getText());
        return "postMessage";
    }

}

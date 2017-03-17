package se.jolind.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;
import se.jolind.domain.MessageInfo;
import se.jolind.service.MessageService;

import javax.jms.JMSException;
import javax.jms.Message;

/**
 * Created by juan on 2017-03-14.
 */

@Component
public class Receiver {

    @Autowired
    private MessageService messageService;

    @JmsListener(destination = "TESTTOPIC", containerFactory = "myFactory")
    public void receiveMessage(Message msg) {
        MessageInfo currMsg = new MessageInfo(msg);
        messageService.addMessage(currMsg);
        System.out.println(currMsg);
        try {
            msg.acknowledge();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}

package se.jolind.service;

import org.springframework.stereotype.Service;
import se.jolind.domain.MessageInfo;

import java.util.ArrayList;

import java.util.Collections;
import java.util.List;

/**
 * Created by juan on 2017-03-15.
 */

@Service
public class MessageService {

    private List<MessageInfo> messageList = new ArrayList<>();

    public void addMessage(MessageInfo messageInfo) {
        this.messageList.add(messageInfo);
    }

    public List<MessageInfo> getMessages() {
        ArrayList<MessageInfo> shallowCopy = new ArrayList<>(messageList);
        Collections.reverse(shallowCopy);
        return shallowCopy;
    }

    public int getNumMessage() {
        return messageList.size();
    }

}

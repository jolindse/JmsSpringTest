package se.jolind.domain;

import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Created by juan on 2017-03-15.
 */

public class PostMessage {

    private String text;

    public PostMessage() {
    }

    public PostMessage(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "PostMessage{" +
                "text='" + text + '\'' +
                '}';
    }
}

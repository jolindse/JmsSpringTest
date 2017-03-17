package se.jolind.domain;


import org.apache.activemq.command.ActiveMQMessage;
import org.apache.activemq.command.ActiveMQTextMessage;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * Created by juan on 2017-03-15.
 */

public class MessageInfo {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    private String msgText, timeStamp, brokerIn, brokerOut;
    private Message msg;

    public MessageInfo(Message msg) {
        this.msg = msg;
        extractInfo();
    }

    public String getMsgText() {
        return msgText;
    }

    public Message getMsg() {
        return msg;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public String getBrokerIn() {
        return brokerIn;
    }

    public String getBrokerOut() {
        return brokerOut;
    }

    private void extractInfo() {
        try {
            this.timeStamp = getTime("timestamp");
            this.brokerIn = getTime("brokerInTime");
            this.brokerOut = getTime("brokerOutTime");
            if (this.msg instanceof ActiveMQTextMessage) {
                TextMessage textMessage = (TextMessage)this.msg;
                this.msgText = textMessage.getText();
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    private String getTime(String property) {
        Long currValue = 0L;
            ActiveMQMessage activeMQMessage = (ActiveMQMessage)msg;
            switch(property) {
                case "timestamp":
                    currValue = activeMQMessage.getJMSTimestamp();
                    break;
                case "brokerInTime":
                    currValue = activeMQMessage.getBrokerInTime();
                    break;
                case "brokerOutTime":
                    currValue = activeMQMessage.getBrokerOutTime();
            }
            return Instant.ofEpochMilli(currValue)
                    .atZone(ZoneId.systemDefault())
                    .format(formatter);

    }
}

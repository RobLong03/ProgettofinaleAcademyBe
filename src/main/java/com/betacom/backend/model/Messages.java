package com.betacom.backend.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="system_messages")
public class Messages {
    @EmbeddedId
    private MessageID msgID;

    public String message;

    public MessageID getMsgID() {
        return msgID;
    }

    public void setMsgID(MessageID msgID) {
        this.msgID = msgID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }




}

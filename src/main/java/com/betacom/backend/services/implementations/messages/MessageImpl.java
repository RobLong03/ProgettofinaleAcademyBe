package com.betacom.backend.services.implementations.messages;

import com.betacom.backend.model.messages.MessageID;
import com.betacom.backend.model.messages.Messages;
import com.betacom.backend.repositories.messages.IMessageRepository;
import com.betacom.backend.services.interfaces.messages.MessageServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MessageImpl implements MessageServices {
    @Value("${langMsg}")
    String langMsg;

    @Autowired
    IMessageRepository msgR;

    @Override
    public String getMessage(String code) {
        Optional<Messages> msg = msgR.findById( new MessageID(langMsg,code) );

        String res = null;

        if(msg.isEmpty())
            res = code;
        else
            res = msg.get().getMessage();

        return res;

    }
}

package com.betacom.backend.services.implementations;

import com.betacom.backend.model.MessageID;
import com.betacom.backend.model.Messages;
import com.betacom.backend.repositories.IMessageRepository;
import com.betacom.backend.services.interfaces.MessageServices;
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

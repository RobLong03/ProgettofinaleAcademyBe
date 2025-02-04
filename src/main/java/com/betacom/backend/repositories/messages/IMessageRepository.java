package com.betacom.backend.repositories.messages;

import com.betacom.backend.model.messages.MessageID;
import com.betacom.backend.model.messages.Messages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMessageRepository extends JpaRepository<Messages, MessageID> {
}

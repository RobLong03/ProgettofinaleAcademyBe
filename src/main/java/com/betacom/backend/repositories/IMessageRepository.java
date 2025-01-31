package com.betacom.backend.repositories;

import com.betacom.backend.model.MessageID;
import org.aspectj.bridge.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMessageRepository extends JpaRepository<Message, MessageID> {
}

package com.betacom.backend.repositories;

import com.betacom.backend.model.MessageID;
import com.betacom.backend.model.Messages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMessageRepository extends JpaRepository<Messages, MessageID> {
}

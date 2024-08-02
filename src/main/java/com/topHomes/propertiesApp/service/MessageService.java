package com.topHomes.propertiesApp.service;

import com.topHomes.propertiesApp.model.dto.CreateMessageDTO;
import com.topHomes.propertiesApp.model.dto.MessageDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MessageService {

    List<MessageDTO> getAllMessages();

    void createMessage(CreateMessageDTO createMessageDTO);

    MessageDTO getMessageById(Long id);

    void deleteMessageById(Long id);
}

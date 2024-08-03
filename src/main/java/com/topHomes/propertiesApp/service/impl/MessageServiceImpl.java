package com.topHomes.propertiesApp.service.impl;

import com.topHomes.propertiesApp.model.dto.CreateMessageDTO;
import com.topHomes.propertiesApp.model.dto.MessageDTO;
import com.topHomes.propertiesApp.service.MessageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.logging.Logger;

@Service
public class MessageServiceImpl implements MessageService {

    private final RestClient messageRestClient;
    private Logger LOGGER = Logger.getLogger(MessageServiceImpl.class.getName());


    public MessageServiceImpl(RestClient messageRestClient) {
        this.messageRestClient = messageRestClient;
    }

    @Override
    public List<MessageDTO> getAllMessages() {

       return messageRestClient
               .get()
               .uri("http://localhost:8081/admin/messages")
               .accept(MediaType.APPLICATION_JSON)
               .retrieve()
               .body(new ParameterizedTypeReference<>() {});
    }

    @Override
    public void createMessage(CreateMessageDTO createMessageDTO) {

        messageRestClient
                .post()
                .uri("http://localhost:8081/admin/messages")
                .body(createMessageDTO)
                .retrieve();
    }

    @Override
    public MessageDTO getMessageById(Long id) {
        return messageRestClient
                .get()
                .uri("http://localhost:8081/admin/messages/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(MessageDTO.class);
    }

    @Override
    public void deleteMessageById(Long id) {
        messageRestClient
                .delete()
                .uri("http://localhost:8081/admin/messages/{id}", id)
                .retrieve();
    }
}

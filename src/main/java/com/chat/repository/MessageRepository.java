package com.chat.repository;

import com.chat.model.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Long> {

    Message findById(long id);

    List<Message> findByTag(String tag);
}

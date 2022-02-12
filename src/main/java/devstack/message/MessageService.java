package devstack.message;

import devstack.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    public Message saveMessage(String username, MessageRequest messageRequest) {

        Message message = new Message();
        message.setAuthor(userRepository.getApplicationUserByUsername(username));
        message.setMessageText(messageRequest.getMessageText());
        message.setTag(messageRequest.getMessageTag());
        message.setDateTime(LocalDateTime.now());

        return messageRepository.save(message);
    }

    public List<Message> getMessages() {
        var messages = messageRepository.findAll();
        Collections.reverse(messages);
        return messages;
    }
}

package devstack.message;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("message")
@AllArgsConstructor
public class ChatController {

    private final MessageService messageService;

    @PostMapping
    public ResponseEntity<Message> postMessage(Principal principal, @RequestBody MessageRequest messageRequest) {

        Message message = messageService.saveMessage(principal.getName(), messageRequest);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity <List<Message>> getMessages() {
        return new ResponseEntity<>(messageService.getMessages(), HttpStatus.OK);
    }
}

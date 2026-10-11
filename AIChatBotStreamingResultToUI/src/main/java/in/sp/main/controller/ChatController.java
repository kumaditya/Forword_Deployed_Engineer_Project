package in.sp.main.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.sp.main.service.ChatService;
import reactor.core.publisher.Flux;

@RestController
public class ChatController {

	private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/chat")
    public Flux<String> chat(@RequestBody String message) {
        return chatService.chat(message);
    }
	
}

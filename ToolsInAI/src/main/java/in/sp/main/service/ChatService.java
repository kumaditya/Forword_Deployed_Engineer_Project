package in.sp.main.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.stereotype.Service;

import in.sp.main.tools.CalculatorTools;

@Service
public class ChatService {

	private ChatClient chatClient;

	private CalculatorTools calculatorTools;

	List<Message> history = new ArrayList<>();

	public ChatService(ChatClient.Builder builder, CalculatorTools calculatorTools) {
		this.chatClient = builder.build();
		this.calculatorTools = calculatorTools;

	}

	private static final String SYSTEM_PROMPT = """
			You are a helpful AI assistant with access to external tools.

			Follow these rules:
			1. For arithmetic calculations, ALWAYS use the calculator tool.
			2. Always use calculator tool for even trivial calculation
			3. After receiving tool results, explain the answer naturally.
			4. Do  not answer any other Questions whihc is not related to calculatiuons
			5. if questions not related to calculations then simple answer please ask questions related to only add subtract , multiply , mod , and divide

			""";

	public String chatWithLLm(String userMessage) {

		history.add(new UserMessage(userMessage));

		String response = chatClient.prompt().messages(history).system(SYSTEM_PROMPT).tools(calculatorTools).call()
				.content();

		history.add(new AssistantMessage(response));

		return response;

	}

}

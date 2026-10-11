package in.sp.main.controller.service;

import org.springframework.stereotype.Service;

import io.modelcontextprotocol.client.McpSyncClient;
import io.modelcontextprotocol.spec.McpSchema;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

	private final ChatClient chatClient;
	private final McpSyncClient mcpClient;

	public ChatService(ChatClient.Builder builder, ToolCallbackProvider mcpTools, List<McpSyncClient> mcpClients) {

		this.chatClient = builder.defaultTools(mcpTools).build();
		this.mcpClient = mcpClients.get(0);
	}

	public String chat(String message) {

		String guidelines = readTaskGuidelines();

		String systemPromot = """
				        You are a task management assistant.

				        Use the following task management guidelines
				        whenever they are relevant to the user's question.

				        TASK GUIDELINES:

				        %s
				""".formatted(guidelines);

		return chatClient.prompt().system(systemPromot).user(message).call().content();
	}

	// For Resources
	private String readTaskGuidelines() {

		McpSchema.ReadResourceResult result = mcpClient
				.readResource(McpSchema.ReadResourceRequest.builder("task://guidelines").build());

		McpSchema.TextResourceContents content = (McpSchema.TextResourceContents) result.contents().get(0);

		return content.text();
	}

	public String planDay(String hours) {

		McpSchema.GetPromptResult result = mcpClient.getPrompt(
				McpSchema.GetPromptRequest.builder("plan_day").arguments(Map.of("availableHours", hours)).build());

		McpSchema.TextContent content = (McpSchema.TextContent) result.messages().get(0).content();

		String generatedPrompt = content.text();

		return chatClient.prompt().user(generatedPrompt).call().content();
	}
}
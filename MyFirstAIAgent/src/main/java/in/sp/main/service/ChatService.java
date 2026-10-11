package in.sp.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.genai.Client;
import com.google.genai.gaos.models.interactions.CreateModelInteraction;
import com.google.genai.gaos.models.interactions.Interaction;
import com.google.genai.gaos.models.interactions.InteractionsInput;
import com.google.genai.gaos.models.interactions.Model;
import com.google.genai.gaos.models.operations.CreateInteractionRequestBody;

@Service
public class ChatService {

	private final Client client;

	ChatService(Client.Builder builder) {
		this.client = builder.build();
	}

	public String chatWithModel(String query) {
		CreateModelInteraction params = CreateModelInteraction.builder().model(Model.of("gemini-3.5-flash"))
				.input(InteractionsInput.of(query)).build();

		Interaction interaction = client.interactions.create(CreateInteractionRequestBody.of(params)).interaction()
				.get();

		System.out.println(interaction.toString());

		return null;
	}

}

package in.sp.main;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.ai.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Component;

@Component
public class TaskToolsService {

	List<String> taskList = new ArrayList<>();

	@McpTool(name = "Create_Task", description = "Will Create New Task")
	public String createTask(@McpToolParam(description = "Tittle of New Task", required = true) String taskTittle) {

		taskList.add(taskTittle);

		return "Task Created With the Task Tttle Name " + taskTittle;

	}

	@McpTool(name = "taskList", description = "get List of All Pending Task")
	public List<String> listTasks() {

		return List.copyOf(taskList);
	}

	@McpTool(name = "compete_Task", description = "exactb tittle of the task")
	public String completeTask(
			@McpToolParam(description = "Correct Tittle of the Task for Completion", required = true) String tittle) {
		boolean removed = taskList.remove(tittle);

		if (removed)
			return "Task Completed Sucessfully" + tittle;

		return "Task Not found" + tittle;

	}

}

package in.sp.main.tools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

@Component
public class CalculatorTools {

	@Tool(description = "this method only supports operation like add , subtract , multiply , mod , pow , divide")
	public double calculate(
			@ToolParam(description = "operation possible : add , subtract , multiply , divide , mod , pow") String operation,
			@ToolParam(description = "for any operation this is first parameter") double a,
			@ToolParam(description = "for any operation this is the second parameter") double b) {

		if (operation.equals("add")) {
			return a + b;
		} else if (operation.equals("subtract")) {
			return a - b;
		} else if (operation.equals("divide")) {
			if (b == 0) {
				throw new IllegalArgumentException("Cannot divide by 0");
			}
			return a / b;
		} else if (operation.equals("multiply")) {
			return a * b;
		} else if (operation.equals("mod")) {
			if (b == 0) {
				throw new IllegalArgumentException("Cannot calculate mod by 0");
			}
		} else if (operation.equals("power")) {
			return Math.pow(a, b);
		} else {
			throw new IllegalArgumentException("Unsupported operation " + operation);
		}
		return 0;

	}

}

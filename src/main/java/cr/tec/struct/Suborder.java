package cr.tec.struct;

import java.util.LinkedList;

/**
 * Created by joseph on 11/3/16.
 */
public class Suborder {
	private int dishId;
	private String comment;
	private int quantity;
	private boolean completed;
	private LinkedList<StepStatus> steps = new LinkedList<>();
	private int chef;
	private String name;

	public Suborder() {
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getDishId() {
		return dishId;
	}

	public void setDishId(int dishId) {
		this.dishId = dishId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public LinkedList<StepStatus> getSteps() {
		return steps;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public int getChef() {
		return chef;
	}

	public void setChef(int chef) {
		this.chef = chef;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

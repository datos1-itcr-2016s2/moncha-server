package cr.tec.struct;

import cr.tec.struct.generic.Node;
import cr.tec.utils.Database;
import cr.tec.utils.sort.BinarySearch;

import javax.xml.crypto.Data;
import java.util.LinkedList;

/**
 * Created by joseph on 11/4/16.
 * Class that makes all the work related to the orders
 */
public class OrderManager {
<<<<<<< HEAD
/**
 * Creates a new order that includes all the suborders
 * @param user client that asked for the order
 * @param suborders parts of the main order of the table
 * @return
 */
=======
	private static OrdersPriorityQueue queue = new OrdersPriorityQueue();

>>>>>>> e0253004e1fd56608315dfacb02b85519d883479
	public static Message placeOrder(User user, LinkedList<Suborder> suborders) {
		int tableId = user.getTable();
		OrderType type = user.getType();

		if (tableId <= 0 || suborders == null || suborders.size() == 0) {
			return new Message("error", "Invalid data");
		}
		Double price = 0.0;

		Table table = TableList.get(tableId);
		for (Suborder sub : suborders) {

			price += Database.getDish(sub.getDishId()).getPrice() * sub.getQuantity();
			Dish dish = Database.getDish(sub.getDishId());
			if (dish == null) {
				return new Message("error", "The order has an invalid dish.");
			}

			for (RecipeStep step : dish.getRecipe()) {
				sub.getSteps().add(new StepStatus(step));
			}
		}

		Order order = new Order();


		int i = 0;
		for (Suborder sub : suborders) {
			i++;
			User chef = getAvailableChef();
			if (chef == null) {
				return new Message("error", "There are no chefs in the restaurant :(");
			}
			sub.setChef(chef.getId());
			chef.getCurrentWorks().add(new Work(order.getId(), i));

			sub.setName(Database.getDish(sub.getDishId()).getName());
		}

		order.setSuborders(suborders);
		order.setTable(tableId);
		order.setPrice(price);
		order.setType(type);
		order.setUser(user.getId());
		table.getOrders().add(order);
		//queue.addLast(order);


		return new Message("ok", "Order added succesfully.");

	}

	public static Message addSuborder(int order, Suborder sub) {
		Dish dish = Database.getDish(sub.getDishId());
		if (dish == null) {
			return new Message("error", "The order has an invalid dish.");
		}

		User chef = getAvailableChef();
		Order orderData = getOrder(order);
		if (chef == null) {
			return new Message("error", "There are no chefs in the restaurant :(");
		}
		sub.setChef(chef.getId());
		chef.getCurrentWorks().add(new Work(order, orderData.getSuborders().size() + 1));
		sub.setName(Database.getDish(sub.getDishId()).getName());


		orderData.setPrice(orderData.getPrice() + dish.getPrice() * sub.getQuantity());

		for (RecipeStep step : dish.getRecipe()) {
			sub.getSteps().add(new StepStatus(step));
		}

		orderData.getSuborders().add(sub);


		return new Message("ok", "Order updated");

	}


/**
 * 
 * @return all the orders that are available
 */
	public static LinkedList<Order> getAllOrders() {
		LinkedList<Order> orderList = new LinkedList<>();

		for (OrderType type : OrderType.values()) {
			Node<Table> actual = TableList.getHead();
			while (actual != null) {
				orderList.addAll(actual.getData().getOrders(type));
				actual = actual.getNext();
			}
		}

		return orderList;
	}
/**
 * 
 * @param id identification of the order
 * @return the specific order that has been requested
 */
	public static Order getOrder(int id) {
		LinkedList<Order> orders = getAllOrders();
		for (Order order : orders) {
			if (order.getId() == id) {
				return order;
			}
		}
		return null;

	}

	private static User getAvailableChef() {
		int current = 999999;
		User currentChef = null;
		for (User user : UserList.getList()) {
			if (user.getRole() != Role.CHEF) {
				continue;
			}

			if (user.getCurrentWorks().size() < current) {
				current = user.getCurrentWorks().size();
				currentChef = user;
			}
		}

		return currentChef;
	}

}

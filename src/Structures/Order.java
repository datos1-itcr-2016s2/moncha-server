package Structures;

import java.io.Serializable;

import Food.Dish;

public class Order implements Serializable, Comparable<Order>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2524583301959818938L;
	private OrderType orderPriority;
	private Table table;
	private GenericList<Dish> dishes;
	private long  orderTime;
	
	
	public Order(OrderType orderPriority, Table table, GenericList<Dish> dishes, long orderTime){
		this.orderPriority = orderPriority;
		this.dishes = dishes;
		this.table = table;
		this.orderTime = orderTime;
	}
	
	public Order() {
		super();
	}
	
	public OrderType getOrderPriority() {
		return orderPriority;
	}


	public void setOrderPriority(OrderType orderPriority) {
		this.orderPriority = orderPriority;
	}


	public Table getTable() {
		return table;
	}


	public void setTable(Table table) {
		this.table = table;
	}


	public GenericList<Dish> getDishes() {
		return dishes;
	}


	public void setDishes(GenericList<Dish> dishes) {
		this.dishes = dishes;
	}


	public long getOrderTime() {
		return orderTime;
	}


	public void setOrderTime(long orderTime) {
		this.orderTime = orderTime;
	}
	
	@Override
	public String toString(){
		String result = "";
		for(int i=0; i<this.getDishes().getLenght(); i++){
			result += this.getDishes().getAt(i).toString() + "\n";
		}
		return result;
	}



	
	
	@Override
	public int compareTo(Order o) {
		System.out.println(5665);
		System.out.println(this.orderPriority);
		return this.orderPriority.compareTo(o.getOrderPriority());
	}
	
}

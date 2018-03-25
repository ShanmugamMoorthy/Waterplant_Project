package org.we5.waterplant.javaclass;

public class ExpenseDetails {
	String Expense_Type;
	int Quantity;
	double Price;
	String Date;
	int id;
	int expenseId;
	
	public int getExpenseId() {
		return expenseId;
	}

	public void setExpenseId(int expenseId) {
		this.expenseId = expenseId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ExpenseDetails(){
	}
	
	public ExpenseDetails(String Expense_Type, int Quantity, double Price, String Date) {
		super();
		this.Expense_Type = Expense_Type;
		this.Quantity = Quantity;
		this.Date = Date;
		this.Price = Price;
	}

	public void setExpense_Type(String Expense_Type) {
		this.Expense_Type = Expense_Type;
	}

	public String getExpense_Type() {
		return this.Expense_Type;
	}

	public void setDate(String Date) {
		this.Date = Date;
	}

	public String getDate() {
		return this.Date;
	}

	public void setQuantity(int Quantity) {
		this.Quantity = Quantity;
	}

	public int getQuantity() {
		return this.Quantity;
	}

	public void setPrice(double Price) {
		this.Price = Price;
	}

	public double getPrice() {
		return this.Price;
	}
}

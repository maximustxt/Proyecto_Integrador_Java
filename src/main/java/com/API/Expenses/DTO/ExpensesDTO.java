package com.API.Expenses.DTO;


import com.API.Expenses.models.Users;


public class ExpensesDTO {
    private Long id;
    private Double amount;
    private String category;
    private String date;
    

    public ExpensesDTO() {
    }

    public ExpensesDTO(Long id, Double amount, String category, String date, Users user) {
        this.id = id;
        this.amount = amount;
        this.category = category;
        this.date = date;
  
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

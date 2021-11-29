package com.example.raakapplication;

public class MenuItem {

    public String Category, Description, ImagePath, Name, Price;

    public MenuItem(String Category,String Description,String ImagePath,String Name,String Price){
        this.Category = Category;
        this.Description= Description;
        this.ImagePath=ImagePath;
        this.Name=Name;
        this.Price = Price;
    }

    public void setCategory(String catagory) {
        Category = catagory;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setImagePath(String imagePath) {
        ImagePath = imagePath;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getCategory() {
        return Category;
    }

    public String getDescription() {
        return Description;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public String getName() {
        return Name;
    }

    public String getPrice() {
        return Price;
    }
}

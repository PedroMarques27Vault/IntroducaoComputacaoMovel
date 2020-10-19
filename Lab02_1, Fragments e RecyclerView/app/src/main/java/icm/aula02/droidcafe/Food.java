package icm.aula02.droidcafe;

public class Food {
    private String image_food;
    private String name, description;
    private Double price;
    private int amount;

    public Food(String name, Double price, String description, String fp, int amount) {
        this.name=name;
        this.price=price;
        this.description=description;
        this.image_food=fp;
        this.amount=amount;
    }

    public Food(String name, Double price, String description, String fp){
        this.name=name;
        this.price=price;
        this.description=description;
        this.image_food=fp;
        this.amount=0;
    }
    public Food(String name, Double price, String description ){
        this.name=name;
        this.price=price;
        this.description=description;
        this.amount=0;
    }
    public Food(String name, Double price){
        this.name=name;
        this.price=price;
        this.amount=0;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setPrice(Double n){
        this.price=n;
    }
    public void setDescription(String description){
        this.description=description;
    }
    public void setImage(String fp){
        this.image_food=fp;
    }

    public void incAmount(){
        this.amount++;
    }

    public void decAmount(){
        if (this.amount>0){
            this.amount--;
        }else
            this.amount=0;
    }


    public String getDescription(){
        return description;
    }
    public Double getPrice(){
        return price;
    }
    public String getName(){
        return this.name;
    }
    public String getImage(){
        return image_food;
    }
    public int getAmount(){
        return amount;
    }







}

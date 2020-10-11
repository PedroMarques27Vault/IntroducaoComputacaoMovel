package ICM.Aula01.phonedialer;

public class Contact {
    private String name;
    private String phone_number;

    public Contact(){
        name="";
        phone_number="";
    }


    public String getName() {
        return this.name;
    }
    public void setName(String n) {
        this.name = n;
    }
    public String getNumber() {
        return this.phone_number;
    }
    public void setNumber(String n) {
        this.phone_number=n;
    }
}

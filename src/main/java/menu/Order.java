package menu;

public class Order {

    //private String plat2;
    private Plat plat;
    private int quantity;

    /*
    Order(String plat1, double price) {
        this.plat1 = plat1;
    }
     */

    Order(Plat plat) {
        this.plat = plat;
    }


    Order(Plat plat, int quantity) {
        if (quantity > 1){
            this.plat = new Plat(plat.getName(), plat.getPrice()*quantity);
        }
        else
            this.plat = plat;
    }


    public Plat getPlat() {
        return plat;
    }

    public void setPlat(Plat plat) {
        this.plat = plat;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
package core.principles.singleton;

public class StatefulService {

    private int price; //상태를 유지하는 필드

    public void statefulorder(String name, int price) {
        System.out.println("name = " + name + " price + " + price);
        this.price = price; //여기가 문제
    }

    public int getPrice() {
        return price;
    }

    public int stateLessOrder(String name, int stateLessPrice) {
        System.out.println("name = " + name + " stateLessPrice + " + stateLessPrice);
        return stateLessPrice;
    }
}

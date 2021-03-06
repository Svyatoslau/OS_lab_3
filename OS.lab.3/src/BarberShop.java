import java.util.ArrayDeque;

public class BarberShop {

    private ArrayDeque<Customer> Queue = new ArrayDeque<Customer>();

    private int MAX_VISITORS = 5;
    private int numberOfVisitors = 5;


    private Thread barberTread;

    public Thread getBarberTread() {
        return barberTread;
    }

    public BarberShop(Barber barber) {
        this.barberTread = barber.getThread();
    }

    public int getMAX_VISITORS() {
        return MAX_VISITORS;
    }

    public void setMAX_VISITORS(int MAX_VISITORS) {
        this.MAX_VISITORS = MAX_VISITORS;
    }

    public int getNumberOfVisitors() {
        return numberOfVisitors;
    }

    public void setNumberOfVisitors(int numberOfVisitors) {
        this.numberOfVisitors = numberOfVisitors;
    }

    public boolean isFull() {
        return numberOfVisitors >= MAX_VISITORS;
    }

    public boolean isEmpty() {
        return numberOfVisitors == 0;
    }

    synchronized public void push(Customer customer) {
        if (!isFull()) {
            Queue.addLast(customer);
            try {
                customer.getThread().join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else System.out.println("Ой не успел");
    }

    synchronized public void wakeup(){
        barberTread.notify();
    }

    synchronized public Customer pop(){
        return Queue.pop();
    }

}

//  4. Написать программу, демонстрирующую race conditions
//     (доказать, что при одновременной записи в одну и ту же память из более чем одного потока, возможна потеря данных).

public class RaceConditionsExample implements Runnable {

    Account account = new Account();

    public static void main(String[] args) {
        RaceConditionsExample r = new RaceConditionsExample();
        Thread first = new Thread(r);
        Thread second = new Thread(r);
        Thread third = new Thread(r);

        first.setName("Ivan");
        second.setName("Pasha");
        third.setName("Sasha");
        first.start();
        second.start();
        third.start();
    }


    @Override
    public void run() {
        for (int i = 0; i < 1; i++) {
            try {
                withdrawMoney(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (account.getMyMoney() <= 0) {
                System.out.println("HA-HA... the money ran out.");
            }
        }
    }

    private /*synchronized*/ void withdrawMoney(int x) throws InterruptedException {
        if (account.getMyMoney() >= x) {
            System.out.println(Thread.currentThread().getName() + " withdraw money.");

            Thread.sleep(1000);
            account.takeMoney(x);
            System.out.println(Thread.currentThread().getName() + " happy)))");

        } else
            System.out.println("Not enough money!!!" + " " + Thread.currentThread().getName() + " is upset");

    }
}

class Account {
    private int myMoney = 30;

    public int getMyMoney() {
        return myMoney;
    }

    public void takeMoney(int amount) {
        myMoney = myMoney - amount;
    }
}

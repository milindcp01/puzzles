package accounts;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.naming.InsufficientResourcesException;

/* This is kind of simulation of two bank accounts. Program below transfers money between them
in multithread environment
*
*/


class Transfer implements Callable<Boolean> {
	Account acc1 = null;
	Account acc2 = null;
	int amount;
	CountDownLatch startLatch = null;
	static AtomicInteger id = new AtomicInteger(0);

	public Transfer(Account acc1, Account acc2, int amount) {
		this.acc1 = acc1;
		this.acc2 = acc2;
		this.amount = amount;
		id.incrementAndGet();
		System.out.println("New Thread created. id=" + id.get());
	}
	public Transfer(Account acc1, Account acc2, int amount, CountDownLatch cdl) {
		this(acc1,acc2,amount);
		System.out.println("New Thread created. id=" + id.get());
		this.startLatch = cdl;
	}

	@Override
	public Boolean call() throws Exception {
		System.out.println("Waiting to start. id="+id);
		startLatch.await();
		if (acc1.getLock().tryLock(3, TimeUnit.SECONDS)) {
			try {
				if (acc2.getLock().tryLock(3, TimeUnit.SECONDS)) {
					try {
						if (acc1.getBalance() < 0) {
							throw new InsufficientFundsException();
						}
						System.out.println("Got account 1,2 locks!");
						acc1.withdraw(amount);
						acc2.deposit(amount);
						Thread.sleep(new Random().nextInt(1000));
					} finally {
						System.out.println("transfer completed. amount:"
								+ amount);
						System.out.println("Account 1: " + acc1.getBalance());
						System.out.println("Account 2: " + acc2.getBalance());
						acc2.getLock().unlock();
						System.out.println("acc2 unlocked");
					}
				} else {
					System.out.println("Fail! Account 2 is locked");
					acc2.failCounter.incrementAndGet();
				}

			} finally {
				acc1.getLock().unlock();
				System.out.println("acc1 unlocked");
			}
		} else {
			System.out.println("Fail! Account 1 is locked");
			acc1.failCounter.incrementAndGet();
		}
		return true;
	}

}

class Account {
	private int balance = 0;
	private Lock lock = new ReentrantLock();
	AtomicInteger failCounter = new AtomicInteger();
	
	public Account(int initalBalance) {
		this.balance = initalBalance;
	}

	public Lock getLock() {
		return this.lock;
	}

	public void withdraw(int amount) {
		balance -= amount;
	}

	public void deposit(int amount) {
		balance += amount;
	}

	public int getBalance() {
		return balance;
	}

	public int getFailCount() {
		return failCounter.get();
	}
}

public class ConcurTest {
	// transfer money from account1 to account2
	public static void transfer(Account acc1, Account acc2, int amount)
			throws InsufficientFundsException, InterruptedException {
		if (acc1.getBalance() < 0)
			throw new InsufficientFundsException();

		if (acc1.getLock().tryLock(3, TimeUnit.SECONDS)) {
			try {
				if (acc2.getLock().tryLock(3, TimeUnit.SECONDS)) {
					try {
						System.out.println("Got account 1,2 locks!");
						acc1.withdraw(amount);
						acc2.deposit(amount);
					} finally {

						System.out.println("transfer completed. amount:"
								+ amount);
						System.out.println("Account 1: " + acc1.getBalance());
						System.out.println("Account 2: " + acc2.getBalance());
						acc2.getLock().unlock();
						System.out.println("acc2 unlocked");
					}
				}
			} finally {

				System.out.println("acc1 unlocked");
				acc1.getLock().unlock();

			}
		} else {
			System.out.println("Account is locked");
			acc1.failCounter.incrementAndGet();
			acc2.failCounter.incrementAndGet();
		}

	}

	public static void main(String[] args) {
		final Account acc1 = new Account(1000);
		final Account acc2 = new Account(3000);
		CountDownLatch cdl = new CountDownLatch(1);

		final ExecutorService service = Executors.newFixedThreadPool(3);
		List<Future> futures = new ArrayList<Future>();

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (!service.isTerminated()) {
					try {
						Thread.sleep(1050);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out
							.println("Monitor: number of failed transfers (Account1): "
									+ acc1.getFailCount());
					System.out
							.println("Monitor: number of failed transfers (Account2): "
									+ acc2.getFailCount());

				}
			}
		}).start();

		for (int i = 0; i < 10; i++) {

			futures.add(service.submit(new Transfer(acc1, acc2, new Random()
					.nextInt(400),cdl
					)));
			cdl.countDown();
//			futures.add(service.submit(new Transfer(acc2, acc1, new Random()
//					.nextInt(400))));

		}

		service.shutdown();
		System.out.println("Shutdown called!!");
		for (Future future : futures) {
			try {
				future.get();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println("--- post execution dump:");
		System.out.println("Account 1 info");
		System.out.println(acc1.getBalance());
		System.out.println(acc1.failCounter);

		System.out.println("Account 2 info");
		System.out.println(acc2.getBalance());
		System.out.println(acc2.failCounter);

	}

}

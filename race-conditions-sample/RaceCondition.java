/* 
 *-The problem-
 *	Two or more threads want are sharing a instance var (x), and those threads
 *	want to increase by 1 this integer. This is a perfect scenario for a "race condition". 
 *
 *	We have five(5) threads and each will increase var x by one(1) one thousand times(1000)
 *	Following this logic this program should finish with x = 5000
 *
 *	This code will not keep a consistent result x = 5000, between one run and another.
 *
 *  Race conditions details at https://en.wikipedia.org/wiki/Race_condition
 *
 *-author-
 *	Mikel Solabarrieta (11 mar 2016)
 */
public class RaceCondition {

	static class IAmRunnable implements Runnable {
		
		public int x = 0;

		public void increment() {
			x++;
		}

		public void run() {
			for(int i = 0; i < 1000; i++) {
				increment();
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		IAmRunnable iAmRunnable = new IAmRunnable();

		Thread[] threads = new Thread[5];

		for (int i = 0; i < 5; i++) {
  			threads[i] = new Thread(iAmRunnable);
  			threads[i].start();
		}

		for (int i = 0; i < threads.length; i++) {
  			threads[i].join();
		}

		System.out.println("End thread main, x value is:" + iAmRunnable.x); //for sure Thread.main will be the last thread to die (we are using join)	
	}
}
/* 
 *-The problem-
 *	Two or more threads want are sharing a instance var (x), and those threads 
 *	want to increase by 1 this integer. This is a perfect scenario for a "race condition". 
 *
 *	We have five(5) threads and each will increase var x by one(1) one thousand times(1000)
 *	Following this logic this program should finish with x = 5000

 *-Solution-
 *	I am going to use "synchronized" in my method, to keep the integrity on our share resource (x).
 *	This solution will keep a consistent result x = 5000, between one run and another.
 *
 *
 *-author-
 *	Mikel Solabarrieta (11 mar 2016)
 */
public class AvoidingRaceCondition {

	static class IAmRunnable implements Runnable {
		
		public int x = 0;

		public void increment() {
			synchronized(this) {
				x++;
			}
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
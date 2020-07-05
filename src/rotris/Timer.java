package rotris;

public class Timer {

	private static int difference;
	private long starttime;
	private static boolean done;

	void startpause() {
		starttime = System.nanoTime()/10000000;
	}


	int getstart() {	

		return (int) starttime;
	}

	boolean ispaused(int timewanted) {

		done = false;

		if 	(System.nanoTime()/10000000 - starttime >timewanted) {done = true;}

		return done;
	}




}

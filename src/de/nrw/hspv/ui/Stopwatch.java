

public class Stopwatch {

	private long starttime;
	private long stoptime;
	double elapsedTime;
	String Zeit = new String();
	
	public void start() {
		starttime = System.currentTimeMillis();
	}
	
	public void stop() {
		stoptime = System.currentTimeMillis();
	}
	
	public double getElapsedMin() {
		elapsedTime = (stoptime - starttime)/1000/60;
		System.out.println(elapsedTime);
		Zeit = String.valueOf(elapsedTime);
		TimePanel.setLblZeit(Zeit);
		System.out.println(Zeit);
		return elapsedTime;
	}
}
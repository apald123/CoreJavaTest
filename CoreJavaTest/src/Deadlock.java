
public class Deadlock implements Runnable {

	private int i;
	private  final Object left = new Object();
	private  final Object right = new Object();
	
	public Deadlock(int i) {
		this.i = i;
	}
	
	public  void goLeftRight() {
		synchronized(left) {
			synchronized(right) {
				System.out.println("Go Left Right");
			}
		}
	}
	
	public  void goRightLeft() {
		synchronized(right) {
			synchronized(left) {
				System.out.println("Go Right Left");
			}
		}
	}
	
	@Override
	public void run() {
		goLeftRight();
		goRightLeft();
		/*
		if (i==0) {
			goLeftRight();
		} else {
			goRightLeft();
		}
		*/
	}
	
}

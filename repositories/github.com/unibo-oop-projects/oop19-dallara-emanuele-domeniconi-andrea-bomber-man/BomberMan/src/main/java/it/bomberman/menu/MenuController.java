package it.bomberman.menu;

public class MenuController {

	private MenuView menuView;
	private boolean running = false;

	// private final long targetTime = 6944444; //144fps
	// private final long targetTime = 8333333; //120fps
	private long targetTime = 16666667; // 60fps
	private final long CONST = (long) 1e6;
	private long frameEnd;

	/*
	 * in caso sia necessario passare ai metodi update quanto tempo è passato
	 * dall'ultimo refresh
	 */
	private long elapsed = 0;

	public MenuController(MenuView menuView) {
		this.menuView = menuView;
	}

	public void start() {
		this.running = true;
		Thread t = new Thread(this::run);
		t.start();
	}

	private void run() {
		while (this.running == true) {
			final long start = System.nanoTime();
			this.menuView.update();
			//this.menuView.draw();
			this.elapsed = System.nanoTime() - start;
			// System.out.println("Elapsed [ns]: " + (elapsed));

			long wait = (targetTime - elapsed) / CONST + 1;
			// System.out.println("wait [ns]: " + (wait *CONSTANT));
			// System.out.println("FPS: " + (1.0d/ (elapsed) ));
			if (wait < 0) {
				wait = 5;
			}
			try {
				Thread.sleep(wait);
			} catch (Exception e) {
				e.printStackTrace();
			}

			final long tmp = System.nanoTime();
			// System.out.println("elapsed [ns]: " + (tmp - this.frameEnd));
			System.out.println("FPS [1/s]: " + (int) (1e9 / (tmp - this.frameEnd)));
			this.frameEnd = tmp;
		}
	}
}

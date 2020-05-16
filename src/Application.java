import java.awt.Color;

public class Application {
	Application m = this;

	public static void main(String[] args) {
		Ch te = new Ch();
		te.i = 0;
		LoginFrame test = new LoginFrame(te);
		SelectFrame select = null;
		WinFrame winFrame = null;
		LoseFrame loseFrame = null;
		int mapSize = 5;
		FrameBlock[] fb = new FrameBlock[mapSize * mapSize];

		for (int i = 0; i < fb.length; i++) {
			fb[i] = new FrameBlock();
		}

		while (true) {

			try {
				Thread.sleep(500);
			} catch (Exception e) {
				break;

			}

			if (te.i == 1) {
				select = new SelectFrame(te);
				te.i = 0;
				test.dispose();

			} else if (te.i == 2) {
				te.i = 0;
				PlayGame_0 game = new PlayGame_0();

				select.dispose();

				game.SetFramBlock(fb, te);
				game.play();

			} else if (te.i == 3) {
				winFrame = new WinFrame(te);
				te.i = 0;
			} else if (te.i == 4) {
				loseFrame = new LoseFrame(te);
				te.i = 0;

			} else if (te.i == 5) {
				select = new SelectFrame(te);
				te.i = 0;
				winFrame.dispose();
			} else if (te.i == 6) {
				select = new SelectFrame(te);
				te.i = 0;
				loseFrame.dispose();
			} else if (te.i == 100) {
				winFrame.dispose();
				break;
			} else if (te.i == 101) {
				loseFrame.dispose();
				break;
			}
		}
//		PlayGame_0 game = new PlayGame_0();
//		game.play();

	}
}

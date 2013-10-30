package co.uk.jamalantonio.GetOffMyLawn;

import co.uk.jamalantonio.GetOffMyLawn.Entities.Mob;

public class WaveManager {
	
	private final Game GAME;
	private int wave = 1;
	private boolean inProgress = false;
	
	public WaveManager(Game game) {
		 GAME = game;
	}
	
	public void tick() {
		if (inProgress){
			if (GAME.entities.size() == 1) {
				wave++;
				inProgress = false;
			}
		} else {
			spawn(wave);
			inProgress = true;
		}
	}
	
	void spawn(int wave) {
		int num = (int) (2 * Math.pow(Math.E, 0.2 * wave));
		
		for (int i = 1; i <= num; i++) {
			new Mob(24, 24, null, GAME, 5, 1, 1);
		}
	}
	
	public int getWave() {
		return wave;
	}
	
}

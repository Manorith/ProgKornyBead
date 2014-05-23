package game.data;

import game.core.Player;

public interface PlayerDAO {

	void playerSave(Player player);
	
	Player getPPlayer();
	
}

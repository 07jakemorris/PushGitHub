package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Lantern extends Entity{
	
	public OBJ_Lantern(GamePanel gp) {
		super(gp);
		
		type = type_light;
		name = "Lantern";
		down1 = setup("/objects/lantern", gp.tileSize, gp.tileSize);
		description = "[Lantern]\nGives lights and \nleads you the way!";
		price = 150;
		lightRadius = 250;
	}
}

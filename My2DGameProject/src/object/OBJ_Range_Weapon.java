package object;

import java.awt.Color;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

public class OBJ_Range_Weapon extends Projectile{
	
	GamePanel gp;

	public OBJ_Range_Weapon(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		name = "Dark Magic";
		speed = 7;
		maxLife = 40;
		life = maxLife;
		attack = 2;
		knockBackPower = 0;
		useCost = 1;
		alive = false;
		getImage();
		
	}
	public void getImage() {
		
		up1 = setup("/projectile/darkmagic_up1", gp.tileSize, gp.tileSize);
		up2 = setup("/projectile/darkmagic_up2", gp.tileSize, gp.tileSize);
		down1 = setup("/projectile/darkmagic_down1", gp.tileSize, gp.tileSize);
		down2 = setup("/projectile/darkmagic_down2", gp.tileSize, gp.tileSize);
		left1 = setup("/projectile/darkmagic_left1", gp.tileSize, gp.tileSize);
		left2 = setup("/projectile/darkmagic_left2", gp.tileSize, gp.tileSize);
		right1 = setup("/projectile/darkmagic_right1", gp.tileSize, gp.tileSize);
		right2 = setup("/projectile/darkmagic_right2", gp.tileSize, gp.tileSize);
		
	}
	public boolean haveResource(Entity user) {
		
		boolean haveResource = false;
		if(user.mana >= useCost) {
			haveResource = true;
		}
		return haveResource;
	}
	public void subtractResource(Entity user) {
		user.mana -= useCost;
	}
	public Color getParticleColor() {
		Color color = new Color(90,34,139);
		return color;
	}
	public int getParticleSize() {
		int size = 8;
		return size;
	}
	public int getParticleSpeed() {
		int speed = 1;
		return speed;
	}
	public int getParticleMaxLife() {
		int maxLife = 20;
		return maxLife;
	}

}

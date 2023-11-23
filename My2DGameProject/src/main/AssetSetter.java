package main;

import entity.NPC_Merchant;
import entity.NPC_OldMan;
import monster.MON_GreenSlime;
import object.OBJ_Axe;
import object.OBJ_Boots;
import object.OBJ_Chest;
import object.OBJ_Coin;
import object.OBJ_Door;
import object.OBJ_Heart;
import object.OBJ_Key;
import object.OBJ_Lantern;
import object.OBJ_ManaCrystal;
import object.OBJ_Potion_Red;
import object.OBJ_Shield_Blue;
import tile_interactive.IT_DryTree;

public class AssetSetter {

	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		
		this.gp = gp;
	}
	public void setObject() {
		
		int mapNum = 0;
		int i = 0;
		
		gp.obj[mapNum][i] = new OBJ_Lantern(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 18;
		gp.obj[mapNum][i].worldY = gp.tileSize * 20;
		i++;
		
		gp.obj[mapNum][i] = new OBJ_Key(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 38;
		gp.obj[mapNum][i].worldY = gp.tileSize * 7;
		i++;
		
		gp.obj[mapNum][i] = new OBJ_Coin(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 25;
		gp.obj[mapNum][i].worldY = gp.tileSize * 19;
		i++;
		
		gp.obj[mapNum][i] = new OBJ_Potion_Red(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 39;
		gp.obj[mapNum][i].worldY = gp.tileSize * 18;
		i++;
		
		gp.obj[mapNum][i] = new OBJ_Potion_Red(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 36;
		gp.obj[mapNum][i].worldY = gp.tileSize * 18;
		i++;
		
		gp.obj[mapNum][i] = new OBJ_Potion_Red(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 39;
		gp.obj[mapNum][i].worldY = gp.tileSize * 22;
		i++;
		
		gp.obj[mapNum][i] = new OBJ_Axe(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 33;
		gp.obj[mapNum][i].worldY = gp.tileSize * 43;
		i++;
		
		gp.obj[mapNum][i] = new OBJ_Shield_Blue(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 23;
		gp.obj[mapNum][i].worldY = gp.tileSize * 7;
		i++;
		
		gp.obj[mapNum][i] = new OBJ_ManaCrystal(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 22;
		gp.obj[mapNum][i].worldY = gp.tileSize * 31;
		i++;
		
		gp.obj[mapNum][i] = new OBJ_Door(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 14;
		gp.obj[mapNum][i].worldY = gp.tileSize * 28;
		i++;
		
		gp.obj[mapNum][i] = new OBJ_Door(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 12;
		gp.obj[mapNum][i].worldY = gp.tileSize * 12;
		i++;
		
		gp.obj[mapNum][i] = new OBJ_Chest(gp,new OBJ_Key(gp));
		gp.obj[mapNum][i].worldX = gp.tileSize * 12;
		gp.obj[mapNum][i].worldY = gp.tileSize * 8;
		i++;
				
	}
	public void setNPC() {
		
		int mapNum = 0;
		int i = 0;
		
		gp.npc[mapNum][0] = new NPC_OldMan(gp);
		gp.npc[mapNum][0].worldX = gp.tileSize * 22;
		gp.npc[mapNum][0].worldY = gp.tileSize * 24;
		i++;
		
		mapNum = 1;
		i = 0;
		
		gp.npc[mapNum][0] = new NPC_Merchant(gp);
		gp.npc[mapNum][0].worldX = gp.tileSize * 12;
		gp.npc[mapNum][0].worldY = gp.tileSize * 7;
		i++;
	}
	
	public void setMonster() {
		
		int mapNum = 0;
		int i = 0;
		
		gp.monster[mapNum][i] = new MON_GreenSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize * 23;
		gp.monster[mapNum][i].worldY = gp.tileSize * 36;
		i++;
		
		gp.monster[mapNum][i] = new MON_GreenSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize * 23;
		gp.monster[mapNum][i].worldY = gp.tileSize * 37;
		i++;
		
		gp.monster[mapNum][i] = new MON_GreenSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize * 23;
		gp.monster[mapNum][i].worldY = gp.tileSize * 42;
		i++;
		
		gp.monster[mapNum][i] = new MON_GreenSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize * 34;
		gp.monster[mapNum][i].worldY = gp.tileSize * 42;
		i++;
		
		
		gp.monster[mapNum][i] = new MON_GreenSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize * 35;
		gp.monster[mapNum][i].worldY = gp.tileSize * 34;
		i++;
		
		gp.monster[mapNum][i] = new MON_GreenSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize * 37;
		gp.monster[mapNum][i].worldY = gp.tileSize * 39;
		i++;
		
		gp.monster[mapNum][i] = new MON_GreenSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize * 14;
		gp.monster[mapNum][i].worldY = gp.tileSize * 33;
		i++;
		
		gp.monster[mapNum][i] = new MON_GreenSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize * 10;
		gp.monster[mapNum][i].worldY = gp.tileSize * 30;
		i++;
		
		gp.monster[mapNum][i] = new MON_GreenSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize * 12;
		gp.monster[mapNum][i].worldY = gp.tileSize * 29;
		i++;
		
		gp.monster[mapNum][i] = new MON_GreenSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize * 35;
		gp.monster[mapNum][i].worldY = gp.tileSize * 8;
		i++;
		
		gp.monster[mapNum][i] = new MON_GreenSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize * 36;
		gp.monster[mapNum][i].worldY = gp.tileSize * 10;
		i++;
		
		gp.monster[mapNum][i] = new MON_GreenSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize * 39;
		gp.monster[mapNum][i].worldY = gp.tileSize * 11;
		i++;

	}
	public void setInteractiveTile() {
		
		int mapNum = 0;
		int i = 0;
		
		gp.iTile[mapNum][i] = new IT_DryTree(gp,27,12); i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp,28,12); i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp,29,12); i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp,30,12); i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp,31,12); i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp,32,12); i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp,33,12); i++;
		
		gp.iTile[mapNum][i] = new IT_DryTree(gp,26,7); i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp,20,7); i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp,34,29); i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp,33,29); i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp,32,29); i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp,31,29); i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp,30,29); i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp,25,16); i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp,16,40); i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp,15,40); i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp,14,40); i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp,13,40); i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp,13,41); i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp,12,41); i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp,11,41); i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp,10,41); i++;
		
	}
}

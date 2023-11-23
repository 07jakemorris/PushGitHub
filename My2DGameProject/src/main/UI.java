package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;

import object.OBJ_Key;
import object.OBJ_ManaCrystal;
import object.OBJ_Coin;
import object.OBJ_Heart;
import entity.Entity;

public class UI {

	GamePanel gp;
	Graphics2D g2;
	Font maruMonica, purisaB;
	BufferedImage heart_full, heart_half, heart_blank, crystal_full, crystal_blank, coin;
	public boolean messageOn = false;
	ArrayList<String> message = new ArrayList<>();
	ArrayList<Integer> messageCounter = new ArrayList<>();
	public boolean gameFinished = false;
	public String currentDialogue = "";
	public int commandNum = 0;
	public int playerSlotCol = 0;
	public int playerSlotRow = 0;
	public int subState = 0;
	public int npcSlotCol = 0;
	public int npcSlotRow = 0;
	int counter = 0;
	public Entity npc;
	
	public UI(GamePanel gp) {
		this.gp = gp;
		
		
		try {
			InputStream is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
			maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
			is = getClass().getResourceAsStream("/font/Purisa Bold.ttf");
			purisaB = Font.createFont(Font.TRUETYPE_FONT, is);
			
		} catch (FontFormatException e){e.printStackTrace();
		} catch (IOException e){e.printStackTrace();}
		
		// CREATE HUD
		Entity heart = new OBJ_Heart(gp);
		heart_full = heart.image;
		heart_half = heart.image2;
		heart_blank = heart.image3;
		Entity crystal = new OBJ_ManaCrystal(gp);
		crystal_full = crystal.image;
		crystal_blank = crystal.image2;
		Entity bronzecoin = new OBJ_Coin(gp);
		coin = bronzecoin.down1;
	}
	public void addMessage(String text) {
		
		message.add(text);
		messageCounter.add(0);
	}
	
	public void draw(Graphics2D g2) {
		
		this.g2 = g2;
		
		g2.setFont(maruMonica);
		g2.setColor(Color.white);
		
		// TITLE STATE
		if(gp.gameState == gp.titleState) {
			drawTitleScreen();
		}
		
		// PLAY STATE
		if(gp.gameState == gp.playState) {
			drawPlayerLife();
			drawMessage();
		}
		// PAUSE STATE
		if(gp.gameState == gp.pauseState) {
			drawPlayerLife();
			drawPauseScreen();
		}	
		// DIALOGUE STATE
		if(gp.gameState == gp.dialogueState) {
			drawPlayerLife();
			drawDialogueScreen();
		}
		// CHARACTER STATE
		if(gp.gameState == gp.characterState) {
			drawCharacterScreen();
			drawInventory(gp.player, true);
		}
		// OPTION STATE
		if(gp.gameState == gp.optionState) {
			drawOptionScreen();
		}
		// GAME OVER STATE
		if(gp.gameState == gp.gameOverState) {
			drawGameOverScreen();
		}
		// TRANSITION STATE
		if(gp.gameState == gp.transitionState) {
			drawTransition();
		}
		// TRADE STATE
		if(gp.gameState == gp.tradeState) {
			drawTradeScreen();
		}
	}
	public void drawPlayerLife() {
		
		int x = gp.tileSize / 2;
		int y = gp.tileSize / 2;
		int i = 0;
		
		// DRAW MAX LIFE
		while(i < gp.player.maxLife / 2) {
			g2.drawImage(heart_blank, x, y, null);
			i++;
			x += gp.tileSize;
		}
		// RESET
		x = gp.tileSize / 2;
		y = gp.tileSize / 2;
		i = 0;
		
		// DRAW CURRENT LIFE
		while(i < gp.player.life) {
			g2.drawImage(heart_half, x, y, null);
			i++;
			if(i < gp.player.life) {
				g2.drawImage(heart_full, x, y, null);
			}
			i++;
			x += gp.tileSize;
		}
		
		// DRAW MAX MANA
		x = (gp.tileSize / 2) - 5;
		y = (int)(gp.tileSize * 1.5);
		i = 0;
		while(i < gp.player.maxMana) {
			g2.drawImage(crystal_blank, x, y, null);
			i++;
			x += 35;
		}
		
		// DRAW MANA
		x = (gp.tileSize / 2) - 5;
		y = (int)(gp.tileSize * 1.5);
		i = 0;
		while(i < gp.player.mana) {
			g2.drawImage(crystal_full, x, y, null);
			i++;
			x += 35;
		}
		
		
		
	}
	public void drawMessage() {
		
		int messageX = gp.tileSize;
		int messageY = gp.tileSize * 4;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));
		
		for(int i = 0; i < message.size(); i++) {
			if(message.get(i) != null) { 
				
				g2.setColor(Color.black);
				g2.drawString(message.get(i), messageX + 2, messageY + 2);
				g2.setColor(Color.white);
				g2.drawString(message.get(i), messageX, messageY);
				
				int counter = messageCounter.get(i) + 1;
				messageCounter.set(i, counter);
				messageY += 50;
				
				if(messageCounter.get(i) > 180) {
					message.remove(i);
					messageCounter.remove(i);
				}
			}
		}	
	}
	public void drawTitleScreen() {
		
		g2.setColor(new Color(70,120,80));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
		// TITLE GAME
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
		String text = "F L U T T E R";
		int x = getXCenteredText(text);
		int y = gp.tileSize * 3;
		
		// SHADOW
		g2.setColor(Color.black);
		g2.drawString(text, x + 5, y + 5);
		
		// MAIN COLOR
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		
		// CHARACTER IMAGE
		x = gp.screenWidth / 2- (gp.tileSize * 2) / 2;
		y += gp.tileSize * 1;
		g2.drawImage(gp.player.down1, x, y, gp.tileSize * 2, gp.tileSize * 2, null);
		
		// MENU 
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48));
		
		text = "NEW GAME";
		x = getXCenteredText(text);
		y += gp.tileSize * 4;
		g2.drawString(text, x, y);
		if(commandNum == 0) {
			g2.drawString(">", x - gp.tileSize, y);
		}
		
		text = "LOAD GAME";
		x = getXCenteredText(text);
		y += gp.tileSize * 1.5;
		g2.drawString(text, x, y);
		if(commandNum == 1) {
			g2.drawString(">", x - gp.tileSize, y);
		}
		
		text = "QUIT";
		x = getXCenteredText(text);
		y += gp.tileSize * 1.5;
		g2.drawString(text, x, y);
		if(commandNum == 2) {
			g2.drawString(">", x - gp.tileSize, y);
		}
		
		
	}
	public void drawPauseScreen() {
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
		String text = "PAUSED";
		int x = getXCenteredText(text);

		int y = gp.screenHeight / 2;
		
		g2.drawString(text, x, y);
	}
	public void drawDialogueScreen() {
		
		// WINDOW 
		int x = gp.tileSize * 3;
		int y = gp.tileSize / 2;
		int width = gp.screenWidth - (gp.tileSize * 6);
		int height = gp.tileSize * 4;
		drawSubWindow(x, y, width, height);
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 34F));
		x += gp.tileSize - 10;
		y += gp.tileSize + 5;
		
		for(String line : currentDialogue.split("\n")) {
			g2.drawString(line, x, y);
			y += 40;
		}		
	}
	public void drawCharacterScreen() {
		
		// CREATE A FRAME
		final int frameX = gp.tileSize * 1;
		final int frameY = gp.tileSize;
		final int frameWidth = gp.tileSize * 5;
		final int frameHeight = gp.tileSize * 10;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		// TEXT
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		
		int textX = frameX + 20;
		int textY = frameY + gp.tileSize;
		final int lineHeight = 36;
		
		// NAMES
		g2.drawString("Level", textX, textY); textY += lineHeight;
		g2.drawString("Life", textX, textY); textY += lineHeight;
		g2.drawString("Mana", textX, textY); textY += lineHeight;
		g2.drawString("Strength", textX, textY); textY += lineHeight;
		g2.drawString("Dexterity", textX, textY); textY += lineHeight;
		g2.drawString("Attack", textX, textY); textY += lineHeight;
		g2.drawString("Defense", textX, textY); textY += lineHeight;
		g2.drawString("Exp", textX, textY); textY += lineHeight;
		g2.drawString("Next Level", textX, textY); textY += lineHeight;
		g2.drawString("Coin", textX, textY); textY += lineHeight + 5;
		g2.drawString("Weapon", textX, textY); textY += lineHeight + 5;
		g2.drawString("Shield", textX, textY); textY += lineHeight;
		
		// VALUES
		int tailX = (frameX + frameWidth) - 40;
		textY = frameY + gp.tileSize;
		String value;
		
		value = String.valueOf(gp.player.level);
		textX = getXForAlignRightText(value, tailX);
		g2.drawString(value, tailX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
		textX = getXForAlignRightText(value, tailX);
		g2.drawString(value, tailX - 25, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.mana + "/" + gp.player.maxMana);
		textX = getXForAlignRightText(value, tailX);
		g2.drawString(value, tailX - 25, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.strength);
		textX = getXForAlignRightText(value, tailX);
		g2.drawString(value, tailX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.dexterity);
		textX = getXForAlignRightText(value, tailX);
		g2.drawString(value, tailX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.attack);
		textX = getXForAlignRightText(value, tailX);
		g2.drawString(value, tailX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.defense);
		textX = getXForAlignRightText(value, tailX);
		g2.drawString(value, tailX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.exp);
		textX = getXForAlignRightText(value, tailX);
		g2.drawString(value, tailX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.nextLevelExp);
		textX = getXForAlignRightText(value, tailX);
		g2.drawString(value, tailX, textY + 2);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.coin);
		textX = getXForAlignRightText(value, tailX);
		g2.drawString(value, tailX, textY + 3);
		textY += lineHeight;
		
		
		g2.drawImage(gp.player.currentWeapon.down1, tailX - gp.tileSize, textY - 27, null);
		textY += gp.tileSize - 10;
		g2.drawImage(gp.player.currentShield.down1, tailX - gp.tileSize, textY - 25, null);
		textY += gp.tileSize;
		
	}
	public void drawInventory(Entity entity, boolean cursor) {
		
		int frameX;
		int frameY;
		int frameWidth;
		int frameHeight;
		int slotCol = 0;
		int slotRow = 0;
		
		if(entity == gp.player) {
			frameX = gp.tileSize * 13;
			frameY = gp.tileSize;
			frameWidth = gp.tileSize * 6;
			frameHeight = gp.tileSize * 5;
			slotCol = playerSlotCol;
			slotRow = playerSlotRow;
		}
		else {
			
			frameX = gp.tileSize * 2;
			frameY = gp.tileSize;
			frameWidth = gp.tileSize * 6;
			frameHeight = gp.tileSize * 5;
			slotCol = npcSlotCol;
			slotRow = npcSlotRow;
		}

		
		// FRAME
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		// SLOT
		final int slotXStart = frameX + 20;
		final int slotYStart = frameY + 20;
		int slotX = slotXStart;
		int slotY = slotYStart;
		int slotSize = gp.tileSize + 3;
		
		// DRAW PLAYER'S ITEMS
		for(int i = 0; i < entity.inventory.size(); i++) {
			
			// Equip cursor
			if(entity.inventory.get(i) == entity.currentWeapon ||
					entity.inventory.get(i) == entity.currentShield || 
					entity.inventory.get(i) == entity.currentLight){
					g2.setColor(new Color(240,190,90));
					g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
			}
			
			g2.drawImage(entity.inventory.get(i).down1, slotX, slotY, null);
			
			// Displays Amount
			if(entity == gp.player && entity.inventory.get(i).amount > 1) {
				
				g2.setFont(g2.getFont().deriveFont(32f));
				int amountX;
				int amountY;
				
				String s = "" + entity.inventory.get(i).amount;
				amountX = getXForAlignRightText(s, slotX + 44);
				amountY = slotY + gp.tileSize;
				
				// Shadow
				g2.setColor(new Color(60,60,60));
				g2.drawString(s,  amountX, amountY);
				// Number
				g2.setColor(Color.white);
				g2.drawString(s, amountX - 3, amountY - 3);
			}
			
			slotX += slotSize;
			
			if(i == 4 || i == 9 || i == 14) {
				slotX = slotXStart;
				slotY += slotSize;
			}
		}
		
		// CURSOR
		if(cursor == true) {
			
			int cursorX = slotXStart + (slotSize * slotCol);
			int cursorY = slotYStart + (slotSize * slotRow);
			int cursorWidth = gp.tileSize;
			int cursorHeight = gp.tileSize;
			// DRAW CURSOR
			g2.setColor(Color.white);
			g2.setStroke(new BasicStroke(3));
			g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);
			
			// DESCRIPTION FRAME
			int dFrameX = frameX;
			int dFrameY = frameY + frameHeight;
			int dFrameWidth = frameWidth;
			int dFrameHeight = gp.tileSize * 3;
			
			// DRAW DESCRIPTION TEXT
			int textX = dFrameX + 20;
			int textY = dFrameY + gp.tileSize - 10;
			g2.setFont(g2.getFont().deriveFont(26F));
			
			int itemIndex = getItemIndexOnSlot(slotCol, slotRow);
			
			if(itemIndex < entity.inventory.size()) {
				
				drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);
				
				for(String line: entity.inventory.get(itemIndex).description.split("\n")) {
					g2.drawString(line, textX, textY);
					textY += 28;
				}	
			}
		}
	}
	public void drawGameOverScreen() {
		
		g2.setColor(new Color(0, 0, 0, 150));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
		int x;
		int y;
		String text;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110f));
		
		text = "Game Over!";
		g2.setColor(Color.black);
		x = getXCenteredText(text);
		y = gp.tileSize * 4;
		g2.drawString(text, x, y);
		g2.setColor(Color.white);
		g2.drawString(text, x - 4, y - 4);
		
		// Retry
		g2.setFont(g2.getFont().deriveFont(50f));
		text = "Retry";
		x = getXCenteredText(text);
		y += gp.tileSize * 4;
		g2.drawString(text, x, y);
		if(commandNum == 0) {
			g2.drawString(">", x - 40, y);
		}
		
		
		// Back to title Screen
		text = "Quit";
		x = getXCenteredText(text);
		y += gp.tileSize + 35;
		g2.drawString(text, x, y);
		if(commandNum == 1) {
			g2.drawString(">", x - 40, y);
		}
		
	}
	public void drawOptionScreen() {
		
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		
		// SUB WINDOW For OPTION
		int frameX = gp.tileSize * 6;
		int frameY = gp.tileSize;
		int frameWidth = gp.tileSize * 8;
		int frameHeight = gp.tileSize * 10;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		switch(subState) {
		case 0: options_top(frameX, frameY); break;
		case 1: option_control(frameX, frameY); break;
		case 2: option_endGameConfirmation(frameX, frameY); break;
		}
		gp.keyH.enterPressed = false;
	}
	public void options_top(int frameX, int frameY) {
		
		int textX;
		int textY;
		
		// TITLE
		String text = "Options";
		textX = getXCenteredText(text);
		textY = frameY + gp.tileSize + 20;
		g2.drawString(text, textX, textY);
		
		// MUSIC
		textX = frameX + gp.tileSize;
		textY += gp.tileSize * 2 - 20;
		g2.drawString("Music", textX, textY);
		if(commandNum == 0) {
			g2.drawString(">", textX - 25, textY);
		}
		
		// Sound effects
		textY += gp.tileSize + 20;
		g2.drawString("Sound", textX, textY);
		if(commandNum == 1) {
			g2.drawString(">", textX - 25, textY);
		}
		
		// Controls
		textY += gp.tileSize + 20;
		g2.drawString("Control", textX, textY);
		if(commandNum == 2) {
			g2.drawString(">", textX - 25, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 1;
			}			
		}
	
		// Quit
		textY += gp.tileSize + 20;
		g2.drawString("Quit Game", textX, textY);
		if(commandNum == 3) {
			g2.drawString(">", textX - 25, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 2;
				commandNum = 0;
			}
		}
		
		// Back
		textY += gp.tileSize + 35;
		textX = getXCenteredText(text);
		g2.drawString("  Back", textX, textY);
		if(commandNum == 4) {
			g2.drawString(">", textX - 5, textY);
			if(gp.keyH.enterPressed == true) {
				gp.gameState = gp.playState;
				commandNum = 0;
			}
		}
		
		// Music Volume
		textX = frameX + gp.tileSize * 5 - 30;
		textY = frameY + gp.tileSize * 2 + 27;
		g2.drawRect(textX, textY, 120, 24);
		int volumeWidth = 24 * gp.music.volumeScale;
		g2.fillRect(textX, textY, volumeWidth, 24);
		
		// Sound Effects
		textY += gp.tileSize + 20;
		g2.drawRect(textX, textY, 120, 24);
		volumeWidth = 24 * gp.se.volumeScale;
		g2.fillRect(textX, textY, volumeWidth, 24);

		gp.config.saveConfig();
	}
	public void option_control(int frameX, int frameY) {
		
		int textX;
		int textY;
		
		// TITLE
		String text = "Controls";
		textX = getXCenteredText(text);
		textY = frameY + gp.tileSize;
		g2.drawString(text, textX, textY);
		
		textX = frameX + gp.tileSize - 20;
		textY += gp.tileSize;
		g2.drawString("Move", textX, textY); textY += gp.tileSize + 3;
		g2.drawString("Confirm & Attack", textX, textY); textY += gp.tileSize + 3;
		g2.drawString("Shoot & Cast", textX, textY); textY += gp.tileSize + 3;
		g2.drawString("Stat & Inventory", textX, textY); textY += gp.tileSize + 3;
		g2.drawString("Pause", textX, textY); textY += gp.tileSize + 3;
		g2.drawString("Options", textX, textY); textY += gp.tileSize + 3;
		
		textX = frameX + gp.tileSize * 5 + 15;
		textY = frameY + gp.tileSize * 2;
		g2.drawString("W, S, D, A", textX - 10, textY); textY += gp.tileSize + 3;
		g2.drawString("ENTER", textX + 8, textY); textY += gp.tileSize + 3;
		g2.drawString("F", textX + 30, textY); textY += gp.tileSize + 3;
		g2.drawString("C", textX + 30, textY); textY += gp.tileSize + 3;
		g2.drawString("P", textX + 30, textY); textY += gp.tileSize + 3;
		g2.drawString("ESC", textX + 20, textY); textY += gp.tileSize + 3;
		
		// Back
		textX = getXCenteredText(text);
		textY = frameY + gp.tileSize * 9;
		g2.drawString("Back", textX, textY);
		if(commandNum == 0) {
			g2.drawString(">", textX - 20, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 0;
				commandNum = 2;
			}
		}
		
	
	}
	public void option_endGameConfirmation(int frameX, int frameY) {
		
		int textX = frameX + gp.tileSize * 2 - 10;
		int textY = frameY + gp.tileSize * 3;
		
		currentDialogue = "Are you sure to quit \nthe game?";
		
		for(String line: currentDialogue.split("\n")) {
			g2.drawString(line, textX, textY);
			textY += 40;
			textX += gp.tileSize;
		}

		String text = "Yes";
		textX = getXCenteredText(text);
		textY += gp.tileSize * 2;
		g2.drawString(text, textX, textY);
		if(commandNum == 0) {
			g2.drawString(">", textX - 25, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 0;
				gp.gameState = gp.titleState;
			}
		}
		text = "No";
		textX = getXCenteredText(text);
		textY += gp.tileSize;
		g2.drawString(text, textX, textY);
		if(commandNum == 1) {
			g2.drawString(">", textX - 25, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 0;
				commandNum = 3;
			}
		}
	}
	public void drawTransition() {
		
		counter++;
		g2.setColor(new Color(0,0,0,counter*5));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
		if(counter == 50) {
			counter = 0;
			gp.gameState = gp.playState;
			gp.currentMap = gp.eHandler.tempMap;
			gp.player.worldX = gp.tileSize * gp.eHandler.tempCol;
			gp.player.worldY = gp.tileSize * gp.eHandler.tempRow;
			gp.eHandler.previousEventX = gp.player.worldX;
			gp.eHandler.previousEventY = gp.player.worldY;
		}
	}
	public void drawTradeScreen() {
		
		switch(subState) {
		case 0: trade_select(); break;
		case 1: trade_buy(); break;
		case 2: trade_sell(); break;
		}
		gp.keyH.enterPressed = false;
	}
	public void trade_select() {
		
		drawDialogueScreen();
		
		// Draw window
		int x = gp.tileSize * 15;
		int y = gp.tileSize * 4;
		int width = gp.tileSize * 3;
		int height = (int)(gp.tileSize * 3.5);
		drawSubWindow(x, y, width, height);
		
		// Draw texts
		x += gp.tileSize;
		y += gp.tileSize;
		g2.drawString("Buy", x, y);
		if(commandNum == 0) {
			g2.drawString(">", x - 24, y);
			if(gp.keyH.enterPressed == true) {
				subState = 1;
			}
		}
		y += gp.tileSize;
		g2.drawString("Sell", x, y);
		if(commandNum == 1) {
			g2.drawString(">", x - 24, y);
			if(gp.keyH.enterPressed == true) {
				subState = 2;
			}
		}
		y += gp.tileSize;
		g2.drawString("Leave", x, y);
		if(commandNum == 2) {
			g2.drawString(">", x - 24, y);
			if(gp.keyH.enterPressed == true) {
				commandNum = 0;
				gp.gameState = gp.dialogueState;
				currentDialogue = "Come again, He he...!";
			}
		}
		y += gp.tileSize;
		
	}
	public void trade_buy() {
		// Draw player's inventory
		drawInventory(gp.player, false);
		// Draw Merchant's Inventory
		drawInventory(npc, true);
		
		// Draw Hint Window
		int x = gp.tileSize * 2;
		int y = gp.tileSize * 9;
		int width = gp.tileSize * 6;
		int height = gp.tileSize * 2;
		drawSubWindow(x, y, width, height);
		g2.drawString("[ESC] Back", x + 24, y + 60);
		
		// Draw player coin window
		x = gp.tileSize * 12;
		y = gp.tileSize * 9;
		width = gp.tileSize * 6;
		height = gp.tileSize * 2;
		drawSubWindow(x, y, width, height);
		g2.drawString("Your Coins: " + gp.player.coin, x + 24, y + 60);
		
		// Draw sub window
		int itemIndex = getItemIndexOnSlot(npcSlotCol, npcSlotRow);
		if(itemIndex < npc.inventory.size()) {
			
			x = (int)(gp.tileSize * 5.5);
			y = (int)(gp.tileSize * 5.5);
			width = (int)(gp.tileSize * 2.5);
			height = gp.tileSize;
			drawSubWindow(x, y, width, height);
			g2.drawImage(coin, x + 10, y + 8, 32, 32, null);
			
			int price = npc.inventory.get(itemIndex).price;
			String text = ""+ price;
			x = getXForAlignRightText(text, gp.tileSize * 8 - 20);
			g2.drawString(text, x, y+34);
			
			// Buy Items
			if(gp.keyH.enterPressed == true) {
				if(npc.inventory.get(itemIndex).price > gp.player.coin) {
					subState = 0;
					gp.gameState = gp.dialogueState;
					currentDialogue = "You don't have enough coins!";
					drawDialogueScreen();
				}
				else {
					if(gp.player.canObtainItem(npc.inventory.get(itemIndex)) == true) {
						gp.player.coin -= npc.inventory.get(itemIndex).price;
					}
					else {
						subState = 0;
						gp.gameState = gp.dialogueState;
						currentDialogue = "Inventory is full!";
					}
				}
			}
		}
	}
	public void trade_sell() {
		
		// Draw player's Inventory
		drawInventory(gp.player, true);
		
		int x; 
		int y;
		int width; 
		int height;
		
		// Draw player coin window
		x = gp.tileSize * 13;
		y = gp.tileSize * 9;
		width = gp.tileSize * 6;
		height = gp.tileSize * 2;
		drawSubWindow(x, y, width, height);
		g2.drawString("Your Coins: " + gp.player.coin, x + 24, y + 60);
		
		x = gp.tileSize * 2;
		y = gp.tileSize * 9;
		width = gp.tileSize * 6;
		height = gp.tileSize * 2;
		drawSubWindow(x, y, width, height);
		g2.drawString("[ESC] Back", x + 24, y + 60);
				
		// Draw sub window
		int itemIndex = getItemIndexOnSlot(playerSlotCol, playerSlotRow);
		if(itemIndex < gp.player.inventory.size()) {
					
			x = (int)(gp.tileSize * 15.5);
			y = (int)(gp.tileSize * 5.5);
			width = (int)(gp.tileSize * 2.5);
			height = gp.tileSize;
			drawSubWindow(x, y, width, height);
			g2.drawImage(coin, x + 10, y + 8, 32, 32, null);
					
			int price = gp.player.inventory.get(itemIndex).price / 2;
			String text = ""+ price;
			x = getXForAlignRightText(text, gp.tileSize * 18 - 20);
			g2.drawString(text, x, y+34);
					
			// Sell Items
			if(gp.keyH.enterPressed == true) {
				
				if(gp.player.inventory.get(itemIndex) == gp.player.currentWeapon || 
						gp.player.inventory.get(itemIndex) == gp.player.currentShield) {
					commandNum = 0;
					subState = 0;
					gp.gameState = gp.dialogueState;
					currentDialogue = "You cannot sell an equiped items!";	
				}
				else {
					if(gp.player.inventory.get(itemIndex).amount > 1) {
						gp.player.inventory.get(itemIndex).amount--;
					}
					else {
						gp.player.inventory.remove(itemIndex);
					}
					gp.player.coin += price;
				}
			}		
		}		
	}
	
	public int getItemIndexOnSlot(int slotCol, int slotRow) {
		int itemIndex = slotCol + (slotRow * 5);
		return itemIndex;
	}
	public void drawSubWindow(int x, int y, int width, int height) {
		
		Color c = new Color(0,0,0,210);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		
		c = new Color(255,255,255);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
		
	}
	public int getXCenteredText(String text) {
		
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth / 2 - length / 2;
		return x;
	}
	public int getXForAlignRightText(String text, int tailX) {
		
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = tailX - length;
		return x;
	}
}

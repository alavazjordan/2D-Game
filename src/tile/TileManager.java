package tile;

import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.InputStream;
import main.MyPanel;

public class TileManager {

	
	MyPanel mp;
	Tile[] tile;
	int mapTileNum[][];
	
	public TileManager(MyPanel mp) {
		this.mp = mp;
		tile = new Tile[10];
		mapTileNum = new int[mp.maxScreenCol][mp.maxScreenRow];
	
		getTileImage();
		loadMap();
	}
	
	
	public void getTileImage() {
		try {
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
			
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/dirt.png"));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void loadMap() {
		try {
			InputStream is = getClass().getResourceAsStream("/maps/map01.txt"); 
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while(col < mp.maxScreenCol && row < mp.maxScreenRow){
				
				String line = br.readLine();
				
				while(col < mp.maxScreenCol) {
					String numbers[] = line.split(" "); // String.split(string) - splits this string around matches of the given regular expression
					int num = Integer.parseInt(numbers[col]);
					mapTileNum[col][row] = num;
					col++;
				}
				if(col == mp.maxScreenCol) {
					col = 0;
					row++;
				}
			}
			br.close();
		}
		catch(Exception e) {
			
		}
	}
	
	public void draw(Graphics2D g2) {
		int col = 0;
		int row = 0;
		int x = 0;
		int y = 0;
		
		while(col < mp.maxScreenCol & row < mp.maxScreenRow) {
			int tileNum = mapTileNum[col][row]; // map should be stored in this tileNum
			
			g2.drawImage(tile[tileNum].image, x, y, mp.tileSize, mp.tileSize, null);
			col++;
			x += mp.tileSize;
			
			if(col == mp.maxScreenCol) {
				col = 0;
				x = 0;
				
				row++;
				y += mp.tileSize;
			}
		}
	}
}

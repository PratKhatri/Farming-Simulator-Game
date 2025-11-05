package core;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;



public class Images {

	public static Image dirt;
	public static Image grass;
	public static Image gray;
	public static Image drySoil;
	public static Image wetSoil;
	public static Image water;
	public static Image fullBucket;
	public static Image emptyBucket;
    public static Image hoe;
    public static Image axe;
    public static Image pick;

	public static SpriteSheet corn;
	public static SpriteSheet potato;

	public static Image rock;
	public static SpriteSheet pineTree;

	public static void loadImages()
	{
		try
		{
			dirt = new Image("res/dirt.png");
			grass = new Image("res/grass.png");
			gray = new Image("res/gray.png");
			drySoil = new Image("res/drySoil.png");
			wetSoil = new Image("res/wetSoil.png");
			water = new Image("res/water.png");
			fullBucket = new Image("res/bucketFull.png");
			emptyBucket = new Image("res/bucketEmpty.png");
	        hoe = new Image("res/hoe.png");
			
			corn = new SpriteSheet("res/corn.png", 32, 48, 0);
			potato = new SpriteSheet("res/potato.png", 32, 48, 0);
			
			rock = new Image("res/rock.png");
			pineTree = new SpriteSheet("res/treePine.png", 32, 48, 0);
		}
		catch (SlickException e)
		{
			e.printStackTrace();
		}
	}

}

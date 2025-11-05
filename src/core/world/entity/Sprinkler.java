package world.entity;

import java.util.ArrayList;

import world.Cell;

public abstract class Sprinkler extends Entity{

	public Sprinkler() {
		
	}
	
	public abstract boolean isValid();
	
	public abstract void clicked();
	
	public abstract void waterCells(ArrayList<Cell> neighbors);
}

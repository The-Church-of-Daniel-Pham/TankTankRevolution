package com.ttr.actor.map;
/**
 * @author Samuel
 * @version April 21st 2018
 * 
 * Description: Array of MapTiles in the layout from MazeMaker. To display the Map, each MapTile is drawn by iterating through them all.
 */

import java.util.ArrayList;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.ttr.level.Level;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Map extends Group {
	public int[][] layout;
	public MapTile[][] map;
	public Level level;

	/**
	 * Creates a new randomly generated map of size width tiles and height tiles
	 * @param width width of Map
	 * @param height height of Map
	 * @param level the Level to which the Map belongs
	 */
	public Map(int width, int height, Level level) {
		this.level = level;
		MazeMaker mazeGen = new MazeMaker(width, height);
		mazeGen.createMaze(0, 0);
		layout = mazeGen.getMaze();
		map = new MapTile[layout.length][layout[0].length];
		for (int row = layout.length - 1; row >= 0; row--) {
			for (int col = 0; col < layout[row].length; col++) {
				if (layout[row][col] == 0) {
					MapTile tile = new GrassTile(layout.length - (1 + row), col);	//polymorphic for simplicity in this class
					map[row][col] = tile;
					super.addActor(tile);	//kinda redundant, but may come in handy later
				} else if (layout[row][col] == 1) {
					// same as for grass, but for brick
					MapTile tile = new BrickTile(layout.length - (1 + row), col);
					map[row][col] = tile;
					super.addActor(tile);
				}
			}
		}
	}
	/**
	 * 
	 * @return the width, in pixels
	 */
	public int getSizeX() {
		return layout[0].length * MapTile.SIZE;
	}
	/**
	 * 
	 * @return the height, in pixels
	 */
	public int getSizeY() {
		return layout.length * MapTile.SIZE;
	}
	
	/**
	 * 
	 * @param x the x position in pixels
	 * @param y the y position in pixels
	 * @return an integer array of size 2 
	 * {row, col} that represents the tile the given pixel coordinates lie on
	 */
	public int[] getTileAt(float x, float y) {
		// due to rounding down with int tankMapRow = (int)((40*128-super.getY())/128),
		// which "rounds back up" when converting back to world coords
		int mapCol = (int) (x / MapTile.SIZE);
		int mapRow = (int) ((level.height * MapTile.SIZE - y) / MapTile.SIZE);
		return new int[] {mapRow, mapCol};
	}
	
	/**
	 * 
	 * @param x the x position in pixels
	 * @param y the y position in pixels
	 * @return returns true if the given coordinates in inside the Map,
	 * otherwise returns false
	 */
	public boolean inMap(float x, float y) {
		return x > 0 && x < getSizeX() && y > 0 && y < getSizeY();
	}
	
	/**
	 * 
	 * @param a tile's row
	 * @param a tile's column
	 * @return an ArrayList of MapTiles of all bricks within two tiles of the given tile
	 */
	public ArrayList<MapTile> getBrickNeighbors(int row, int col) {
		ArrayList<MapTile> brickNeighbors = new ArrayList<MapTile>();
		for (int yOffset = -2; yOffset <= 2; yOffset++) {
			for (int xOffset = -2; xOffset <= 2; xOffset++) {
				int tempRow = row + yOffset;
				int tempCol = col + xOffset;
				if (tempRow < 0 || tempRow >= level.height || tempCol >= level.width || tempCol < 0) // edge
				{
					//handle edge vertices separately
					MapTile border = new BorderTile(layout.length - (1 + tempRow), tempCol);	// see constructor
					brickNeighbors.add(border);	//only in group for now, may add to array later
				}
				else if (level.map.layout[tempRow][tempCol] == 1) // normal brick in bounds
				{
					brickNeighbors.add(level.map.map[tempRow][tempCol]);
				}
			}
		}
		return brickNeighbors;
	}
	
	/**
	 * 
	 * @param tiles an ArrayList of MapTiles
	 * @return an ArrayList of Polygons, each of which represents a tile
	 */
	public ArrayList<Polygon> getHitboxes(ArrayList<MapTile> tiles) {
		ArrayList<Polygon> hitboxes = new ArrayList<Polygon>();
		for (MapTile tile : tiles) {
			tile.setHitbox(tile.getX(), tile.getY(), (float) Math.toRadians(tile.getRotation()));
			hitboxes.add(tile.getHitbox());
		}
		return hitboxes;
	}

	
	/**
	 * Precondition: batch.begin() has been called
	 * @Override
	 * @param batch the current batch for drawing sprites
	 * @param alpha transparency [0,1]
	 */
	public void draw(Batch batch, float alpha) {
		for (Actor tile : super.getChildren()) {
				tile.draw(batch, alpha);
		}
	}
}
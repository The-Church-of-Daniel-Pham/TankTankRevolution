package com.tank.actor.map;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.tank.actor.map.tiles.AbstractMapTile;
import com.tank.actor.map.tiles.BorderTile;
import com.tank.actor.map.tiles.FloorTile;
import com.tank.actor.map.tiles.WallTile;
import com.tank.stage.Level;
import com.tank.utils.mapgenerator.MazeMaker;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Map extends Group {
	private int[][] layout;
	public AbstractMapTile[][] map;
	protected Level level;

	/**
	 * Creates a new randomly generated map of size width tiles and height tiles
	 * 
	 * @param width
	 *            width of Map
	 * @param height
	 *            height of Map
	 * @param level
	 *            the Level to which the Map belongs
	 */
	public Map(int width, int height, Level level) {
		this.level = level;
		MazeMaker mazeGen = new MazeMaker(height, width);
		mazeGen.createMaze(0, 0);
		mazeGen.clearBottomLeftCorner(2);	//Clears out corner so tank doesn't spawn on bricks
		mazeGen.addBorder(1);
		layout = mazeGen.getMaze();
		
		for (int[] row : layout) {
			for (int i : row) {
				System.out.print(i);
			}
			System.out.println();
		}
		
		map = new AbstractMapTile[height][width];
		for (int row = 0; row < map.length; row++) {
			for (int col = 0; col < map[row].length; col++) {
				AbstractMapTile tile = null;
				if (layout[row][col] == 0) {
					// polymorphic for simplicity
					tile = new FloorTile(row, col, this);
				} else if (layout[row][col] == 1) {
					tile = new WallTile(row, col, this);
				}
				if (layout[row][col] == 2) {
					tile = new BorderTile(row, col, this);
				}
				map[row][col] = tile;
				super.addActor(tile);// kinda redundant, but may come in handy later
			}
		}
	}

	public void setTilesVisibility(int minRow, int minCol, int maxRow, int maxCol, boolean vis) {
		// can us clamp later on
		minRow--;
		minCol--;
		maxRow++;
		maxCol++;
		if (maxRow > level.getMapHeight() - 1) {
			maxRow = level.getMapHeight() - 1;
		}
		if (maxCol > level.getMapWidth() - 1) {
			maxCol = level.getMapWidth() - 1;
		}
		if (minRow < 0) {
			minRow = 0;
		}
		if (minCol < 0) {
			minCol = 0;
		}
		for (int r = minRow; r <= maxRow; r++) {
			for (int c = minCol; c <= maxCol; c++) {
				map[r][c].inView = vis;
			}
		}
	}

	/**
	 * 
	 * @return the width, in pixels
	 */
	public int getSizeX() {
		return map[0].length * AbstractMapTile.SIZE;
	}

	/**
	 * 
	 * @return the height, in pixels
	 */
	public int getSizeY() {
		return map.length * AbstractMapTile.SIZE;
	}

	/**
	 * 
	 * @param x
	 *            the x position in pixels
	 * @param y
	 *            the y position in pixels
	 * @return an integer array of size 2 {row, col} that represents the tile the
	 *         given pixel coordinates lie on
	 */
	public int[] getTileAt(float x, float y) {
		int mapCol = (int) (x / AbstractMapTile.SIZE);
		int mapRow = (int) (y / AbstractMapTile.SIZE);
		return new int[] { mapRow, mapCol };
	}

	/**
	 * 
	 * @param x
	 *            the x position in pixels
	 * @param y
	 *            the y position in pixels
	 * @return returns true if the given coordinates in inside the Map, otherwise
	 *         returns false
	 */
	public boolean inMap(float x, float y) {
		return x > 0 && x < getSizeX() && y > 0 && y < getSizeY();
	}

	/**
	 * 
	 * @param a
	 *            tile's row
	 * @param a
	 *            tile's column
	 * @return an ArrayList of MapTiles of all bricks within two tiles of the given
	 *         tile
	 */
	public ArrayList<AbstractMapTile> getWallNeighbors(int row, int col) {
		ArrayList<AbstractMapTile> brickNeighbors = new ArrayList<AbstractMapTile>();
		for (int yOffset = -1; yOffset <= 1; yOffset++) {
			for (int xOffset = -1; xOffset <= 1; xOffset++) {
				int tempRow = MathUtils.clamp(row + yOffset, 0, map.length - 1);
				int tempCol = MathUtils.clamp(col + xOffset, 0, map[row].length - 1);
				if (map[tempRow][tempCol] instanceof WallTile)
				{
					brickNeighbors.add(map[tempRow][tempCol]);
				}
			}
		}
		return brickNeighbors;
	}

	public void removeWall(AbstractMapTile m) {
		if (!(m instanceof BorderTile)) {
			AbstractMapTile n = new FloorTile(m.getRow(), m.getCol(), this);
			addActor(n);
			map[m.getRow()][m.getCol()] = n;
			layout[m.getRow()][m.getCol()] = 0;
			m.remove();
		}
	}

	/**
	 * Precondition: batch.begin() has been called
	 * 
	 * @Override
	 * @param batch
	 *            the current batch for drawing sprites
	 * @param alpha
	 *            transparency [0,1]
	 */
	public void draw(Batch batch, float alpha) {
		for (Actor tile : super.getChildren()) {
			tile.draw(batch, alpha);
		}
	}
}
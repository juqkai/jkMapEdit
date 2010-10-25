package org.juqkai.z1.mapEdit.tile.format.square;

import java.awt.Point;
import org.juqkai.z1.mapEdit.CurrentMapInfo;
import org.juqkai.z1.mapEdit.Tile;

/**
 * 按左下对齐左下
 * @author juqkai(juqkai@gmail.com) 2010-9-27
 */
public class SquareLeftDownFormat extends SquareFormat{
	private static final long serialVersionUID = 1L;
	public SquareLeftDownFormat(boolean order) {
		super(order);
	}
	public Point formatCoord(Point point) {
		int x = point.x;
		int y = point.y;
		
		x = x - x % CurrentMapInfo.tileW;
		y = y + (CurrentMapInfo.tileH - y % CurrentMapInfo.tileH);
		
		return new Point(x, y);
	}
	@Override
	public Point datumOffset(Tile tile) {
		int x = 0;
		int y = 0;
		x = 0;
		y = tile.getMapImage().getHeight() * -1;
		return new Point(x, y);
	}
}

package org.juqkai.z1.mapEdit.tile.format.square;

import java.awt.Point;

import org.juqkai.z1.mapEdit.CurrentMapInfo;
import org.juqkai.z1.mapEdit.Tile;

/**
 * 格式化坐标到左上角
 * @author juqkai(juqkai@gmail.com) 2010-9-27
 */
public class SquareLeftTopFormat extends SquareFormat{
	public SquareLeftTopFormat(boolean order) {
		super(order);
	}
	@Override
	public Point datumOffset(Tile tile) {
		return new Point(0, 0);
	}
	@Override
	public Point formatCoord(Point point) {
		int x = point.x;
		int y = point.y;
		//格式化到格子左上角
		x = x - x % CurrentMapInfo.tileW;
		y = y - y % CurrentMapInfo.tileH;
		return new Point(x, y);
	}
}

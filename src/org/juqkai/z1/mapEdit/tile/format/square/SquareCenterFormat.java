package org.juqkai.z1.mapEdit.tile.format.square;

import java.awt.Point;
import org.juqkai.z1.mapEdit.CurrentMapInfo;
import org.juqkai.z1.mapEdit.Tile;
/**
 * 中心格式化到中心位置
 * @author juqkai(juqkai@gmail.com) 2010-9-27
 */
public class SquareCenterFormat extends SquareFormat{
	private static final long serialVersionUID = 1L;
	public SquareCenterFormat(boolean order) {
		super(order);
	}

	public Point formatCoord(final Tile tile) {
		int x = tile.getX();
		int y = tile.getY();
		//格式化到格子左上角
		x = x - x % CurrentMapInfo.tileW;
		y = y - y % CurrentMapInfo.tileH;
		
		//调整图片的位置
		x = x + (CurrentMapInfo.tileW - tile.getMapImage().getWidth()) / 2;
		y = y + (CurrentMapInfo.tileH - tile.getMapImage().getHeight()) / 2;
		return new Point(x, y);
	}

	public Point formatCoord(Point point) {
		int x = point.x;
		int y = point.y;
		//格式化到格子左上角
		x = x - x % CurrentMapInfo.tileW;
		y = y - y % CurrentMapInfo.tileH;
		
		//调整图片的位置
		x = x + (CurrentMapInfo.tileW/2);
		y = y + (CurrentMapInfo.tileH/2);
		return new Point(x, y);
	}
	@Override
	public Point datumOffset(Tile tile) {
		int x = tile.getMapImage().getWidth() ;
		int y = tile.getMapImage().getHeight();
		x =  x /2 * -1;
		y = y / 2 * -1;
		return new Point(x, y);
	}
}

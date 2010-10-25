package org.juqkai.z1.mapEdit.tile.format;

import java.awt.Point;
import org.juqkai.z1.mapEdit.MapImage;
import org.juqkai.z1.mapEdit.Tile;

/**
 * 默认格式化
 * @author juqkai(juqkai@gmail.com) 2010-9-27
 */
public class DefaultFormat implements Format{
	public Point formatCoord(Point point) {
		return point;
	}
	@Override
	public Point datumOffset(Tile tile) {
		return new Point(0, 0);
	}
	@Override
	public int fetchIndex(Point point, MapImage mi) {
		return -1;
	}
}

package org.juqkai.z1.mapEdit.tile.format;

import java.awt.Point;
import java.io.Serializable;

import org.juqkai.z1.mapEdit.MapImage;
import org.juqkai.z1.mapEdit.Tile;


/**
 * 格式化元素(砖块)位置
 * @author juqkai(juqkai@gmail.com) 2010-9-25
 */
public interface Format extends Serializable {
	/**
	 * 格式化基准点的位置,即将其调整到格子的那个角上
	 * @param point 基准点的真实坐标
	 * @return
	 * @author juqkai(juqkai@gmail.com) 2010-9-29
	 */
	public Point formatCoord(final Point point);
	/**
	 * 基本基准点计算索引值
	 * @param point
	 * @param mi
	 * @return
	 * @author juqkai(juqkai@gmail.com) 2010-9-29
	 */
	public int fetchIndex(Point point, final MapImage mi);
	/**
	 * 元素上指定一个基准点,该点到元素左上角的偏移量
	 * @param tile 用于计算的元素
	 * @return
	 * @author juqkai(juqkai@gmail.com) 2010-9-28
	 */
	public Point datumOffset(Tile tile);
}

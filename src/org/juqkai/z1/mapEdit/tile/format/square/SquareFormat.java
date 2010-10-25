package org.juqkai.z1.mapEdit.tile.format.square;

import java.awt.Point;
import org.juqkai.z1.mapEdit.MapImage;
import org.juqkai.z1.mapEdit.CurrentMapInfo;
import org.juqkai.z1.mapEdit.tile.format.Format;

/**
 * 方形格式化组件
 * @author juqkai(juqkai@gmail.com) 2010-9-27
 */
public abstract class SquareFormat implements Format{
	private static final long serialVersionUID = 1L;
	private boolean order = false;
	public SquareFormat(boolean order) {
		this.order = order; 
	}
	/**
	 * 默认实现,直接按格子尺寸进行计算
	 */
	public int fetchIndex(Point point, MapImage mi) {
		if(!order){
			return -1;
		}
		int x = point.x;
		int y = point.y;
		
		if(x < 0 || y < 0){
			return -1;
		}
		int h = 0;
		int w = 0;
		h = y / CurrentMapInfo.tileH;
		w = x / CurrentMapInfo.tileW;
		
		int size = h * CurrentMapInfo.width;
		size = size + w;
		return size;
	}
}

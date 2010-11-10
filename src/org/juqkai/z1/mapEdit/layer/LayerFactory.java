package org.juqkai.z1.mapEdit.layer;

import org.juqkai.z1.mapEdit.ImageCache;
import org.juqkai.z1.mapEdit.MapImage;
import org.juqkai.z1.mapEdit.Tile;
import org.juqkai.z1.mapEdit.tile.format.DefaultFormat;
import org.juqkai.z1.mapEdit.tile.format.Format;
import org.juqkai.z1.mapEdit.tile.format.square.SquareCenterFormat;
import org.juqkai.z1.mapEdit.tile.format.square.SquareLeftDownFormat;
import org.juqkai.z1.mapEdit.tile.format.square.SquareLeftTopFormat;


/**
 * 层工厂
 * @author juqkai(juqkai@gmail.com) 2010-9-26
 */
public class LayerFactory {
	public static final String imgmove = "/img/move.png";
	/**
	 * 根据枚举类型创建层
	 * @param le
	 * @return
	 * @author juqkai(juqkai@gmail.com) 2010-9-26
	 */
	public static Layer make(LayerEnum le){
		Layer layer = null ;
		switch(le){
		case MOVE:
			layer = new Layer(true);
			ImageCache.make().put(new MapImage(Tile.class.getResource(imgmove), imgmove));
			break;
		case BULIDING:
			layer = new Layer(true);
			break;
		case FLOOR:
			layer = new Layer(true);
			break;
		case GRID:
			layer = new LayerForGrid();
			break;
		}
		layer.setFormat(makeFormat(le));
		return layer;
	}
	public static Format makeFormat(LayerEnum le){
		Format fm = null;
		switch(le){
		case MOVE:
			fm = new SquareCenterFormat(true);
			break;
		case BULIDING:
			fm = new SquareLeftDownFormat(true);
			break;
		case FLOOR:
			fm = new SquareLeftTopFormat(true);
			break;
		case GRID:
			fm = new DefaultFormat();
			break;
		}
		return fm;
	}
}

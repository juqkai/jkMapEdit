package org.juqkai.z1.mapEdit.layer;

import java.awt.Graphics2D;
import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.juqkai.util.ZKLang;
import org.juqkai.z1.mapEdit.CurrentMapInfo;
import org.juqkai.z1.mapEdit.Tile;
import org.juqkai.z1.mapEdit.tile.format.DefaultFormat;
import org.juqkai.z1.mapEdit.tile.format.Format;

/**
 * 层,地图编辑器中的层,默认情况下砖块是不与格子对齐的
 * @author juqkai(juqkai@gmail.com)
 */
public class Layer implements Serializable{
	private static final long serialVersionUID = 1L;
	//当前层的元素
	private List<Tile> tiles = new ArrayList<Tile>();
	//格式化
	private Format format = new DefaultFormat(); 
	
	public Layer() {}
	/**
	 * 是否是有序的层
	 * @param order
	 */
	public Layer(boolean order){
		if(order){
			fillContainer(CurrentMapInfo.width * CurrentMapInfo.height);
		}
	}
	/**
	 * 添加地板
	 * @param tile
	 * @author juqkai(juqkai@gmail.com)
	 */
	public void add(Tile tile){
		int x = tile.getX();
		int y = tile.getY();
		
		Integer index = format.fetchIndex(tile.getPoint(), tile.getMapImage());
		if(index > 0 && index == tile.getIndex()){
			return;
		}
		
		Point p = format.datumOffset(tile);
		Point pp = format.formatCoord(tile.getPoint());
		x = pp.x + p.x;
		y = pp.y + p.y;
		
		if(index < 0){
			tile.setPoint(new Point(x, y));
			tiles.add(tile);
			return;
		}
		
		if(tile.getIndex() >= 0 && tile.getIndex() < tiles.size()){
			removeForList(tile);
		}
		tile.setPoint(new Point(x, y));
		tile.setIndex(index);
		add(index, tile);
	}
	/**
	 * 添加地板到指定位置
	 * @param fetchIndex
	 * @param tile
	 * @author juqkai(juqkai@gmail.com)
	 */
	private void add(int index, Tile tile) {
		if(index < 0){
			return;
		}
		fillContainer(index);
		tiles.set(index, tile);
	}
	/**
	 * 初始化容器
	 * @param index 要填充的数量
	 * @author juqkai(juqkai@gmail.com) 2010-9-27
	 */
	private void fillContainer(int index){
		if(tiles.size() <= index){
			for(int i = tiles.size() - 1; i < index; i ++){
				tiles.add(null);
			}
		}
	}
	/**
	 * 绘制当前层的信息
	 * @param g
	 * @author juqkai(juqkai@gmail.com)
	 */
	public void draw(Graphics2D g){
		for(Tile tile : tiles){
			if(ZKLang.isNull(tile)){
				continue;
			}
    		tile.draw(g);
    	}
    }
	
	/**
	 * 移除坐标范围的元素
	 * @param x
	 * @param y
	 * @author juqkai(juqkai@gmail.com) 2010-9-26
	 */
	public void remove(int x, int y) {
		Tile tile = fetchTile(x, y);
		if(ZKLang.isNull(tile)){
			return ;
		}
		removeForList(tile);
	}
	/**
	 * 移除列表中的元素,很重要呀!~~~如果直接使用list的remove方法,会导致坐标错位
	 * @param tile
	 * @author juqkai(juqkai@gmail.com) 2010-9-27
	 */
	private void removeForList(Tile tile){
		if(tile.getIndex() < 0){
			tiles.remove(tile);
		}
		add(tile.getIndex(), null);
	}
	/**
	 * 获取元素(砖块)
	 * @param x
	 * @param y
	 * @return
	 * @author juqkai(juqkai@gmail.com)
	 */
	public Tile fetchTile(int x, int y) {
		for(int i = tiles.size() - 1; i >=0; i --){
			Tile tile = tiles.get(i);
			if(ZKLang.isNull(tile)){
				continue;
			}
			if(tile.isSelected(x, y)){
				return tile;
			}
		}
		return null;
	}
	
	
	
	//初选中的元素
	private Tile selectTile;
	//在移动时鼠标选择位置的偏移值
	private int offsetX = 0, offsetY = 0;
	/**
	 * 选择元素
	 * @param x
	 * @param y
	 * @return 
	 */
	public Tile selectedTile(Integer x , Integer y){
		selectTile = fetchTile(x, y);
		if(ZKLang.isNull(selectTile)){
			return null;
		}
		Point po = format.datumOffset(selectTile);
		
		offsetX = x + po.x - selectTile.getX();
		offsetY = y + po.y - selectTile.getY();
		return selectTile;
	}
	/**
	 * 移动选中的元素,在做计算的时候是按元素的左上角来计算的
	 * @param x
	 * @param y
	 * @return 
	 */
	public void move(int x, int y ){
		/*
		 * ZKFIXME 这个地方有个问题,在移动的过程中把基准点的坐标移动到范围外面去了,那么该元素的
		 * 坐标将被设置成-1,而后在添加的时候将直接添加到列表的最末的位置,
		 * 导致在一些需要严格遵守顺序的地方出现问题
		 */
		if(ZKLang.isNull(selectTile)){
			return;
		}
		x = x - offsetX;
		y = y - offsetY;
		
		Tile tile = new Tile(x, y, selectTile.getMapImage());
		
		Integer index = format.fetchIndex(new Point(x,y), selectTile.getMapImage());
		
		if(index > 0 && index == selectTile.getIndex()){
			return;
		}
		
		Point p = format.datumOffset(tile);
		Point pp = format.formatCoord(tile.getPoint());
		x = pp.x + p.x;
		y = pp.y + p.y;
		
		
		if(index < 0){
			selectTile.setPoint(new Point(x, y));
			return;
		}
		if(tiles.size() > index && tiles.get(index) != null){
			return;
		}
		removeForList(selectTile);
		selectTile.setPoint(new Point(x, y));
		selectTile.setIndex(index);
		add(index, selectTile);
	}
	/**
	 * 释放选中的元素
	 */
	public void releaseSelectTile(){
		selectTile = null;
		offsetX = 0;
		offsetY = 0;
	}
	public Format getFormat() {
		return format;
	}
	public void setFormat(Format format) {
		this.format = format;
	}
	public List<Tile> getTiles() {
		return tiles;
	}
}

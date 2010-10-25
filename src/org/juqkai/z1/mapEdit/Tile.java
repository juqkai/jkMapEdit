package org.juqkai.z1.mapEdit;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.io.Serializable;

import org.juqkai.util.ZKLang;


/**
 * 砖块
 * @author juqkai(juqkai@gmail.com)
 */
public class Tile implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer x;
	private Integer y;
	//索引
	private int index = -1;
	private MapImage img;
	
	public Tile(Integer x, Integer y, MapImage mi){
		this.x = x;
		this.y = y;
		this.img = mi;
	}
	public void draw(Graphics2D g) {
		if(ZKLang.isNull(img)){
			return;
		}
		g.drawImage(img.getImg(), x , y , null);
	}
	//是否选中
	public boolean isSelected(int x, int y){
		//ZKFIXME 这个地方以后可以改成验证像素的方式 
		return 
		x >= this.x &&
		y >= this.y &&
		x <= this.x + img.getWidth() &&
		y <= this.y + img.getHeight();
	}
	@Override
	public String toString() {
		return "x:" + x + " y:" + y + " index:" + index+ " img:" + img.toString();
	}
	
	public Integer getX() {
		return x;
	}
	public void setX(Integer x) {
		this.x = x;
	}
	public Integer getY() {
		return y;
	}
	public void setY(Integer y) {
		this.y = y;
	}
	public Image getImg() {
		if(ZKLang.isNull(img)){
			return null;
		}
		return img.getImg();
	}
	public MapImage getMapImage(){
		return img;
	}
	public void setPoint(Point point) {
		x = point.x;
		y = point.y;
	}
	public Point getPoint(){
		return new Point(x, y);
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
}

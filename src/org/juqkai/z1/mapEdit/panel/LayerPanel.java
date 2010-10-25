package org.juqkai.z1.mapEdit.panel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.Serializable;

import javax.swing.JPanel;

import org.juqkai.z1.mapEdit.Tile;
import org.juqkai.z1.mapEdit.layer.Layer;
import org.juqkai.z1.mapEdit.layer.LayerEnum;

/**
 * 层面板
 * @author juqkai(juqkai@gmail.com)
 */
public class LayerPanel extends JPanel implements Serializable{
	private static final long serialVersionUID = 1L;
	private Layer layer;
	private LayerEnum layerEnum;
	
	public LayerPanel(Layer layer, LayerEnum le){
		this.layer = layer;
		layerEnum = le;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		layer.draw((Graphics2D) g);
    }
	/**
	 * 删除一个元素
	 * @param x
	 * @param y
	 * @author juqkai(juqkai@gmail.com) 2010-9-26
	 */
	public void removeTile(int x, int y) {
		layer.remove(x, y);
		repaint();
	}
	/**
	 * 添加一个元素
	 * @param x
	 * @param y
	 * @param imgUrl
	 * @author juqkai(juqkai@gmail.com) 2010-9-26
	 */
	public void add(Tile tile) {
		layer.add(tile);
		repaint();
	}
	/**
	 * 获取一个元素
	 * @param x
	 * @param y
	 * @return
	 * @author juqkai(juqkai@gmail.com) 2010-9-26
	 */
	public Tile fetchTile(int x, int y) {
		return layer.fetchTile(x, y);
	}
	/**
	 * 选择元素
	 * @param x
	 * @param y
	 */
	public Tile selectedTile(Integer x , Integer y){
		return layer.selectedTile(x, y);
	}
	/**
	 * 移动选中的元素
	 * @param x
	 * @param y
	 * @return 
	 */
	public void moveTile(Integer x, Integer y ){
		layer.move(x, y);
		repaint();
	}
	/**
	 * 释放选中的元素
	 */
	public void releaseSelectTile(){
		layer.releaseSelectTile();
	}

	public Layer getLayer() {
		return layer;
	}

	public LayerEnum getLayerEnum() {
		return layerEnum;
	}

}

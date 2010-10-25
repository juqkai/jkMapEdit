package org.juqkai.z1.mapEdit.panel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;
import org.juqkai.z1.mapEdit.EditModel;
import org.juqkai.z1.mapEdit.ImageCache;
import org.juqkai.z1.mapEdit.MapEdit;
import org.juqkai.z1.mapEdit.MapImage;
import org.juqkai.z1.mapEdit.CurrentMapInfo;
import org.juqkai.z1.mapEdit.Tile;
import org.juqkai.z1.mapEdit.layer.LayerEnum;
import org.juqkai.z1.mapEdit.layer.LayerFactory;
import org.juqkai.z1.mapEdit.tile.format.Format;

/**
 * 事件层面板,本层面板主要接收鼠标编辑事件
 * @author juqkai(juqkai@gmail.com) 2010-9-25
 */
public class EventPanel extends JPanel implements MouseListener, MouseMotionListener{
	private Tile tile;
	private static final long serialVersionUID = 1L;
	private MapEdit mapEdit;
	public EventPanel() {
		
	}
	private void init(){
		setSize(CurrentMapInfo.fetchWidth(), CurrentMapInfo.fetchHeight());
		setLocation(0, 0);
		//透明
		setOpaque(false);
		//添加事件
		addMouseListener(this);
		addMouseMotionListener(this);
		tile = new Tile(0, 0, null);
	}
	
	Point point = new Point(0,0);
	@Override
	protected void paintComponent(Graphics g) {
		//ZKFIXME 这个地方应该有性能问题,后面记得修改
		super.paintComponent(g);
		if(tile != null && tile.getImg() != null){
			Format fm = LayerFactory.makeFormat(CurrentMapInfo.currentLayer);
			Point po = fm.datumOffset(tile);
			g.drawImage(tile.getImg(), point.x + po.x, point.y + po.y , null);
		}
		g.drawString("("+point.x+", "+point.y+")", 10, 10);
		g.setColor(new Color(255, 0, 0));
		g.drawRect(0, 0, this.getWidth() - 1, this.getHeight() - 1);
	}
	
	
	public void mouseClicked(MouseEvent e) {
		switch(CurrentMapInfo.mode){
		case NULL:
			break;
		case EDIT:
			edit(e);
			break;
		case CLEAR:
			clear(e);
			break;
		case MOVE:
//			moveItem(e);
			break;
		}
	}
	public void mouseMoved(MouseEvent e) {
		suspensoid(e);
	}
	/**
	 * 悬浮
	 * 
	 * @author juqkai(juqkai@gmail.com) 2010-9-26
	 * @param e 
	 */
	private void suspensoid(MouseEvent e){
		if(CurrentMapInfo.mode == EditModel.EDIT && tile != null){
			tile.setX(e.getX());
			tile.setY(e.getY());
		}
		point = e.getPoint();
		repaint();
	}
	/**
	 * 鼠标按下后移动
	 */
	public void mouseDragged(MouseEvent e) {
		if(CurrentMapInfo.currentLayer == LayerEnum.MOVE){
			if(CurrentMapInfo.mode == EditModel.EDIT){
				edit(e);
				suspensoid(e);
				return;
			}
			if(CurrentMapInfo.mode == EditModel.CLEAR){
				clear(e);
				return;
			}
		}
		if(CurrentMapInfo.mode == EditModel.MOVE){
			moveItem(e);
		}
	}
	/**
	 * 清除元素
	 * @param e
	 */
	private void clear(MouseEvent e) {
		mapEdit.fetchLayerPanel().removeTile(e.getX(), e.getY());
	}

	/**
	 * 移动元素
	 * @param e
	 */
	private void moveItem(MouseEvent e) {
		mapEdit.fetchLayerPanel().moveTile(e.getX(), e.getY());
	}

	/**
	 * 编辑
	 * @param e
	 */
	private void edit(MouseEvent e){
		mapEdit.addTile(e.getX(), e.getY());
	}
	
	
	/**
	 * 进入panel
	 */
	public void mouseEntered(MouseEvent e) {
		if(CurrentMapInfo.mode == EditModel.EDIT){
			MapImage url = CurrentMapInfo.imgUrl;
			if(CurrentMapInfo.currentLayer.equals(LayerEnum.MOVE)){
				url = ImageCache.make().get(LayerFactory.imgmove);
			}
			tile = new Tile(e.getX(), e.getY(), url);
		}
	}
	/**
	 * 离开panel
	 */
	public void mouseExited(MouseEvent e) {
		if(CurrentMapInfo.mode == EditModel.MOVE){
			mapEdit.fetchLayerPanel().releaseSelectTile();
		}
		tile = null;
		repaint();
	}
	/**
	 * 按下鼠标
	 */
	public void mousePressed(MouseEvent e) {
		if(CurrentMapInfo.mode == EditModel.MOVE){
			mapEdit.fetchLayerPanel().selectedTile(e.getX(), e.getY());
		}
	}
	/**
	 * 释放鼠标
	 */
	public void mouseReleased(MouseEvent e) {
		if(CurrentMapInfo.mode == EditModel.MOVE){
			mapEdit.fetchLayerPanel().releaseSelectTile();
		}
	}
	public MapEdit getMapEdit() {
		return mapEdit;
	}
	public void show(MapEdit mapEdit) {
		this.mapEdit = mapEdit;
		init();
	}
}

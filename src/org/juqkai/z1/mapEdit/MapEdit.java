/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.juqkai.z1.mapEdit;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.juqkai.util.ZKLang;
import org.juqkai.z1.mapEdit.layer.LayerEnum;
import org.juqkai.z1.mapEdit.layer.LayerFactory;
import org.juqkai.z1.mapEdit.panel.LayerPanel;
import org.juqkai.z1.mapEdit.save.MapSave;
import org.juqkai.z1.mapEdit.save.MapSerializable;
import org.juqkai.z1.mapEdit.save.WawaDemo1Save;

/**
 *
 * @author juqkai <juqkai@gmail.com>
 */
public class MapEdit {
    
    private Map<LayerEnum, LayerPanel> panels = new HashMap<LayerEnum, LayerPanel>();
    private MapSave mapSave;
    private File saveFile;

    public MapEdit(){
    	this(30, 30, 30, 30, "");
    }
    public MapEdit(int hcount, int vcount, int tileH, int tileW, String resource){
    	CurrentMapInfo.width = hcount;
    	CurrentMapInfo.height = vcount;
    	CurrentMapInfo.tileH = tileH;
    	CurrentMapInfo.tileW = tileW;
    	CurrentMapInfo.resourcePath = resource;
    	initPanel();
    }
    /**
     * 初始化面板
     * @author juqkai(juqkai@gmail.com) 2010-9-26
     */
	private void initPanel() {
		panels.put(LayerEnum.FLOOR, makePanel(LayerEnum.FLOOR));
		panels.put(LayerEnum.MOVE, makePanel(LayerEnum.MOVE));
		panels.put(LayerEnum.BULIDING, makePanel(LayerEnum.BULIDING));
		panels.put(LayerEnum.GRID, makePanel(LayerEnum.GRID));
	}
	/**
	 * 创建面板
	 * @param le
	 * @return
	 * @author juqkai(juqkai@gmail.com) 2010-9-26
	 */
	private LayerPanel makePanel(LayerEnum le){
		LayerPanel lp = LayerFactory.makePanel(le);
		lp.setSize(CurrentMapInfo.fetchWidth(), CurrentMapInfo.fetchHeight());
		lp.setLocation(0, 0);
		//透明
		lp.setOpaque(false);
		return lp;
	}
    
    /**
     * 保存
     * @author juqkai(juqkai@gmail.com) 2010-9-27
     */
    public void save(){
    	System.out.println("正在进行保存!");
    	if(ZKLang.isNull(saveFile)){
    		throw new NullPointerException();
    	}
//    	mapSave = new WawaDemo1Save();
    	mapSave = new MapSerializable();
    	mapSave.save(saveFile, panels);
    	
    }
    
    public void load(){
    	MapSerializable ms = new MapSerializable();
    	ms.load();
    }
    
	/**
	 * 设置当前操作的素材
	 * @param mi
	 * @author juqkai(juqkai@gmail.com)
	 */
	public void selectedImgUrl(MapImage mi){
		ImageCache.make().put(mi);
		CurrentMapInfo.imgUrl = mi;
	}
	/**
	 * 选择要编辑层
	 * @param layerName
	 * @author juqkai(juqkai@gmail.com)
	 */
	public void selectedLayerName(LayerEnum layerName){
		CurrentMapInfo.currentLayer = layerName;
	}
	/**
	 * 添加砖块
	 * @param x
	 * @param y
	 */
	public void addTile(Integer x, Integer y) {
		if(canCrossTheBorder(x, y)){
			return;
		}
		MapImage mi = CurrentMapInfo.imgUrl;
		if(CurrentMapInfo.currentLayer == LayerEnum.MOVE){
			mi = ImageCache.make().get(LayerFactory.imgmove);
		}
		Tile tile = new Tile(x, y, mi);
		panels.get(CurrentMapInfo.currentLayer).add(tile);
	}
	/**
	 * 是否越界
	 * @param x
	 * @param y
	 * @return
	 * @author juqkai(juqkai@gmail.com) 2010-9-27
	 */
	private boolean canCrossTheBorder(Integer x, Integer y){
		return 
		x < 0 || 
		y < 0 || 
		x > CurrentMapInfo.fetchWidth() || 
		y > CurrentMapInfo.fetchHeight();
		
//		if(x < 0 || y < 0 || x > CurrentMapInfo.fetchWidth() || y > CurrentMapInfo.fetchHeight())
//			return true;
//		return false;
	}
	public Map<LayerEnum, LayerPanel> fetchPanels(){
		return panels;
	}
	/**
	 * 是否显示指定的面板
	 * @param le
	 * @param visible
	 */
	public void showPanel(LayerEnum le, boolean visible){
		panels.get(le).setVisible(visible);
	}
	
	/**
	 * 获取当前正在使用的 LayerPanel
	 * @return
	 * @author juqkai(juqkai@gmail.com) 2010-10-13
	 */
	public LayerPanel fetchLayerPanel(){
		return panels.get(CurrentMapInfo.currentLayer);
	}
	public File getSaveFile() {
		return saveFile;
	}
	public void setSaveFile(File saveFile) {
		this.saveFile = saveFile;
	}
}

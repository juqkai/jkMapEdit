/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.juqkai.z1.mapEdit;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JPanel;

import org.juqkai.z1.mapEdit.layer.LayerEnum;
import org.juqkai.z1.mapEdit.panel.EventPanel;

/**
 *
 * @author juqkai <juqkai@gmail.com>
 */
public class MapViewPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private MapEdit mapEdit;
	private EventPanel eventPanel;
//	private Map<String, LayerPanel> panels = new HashMap<String, LayerPanel>();
	
	public MapViewPanel() {
		eventPanel = new EventPanel();
//		init();
	}
	/**
	 * 初始化面板
	 * @author juqkai(juqkai@gmail.com)
	 */
	private void initPanel() {
		setPreferredSize(new Dimension(CurrentMapInfo.fetchWidth(), CurrentMapInfo.fetchHeight()));
		add(eventPanel);
		add(mapEdit.fetchPanels().get(LayerEnum.GRID));
		add(mapEdit.fetchPanels().get(LayerEnum.MOVE));
		add(mapEdit.fetchPanels().get(LayerEnum.BULIDING));
		add(mapEdit.fetchPanels().get(LayerEnum.FLOOR));
		repaint();
	}
	/**
	 * 清理无用的组件
	 * @author juqkai(juqkai@gmail.com) 2010-9-29
	 */
	private void clear(){
		for(Component com : getComponents()){
			System.out.println("~~~:" + com.equals(eventPanel));
			if(!com.equals(eventPanel)){
				com.setVisible(false);
				com.setEnabled(false);
				this.remove(com);
			}
		}
	}
	public MapEdit getMapEdit() {
		return mapEdit;
	}
	public void show(MapEdit mapEdit) {
		this.mapEdit = mapEdit;
		eventPanel.show(mapEdit);
		clear();
		initPanel();
	}
}

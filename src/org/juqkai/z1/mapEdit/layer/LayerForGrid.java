package org.juqkai.z1.mapEdit.layer;

import java.awt.Graphics2D;
import org.juqkai.z1.mapEdit.MapImage;
import org.juqkai.z1.mapEdit.CurrentMapInfo;
import org.juqkai.z1.mapEdit.Tile;

/**
 * 格子层,无砖块
 * @author juqkai(juqkai@gmail.com)
 */
public class LayerForGrid extends Layer{
    
	public void draw(Graphics2D g) {
    	int t = CurrentMapInfo.tileW;
    	for(int i = 0; i < CurrentMapInfo.width; i ++){
    		g.drawLine(t, 0, t, CurrentMapInfo.fetchHeight());
    		t += CurrentMapInfo.tileW;
    	}
    	t = CurrentMapInfo.tileH;
    	for(int i = 0; i < CurrentMapInfo.height; i ++){
    		g.drawLine(0, t, CurrentMapInfo.fetchWidth(), t);
    		t += CurrentMapInfo.tileH;
    	}
	}
	public void add(Integer x, Integer y, MapImage imgUrl) {
		return;
	}
	public void add(Tile tile) {
		return;
	}
}

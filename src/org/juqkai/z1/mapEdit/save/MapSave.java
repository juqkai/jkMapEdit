package org.juqkai.z1.mapEdit.save;

import java.io.File;
import java.util.Map;

import org.juqkai.z1.mapEdit.layer.LayerEnum;
import org.juqkai.z1.mapEdit.panel.LayerPanel;

/**
 * 保存地图信息的接口
 * @author juqkai(juqkai@gmail.com) 2010-9-27
 */
public interface MapSave {

	/**
	 * 保存
	 * @param panels
	 * @author juqkai(juqkai@gmail.com) 2010-9-27
	 */
	public void save(File file, Map<LayerEnum, LayerPanel> panels);

}

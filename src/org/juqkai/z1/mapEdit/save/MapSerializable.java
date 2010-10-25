package org.juqkai.z1.mapEdit.save;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;

import org.juqkai.z1.mapEdit.layer.LayerEnum;
import org.juqkai.z1.mapEdit.panel.LayerPanel;

/**
 * 地图信息序列化
 * 
 * @author juqkai(juqkai@gmail.com) 2010-9-30
 */
public class MapSerializable implements MapSave {
	private File file;
	private ObjectOutputStream oos;

	public MapSerializable() {
		file = new File("d:/temp/jk.jk");
		try {
			oos = new ObjectOutputStream(new FileOutputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void save(File file, Map<LayerEnum, LayerPanel> panels) {
		try {
			for (LayerPanel lp : panels.values()) {
				oos.writeObject(lp);
			}
			oos.flush();
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

package org.juqkai.z1.mapEdit.save;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.Map.Entry;

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
//			for (LayerPanel lp : panels.values()) {
//				oos.writeObject(lp);
//			}
			oos.writeObject(panels);
			oos.flush();
			oos.close();
			
			load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void load() {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
			Map<LayerEnum, LayerPanel> panels = (Map<LayerEnum, LayerPanel>) ois.readObject();
			for(Entry<LayerEnum, LayerPanel> e : panels.entrySet()){
				System.out.println(e.getKey());
				System.out.println(e.getValue());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

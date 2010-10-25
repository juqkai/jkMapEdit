package org.juqkai.z1.mapEdit;

import java.util.HashMap;
import java.util.Map;

import org.juqkai.util.ZKLang;

/**
 * 图片缓存
 * @author juqkai(juqkai@gmail.com)
 */
public class ImageCache {
	private Map<String, MapImage> images = new HashMap<String, MapImage>();
	private static ImageCache ic;
	public static ImageCache make(){
		if(ZKLang.isNull(ic)){
			ic = new ImageCache();
		}
		return ic;
	}
	
	public void put(MapImage img){
		images.put(img.fetchPaht(), img);
	}
	public MapImage get(String url){
		return images.get(url);
	}
}

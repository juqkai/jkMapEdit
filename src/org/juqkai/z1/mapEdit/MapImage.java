package org.juqkai.z1.mapEdit;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.Serializable;
import java.net.URL;

import org.juqkai.z1.mapEdit.exception.MapEditException;

/**
 * 地图中用到的图片信息
 * @author juqkai(juqkai@gmail.com) 2010-9-26
 */
public class MapImage implements Serializable{
	//资源目录
	private String root = "";
	//图片相对路径
	private String relative = "";
	//完整路径
	private String path = "";
	private transient Image img ;
	
	public MapImage(String path) throws MapEditException{
		if(path.indexOf(CurrentMapInfo.resourcePath) < 0){
			throw new MapEditException("你访问的资源不在资源库内!请重新设置资源!");
		}
		String re = path.replace(CurrentMapInfo.resourcePath, "");
		relative = re;
		this.path = path;
		img = Toolkit.getDefaultToolkit().getImage(path);
	}
	
	public MapImage(URL url, String path){
		root = url.getPath();
		relative = "";
		this.path = path;
		img = Toolkit.getDefaultToolkit().getImage(url);
	}
	
	/**
	 * 得到图片的宽度
	 * @return
	 * @author juqkai(juqkai@gmail.com) 2010-9-26
	 */
	public Integer getWidth(){
		return img.getWidth(null);
	}
	/**
	 * 得到图片的高度
	 * @return
	 * @author juqkai(juqkai@gmail.com) 2010-9-26
	 */
	public Integer getHeight(){
		return img.getHeight(null);
	}
	/**
	 * 获取完整路径
	 * @return
	 * @author juqkai(juqkai@gmail.com) 2010-9-26
	 */
	public String fetchPaht(){
		return path;
	}
	/**
	 * 资源库路径
	 * @return
	 * @author juqkai(juqkai@gmail.com) 2010-9-27
	 */
	public String getRoot() {
		return root;
	}
	/**
	 * 相对路径
	 * @return
	 * @author juqkai(juqkai@gmail.com) 2010-9-27
	 */
	public String getRelative() {
		return relative;
	}
	public Image getImg() {
		return img;
	}
}

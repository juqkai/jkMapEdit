package org.juqkai.z1.mapEdit;

import org.juqkai.z1.mapEdit.layer.LayerEnum;

/**
 * 当前地图的信息
 * @author juqkai(juqkai@gmail.com) 2010-9-29
 */
public class CurrentMapInfo {
	/**
	 * 地图横向格子数
	 */
	public static Integer width = 0;
	/**
	 * 地图纵向格子数
	 */
	public static Integer height = 0;
	/**
	 * 砖块宽
	 */
	public static Integer tileW = 0;
	/**
	 * 砖块高
	 */
	public static Integer tileH = 0;
	
	/**
	 * 外边
	 */
	public static Integer margin = 60;
	
	/**
	 * 当前使用的素材地址
	 */
    public static MapImage imgUrl = null;
    /**
     * 当前编辑的层
     */
    public static LayerEnum currentLayer = LayerEnum.MOVE;
    /**
     * 操作模式
     */
    public static EditModel mode = EditModel.NULL;
	/**
	 * 资源库路径
	 */
    public static String resourcePath = "";
	/**
	 * 宽度
	 * @return
	 * @author juqkai(juqkai@gmail.com)
	 */
	public static Integer fetchWidth(){
		return width * tileW;
	}
	/**
	 * 高度
	 * @return
	 * @author juqkai(juqkai@gmail.com)
	 */
	public static Integer fetchHeight(){
		return height * tileH;
	}
}

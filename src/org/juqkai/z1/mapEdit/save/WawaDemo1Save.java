package org.juqkai.z1.mapEdit.save;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.juqkai.z1.mapEdit.CurrentMapInfo;
import org.juqkai.z1.mapEdit.Tile;
import org.juqkai.z1.mapEdit.layer.Layer;
import org.juqkai.z1.mapEdit.layer.LayerEnum;

public class WawaDemo1Save implements MapSave{
	private FileWriter fw;

	public void save(File file, Map<LayerEnum, Layer> panels) {
		try {
			fw = new FileWriter(file);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			writeHead();
			makeVar(panels);
			floorData(panels);
			buildingData(panels);
			writeRole();
			writePanel();
			writeMap(panels);
			writeMove(panels);
			writeEnd();
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 背景数据
	 * 
	 * @author juqkai(juqkai@gmail.com) 2010-9-27
	 * @throws IOException 
	 */
	private void floorData(Map<LayerEnum, Layer> panels) throws IOException{
		StringBuilder sb = new StringBuilder();
		sb.append("private var bg1:Array=[");
		sb.append("\n");
		sb.append(fetchJsons(panels.get(LayerEnum.FLOOR)));
		sb.append("];");
		sb.append("\n");
		fw.write(sb.toString());
	}
	/**
	 * 保存建筑
	 * @param panels
	 * @throws IOException
	 * @author juqkai(juqkai@gmail.com) 2010-9-28
	 */
	private void buildingData(Map<LayerEnum, Layer> panels) throws IOException{
		StringBuilder sb = new StringBuilder();
		sb.append("private var front1:Array=[");
		sb.append("\n");
		sb.append(fetchJsons(panels.get(LayerEnum.BULIDING)));
		sb.append("];");
		sb.append("\n");
		fw.write(sb.toString());
	}
	/**
	 * 角色进入的位置
	 * @throws IOException
	 * @author juqkai(juqkai@gmail.com) 2010-9-28
	 */
	private void writeRole() throws IOException{
		StringBuilder sb = new StringBuilder();
		sb.append("private var role1:Object={x:1,y:1};");
		sb.append("\n");
		sb.append("");
		fw.write(sb.toString());
	}
	private void writePanel() throws IOException{
		StringBuilder sb = new StringBuilder();
		sb.append("private var panle1:Object={width:");
		sb.append(CurrentMapInfo.fetchWidth());
		sb.append(",height:");
		sb.append(CurrentMapInfo.fetchHeight());
		sb.append(",center:0,y:0,x:0};");
		sb.append("\n");
		sb.append("");
		sb.append("");
		fw.write(sb.toString());
	}
	private String fetchJsons(Layer lp){
		if(lp == null){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		boolean  i = false;
		for(Tile t : lp.getTiles()){
			if(t == null || t.getMapImage() == null){
				continue;
			}
			if(i){
				sb.append(",");
				sb.append("\n");
			}
			sb.append(tileToString(t));
			i = true;
		}
		sb.append("");
		return sb.toString();
	}
	/**
	 * 地图层
	 * @param panels
	 * @throws IOException
	 * @author juqkai(juqkai@gmail.com) 2010-9-28
	 */
	private void writeMap(Map<LayerEnum, Layer> panels) throws IOException{
		List<Tile> ts = panels.get(LayerEnum.MOVE).getTiles();
		
		StringBuilder sb = new StringBuilder();
		int t = 0;
		sb.append("public var mappoint1:Array = ");
		sb.append("[");
		sb.append("\n");
		for(int i = 0; i < CurrentMapInfo.width; i ++){
			if(i > 0){
				sb.append(",");
				sb.append("\n");
			}
			sb.append("[");
			for(int j = 0; j < CurrentMapInfo.height; j ++){
				Tile tile = ts.get(t);
				if(tile == null){
					sb.append("8");
				}else{
					sb.append("4");
				}
				t++;
			}
			sb.append("]");
		}
		sb.append("]");
		sb.append("\n");
		fw.write(sb.toString());
	}
	/**
	 * 移动层
	 * @param panels
	 * @throws IOException
	 * @author juqkai(juqkai@gmail.com) 2010-9-28
	 */
	private void writeMove(Map<LayerEnum, Layer> panels) throws IOException{
		List<Tile> ts = panels.get(LayerEnum.MOVE).getTiles();
		
		StringBuilder sb = new StringBuilder();
		int t = 0;
		sb.append("public var map:Array = ");
		sb.append("[");
		sb.append("\n");
		for(int i = 0; i < CurrentMapInfo.width; i ++){
			if(i > 0){
				sb.append(",");
				sb.append("\n");
			}
			sb.append("[");
			for(int j = 0; j < CurrentMapInfo.height; j ++){
				Tile tile = ts.get(t);
				if(tile == null){
					sb.append("0");
				}else{
					sb.append("1");
				}
				t++;
			}
			sb.append("]");
		}
		sb.append("]");
		sb.append("\n");
		fw.write(sb.toString());
	}
	/**
	 * 将元素转成JSON对象
	 * {url:bigtree,width:130,height:167,x:250,y:250,rotation:0}
	 * @param tile
	 * @return
	 * @author juqkai(juqkai@gmail.com) 2010-9-27
	 */
	private String tileToString(Tile tile){
		StringBuilder sb = new StringBuilder();
		sb.append("{url:");
		sb.append(toPathVar(tile.getMapImage().getRelative()));
		sb.append(",width:");
		sb.append(tile.getMapImage().getWidth());
		sb.append(",height:");
		sb.append(tile.getMapImage().getHeight());
		sb.append(",x:");
		sb.append(tile.getX());
		sb.append(",y:");
		sb.append(tile.getY());
		sb.append(",rotation:0");
		sb.append("}");
		
		return sb.toString();
	} 
	/**
	 * 定义变量
	 * [Embed("pic/build/rbg.jpg")]
			[Bindable]
			private static var rbg:Class

	 * 
	 * @author juqkai(juqkai@gmail.com) 2010-9-27
	 * @throws IOException 
	 */
	private void makeVar(Map<LayerEnum, Layer> panels) throws IOException{
		Set<String> imgs = new HashSet<String>();
		StringBuilder sb = new StringBuilder();
		for(Layer layer : panels.values()){
			for(Tile tile : layer.getTiles()){
				if(tile != null && tile.getMapImage() != null){
					imgs.add(tile.getMapImage().getRelative());
				}
			}
		}
		
		for(String path : imgs){
			if(path.equals("") || path == ""){
				continue;
			}
			path = path.substring(1);
			path = path.replace('\\', '/');
			sb.append("[Embed(\"" + path + "\")]");
			sb.append("\n");
			
			sb.append("[Bindable]");
			sb.append("\n");
			
			sb.append("private static var "+ toPathVar(path) +":Class");
			sb.append("\n");
			sb.append("\n");
		}
		
		fw.write(sb.toString());
	}
	/**
	 * 转换路径成变量
	 * @param path
	 * @return
	 * @author juqkai(juqkai@gmail.com) 2010-9-27
	 */
	private String toPathVar(String path){
		String x = path.replace('/', '_');
		x = x.replace('\\', '_');
		x = x.replace('.', '_');
		return x;
	}
	/**
	 * 写入头信息
	 * @throws IOException
	 * @author juqkai(juqkai@gmail.com) 2010-9-27
	 */
	private void writeHead() throws IOException{
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		sb.append("\n");
		sb.append("	<mx:Application xmlns:mx=\"http://www.adobe.com/2006/mxml\" layout=\"absolute\" width=\"0\" height=\"0\">");
		sb.append("\n");
		sb.append("	<mx:Script>");
		sb.append("\n");
		sb.append("<![CDATA[");;
		sb.append("\n");
		fw.write(sb.toString());
	}
	/**
	 * 写入尾信息
	 * @throws IOException
	 * @author juqkai(juqkai@gmail.com) 2010-9-27
	 */
	private void writeEnd() throws IOException{
		StringBuilder sb = new StringBuilder();
		sb.append("private var maplist1:Object={bg:bg1,front:front1,role:role1,panle:panle1,map:mappoint1,data:map};");
		sb.append("\n");
		
		sb.append("public function GetMapObject(id:int=0):Object");
		sb.append("\n");
		sb.append("	{");
		sb.append("\n");
		sb.append("		return maplist1;");
		sb.append("\n");
		sb.append("	}");
		sb.append("\n");
		sb.append("	]]>");
		sb.append("\n");
		sb.append("	</mx:Script>");
		sb.append("\n");
		sb.append("</mx:Application>");
		sb.append("\n");
		sb.append("");
		fw.write(sb.toString());
	}

}

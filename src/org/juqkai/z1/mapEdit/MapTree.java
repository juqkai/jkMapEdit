/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.juqkai.z1.mapEdit;

import java.io.File;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import org.juqkai.z1.mapEdit.exception.MapEditException;

/**
 *
 * @author juqkai <juqkai@gmail.com>
 */
public class MapTree{
	private MapEdit mapEdit;
	
	//根目录
	private String rootPath;
	
	private JTree tree;
	private DefaultTreeModel dtm;
	private DefaultMutableTreeNode root;
	
	public MapTree() {
		init();
	}
	
	private void init(){
		root = new DefaultMutableTreeNode("素材");
		dtm = new DefaultTreeModel(root);
		tree = new JTree(dtm);
		
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				if(mapEdit == null){
					return ;
				}
				String relative = "";
				int i = 0;
				for(Object tp : e.getPath().getPath()){
					if(i < 2){
						i++;
						continue;
					}
					relative += "\\" + tp;
				}
				String path = rootPath + relative;
				System.out.println(path);
				MapImage mi;
				try {
					mi = new MapImage(path);
					mapEdit.selectedImgUrl(mi);
				} catch (MapEditException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	public void display(String url){
		rootPath = url;
		root.removeAllChildren();
		display(root, new File(url));
		dtm.reload();
	}
	private void display(DefaultMutableTreeNode parse, File file){
		DefaultMutableTreeNode dmt = new DefaultMutableTreeNode(file.getName());
		parse.add(dmt);
		for(File fi : file.listFiles()){
			if(fi.isDirectory()){
				display(dmt, fi);
			}
			if(fi.isFile()){
				dmt.add(new DefaultMutableTreeNode(fi.getName()));
			}
		}
	}
	
	public JTree fetchJTree(){
		return tree;
	}
	public MapEdit getMapEdit() {
		return mapEdit;
	}
	public void setMapEdit(MapEdit mapEdit) {
		this.mapEdit = mapEdit;
	}
}

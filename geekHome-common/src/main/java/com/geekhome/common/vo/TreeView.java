package com.geekhome.common.vo;

import java.util.List;

/**
 * @Description: bootstrap-treeview
 * @author handx
 * @date 2017年10月13日 上午11:11:50
 * @version V1.0
 */
public class TreeView {

	private String text;
	private List<TreeView> nodes;
	private String icon;
	private String selectedIcon;
	private Long labelId;
	
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getSelectedIcon() {
		return selectedIcon;
	}

	public void setSelectedIcon(String selectedIcon) {
		this.selectedIcon = selectedIcon;
	}

	public Long getLabelId() {
		return labelId;
	}

	public void setLabelId(Long labelId) {
		this.labelId = labelId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<TreeView> getNodes() {
		return nodes;
	}

	public void setNodes(List<TreeView> nodes) {
		this.nodes = nodes;
	}

	public TreeView() {
		super();
	}

	public TreeView(String text, List<TreeView> nodes) {
		super();
		this.text = text;
		this.nodes = nodes;
	}

	@Override
	public String toString() {
		return "TreeView [text=" + text + ", nodes=" + nodes + "]";
	}

}

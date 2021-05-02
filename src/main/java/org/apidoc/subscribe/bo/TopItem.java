package org.apidoc.subscribe.bo;

import java.util.List;

import lombok.Data;
import lombok.ToString;

/**
 * 最顶层之item节点
 * 
 * @author user
 *
 */
@Data
@ToString
public class TopItem {
	private String description;
	private String name;
	private List<Item> items;
}

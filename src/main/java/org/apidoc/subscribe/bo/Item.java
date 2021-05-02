package org.apidoc.subscribe.bo;

import java.util.List;

import lombok.Data;

/**
 * 亚层节点item,从属于item(top item)
 * 
 * @author user
 *
 */
@Data
public class Item {
	private String name;
	private List<Request> request;
}

package org.apidoc.subscribe.bo;

import lombok.Data;

/**
 * postman.json中的info节点,从属于元节点
 * 
 * @author user
 *
 */
@Data
public class Info {
	private String name;
	private String description;
}

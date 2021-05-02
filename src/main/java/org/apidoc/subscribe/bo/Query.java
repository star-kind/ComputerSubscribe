package org.apidoc.subscribe.bo;

import lombok.Data;

/**
 * query节点,从属于url=>request=>item=>item(original)
 * 
 * @author user
 *
 */
@Data
public class Query {
	private String key;
	private String description;
}

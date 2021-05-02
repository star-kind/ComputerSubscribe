package org.apidoc.subscribe.bo;

import lombok.Data;

/**
 * Header节点,从属于request=>item=>item(original)
 * 
 * @author user
 *
 */
@Data
public class Header {
	private String key;
	private String description;
}

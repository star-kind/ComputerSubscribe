package org.apidoc.subscribe.bo;

import java.util.List;

import lombok.Data;

/**
 * 从属于request=>item=>item(original)
 * 
 * @author user
 *
 */
@Data
public class URL {
	private String raw;
	private String protocol;
	private String host;
	private String port;
	private String path;

	private List<Query> query;
}

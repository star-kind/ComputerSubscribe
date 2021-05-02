package org.apidoc.subscribe.bo;

import java.util.List;

import lombok.Data;

/**
 * request节点,从属于item=>item(original)
 * 
 * @author user
 *
 */
@Data
public class Request {
	private String method;
	private List<Header> header;
	private List<URL> url;
}

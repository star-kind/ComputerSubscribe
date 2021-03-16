package com.computer.subscribe.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

/**
 * 逆向工程生成类
 * 
 * @author user
 *
 */
public class GenerateConfig {
	public static void main(String[] args) throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException  {
		ArrayList<String> warnings = new ArrayList<String>();
		boolean overwrite = true;

		InputStream is = GenerateConfig.class.getClassLoader().getResource("GenerateConfigure.xml").openStream();

		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(is);
		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		myBatisGenerator.generate(null);

		is.close();
		System.out.println("生成代码成功，刷新项目，查看文件");
	}
}

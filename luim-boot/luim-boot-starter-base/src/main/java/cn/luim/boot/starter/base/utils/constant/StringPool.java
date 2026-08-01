package cn.luim.boot.starter.base.utils.constant;

/**
 * 字符串常量池
 *
 * @author yang.lu
 */
public interface StringPool {

	// 基础字符串
	String SPACE = " ";

	// 路径分隔符字符串
	String SLASH = "/";

	// 连接符字符串
	String UNDERLINE = "_";

	String BEARER_TYPE = "Bearer";
	String BEARER_TOKEN = BEARER_TYPE + SPACE;
	String BASIC_TYPE = "Basic";
	String BASIC_TOKEN = BASIC_TYPE + SPACE;
}

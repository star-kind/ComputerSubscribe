package com.computer.subscribe.anotation;

/**
 * <p>
 * 常为校验参数而烦,如空指针异常(NPE)
 * </p>
 * <p>
 * 但{javax.validation}提供的方式有时不足以灵活
 * </p>
 * <p>
 * 比如说在实体每个属性上加上非空校验注解
 * </p>
 * <p>
 * 但有些操作是不需要提交所有的参数,这时对应的属性就会报错
 * </p>
 * <p>
 * 遂自创自定义注解,只校验指定的实体属性
 * </p>
 * 
 * 本注解为,校验属性是否含有空指针,为空则报异常
 * 
 * @author user
 *
 */
public class ValidateAttributeNPE {
	// TODO ValidateAttributeNPE
}

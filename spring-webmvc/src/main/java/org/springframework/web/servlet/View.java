/*
 * Copyright 2002-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.web.servlet;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;

/**
 * MVC View for a web interaction. Implementations are responsible for rendering
 * content, and exposing the model. A single view exposes multiple model attributes.
 *
 * <p>This class and the MVC approach associated with it is discussed in Chapter 12 of
 * <a href="http://www.amazon.com/exec/obidos/tg/detail/-/0764543857/">Expert One-On-One J2EE Design and Development</a>
 * by Rod Johnson (Wrox, 2002).
 *
 * <p>View implementations may differ widely. An obvious implementation would be
 * JSP-based. Other implementations might be XSLT-based, or use an HTML generation library.
 * This interface is designed to avoid restricting the range of possible implementations.
 *
 * <p>Views should be beans. They are likely to be instantiated as beans by a ViewResolver.
 * As this interface is stateless, view implementations should be thread-safe.
 *
 * @author Rod Johnson
 * @author Arjen Poutsma
 * @see org.springframework.web.servlet.view.AbstractView
 * @see org.springframework.web.servlet.view.InternalResourceView
 */
/*
 * 视图基础接口
 */
public interface View {

	/**
	 * Name of the {@link HttpServletRequest} attribute that contains the response status code.
	 * <p>Note: This attribute is not required to be supported by all View implementations.
	 */
	/*
	 * HttpServletRequest中的属性名，其值是响应状态码，并不是被所有的视图都支持
	 */
	String RESPONSE_STATUS_ATTRIBUTE = View.class.getName() + ".responseStatus";

	/**
	 * Name of the {@link HttpServletRequest} attribute that contains a Map with path variables.
	 * The map consists of String-based URI template variable names as keys and their corresponding
	 * Object-based values -- extracted from segments of the URL and type converted.
	 *
	 * <p>Note: This attribute is not required to be supported by all View implementations.
	 */
	/*
	 * HttpServletRequest中的属性名,其值是路径变量map，键为uri模板上的变量名，值为uri上对应的值
	 */
	String PATH_VARIABLES = View.class.getName() + ".pathVariables";

	/**
	 * The {@link MediaType} selected during content negotiation, which may be
	 * more specific than the one the View is configured with. For example:
	 * "application/vnd.example-v1+xml" vs "application/*+xml".
	 */
	/*
	 * 约定的MediaType
	 */
	String SELECTED_CONTENT_TYPE = View.class.getName() + ".selectedContentType";

	/**
	 * Return the content type of the view, if predetermined.
	 * <p>Can be used to check the content type upfront,
	 * before the actual rendering process.
	 * @return the content type String (optionally including a character set),
	 * or {@code null} if not predetermined.
	 */
	/*
	 * 视图的ContentType
	 * 如果预先定义，可以用于在实际渲染过程之前检查内容类型
	 * @return String 预先定义的内容类型（可选包括character set），或者如果没有预先确定，则返回null
	 */
	String getContentType();

	/**
	 * Render the view given the specified model.
	 * <p>The first step will be preparing the request: In the JSP case,
	 * this would mean setting model objects as request attributes.
	 * The second step will be the actual rendering of the view,
	 * for example including the JSP via a RequestDispatcher.
	 * @param model Map with name Strings as keys and corresponding model
	 * objects as values (Map can also be {@code null} in case of empty model)
	 * @param request current HTTP request
	 * @param response HTTP response we are building
	 * @throws Exception if rendering failed
	 */
	/*
	 * 用指定model渲染视图
	 * 1 准备request请求对象; 以jsp为例，这意味着设置model数据到request域中
	 * 2 渲染视图
	 * @param model 键和对应model的Map
	 * @param request  http request
	 * @param response 正构建的http response
	 * @throws Exception 渲染失败抛出异常
	 */
	void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception;

}

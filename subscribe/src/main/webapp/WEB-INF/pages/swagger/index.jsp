<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<c:set var="projectUrl" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Swagger UI</title>
        <link rel="icon" type="image/png" href="${projectUrl}/swaggerbase/images/favicon-32x32.png" sizes="32x32"/>
        <link rel="icon" type="image/png" href="${projectUrl}/swaggerbase/images/favicon-16x16.png" sizes="16x16"/>
        <link href='${projectUrl}/swaggerbase/css/typography.css' media='screen' rel='stylesheet' type='text/css'/>
        <link href='${projectUrl}/swaggerbase/css/reset.css' media='screen' rel='stylesheet' type='text/css'/>
        <link href='${projectUrl}/swaggerbase/css/screen.css' media='screen' rel='stylesheet' type='text/css'/>
        <link href='${projectUrl}/swaggerbase/css/reset.css' media='print' rel='stylesheet' type='text/css'/>
        <link href='${projectUrl}/swaggerbase/css/print.css' media='print' rel='stylesheet' type='text/css'/>

        <script src='${projectUrl}/swaggerbase/lib/object-assign-pollyfill.js' type='text/javascript'></script>
        <script src='${projectUrl}/swaggerbase/lib/jquery-1.8.0.min.js' type='text/javascript'></script>
        <script src='${projectUrl}/swaggerbase/lib/jquery.slideto.min.js' type='text/javascript'></script>
        <script src='${projectUrl}/swaggerbase/lib/jquery.wiggle.min.js' type='text/javascript'></script>
        <script src='${projectUrl}/swaggerbase/lib/jquery.ba-bbq.min.js' type='text/javascript'></script>
        <script src='${projectUrl}/swaggerbase/lib/handlebars-2.0.0.js' type='text/javascript'></script>
        <script src='${projectUrl}/swaggerbase/lib/lodash.min.js' type='text/javascript'></script>
        <script src='${projectUrl}/swaggerbase/lib/backbone-min.js' type='text/javascript'></script>
        <script src='${projectUrl}/swaggerbase/swagger-ui.min.js' type='text/javascript'></script>
        <script src='${projectUrl}/swaggerbase/lib/highlight.9.1.0.pack.js' type='text/javascript'></script>
        <script src='${projectUrl}/swaggerbase/lib/highlight.9.1.0.pack_extended.js' type='text/javascript'></script>
        <script src='${projectUrl}/swaggerbase/lib/jsoneditor.min.js' type='text/javascript'></script>
        <script src='${projectUrl}/swaggerbase/lib/marked.js' type='text/javascript'></script>
        <script src='${projectUrl}/swaggerbase/lib/swagger-oauth.js' type='text/javascript'></script>

        <!-- 加入下面的两个js后,访问swagger时语言会切换到中文 -->
        <script src='${projectUrl}/swaggerbase/lang/translator.js' type="text/javascript"></script>
        <script src='${projectUrl}/swaggerbase/lang/zh-cn.js' type="text/javascript"></script>
    </head>

    <body class="swagger-section">
        <div id='header'>
            <div class="swagger-ui-wrap">
                <a id="logo" href="http://swagger.io">
                    <img class="logo__img" alt="swagger" height="30" width="30" src="${projectUrl}/swaggerbase/images/logo_small.png" />
                    <span class="logo__title">swagger</span>
                </a>
                <form id='api_selector'>
                    <div class='input'>
                        <select id="select_baseUrl" name="select_baseUrl"/>
                    </div>
                    <div class='input'><input placeholder="http://example.com/api" id="input_baseUrl" name="baseUrl" type="text"/></div>
                    <div id='auth_container'></div>
                    <div class='input'><a id="explore" class="header__btn" href="#" data-sw-translate>Explore</a></div>
                </form>
            </div>
        </div>

        <div id="message-bar" class="swagger-ui-wrap" data-sw-translate>&nbsp;</div>
        <div id="swagger-ui-container" class="swagger-ui-wrap"></div>
    </body>
</html>

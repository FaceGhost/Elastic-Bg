<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../includes/meta.jsp" %>
<%@ include file="../includes/top_css_js.jsp" %>
<shiro:hasPermission name="system:log:view">
    <script src="${basepath}statics/uijs/system/system_log.js?t=<%=new Date().getTime()%>"></script>
</shiro:hasPermission>
<shiro:lacksPermission name="system:log:view">
    <%@include file="../error/no_access.jsp"%>
</shiro:lacksPermission>
<%@ include file="../includes/bottom_css_js.jsp" %>
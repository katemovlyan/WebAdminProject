<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: human
  Date: 12/1/16
  Time: 8:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello</title>

    <%@include file="/WEB-INF/jsp/common/stylesheet.jsp" %>
</head>
<body>

<div class="container">

    <header class="page-header">
        <h1>Hello ${$USERNAME}</h1>
    </header>

    <div class="row">
        <%@include file="/WEB-INF/jsp/admin/menu.jsp" %>
        <div class="col-md-9">
            <%@include file="/WEB-INF/jsp/admin/users/userProfilePartials/user_profile_partial.jsp" %>
        </div>
    </div>

    <div class="row">
        <%@include file="/WEB-INF/jsp/users.jsp" %>
    </div>

</div>
<%@include file="/WEB-INF/jsp/common/javascript.jsp" %>
</body>
</html>

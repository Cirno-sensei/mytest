<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/3/18 0018
  Time: 下午 3:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div class="box-footer">
        <div class="pull-left">
            <div class="form-group form-inline">
                总共${pageInfo.pages} 页，共${pageInfo.total} 条数据。 每页
                <select class="form-control" onchange="changePage(1,this.value)" >
                    <c:forEach var="sizeNum" begin="1" end="10" step="1">
                        <option value="${sizeNum}" ${sizeNum == pageInfo.pageSize? "selected":""}>${sizeNum}</option>
                    </c:forEach>
                </select>条
            </div>
        </div>

        <div class="box-tools pull-right">
            <ul class="pagination">
                <li><a href="javascript:changePage(1,'${pageInfo.pageSize}')" aria-label="Previous">首页</a></li>
                <li><a href="javascript:changePage('${pageInfo.prePage}','${pageInfo.pageSize}')">上一页</a></li>
                <c:forEach var="num" begin="${pageInfo.pageNum-5>0?pageInfo.pageNum-5:1 }" end="${pageInfo.pageNum+5<pageInfo.pages?pageInfo.pageNum+5:pageInfo.pages}">
                    <li><a href="javascript:changePage('${num}','${pageInfo.pageSize}')">
                        <c:choose>
                            <c:when test="${num == pageInfo.pageNum}">
                                <span style="color: red">${num}</span>
                            </c:when>
                            <c:otherwise>${num}</c:otherwise>
                        </c:choose>
                    </a>
                    </li>
                </c:forEach>
                <li><a href="javascript:changePage('${pageInfo.nextPage}','${pageInfo.pageSize}')">下一页</a></li>
                <li><a href="javascript:changePage('${pageInfo.pages}','${pageInfo.pageSize}')"  aria-label="Next">尾页</a></li>
            </ul>
        </div>
    </div>
    <script>
        function changePage(pageNum,pageSize) {
            //向表单添加隐藏域
            $("#pageNum").val(pageNum);
            $("#pageSize").val(pageSize);
            //提交页面第一个表单
            document.forms[0].submit();
        }
    </script>
</body>
</html>

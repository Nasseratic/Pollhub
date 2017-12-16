<%-- 
    Document   : report-view
    Created on : Dec 12, 2017, 12:47:10 PM
    Author     : LENOVO
--%>

<%@page import="java.util.List"%>
<%@page import="model.Report"%>
<%@include file="/header.jsp" %>
<%
    Report report = (Report)request.getAttribute("report");
%>
<section id="report" class="section">
    <div class="container">
        <h1 class="title">Section</h1>
        <h2 class="subtitle">
            <%= report.content %>
            <%= report.ischecked ? "Checked" : "Not Checked" %>
        </h2>
    </div>
</section>

<script>

//    function suspend(){
//        axios.get('Poll').then( () =>{ 
//        
//        });
//    }

</script>

<%@include file="/footer.jsp" %> 

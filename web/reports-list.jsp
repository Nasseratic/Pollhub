<%-- 
    Document   : view-reports
    Created on : Dec 12, 2017, 1:53:12 AM
    Author     : LENOVO
--%>
<%@page import="model.Report"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/header.jsp" %>


<%
    List<Report> list = (List<Report>) request.getAttribute("listOfReports");
%>

<div class="container card" id="reports-list">
    <table class="table">
        <thead>
            <tr>
                <th>Report number</th>
                <th>Report content</th>
            </tr>
        </thead>
        <tbody>
            <%
                for (Report report : list) {
                    out.print("<tr>"
                            + "<td>" + report.reportid + "</td>"
                            + "<td>" + report.content + "</td>"
                            + "<td>"
                            + "<a class='button' href='ReportController?op=selectById&reportid=" + report.reportid + "'>View report</a>"
                            + "</td>"
                            + "</tr>"
                    );
                }
            %>
        </tbody>
    </table>
</div>
<!--
<div class="container card" id="reports-list">
    <table class="table is-bordered">
        <thead>
            <tr>
                <th>Report number</th>
                <th>Report content</th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="report in reports">
                <th>{{ report.id }}</th>
                <td>{{ report.content }}</td>
            </tr>
        </tbody>
    </table>
</div>
-->
<script>
    /*
     var app = new Vue({
     el: '#reports-list',
     data: {
     reports: []
     },
     methods: {
     getAllReport: function () {
     axios.get('ReportController?op=selectAll').then(response => {
     this.reports = response.data;
     });
     }
     },
     beforeMount() {
     this.getAllReport();
     }
     });
     */
</script>

<%@include file="/footer.jsp" %> 

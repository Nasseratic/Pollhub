<%-- 
    Document   : view-my-message.jsp
    Created on : Dec 19, 2017, 9:22:30 AM
    Author     : LENOVO
--%>

<%@page import="model.Message"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/header.jsp" %>


<%
    List<Message> list = (List<Message>)request.getAttribute("listOfMessages");
%>

<div class="container card" id="reports-list">
    <table class="table">
        <thead>
            <tr>
                <th>Message content</th>
                <th>Message Number</th>
            </tr>
        </thead>
        <tbody>
            <%
                   
                for (Message message : list) {
                 String  str = "";
                if( ! message.ischecked){
                     str = " style='background: #efeeee;'";
                 }
                    out.print("<tr "+ str +" >"
                            + "<td>" + message.content + "</td>"
                            + "<td>" + message.massageid + "</td>"
                            + "<td>"
                            + "<a class='button' href='MessageController?op=update&messageid=" + message.massageid + "'>View Message</a>"
                            + "</td>"
                            + "</tr>"
                    );
                }
            %>
        </tbody>
    </table>
</div>

<script>
//    var vue = new Vue({
//        el: "#message-vue",
//        data: {
//            listOfMessages: []
//        }, created() {
//            axios.get("MessageController?op=selectByUserId").then(res => {
//                this.listOfUsers = res.data;
//            }).catch(() => {
//                console.log("Can't get the users");
//            });
//        },
//        methods: {
//
//        }
//    });
</script>

            
<%@include file="/footer.jsp" %> 
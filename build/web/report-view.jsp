<%-- 
    Document   : report-view
    Created on : Dec 12, 2017, 12:47:10 PM
    Author     : LENOVO
--%>

<%@include file="/header.jsp" %>

<div id="report" class="container">
    
</div>


<script>
    
    new Vue({
        el: '#report',
        data(){
            return {
                report : {} // TODO GET A REPORT DATA   
            };
        }
    });
    
</script>

<%@include file="/footer.jsp" %> 

<%-- 
    Document   : poll
    Created on : Dec 7, 2017, 11:53:06 AM
    Author     : LENOVO
--%>

<%@include file="/header.jsp" %>

<div id="poll" class="container">
    
    <div class="card" v-for="question of poll.questions">
        
    </div>
    
</div>

<script>
    
    new Vue({
        el: "#poll",
        data(){
            return {
                poll : JSON.parse( <%= request.getAttribute("json") %> ) // TODO POLL DATA INCLODING ALL QESTIONS 
            };
        }
    });
    
</script>

<%@include file="/footer.jsp" %> 

<!-- Get poll questions -->


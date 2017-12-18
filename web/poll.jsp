<%-- 
Document   : poll
Created on : Dec 7, 2017, 11:53:06 AM
Author     : LENOVO
--%>

<%@include file="/header.jsp" %>

<div id="poll" class="container">
<h2 class="is-size-2 has-text-weight-bold is-uppercase"> {{poll.title}} </h2>
<div class="card" style="padding: 15px; margin: 5px;" v-for="question of poll.questions">
    <h4 class="is-size-4 has-text-weight-semibold" >{{question.content}}</h4>
    <div v-if="question.type == 'text'"> 
        <input class="input"  />
    </div>
</div>

</div>

<script>

new Vue({
    el: "#poll",
    data(){
        return {
            poll :  <%= request.getAttribute("poll") %> // TODO POLL DATA INCLODING ALL QESTIONS 

//                    { title:" poll 1" , questions : [ { content : "Q1 ?" , type : 'text' } , { content : "Q2 ?" , type : 'text' } ] }
        };
    }
});

</script>

<%@include file="/footer.jsp" %> 

<!-- Get poll questions -->
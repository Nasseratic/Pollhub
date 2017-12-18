<%-- 
    Document   : user-profile
    Created on : Dec 7, 2017, 12:23:16 PM
    Author     : LENOVO
--%>

<%@include file="/header.jsp" %>


<div class="container">
    <h3 class="is-size-3">
        POLLS 
    </h3>
    <br/>
    <div class="container">
        <div id="myPolls" class="columns is-multiline">
            <div class="column is-4" v-for="poll of polls" >
                <div class="card">
                    <div class="card-header">
                        <span class="card-header-title">{{poll.title}}</span>
                    </div>
                    <div class="card-footer">
                        <div class="card-footer-item"> <a class="button is-info is-outlined" :href="'PollController?op=getPollWithEverything&pollid=' + poll.pollid" > OPEN </a> </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>

    new Vue({
        el: "#myPolls",
        data() {
            return {
                polls: <%= request.getAttribute("polls") %>  // #TODO HERE WE GET THE ARRAY OF THE POLLS OF THE USER
            };
        }
    });

</script>

<%@include file="/footer.jsp" %> 

<!-- user data , edit user data ( if admin , check if there is new reports )  -->

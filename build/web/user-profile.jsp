<%-- 
    Document   : user-profile
    Created on : Dec 7, 2017, 12:23:16 PM
    Author     : LENOVO
--%>

<%@include file="/header.jsp" %>


<div class="container">
    <h3 class="is-size-3">
        MY POLLS 
    </h3>
    <br/>
    <div class="container">
        <div id="mypolls" class="columns">
            <div class="card column" v-for="poll of polls" >
                <div class="card-content">
                    HEY
                </div>
            </div>
        </div>
    </div>
</div>
<script>

    new Vue({
        el: "#mypolls",
        data() {
            return {
                polls: [ 1 , 2 , 3 ]
            };
        }
    });

</script>

<%@include file="/footer.jsp" %> 

<!-- user data , edit user data ( if admin , check if there is new reports )  -->

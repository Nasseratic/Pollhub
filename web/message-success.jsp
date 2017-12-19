<%-- 
    Document   : message-success
    Created on : Dec 18, 2017, 10:06:40 AM
    Author     : Abdullah
--%>

<%@include file="/header.jsp" %>

<section class="section">
    <div class="container">
        <div id="message-success">
            <div class="card-content">
                <article class="message is-success is">
                    <div class="message-header">
                        <p>Success</p>
                        <!--<button class="delete" aria-label="delete" v-on:click="close"></button>-->
                    </div>
                    <div class="message-body">
                        Your message was sent successfully.
                    </div>
                </article>
            </div>
        </div>
    </div>
</section>
<script>
    /*
     new Vue({
     el: '#message-success',
     methods: {
     close: function () {
     alert('Hello World' + '!');
     
     }
     }
     });
     */
</script>
<%@include file="/footer.jsp" %>
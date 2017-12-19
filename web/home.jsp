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
            <div class="column is-4" v-for="(poll,i) of polls" v-if="(!poll.aissuspended && !poll.uissuspended) || <%= session.getAttribute("session_IsAdmin") %>" >
                <div class="card">
                    <div class="card-header">
                        <span class="card-header-title">{{poll.title}}</span>
                    </div>
                    <div class="card-footer">
                        <div class="card-footer-item"> <a class="button is-info is-outlined" :href="'PollController?op=getPollWithEverything&pollid=' + poll.pollid" :disabled="poll.close" > OPEN </a> </div>
                        
                        <div v-if="<%= session.getAttribute("session_IsAdmin") %>" class="card-footer-item"> <button class="button is-warning" @click="close( poll.pollid , i )" > {{poll.close ? 'Open' : 'Close'}} </button> </div>
                        <div v-if="<%= session.getAttribute("session_IsAdmin") %>" class="card-footer-item"> <button class="button is-danger" @click="suspend( poll.pollid , i )"> {{ (poll.uissuspended || poll.aissuspended)  ? 'Unsuspend' : 'Suspend'}} </button> </div>

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
        }, methods:{
            suspend(pollId , i){
                axios.get( 'PollController?op=' +((this.polls[i].uissuspended || this.polls[i].aissuspended) ? 'unsuspend' : 'suspend' )+ '&pollId='+pollId ).then( res =>{
                    this.polls[i].aissuspended = !this.polls[i].aissuspended;
                });
            },close(pollId , i){
               
                axios.get( 'PollController?op=' + (this.polls[i].close ? 'open' : 'close' ) + '&pollId='+pollId ).then( res =>{
                    this.polls[i].close = !this.polls[i].close;
                });
            }
        }
    });

</script>

<%@include file="/footer.jsp" %> 

<!-- user data , edit user data ( if admin , check if there is new reports )  -->

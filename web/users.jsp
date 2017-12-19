<%-- 
    Document   : users
    Created on : Dec 17, 2017, 9:31:39 PM
    Author     : LENOVO
--%>

<%@include file="/header.jsp" %>


<div class="container" id="users">
    <br/>
    <h3 class="is-size-3 has-text-weight-bold">
        Users 
    </h3>
    <br/>
    <div class="container">
        <div id="checkboxes" class="card-content">
            <table class="table is-bordered" style="width: 100%">
                <tr v-for="(user , i ) in listOfUsers">
                    <td><label class="checkbox">{{ user.username }}</label></td>
                    <td> <button class="button is-warning" @click="admin(i)"> {{ !user.isAdmin ? 'Make Admin' : 'Remove from admin' }} </button> </td>
                    <td> <button class="button is-danger" @click="suspend(i)"> {{ !user.isSuspended ? 'Suspend' : 'Unsuspend' }} </button> </td>
                    <td> <input class="input is-inline" type="password" v-model="user['password']" /> <button class="button is-info is-inline" @click="reset(i)"> Reset </button> </td>

                </tr>
            </table>
        </div>
        <br/>
        <br/>
        <article v-if="log !== '' " class="message is-info">
            <div class="message-header">
                <p> LOG </p>
                <button class="delete" aria-label="delete"></button>
            </div>
            <div class="message-body">
                <strong> {{ log }} </strong>
            </div>
        </article>
        <br/>
        <br/>
    </div>
</div>
<script>

    new Vue({
        el: "#users",
        data() {
            return {
                listOfUsers: [],
                log :''
            };
        }, created() {
            axios.get("User?op=selectAll").then(res => {
                this.listOfUsers = res.data;
            }).catch(() => {
                console.log("Can't get the users");
            });
        }, methods: {
            admin(i) {
                axios.get('UpdateUser?op=admin&username='+this.listOfUsers[i].username+ '&' + 'isAdmin=' + (this.listOfUsers[i].isAdmin ? false : true)   ).then(res => {
                    this.listOfUsers[i].isAdmin = !this.listOfUsers[i].isAdmin;
                    this.log = res.data;
                });
            },suspend(i) {
                axios.get('UpdateUser?op=suspended&username='+this.listOfUsers[i].username+ '&' + 'isSuspended=' + (this.listOfUsers[i].isSuspended ? false : true)   ).then(res => {
                    this.listOfUsers[i].isSuspended = !this.listOfUsers[i].isSuspended;
                    this.log = res.data;
                });
            },reset(i) {
                axios.get('UpdateUser?op=reset_password&username='+this.listOfUsers[i].username+ '&' + 'password=' + this.listOfUsers[i].password ).then(res => {
                    this.log = res.data;
                });
            }
        }
    });

</script>

<%@include file="/footer.jsp" %> 

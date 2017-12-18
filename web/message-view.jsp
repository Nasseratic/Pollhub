<%-- 
    Document   : message-view
    Created on : Dec 17, 2017, 12:08:30 PM
    Author     : Abdullah
--%>
<%@include file="/header.jsp" %>
<div class="card"  style="padding: 50px;" id="message-vue">
    <form class="container" action="MessageController?op=add" method="POST">
        <div class="field">
            <label class="label">Message</label>
            <div class="control">
                <textarea name="content" class="textarea" placeholder="Your Message"></textarea>
            </div>
        </div>
        <div class="select is-multiple" multiple="multiple">
            <div id="checkboxes" class="card-content">
                <table class="table is-bordered">
                    <td><label class="checkbox">All Users</label></td>
                    <td><input type="radio" name="spesific" value="allUsers" checked/></td>
                    <tr v-for="user in listOfUsers">
                        <td><label class="checkbox">{{ user.username }}</label></td>
                        <td><input type="radio" name="spesific" v-bind:value="user.userId"/></td>
                    </tr>
                </table>
            </div>
        </div>
        <div class="field is-grouped">
            <div class="control">
                <button class="button is-link">Submit</button>
            </div>
            <div class="control">
                <button class="button is-text">Cancel</button>
            </div>
        </div>
    </form>
</div>

<script>
    var vue = new Vue({
        el: "#message-vue",
        data: {
            listOfUsers: []
        }, created() {
            axios.get("/IA-Test/UserController?op=selectAll").then(res => {
                this.listOfUsers = res.data;
            }).catch(() => {
                console.log("Can't get the users");
            });
        },
        methods: {

        }
    });
</script>

<%@include file="/footer.jsp" %>

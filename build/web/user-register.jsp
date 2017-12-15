<%-- 
    Document   : user-register
    Created on : Dec 7, 2017, 12:22:49 PM
    Author     : LENOVO
--%>

<%@include file="/header.jsp" %>
<br/>
<br/>
<div class="card"  style="padding: 50px;">
    <h2 class="is-size-3 has-text-centered"> USER SIGN UP </h2>
    <br/>
    <form class="container" action="UserCrud?op=signup" method="POST" style="max-width: 350px;">
        <div class="field">
            <div class="control">
                <input class="input" v-model="username" @keyup="checkUserExists()" required name="username" type="text" placeholder="User Name">
            </div>
        </div>
        <div class="field">
            <div class="control">
                <input class="input" name="email" type="text" placeholder="Email" required>
            </div>
        </div>
        <div class="field">
            <div class="control">
                <input class="input" name="password" type="password" placeholder="Password" required>
            </div>
        </div>

        <button class="button is-primary is-medium btn-text is-centered" type="submit">SIGN UP</button>
    </form>
</div>
<script>

    new Vue({
        el: "form",
        data(){
            return {username: "", isValid: true, loginError: false};
        },
        methods: {
            checkUserExists() {
                console.log(this.username);
                axios.get('UserCrud?op=unique&username=' + this.username).then(res => {
                    this.isValid = res;
                    console.log(this.username);
                });
            }
        }
    });

</script>

<%@include file="/footer.jsp" %> 




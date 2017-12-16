<%-- 
    Document   : login
    Created on : Dec 12, 2017, 1:00:53 AM
    Author     : LENOVO
--%>

<%@include file="/header.jsp" %>
<br/>
<br/>
<div class="card"  style="padding: 50px;">
    <h2 class="is-size-2 has-text-centered"> USER LOGIN </h2>
    <br/>
<form class="container" action="UserController?op=login" method="POST" style="max-width: 350px;">
          <div class="field">
            <div class="control">
              <input class="input" name="username" type="text" placeholder="User Name">
            </div>
          </div>
          <div class="field">
            <div class="control">
              <input class="input" name="password" type="password" placeholder="Password">
            </div>
          </div>
    
          <button class="button is-primary is-medium btn-text is-centered" type="submit">LOGIN</button>
</form>
</div>
<script>
    
//    new Vue({
//        
//    });
//    
</script>

<%@include file="/footer.jsp" %> 

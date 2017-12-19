<%-- 
Document   : header
Created on : Dec 7, 2017, 11:54:09 AM
Author     : LENOVO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="has-navbar-fixed-top">
    <head>
        <title>PollHub</title>
        <link rel="stylesheet" href="assets/css/bulma.css" />        
        <link rel="stylesheet" href="assets/css/ionicons.min.css" />

        <script src="assets/vue.js"></script>
        <script src="assets/axios.min.js"></script>
    </head>
    <body>

        <header>
            <nav class="navbar is-fixed-top card" style="opacity: 0;" v-bind:style="{ opacity: 1 }">
                <div class="navbar-brand">
                    <a class="navbar-item" href="index.jsp">
                        <img src="assets/logo.png" style="min-width: 100px; min-height: 100%" >
                    </a>
                    <div class="navbar-burger burger" data-target="navbarExampleTransparentExample">
                        <span></span>
                        <span></span>
                        <span></span>
                    </div>
                </div>

                <div id="navbarExampleTransparentExample" class="navbar-menu">
                    <div class="navbar-start">
                        <a v-if="isAuth" class="navbar-item" href="PollController?op=getAllForSystem">
                            All Polls
                        </a>
                        <a v-if="isAdmin" class="navbar-item" href="users.jsp">
                            Users
                        </a>
                        <a v-if="isAdmin" class="navbar-item" href="ReportController?op=selectAll">
                            <span style="margin-right: 5px;">Reports</span>  <span class="tag is-danger"> 0 </span>
                        </a>
                    </div>


                    <div class="navbar-end">
                        <div class="navbar-item">
                            <div v-if="!isAuth"  class="field is-grouped">
                                <p class="control">
                                    <a class="button" href="user-login.jsp">
                                        <span class="icon">
                                            <i class="ion-log-in"></i>
                                        </span>
                                        <span>
                                            Login
                                        </span>
                                    </a>
                                </p>
                                <p class="control">
                                    <a class="button is-primary" href="user-register.jsp">
                                        <span class="icon">
                                            <i class="ion-android-favorite-outline"></i>
                                        </span>
                                        <span>Sign Up</span>
                                    </a>
                                </p>
                            </div>
                            <div v-if="isAuth"  class="field is-grouped">
                                <p class="control">
                                    <a class="button is-primary" href="poll-add.jsp">
                                        <span class="icon">
                                            <i class="ion-ios-plus"></i>
                                        </span>
                                        <span>New Poll</span>
                                    </a>
                                </p>
                                <p class="control">
                                    <a class="button" href="PollController?op=getAllForProfile">
                                        <span class="icon">
                                            <i class="ion-person"></i>
                                        </span>
                                        <span>
                                            Welcome, {{username}}
                                        </span>
                                    </a>
                                    <a class="button is-info" href="MessageController?op=selectByUserId">
                                        <span> {{numberOfMessages}} </span>
                                        <span class="icon">
                                            <i class="ion-email"></i>
                                        </span>
                                    </a>
                                    <a v-if="isAdmin" class="button is-info" href="message-view.jsp">
                                        <span> Send Message </span>
                                        <span class="icon">
                                            <i class="ion-plus-circled"></i>
                                        </span>
                                    </a>
                                </p>
                                <p class="control">
                                    <a class="button" href="User?op=logout">
                                        <span class="icon">
                                            <i class="ion-log-out"></i>
                                        </span>
                                        <span>
                                            Logout
                                        </span>
                                    </a>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
            </nav>
        </header>


        <script>
    new Vue({
        el: "nav",
        created() {
            axios.get("MessageController?op=selectNumberOfMessages").then(res => {
                this.numberOfMessages = res.data;
            }).catch(() => {
                console.log("Can't get the users");
            });
        },
        data() {
            return {
                numberOfMessages: 0,
                isAuth: <%= session.getAttribute("session_valid")%>,
                username: <%= "\'" + session.getAttribute("session_username") + "\'"%>,
                isAdmin: <%= session.getAttribute("session_IsAdmin")%>
            };
        }
    });
        </script>

        <main style="min-height: 75vh">
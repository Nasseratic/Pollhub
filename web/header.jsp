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
            <nav class="navbar is-fixed-top" style="opacity: 0;" v-bind:style="{ opacity: 1 }">
                <div class="navbar-brand">
                    <a class="navbar-item" href="https://bulma.io">
                        <h3> LOGO </h3>
                    </a>
                    <div class="navbar-burger burger" data-target="navbarExampleTransparentExample">
                        <span></span>
                        <span></span>
                        <span></span>
                    </div>
                </div>

                <div id="navbarExampleTransparentExample" class="navbar-menu">
                    <div class="navbar-start">
                        <a class="navbar-item" href="https://bulma.io/">
                            Home
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
                                    <a class="button" href="user-login.jsp">
                                        <span class="icon">
                                            <i class="ion-person"></i>
                                        </span>
                                        <span>
                                            Welcome, {{username}}
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
                el:"nav",
                data(){
                    return { isAuth: <%= "true" %> , username: <%= "\'Mohamed\'" %> };
                }
            });
        </script>
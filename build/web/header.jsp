<%-- 
    Document   : header
    Created on : Dec 7, 2017, 11:54:09 AM
    Author     : LENOVO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="has-navbar-fixed-top">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PollHub</title>
        <link rel="stylesheet" href="assets/css/bulma.css" />        
        <link rel="stylesheet" href="assets/css/ionicons.min.css" />

        <script src="assets/vue.js"></script>
        <script src="assets/axios.min.js"></script>
    </head>
    <body>

        <header>
            <nav class="navbar is-fixed-top">
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
                        <div class="navbar-item has-dropdown is-hoverable">
                            <a class="navbar-link" href="/documentation/overview/start/">
                                Docs
                            </a>
                            <div class="navbar-dropdown is-boxed">
                                <a class="navbar-item" href="/documentation/overview/start/">
                                    Overview
                                </a>
                                <a class="navbar-item" href="https://bulma.io/documentation/modifiers/syntax/">
                                    Modifiers
                                </a>
                                <a class="navbar-item" href="https://bulma.io/documentation/columns/basics/">
                                    Columns
                                </a>
                                <a class="navbar-item" href="https://bulma.io/documentation/layout/container/">
                                    Layout
                                </a>
                                <a class="navbar-item" href="https://bulma.io/documentation/form/general/">
                                    Form
                                </a>
                                <hr class="navbar-divider">
                                <a class="navbar-item" href="https://bulma.io/documentation/elements/box/">
                                    Elements
                                </a>
                                <a class="navbar-item is-active" href="https://bulma.io/documentation/components/breadcrumb/">
                                    Components
                                </a>
                            </div>
                        </div>
                    </div>

                    <div class="navbar-end">
                        <div class="navbar-item">
                            <div class="field is-grouped">
                                <p class="control">
                                    <a class="button" href="login.jsp">
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
                        </div>
                    </div>
                </div>
            </nav>
        </header>
        
        
        
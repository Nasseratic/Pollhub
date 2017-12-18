<%-- 
Document   : poll
Created on : Dec 7, 2017, 11:53:06 AM
Author     : LENOVO
--%>

<%@include file="/header.jsp" %>

<div id="poll" class="container">
    <h2 class="is-size-2 has-text-weight-bold is-uppercase"> {{poll.title}} </h2>
    <div class="card" style="padding: 15px; margin: 5px;" v-for="(question,i) of poll.questions">
        <h4 class="is-size-4 has-text-weight-semibold" >{{question.content}}</h4>
        <div v-if="question.type == 'text'"> 
            <input class="input" @keyup="addAnswer( question.questionid , answer)" />
        </div>
        <div v-if="question.type == 'checkbox'"> 
            <label style="display: block" v-for="answer of question.answer">
                <input type="checkbox" @click="addAnswer( question.questionid , answer)">
                {{answer}}
            </label>
        </div>
        <div v-if="question.type == 'mcq'"> 
            <label style="display: block" v-for="answer of question.answer">
                <input type="radio" :name="'answer'+i" @click="addAnswer( question.questionid , answer)" >
                {{answer}}
            </label>
        </div>
    </div>
    <a class="button is-primary" @mouseover="toJson()" :href="'PollController?op=answerPoll&json=' + json" > submit </a>

</div>

<script>

    new Vue({
        el: "#poll",
        data() {
            return {
                poll: <%= request.getAttribute("poll")%>, // TODO POLL DATA INCLODING ALL QESTIONS 

//                    { title:" poll 1" , questions : [ { content : "Q1 ?" , type : 'text' } , { content : "Q2 ?" , type : 'text' } ] }

                answers: {
                    questionid: null,
                    content: null,
                    type: null,
                    poll: null,
                    answer: null,
                    answers: []
                },
                isrevailed : true,
                json : ''
            };
        }, created() {
            this.poll.questions.forEach( (e) => {
                e.answer = e.answer.split('/').filter( e => { return e !== '' } );
            });
        },methods:{
            addAnswer(qId , content){
                this.answers.answers.push( { 
                    question: qId ,
                    user: <%= session.getAttribute("session_userid") %> ,
                    content: content ,
                    isrevailed : this.isrevailed } )
            },toJson(){
                this.json = JSON.stringify( this.answers );
            }
        }
    });

</script>

<%@include file="/footer.jsp" %> 

<!-- Get poll questions -->
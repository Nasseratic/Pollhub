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
            <input class="input" v-model="question[i]" @keyup="addAnswer( question.type,question.questionid , question[i] )" />
        </div>
        <div v-if="question.type == 'checkbox'"> 
            <label style="display: block" v-for="(answer,j) of question.answer">
                <input type="checkbox" @click="addAnswer( question.type , question.questionid , answer , j )">
                {{answer}}
            </label>
        </div>
        <div v-if="question.type == 'mcq'"> 
            <label style="display: block" v-for="answer of question.answer">
                <input type="radio" :name="'answer'+i" @click="addAnswer( question.type , question.questionid , answer)" >
                {{answer}}
            </label>
        </div>
    </div>
    <br/>
    <label style="display: block">
        <input type="checkbox" :checked="isrevailed" @click="revile()" >
        Those answers will be reviled 
    </label>
    <br/>
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
                    answers: {}
                },
                isrevailed: true,
                json: ''
            };
        }, created() {
            this.poll.questions.forEach((e) => {
                e.answer = e.answer.split('/').filter(e => {
                    return e !== ''
                });
            });
        }, methods: {
            addAnswer(type, qId, content, index = 0) {
                if (type === 'checkbox') {
                    if (!this.answers.answers[qId]) {
                        this.answers.answers[qId] = [];
                    }
                    this.answers.answers[qId][index] = ({
                        question: qId,
                        user: <%= session.getAttribute("session_userid")%>,
                        content: content,
                        isrevailed: this.isrevailed})
                } else {
                    this.answers.answers[qId] = {
                        question: qId,
                        user: <%= session.getAttribute("session_userid")%>,
                        content: content,
                        isrevailed: this.isrevailed};
            }
            }, toJson() {
                let arr = [];
                for (var key in this.answers.answers) {
                    if (this.answers.answers[key] instanceof Array) {
                        this.answers.answers[key].forEach(e2 => {
                            if (e2 !== undefined) {
                                arr.push(e2);
                            }
                        });
                    } else {
                        arr.push(this.answers.answers[key]);
                    }
                }
                this.answers.answers = arr;
                this.json = JSON.stringify(this.answers);
            }, revile() {
                this.isrevailed = !this.isrevailed;
                for (var key in this.answers.answers) {
                    if (this.answers.answers[key] instanceof Array) {
                        this.answers.answers[key].forEach(e2 => {
                            e2.isrevailed = this.isrevailed;
                        });
                    } else {
                        this.answers.answers[key].isrevailed = this.isrevailed
                    }
                }
            }
        }
    });

</script>

<%@include file="/footer.jsp" %> 

<!-- Get poll questions -->
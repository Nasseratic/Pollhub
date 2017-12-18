<%-- 
    Document   : add-poll
    Created on : Dec 7, 2017, 12:22:26 PM
    Author     : LENOVO
--%>

<%@include file="/header.jsp" %>

<div id="newPoll"  class="container">
    <br/>
    <h2 class="is-size-3"> New Poll </h2>
    <div class="card">
        <div class="card-header" style="padding: 10px;">
            <div class="control">
                <input class="input" v-model="title"  name={{}} type="text" placeholder="Poll title">
            </div>
            <div class="field is-grouped navbar-end">
                <p class="control">
                    <a class="button" @click="addQuestion('mcq')">
                        <span class="icon"> <i class="ion-checkmark-circled"></i> </span> 
                        <span> Add MCQ </span>
                    </a>
                </p>
                <p class="control">
                    <a class="button" @click="addQuestion('checkbox')">
                        <span class="icon">
                            <i class="ion-android-checkbox-outline"></i> 
                        </span> 
                        <span> Add Checkbox </span>
                    </a>
                </p>
                <p class="control">
                    <a class="button" @click="addQuestion('text')">
                        <span class="icon"> <i class="ion-document-text"></i> </span>
                        <span> Add Text </span> 
                    </a>
                </p>
            </div>   
        </div>

        <div class="card-content">
            <div class="card" style="padding: 10px" v-for="( q , index) of questions" v-bind:key="q.key">
                <div class="field" >
                    <div class="control">
                        <input class="input" v-model="q.content"  @change="toJson()" type="text" placeholder="Question">
                    </div>
                    <div style="padding: 10px" v-if="q.type !== 'text'"> 
                        <h4 class=" columns is-size-5">Answers: </h4>
                        <div class="control columns">
                            <input class="input column is-6 is-small" v-model="q.answerToAdd"  name={{}} type="text" placeholder="Add Answer">
                            <a  @click="addAnswer( index , q.answerToAdd ); toJson()"> <span class="icon is-medium" style="margin-top: -3px; padding: 2px;"> <i class="ion-plus-circled is-size-4"></i> </span> </a>
                        </div>
                        <div class="columns">

                            <span style="margin: 2px;" v-for="( answer , i ) of q.answer" class="tag is-medium">
                                <span style="margin: 5px;"> {{answer}} </span>   
                                <button class="delete is-small"></button> 
                            </span>

                        </div>
                    </div>
                </div>
                <br/>
            </div>
            <form v-bind:action=" 'PollController?op=add&json=' + json" method="POST">
                <button @mouseover="toJson()" class="button is-primary" type="submit"> Submit </button>
            </form>
        </div>

    </div>

    
</div>


<script>


    new Vue({
        el: "#newPoll",
        data() {
            return {
                json:"",
                title: "",
                questions: []
            };
        },
        methods: {
            addQuestion(type) {
                questionObj = {type: type};

                if (type === 'text') {
                    questionObj['answer'] = "";
                } else {
                    questionObj['answer'] = [];
                }
                this.questions.push(questionObj);
            },
            removeQuestion($index) {
                this.questions.remove($index);
            },
            addAnswer($index, $answer) {
                if ($answer !== '' || $answer !== undefined) {
                    this.questions[$index].answer.push($answer);
                    this.questions[$index].answerToAdd = "";
                }

            },
            removeAnswer($index) {
                this.questions[$index].remove($index);
            }, toJson() {
                console.log('toZeft');
                let questions= JSON.parse(JSON.stringify(this.questions));
                this.json = JSON.stringify({    "pollid": null, "user": null, 
                            "uissuspended": null,
                            "aissuspended": null,
                            "close": false, 
                            "title": this.title, 
                            "questions": questions.map(e => {
                                e['answers'] = null;
                                e['poll'] = null;
                                e['questionid'] = null;
                                let v = "";
                                e['answer'].forEach( e => {
                                    v= v+'/'+e;
                                });
                                e['answer'] = v;
                                return e;
                            })
                });
            }
        }
    });

</script>

<%@include file="/footer.jsp" %> 

<%--
class Question{
        type: 'text',
        answers:[]
    }

new Vue({
        el: "#newPoll",
        data() {
            return {
                questions: []
            };
        },
        methods:{
            addQuestion(){
                this.questions.push( new Question() )
            },
            removeQuestion($index){
                this.questions.remove($index);
            },
            addAnswer($index){
                this.questions[$index].push(ture)
            },
            removeAnswer($index,$aIndex){
                
            }
            
        }
    });
--%>
<%-- 
    Document   : add-poll
    Created on : Dec 7, 2017, 12:22:26 PM
    Author     : LENOVO
--%>

<%@include file="/header.jsp" %>

<div id="newPoll"  class="container">

    <form>
        <div class="card" v-for="q of qs" v-bind:key="q.key">
            <div class="field" >
                <div class="control">
                    <input class="input" v-model="q.name"  name={{}} type="text" placeholder="text">
                </div>
            </div>
        </div>
    </form>

</div>


<script>
    
        
    new Vue({
        el: "#newPoll",
        data() {
            return {
                questions: []
            };
        },
        methods:{
            addQuestion(){
                this.questions.push(0); 
            },
            removeQuestion($index){
                this.questions.remove($index);
            },
            addAnswer($index){
                this.questions[$index]++;
            },
            removeAnswer($index){
                this.questions[$index]--;
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

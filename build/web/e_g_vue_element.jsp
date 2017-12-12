<div id="NavBar">
    <div v-for="user in users" >
        {{ user.name}}
        {{ user.id }}
        {{ user.isAdmin }}
    </div>
    <button @click="checkIfUserExist()"> what ever </button>
</div>

<script>
    new Vue({
        el: '#NavBar',
        data() {
            return {
                userExist: false,
                users: [{
                        name: "Mohamed",
                        id: "1",
                        isAdmin: "0"
                    },
                    {
                        name: "Dawood",
                        id: "2",
                        isAdmin: "1"
                    },
                    {
                        name: "Mohamed",
                        id: "1",
                        isAdmin: "0"
                    }
                ]
            }
        },
        methods: {
            checkIfUserExist() {
                axios.get("userNameCheck/" + this.username).then((res) => {
                    this.userExist = res;
                }).catch((err) => {
                    alert(err)
                });
            }
        }
    });

</script>
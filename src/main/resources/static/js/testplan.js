function getIndex(list,id){
    for (var i=0; i<list.length; i++){
        if (list[i].id === id){
            return i;
        }
    }
    return -1;
}

var testplanApi = Vue.resource('/testplan{/id}')


Vue.component('testplan-form',{
    props:['testplans','testplanAttr'],
    data:function() {
        return {
            id:'',
            author: '',
            update_date: "2021-01-01",
            creation_date: "2021-01-01",
            release_id: 0
        }
    },
    watch:{
        testplanAttr: function(newVal,oldVal){
            this.id=newVal.id,
                this.author=newVal.author,
                this.update_date = newVal.update_date,
                this.creation_date = newVal.creation_date,
                this.release_id = newVal.release_id
        }
    },
    template:
        '<div>'+
        '<input type="text" placeholder="Автор" v-model="author"/>'+
        '<input type="datetime-local" placeholder="Дата изменения" v-model="update_date"/>'+
        '<input type="datetime-local" placeholder="Дата создания" v-model="creation_date"/>'+
        '<input type="text" placeholder="release_id" v-model="release_id"/>'+
        '<input type="button" value="Save" @click="save"/>'+
        '</div>',
    methods:{
        save:function () {
            var testplan = {
                author: this.author,
                update_date: this.update_date,
                creation_date: this.creation_date,
                release_id: this.release_id
            };

            if (this.id) {
                testplanApi.update({id: this.id}, testplan).then(result =>
                    result.json().then(data => {
                        var index = getIndex(this.testplans, data.id);
                        this.testplans.splice(index, 1, data);
                        this.author = '';
                        this.update_date = "2021-01-01";
                        this.creation_date = "2021-01-01";
                        this.release_id = 0;
                        this.id = 0;
                    })
                )
            } else {
                testplanApi.save({}, testplan).then(result =>
                    result.json().then(data => {
                        this.author = '';
                        // if (this.author ==='') alert ("Введите имя автора");
                        this.update_date = "2021-01-01";
                        this.creation_date = "2021-01-01";
                        this.release_id = 0
                    })
                )
            }
        }
    }
})

Vue.component('testplan-row',{
    props: ['testplan','editTestPlan','testplans'],
    template:'<div><i>({{testplan.id}})</i>{{testplan.author}} {{testplan.release_id}}' +
        '<span style="position: absolute; right: 0">' +
        '<input type="button" value="Edit" @click="edit" />' +
        '<input type="button" value="X" @click="del" />' +
        '</span>' +
        '</div>',
    methods :{
        edit: function (){
            this.editTestPlan(this.testplan)
        },
        del: function (){
            testplanApi.remove({id:this.testplan.id}).then(result => {
                if (result.ok){
                    this.testplans.splice(this.testplans.indexOf(this.testplan), 1)
                }
            })
        }
    }
})

Vue.component('testplans-list', {
    props:['testplans'],
    data: function (){
        return {
            testplan: null
        }
    },
    template:'<div style="position: relative; width: 300px">' +
        '<testplan-form :testplans="testplans" :testplanAttr="testplan"/>'+
        '<testplan-row v-for="testplan in testplans" :key="testplan.id" :testplan="testplan" ' +
        ':editTestPlan="editTestPlan" :testplans="testplans" /></div>',

    created: function (){
        testplanApi.get().then(result =>
            result.json().then(data=>
                data.forEach(testplan => this.testplans.push(testplan))
            )
        )
    },
    methods: {
        editTestPlan: function (testplan) {
            this.testplan = testplan;
        }
    }
});

var plan = new Vue({
    el: "#plan",
    template: '<testplans-list :testplans="testplans"/>',
    data: {
        testplans: []
    }
});
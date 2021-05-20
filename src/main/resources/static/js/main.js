function getIndex(list,id){
    for (var i=0; i<list.length; i++){
        if (list[i].id === id){
            return i;
        }
    }
    return -1;
}

var testcaseApi = Vue.resource('/testcase{/id}')

Vue.component('testcase-form',{
    props:['testcases','testcaseAttr'],
    data:function() {
        return {
            id:'',
            author: '',
            update_date: "2021-01-01",
            creation_date: "2021-01-01",
            case_name: '',
            step: '',
            input_data: '',
            result: '',
            chain_id: 0,
            testplan_id: 0,
            element_id: 0
        }
        },
    watch:{
        testcaseAttr: function(newVal,oldVal){
                this.id=newVal.id,
                this.author=newVal.author,
                this.update_date = newVal.update_date,
                this.creation_date = newVal.creation_date,
                this.case_name = newVal.case_name,
                this.step = newVal.step,
                this.input_data = newVal.input_data,
                this.result = newVal.result,
                this.chain_id = newVal.chain_id,
                this.testplan_id = newVal.testplan_id,
                this.element_id = newVal.element_id
        }
    },
    template:
    '<div>'+
        '<input type="text" placeholder="Автор" v-model="author"/>'+
        '<input type="datetime-local" placeholder="Дата изменения" v-model="update_date"/>'+
        '<input type="datetime-local" placeholder="Дата создания" v-model="creation_date"/>'+
        '<input type="text" placeholder="Название" v-model="case_name"/>'+
        '<input type="text" placeholder="Шаг тестирования" v-model="step"/>'+
        '<input type="text" placeholder="Входные данные" v-model="input_data"/>'+
        '<input type="text" placeholder="Ожидаемый результат" v-model="result"/>'+
        '<input type="text" placeholder="chain_id" v-model="chain_id"/>'+
        '<input type="text" placeholder="testplan_id" v-model="testplan_id"/>'+
        '<input type="text" placeholder="element_id" v-model="element_id"/>'+
        '<input type="button" value="Save" @click="save"/>'+
        '</div>',
    methods:{
        save:function (){
            var testcase = {
                author:this.author,
                update_date: this.update_date,
                creation_date: this.creation_date,
                case_name: this.case_name,
                step: this.step,
                input_data: this.input_data,
                result:this.result,
                chain_id: this.chain_id,
                testplan_id: this.testplan_id,
                element_id: this.element_id
            };

            if (this.id){
                testcaseApi.update({id:this.id}, testcase).then(result =>
                    result.json().then(data =>{
                        var index = getIndex(this.testcases,data.id);
                        this.testcases.splice(index, 1, data);
                        this.author = '';
                        this.update_date = "2021-01-01";
                        this.creation_date= "2021-01-01";
                        this.case_name = '';
                        this.step = '';
                        this.input_data = '';
                        this.result = '';
                        this.chain_id = 0;
                        this.testplan_id = 0;
                        this.element_id = 0;
                        this.id='';
                    })
                )
            }
            testcaseApi.save({},testcase).then(result =>
            result.json().then(data=>{
                this.author = '';
                this.update_date = "2021-01-01";
                this.creation_date= "2021-01-01";
                this.case_name = '';
                this.step = '';
                this.input_data = '';
                this.result = '';
                this.chain_id = 0;
                this.testplan_id = 0;
                this.element_id = 0
                })
            )
        }
    }
})

Vue.component('testcase-row',{
    props: ['testcase','editTestCase','testcases'],
    template:'<div><i>({{testcase.id}})</i>{{testcase.author}} {{testcase.case_name}}' +
        '<span style="position: absolute; right: 0">' +
        '<input type="button" value="Edit" @click="edit" />' +
        '<input type="button" value="X" @click="del" />' +
        '</span>' +
        '</div>',
    methods :{
        edit: function (){
            this.editTestCase(this.testcase)
        },
        del: function (){
            testcaseApi.remove({id:this.testcase.id}).then(result => {
                if (result.ok){
                    this.testcases.splice(this.testcases.indexOf(this.testcase), 1)
                }
            })
        }
    }
})

Vue.component('testcases-list', {
    props:['testcases'],
    data: function (){
        return {
            testcase: null
        }
    },
    template:'<div style="position: relative; width: 300px">' +
        '<testcase-form :testcases="testcases" :testcaseAttr="testcase"/>'+
        '<testcase-row v-for="testcase in testcases" :key="testcase.id" :testcase="testcase" ' +
        ':editTestCase="editTestCase" :testcases="testcases" /></div>',

    created: function (){
        testcaseApi.get().then(result =>
        result.json().then(data=>
            data.forEach(testcase => this.testcases.push(testcase))
            )
        )
    },
    methods: {
        editTestCase: function (testcase) {
            this.testcase = testcase;
        }
    }
});


var app = new Vue({
    el: "#app",
    template: '<testcases-list :testcases="testcases"/>',
    data: {
        testcases: []
    }
});
var testcaseApi = Vue.resource('/testcase{/id}')

Vue.component('testcase-form',{
    props:['testcases'],
    data:function() {
        return {
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

            testcaseApi.save({},testcase).then(result =>
            result.json().then(data=>{
                this.testcases.push(data);
                })
            )
        }
    }
})

Vue.component('testcase-row',{
    props: ['testcase'],
    template:'<div><i>({{testcase.id}})</i>{{testcase.author}}{{testcase.case_name}}</div>'
})

Vue.component('testcases-list', {
    props:['testcases'],
    template:'<div>' +
        '<testcase-form testcases="testcases"/>'+
        '<testcase-row v-for="testcase in testcases" :key="testcase.id" :testcase="testcase" /></div>',
    created: function (){
        testcaseApi.get().then(result =>
        result.json().then(data=>
            data.forEach(testcase => this.testcases.push(testcase))
            )
        )
    }
});


var app = new Vue({
    el: "#app",
    template: '<testcases-list :testcases="testcases"/>',
    data: {
        testcases: []
    }
});
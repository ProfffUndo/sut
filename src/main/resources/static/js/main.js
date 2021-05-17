var testcaseApi = Vue.resource('/testcase{/id}')

Vue.component('testcase-row',{
    props: ['testcase'],
    template:'<div><i>({{testcase.id}})</i>{{testcase.author}}</div>'
})

Vue.component('testcases-list', {
    props:['testcases'],
    template:'<div><testcase-row v-for="testcase in testcases" :key="testcase.id" :testcase="testcase" /></div>',
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
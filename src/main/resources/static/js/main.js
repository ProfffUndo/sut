Vue.component('testcases-list', {
    props:['testcases'],
    template:'<div><div v-for="testcase in testcases">{{testcase.author}}</div></div>'
});


var app = new Vue({
    el: "#app",
    template: '<testcases-list :testcases="testcases"/>',
    data: {
        testcases: [
            {id:'123', author:'Ivanov'},
            {id:'12', author:'Petrov'},
            {id:'23', author:'Sidorov'},
        ]
    }
});
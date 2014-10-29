require.config({
	baseUrl: "/assets/javascripts",
	shim: {
		jquery: {
			exports: [ '$' ]
		},
		bootstrap : {
			deps : [ 'jquery' ]
		}
	},
	paths : {
		jquery : "/webjars/jquery/2.1.1/jquery.min",
		bootstrap : '/webjars/bootstrap/3.2.0/js/bootstrap.min'
	}
});

require(["helper/Abc", "jquery", "bootstrap"],function(Abc, $) {
	var abc = new Abc();
	alert(abc.sum(4, 5));
	alert($(".test-button").text());
});

require.config({
	baseUrl: "/assets/javascripts",
	shim: {
		'jquery': {
			exports: [ '$' ]
		}
	},
	paths : {
		"jquery" : "/webjars/jquery/2.1.1/jquery.min"
	}
});

require(["helper/Abc", "jquery"],function(Abc, $) {
	var abc = new Abc();
	alert(abc.sum(4, 5));
	alert($(".test-button").text());
});

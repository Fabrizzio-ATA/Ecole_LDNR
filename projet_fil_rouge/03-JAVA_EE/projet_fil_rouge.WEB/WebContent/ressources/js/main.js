$(window).resize(function() {
	placementFooter();
});
$(window).load(function() {
	placementFooter();
});

function placementFooter() {
	var Hmain = $(window).height()-(
			$('header').outerHeight()
			+$('nav').outerHeight()
			+$('footer').outerHeight()
		);

	$('main').css('height', (Hmain-45));
}
$(function() {

	$('#roleTriger').click(function(e) {
		var obj = e.target;
		var url = obj.getAttribute("href");
		$.get(e.target.getAttribute("href"), function(responseText) {

		});

		var params = url.split('&');
		for (var i = 0; i < params.length; i++) {
			var hash = params[i].split('=');
			var key = hash[0];
			var value = hash[1];
			if (key == 'role') {
				if (value == 'admin') {
					// obj.parent().closest('td#role').text = 'user';
					obj.href = url.replace("role=admin", "role=user");
					value = 'user';
				} else {

					// obj.parent().closest('td#role').text = 'admin';
					obj.href = url.replace("role=user", "role=admin");
					value = 'admin';
				}
				obj.text = value;
			}
		}

		return false;
	});



$('body').on('mouseleave', '#searchBox', function() {
	$("#searchBox").remove();
});


$( "#searchTrail" ).focusout(function() {
  
  $(this).val("");
});

	$("#searchTrail").change(
			function() {
				var obj = $(this);

				if (obj.val().length >= 3) {
					var link = "/EpamRankTask4/search?search=" + obj.val();

					$.get(link, function(responseJson) {

						var $baseDiv = $('<div id="searchBox" class="list-group dropdown-toggle" >');

						var $li;
						var $div;
						var $h4;
						var $h6;
						var $span;
						var $a;
						
						$baseDiv.css('position', 'absolute');
						obj.after($baseDiv);
						$.each(responseJson, function(index, item) {
							$a = $(
									'<a class="list-group-item" href="/EpamRankTask4/user/account?action=index&id='
											+ item['id'] + '">').appendTo(
									$baseDiv);
							$div = $('<div class="span4">').appendTo($a);
							$h4 = $('<h4>').appendTo($div);
							$span = $('<span>').text(item['firstName'])
									.appendTo($h4);
							$h4.text(item['lastName']);
							$h6 = $('<h6>').text(item['email']).appendTo($div);
						});

					});

				}
				return false;
			});

});

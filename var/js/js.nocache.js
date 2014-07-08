function getParameterByName(name)
{
 return decodeURIComponent((new RegExp('[?|&]' + name + '=' + '([^&;]+?)(&|#|;|$)').exec(location.search)||[,""])[1].replace(/\+/g, '%20'))||"";
}

$(document).ready
(
	function()
	{
		xs = $("#navigation > li");
		status = getParameterByName("status");
		for(i = 0; i < xs.length; i++)
		{
			x = $(xs[i]);
			if(!status && x.data("status") == "ALL")
				x.addClass("active");
			else if(status && $(x).data("status") == status)
				x.addClass("active");
		}

		$(".modal-hook").click
		(
			function()
			{
				$("#proceed_link").attr("href",$(this).data("href"));
				$("#new_status").text($(this).data("desc"));
				$("#yesno").modal('show');
			}
		)
	}
)
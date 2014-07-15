function showhide()
{
	$("form > div").hide();
	$("form > [data-visible=all]").show();
	var selected = $("#delivery-type :selected").val();
	switch(selected)
	{
		case "DELIVERY":
		case "POST":
		{
			$("form > [data-visible=delivery]").show();
			break;
		}
		case "PICKUP":
		{
			$("form > [data-visible=pickup]").show();
			break;
		}

	}

}
$(document).ready
(
	function()
	{
		$("#delivery-type").change(showhide);
		showhide();
	}
);
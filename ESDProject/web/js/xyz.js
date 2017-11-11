window.onload = function () {
    $("#postcode-lookup").click(function () {
        var postcodeApi = "https://api.getAddress.io/find/";
        var postcode = $("input#postcode").val();
        var apiKey = "NNCd8ZNAlUOZ-4SJZ1lhxw11042"; // Group (for demo)
        //var apiKey = "eV4ppm_xFkCVVPM8VC9zXw11035"; //James
        //var apiKey = "6QpzXEzcP0yMHTJJwc6l0g11037"; //James 2
        //var apiKey = ""; //Matt
        //var apiKey = ""; //Kieran
        //var apiKey = ""; //Rach
        //var apiKey = ""; //Charlotte

        var ajaxUrl = postcodeApi + postcode + "?api-key=" + apiKey;

        $.ajax({
            url: ajaxUrl,
            dataType: "json",
            success: function (result) {
                // Populate address picker
                $("#available-addresses").empty();


                for (i = 0; i < result.addresses.length; i++) {
                    var address = result.addresses[i].replace(new RegExp(" ,", "g"), "");
                    var addressWithPostcode = address + ", " + postcode.toUpperCase().trim();
                    $("#available-addresses").append("<option value='" + addressWithPostcode + "'>" + address + "</option>")
                }

                // Show the address picker
                $("#address-lookup").show();
            },
            error: function (result) {
                //Hide the address picker
                $("#address-lookup").hide();

                // Set the textbox to be empty
                $("input#postcode").val("");

                // Display an invalid postcode message
                alert("Invalid postcode");
            }
        })
    });
};

window.onload = function () {
    
    $("table").DataTable();
    
    $("#postcode-lookup").click(function () {
        var postcodeApi = "https://api.getAddress.io/find/";
        var postcode = $("input#postcode").val();
        //var apiKey = "NNCd8ZNAlUOZ-4SJZ1lhxw11042"; // Group (for demo)
        //var apiKey = "eV4ppm_xFkCVVPM8VC9zXw11035"; //James
        //var apiKey = "6QpzXEzcP0yMHTJJwc6l0g11037"; //James 2
        var apiKey = "icuttQCAnUC-qeo_UwJEPQ11046"; //Matt
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
                
                //Remove error message
                $("#alertPostcode").remove();


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
                
                //Remove items in the select
                $("#available-addresses").empty();

                // Set the textbox to be empty
                $("input#postcode").val("");
                
                // Display an invalid postcode message
                
                $("div#alerts").append("<div class='alert alert-danger' id='alertPostcode' role='alert'>Please enter a valid postcode.</div>")
                //alert("Invalid postcode");
            }
        })
    });
};
